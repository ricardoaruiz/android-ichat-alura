package br.com.caelum.ichat_alura.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.ichat_alura.R;
import br.com.caelum.ichat_alura.adapter.MensagemAdapter;
import br.com.caelum.ichat_alura.app.ChatApplication;
import br.com.caelum.ichat_alura.callback.EnviarMensagemCallback;
import br.com.caelum.ichat_alura.callback.ReceberMensagemCallback;
import br.com.caelum.ichat_alura.component.ChatComponent;
import br.com.caelum.ichat_alura.event.FailureEvent;
import br.com.caelum.ichat_alura.event.MensagemEvent;
import br.com.caelum.ichat_alura.model.Mensagem;
import br.com.caelum.ichat_alura.service.ChatService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private int idCliente =1;

    @BindView(R.id.lv_mensagem)
    ListView listaMensagens;

    @BindView(R.id.txtMensagem)
    EditText txtMensagem;

    @BindView(R.id.btnEnviar)
    Button btnEnviar;

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;

    private List<Mensagem> mensagens;

    @Inject
    ChatService chatService;

    @Inject
    Picasso picasso;

    @Inject
    EventBus eventBus;

    @Inject
    InputMethodManager imm;

    ChatComponent chatComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ChatApplication chatApplication = (ChatApplication) getApplication();
        chatComponent = chatApplication.getChatComponent();
        chatComponent.inject(this);

        picasso.with(this).load("https://api.adorable.io/avatars/285/" + idCliente + ".png").into(ivAvatar);

        receberMensagens(null);

        eventBus.register(this);

        if(savedInstanceState != null) {
            mensagens = (List<Mensagem>) savedInstanceState.getSerializable("mensagens");
        } else {
            mensagens = new ArrayList<Mensagem>();
        }

        carregaLista(mensagens);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("mensagens", (ArrayList<Mensagem>) mensagens);
    }

    @OnClick(R.id.btnEnviar)
    public void enviarMensagem() {
        chatService.enviar(new Mensagem(idCliente, txtMensagem.getText().toString())).enqueue(new EnviarMensagemCallback());
        txtMensagem.getText().clear();
        imm.hideSoftInputFromWindow(txtMensagem.getWindowToken(), 0);
    }

    private void carregaLista(List<Mensagem> mensagens) {
        listaMensagens.setAdapter(new MensagemAdapter(this, 1, mensagens));
    }

    @Subscribe
    public void adicionaMensagemNaLista(MensagemEvent mensagemEvent) {
        if(mensagemEvent != null) {
            if(mensagens == null) {
                mensagens = new ArrayList<>();
            }
            List<Mensagem> lista = new ArrayList<Mensagem>();
            lista.addAll(mensagens);
            lista.add(mensagemEvent.getMensagem());
            mensagens = lista;

            carregaLista(mensagens);
        }
    }

    @Subscribe
    public void receberMensagens(MensagemEvent mensagemEvent) {
        chatService.receber().enqueue(new ReceberMensagemCallback(eventBus));
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Subscribe
    public void lidarCom(FailureEvent event) {
        receberMensagens(null);
    }
}
