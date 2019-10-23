package br.ufjf.getsi.talktwome.Persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirestoreLocator {

    private static FirestoreLocator instance = new FirestoreLocator();
    private static Boolean visitado = false;

    public FirestoreLocator() {
    }

    public static FirestoreLocator getInstance() {
        return instance;
    }

    public void getEscrita() throws IOException {
        if (!visitado) {
            conectaEscrita();
            visitado = true;
        }
    }

    private void conectaEscrita() throws FileNotFoundException, IOException {
//        FileInputStream serviceAccount = new FileInputStream("C:\\Users\\Mateu\\Documents\\readergameserver-firebase-adminsdk-ai20d-222b19a7fc.json");
        FileInputStream serviceAccount = new FileInputStream("C:\\Users\\Mateu\\Documents\\readergame-57416-firebase-adminsdk-el9xt-32bd222949.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setStorageBucket("readergameserver.appspot.com")
                .setStorageBucket("readergame-57416.appspot.com")
                .build();

        FirebaseApp.initializeApp(options);
    }

}
