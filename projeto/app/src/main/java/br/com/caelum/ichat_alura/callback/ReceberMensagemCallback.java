package br.com.caelum.ichat_alura.callback;

import br.com.caelum.ichat_alura.activity.MainActivity;
import br.com.caelum.ichat_alura.model.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ralmendro on 2/4/17.
 */

public class ReceberMensagemCallback implements Callback<Mensagem> {

    private MainActivity mainActivity;

    public ReceberMensagemCallback(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if(response.isSuccessful()) {
            Mensagem mensagem = response.body();
            mainActivity.adicionaMensagemNaLista(mensagem);
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
        mainActivity.receberMensagens();
    }

}
