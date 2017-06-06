package org.itbparnamirim.kadosh6.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import org.itbparnamirim.kadosh6.data.NoticiaDAO;
import org.itbparnamirim.kadosh6.model.Noticia;

/**
 *
 * @author Geraldo Jr
 */
@SessionScoped
@Named
public class NoticiaMB implements Serializable {

    private Noticia noticia = new Noticia();
    private List<Noticia> noticias = new ArrayList<>();

    @Inject
    NoticiaDAO noticiaDAO;

    public void carregarLista(){
        this.noticias = noticiaDAO.list();
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

    public String salvar() {
        try {
            noticia = noticiaDAO.save(noticia);
            limparObjetos();
        } catch (IllegalStateException ex) {
            Logger.getLogger(NoticiaMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(NoticiaMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(NoticiaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/pages/noticias/exibirNoticia" + ManagedBeanUtil.REDIRECT;
    }

    private void limparObjetos() {
        this.noticia = new Noticia();
    }
    
    public String deletar (Noticia noticia){
        String pagDestino = "/pages/noticias/exibirNoticia.xhtml";
        try {
            noticiaDAO.delete(noticia);
            ManagedBeanUtil.refresh();
            System.out.println("Notícia deletada com sucesso!");
        } catch (IllegalStateException | SecurityException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException ex) {
            Logger.getLogger(NoticiaMB.class.getName()).log(Level.SEVERE, null, ex);
            pagDestino = "/pages/dashboardAdmin.xhtml";
        }
        System.out.println(pagDestino+ManagedBeanUtil.REDIRECT);
        return pagDestino + ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararEdicao(Noticia noticia){
        this.noticia = noticia;
        return "/pages/noticias/cadastrarNoticia.xhtml" + ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararConsulta(Noticia noticia){
        this.noticia = noticia;
        return "/pages/noticias/detalharNoticia.xhtml" + ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararCadastro(Noticia noticia){
        limparObjetos();
        return "/pages/noticias/cadastrarNoticia.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String publicarNoticia(Noticia noticia){
        this.noticia = noticia;
        return "/pages/noticias/publicarNoticia"+ManagedBeanUtil.REDIRECT;
    }

}
