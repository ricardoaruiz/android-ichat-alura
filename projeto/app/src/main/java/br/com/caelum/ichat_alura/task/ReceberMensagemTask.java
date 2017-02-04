package br.com.caelum.ichat_alura.task;

import android.app.Activity;
import android.os.AsyncTask;

import br.com.caelum.ichat_alura.activity.MainActivity;
import br.com.caelum.ichat_alura.model.Mensagem;
import br.com.caelum.ichat_alura.service.ChatService;

/**
 * Created by ralmendro on 2/4/17.
 */

public class ReceberMensagemTask extends AsyncTask<Void, Void, Mensagem> {

//    private MainActivity activity;
//    private ChatService chatService;
//
//    public ReceberMensagemTask(MainActivity activity) {
//        this.activity = activity;
//        this.chatService = new ChatService();
//    }

    @Override
    protected Mensagem doInBackground(Void... params) {
        return null;
//        return chatService.receber();
    }

//    @Override
//    protected void onPostExecute(Mensagem mensagem) {
//        activity.adicionaMensagemNaLista(mensagem);
//    }

}
