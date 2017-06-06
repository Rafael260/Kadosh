package org.itbparnamirim.kadosh6.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
//import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.itbparnamirim.kadosh6.data.MembroDAO;
import org.itbparnamirim.kadosh6.data.MinisterioDAO;
import org.itbparnamirim.kadosh6.model.Membro;
import org.itbparnamirim.kadosh6.model.Ministerio;

/**
 *
 * @author Geraldo
 */
@SessionScoped
@Named
public class MinisterioMB implements Serializable {

    private Ministerio ministerio = new Ministerio();
    private List<Membro> membrosForaDoMinisterio = new ArrayList<>();
    private Membro membroSelecionado = new Membro();
    private List<Ministerio> ministerios = new ArrayList<>();
    
    @Inject
    MinisterioDAO ministerioDAO;

    @Inject
    MembroDAO membroDAO;
    
    public void carregarLista() {
        this.ministerios = ministerioDAO.list();
    }

    public Ministerio getMinisterio() {
        return ministerio;
    }

    public void setMinisterio(Ministerio ministerio) {
        this.ministerio = ministerio;
    }

    public List<Ministerio> getMinisterios() {
        return ministerios;
    }

    public void setMinisterios(List<Ministerio> ministerios) {
        this.ministerios = ministerios;
    }

    public List<Membro> getMembrosForaDoMinisterio() {
        return membrosForaDoMinisterio;
    }

    public void setMembrosForaDoMinisterio(List<Membro> membrosForaDoMinisterio) {
        this.membrosForaDoMinisterio = membrosForaDoMinisterio;
    }

    public Membro getMembroSelecionado() {
        return membroSelecionado;
    }

    public void setMembroSelecionado(Membro membroSelecionado) {
        this.membroSelecionado = membroSelecionado;
    }
    
    public String salvar(){
        ministerio = ministerioDAO.save(ministerio);
        limparMinisterio();
        return "/pages/ministerio/exibirMinisterios.xhtml"+ManagedBeanUtil.REDIRECT;
    }

    public void limparMinisterio() {
        this.ministerio = new Ministerio();
    }

    public String deletar(Ministerio ministerio) {
        String paginaDestino = "/pages/ministerio/exibirMinisterios.xhtml";
        try {
            ministerioDAO.delete(ministerio);
            ManagedBeanUtil.refresh();
        } catch (Exception e) {
            paginaDestino = "/pages/dashboardAdmin.xhtml";
        }
        return paginaDestino+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararEdicao(Ministerio ministerio) {
        this.ministerio = ministerio;
        return "/pages/ministerio/cadastroMinisterio.xhtml"+ManagedBeanUtil.REDIRECT;
    }

    public String prepararCadastro() {
        limparMinisterio();
        return "/pages/ministerio/cadastroMinisterio.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararConsulta(Ministerio ministerio){
        this.ministerio = ministerio;
        System.out.println("Preparando consulta do ministerio " + ministerio.getNome());
        if (this.ministerio.getId() == null){
            System.out.println("metodo prepararConsulta, id do ministerio NULO");
        }
        return "/pages/ministerio/detalharMinisterio.xhtml"+ManagedBeanUtil.REDIRECT;
    }

    //Funcao sera usada quando a pagina for carregada, e entao teremos uma lista de membros que nao estao no ministerio em questao
    //Sera usada como base para a funcao de autocomplete
    public void carregarMembrosForaDoMinisterio(){            
        membrosForaDoMinisterio = membroDAO.getMembrosNaoNesseMinisterio(ministerio);
    }
    
    
    public List<Membro> completeMembroAutoComplete(String query){
        List<Membro> membrosAutoComplete = new ArrayList<>();
//        carregarMembrosForaDoMinisterio();
        for (Membro membro: membrosForaDoMinisterio){
            if (membro.getNome().toLowerCase().contains(query.toLowerCase())){
                membrosAutoComplete.add(membro);
            }
        }
        return membrosAutoComplete;
    }
    
    public String adicionarMembro(){
        this.ministerio.adicionarMembro(membroSelecionado);
        ministerioDAO.save(ministerio);
        ManagedBeanUtil.refresh();
        membroSelecionado = null;
        return "/pages/ministerio/detalharMinisterio.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String removerMembro(Membro membro){
        this.ministerio.removerMembro(membro);
        ministerioDAO.save(ministerio);
         ManagedBeanUtil.refresh();
        return "/pages/ministerio/detalharMinisterio.xhtml"+ManagedBeanUtil.REDIRECT;
    }
}
