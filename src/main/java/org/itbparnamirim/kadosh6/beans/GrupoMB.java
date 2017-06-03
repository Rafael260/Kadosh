package org.itbparnamirim.kadosh6.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;
import org.itbparnamirim.kadosh6.data.GrupoDAO;
import org.itbparnamirim.kadosh6.model.Grupo;
import org.itbparnamirim.kadosh6.model.Membro;

/**
 *
 * @author geral_001
 */
@SessionScoped
@ManagedBean
public class GrupoMB implements Serializable {

    //Atributo auxiliar para recuperar dados do formulario
    private Grupo grupo = new Grupo();
    private List<Grupo> grupos = new ArrayList<>();
    private boolean cadastrando = true;
    
    @Inject
    GrupoDAO grupoDAO;

    public void carregarLista() {
        this.grupos = grupoDAO.list();
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public boolean isCadastrando() {
        return cadastrando;
    }

    public void setCadastrando(boolean cadastrando) {
        this.cadastrando = cadastrando;
    }

    public String salvar() {
        grupo = grupoDAO.save(grupo);
        limparObjetos();
        return "/pages/exibirGrupos.xhtml"+ManagedBeanUtil.REDIRECT;
    }

    private void limparObjetos() {
        this.grupo = new Grupo();
    }

    public String deletar(Grupo grupo) {
        String paginaDestino = "/pages/exibirGrupos.xhtml";
        try {
            grupoDAO.delete(grupo);
            ManagedBeanUtil.refresh();
        } catch (Exception ex) {
            paginaDestino = "dashboardAdmin";
        }
        return paginaDestino+ManagedBeanUtil.REDIRECT;
    }

    public String prepararEdicao(Grupo grupo) {
        this.grupo = grupo;
        this.cadastrando = false;
        return "/pages/cadastroGrupo.xhtml"+ManagedBeanUtil.REDIRECT;
    }

    public String prepararCadastro() {
        this.cadastrando = true;
        limparObjetos();
        return "/pages/cadastroGrupo.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararConsulta(Grupo grupo){
        this.grupo = grupo;
        return "/pages/detalharGrupo.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public List<Membro> getLideresDoGrupo(){
        List<Membro> membros = this.grupo.getMembrosGrupo();
        Iterator<Membro> it = membros.iterator();
        Membro membro;
        while (it.hasNext()){
            membro = it.next();
            if (!membro.getLider()){
                it.remove();
            }
        }
        return membros;
    }
}
