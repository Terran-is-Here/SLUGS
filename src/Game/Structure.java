/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

/**
 *
 * @author plcau
 */

import java.util.ArrayList;
import java.util.Iterator; 
import guiObjects.StructureButton; 
/*
Main class for all Structures present within the game. 
*/
public class Structure extends AbstractGameObject{
    public static String INNATE_IDENTIFIER = "INNATE"; 
    
    /*** The amount of structures present within this object. */
    protected int structureAmount; 
    
    /*** The amount of structures currently inactive within this instance of the object. */
    protected int inactiveStructureAmount; 
    
    /*** The parent BuildableBody this Structure is currently present on. */
    private BuildableBody parentBody; 
    
    /**
     * If the structure is currently enabled or not (by default set to on) 
     */
    protected boolean enabledFlag = true; 
    
    protected StructureButton structureDisplayButton; 
    /**
     * ArrayList of Modifier objects for the current set of modifiers affecting this object instance alone (ignoring global effects). 
     */
    protected ArrayList<Modifier> innateOutputEfficiency = new ArrayList<>(); 
    
    /**
     * Additional modifier ontop of the base input modifier. 
     */
    protected ArrayList <Modifier> innateInputEfficiency = new ArrayList<>();
    
    
    // Constructor Methods
    
    /**
     * Private internal constructor method for creating Structure objects.
     * @param _identifierName The internal identifier name used for internal identification/searching.
     * @param _structureAmount The amount of structures present within this instance of the structure object.
     * @param _parentBody The parent BuildableBody this structure belongs to. 
     * @param _enabledFlag A boolean signifying if this structure in it's entirety is toggled on. 
     * @param _innateOutputEfficiency The unique output efficiency modifier of this specific instance of this type of structure.
     * @param _innateInputEfficiency The unique input efficiency modifier of this specific instance of this type of structure.
     * @param _referenceDataEntry The reference Data Table entry this instance belongs to. 
     */
    private Structure(
            String _identifierName,
            int _structureAmount, 
            BuildableBody _parentBody,
            boolean _enabledFlag,
            double _innateOutputEfficiency, 
            double _innateInputEfficiency,
            ReferenceDataEntry _referenceDataEntry) {
        identifierName = _identifierName;
        structureAmount = _structureAmount; 
        parentBody = _parentBody; 
        enabledFlag = _enabledFlag; 
        innateOutputEfficiency.add(Modifier.newModifier(Structure.INNATE_IDENTIFIER, _innateOutputEfficiency)); 
        innateInputEfficiency.add(Modifier.newModifier(Structure.INNATE_IDENTIFIER, _innateInputEfficiency));
        referenceDataEntry = _referenceDataEntry; 
        inactiveStructureAmount = 0; 
    }
    
    /**
     * Creates a new Structure object; with all non-provided fields set to their default values. 
     * @param _identifierName The internal identifier name used for internal identification/searching.
     * @param _structureAmount The amount of structures present within this instance of the structure object.
     * @param _parentBody The parent BuildableBody this structure belongs to. 
     * @return A new Structure object with the information provided above; and with innate efficiencies set to 1.0 and with a true enabledFlag value.
     */
    public static Structure newStructure (
            String _identifierName,
            int _structureAmount,
            BuildableBody _parentBody) {
        return new Structure(
                _identifierName,
                _structureAmount,
                _parentBody,
                true,
                1.0,
                1.0,
                GameData.fetchReferenceDataEntry(Game.getStructureReferenceTable(), _identifierName)); 
        
        
    }


    // Property Return functions 
    
    /** 
     * Returns the display name of this Structure object. 
     * @return 
     */
    public String getDisplayName() {
        // Get reference data entry , get the related data value using structure identifier
        return this.getReferenceDataTableEntry().getDisplayName(); 
    }
    
    /**
     * Returns the display description of this Structure object via reference data table. 
     * @return 
     */
    public String getDisplayDescription() {
        return this.getReferenceDataTableEntry().getDescription(); 
    }
    
    /**
     * Returns the structureAmount property of the Structure Object.
     * @return 
     */
    public int getStructureAmount() {
        return structureAmount;
    }
    
    /**
     * Returns the enabledFlag property of the Structure Object.
     * @return 
     */
    public boolean getEnabledFlag() {
        return enabledFlag; 
    }
    
    /**
     * Returns the parentBody (BuildableBody) property of the current object.
     * @return 
     */
    public BuildableBody getParentBuildableBody(){
        return parentBody; 
    }
    
    /**
     * Returns the baseStructureCosts (ArrayList<Resoruce>) property of the current object.
     * @return 
     */
    public ArrayList<Resource> getBaseStructureCosts() {
        return this.getReferenceDataTableEntry().getCostResourceArrayList(); 
    }
    
    public ArrayList<Modifier> getStructureInnateInputEfficiencyModifiers() {
        return this.innateInputEfficiency; 
    }
    
    public ArrayList<Modifier> getStructureInnateOutputEfficiencyModifiers() {
        return this.innateOutputEfficiency; 
    }
    
