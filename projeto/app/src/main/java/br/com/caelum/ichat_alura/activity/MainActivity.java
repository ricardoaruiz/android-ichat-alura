package br.com.caelum.ichat_alura.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.ichat_alura.R;
import br.com.caelum.ichat_alura.adapter.MensagemAdapter;
import br.com.caelum.ichat_alura.model.Mensagem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listaMensagens = (ListView) findViewById(R.id.lv_mensagem);

        List<Mensagem> mensagens = Arrays.asList(new Mensagem(1, "blá"), new Mensagem(2, "blé"));

        listaMensagens.setAdapter(new MensagemAdapter(getBaseContext(), 1, mensagens));
    }
}
