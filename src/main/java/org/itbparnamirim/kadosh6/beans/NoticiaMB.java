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
import org.itbparnamirim.kadosh6.data.NoticiaDAO;
import org.itbparnamirim.kadosh6.model.Noticia;

/**
 *
 * @author rafao
 */
@Named
@SessionScoped
public class NoticiaMB implements Serializable{

    private Noticia noticia = new Noticia();
    private List<Noticia> noticias = new ArrayList<>();
    @Inject NoticiaDAO noticiaDAO;
    /**
     * Creates a new instance of NoticiaMB
     */
    public NoticiaMB() {
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }
    
    public void limparObjetos(){
        noticia = new Noticia();
    }
    
    public void carregarLista(){
        this.noticias = noticiaDAO.list();
    }
    
    public String prepararCadastro(){
        limparObjetos();
        return "/pages/noticia/cadastroNoticia.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararEdicao(Noticia noticia){
        this.noticia = noticia;
        return "/pages/noticia/cadastroNoticia.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararConsulta(Noticia noticia){
        this.noticia = noticia;
        return "/pages/noticia/detalharNoticia.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String salvar(){
        noticiaDAO.save(noticia);
        return "/pages/noticia/exibirNoticias.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String deletar(Noticia noticia){
        try {
            noticiaDAO.delete(noticia);
        } catch (SecurityException ex) {
            Logger.getLogger(NoticiaMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(NoticiaMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NoticiaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        ManagedBeanUtil.refresh();
        return "";
    }
    
    public void carregarNoticia(int id){
        this.noticia = noticiaDAO.find(id);
    }
    
    public void carregarAtivas(){
        noticiaDAO.carregarAtivas();
    }
    
}
