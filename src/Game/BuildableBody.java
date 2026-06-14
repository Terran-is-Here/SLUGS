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
public class BuildableBody extends AbstractGameObject{
    
    
    
    /**
     * ArrayList of Resource objects that dictate the amount of Resources currently in this BuildableBody's storage. 
    */
    protected ArrayList<Resource> bodyResourceStorage; 
    
    /**
     * ArrayList of Resource objects that dictate the amount of Resources currently within this BuildableBody's internal deposits (i.e. not readily accessible). 
     */
    protected ArrayList<Resource> bodyResourceDeposits; 
    
    /** ArrayList of Resource Object that dictate the amount of resources allocated to some capacity-limiting purposes (e.g. electricity) */
    protected ArrayList<Resource> bodyCapacityResourceStorage; 
    
    /** ArrayList of Structure objects that represent the Structures currently present in this BuildableBody. */
    protected ArrayList<Structure> bodyStructures; 
    
    /** ArrayList of Vehicle objects that represent the Vehicles currently present in this BuildableBody.*/
    protected ArrayList<Vehicle> bodyVehicles; // Vehicles are currently unimplemented but will be in the future. 
    
    /** Flag if a BuildableBody is currently accessible to have structures built on it. */ 
    protected boolean buildableFlag = true; 
    
    /** Parent BuildableBody this BuildableBody belongs to*/
    protected BuildableBody parentBuildableBody; 
    //## Constructor Methods
    /**
     * Internal constructor method of the BuildableBody class.
     * @param _identifierName The internal identifier name used for internal identification/searching methods.
     * @param _bodyResourceStorage An ArrayList of Resources this BuildableBody currently holds.
     * @param _bodyStructures An ArrayList of Structures this BuildableBody currently holds. 
     * @param _bodyVehicle An ArrayList of Vehicles this BuildableBody currently has present.
     * @param _referenceDataEntry The reference Data Table entry this instance is linked to. 
     * @param _parentBuildableBody The parent BuildableBody of this object. 
     */
    protected BuildableBody(String _identifierName,
            ArrayList<Resource> _bodyResourceStorage,
            ArrayList<Resource> _bodyResourceDeposits, 
            ArrayList<Structure> _bodyStructures, 
            ArrayList<Vehicle> _bodyVehicle, 
            ReferenceDataEntry _referenceDataEntry,
            BuildableBody _parentBuildableBody) {
        
            identifierName = _identifierName; 
            bodyResourceStorage = _bodyResourceStorage;
            bodyResourceDeposits = _bodyResourceDeposits; 
            bodyStructures = _bodyStructures; 
            bodyVehicles = _bodyVehicle; 
            referenceDataEntry = _referenceDataEntry;
            parentBuildableBody = _parentBuildableBody;
    }
    
    /**
     * Creates a new BuildableBody object with parameters set from the identifierName. 
     * Automatically sets the Parent BuildableBody as null and must be assigned. 
     * @param _identifierName
     * @return 
     */
    public static BuildableBody initializeNewBuildableBody(String _identifierName) {
        BuildableBody buffer = new BuildableBody(
                _identifierName,
                new ArrayList<Resource>(),
                new ArrayList<Resource>(), 
                new ArrayList<Structure>(), 
                new ArrayList<Vehicle>(),
                GameData.fetchReferenceDataEntry(Game.getBuildableBodyReferenceTable(), _identifierName),
                null);
        // Automatically add self when instantiated into the master arrayList of BuildableBodies for tickGame(). 
        Game.getBuildableBodyContainerTable().add(buffer); 
        
        return buffer; 
    }
    
    
    
    //## Property Return Functions ##//
    
    
    /**
     * Gets this object's display name for use in the GUI
     * @return Returns the displayName field of this object's corresponding ReferenceDataEntry in the reference data table. 
     */
    public String getDisplayName() {
        return this.referenceDataEntry.getDisplayName(); 
    }
    
    /**
     * Gets this object's display description for use in the GUI
     * @return Returns the displayDescription field of this object's corresponding ReferenceDataEntry in the reference data table. 
     */
    public String getDisplayDescription() {
        return this.referenceDataEntry.getDescription(); 
    }
    
    /**
     * Gets the current list of Resources present on this object. 
     * @return Returns the bodyResourceStorage field of this object as an ArrayList of Resource objects.
     */
    public ArrayList<Resource> getBodyResourceStorage() {
        return this.bodyResourceStorage; 
    }
    
