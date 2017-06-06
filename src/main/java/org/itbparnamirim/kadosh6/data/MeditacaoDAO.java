/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.itbparnamirim.kadosh6.model.Meditacao;

@Named
@RequestScoped
public class MeditacaoDAO extends TemplateDAO{
    
    public Meditacao save(Meditacao meditacao){
        try {
            userTransaction.begin();
            if (meditacao.getId() == null) {
                em.persist(meditacao);
            } else {
                em.merge(meditacao);
            }
            userTransaction.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
        }
        return meditacao;
    }
    
    public List<Meditacao> list() {
        TypedQuery<Meditacao> query = em.createQuery("Select m from Meditacao m", Meditacao.class);
        List<Meditacao> meditacoes = query.getResultList();
        return meditacoes;
    }
    
    public void delete(Meditacao meditacao) throws IllegalStateException, SecurityException, SystemException, Exception{
        try{
            userTransaction.begin();
            Meditacao m = em.find(Meditacao.class, meditacao.getId());
            em.remove(m);
            userTransaction.commit();
        }catch(Exception e){
            userTransaction.rollback();
            throw new Exception("Houve um problema ao deletar a meditacao");
        }
    }
}
