/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

/**
 * Abstract Game Object class detailing identifier and data table related methods; inherited by all game objects.
 * @author plcau
 */
public class AbstractGameObject {
    /**
     * Internal identifier for use within searching and getting visual and type. 
     */
    protected String identifierName; 
    
    /** ReferenceDataEntry of this type of Object; determined by identifierName. */ 
    protected ReferenceDataEntry referenceDataEntry; 
    
    /**
     * Gets the internal identifier of this object.
     * @return Returns the internal identifierName of this object as a String. 
     */
    public String getIdentifier() {
        return this.identifierName; 
    }

    
    /**
     * Gets this object's data entry in the reference data table. 
     * @return Returns the referenceDataEntry property of this object. 
     */
    public ReferenceDataEntry getReferenceDataTableEntry() {
        return this.referenceDataEntry; 
    }
}
