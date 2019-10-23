package br.ufjf.getsi.talktwome.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufjf.getsi.talktwome.DTO.ArquivoDTO;
import br.ufjf.getsi.talktwome.DTO.AudioProcessamentoDTO;
import br.ufjf.getsi.talktwome.Models.Arquivo;
import br.ufjf.getsi.talktwome.Models.Jogador;
import br.ufjf.getsi.talktwome.Models.Partida;
import br.ufjf.getsi.talktwome.Persistence.ArquivoRepository;
import br.ufjf.getsi.talktwome.Persistence.FirestoreLocator;
import br.ufjf.getsi.talktwome.Persistence.JogadorRepository;
import br.ufjf.getsi.talktwome.Persistence.PartidaRepository;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Controller
public class ArquivoController {

    @Autowired
    private PartidaRepository repositoryPartida;
    @Autowired
    private JogadorRepository repositoryJogador;
    @Autowired
    private ArquivoRepository repositoryArquivo;

    @RequestMapping(value = "/talk2me-upload-palavra", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public void PostPalavraPartida(@RequestParam("file") MultipartFile file, @RequestParam("partida") String jsonPartida,
        HttpServletResponse response) throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        ArquivoDTO arquivo = objectMapper.readValue(jsonPartida, ArquivoDTO.class);

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS).
                writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();

        File convertido = conversor(file);
 
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("word", arquivo.getPalavra())
                .addFormDataPart("audio", file.getOriginalFilename(),
                        RequestBody.create(MediaType.get("audio/wav"), convertido))
                .build();

        Request requestFinal = new Request.Builder()
                .url("http://200.131.17.29:5080/process-audio-game")
                .post(requestBody)
                .build();

        Response responseFinal = client.newCall(requestFinal).execute();
        
        String avaliacaoAudio = responseFinal.body().string();
        ObjectMapper objectMapper2 = new ObjectMapper();
        AudioProcessamentoDTO resultadoAudio = objectMapper2.readValue(avaliacaoAudio, AudioProcessamentoDTO.class);
        salvarComFireStore(convertido, arquivo, resultadoAudio.getResult());
    }

    private File conversor(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    @Async
    private void salvarComFireStore(File som, ArquivoDTO arquivo, Boolean corretude)
            throws FileNotFoundException, IOException {
        FirestoreLocator.getInstance().getEscrita();
        Bucket bucket = StorageClient.getInstance().bucket();

        int len = (int) som.length();
        byte[] sendBuf = new byte[len];
        FileInputStream inFile = null;
        try {
            inFile = new FileInputStream(som);
            inFile.read(sendBuf, 0, len);
            inFile.close();
        } catch (Exception ex) {
            throw ex;
        }

        Partida partida = repositoryPartida.getOne(arquivo.getIdPartida());
        Jogador jogador = repositoryJogador.getOne(arquivo.getIdJogador());

        /*BlobId blobId = BlobId.of("readergameserver.appspot.com", "partida" + partida.getId() +
         "/" + jogador.getId() + "/" + som.getName());*/
        BlobId blobId = BlobId.of("readergame-57416.appspot.com", "partida" + partida.getId() +
         "/" + "jogador" + jogador.getId() + "/" + som.getName());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("audio/wav").build();
        Blob blob = bucket.getStorage().create(blobInfo, sendBuf);

        Arquivo arquivoAux = new Arquivo();
        arquivoAux.setIdJogador(jogador.getId());
        arquivoAux.setIdPartida(partida.getId());
        arquivoAux.setPartida(partida);
        arquivoAux.setJogador(jogador);
        arquivoAux.setPalavra(arquivo.getPalavra());
        arquivoAux.setUrlDeUpload(blob.getMediaLink());
        repositoryArquivo.save(arquivoAux);
    }
}