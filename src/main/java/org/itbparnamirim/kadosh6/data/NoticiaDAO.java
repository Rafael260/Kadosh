package org.itbparnamirim.kadosh6.data;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import org.itbparnamirim.kadosh6.model.Noticia;

@Named
@RequestScoped
public class NoticiaDAO extends TemplateDAO{

    public NoticiaDAO() {

    }
    //Eh importante usar o mesmo metodo para inserir e atualizar para conseguir aproveitar a tela de cadastro para fazer edicoes
    public Noticia save(Noticia noticia) {
        try {
            userTransaction.begin();
            if (noticia.getId() == null) {
                em.persist(noticia);
            } else {
                em.merge(noticia);
            }
            userTransaction.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            e.printStackTrace();
        }
        return noticia;
    }

    public List<Noticia> list() {
        TypedQuery<Noticia> query = em.createNamedQuery("findAllNoticias", Noticia.class);
        return query.getResultList();
    }
    
    public Noticia find(Integer id){
        return em.find(Noticia.class, id);
    }

    public void delete(Noticia noticia) throws IllegalStateException, SecurityException, SystemException, Exception{
        try{
            userTransaction.begin();
            Noticia not = em.find(Noticia.class, noticia.getId());
            em.remove(not);
            userTransaction.commit();
        }catch(Exception e){
            userTransaction.rollback();
            throw new Exception("Houve um problema ao deletar a notícia");
        }
    }
}
