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
Superclass for all Structures present within the game. 
*/
public class Structure {
    
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
    protected BuildableBody parentBody; 
    
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
    // Property Return functions 
    
    /**
     * Gets the related data entry within the reference HashMap. 
     * @return Returns the related data entry within the reference dataTable.
     */
    private ReferenceDataEntry getReferenceDataTableEntry() {
        return GameData.fetchReferenceDataEntry(Game.getStructureReferenceTable(), this.identifierName); 
    }
    
    /** 
     * Returns the display name of this Structure object. 
     * @return 
     */
    public String getStructureDisplayName() {
        // Get reference hashtable, get the related data value using structure identifier
        ReferenceDataEntry bufferDataEntry = getReferenceDataTableEntry(); 
        return bufferDataEntry.getDisplayName(); 
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
    public String getStructureDescription() {
        ReferenceDataEntry bufferDataEntry = getReferenceDataTableEntry(); 
        return bufferDataEntry.getDescription(); 
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
        ReferenceDataEntry bufferDataEntry = this.getReferenceDataTableEntry(); 
        return bufferDataEntry.getCostResourceArrayList(); 
    }
    
    /**
     * Returns the structureCostScaleFactor (double) property of the current object.
     * @return 
     */
    public double getStrucutreCostScaleFactor() {
        ReferenceDataEntry bufferDataEntry = this.getReferenceDataTableEntry(); 
        return bufferDataEntry.getCostScaleFactor(); 
    }
    
    /**
     * Returns the structureOutputEfficiency of the current Structure Object.
     * @return 
     */
    public double getStructureOutputEfficiency() {
        ReferenceDataEntry bufferDataEntry = this.getReferenceDataTableEntry(); // update
        return bufferDataEntry.getBaseOutputEfficiency(); 
    }
    
    /**
     * Returns the structureInputEfficiency of the current Structure Object.
     * @return 
     */
    public double getStructureInputEfficiency() {
        ReferenceDataEntry bufferDataEntry = this.getReferenceDataTableEntry(); //update
        return bufferDataEntry.getBaseInputEfficiency(); 
    }
    
    /**
     * Returns the arrayList representing the BASE output resources of the current Structure.
     * @return 
     */
    public ArrayList<Resource> getStructureBaseOutputResources() {
        ReferenceDataEntry bufferDataEntry = this.getReferenceDataTableEntry();  //update
        return bufferDataEntry.getOutputResourceArrayList(); 
    }; 
    
    /**
     * Returns the arrayList representing the BASE input resources of the current Structure.
     * @return 
     */
    public ArrayList<Resource> getStructureBaseInputResources() {
        ReferenceDataEntry bufferDataEntry = this.getReferenceDataTableEntry(); 
        return bufferDataEntry.getInputResourceArrayList(); 
    }; 
    
   
    
    // Functions for altering current Structure object properties.
    
    /**
     * changes the structureAmount property with the value giving in structuresToAdd. 
     * @param structuresToAdd 
     */
    public void updateStructureAmount (int structuresToAdd) {
        this.structureAmount += structuresToAdd;
    }
    
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
        double buffer = 0; 
        buffer = Utilities.geometricSeriesInitialSum(1, this.getStructureAmount(),this.getStrucutreCostScaleFactor());
        Resource outputResource = Resource.newResource(resourceType.getIdentifierName(), buffer); 
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
               buffer = Resource.newResource(currentResource.getIdentifierName(), currentResource.getResourceAmount()*scalingFactor);
               outputArrayList.add(buffer);
        }
        return outputArrayList; 
    }  
    
    
    /** Ticks the structure by a single tick; logic dictated by child classes.  **/ 
    // wip
    public void tickStructure() {
        ArrayList<Resource> baseInputResources = this.getReferenceDataTableEntry().getInputResourceArrayList();
        ArrayList<Resource> baseOutputResources = this.getReferenceDataTableEntry().getOutputResourceArrayList(); 
        
        boolean hasInputFlag = false; 
        Iterator baseInputResourceIterator = baseInputResources.iterator(); 
        Iterator baseOutputResourceIterator = baseOutputResources.iterator(); 
        
        
        
    }; 
    
    
}


