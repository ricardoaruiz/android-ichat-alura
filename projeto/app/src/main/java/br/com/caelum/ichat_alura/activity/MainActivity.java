package br.com.caelum.ichat_alura.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.ichat_alura.R;
import br.com.caelum.ichat_alura.adapter.MensagemAdapter;
import br.com.caelum.ichat_alura.model.Mensagem;
import br.com.caelum.ichat_alura.service.ChatService;

public class MainActivity extends AppCompatActivity {

    private int idCliente =1;

    private ListView listaMensagens;
    private EditText txtMensagem;
    private Button btnEnviar;

    private List<Mensagem> mensagens;

    private ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatService = new ChatService(this);
        chatService.receber();

        txtMensagem = (EditText) findViewById(R.id.txtMensagem);

        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  chatService.enviar(new Mensagem(idCliente, txtMensagem.getText().toString()));
            }
        });

    }

    private void carregaLista(List<Mensagem> mensagens) {
        listaMensagens = (ListView) findViewById(R.id.lv_mensagem);
        listaMensagens.setAdapter(new MensagemAdapter(getBaseContext(), 1, mensagens));
    }

    public void adicionaMensagemNaLista(Mensagem mensagem) {
        if(mensagem != null) {
            if(mensagens == null) {
                mensagens = new ArrayList<>();
            }
            List<Mensagem> lista = new ArrayList<Mensagem>();
            lista.addAll(mensagens);
            lista.add(mensagem);
            mensagens = lista;

            carregaLista(mensagens);
        }
        chatService.receber();
    }



}
