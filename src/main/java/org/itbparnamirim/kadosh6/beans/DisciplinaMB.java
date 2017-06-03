/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itbparnamirim.kadosh6.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.NotSupportedException;
import org.itbparnamirim.kadosh6.data.DisciplinaDAO;
import org.itbparnamirim.kadosh6.model.Disciplina;

@ManagedBean
@SessionScoped
public class DisciplinaMB implements Serializable{
    
    private Disciplina disciplina = new Disciplina();
    private List<Disciplina> disciplinas = new ArrayList<>();
    @Inject DisciplinaDAO disciplinaDAO;
//    private boolean cadastrando = true;
    
    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    
    public String salvar(){
        String paginaDestino = "/pages/exibirDisciplinas.xhtml";
        try {
            disciplina = disciplinaDAO.save(disciplina);
            limparObjetos();
        } catch (NotSupportedException ex) {
            paginaDestino = "/pages/dashboardAdmin.xhml";
        }
        return paginaDestino+ManagedBeanUtil.REDIRECT;
    }
    
    public String deletar(Disciplina disciplina) {
        System.out.println("Deletando disciplina....");
        String paginaDestino = "/pages/exibirDisciplinas.xhtml";
        try {
            disciplinaDAO.delete(disciplina);
            ManagedBeanUtil.refresh();
        } catch (Exception ex) {
            paginaDestino = "/pages/dashboardAdmin.xhtml";
        }
        return paginaDestino+ManagedBeanUtil.REDIRECT;
    }
    
    public void carregarLista(){
        disciplinas = disciplinaDAO.list();
    }
    
    public String prepararConsulta(Disciplina disciplina){
        this.disciplina = disciplina;
        return "/pages/detalharDisciplina.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararEdicao(Disciplina disciplina){
        this.disciplina = disciplina;
//        this.cadastrando = false;
        return "/pages/cadastroDisciplina.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararCadastro(){
        limparObjetos();
//        this.cadastrando = true;
        return "/pages/cadastroDisciplina.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    private void limparObjetos(){
        this.disciplina = new Disciplina();
    }
}
