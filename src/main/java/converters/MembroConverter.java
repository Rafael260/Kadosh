/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import org.itbparnamirim.kadosh6.data.MembroDAO;
import org.itbparnamirim.kadosh6.model.Membro;

@Named
@FacesConverter(value = "membroConverter")
public class MembroConverter implements Converter{

    @Inject MembroDAO membroDAO;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null){
            System.out.println("VALUE NULO");
        }
        if (membroDAO == null){
            System.out.println("MEMBRO DAO NULO");
        }
        Object object = membroDAO.getMembroById(Integer.parseInt(value));
        return object;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Membro membro = (Membro) value;
        return String.valueOf(membro.getId());
    }
    
}
