/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itbparnamirim.kadosh6.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author rafao
 */
@Entity
public class Meditacao implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String titulo;
    private String versiculoBase;
    private String aprofundando;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    private List<String> versiculosDeApoio;
    
    private String ilustracao;
    
    //Essa anotacao serve para nao colocar FetchType.EAGER para mais de um atributo na mesma classe
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    private List<String> decisoes;
    
    private String diaSemana;
    
    public Meditacao(){
        this.versiculosDeApoio = new ArrayList<>();
        this.decisoes = new ArrayList<>();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getVersiculoBase() {
        return versiculoBase;
    }

    public void setVersiculoBase(String versiculoBase) {
        this.versiculoBase = versiculoBase;
    }

    public String getAprofundando() {
        return aprofundando;
    }

    public void setAprofundando(String aprofundando) {
        this.aprofundando = aprofundando;
    }

    public List<String> getVersiculosDeApoio() {
        return versiculosDeApoio;
    }

    public void setVersiculosDeApoio(List<String> versiculosDeApoio) {
        this.versiculosDeApoio = versiculosDeApoio;
    }

    public String getIlustracao() {
        return ilustracao;
    }

    public void setIlustracao(String ilustracao) {
        this.ilustracao = ilustracao;
    }

    public List<String> getDecisoes() {
        return decisoes;
    }

    public void setDecisoes(List<String> decisoes) {
        this.decisoes = decisoes;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }
    
    public void adicionarVersiculoDeApoio(String versiculoDeApoio){
        this.versiculosDeApoio.add(versiculoDeApoio);
    }
    
    public void removerVersiculoDeApoio(String versiculoDeApoio){
        this.versiculosDeApoio.remove(versiculoDeApoio);
    }
    
    public void adicionarDecisao(String decisao){
        this.decisoes.add(decisao);
    }
    
    public void removerDecisao(String decisao){
        this.decisoes.remove(decisao);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meditacao)) {
            return false;
        }
        Meditacao other = (Meditacao) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return this.titulo + " - " + this.diaSemana;
    }
}