package org.itbparnamirim.kadosh6.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import org.itbparnamirim.kadosh6.model.Grupo;
import org.itbparnamirim.kadosh6.model.Membro;
import org.itbparnamirim.kadosh6.model.Ministerio;

@Named
@RequestScoped
public class MembroDAO extends TemplateDAO{

    public Membro save(Membro membro) throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        userTransaction.begin();
        if (membro.getId() == null) {
            em.persist(membro);
        } else {
            em.merge(membro);
        }
        userTransaction.commit();
        return membro;
    }

    public List<Membro> list() {
        TypedQuery<Membro> query = em.createQuery("select m from Membro m",Membro.class);
        return query.getResultList();
    }

    public void delete(Membro membro) throws SystemException, Exception {
        try {
            userTransaction.begin();
            Membro m = em.find(Membro.class, membro.getId());
            em.remove(m);
            userTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            userTransaction.rollback();
            throw new Exception("Houve um erro ao deletar o membro");
        }
    }

    public List<Membro> getMembrosDoGrupo(Grupo grupo) {
        TypedQuery<Membro> query = em.createQuery("Select m from Membro m where m.grupo=:grupo", Membro.class);
        query.setParameter("grupo", grupo);
        return query.getResultList();
    }
    
    public Membro getMembroById(Integer id){
        return em.find(Membro.class, id);
    }

    /**
     * Talvez a situação não aconteça, pois para uma pessoa ser considerada
     * Membro é preciso estar num grupo
     *
     * @return
     */
    public List<Membro> getMembrosSemGrupo() {
        TypedQuery<Membro> query = em.createQuery("Select m from Membro m where m.grupo=:grupo", Membro.class);
        query.setParameter("grupo", null);
        return query.getResultList();
    }

    public List<Membro> getMembrosDoMinisterio(Ministerio ministerio) {
        TypedQuery<Membro> query;
        query = em.createQuery("select m from Membro m inner join m.ministerios d where d = :ministerio", Membro.class);
        query.setParameter("ministerio", ministerio);
        return query.getResultList();
    }

    public List<Membro> getMembrosNaoNesseMinisterio(Ministerio ministerio) {
        List<Membro> todos = list();
        List<Membro> membrosMinisterio = getMembrosDoMinisterio(ministerio);
        List<Membro> naoDoMinisterio = new ArrayList<>();
        for (Membro membro: todos){
            if (!membrosMinisterio.contains(membro)){
                naoDoMinisterio.add(membro);
            }
        }
        return naoDoMinisterio;
    }

    public List<Membro> membrosLideres() {
        TypedQuery<Membro> query = em.createQuery("Select m from Membro m where m.lider = :parametroLider", Membro.class);
        query.setParameter("parametroLider", true);
        return query.getResultList();
    }

}
