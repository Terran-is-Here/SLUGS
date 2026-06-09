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
public class Resource{
    
    /**
     * The internal identifier of this Resource Object, used to gain information about this Resource from a data reference Hashmap. 
     */
    final private String identifierName;
    
    /**
     * The amount of resources currently present within the object. 
     */
    private double resourceAmount;
    
    /** ReferenceDataEntry of this type of Object, determined by identifierName and is used to gleam other information universal across 
     * the same type of resource.
     */
    ReferenceDataEntry referenceDataEntry; 
    
    /**
     * Special variable used for resources which are not directly consumed; yet contribute towards a cap (e.g. electricity). 
     */
    private double resourceUsed; 
    
    private Resource(
            String _identifierName,
            double _resourceAmount,
            double _resourceUsed,
            ReferenceDataEntry _referenceDataEntry
            ) {
        identifierName = _identifierName; 
        resourceAmount = _resourceAmount; 
        referenceDataEntry = _referenceDataEntry; 
        resourceUsed = _resourceUsed;
    }
    
    public static Resource newResource(String _identifierName, double _resourceAmount) {
        return new Resource(
                _identifierName,
                _resourceAmount,
                0,
                GameData.fetchReferenceDataEntry(Game.getResourceReferenceTable(), _identifierName));
    }
    
    
    public ReferenceDataEntry getReferenceDataTableEntry() {
        return referenceDataEntry; 
    }
    
    // ##Property Returning methods
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
        return this.referenceDataEntry.getDescription(); 
    }
    
    /**
     * Gets the current amount of resources logged within this object.
     * @return A double representing the resourceAmount property of this object.
     */
    public double getResourceAmount() {
        return this.resourceAmount; 
    }
    
    /**
     * Gets the current type of resource of this object. (i.e. renewable / consumed) 
     * @return Returns a string specifying the objectType of this specific Resource instance. 
     */
    public String getResourceType() {
        return this.referenceDataEntry.getObjectType(); 
    }
    
    public double getResourceUsedAmount() {
        return this.resourceUsed; 
    }
    
    public double getCurrentAvailableCapacity() {
        return (this.resourceAmount - this.resourceUsed); 
    }
    //## Property setting methods. 
    public void setResourceAmount(double newValue) {
        this.resourceAmount = newValue; 
    }
    
    public void setResourceUsedAmount (double newValue) {
        this.resourceUsed = newValue; 
    }
    
    /**
     * Debugging tool that prints out the contents of an ArrayList<Resoruce> to console. 
     * @param inputArrayList Input ArrayList to display contents of. 
     */
    public static void debugResourceArrayList(ArrayList<Resource> inputArrayList) {
        Iterator resourceListIterator = inputArrayList.iterator(); 
        Resource bufferResource;
        while (resourceListIterator.hasNext()) {
            bufferResource = (Resource)resourceListIterator.next();
            System.out.println("Identifier: " + bufferResource.getIdentifier());
            System.out.println("Resource Amount: " + bufferResource.getResourceAmount()); 
            System.out.println("Resource Display Name: " +bufferResource.getDisplayName()); 
            System.out.println("Resource Display Description: " +bufferResource.getDisplayDescription()); 
            System.out.println("Resource Type: " + bufferResource.getResourceType());
                    
        }
    }
    
    public static Resource getResourceFromArray(ArrayList<Resource> inputArrayList, String identifierName) {
        Iterator inputArrayListIterator = inputArrayList.iterator(); 
        Resource currentResource; 
        while (inputArrayListIterator.hasNext()) {
            currentResource = (Resource) inputArrayListIterator.next(); 
            if (currentResource.getIdentifier().equals(identifierName)) {
                return currentResource; 
            }
            
        }
        return null; 
    }
    
    
   
}
