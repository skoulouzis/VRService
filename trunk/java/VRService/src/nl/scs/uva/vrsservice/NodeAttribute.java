/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import nl.uva.vlet.data.VAttribute;
import nl.uva.vlet.data.VAttributeType;

/**
 *
 * @author S.Koulouzis
 */
public class NodeAttribute {

//    private final VAttribute attribute;
    private String key;
    private String value;
    private final VAttributeType type;

    public NodeAttribute(VAttribute attribute) {
//        this.attribute = attributes;
        key = attribute.getName();
        value = attribute.getValue();
        type = attribute.getType();
    }

    public String getAttributeName() {
        return key;
    }

    public String getValue() {
        return value;
    }
    
    public VAttributeType getType(){
        return type;
    }
}
