package org.itbparnamirim.kadosh6.data;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import org.itbparnamirim.kadosh6.model.Noticia;

/**
 *
 * @author Geraldo Jr
 */
@Named
@RequestScoped
public class NoticiaDAO extends TemplateDAO {

    public NoticiaDAO() {
    }
    
    public Noticia save(Noticia noticia) throws IllegalStateException, SecurityException, SystemException{
        try {
            userTransaction.begin();
            if (noticia.getId() == null){
                em.persist(noticia);
            } else {
                em.merge(noticia);
            }
            userTransaction.commit();
        } catch (Exception e) {
            userTransaction.rollback();
        }
        return noticia;
    }
    
    public List<Noticia> list(){
        TypedQuery<Noticia> query = em.createNamedQuery("findAllNoticias", Noticia.class);
        return query.getResultList();
    }
    
    public void delete(Noticia noticia) throws IllegalStateException, SecurityException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException{
        try {
            userTransaction.begin();
            Noticia noticiaAdeletar = em.find(Noticia.class, noticia.getId());
            em.remove(noticiaAdeletar);
            userTransaction.commit();
        } catch (Exception e) {
            userTransaction.rollback();
        }
    }
    
    
}
