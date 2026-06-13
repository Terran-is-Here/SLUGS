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
public class ReferenceDataEntry extends AbstractGameObject{
    
    // Display Fields
    private String displayName; // Display name of this object (if it exists) 
    private String displayDescription; // Display description of this object (used for GUI) 
    
    // Internal fields / used for simulation
    private ArrayList<Resource> inputResources;  //ArrayList for input resources for an object; such as a Structure. 
    private ArrayList<Resource> outputResources; //ArrayList for output resources for an object; such as a Structure. 
    private ArrayList<Resource> costResources;   //ArrayList for the cost to build an object.
    private ArrayList<Resource> depositResources; //Array for the internal deposits of an object.
    private double outputEfficiency = 1.0; //double representing the efficiency of an object to output something.  
    private double inputEfficiency = 1.0; //double representing the efficiency of an object in terms of consuming inputs. 
    private double costScaleFactor = 1.0; //double representing how much the cost of an object scales exponentially. 
    private String objectType; // Type of object (mainly for Resource and Structure and another subset of identification) 
    
    private boolean booleanFlag; 
    
    
    /**
     * Creates a new ReferenceDataEntry. Meant to be used as an internal constructor for this function alone. 
     * @param _identifierName The unique internal identifier of this object; mirrored in both a HashMap's Key and part of the resulting value. 
     * @param _description A string used for displaying the description in the GUI. 
     * @param _displayName A string used for displaying a name in the GUI.
     * @param _inputResourceArrayList The arrayList of inputResources an object takes in.
     * @param _outputResourceArrayList The arrayList of outputResources an object outputs.
     * @param _costResourceArrayList The arrayList of build costs of an object.
     * @param _costScaleFactor The double representing the cost-scaling factor of an object.
     * @param _inputEfficiency The double representing the input efficiency of an object. 
     * @param _outputEfficiency The double representing the output efficiency of an object. 
     * @param _objectType A String representing the specific type of the object (mainly for BuildableBody or Resource) 
     */
    private ReferenceDataEntry(
            String _identifierName, 
            String _displayName,
            String _displayDescription,
            ArrayList<Resource> _inputResources,
            ArrayList<Resource> _outputResources, 
            ArrayList<Resource> _costResources,
            ArrayList<Resource> _depositResources,
            double _outputEfficiency,
            double _inputEfficiency, 
            double _costScaleFactor,
            String _objectType, 
            boolean _booleanFlag
                ) {
            identifierName = _identifierName; 
            displayName = _displayName; 
            displayDescription = _displayDescription; 
            inputResources = _inputResources; 
            outputResources = _outputResources; 
            costResources = _costResources;
            depositResources = _depositResources; 
            outputEfficiency = _outputEfficiency;
            inputEfficiency = _inputEfficiency; 
            costScaleFactor = _costScaleFactor; 
            objectType = _objectType; 
            booleanFlag = _booleanFlag; 
        
            
    } 
    
    public static ReferenceDataEntry newReferenceDataEntry(
            String _identifierName,
            String _displayName,
            String _displayDescription,
            ArrayList<Resource> _inputResources,
            ArrayList<Resource> _outputResources, 
            ArrayList<Resource> _costResources,
            ArrayList<Resource> _depositResources,
            double _outputEfficiency,
            double _inputEfficiency, 
            double _costScaleFactor,
            String _objectType,
            boolean _booleanFlag) {
        return new ReferenceDataEntry(
                _identifierName, 
                _displayName,
                _displayDescription,
                _inputResources,
                _outputResources,
                _costResources,
                _depositResources,
                _outputEfficiency,
                _inputEfficiency,
                _costScaleFactor,
                _objectType,
                _booleanFlag);
    
    }
    
    /**
     * Create an empty ReferenceDataEntry with all fields initialized as either null or 1.0. 
     * @return An empty referenceDataEntry. 
     */
    public static ReferenceDataEntry newEmptyReferenceDataEntry() {
        ReferenceDataEntry buffer = new ReferenceDataEntry(null,null,null,null,null,null,null,1.0,1.0,1.0,null,false);
        return buffer; 
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
        return this.displayDescription; 
    }
    
    public String getDisplayName() {
        return this.displayName; 
    }
    
    public ArrayList<Resource> getInputResourceArrayList() {
        return this.inputResources;
    }
    
    public ArrayList<Resource> getOutputResourceArrayList() {
        return this.outputResources; 
    }
    
    public ArrayList<Resource> getCostResourceArrayList() {
        return this.costResources;
    }
    
    public ArrayList<Resource> getDepositResourceArrayList() {
        return this.depositResources; 
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
    
    public String getObjectType() {
        return this.objectType; 
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
