/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator; 
import java.util.Scanner;  
/**
 * Contains methods to create reference data tables, as well as methods to fetch data entries within them based off an internal identifier. 
 * @author plcau
 */
public class GameData {
    
    
    /**
     * Processes a line from the expected file format and returns a value's name and value definition.
     * @param inputRegex String regex to match with, removes this before splitting inputString.
     * @param inputString Line of file to process as a String. 
     * @return 
     */
    public static String[] getLineValue(String inputRegex, String inputString) {
        // First trims of the initial identifier of this specific regex value.
        // For insstance, given input.iron_ore=123456; this would return iron_ore=123456.
        String processedInputString = inputString.replaceFirst(inputRegex, "");
        
        // Isolates the name of the term via identifier; and returns the second part as the value. 
        // Given our previous example; iron_ore=123456 -> ["iron_ore", "123456"] as a string array. 
        // In the case of =123456 (i.e. for non-identifier values); returns ["", "123456"].
        String[] OutputArray = processedInputString.split(GameDataConfigs.VALUE_DEFINITION_REGEX);
        return OutputArray; 
                
    }
    
    /**
     * Work in progress. 
     * @param fileName String filename to prase as a reference table; must follow and or use expressions present within GameDataConfigs.java!
     * @return 
     */
    public static ArrayList<ReferenceDataEntry> readDataFileAsReferenceTable(String fileName) {
        ArrayList<ReferenceDataEntry> outputTable = new ArrayList(); 
        String currentLine; 
        String[] bufferStringArray;
        Resource bufferResource; 
        
        // Buffers for intermediate step values before being entered into ReferenceDataEntry. 
        String bufferIdentifierName = ""; 
        String bufferDisplayName = ""; 
        String bufferDisplayDescription = ""; 
        String bufferObjectType = ""; 
        String bufferObjectDisplayType = ""; 
        String bufferImageIcon = ""; 
        ArrayList<Resource> bufferInputResourceArrayList = new ArrayList(); 
        ArrayList<Resource> bufferOutputResourceArrayList = new ArrayList(); 
        ArrayList<Resource> bufferBuildCostResourceArrayList = new ArrayList(); 
        ArrayList<Resource> bufferDepositResourceArrayList = new ArrayList(); 
        
        double bufferCostScaleFactor = 1.0; 
        double bufferInputEfficiency = 1.0; 
        double bufferOutputEfficiency = 1.0; 
        boolean bufferBooleanFlag = false; 
        
        // Buffer ReferenceDataObject for addition to outputTable. 
        ReferenceDataEntry bufferReferenceDataEntry; 
        try {
            // Tries to open up .txt file with fileName filename. 
            File openedFile = new File(fileName); 
            
            // sets up output Scanner. 
            Scanner fileScanner = new Scanner(openedFile); 
            
            // Then run through all lines of the .txt file to parse. 
            while (fileScanner.hasNextLine()) {
                // Trim text to prevent any leading spaces from interfering with parsing operations. 
                currentLine = fileScanner.nextLine().trim(); 
                
                // If a new data entry is being read; reset all buffers. 
                if (currentLine.contains(GameDataConfigs.ENTRY_START_REGEX)) {
                    bufferIdentifierName = "";
                    bufferDisplayDescription = "";
                    bufferDisplayName = "";
                    bufferObjectType = ""; 
                    bufferObjectDisplayType = ""; 
                    bufferInputEfficiency = 1.0;
                    bufferOutputEfficiency = 1.0;
                    bufferCostScaleFactor = 1.0;
                    bufferInputResourceArrayList = new ArrayList<>();
                    bufferOutputResourceArrayList = new ArrayList<>();
                    bufferBuildCostResourceArrayList = new ArrayList<>();
                    bufferDepositResourceArrayList = new ArrayList<>(); 
                    bufferBooleanFlag = false; 
                    continue; 
                }
                
                // Checks if the current line starts with "entrystart" (i.e. data entry has finished.)
                // If so; add collected data into HashMap. 
                // Use bufferIdentifier as the "key" and set the respective bufferReferenceDataHashMapEntry as it's value pair.
                if (currentLine.startsWith(GameDataConfigs.ENTRY_END_REGEX)) {
                    // Create new dataHashMapEntry specific for Structure objects. 
                    bufferReferenceDataEntry = ReferenceDataEntry.newReferenceDataEntry(
                            bufferIdentifierName, 
                            bufferDisplayName,
                            bufferDisplayDescription, 
                            bufferInputResourceArrayList,
                            bufferOutputResourceArrayList,
                            bufferBuildCostResourceArrayList,
                            bufferDepositResourceArrayList,
                            bufferOutputEfficiency,
                            bufferInputEfficiency,
                            bufferCostScaleFactor,
                            bufferObjectType,
                            bufferObjectDisplayType, 
                            bufferBooleanFlag,
                            bufferImageIcon);
                    
                    // Put respective buffer entry at the end of the table.
                    
                    outputTable.add(bufferReferenceDataEntry);
                    continue; 
                }
                
                
                // Checks if current line starts with with "input."
                if (currentLine.startsWith(GameDataConfigs.INPUT_RESOURCE_REGEX)) {
                    // If true, add new Resource object to bufferInputResourceArrayList. 
                    
                    bufferStringArray = getLineValue(GameDataConfigs.INPUT_RESOURCE_REGEX, currentLine);
                    bufferResource = Resource.newResource(bufferStringArray[0], Double.parseDouble(bufferStringArray[1]));
                    bufferInputResourceArrayList.add(bufferResource);
                    continue; 
                }
                
                // Checks if current line starts with with "output." 
                if (currentLine.startsWith(GameDataConfigs.OUTPUT_RESOURCE_REGEX)) {
                    
                    // If true, add new Resource object to bufferOutputResourceArrayList
                    bufferStringArray = getLineValue(GameDataConfigs.OUTPUT_RESOURCE_REGEX, currentLine); 
                    bufferResource = Resource.newResource(bufferStringArray[0], Double.parseDouble(bufferStringArray[1]));
                    bufferOutputResourceArrayList.add(bufferResource); 
                    continue; 
                }
                
                //Checks if current line starts with with "cost." 
                if (currentLine.startsWith(GameDataConfigs.COST_RESOURCE_REGEX)) {
                    
                    // If true, add new Resource object to bufferCostResourceArrayList
                    bufferStringArray = getLineValue(GameDataConfigs.COST_RESOURCE_REGEX, currentLine); 
                    bufferResource = Resource.newResource(bufferStringArray[0], Double.parseDouble(bufferStringArray[1]));
                    bufferBuildCostResourceArrayList.add(bufferResource); 
                    continue; 
                }
                
                // Checks if current line starts with "identifier"
                if (currentLine.startsWith(GameDataConfigs.INTERNAL_IDENTIFIER_REGEX)) {
                    bufferStringArray = getLineValue(GameDataConfigs.INTERNAL_IDENTIFIER_REGEX, currentLine); 
                    bufferIdentifierName = bufferStringArray[1]; 
                    continue; 
                }
                
                // Checks if current line starts with "description".
                if (currentLine.startsWith(GameDataConfigs.DISPLAY_DESCRIPTION_REGEX)) {
                    // If true, set bufferDescription to the respective line's value. 
                    bufferStringArray = getLineValue(GameDataConfigs.DISPLAY_DESCRIPTION_REGEX, currentLine); 
                    bufferDisplayDescription = bufferStringArray[1]; 
                    continue; 
                }
                
                // Checks if current line starts with "displayname" 
                if (currentLine.startsWith(GameDataConfigs.DISPLAY_NAME_REGEX)) {
                    bufferStringArray = getLineValue(GameDataConfigs.DISPLAY_NAME_REGEX, currentLine); 
                    bufferDisplayName = bufferStringArray[1]; 
                    continue; 
                }
                
                if (currentLine.startsWith(GameDataConfigs.STRUCTURE_INPUT_EFFICIENCY_REGEX)) {
                    bufferStringArray = getLineValue(GameDataConfigs.STRUCTURE_INPUT_EFFICIENCY_REGEX, currentLine); 
                    bufferOutputEfficiency = Double.parseDouble(bufferStringArray[1]); 
                    continue; 
                }
                
                if (currentLine.startsWith(GameDataConfigs.STRUCTURE_OUTPUT_EFFICIENCY_REGEX)) {
                    bufferStringArray = getLineValue(GameDataConfigs.STRUCTURE_OUTPUT_EFFICIENCY_REGEX, currentLine); 
                    bufferInputEfficiency = Double.parseDouble(bufferStringArray[1]); 
                    continue; 
                }
                
                if (currentLine.startsWith(GameDataConfigs.STRUCTURE_COST_SCALE_FACTOR_REGEX)) {
                    bufferStringArray = getLineValue(GameDataConfigs.STRUCTURE_COST_SCALE_FACTOR_REGEX, currentLine); 
                    bufferCostScaleFactor = Double.parseDouble(bufferStringArray[1]); 
                    continue; 
                }
                
                if (currentLine.startsWith(GameDataConfigs.OBJECT_TYPE_REGEX)) {
                    bufferStringArray = getLineValue(GameDataConfigs.OBJECT_TYPE_REGEX, currentLine);
                    bufferObjectType = bufferStringArray[1]; 
                    continue; 
                }
                
                if (currentLine.startsWith(GameDataConfigs.BUILDABLEBODY_DEPOSIT_REGEX)) {
                    
                    // If true, add new Resource object to bufferCostResourceArrayList
                    bufferStringArray = getLineValue(GameDataConfigs.BUILDABLEBODY_DEPOSIT_REGEX, currentLine); 
                    bufferResource = Resource.newResource(bufferStringArray[0], Double.parseDouble(bufferStringArray[1]));
                    bufferDepositResourceArrayList.add(bufferResource); 
                    continue; 
                }
                
                if (currentLine.startsWith(GameDataConfigs.OBJECT_DISPLAY_TYPE_REGEX)) {
                    
                    // If true, add new Resource object to bufferCostResourceArrayList
                    bufferStringArray = getLineValue(GameDataConfigs.OBJECT_DISPLAY_TYPE_REGEX, currentLine);
                    bufferObjectDisplayType = bufferStringArray[1]; 
                    continue; 
                }
                
                if (currentLine.startsWith(GameDataConfigs.OBJECT_ICON_REGEX)) {
                    
                    // If true, add new Resource object to bufferCostResourceArrayList
                    bufferStringArray = getLineValue(GameDataConfigs.OBJECT_ICON_REGEX, currentLine);
                    bufferImageIcon = bufferStringArray[1]; 
                    continue; 
                }
                
                if (currentLine.startsWith(GameDataConfigs.BOOLEAN_FLAG_REGEX)) {
                    
                    // If true, add new Resource object to bufferCostResourceArrayList
                    bufferStringArray = getLineValue(GameDataConfigs.BOOLEAN_FLAG_REGEX, currentLine); 
                    if(bufferStringArray[1].equals("true")) {
                        bufferBooleanFlag = true; 
                    }
                    else {
                        bufferBooleanFlag = false; 
                    }
                    continue; 
                } 
            }
            return outputTable; 
        }
        catch (FileNotFoundException e) {
            System.out.println("The reference file: " +fileName + " was not found."); 
            e.printStackTrace(); 
            return null;
        }
    }
    /**
     * Uses binary search to find the corresponding ReferenceDataEntry within a reference arrayList. 
     * @param parentDataTable The parent data table in which to search. 
     * @param identifierToSearch The identifier to search by. 
     * @return Returns the respective reference data entry if an entry exists with identifierToSearch. 
     */
    public static ReferenceDataEntry fetchReferenceDataEntry(ArrayList <ReferenceDataEntry> parentDataTable, String identifierToSearch) {        
        if (identifierToSearch != null) {
            return getReferenceDataEntryStep(parentDataTable, identifierToSearch, 0, parentDataTable.size()-1); }
        else {
            return null; 
        }
        
    }
    
