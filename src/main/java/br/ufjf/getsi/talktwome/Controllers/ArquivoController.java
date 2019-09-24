package br.ufjf.getsi.talktwome.Controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Controller
public class ArquivoController {

    @RequestMapping(value = "/talk2me-upload-palavra", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public void PostPalavraPartida(@RequestParam("file") MultipartFile file, @RequestParam("map") String jsonMap,
        HttpServletResponse response) throws Exception
    {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS).
                writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();

        File convertido = conversor(file);
 
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("word", jsonMap)
                .addFormDataPart("audio", file.getOriginalFilename(),
                        RequestBody.create(MediaType.get("audio/wav"), convertido))
                .build();

        Request requestFinal = new Request.Builder()
                .url("http://200.131.17.29:5080/process-audio-game")
                .post(requestBody)
                .build();

        Response responseFinal = client.newCall(requestFinal).execute();
        
        System.out.println(responseFinal.message());
        System.out.println(responseFinal.body().string());
    }

    private File conversor(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}