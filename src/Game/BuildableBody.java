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
abstract class BuildableBody {
    
    /**
     * Internal identifier for use within searching methods. 
     */
    protected String identifierName; 
    
    // ArrayList of Resource objects that dictate the amount of resources currently in this BuildableBody's storage.
    protected ArrayList<Resource> bodyResourceStorage; 
    
    // ArrayList of Structure objects that dictate the current about of Structure objects present in this BuildableBody
    protected ArrayList<Structure> bodyStructures; 
    
    // todo: implement Vehicles
    protected ArrayList<Vehicle> bodyVehicles; 
    
    
    // Methods to return properties; 
    
    /** 
     * Returns the bodyStructures ArrayList.
     * @return 
     */
    public ArrayList<Structure> getBodyStructures() {
        return this.bodyStructures;
    }
    
    /**
     * Returns the bodyResourceStorage ArrayList.
     * @return 
     */
    public ArrayList<Resource> getBodyResources() {
        return this.bodyResourceStorage;
    }
    
    /**
     * Returns the bodyVehicles ArrayList. 
     * @return 
     */
    public ArrayList<Vehicle> getBodyVehicles() {
        return this.bodyVehicles; 
    }
    
    /** 
     * Checks if structureToFind is already present on this Structure object; in which case return true.
     * If not, return false. 
     * @param structureToFind
     * @return 
     */
    public Boolean checkIfBuildingIsPresent(Structure structureToFind) {
        Iterator structureIterator = this.getBodyStructures().iterator(); 
        Structure currentStructure;
        // Iterates though every Structure within bodyStructures; 
        while (structureIterator.hasNext()) {
            currentStructure = (Structure) structureIterator.next(); 
            
            // Searches linearly to check if the strutcureToFind.structureIdentifier = currentStructure.structureIdentifier.
            // If a match is found; returns true.
            if (currentStructure.getIdentifier().equals(structureToFind.getIdentifier())) {
                return true; 
            }
            
        }
        return false;
    } 
    
    
    /**
     * Returns the Structure of a linear search of structureToFind in a buildableBody's bodyStructures via comparing their structureName. 
     * Returns null if the Structure is not found. 
     * @param structureToFind The structure being found and matched.
     * @return 
     */
    public Structure findStructure(Structure structureToFind){
        Iterator structureIterator = this.getBodyStructures().iterator(); 
        Structure currentStructure;
        // Iterates through every Structure within bodyStructures
        while (structureIterator.hasNext()) {
            currentStructure = (Structure)structureIterator.next(); 
            
            // Searches linearly to check if the strutcureToFind.structureIdentifier = currentStructure.structureIdentifier.
            // If a match is found; returns currentStructure Object.
            if (currentStructure.getIdentifier().equals(structureToFind.getIdentifier())) {
                return currentStructure;
            }
        }
        // Returns null if no corresponding Structure is found. 
        return null; 
    };
    
    /**
     * Returns the Resource of a linear search of resourceToFind in a buildableBody's bodyResources via comparing their resourceName. 
     * Returns null if the Resource is not found. 
     * @param resourceToFind
     * @return 
     */
    public Resource getResource(Resource resourceToFind) {
    Iterator bodyResourceIterator = this.getBodyResources().iterator(); 
    Resource currentResource; 
    
    // Iterates through every Resource object present within bodyResources. 
        while (bodyResourceIterator.hasNext()) {
            currentResource = (Resource) bodyResourceIterator.next();
            
            // Searches linarly to check if currentResource is resourceToFind based on their resourceName properties. 
            // If a match is found; return currentResource. 
            if(resourceToFind.getIdentifier().equals(currentResource.getIdentifier())) {
                return currentResource; 
            }
        }
        // Returns null if no corresponding Resource is found. 
        return null; 
    }
    
    /**
     * Adds resourceToAdd's resourceAmount value to it's corresponding Resource element within bodyResources.
     * 
     * Expects that resourceToUpdate.structureAmount value is the change desired in resource amount. 
     * 
     * Creates a new entry if the resource has not yet existed before within that ArrayList with the same data that resourceToUpdate has.
     * @param resourceToUpdate
     */
    public void updateResourceAmount(Resource resourceToUpdate) {
        
        // Attempts to get the current Resource object within bodyResources that matches resourceToAdd.
        Resource resourceFoundToAdd = getResource(resourceToUpdate); 
        double currentValue; 
        
        // In the case in which a match is found; directly add the resourceToAdd's value with resourceFoundToAdd's value.
        if (resourceFoundToAdd != null) {
            
            // set as temporary variable 
            currentValue = resourceFoundToAdd.getResourceAmount(); 
            
            // update resourceFoundToAdd with new value within resourceToAdd. 
            resourceFoundToAdd.setResourceAmount(currentValue + resourceToUpdate.getResourceAmount());
        }
        
        // If resource is not found at all; directly add as a new object within the ArrayList. 
        else {
            this.getBodyResources().add(resourceToUpdate); 
        }
    }
    
    /**
     * Adds structureToUpdate to it's corresponding Structure entry in bodyStructures. 
     * Expects that the structureToUpdate.structureAmount value is the change desired in structure amount. 
     * If the Structure object is not currently within bodyStructures via name comparison, create a new Structure with structureQuantity structureAmount. 
     * @param structureToUpdate
     * @param structureQuantity 
     */
    public void updateStructureAmount (Structure structureToUpdate, int structureQuantity) {
        Structure buildingBuffer;
        // In the case in which structureToAdd is already within bodyStructures via comparing structureName:
        if (this.checkIfBuildingIsPresent(structureToUpdate)) {
            buildingBuffer = this.findStructure(structureToUpdate);
            buildingBuffer.updateStructureAmount(structureQuantity);
        }
        
        // In the case in which structureToAdd is not already within bodyStructures, create a new Structures object within bodyStructures.
        else {
            buildingBuffer = structureToUpdate; 
            buildingBuffer.setStructureAmount(structureQuantity);
            this.getBodyStructures().add(buildingBuffer);
        }
    }
           
}