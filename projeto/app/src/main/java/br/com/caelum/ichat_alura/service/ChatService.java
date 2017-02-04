package br.com.caelum.ichat_alura.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import br.com.caelum.ichat_alura.activity.MainActivity;
import br.com.caelum.ichat_alura.model.Mensagem;

/**
 * Created by ralmendro on 2/3/17.
 */
public class ChatService {

    private static final String URI_POLLING = "http://192.168.6.17:8080/polling";

    private MainActivity activity;

    public ChatService(MainActivity activity) {
        this.activity = activity;
    }

    public void enviar(final Mensagem mensagem) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String texto = mensagem.getTexto();

                try {
                    URL url = new URL(URI_POLLING);
                    HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                    httpConnection.setRequestMethod("POST");
                    httpConnection.setRequestProperty("content-type", "application/json");

                    JSONStringer json = new JSONStringer();
                    json.object()
                            .key("text").value(texto)
                            .key("id").value(mensagem.getId())
                            .endObject();

                    OutputStream outputStream = httpConnection.getOutputStream();
                    PrintStream ps = new PrintStream(outputStream);
                    ps.println(json.toString());

                    httpConnection.connect();
                    httpConnection.getInputStream();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }

    public void receber() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(URI_POLLING);
                    HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                    httpConnection.setRequestMethod("GET");
                    httpConnection.setRequestProperty("Accept", "application/json");

                    httpConnection.connect();
                    InputStream inputStream = httpConnection.getInputStream();

                    Scanner scanner = new Scanner(inputStream);
                    StringBuilder json = new StringBuilder();
                    while(scanner.hasNextLine()) {
                        json.append(scanner.nextLine());
                    }

                    final JSONObject jsonObject = new JSONObject(json.toString());
                    if(jsonObject != null && !jsonObject.get("id").equals(null) && !jsonObject.get("text").equals(null) ) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    activity.adicionaMensagemNaLista(new Mensagem(jsonObject.getInt("id"), jsonObject.getString("text")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
