/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itbparnamirim.kadosh6.beans;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rafael
 */
public class ManagedBeanUtil {
    public static final String REDIRECT = "?faces-redirect=true";
    
    public static void refresh(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException ex) {
            Logger.getLogger(GrupoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
