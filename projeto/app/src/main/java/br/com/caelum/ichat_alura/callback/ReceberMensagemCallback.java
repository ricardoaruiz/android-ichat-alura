package br.com.caelum.ichat_alura.callback;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import org.greenrobot.eventbus.EventBus;

import br.com.caelum.ichat_alura.activity.MainActivity;
import br.com.caelum.ichat_alura.event.MensagemEvent;
import br.com.caelum.ichat_alura.model.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ralmendro on 2/4/17.
 */

public class ReceberMensagemCallback implements Callback<Mensagem> {

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if(response.isSuccessful()) {
            Mensagem mensagem = response.body();
            EventBus.getDefault().post(new MensagemEvent(mensagem));
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {

    }

}
