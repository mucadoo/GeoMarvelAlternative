package ejb.beans;

import ejb.entities.Posicao;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface PosicaoRemote {

    public List<Posicao> list();

}
