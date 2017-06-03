package org.itbparnamirim.kadosh6.data;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.itbparnamirim.kadosh6.model.Grupo;
import org.itbparnamirim.kadosh6.model.Membro;

@Named
@RequestScoped
public class GrupoDAO extends TemplateDAO{

    //Posso fazer isso? eh para setar o grupo de cada membro como null na hora de deletar
    @Inject
    MembroDAO membroDAO;

    public GrupoDAO() {

    }

    //Eh importante usar o mesmo metodo para inserir e atualizar para conseguir aproveitar a tela de cadastro para fazer edicoes
    public Grupo save(Grupo grupo) {
        try {
            userTransaction.begin();
            if (grupo.getId() == null) {
                em.persist(grupo);
            } else {
                em.merge(grupo);
            }
            userTransaction.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            e.printStackTrace();
        }
        return grupo;
    }

    public List<Grupo> list() {
        TypedQuery<Grupo> query = em.createNamedQuery("findAllGrupos", Grupo.class);
        return query.getResultList();
    }

    public void delete(Grupo grupo) throws IllegalStateException, SecurityException, SystemException, Exception{
        try{
            userTransaction.begin();
            Grupo gr = em.find(Grupo.class, grupo.getId());
            List<Membro> membrosDoGrupo = membroDAO.getMembrosDoGrupo(gr);
            for (Membro m: membrosDoGrupo){
                m.setGrupo(null);
            }
            em.remove(gr);
            userTransaction.commit();
        }catch(Exception e){
            userTransaction.rollback();
            throw new Exception("Houve um problema ao deletar o grupo");
        }
    }
}
