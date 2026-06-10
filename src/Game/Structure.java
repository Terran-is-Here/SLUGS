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
public class Structure{
    
    /**
     * Internal structure identifier; used for parsing referenceHashMaps for gathering data shared by other 
     * structures of the same identity. 
     */
    protected String identifierName; 
    
    /**
     * The amount of structures present within this object. 
     */
    protected int structureAmount; 
    
    /**
     * The parent BuildableBody this Structure is currently present on. 
     */
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
    
    /** Pointer towards ReferenceDataEntry in reference Data Table. */
    protected ReferenceDataEntry referenceDataEntry; 
    
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
     * Gets the related data entry within the reference HashMap. 
     * @return Returns the related data entry within the reference dataTable.
     */
    
    public ReferenceDataEntry getReferenceDataTableEntry() {
        return referenceDataEntry; 
    }
    
    /** 
     * Returns the display name of this Structure object. 
     * @return 
     */
    public String getDisplayName() {
        // Get reference hashtable, get the related data value using structure identifier
        return this.getReferenceDataTableEntry().getDisplayName(); 
    }
    
    /**
     * Returns the internal identifier of this Structure Object. 
     * @return 
     */
    public String getIdentifier() {
        return identifierName; 
    }
    
    /**
     * Returns the display description of this Structure object via reference HashMap. 
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
    
    
    // Functions for altering current Structure object properties.
    
    /**
     * Directly sets the amount of structures of this object.
     * @param structureAmountToSet Value to set this object's structure amount. 
     */
    public void setStructureAmount (int structureAmountToSet) {
        this.structureAmount = structureAmountToSet; 
    }
    
    /**
     * Wrapper function of removeObject, removes structuresToAdd to the amount of structures present in Structure.structureAmount.
     * @param structuresToAdd 
     */
    
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
        System.out.println(this.getStructureAmount());
        double effectiveStructures = this.getStructureAmount(); // Assume from the start that all structures can be used at 100%. 
        double temp; 
        Resource tempResource;
        Resource bufferResource; 
        System.out.println("Testing10");
        
        // Check if structure has any inputs to begin with. 
        if (!baseInputResources.isEmpty())
            System.out.println("Input resources found.");
        // Check if we are bottlenecks with any input resources; iterate through all input resources.
            while (baseInputResourceIterator.hasNext()) {
            
            // Get current input resource being checked. 
            bufferResource = (Resource) baseInputResourceIterator.next(); 
            System.out.println("BufferResource: " + bufferResource.getDisplayName()); 
            tempResource = this.getParentBuildableBody().getResource(bufferResource.getIdentifier()); 
            
            // Check if the resource with the same identifier actually exists within the parent BuildableBody. 
            if (tempResource != null) {
                System.out.println("Resource found in parent!");
                // Uses the equation of bodyResourceAmount / (InputResourceAmount * Innate Input Efficiency * Universal Input Efficiency); 
                // Temp is equal to the amount of structures sustainable by this amount stockpiled. 
                temp = tempResource.getResourceAmount() / (bufferResource.getResourceAmount() * this.getStructureInnateInputEfficiency() * this.getStructureInputEfficiency()); 
                System.out.println(temp);
                // If Temp (sustainable structures) is less than effective structures previously calculated; set that as the new effective structure amount. 
                if (temp < effectiveStructures) {;
                    effectiveStructures = temp; 
                }
                continue; 
            }
            
            // If resource does not exist; that means that our body does not have it and therefore we are bottlenecked with no input. 
            else if (tempResource == null) {
                System.out.println("Resource not found.");
                effectiveStructures = 0; 
                break; 
            }        
        }
        // Reset input resource iterator. 
        baseInputResourceIterator = baseInputResources.iterator(); 
        System.out.println("uh oh" + this.getStructureAmount());
        // If we have any effective structures at all; iterate through using effective amount and update resources of parent BuildableBody accordingly. 
        if (effectiveStructures > 0) {
            
            while (baseInputResourceIterator.hasNext()) {
                bufferResource = (Resource) baseInputResourceIterator.next(); 
                
                // Update all input resources first..
                tempResource = this.getParentBuildableBody().getResource(bufferResource.getIdentifier()); 
                temp = tempResource.getResourceAmount() - bufferResource.getResourceAmount() * effectiveStructures * this.getStructureInnateInputEfficiency() * this.getStructureInputEfficiency(); 
                System.out.println("Input Updated:" + temp);
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
    
    
}


