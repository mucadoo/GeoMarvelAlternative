package ejb.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    @Id
    @Column(name = "usuario_id")
    @SequenceGenerator(name = "usuarioGenerator", sequenceName = "usuario_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioGenerator")
    private int id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "sobrenome")
    private String sobrenome;
    @Column(name = "login")
    private String login;
    @Column(name = "hash")
    private String hash;
    @Column(name = "personagem_marvel")
    private String personagemMarvel;

    public Usuario() {
    }

    public Usuario(int id, String nome, String sobrenome, String login, String hash) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.login = login;
        this.hash = hash;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPersonagemMarvel() {
        return personagemMarvel;
    }

    public void setPersonagemMarvel(String personagemMarvel) {
        this.personagemMarvel = personagemMarvel;
    }

}
