package br.com.caelum.ichat_alura.callback;

import br.com.caelum.ichat_alura.activity.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ralmendro on 2/4/17.
 */
public class EnviarMensagemCallback implements Callback<Void> {

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {

    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {

    }
}
