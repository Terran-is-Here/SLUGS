/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

/**
 * A container class that contains data for reference in other functions, such as the base data for Strcutures.
 * @author plcau
 */
import java.util.ArrayList; 
public class ReferenceDataEntry {
    
    // ## Global / universal data fields (required for all ReferenceDataEntry Objects) 
    private String identifierName; 
    private String displayName; 
    
    // Optional graphical definitions
    private String description; 
    
    // Structure-specific fields. 
    private ArrayList<Resource> inputResourceArrayList; 
    private ArrayList<Resource> outputResourceArrayList; 
    private ArrayList<Resource> costResourceArrayList; 
    private double outputEfficiency = 1; 
    private double inputEfficiency = 1; 
    private double costScaleFactor = 1.0;  
    
    // Resource-specific fields. 
    private String resourceType; 
    
    /**
     * Creates a new ReferenceDataHashMapObject. Meant to be used as an internal constructor for this function alone. 
     * @param _identifierName The unique internal identifier of this object; mirrored in both a HashMap's Key and part of the resulting value. 
     * @param _description A string used for displaying the description in the GUI. 
     * @param _displayName A string used for displaying a name in the GUI.
     * @param _inputResourceArrayList The arrayList of inputResources an object takes in.
     * @param _outputResourceArrayList The arrayList of outputResources an object outputs.
     * @param _costResourceArrayList The arrayList of build costs of an object.
     * @param _costScaleFactor The double representing the cost-scaling factor of an object.
     * @param _inputEfficiency The double representing the input efficiency of an object. 
     * @param _outputEfficiency The double representing the output efficiency of an object. 
     * @param _resourceType A String representing the type of Resource. 
     */
    private ReferenceDataEntry(
            String _identifierName,
            String _description,
            String _displayName, 
            ArrayList<Resource> _inputResourceArrayList,
            ArrayList<Resource> _outputResourceArrayList,
            ArrayList<Resource> _costResourceArrayList,
            double _costScaleFactor, 
            double _inputEfficiency, 
            double _outputEfficiency, 
            String _resourceType) {
        identifierName = _identifierName; 
        description = _description; 
        displayName = _displayName; 
        inputResourceArrayList = _inputResourceArrayList; 
        outputResourceArrayList = _outputResourceArrayList; 
        costResourceArrayList = _costResourceArrayList; 
        costScaleFactor = _costScaleFactor; 
        inputEfficiency = _inputEfficiency;
        outputEfficiency = _outputEfficiency; 
        resourceType = _resourceType; 
    } 
    
    /**
     * Creates and returns a ReferenceDataHashMapEntry Object specifically for Structure reference data HashMaps. Passes _resourceType as null. 
     * @param _identifierName The internal identifier of a structure. 
     * @param _description The description of the structure to display.
     * @param _displayName The name of the structure to visually display.
     * @param _inputResourceArrayList Optional resource ArrayList denoting input resources.
     * @param _outputResourceArrayList Optional resource ArrayList denoting output resources.
     * @param _costResourceArrayList Optional resource ArrayList denoting resource cost to create an object. 
     * @param _costScaleFactor double value denoting the amount this structure's cost scales as more are created on the same body. 
     * @param _inputEfficiency double value denoting a flat modifier to inputResources. 
     * @param _outputEfficiency double value denoting a flat modifier to outputResources. 
     * @return 
     */
    
    public static ReferenceDataEntry newStructureReferenceDataHashMapEntry(
            String _identifierName,
            String _description,
            String _displayName, 
            ArrayList<Resource> _inputResourceArrayList,
            ArrayList<Resource> _outputResourceArrayList,
            ArrayList<Resource> _costResourceArrayList, 
            double _costScaleFactor, 
            double _inputEfficiency, 
            double _outputEfficiency) {
        
        
        return new ReferenceDataEntry(
                _identifierName,
                _description,
                _displayName, 
                _inputResourceArrayList,
                _outputResourceArrayList,
                _costResourceArrayList,
                _costScaleFactor, 
                _inputEfficiency,
                _outputEfficiency,
                null); 
        
    } 
    
    /**
     * Creates and returns a ReferenceDataHashMapEntry Object specifically for Resource reference data HashMaps. Passes all other non-used properties in the class as null or 0.
     * @param _identifierName Internal identifier of a Resource object. 
     * @param _description The display description of a Resource object. 
     * @param _displayName The display name of a Resource object. 
     * @param _resourceType The type of resource of a Resource object.
     * @return 
     */
    public static ReferenceDataEntry newResourceReferenceDataHashMapEntry (
                String _identifierName, 
                String _description, 
                String _displayName, 
                String _resourceType
    ) {
    return new ReferenceDataEntry(
                _identifierName,
                _description,
                _displayName, 
                null,
                null,
                null,
                0, 
                0,
                0,
                _resourceType); 
    };
    
    /**
     * Returns an empty referenceDataEntry object.
     * @return 
     */
    public static ReferenceDataEntry emptyReference() {
        return new ReferenceDataEntry(
                null,
                null,
                null, 
                null,
                null,
                null,
                0, 
                0,
                0,
                null); 
    }
    
    /**
     * Fetches the internal identifier name of a ReferenceDataEntry. 
     * @return Returns the internal identifier of a ReferenceDataEntry as a String. 
     */
    public String getIdentifierName() {
        return this.identifierName;
    }
    
    /**
     * Fetches the display description of a ReferenceDataEntry.
     * @return Returns the display description parameter of this object as a string. 
     */
    public String getDescription() {
        return this.description; 
    }
    
    public String getDisplayName() {
        return this.displayName; 
    }
    
    public ArrayList<Resource> getInputResourceArrayList() {
        return this.inputResourceArrayList;
    }
    
    public ArrayList<Resource> getOutputResourceArrayList() {
        return this.outputResourceArrayList; 
    }
    
    public ArrayList<Resource> getCostResourceArrayList() {
        return this.costResourceArrayList;
    }
    
    
    
    public double getCostScaleFactor() {
        return this.costScaleFactor; 
    }
    
    public double getBaseInputEfficiency() {
        return this.inputEfficiency; 
    }
    
    public double getBaseOutputEfficiency() {
        return this.outputEfficiency; 
    }
    
    public String getResourceType() {
        return this.resourceType; 
    }
    
    
    /**
     * Debugging tool to check if values have been properly loaded for a ReferenceDataEntry object. 
     * Logs ALL properties of an Object of this class into the console. 
     */
    public void debugReferenceDataEntry() {
        System.out.println("Internal Identifier: " + this.getIdentifierName()); 
        System.out.println("Display Name: " + this.getDisplayName()); 
        System.out.println("Display Description: " + this.getDescription()); 
        
    }
}
