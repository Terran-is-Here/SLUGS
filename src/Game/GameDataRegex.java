/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

/**
 *
 * @author plcau
 */
public class GameDataRegex {
        
    //## Universal REGEX
    // Regex used across all objects regardless of type. 
    /**
     * Regex to notate that a specific line's value is relating to the visual description of an object.
     */
    public final static String STRUCTURE_DESCRIPTION_REGEX = "description"; 
    
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
    
    
    // ##Resource-Specific REGEX
    
    public final static String RESOURCE_TYPE_REGEX = "resourcetype"; 
    
    
    // ##BuildableBody Specific REGEX
    
    public final static String BUILDABLEBODY_TYPE_REGEX = "buildablebodytype"; 
    
}
