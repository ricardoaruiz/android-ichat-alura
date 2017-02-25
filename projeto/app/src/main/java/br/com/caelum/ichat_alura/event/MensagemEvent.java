package br.com.caelum.ichat_alura.event;

import br.com.caelum.ichat_alura.model.Mensagem;

/**
 * Created by ralmendro on 25/02/17.
 */
public class MensagemEvent {

    private final Mensagem mensagem;

    public MensagemEvent(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }
}