    public double getStructureInnateInputEfficiency() {
        return Modifier.getEffectiveModifier(this.getStructureInnateInputEfficiencyModifiers());
    }
    
    public double getStructureInnateOutputEfficiency() {
        return Modifier.getEffectiveModifier(this.getStructureInnateOutputEfficiencyModifiers());
    }
    
    public String getStructureType() {
        return this.getReferenceDataTableEntry().getObjectType(); 
    }
    /**
     * Returns the structureCostScaleFactor (double) property of the current object.
     * @return 
     */
    public double getStrucutreCostScaleFactor() {
        return this.getReferenceDataTableEntry().getCostScaleFactor(); 
    }
    
    /**
     * Returns the structureOutputEfficiency of the current Structure Object.
     * @return 
     */
    public double getStructureOutputEfficiency() {
        return this.getReferenceDataTableEntry().getBaseOutputEfficiency(); 
    }
    
    /**
     * Returns the structureInputEfficiency of the current Structure Object.
     * @return 
     */
    public double getStructureInputEfficiency() {
        return this.getReferenceDataTableEntry().getBaseInputEfficiency(); 
    }
    
    /**
     * Returns the arrayList representing the BASE output resources of the current Structure.
     * @return 
     */
    public ArrayList<Resource> getStructureBaseOutputResources() {
        return this.getReferenceDataTableEntry().getOutputResourceArrayList(); 
    }; 
    
    /**
     * Returns the arrayList representing the BASE input resources of the current Structure.
     * @return 
     */
    public ArrayList<Resource> getStructureBaseInputResources() {
        return this.getReferenceDataTableEntry().getInputResourceArrayList(); 
    }; 
    
    /**
     * Gets the current inactive structure amount of this object. 
     * @return Returns the inactiveStructureAmount property of this object as an int. 
     */
    public int getInactiveStructureAmount(){
        return this.inactiveStructureAmount; 
    }
    
    // Functions for altering current Structure object properties.
    
    /**
     * Directly sets the amount of structures of this Structure.
     * @param structureAmountToSet Value to set this object's structure amount. 
     */
    public void setStructureAmount (int structureAmountToSet) {
        this.structureAmount = structureAmountToSet; 
    }
    
    /**
     * Directly sets the amount of inactive structures of this Structure. 
     * @param inactiveStructureAmount Value to set the inactiveStructureAmount property of this object to. 
     */
    public void setInactiveStructureAmount (int inactiveStructureAmount) {
        this.inactiveStructureAmount = inactiveStructureAmount;
    }
    
    
    // ## Cost Calculation Functions
    /**
     * Returns the total Resources worth of Resources is present within this Structure Object.
     * @param resourceType
     * @return 
     */
    public Resource findTotalResources(Resource resourceType) {
        double buffer; 
        buffer = Utilities.geometricSeriesInitialSum(1, this.getStructureAmount(),this.getStrucutreCostScaleFactor());
        Resource outputResource = Resource.newResource(resourceType.getIdentifier(), buffer); 
        return outputResource; 
    }
    
