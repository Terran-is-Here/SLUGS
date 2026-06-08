/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game;

/**
 *
 * @author plcau
 */
public interface GameDataInterface {
    
    
    abstract public ReferenceDataEntry getReferenceDataTableEntry();  
    abstract public String getIdentifier(); 
    
    default public String getDisplayName() {
        return this.getReferenceDataTableEntry().getDisplayName(); 
    }
    
    default public String getDisplayDescription() {
        return this.getReferenceDataTableEntry().getDescription(); 
    }
    
    
}
