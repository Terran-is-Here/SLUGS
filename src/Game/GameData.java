/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        String processedInputString = inputString.replaceFirst(inputRegex, "");
        String[] OutputArray = processedInputString.split(GameDataConfigs.VALUE_DEFINITION_REGEX);
        return OutputArray; 
                
    }
    
    /**
     * Reads fileName and returns a HashMap with internal identifiers as keys, and their respective ReferenceDataHashMapEntries as their value. 
     * @param fileName
     * @return 
     */
    public static ArrayList<ReferenceDataEntry> readStructureDataFile(String fileName) {
        
        // Set up output HashMap with a String, ReferenceDataEntry structure. 
        ArrayList<ReferenceDataEntry> outputTable = new ArrayList<>(); 
        String currentLine; 
       
        // Buffers for intermediate steps. 
        // Buffer for the resource input of a specific Structure. 
        ArrayList<Resource> bufferInputResourceArrayList = new ArrayList<>(); 
        
        // Buffer for the resource output of a specific Structure. 
        ArrayList<Resource> bufferOutputResourceArrayList = new ArrayList<>(); 
        
        // Buffer for the resource cost to build a specific Structure. 
        ArrayList<Resource> bufferCostResourceArrayList = new ArrayList<>(); 
        
        // Buffer for values returned by getLineValue. 
        String[] bufferStringArray; 
        
        // Intermediate value before appending bufferResource to resource arraylists above. 
        Resource bufferResource; 
        
        // buffer value for the internal identifier value of the specific referenceDataList entry. 
        String bufferIdentifier = ""; 
        
        // buffer value for the description parameter of the specific referenceDataList entry. 
        String bufferDescription = "";
        
        String bufferDisplayName = ""; 
        double bufferInputEfficiency = 1.0; 
        double bufferOutputEfficiency = 1.0; 
        double bufferCostScaleFactor = 1.0; 
        // Buffer referenceDataList object. 
        ReferenceDataEntry bufferReferenceDataEntry; 
        try {
            // Tries to open up .txt file with fileName filename. 
            File openedFile = new File(fileName); 
            
            // sets up output Scanner. 
            Scanner fileScanner = new Scanner(openedFile); 
            
            // While lines still exist for the scanner: 
            while (fileScanner.hasNextLine()) {
                
                currentLine = fileScanner.nextLine().trim(); 
                
                // Checks if the current line starts with "entryend" 
                // If so, initialize new set of buffers. 
                if (currentLine.contains(GameDataConfigs.ENTRY_END_REGEX)) {
                    bufferIdentifier = "";
                    bufferDescription = "";
                    bufferDisplayName = "";
                    bufferInputEfficiency = 1.0;
                    bufferOutputEfficiency = 1.0;
                    bufferCostScaleFactor = 1.0;
                    bufferInputResourceArrayList = new ArrayList<>();
                    bufferOutputResourceArrayList = new ArrayList<>();
                    bufferCostResourceArrayList = new ArrayList<>();
                    continue; 
                }
                
                // Checks if the current line starts with "entrystart" (i.e. data entry has finished.)
                // If so; add collected data into HashMap. 
                // Use bufferIdentifier as the "key" and set the respective bufferReferenceDataHashMapEntry as it's value pair.
                if (currentLine.startsWith(GameDataConfigs.ENTRY_START_REGEX)) {
                    // Create new dataHashMapEntry specific for Structure objects. 
                    bufferReferenceDataEntry = ReferenceDataEntry.newStructureReferenceDataEntry(
                            bufferIdentifier,
                            bufferDescription,
                            bufferDisplayName, 
                            bufferInputResourceArrayList,
                            bufferOutputResourceArrayList,
                            bufferCostResourceArrayList,
                            bufferCostScaleFactor,
                            bufferInputEfficiency,
                            bufferOutputEfficiency); 
                    
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
                    bufferCostResourceArrayList.add(bufferResource); 
                    continue; 
                }
                
                // Checks if current line starts with "identifier"
                if (currentLine.startsWith(GameDataConfigs.INTERNAL_IDENTIFIER_REGEX)) {
                    bufferStringArray = getLineValue(GameDataConfigs.INTERNAL_IDENTIFIER_REGEX, currentLine); 
                    bufferIdentifier = bufferStringArray[1]; 
                    continue; 
                }
                
                // Checks if current line starts with "description".
                if (currentLine.startsWith(GameDataConfigs.DISPLAY_DESCRIPTION_REGEX)) {
                    // If true, set bufferDescription to the respective line's value. 
                    bufferStringArray = getLineValue(GameDataConfigs.DISPLAY_DESCRIPTION_REGEX, currentLine); 
                    bufferDescription = bufferStringArray[1]; 
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
            
            }
            
            
            return outputTable;  
        }
        catch (FileNotFoundException e) {
            System.out.println("The reference file was not found."); 
            e.printStackTrace(); 
            return null;
        }
    }
    
    /**
     * @param fileName The string filename of the file to read to generate this HashMap. 
     * @return A HashMap of (String, ReferenceDataEntry) tailored for returning universal data across Resource instances of the same type. 
     */
    public static ArrayList<ReferenceDataEntry> readResourceDataFile(String fileName) {
    // Set up output HashMap with a String, ReferenceDataEntry structure. 
        ArrayList<ReferenceDataEntry> outputTable = new ArrayList<>(); 
        String currentLine; 
        String[] bufferStringArray; 
        // Buffer ReferenceDataEntry object. 
        ReferenceDataEntry bufferReferenceDataEntry; 
        
        // Buffer variables for ReferenceDataEntry (resource) 
        String bufferIdentifier = ""; 
        String bufferDescription = "";
        String bufferDisplayName = ""; 
        String bufferResourceType = ""; 
        try {
            // Tries to open up .txt file with fileName filename. 
            File openedFile = new File(fileName); 
            
            // sets up output Scanner. 
            Scanner fileScanner = new Scanner(openedFile); 
            
            while (fileScanner.hasNextLine()) {
                currentLine = fileScanner.nextLine().trim(); 
                // Checks if the current line starts with "entryend" 
                // If so, initialize new set of buffers. 
                if (currentLine.contains(GameDataConfigs.ENTRY_END_REGEX)) {
                    bufferIdentifier = "";
                    bufferDescription = "";
                    bufferDisplayName = "";
                    bufferResourceType = "";
                    continue; 
                }
                
                // Checks if the current line starts with "entrystart" (i.e. data entry has finished.)
                // If so; add collected data into HashMap. 
                // Use bufferIdentifier as the "key" and set the respective bufferReferenceDataHashMapEntry as it's value pair.
                if (currentLine.startsWith(GameDataConfigs.ENTRY_START_REGEX)) {
                    // Create new dataHashMapEntry specific for Structure objects. 
                    bufferReferenceDataEntry = ReferenceDataEntry.newResourceReferenceDataEntry(
                            bufferIdentifier,
                            bufferDescription, 
                            bufferDisplayName,
                            bufferResourceType);
                    // Put respective buffer entry at the end of the hashmap. 
                    outputTable.add(bufferReferenceDataEntry);
                    continue; 
                }
                
                // Checks if current line starts with "identifier"
                if (currentLine.startsWith(GameDataConfigs.INTERNAL_IDENTIFIER_REGEX)) {
                    bufferStringArray = getLineValue(GameDataConfigs.INTERNAL_IDENTIFIER_REGEX, currentLine); 
                    bufferIdentifier = bufferStringArray[1]; 
                    continue; 
                }
                
                // Checks if current line starts with "description".
                if (currentLine.startsWith(GameDataConfigs.DISPLAY_DESCRIPTION_REGEX)) {
                    // If true, set bufferDescription to the respective line's value. 
                    bufferStringArray = getLineValue(GameDataConfigs.DISPLAY_DESCRIPTION_REGEX, currentLine); 
                    bufferDescription = bufferStringArray[1]; 
                    continue; 
                }
                
                // Checks if current line starts with "displayname" 
                if (currentLine.startsWith(GameDataConfigs.DISPLAY_NAME_REGEX)) {
                    bufferStringArray = getLineValue(GameDataConfigs.DISPLAY_NAME_REGEX, currentLine); 
                    bufferDisplayName = bufferStringArray[1]; 
                    continue; 
                }
                
                if (currentLine.startsWith(GameDataConfigs.RESOURCE_TYPE_REGEX)) {
                    bufferStringArray = getLineValue(GameDataConfigs.RESOURCE_TYPE_REGEX, currentLine); 
                    bufferResourceType = bufferStringArray[1]; 
                    continue; 
                }
                
            }
            return outputTable; 
        }
        
        catch (FileNotFoundException e) {
            System.out.println("The reference file was not found."); 
            e.printStackTrace(); 
            return null;
        }
    }
    
    public static ArrayList<ReferenceDataEntry> readBuildableBodyDataFile(String fileName) {
        ArrayList<ReferenceDataEntry> outputTable = new ArrayList<>(); 
        String currentLine; 
        String[] bufferStringArray; 
        // Buffer ReferenceDataEntry object. 
        ReferenceDataEntry bufferReferenceDataEntry; 
        
        // Buffer variables for ReferenceDataEntry (resource) 
        String bufferIdentifier = ""; 
        String bufferDescription = "";
        String bufferDisplayName = ""; 
        String bufferBuildableBodyType = ""; 
        try {
            // Tries to open up .txt file with fileName filename. 
            File openedFile = new File(fileName); 
            
            // sets up output Scanner. 
            Scanner fileScanner = new Scanner(openedFile); 
            
            while (fileScanner.hasNextLine()) {
                currentLine = fileScanner.nextLine().trim(); 
                // Checks if the current line starts with "entryend" 
                // If so, initialize new set of buffers. 
                if (currentLine.contains(GameDataConfigs.ENTRY_END_REGEX)) {
                    bufferIdentifier = "";
                    bufferDescription = "";
                    bufferDisplayName = "";
                    bufferBuildableBodyType = "";
                    continue; 
                }
                
                // Checks if the current line starts with "entrystart" (i.e. data entry has finished.)
                // If so; add collected data into HashMap. 
                // Use bufferIdentifier as the "key" and set the respective bufferReferenceDataHashMapEntry as it's value pair.
                if (currentLine.startsWith(GameDataConfigs.ENTRY_START_REGEX)) {
                    // Create new dataHashMapEntry specific for Structure objects. 
                    bufferReferenceDataEntry = ReferenceDataEntry.newResourceReferenceDataEntry(
                            bufferIdentifier,
                            bufferDescription, 
                            bufferDisplayName,
                            bufferBuildableBodyType);
                    // Put respective buffer entry at the end of the hashmap. 
                    outputTable.add(bufferReferenceDataEntry);
                    continue; 
                }
                
                // Checks if current line starts with "identifier"
                if (currentLine.startsWith(GameDataConfigs.INTERNAL_IDENTIFIER_REGEX)) {
                    bufferStringArray = getLineValue(GameDataConfigs.INTERNAL_IDENTIFIER_REGEX, currentLine); 
                    bufferIdentifier = bufferStringArray[1]; 
                    continue; 
                }
                
                // Checks if current line starts with "description".
                if (currentLine.startsWith(GameDataConfigs.DISPLAY_DESCRIPTION_REGEX)) {
                    // If true, set bufferDescription to the respective line's value. 
                    bufferStringArray = getLineValue(GameDataConfigs.DISPLAY_DESCRIPTION_REGEX, currentLine); 
                    bufferDescription = bufferStringArray[1]; 
                    continue; 
                }
                
                // Checks if current line starts with "displayname" 
                if (currentLine.startsWith(GameDataConfigs.DISPLAY_NAME_REGEX)) {
                    bufferStringArray = getLineValue(GameDataConfigs.DISPLAY_NAME_REGEX, currentLine); 
                    bufferDisplayName = bufferStringArray[1]; 
                    continue; 
                }
                
                if (currentLine.startsWith(GameDataConfigs.BUILDABLEBODY_TYPE_REGEX)) {
                    bufferStringArray = getLineValue(GameDataConfigs.DISPLAY_NAME_REGEX, currentLine); 
                    bufferBuildableBodyType = bufferStringArray[1]; 
                    continue; 
                }
                
            }
            return outputTable; 
        }
        
        catch (FileNotFoundException e) {
            System.out.println("The reference file was not found."); 
            e.printStackTrace(); 
            return null;
        }
    }
    
    /**
     * Uses binary search to find the corresponding ReferenceDataEntry within a reference arrayList. 
     * @param parentDataTable The parent data table in which to search. 
     * @param identifierToSearch The identifier to search by. 
     * @return 
     */
    public static ReferenceDataEntry fetchReferenceDataEntry(ArrayList <ReferenceDataEntry> parentDataTable, String identifierToSearch) {
        return getReferenceDataEntryStep(parentDataTable, identifierToSearch, 0, parentDataTable.size()-1); 
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
            return ReferenceDataEntry.emptyReference();
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
}

