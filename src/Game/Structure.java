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
/*
Main class for all Structures present within the game. 
*/
public class Structure extends AbstractGameObject{
    
    
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
    
    /**
     * Additional modifier ontop of the base output modifier. 
     */
    protected double innateOutputEfficiency = 1.0; 
    
    /**
     * Additional modifier ontop of the base input modifier. 
     */
    protected double innateInputEfficiency = 1.0;
    
    
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
        innateOutputEfficiency = _innateOutputEfficiency; 
        innateInputEfficiency = _innateInputEfficiency; 
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
    
    public double getStructureInnateInputEfficiency() {
        return this.innateInputEfficiency; 
    }
    
    public double getStructureInnateOutputEfficiency() {
        return this.innateOutputEfficiency; 
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
    
    public int getInactiveStructureAmount(){
        return this.inactiveStructureAmount; 
    }
    
    // Functions for altering current Structure object properties.
    
    /**
     * Directly sets the amount of structures of this object.
     * @param structureAmountToSet Value to set this object's structure amount. 
     */
    public void setStructureAmount (int structureAmountToSet) {
        this.structureAmount = structureAmountToSet; 
    }
    
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
            scalingFactor = Utilities.geometricSeriesInitialSum(currentStructureAmount, currentStructureAmount, structureCostScaleFactor);
        }
        
        // Using scalingFactor calculated above; iterate across baseStructureCosts to return the resulting cost as a Resource ArrayList. 
        while (resourceCostIterator.hasNext()) {
               currentResource = (Resource) resourceCostIterator.next(); 
               buffer = Resource.newResource(currentResource.getIdentifier(), currentResource.getResourceAmount()*scalingFactor);
               outputArrayList.add(buffer);
        }
        return outputArrayList; 
    }  
    
    
    /** Ticks the structure by a single tick; logic can be dictated by child classes.  **/ 
    public void tickStructure() {
        ArrayList<Resource> baseInputResources = this.getReferenceDataTableEntry().getInputResourceArrayList();
        ArrayList<Resource> baseOutputResources = this.getReferenceDataTableEntry().getOutputResourceArrayList(); 
        Iterator baseInputResourceIterator = baseInputResources.iterator(); 
        Iterator baseOutputResourceIterator = baseOutputResources.iterator(); 
        
        double effectiveStructures = this.getStructureAmount() - this.getInactiveStructureAmount(); // Assume from the start that all active structures can be used at 100%. 
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
                if (temp < effectiveStructures) {;
                    effectiveStructures = temp; 
                }
                continue; 
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
    
         public static void buildStructure(BuildableBody bodyToBuildOn, String structureIdentifier, int structuresToBuild) {
        
        // Get current available resources present in bodyToBuildOn; 
        ArrayList<Resource> parentBodyResources = bodyToBuildOn.getBodyResourceStorage(); 
        
        // Get a reference data entry of an object with our structureIdentifier (mainly for resource cost tables); 
        ReferenceDataEntry structureReference = GameData.fetchReferenceDataEntry(Game.getStructureReferenceTable(), structureIdentifier); 
        
        // Buffer variables for iteration. 
        Resource currentResource; 
        Resource currentResourceTaken; 
        Structure currentStructure; 
        Iterator structureResourceCostIterator = structureReference.getCostResourceArrayList().iterator(); 
        
        double tempResourceAmount;
        
        // Check if there's sufficient materials to perform this build order to begin with. 
        // Prematurely ends buy transaction if there are not enough resources. 
        while (structureResourceCostIterator.hasNext()) {
            // Get current resource in the arraylist of structure build costs.
            currentResource = (Resource) structureResourceCostIterator.next(); 
            
            // Checks if the current resource is present at all on the parent body. 
            if (bodyToBuildOn.checkIfResourceIsPresent(currentResource.getIdentifier())){
                // If present, calculates current build cost; 
                tempResourceAmount = currentResource.getResourceAmount() * structuresToBuild; 
                
                // Compare current buildcost with current resource stockpile in bodyToBuildOn. 
                // If current body resources in this type of resource are less than the total build cost; build order does not go through. 
                if (Resource.getResourceFromArray(parentBodyResources, currentResource.getIdentifier()).getResourceAmount() < tempResourceAmount) {
                    // Implement "insufficient resources" logic here. 
                    return;
                }
            
            }
            else {
                // Implement "insufficient resources" logic here. 
                return; 
            }
         }
        
        
        // If all resources are available; check if a Structure object of the same identifier already exists. 
        if (bodyToBuildOn.checkIfStructureIsPresent(structureIdentifier)) {
            // If it exists; then add it to the count of structures present with that object and update resoruce tables accordingly. 
            currentStructure = bodyToBuildOn.getStructure(structureIdentifier); 
            currentStructure.setStructureAmount(currentStructure.getStructureAmount() + structuresToBuild);
        }
        // If structure is not already inherently present; create new structure object and add to the BuildableBody's ArrayList of structures. 
        else {
            currentStructure = Structure.newStructure(structureIdentifier, structuresToBuild, bodyToBuildOn); 
            bodyToBuildOn.getBodyStructures().add(currentStructure); 
        }
        
        structureResourceCostIterator = structureReference.getCostResourceArrayList().iterator(); 
        // Then iterate through costs and update parentBody resource amounts accordingly. 
        while (structureResourceCostIterator.hasNext()) {
            currentResourceTaken = (Resource) structureResourceCostIterator.next();
            currentResource = bodyToBuildOn.getResource(currentResourceTaken.getIdentifier()); 
            tempResourceAmount = currentResource.getResourceAmount() - (currentResourceTaken.getResourceAmount() * structuresToBuild); 
            currentResource.setResourceAmount(tempResourceAmount);
                    
        }
    }
     
    public static void sellStructure(BuildableBody parentBody, String structureIdentifier, int structuresToSell) {
        
    }
}


