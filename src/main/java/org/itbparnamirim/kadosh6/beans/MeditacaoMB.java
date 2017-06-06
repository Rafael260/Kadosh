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
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.SystemException;
import org.itbparnamirim.kadosh6.data.MeditacaoDAO;
import org.itbparnamirim.kadosh6.model.Meditacao;

/**
 *
 * @author rafao
 */
@Named
@SessionScoped
public class MeditacaoMB implements Serializable{

    private Meditacao meditacao = new Meditacao();
    private List<Meditacao> meditacoes = new ArrayList<>();
    private String novoVersiculoDeApoio = "";
    private String novaDecisao = "";
    
    @Inject MeditacaoDAO meditacaoDAO;
    
    /**
     * Creates a new instance of MeditacaoMB
     */
    public MeditacaoMB() {
    }

    public Meditacao getMeditacao() {
        return meditacao;
    }

    public void setMeditacao(Meditacao meditacao) {
        this.meditacao = meditacao;
    }

    public List<Meditacao> getMeditacoes() {
        return meditacoes;
    }

    public void setMeditacoes(List<Meditacao> meditacoes) {
        this.meditacoes = meditacoes;
    }

    public String getNovoVersiculoDeApoio() {
        return novoVersiculoDeApoio;
    }

    public void setNovoVersiculoDeApoio(String novoVersiculoDeApoio) {
        this.novoVersiculoDeApoio = novoVersiculoDeApoio;
    }

    public String getNovaDecisao() {
        return novaDecisao;
    }

    public void setNovaDecisao(String novaDecisao) {
        this.novaDecisao = novaDecisao;
    }
    
    public String adicionarVersiculoDeApoio(){
        this.meditacao.adicionarVersiculoDeApoio(novoVersiculoDeApoio);
        ManagedBeanUtil.refresh();
        return "";
    }
    
    public String removerVersiculoDeApoio(String versiculoDeApoio){
        this.meditacao.removerVersiculoDeApoio(versiculoDeApoio);
        ManagedBeanUtil.refresh();
        return "";
    }
    
    public String adicionarDecisao(){
        this.meditacao.adicionarDecisao(novaDecisao);
        ManagedBeanUtil.refresh();
        return "";
    }
    
    public String removerDecisao(String decisao){
        this.meditacao.removerDecisao(decisao);
        ManagedBeanUtil.refresh();
        return "";
    }
    
    public void limparObjetos(){
        meditacao = new Meditacao();
        novoVersiculoDeApoio = "";
        novaDecisao = "";
    }
    
    public void carregarLista(){
        this.meditacoes = meditacaoDAO.list();
    }
    
    public String prepararCadastro(){
        limparObjetos();
        return "/pages/meditacao/cadastroMeditacao.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararEdicao(Meditacao meditacao){
        this.meditacao = meditacao;
        return "/pages/meditacao/cadastroMeditacao.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararConsulta(Meditacao meditacao){
        this.meditacao = meditacao;
        return "/pages/meditacao/detalharMeditacao.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String salvar(){
        meditacaoDAO.save(meditacao);
        return "/pages/meditacao/exibirMeditacoes.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String deletar(Meditacao meditacao){
        try {
            meditacaoDAO.delete(meditacao);
        } catch (SecurityException ex) {
            Logger.getLogger(MeditacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(MeditacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MeditacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        ManagedBeanUtil.refresh();
        return "";
    }
    
}
