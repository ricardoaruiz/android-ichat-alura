package br.com.caelum.ichat_alura.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import br.com.caelum.ichat_alura.activity.MainActivity;
import br.com.caelum.ichat_alura.model.Mensagem;
import br.com.caelum.ichat_alura.service.ChatService;

/**
 * Created by ralmendro on 2/3/17.
 */

public class EnviarMensagemTask extends AsyncTask<Mensagem, Void, String> {

//    private MainActivity mainActivity;
//    private ProgressDialog processando;
//    private ChatService chatService;
//
//    public EnviarMensagemTask(MainActivity mainActivity) {
//        this.mainActivity = mainActivity;
//        this.chatService = new ChatService();
//    }
//
//   @Override
//    protected void onPreExecute() {
//        processando = ProgressDialog.show(mainActivity, "Processando", "Enviando mensagem", true, true);
//    }
//
    @Override
    protected String doInBackground(Mensagem... params) {
//        chatService.enviar(params[0]);
        return "";
    }
//
//    @Override
//    protected void onPostExecute(String aVoid) {
//        processando.dismiss();
//    }
}
