package org.itbparnamirim.kadosh6.data;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.itbparnamirim.kadosh6.model.Membro;
import org.itbparnamirim.kadosh6.model.Turma;

/**
 *
 * @author Geraldo
 */
@Named
@RequestScoped
public class TurmaDAO extends TemplateDAO {

    public TurmaDAO() {
    }

    public Turma save(Turma turma) {
        try {
            userTransaction.begin();
            if (turma.getId() == null) {
                em.persist(turma);
            } else {
                em.merge(turma);
            }
            userTransaction.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            e.printStackTrace();
        }
        return turma;
    }

    public Turma find(Integer id) {
        Turma turma = em.find(Turma.class, id);
        return turma;
    }

    public List<Turma> list() {
        TypedQuery<Turma> query = em.createQuery("select t from Turma t", Turma.class);
        List<Turma> turmas = query.getResultList();
        return turmas;
    }

    public void delete(Turma turma) throws IllegalStateException, SecurityException, SystemException, Exception {
        try {
            userTransaction.begin();
            Turma t = em.find(Turma.class, turma.getId());
            em.remove(t);
            userTransaction.commit();
        } catch (Exception e) {
            userTransaction.rollback();
            throw new Exception("Houve um problema ao deletar a turma");
        }
    }

    public List<Membro> buscarProfessorPorNome(String nome) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Membro> criteriaQuery = builder.createQuery(Membro.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<Membro> membroRoot = criteriaQuery.from(Membro.class);

        if (StringUtils.isNotBlank(nome)) {
            predicates.add(builder.equal(membroRoot.get("nome"), nome));
        }

        if (StringUtils.isNotBlank(nome)) {
            predicates.add(builder.like(membroRoot.<String>get("nome"),
                    "%" + nome.toLowerCase() + "%"));
        }

        criteriaQuery.select(membroRoot);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Membro> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

}
