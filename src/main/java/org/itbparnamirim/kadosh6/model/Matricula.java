package org.itbparnamirim.kadosh6.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Geraldo Jr
 */
@Entity
public class Matricula implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Membro aluno;
    private BigDecimal nota;
    @ManyToOne
    private Turma turma;
    @OneToMany
    private List<Frequencia> frequencia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Membro getAluno() {
        return aluno;
    }

    public void setAluno(Membro aluno) {
        this.aluno = aluno;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Frequencia> getAssiduidade() {
        return frequencia;
    }

    public void setAssiduidade(List<Frequencia> frequencia) {
        this.frequencia = frequencia;
    }
    
}
