package org.itbparnamirim.kadosh6.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * Classe responsável por gerenciar os ministérios da igreja
 * @author Geraldo
 */
@Entity
@NamedQuery(name = "findAllMinisterios", query = "SELECT m FROM Ministerio m")
public class Ministerio implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String descricao;
    @ManyToOne
    private Membro lider;
    
    /**
     * Ministério possui uma lista de membros
     */
    @ManyToMany(fetch = FetchType.EAGER, mappedBy="ministerios", cascade=CascadeType.ALL)
    private List<Membro>membrosMinisterio = new ArrayList();

    public Ministerio() {
    }

    public Ministerio(Integer id, String nome, String descricao, Membro lider) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.lider = lider;
    }

    public void setMembrosDoMinisterio (List<Membro> membros){
        this.membrosMinisterio = membros;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Membro getLider() {
        return lider;
    }

    public void setLider(Membro lider) {
        this.lider = lider;
    }

    public List<Membro> getMembrosMinisterio() {
        return membrosMinisterio;
    }

    public void setMembrosMinisterio(List<Membro> membrosMinisterio) {
        this.membrosMinisterio = membrosMinisterio;
    }
    
    public void adicionarMembro (Membro membro){
        this.membrosMinisterio.add(membro);
        membro.adicionarMinisterio(this);
    }
    
    public void removerMembro (Membro membro){
        this.membrosMinisterio.remove(membro);
        membro.removerMinisterio(this);
    }
    
    @Override
    public String toString(){
        return this.nome;
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
        final Ministerio other = (Ministerio) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
}
