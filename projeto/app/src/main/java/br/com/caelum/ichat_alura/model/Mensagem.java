package br.com.caelum.ichat_alura.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ralmendro on 2/3/17.
 */
public class Mensagem {

    private Integer id;

    @SerializedName("text")
    private String texto;

    public Mensagem(Integer id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

}
