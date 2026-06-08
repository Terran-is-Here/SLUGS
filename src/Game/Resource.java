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
    
    ReferenceDataEntry referenceDataEntry; 
    
    
    private Resource(
            String _identifierName,
            double _resourceAmount,
            ReferenceDataEntry _referenceDataEntry) {
        identifierName = _identifierName; 
        resourceAmount = _resourceAmount; 
        referenceDataEntry = _referenceDataEntry; 
    }
    
    public static Resource newResource(String _identifierName, double _resourceAmount) {
        return new Resource(
                _identifierName,
                _resourceAmount,
                GameData.fetchReferenceDataEntry(Game.getResourceReferenceTable(), _identifierName));
    }
    
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
    
    public double getResourceAmount() {
        return this.resourceAmount; 
    }
    
    public String getResourceType() {
        return this.getReferenceDataTableEntry().getResourceType(); 
    }
    
    public void setResourceAmount(double newValue) {
        this.resourceAmount = newValue; 
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
            System.out.println("Resource Display Description: " +bufferResource.getResourceDisplayDescription()); 
            System.out.println("Resource Type: " + bufferResource.getResourceType());
                    
        }
    }
    
    
   
}
