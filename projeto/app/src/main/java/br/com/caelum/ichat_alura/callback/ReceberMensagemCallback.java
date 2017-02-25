package br.com.caelum.ichat_alura.callback;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import br.com.caelum.ichat_alura.activity.MainActivity;
import br.com.caelum.ichat_alura.model.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ralmendro on 2/4/17.
 */

public class ReceberMensagemCallback implements Callback<Mensagem> {

    private Context context;

    public ReceberMensagemCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if(response.isSuccessful()) {
            Mensagem mensagem = response.body();

            Intent intent = new Intent("nova_mensagem");
            intent.putExtra("mensagem", mensagem);

            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
            localBroadcastManager.sendBroadcast(intent);
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {

    }

}
