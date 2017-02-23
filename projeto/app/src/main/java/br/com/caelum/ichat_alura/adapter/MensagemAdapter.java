package br.com.caelum.ichat_alura.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.ichat_alura.R;
import br.com.caelum.ichat_alura.app.ChatApplication;
import br.com.caelum.ichat_alura.model.Mensagem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ralmendro on 2/3/17.
 */

public class MensagemAdapter extends BaseAdapter {

    private final List<Mensagem> mensagens;
    private final Activity activity;
    private final Integer idCliente;

    @BindView(R.id.texo_mensagem)
    TextView texto;

    @BindView(R.id.iv_avatar_msg)
    ImageView ivAvatarMsg;

    @Inject
    Picasso picasso;

    public MensagemAdapter(Activity activity, Integer idCliente, List<Mensagem> mensagens) {
        this.mensagens = mensagens;
        this.activity = activity;
        this.idCliente = idCliente;

        ChatApplication chatApplication = (ChatApplication) activity.getApplication();
        chatApplication.getChatComponent().inject(this);

    }

    @Override
    public int getCount() {
        return this.mensagens.size();
    }

    @Override
    public Mensagem getItem(int position) {
        return this.mensagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.mensagem, parent, false);
        }

        ButterKnife.bind(this, convertView);

        Mensagem mensagem = getItem(position);

        texto.setText(mensagem.getTexto());

        if(idCliente != mensagem.getId()){
            convertView.setBackgroundColor(Color.CYAN);
        }

        picasso.with(activity).load("https://api.adorable.io/avatars/285/" + mensagem.getId() + ".png").into(ivAvatarMsg);

        return convertView;
    }

}
