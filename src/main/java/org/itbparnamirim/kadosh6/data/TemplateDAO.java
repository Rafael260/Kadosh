/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itbparnamirim.kadosh6.data;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@Named
@SessionScoped
public class TemplateDAO {
    @PersistenceContext(unitName = "kadosh6PU")
    protected EntityManager em;
    
    //Ate agora nao precisamos usar esse objeto
    @Resource
    protected UserTransaction userTransaction;
}
