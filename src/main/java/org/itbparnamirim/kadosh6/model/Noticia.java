package org.itbparnamirim.kadosh6.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author Geraldo Jr
 */
@Entity
@NamedQuery(name = "findAllNoticias", query = "SELECT n FROM Noticia n")
public class Noticia implements Serializable {
    
    @GeneratedValue
    @Id
    private Integer id;
    private String manchete;
    private String textoDaNoticia;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date validade;
    private String destinatario;

    public Noticia(Integer id, String manchete, String textoDaNoticia, Date validade, String destinatario) {
        this.id = id;
        this.manchete = manchete;
        this.textoDaNoticia = textoDaNoticia;
        this.validade = validade;
        this.destinatario = destinatario;
    }

    public Noticia() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManchete() {
        return manchete;
    }

    public void setManchete(String manchete) {
        this.manchete = manchete;
    }

    public String getTextoDaNoticia() {
        return textoDaNoticia;
    }

    public void setTextoDaNoticia(String textoDaNoticia) {
        this.textoDaNoticia = textoDaNoticia;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Noticia other = (Noticia) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
}