    /**
     * Internal binary search stepping algorithm for finding and returning ReferenceDataEntryValues within GameData's created reference tables. 
     * @param parentDataTable The parent ArrayList to use as the reference table to search through.
     * @param identifierToSearch The internalIdentifier to use as a search key. 
     * @param minSearchRow The minimum search row to search through in the parent ArrayList.
     * @param maxSearchRow The maximum search row to search through in the parent ArrayList. 
     * @return 
     */
    private static ReferenceDataEntry getReferenceDataEntryStep(ArrayList<ReferenceDataEntry> parentDataTable, String identifierToSearch, int minSearchRow, int maxSearchRow){
        int middle = (minSearchRow + maxSearchRow) / 2; 
        int comparison = identifierToSearch.compareTo(parentDataTable.get(middle).getIdentifierName()); 
        
        if (minSearchRow > maxSearchRow) {
            return ReferenceDataEntry.newEmptyReferenceDataEntry();
        }
        
        if (comparison == 0) {
            return parentDataTable.get(middle);
        }
        
        else if (comparison < 0 ) {
            return getReferenceDataEntryStep(parentDataTable, identifierToSearch, minSearchRow, middle -1); 
        }
        
        else {
            return getReferenceDataEntryStep(parentDataTable, identifierToSearch, middle + 1, maxSearchRow); 
        }
                
    }
    
