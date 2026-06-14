/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

/**
 *
 * @author plcau
 */
public class GameDataConfigs {
        
    //## Universal REGEX
    // Regex used across all objects regardless of type. 
    /**
     * Regex to notate that a specific line's value is relating to the visual description of an object.
     */
    public final static String DISPLAY_DESCRIPTION_REGEX = "displaydescription"; 
    
    /**
     * Regex to notate the internal reference name for a referenceDataList. 
     */
    public final static String INTERNAL_IDENTIFIER_REGEX = "identifier"; 
    
    /**
     * Regex to notate that a specific line's value is relating to the visual name of an object. 
     */
    public final static String DISPLAY_NAME_REGEX = "displayname"; 
    
    /**
     * Regex to define the split between the name of a property and it's related value. 
     */
    public final static String VALUE_DEFINITION_REGEX = "="; 
    // For instance, test=1234 would have "test" be the name of a property, and "1234" as it's value. 
    
    /**
     * Regex to define the start of a reference data object entry. 
     */
    public final static String ENTRY_START_REGEX = "entrystart"; 
    
    /**
     * Regex to define the end of a reference data object entry.
     */
    public final static String ENTRY_END_REGEX = "entryend"; 
    
    // ## Structure-specific/tailored regex.
    /**
     * Regex to notate that a specific line's value is to be parsed as an input efficiency value for an object. 
     */
    public final static String STRUCTURE_INPUT_EFFICIENCY_REGEX = "inputefficiency"; 
    
    /**
     * Regex to notate that a specific line's value is to be parsed as an output efficiency value for an object. 
     */
    public final static String STRUCTURE_OUTPUT_EFFICIENCY_REGEX = "outputefficiency";  
    
    /**
     * Regex to notate that a specific line's value is to be parsed as a cost scaling factor for an object. 
     */
    public final static String STRUCTURE_COST_SCALE_FACTOR_REGEX = "costscalefactor"; 
    
    /**
     * Regex to notate that a specific line's value is relating to an input Resource. 
     */
    public final static String INPUT_RESOURCE_REGEX = "input."; 
    
    /**
     * Regex to notate that a specific line's value relates to an output Resource. 
     */
    public final static String OUTPUT_RESOURCE_REGEX = "output."; 
    
    /**
     * Regex to notate that a specific line's value is relating to a Resource cost to create an object. 
     */ 
    public final static String COST_RESOURCE_REGEX = "basecost."; 
    
    
    
    /**
     * Regex to notate that a specific line's value is relating to it's type (as a String)
     */
    public final static String OBJECT_TYPE_REGEX = "type"; 
    
    /**
     * Regex to notate that an object's value is relating to it's type displayed (i.e. a display category); 
     */
    public final static String OBJECT_DISPLAY_TYPE_REGEX = "displaytype"; 
    
    // ##BuildableBody Specific REGEX
    public final static String BUILDABLEBODY_DEPOSIT_REGEX = "deposit.";
    
    public final static String OBJECT_COORDINATES_REGEX = "coordinate."; 
    
    public final static String BOOLEAN_FLAG_REGEX = "booleanflag"; 
    
    
    
    
    
    // ## Other object expectations / formats. 
    /** String used for dictating that a resource is persistent, that is they tend towards a capacity such as electricity.*/
    public final static String PERSISTENT_RESOURCE_STRING = "non-consumed"; 
    public final static String NORMAL_RESOURCE_STRING = "normal"; 
}
