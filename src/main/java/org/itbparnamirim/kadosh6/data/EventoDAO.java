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
import org.itbparnamirim.kadosh6.model.Evento;

@Named
@RequestScoped
public class EventoDAO extends TemplateDAO{

    public EventoDAO() {

    }
    //Eh importante usar o mesmo metodo para inserir e atualizar para conseguir aproveitar a tela de cadastro para fazer edicoes
    public Evento save(Evento evento) {
        try {
            userTransaction.begin();
            if (evento.getId() == null) {
                em.persist(evento);
            } else {
                em.merge(evento);
            }
            userTransaction.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            e.printStackTrace();
        }
        return evento;
    }

    public List<Evento> list() {
        TypedQuery<Evento> query = em.createQuery("Select e from Evento e", Evento.class);
        return query.getResultList();
    }
    
    public Evento find(Integer id){
        return em.find(Evento.class, id);
    }

    public void delete(Evento evento) throws IllegalStateException, SecurityException, SystemException, Exception{
        try{
            userTransaction.begin();
            Evento ev = em.find(Evento.class, evento.getId());
            em.remove(ev);
            userTransaction.commit();
        }catch(Exception e){
            userTransaction.rollback();
            throw new Exception("Houve um problema ao deletar o grupo");
        }
    }
}