    /**
     * Gets the current list of Structures present on this object. 
     * @return Returns the bodyStructures field of this object as an ArrayList of Structure objects. 
     */
    public ArrayList<Structure> getBodyStructures() {
        return this.bodyStructures;
    }
    
    /**
     * Gets the current list of Vehicles present on this object.
     * @return Returns an ArrayList of Vehicles which point towards this object's bodyVehicles property.
     */
    public ArrayList<Vehicle> getBodyVehicles() {
        return this.bodyVehicles;
    }
    
    //##Property Assignment Functions ##//
    
    /**
     * Sets the parentBuildableBody of this object to _newParent.
     * @param _newParent The new BuildableBody to set this object to be the parent of. 
     */
    public void setParentBuildableBody (BuildableBody _newParent) {
        this.parentBuildableBody = _newParent; 
    }

    //## Structure Management Functions ##//
    
    /**
     * Checks if a building is present on this BuildableBody through the identifierName of the objects.
     * @param structureIdentifier String identifier to search for the structure with. 
     * @return Returns false in the case no match is found, returns true if a match is found. 
     */
    public boolean checkIfStructureIsPresent(String structureIdentifier) {
        Iterator structureIterator = this.getBodyStructures().iterator(); 
        Structure currentStructure; 
        
        while (structureIterator.hasNext()) {
            currentStructure =(Structure) structureIterator.next(); 
            if (currentStructure.getIdentifier().equals(structureIdentifier)) {
                return true;
            }
        }
        return false;
    }
    
    /** 
     * Checks if a building is present on this BuildableBody; then returns it if it exists. 
     * @param structureToFindIdentifier The identifier of the Structure object being searched for.
     * @return Returns the Structure object with the same identifierName as the structure being found. If no match is found, returns null.
     */
    public Structure getStructure (String structureToFindIdentifier) {
        Iterator structureIterator = this.getBodyStructures().iterator(); 
        Structure currentStructure; 
        if (checkIfStructureIsPresent(structureToFindIdentifier)){ 
            while (structureIterator.hasNext()) {
            currentStructure = (Structure) structureIterator.next(); 
            
            if (currentStructure.getIdentifier().equals(structureToFindIdentifier)) {
                return currentStructure; 
            }
            }
            // Also returns null if structure is not present at all (mainly for Apache Netbeans Error Handling
            return null; 
        
        }
        else {
        // Returns null if structure is not present at all. 
        return null;
        }
    }
    
    public void updateStructureAmount(String structureToUpdateIdentifier, int newValue) {
        // Checks if the resource is present first.
        Structure bufferStructure;
        if (checkIfStructureIsPresent(structureToUpdateIdentifier)) {
            bufferStructure = this.getStructure(structureToUpdateIdentifier); 
            bufferStructure.setStructureAmount(newValue);
        }
        // Else, make a new resource object into the body's resource storage.
        else {
            bufferStructure = Structure.newStructure(identifierName, newValue, this);
            this.bodyStructures.add(bufferStructure); 
        }
    }
    

    //## Resource Management Functions ##//
    
    /**
     * Checks if a Resource is present on this BuildableBody through the identifierName of the objects.
     * @param resourceIdentifier String identifier to search for the structure with. 
     * @return Returns false in the case no match is found, returns true if a match is found. 
     */
    public boolean checkIfResourceIsPresent (String resourceIdentifier) {
        Iterator resourceIterator = this.getBodyResourceStorage().iterator(); 
        Resource currentResource; 
        
        while (resourceIterator.hasNext()) {
            currentResource =(Resource) resourceIterator.next(); 
            if (currentResource.getIdentifier().equals(resourceIdentifier)) {
                return true;
            }
        }
        return false;
    }
    
    /** 
     * Checks if a resource is present on this BuildableBody; then returns it if it exists. 
     * @param resourceToFindIterator The identifier of the Resource object being searched for.
     * @return Returns the Resource object with the same identifierName as the resource being found. If no match is found, creates a new empty resource object with the same identifier and returns that.
     */
    public Resource getResource (String resourceToFindIdentifier) {
        Iterator resourceIterator = this.getBodyResourceStorage().iterator();
        Resource currentResource; 
        Resource bufferResource;
        
        // First check if resource is even present to begin with; 
        if (checkIfResourceIsPresent(resourceToFindIdentifier)){ 
            // If so; iterate through body resource storage to find it;  
            while (resourceIterator.hasNext()) {
                // Get current Resource in bodyResourceStorage, 
                currentResource = (Resource) resourceIterator.next(); 
                
                // If identifiers match between resourceToFindIdentifier and currentResource; then return currentResource. 
                if (currentResource.getIdentifier().equals(resourceToFindIdentifier)) {
                    return currentResource; 
                }
            }
        }
        // Create new output resource if it is not already present on the body, or if no match was found. 
        bufferResource = Resource.newResource(resourceToFindIdentifier, 0);
        this.getBodyResourceStorage().add(bufferResource);
        Utilities.quickSort(this.bodyResourceStorage);
        return bufferResource;
    }
    
