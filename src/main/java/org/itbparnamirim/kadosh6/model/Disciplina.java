/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itbparnamirim.kadosh6.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "findAllDisciplinas", query = "SELECT d FROM Disciplina d")
public class Disciplina implements Serializable {
    
    @GeneratedValue
    @Id
    private Integer id;
    private String nome;
    private String sigla;
    private String ciclo;
    
    @OneToMany
    private List<Turma> turmas;

    public Disciplina() {
        turmas = new ArrayList<>();
    }

    public Disciplina(Integer id, String nome, String sigla, String ciclo, List<Turma> turmas) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.ciclo = ciclo;
        this.turmas = turmas;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }
    
    @Override
    public String toString(){
        return this.sigla + " - " + this.nome + " ciclo: "+ this.ciclo;
    }
    
    @Override
    public boolean equals(Object outra){
        if (!(outra instanceof Disciplina)){
            return false;
        }
        Disciplina outraDisciplina = (Disciplina) outra;
        return this == outraDisciplina || this.getId().equals(outraDisciplina.getId());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
}
