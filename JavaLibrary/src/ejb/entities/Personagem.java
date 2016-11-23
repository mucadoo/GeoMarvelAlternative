package ejb.entities;

import java.io.Serializable;

public class Personagem implements Serializable {

    private String nome;
    private String imagem;

    public Personagem(String nome, String imagem) {
        this.nome = nome;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
