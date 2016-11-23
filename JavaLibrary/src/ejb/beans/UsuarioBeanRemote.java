package ejb.beans;

import ejb.entities.Personagem;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import ejb.entities.Usuario;

@Remote
public interface UsuarioBeanRemote {

    public Usuario criaUsuario(Usuario u);

    public List<Usuario> list();

    public Usuario buscaUsuarioPorId(final int id);

    public Collection buscaUsuarioPorNome(final String nome);

    public void removeUsuario(final int id);

    public void updateUsuario(Usuario user);

    public boolean autentica(String user, String senha);

    public Usuario alteraSenha(String usuario, String senha, String novaSenha);

    public String getHash(String senha);

    public Personagem pegaPersonagem(String nome);
}
