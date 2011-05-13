/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.scs.uva.vrsservice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import nl.uva.vlet.data.VAttribute;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.vrs.VNode;

/**
 *
 * @author S.Koulouzis
 */
public class NodeAttributes {

    private final VAttribute[] attributes;
    private final HashMap<String, String> mapAttributes = new HashMap<String, String>();

    public NodeAttributes(VNode node) throws VlException {
        this.attributes = node.getAttributes();

        for (int i = 0; i < attributes.length; i++) {
            mapAttributes.put(attributes[i].getName(), attributes[i].getValue());
        }
    }
    
    public String[] getAttributeNames() {
        Set<String> keySet = mapAttributes.keySet();
        String[] names = new String[keySet.size()];
        names = keySet.toArray(names);
        return names;
    }

    public String getAttribute(String key) {
        return mapAttributes.get(key);
    }

    public String[] getValues() {
        Collection<String> values = mapAttributes.values();
        String[] strValues = new String[values.size()];
        strValues = values.toArray(strValues);
        return strValues;
    }
}
