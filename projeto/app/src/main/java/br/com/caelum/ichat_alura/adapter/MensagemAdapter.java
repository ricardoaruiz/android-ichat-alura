package br.com.caelum.ichat_alura.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.caelum.ichat_alura.R;
import br.com.caelum.ichat_alura.model.Mensagem;

/**
 * Created by ralmendro on 2/3/17.
 */

public class MensagemAdapter extends BaseAdapter {

    private final List<Mensagem> mensagens;
    private final Context context;
    private final Integer idCliente;

    public MensagemAdapter(Context context, Integer idCliente, List<Mensagem> mensagens) {
        this.mensagens = mensagens;
        this.context = context;
        this.idCliente = idCliente;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.mensagem, parent, false);
        }

        Mensagem mensagem = getItem(position);
        TextView texto = (TextView) convertView.findViewById(R.id.texo_mensagem);
        texto.setText(mensagem.getTexto());

        if(idCliente != mensagem.getId()){
            convertView.setBackgroundColor(Color.CYAN);
        }

        return convertView;
    }

}
