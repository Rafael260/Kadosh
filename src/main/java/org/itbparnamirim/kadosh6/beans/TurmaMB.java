/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itbparnamirim.kadosh6.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.transaction.SystemException;
import org.itbparnamirim.kadosh6.data.TurmaDAO;
import org.itbparnamirim.kadosh6.model.Turma;

/**
 *
 * @author rafao
 */
@Named
@SessionScoped
public class TurmaMB implements Serializable{

    private Turma turma = new Turma();
    private List<Turma> turmas = new ArrayList<>();
    @Inject TurmaDAO turmaDAO;
    
    /**
     * Creates a new instance of TurmaMB
     */
    public TurmaMB() {
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
    
    public void carregarLista(){
        this.turmas = turmaDAO.list();
    }
    
    public String prepararConsulta(Turma turma){
        this.turma = turma;
        return "/pages/turma/detalharTurma.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararEdicao(Integer id){
        Turma turmaSelecionada = turmaDAO.find(id);
        this.turma = turmaSelecionada;
        return "/pages/turma/cadastroTurma.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararCadastro(){
        limparObjetos();
        return "/pages/turma/cadastroTurma.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public void limparObjetos(){
        this.turma = new Turma();
    }
    
    public String deletar(Turma turma){
        try {
            turmaDAO.delete(turma);
        } catch (SecurityException ex) {
            Logger.getLogger(TurmaMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(TurmaMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TurmaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        ManagedBeanUtil.refresh();
        return "";
    }
    
    public String salvar(){
        turmaDAO.save(turma);
        limparObjetos();
        return "/pages/turma/exibirTurmas.xhtml"+ManagedBeanUtil.REDIRECT;
    }
}
