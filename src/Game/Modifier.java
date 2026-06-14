/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

/**
 *
 * @author plcau
 */
import java.util.Iterator; 
import java.util.ArrayList; 
public class Modifier extends AbstractGameObject {
    private double modifierValue = 1.0; 
    private Modifier(String _identifierName, double _modifierValue) {
        identifierName = _identifierName; 
        modifierValue = _modifierValue; 
    }
    
    public static Modifier newModifier(String _identifierName, double _modifierValue) {
        return new Modifier(_identifierName, _modifierValue); 
    }
    
    public double getModifierValue() {
        return this.modifierValue; 
    }
    
    public void setModifierValue(double newValue) {
        this.modifierValue = newValue; 
    }
    
    public static double getEffectiveModifier(ArrayList<Modifier> modifierArrayList) {
        double output = 1.0; 
        Modifier buffer; 
        Iterator modifierIterator = modifierArrayList.iterator(); 
        while (modifierIterator.hasNext()) {
            buffer = (Modifier) modifierIterator.next();
            output *= buffer.getModifierValue(); 
        }
        System.out.println("Testing!"); 
        System.out.println(output);
        return output; 
    }
    
    public static Modifier getModifierFromArray(ArrayList<Modifier> inputArrayList, String identifierToSearch) {
        Iterator modifierIterator = inputArrayList.iterator(); 
        Modifier buffer; 
        while (modifierIterator.hasNext()) {
            buffer = (Modifier) modifierIterator.next(); 
            if (buffer.getIdentifier().equals(identifierToSearch)) {
                return buffer; 
            }
        }
        return null; 
    }
}
