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
import org.itbparnamirim.kadosh6.data.EventoDAO;
import org.itbparnamirim.kadosh6.model.Evento;

/**
 *
 * @author rafao
 */
@Named
@SessionScoped
public class EventoMB implements Serializable{

    private Evento evento = new Evento();
    private List<Evento> eventos = new ArrayList<>();
    @Inject EventoDAO eventoDAO;
    /**
     * Creates a new instance of EventoMB
     */
    public EventoMB() {
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    
    public void limparObjetos(){
        evento = new Evento();
    }
    
    public void carregarLista(){
        this.eventos = eventoDAO.list();
    }
    
    public String prepararCadastro(){
        limparObjetos();
        return "/pages/evento/cadastroEvento.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararEdicao(Evento evento){
        this.evento = evento;
        return "/pages/evento/cadastroEvento.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String prepararConsulta(Evento evento){
        this.evento = evento;
        return "/pages/evento/detalharEvento.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String salvar(){
        eventoDAO.save(evento);
        return "/pages/evento/exibirEventos.xhtml"+ManagedBeanUtil.REDIRECT;
    }
    
    public String deletar(Evento evento){
        try {
            eventoDAO.delete(evento);
        } catch (SecurityException ex) {
            Logger.getLogger(EventoMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(EventoMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EventoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        ManagedBeanUtil.refresh();
        return "";
    }
    
}
