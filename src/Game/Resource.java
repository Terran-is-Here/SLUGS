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
import guiObjects.ResourceDisplayPanel; 
public class Resource extends AbstractGameObject{
    
    /**
     * The amount of resources previously present within this object; mainly used for GUI objects.
     */
    private double pastResourceAmount = 0; 
    
    private int displayIndex;
    /**
     * GUI object linked to this class for use in displaying to the player. 
     */
    private ResourceDisplayPanel resourceDisplayPanel; 
    /**
     * The amount of resources currently present within the object. 
     */
    private double resourceAmount;
    
    /**
     * Special variable used for resources which are not directly consumed; yet contribute towards a cap (e.g. electricity). 
     */
    private double resourceUsed; 
    
    private boolean isInitial = true; 
    /**
     * Private constructor for Resource objects. 
     * @param _identifierName Internal identifier for this Resource. 
     * @param _resourceAmount Amount of resources this object currently has.
     * @param _resourceUsed Amount of resources used (mainly used for capacity-limited resources) 
     * @param _referenceDataEntry The reference data entry this object uses. 
     */
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
    
    /**
     * Public constructor class for creating a new Resource object. 
     * @param _identifierName
     * @param _resourceAmount
     * @return 
     */
    public static Resource newResource(String _identifierName, double _resourceAmount) {
        ReferenceDataEntry referenceDataEntryBuffer = GameData.fetchReferenceDataEntry(Game.getResourceReferenceTable(), _identifierName);
        return new Resource(
                _identifierName,
                _resourceAmount,
                0,
                referenceDataEntryBuffer);
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
     * Returns the previous amount this Resource object had. 
     * @return 
     */
    public double getPreviousResourceValue() {
        return this.pastResourceAmount; 
    }
    /**
     * Returns the internal identifier of this Structure Object. 
     * @return 
     */
    public String getIdentifier() {
        return identifierName; 
    }
    
    public ResourceDisplayPanel getResourceDisplayPanel(){
        return this.resourceDisplayPanel; 
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
        if (this.isInitial){
            this.pastResourceAmount = this.resourceAmount;
            this.isInitial = false; 
        }

        this.resourceAmount = newValue; 
        // Checks if it has a related resource display object currently 
        if (this.getResourceDisplayPanel() != null) {
            this.updateResourceDisplay(true);
        }
    }
    
    public void setResourceDisplayPanel (ResourceDisplayPanel _resourceDisplayPanel) {
        this.resourceDisplayPanel = _resourceDisplayPanel;
    }
    public void setResourceUsedAmount (double newValue) {
        this.resourceUsed = newValue; 
    }
    
    public void setDisplayIndex (int newValue) {
        this.displayIndex = newValue; 
        System.out.println("+" + displayIndex);
    }
    /**
     * Debugging tool that prints out the contents of an ArrayList<Resoruce> to console. 
     * @param inputArrayList Input ArrayList to display contents of. 
     */
    public static void debugResourceArrayList(ArrayList<Resource> inputArrayList) {
        Iterator resourceListIterator = inputArrayList.iterator(); 
        Resource bufferResource;
        System.out.println("Resource Objects present:" + inputArrayList.size());
        while (resourceListIterator.hasNext()) {
            bufferResource = (Resource)resourceListIterator.next();
            System.out.println("Identifier: " + bufferResource.getIdentifier());
            System.out.println("Resource Amount: " + bufferResource.getResourceAmount()); 
            System.out.println("Resource Display Name: " +bufferResource.getDisplayName()); 
            System.out.println("Resource Display Description: " +bufferResource.getDisplayDescription()); 
            System.out.println("Resource Type: " + bufferResource.getResourceType());
            System.out.println(""); 
                    
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
    
    public double getDeltaResource() {
        return (this.getResourceAmount() - this.getPreviousResourceValue()); 
    }
    
    /**
     * Updates the resourceDisplayPanel object of this ResourceInstance. If it does not currently exist; create a new instance. 
     * @param currentIndex The current index of this resource object in terms of being displayed.
     */
    public void updateResourceDisplay(boolean isInternal) {
        if (!isInternal) {
            this.isInitial = true;
        }
        // Check first if the GUI object acutally exists. If it doesnt; create a new object. 
        if (this.resourceDisplayPanel == null) {
            
            System.out.println(this.displayIndex + "Display Index");
            this.resourceDisplayPanel = ResourceDisplayPanel.newResourceDisplayPanel(this, this.displayIndex); 
        }
        
        // If object actually exists; simply call update value. 
        else {
            this.resourceDisplayPanel.updateValue();
        }
    }
    
    
    
    /**
     * Destroys resourceDisplayPanel by setting it to null; 
     */
    public void destroyResourceDisplay() {
        this.resourceDisplayPanel = null; 
    }
    
    /**
     * Creates a basic formatted arraylist of strings which represent a set of resource values and their associated resource names within inputResourceList.
     * @param inputResourceList An input ArrayList of Resource objects.
     * @return A formatted string of Resource Name: Resource Value * scaleFactor. 
     */
    public static String[] getScaledResourceReceipt(ArrayList<Resource> inputResourceList, String headerMessage, String endingMessage) {
        Iterator resourceIterator = inputResourceList.iterator(); 
        double temp; 
        String[] outputArray = new String[inputResourceList.size()+2];  
        Resource tempResource; 
        String tempOutput;
        outputArray[0] = headerMessage;
        int index = 1; 
        while (resourceIterator.hasNext()) {
        
            tempResource = (Resource) resourceIterator.next(); 
            temp = tempResource.getResourceAmount(); 
            tempOutput ="> " +  tempResource.getDisplayName() + ": " + Double.toString(temp); 
            outputArray[index] = tempOutput; 
            index++;
        }
        outputArray[outputArray.length -1 ] = endingMessage; 
        return outputArray; 
    }
    
}