    /**
     * Updates a resource value based off it's identifier. If a resource is not yet present, creates a new object and sets newValue as it's amount value.
     * @param resourceToUpdateIdentifier The internal identifier of the resource object being updated.
     * @param newValue The new value to set the resourceAmount property to. 
     */
    public void updateResourceAmount(String resourceToUpdateIdentifier, double newValue) {
        // Checks if the resource is present first.
        Resource bufferResource;
        if (checkIfResourceIsPresent(resourceToUpdateIdentifier)) {
            bufferResource = this.getResource(resourceToUpdateIdentifier); 
            bufferResource.setResourceAmount(newValue);
        }
        // Else, make a new resource object into the body's resource storage.
        else {
            bufferResource = Resource.newResource(resourceToUpdateIdentifier, newValue);
            Utilities.quickSort(this.bodyResourceStorage);
            this.bodyResourceStorage.add(bufferResource); 
        }
    }
    
    
    public static void buildStructure(BuildableBody bodyToBuildOn, Structure structureToBuild, int structuresToBuild) {
        
        // Get current available resources present in bodyToBuildOn; 
        ArrayList<Resource> parentBodyResources = bodyToBuildOn.getBodyResourceStorage(); 
        
        
        // Buffer variables for iteration. 
        Resource currentResource; 
        Resource currentResourceTaken; 
        Structure currentStructure; 
        Iterator structureResourceCostIterator = structureToBuild.getReferenceDataTableEntry().getCostResourceArrayList().iterator(); 
        
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
                    javax.swing.JOptionPane.showMessageDialog(null, "Insufficient resources to finish build order", "Insuffiicent resources",javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
            
            }
            else {
                javax.swing.JOptionPane.showMessageDialog(null, "Insufficient resources to finish build order", "Insuffiicent resources",javax.swing.JOptionPane.WARNING_MESSAGE);
                return; 
            }
         }
        
        
        // If all resources are available; check if a Structure object of the same identifier already exists. 
        if (bodyToBuildOn.checkIfStructureIsPresent(structureToBuild.getIdentifier())) {
            // If it exists; then add it to the count of structures present with that object and update resoruce tables accordingly. 
            currentStructure = bodyToBuildOn.getStructure(structureToBuild.getIdentifier()); 
            currentStructure.setStructureAmount(currentStructure.getStructureAmount() + structuresToBuild);
        }
        // If structure is not already inherently present; create new structure object and add to the BuildableBody's ArrayList of structures. 
        else {
            currentStructure = Structure.newStructure(structureToBuild.getIdentifier(), structuresToBuild, bodyToBuildOn); 
            bodyToBuildOn.getBodyStructures().add(currentStructure); 
        }
        
        structureResourceCostIterator = structureToBuild.getReferenceDataTableEntry().getCostResourceArrayList().iterator(); 
        // Then iterate through costs and update parentBody resource amounts accordingly. 
        while (structureResourceCostIterator.hasNext()) {
            currentResourceTaken = (Resource) structureResourceCostIterator.next();
            currentResource = bodyToBuildOn.getResource(currentResourceTaken.getIdentifier()); 
            tempResourceAmount = currentResource.getResourceAmount() - (currentResourceTaken.getResourceAmount() * structuresToBuild); 
            currentResource.setResourceAmount(tempResourceAmount);
                    
        }
    }
    
    public static void destroyStructure(BuildableBody parentBuildableBody,Structure inputStructure, int amountToDestroy) {
        Iterator structureResourceCostIterator = inputStructure.getBaseStructureCosts().iterator(); 
        Resource currentStructureResource; 
        Resource currentBodyResource; 
        double temp; 
        while (structureResourceCostIterator.hasNext()) {
            currentStructureResource = (Resource) structureResourceCostIterator.next(); 
            currentBodyResource = parentBuildableBody.getResource(currentStructureResource.getIdentifier());
            temp = currentStructureResource.getResourceAmount() * amountToDestroy; 
            currentBodyResource.setResourceAmount(currentBodyResource.getResourceAmount() - temp);
        }
        inputStructure.setStructureAmount(inputStructure.getStructureAmount() - amountToDestroy);
    } 
}