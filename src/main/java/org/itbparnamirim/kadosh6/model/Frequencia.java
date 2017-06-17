package org.itbparnamirim.kadosh6.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Geraldo Jr
 */
@Entity
public class Frequencia implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    @Temporal(TemporalType.DATE)
    private Date dataAula;
    private boolean presenca;
    
    @ManyToOne
    private Matricula matricula;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Date getData() {
        return dataAula;
    }

    public void setData(Date data) {
        this.dataAula = data;
    }

    public boolean isPresenca() {
        return presenca;
    }

    public void setPresenca(boolean presenca) {
        this.presenca = presenca;
    }

    public Date getDataAula() {
        return dataAula;
    }

    public void setDataAula(Date dataAula) {
        this.dataAula = dataAula;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
    
}