    private static boolean loadGame() {
        return false; 
    }
    
    private static boolean saveGame() {
        return false; 
    }
    
    /**
     * Gets a subarray of Objects within inputTableToSearch that match with identifierArray's identifiers.
     * @param <T> Any object/class which extends the AbstractGameObject class. 
     * @param identifierArray An array of Identifiers to get a subArray from a referenceDataTable. 
     * @param inputTableToSearch An array of Objects of type <T> to search from using identifierArray. 
     * @return Returns a subarray of inputTableToSearch whose objects match identifierArray's identifiers. Identifiers with no match are not included in this resultant array. 
     */
    public static <T extends AbstractGameObject> ArrayList<T> getSubArrayThroughIdentifier(ArrayList<String> identifierArray, ArrayList<T> inputTableToSearch) {
        ArrayList<T> outputArray = new ArrayList<>(); 
        
        // Iterate through identifier list to search and create subarray of corresponding ? -> AbstractGameObject objects. 
        Iterator identifierIterator = identifierArray.iterator(); 
        
        // Buffer value for any ? -> AbstractGameObject; 
        T buffer;
        
        // Loop identifier buffer; 
        String currentIdentifier; 
        
        // Iterate through all identifiers present; 
        while (identifierIterator.hasNext()) {
            
            // Get identifier and find object using Utilities.findObject. 
            currentIdentifier = (String) identifierIterator.next(); 
            buffer = (T) Utilities.findObject(inputTableToSearch, currentIdentifier); 
            
            // Checks if findObject acutally returned an object; only evaluates as null if it hasnt. 
            if (buffer == null) {
                continue; 
            }
            // if findObject actually found the object; add object to outputArray. 
            else {
                outputArray.add(buffer); 
            }
        }
        return outputArray; 
    }
    
}