    /**
     * Returns an ArrayList of Resources which symbolize the costs required to build buildAmount Structure objects. 
     * Exponentially scaled based off structureCostScaleFactor based on currentStructures. 
     * Can be overridden by child classes. 
     * @param buildAmount
     * @return 
     */
    public ArrayList<Resource> getStructureCosts(int buildAmount) {
        Iterator resourceCostIterator = this.getBaseStructureCosts().iterator(); 
        int currentStructureAmount = this.getStructureAmount(); 
        double structureCostScaleFactor = this.getReferenceDataTableEntry().getCostScaleFactor(); 
        double scalingFactor = 0;
        Resource buffer;
        Resource currentResource;
        ArrayList<Resource> outputArrayList = new ArrayList<>(); 
        // First, get the effective scaling factor for this build order.
        // Special case if only one object is being built. 
        if (buildAmount == 1) {
           scalingFactor = Utilities.geometricSeriesInitialSum(currentStructureAmount, currentStructureAmount-1, structureCostScaleFactor);
        }
        
        //Else, calculate as normal
        else if (buildAmount > 1) {
            scalingFactor = Utilities.geometricSeriesInitialSum(currentStructureAmount, currentStructureAmount + buildAmount, structureCostScaleFactor);
        }
        
        // Using scalingFactor calculated above; iterate across baseStructureCosts to return the resulting cost as a Resource ArrayList. 
        while (resourceCostIterator.hasNext()) {
               currentResource = (Resource) resourceCostIterator.next(); 
               buffer = Resource.newResource(currentResource.getIdentifier(), currentResource.getResourceAmount()*scalingFactor);
               outputArrayList.add(buffer);
        }
        return outputArrayList; 
    }  
    
    
    /** Ticks the structure by a single tick; updating the Resources of this Structure's parent BuildableBody.**/ 
    public void tickStructure() {
        ArrayList<Resource> baseInputResources = this.getReferenceDataTableEntry().getInputResourceArrayList();
        ArrayList<Resource> baseOutputResources = this.getReferenceDataTableEntry().getOutputResourceArrayList(); 
        Iterator baseInputResourceIterator = baseInputResources.iterator(); 
        Iterator baseOutputResourceIterator = baseOutputResources.iterator(); 
        double effectiveStructures; 
        
        
        // Assume from the start that all active structures can be used at 100%.
            effectiveStructures = this.getStructureAmount() - this.getInactiveStructureAmount(); 
        
        // Buffer variables for resource output/input. 
        double temp; 
        Resource tempResource;
        Resource bufferResource; 
        
        
        // Check if structure has any inputs to begin with. 
        if (!baseInputResources.isEmpty())
            
        // Check if we are bottlenecks with any input resources; iterate through all input resources.
            while (baseInputResourceIterator.hasNext()) {
            
            // Get current input resource being checked. 
            bufferResource = (Resource) baseInputResourceIterator.next(); 
            
            tempResource = this.getParentBuildableBody().getResource(bufferResource.getIdentifier()); 
            
            // Check if the resource with the same identifier actually exists within the parent BuildableBody. 
            if (tempResource != null) {
                
                // Uses the equation of bodyResourceAmount / (InputResourceAmount * Innate Input Efficiency * Universal Input Efficiency); 
                // Temp is equal to the amount of structures sustainable by this amount stockpiled. 
                temp = tempResource.getResourceAmount() / (bufferResource.getResourceAmount() * this.getStructureInnateInputEfficiency() * this.getStructureInputEfficiency()); 
                
                // If Temp (sustainable structures) is less than effective structures previously calculated; set that as the new effective structure amount. 
                if (temp < effectiveStructures) {
                    effectiveStructures = temp; 
                }
            }
            
            // If resource does not exist; that means that our body does not have it and therefore we are bottlenecked with no input. 
            else if (tempResource == null) {
                
                effectiveStructures = 0; 
                break; 
            }        
        }
        // Reset input resource iterator. 
        baseInputResourceIterator = baseInputResources.iterator(); 
        
        // If we have any effective structures at all; iterate through using effective amount and update resources of parent BuildableBody accordingly. 
        if (effectiveStructures > 0) {
            
            while (baseInputResourceIterator.hasNext()) {
                bufferResource = (Resource) baseInputResourceIterator.next(); 
                
                // Update all input resources first..
                tempResource = this.getParentBuildableBody().getResource(bufferResource.getIdentifier()); 
                temp = tempResource.getResourceAmount() - bufferResource.getResourceAmount() * effectiveStructures * this.getStructureInnateInputEfficiency() * this.getStructureInputEfficiency(); 
                
                tempResource.setResourceAmount(temp);
                
            }
            while (baseOutputResourceIterator.hasNext()) {
                bufferResource = (Resource) baseOutputResourceIterator.next(); 
                
                // Update all output resources...
                tempResource = this.getParentBuildableBody().getResource(bufferResource.getIdentifier()); 
                temp = tempResource.getResourceAmount() + bufferResource.getResourceAmount() * effectiveStructures * this.getStructureInnateOutputEfficiency() * this.getStructureOutputEfficiency();
                tempResource.setResourceAmount(temp);
            }
        }
        
    }; 
    
    // ## GUI Functions 
    /**
     * Updates the StructureDisplayButton of this Structure; 
     */
    public void updateStructureButton() {
        
        // Check if this object is currently displaying a button; if it isnt create one. 
        if (this.getStructureButton() == null) {
            System.out.println("Structure button created");
            this.structureDisplayButton = StructureButton.newStructureButton(this.getDisplayName(), this); 
        }
        
        // Else, if the structure button already exists; update display text if required. 
        else {
            this.structureDisplayButton.setDisplayText(this.getDisplayName());
        }
    }
    
    public StructureButton getStructureButton() {
        return this.structureDisplayButton; 
    }
    
    public void destroyStructureButton() {
        this.structureDisplayButton = null; 
    }
    
    public ArrayList<Resource> getEffectiveResourceArray(ArrayList<Resource> resourceArray, boolean isInput) {
        ArrayList<Resource> output = new ArrayList<>(); 
        Iterator resourceIterator = resourceArray.iterator();
        int effectiveStructures = this.getStructureAmount() - this.getInactiveStructureAmount(); 
        Resource buffer; 
        String currentLineOutput; 
        double multiplier; 
        if (isInput) {

            multiplier = effectiveStructures*this.getStructureInputEfficiency()*this.getReferenceDataTableEntry().getBaseInputEfficiency(); 
        }
        else {
            multiplier = effectiveStructures*this.getStructureOutputEfficiency()*this.getReferenceDataTableEntry().getBaseOutputEfficiency(); 
        }
        while (resourceIterator.hasNext()) {
            buffer = (Resource) resourceIterator.next(); 
            output.add(Resource.newResource(buffer.getIdentifier(), buffer.getResourceAmount()*multiplier)); 
        }
        return output; 
    }
}


