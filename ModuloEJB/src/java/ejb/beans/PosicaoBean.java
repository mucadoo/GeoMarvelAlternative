package ejb.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ejb.entities.Posicao;
import ejb.entities.Usuario;
import ejb.interceptors.LogInterceptor;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors(LogInterceptor.class)
public class PosicaoBean implements PosicaoRemote {

    @PersistenceContext(unitName = "DerbyPU")
    private EntityManager em;

    public void save(Posicao p) {
        em.persist(p);
    }

    /*public List<Posicao> list(String login) {
        Query query = em.createQuery("FROM Posicao p where p.login='" + login + "'");
        List<Posicao> list = query.getResultList();
        return list;
    }*/
    
    public List<Posicao> list() {
        Query query = em.createQuery("FROM Posicao p");
        List<Posicao> list = query.getResultList();
        return list;
    }

}
