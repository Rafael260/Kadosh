package org.itbparnamirim.kadosh6.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import org.itbparnamirim.kadosh6.data.GrupoDAO;
import org.itbparnamirim.kadosh6.data.MembroDAO;
import org.itbparnamirim.kadosh6.model.Grupo;
import org.itbparnamirim.kadosh6.model.Membro;

/**
 *
 * @author geral_001
 */
@SessionScoped
@ManagedBean
public class MembroMB implements Serializable {

    @Inject
    MembroDAO membroDAO;
    
    @Inject
    GrupoDAO grupoDAO;
    
    private Membro membro = new Membro();
    private List<Membro> membros = new ArrayList<>();

    public void carregarLista() {
        this.membros = membroDAO.list();
    }
    
    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    public List<Membro> getMembros() {
        return membros;
    }

    public void setMembros(List<Membro> membros) {
        this.membros = membros;
    }

    public String salvar() {
        String paginaDestino = "/pages/exibirMembros.xhtml";
        try {
            boolean cadastrando = membro.getId() == null;
            membro = membroDAO.save(membro);
            if (cadastrando){
                membros.add(membro);
            }
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException ex) {
            paginaDestino = "/pages/dashboardAdmin.xhml";
        }
        limparObjetos();
        return paginaDestino+ManagedBeanUtil.REDIRECT;
    }

    public String deletar(Membro membro) {
        String paginaDestino = "/pages/exibirMembros.xhtml";
        try {
            Grupo grupo = membro.getGrupo();
            if (grupo.getLider().equals(membro)){
                grupo.setLider(null);
                grupoDAO.save(grupo);
            }
            membroDAO.delete(membro);
            ManagedBeanUtil.refresh();
        } catch (Exception ex) {
            paginaDestino = "/pages/dashboardAdmin.xhtml";
        }
        return paginaDestino+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararEdicao(Membro membro){
        this.membro = membro;
        return "/pages/cadastroMembro.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararConsulta(Membro membro){
        this.membro = membro;
        return "/pages/detalharMembro.xhtml"+ManagedBeanUtil.REDIRECT;
    }

    private void limparObjetos(){
        this.membro = new Membro();
    }
    
    public String prepararCadastro(){
        limparObjetos();
        return "/pages/cadastroMembro.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public List<Membro> listarLideres(){
        return membroDAO.membrosLideres();
    }
}
