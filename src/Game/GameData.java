/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;


import java.io.File;
import java.nio.file.Files; 
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
    
    /** Load a game file and set it to be the data arrays for Game.*/ 
    public static void loadSaveData() {
        // File variables
        ArrayList<String> fileContents; 
        File currentFile;
        
        // Iterate through every file within savefile directory to get most recent one (i.e. the one with thie largest number) 
        int filesChecked = 0; 
        boolean mostRecentFileFound = false; 
        String currentFileName = ""; 
        while (!mostRecentFileFound) {
            currentFileName = SaveDataConfigs.SAVE_FILE_NAME + filesChecked + SaveDataConfigs.SAVE_FILE_EXTENSION; 
            // Iterate through every file within the directory and check if the file exists; 
            currentFile = new File(SaveDataConfigs.SAVE_FILE_DIRECTORY + currentFileName); 
            
            // If current file exists; continue parsing until the file is no longer found. 
            if (currentFile.exists()) {
                filesChecked += 1; 
                continue; 
            }
            // Catch case in which the game has no save files at all; in which case end loading function early. 
            else if (filesChecked == 0) {
                return; 
            }
            // If current file doesnt exist; previous file was the last accessible file.
            else {
                Game.saveFileLoadFlag = true; 
                currentFileName = SaveDataConfigs.SAVE_FILE_DIRECTORY + SaveDataConfigs.SAVE_FILE_NAME + (filesChecked-1) + SaveDataConfigs.SAVE_FILE_EXTENSION; 
                break;
            }
        }
        //Data Buffer Variables
        BuildableBody bufferBuildableBody = null; 
        Structure bufferStructure = null; 
        double resourceAmountBuffer = 0.0; 
        Resource bufferResource; 
       
        // Read file as an ArrayList of String objects; 
        fileContents = Utilities.readTxtFile(currentFileName);
        Iterator fileContentsIterator = fileContents.iterator(); 
        String currentLine = ""; 
        String[] bufferStringArray; 
        while (fileContentsIterator.hasNext()) {
            currentLine = (String) fileContentsIterator.next(); 
            
            // Check if line starts with "bodystart.body_identifier". If true; initialize new bufferBuildableBody with that identifier. 
            // This line will always be called first before any other properties of the buildablebody are initialized; meaning that any lines after it 
            // are safely defining things inside this body. 
            if (currentLine.startsWith(SaveDataConfigs.BODY_START)) {
                currentLine = currentLine.replaceFirst(SaveDataConfigs.BODY_START, ""); 
                // Since initializeNewBuildableBody already saves itself to Game's array; we don't need to add it again. 
                bufferBuildableBody = BuildableBody.initializeNewBuildableBody(currentLine); 
            }
            
            // Check if line has the structure of "structurestart.structure_identifier"
            // Then process line to create a new structure object with that identifier. 
            if (currentLine.startsWith(SaveDataConfigs.STRUCTURE_START)) {
                currentLine = currentLine.replaceFirst(SaveDataConfigs.STRUCTURE_START, ""); 
                bufferStructure = Structure.newStructure(currentLine,0, bufferBuildableBody); 
            }
            
            // Check if line has the structure of "amount=structure_amount"
            // Then process line to update current structure object's amount with structure_amount. 
            if (currentLine.startsWith(SaveDataConfigs.STRUCTURE_AMOUNT)) {
                currentLine = currentLine.replaceFirst(SaveDataConfigs.STRUCTURE_AMOUNT + SaveDataConfigs.VALUE_DEFINITION, ""); 
                bufferStructure.setStructureAmount(Integer.parseInt(currentLine));
            }
            
            // Check if line has the structure of "amount=inactive_structure_amount"
            // Then process line to update current structure object's inactive structure amount with inactive_structure_amount. 
            if (currentLine.startsWith(SaveDataConfigs.STRUCTURE_INACTIVE_AMOUNT)) {
                currentLine = currentLine.replaceFirst(SaveDataConfigs.STRUCTURE_INACTIVE_AMOUNT + SaveDataConfigs.VALUE_DEFINITION, ""); 
                bufferStructure.setInactiveStructureAmount(Integer.parseInt(currentLine));
            }
            
            // Check if line has the structure of "toggleflag=flag_state"
            // Then process line to update current structure object's enabledFlag with flag_state. 
            if (currentLine.startsWith(SaveDataConfigs.STRUCTURE_TOGGLE)) {
                currentLine = currentLine.replaceFirst(SaveDataConfigs.STRUCTURE_TOGGLE + SaveDataConfigs.VALUE_DEFINITION, ""); 
                bufferStructure.setStructureEnabledFlag(Boolean.parseBoolean(currentLine));
            }
            
            // Check if line has the structure of "structureend"
            // If so; add processed Structure into bufferBuildableBody; then set current variable to null
            if (currentLine.startsWith(SaveDataConfigs.STRUCTURE_END)) {
                bufferBuildableBody.getBodyStructures().add(bufferStructure); 
                bufferStructure = null; 
            }
            
            // Check if the line has the structure of "resourceamount.resource_identifier=resource_amount" 
            // If so; add processed resource with resource_identifier as it's identifier and resoruce_amount as it's amount. 
            if (currentLine.startsWith(SaveDataConfigs.RESOURCE_AMOUNT)) {
                // First, get it in terms of resource_identifier=resourceamount; 
                // Returns an array with [resource_identifier, resource_amount] 
                bufferStringArray = GameData.getLineValue(SaveDataConfigs.RESOURCE_AMOUNT, currentLine);
                
                // Parse second entry as a double as it represents our resource value; 
                resourceAmountBuffer = Double.parseDouble(bufferStringArray[1]); 
                
                // Create new Resource object with identifier and value; then add to our BuildableBody's buffer. 
                bufferResource = Resource.newResource(bufferStringArray[0],resourceAmountBuffer); 
                bufferBuildableBody.getBodyResourceStorage().add(bufferResource); 
            }
            
            // Check if the line has the structure of "resourcedepositamount.resource_identifier=resource_amount" 
            // If so; add processed resource with resource_identifier as it's identifier and resoruce_amount as it's amount. 
            if (currentLine.startsWith(SaveDataConfigs.RESOURCE_DEPOSIT_AMOUNT)) {
                // First, get it in terms of resource_identifier=resourceamount; 
                // Returns an array with [resource_identifier, resource_amount] 
                bufferStringArray = GameData.getLineValue(SaveDataConfigs.RESOURCE_DEPOSIT_AMOUNT, currentLine);
                
                // Parse second entry as a double as it represents our resource value; 
                resourceAmountBuffer = Double.parseDouble(bufferStringArray[1]); 
                
                // Create new Resource object with identifier and value; then add to our BuildableBody's buffer. 
                bufferResource = Resource.newResource(bufferStringArray[0],resourceAmountBuffer); 
                bufferBuildableBody.getBodyDeposits().add(bufferResource); 
            }
            
            
            // Check if the line has the structure of being "bodyend"; in which case clear current values for re-assignment. 
            if (currentLine.startsWith(SaveDataConfigs.BODY_END)) {
                bufferBuildableBody = null; 
                bufferStructure = null; 
                resourceAmountBuffer = 0.0; 
                bufferResource = null;
            }
        }
        
    }
    
    public static void saveData() {
        ArrayList<String> dataToWrite = new ArrayList<>(); 
        
        // All data that needs to be saved can be gathered from the master BuildableBody table; as these contain data on the speciifc instances of resources
        // and strucures present. 
        ArrayList<BuildableBody> buildableBodyData = Game.getBuildableBodyContainerTable(); 
        Iterator buildableBodyDataIterator = buildableBodyData.iterator(); 
        Iterator bodyStructuresIterator, bodyResourcesIterator; 
        BuildableBody currentBuildableBody; 
        Structure currentStructure; 
        Resource currentResource; 
        String buffer; 
        // Iterate through all bodies present in the master table to save data in the form of a String ArrayList
        while (buildableBodyDataIterator.hasNext()) {
            
            //Get the current buildable body
            currentBuildableBody = (BuildableBody) buildableBodyDataIterator.next();
            // Adds into first line as "bodystart.body_identifier"
            buffer = SaveDataConfigs.BODY_START + currentBuildableBody.getIdentifier();
            dataToWrite.add(buffer); 
            
            // Then iterate and save data of all structures present: 
            bodyStructuresIterator = currentBuildableBody.getBodyStructures().iterator(); 
            while (bodyStructuresIterator.hasNext()) {
                currentStructure = (Structure) bodyStructuresIterator.next(); 
                
                // Adds into first line as "structurestart.structure_identifier"
                buffer = SaveDataConfigs.STRUCTURE_START + currentStructure.getIdentifier(); 
                dataToWrite.add(buffer);
                
                //Then add current structure amount; inactive structure amount and toggle. 
                buffer = SaveDataConfigs.STRUCTURE_AMOUNT + SaveDataConfigs.VALUE_DEFINITION + currentStructure.getStructureAmount(); 
                dataToWrite.add(buffer);
                
                //Then add current inactive structure amount as  "inactiveamount=..." 
                buffer = SaveDataConfigs.STRUCTURE_INACTIVE_AMOUNT + SaveDataConfigs.VALUE_DEFINITION+ currentStructure.getInactiveStructureAmount(); 
                dataToWrite.add(buffer);
                
                //THen add current structure toggle status as "toggleflag=..." 
                buffer = SaveDataConfigs.STRUCTURE_TOGGLE + SaveDataConfigs.VALUE_DEFINITION+ currentStructure.getEnabledFlag(); 
                dataToWrite.add(buffer);
                
                // Then add "structureend" to denote that this structure has ended.
                buffer = SaveDataConfigs.STRUCTURE_END; 
                dataToWrite.add(buffer); 
            }
            
            // Then iterate through all Resources present in storage: 
            bodyResourcesIterator = currentBuildableBody.getBodyResourceStorage().iterator(); 
            
            while (bodyResourcesIterator.hasNext()) {
                currentResource = (Resource) bodyResourcesIterator.next();
                
                // Add "resource_deposit_amount.resource_identifier=resource_amount" into data to write.
                buffer = SaveDataConfigs.RESOURCE_AMOUNT + currentResource.getIdentifier()+ SaveDataConfigs.VALUE_DEFINITION + currentResource.getResourceAmount(); 
                dataToWrite.add(buffer);
            }
            
            // Then iterate through all Resources present in deposits: 
            bodyResourcesIterator = currentBuildableBody.getBodyDeposits().iterator(); 
            
            while (bodyResourcesIterator.hasNext()) {
                currentResource = (Resource) bodyResourcesIterator.next();
                
                // Add "resource_deposit_amount.resource_identifier=resource_amount" into data to write.
                buffer = SaveDataConfigs.RESOURCE_DEPOSIT_AMOUNT + currentResource.getIdentifier()+ SaveDataConfigs.VALUE_DEFINITION + currentResource.getResourceAmount(); 
                dataToWrite.add(buffer);
            }
            
            // Once all of the data fields of a BuildableBody are done; add bodyend line to signify this in save file: 
            buffer = SaveDataConfigs.BODY_END; 
            dataToWrite.add(buffer); 
            
        }
        
        // Now write it to a new save file within this game; 
        int filesChecked = 0; 
        boolean newFileCreated = false; 
        String currentFileName; 
        File currentFile; 
        while (!newFileCreated) {
            
            currentFileName = SaveDataConfigs.SAVE_FILE_NAME + filesChecked + SaveDataConfigs.SAVE_FILE_EXTENSION; 
            // Iterate through every file within the directory and check if the file exists; 
            currentFile = new File(SaveDataConfigs.SAVE_FILE_DIRECTORY + currentFileName); 
            // If file does not exist; write to data then break loop. 
            if (!currentFile.exists()) {
                Utilities.writeTxtFile(SaveDataConfigs.SAVE_FILE_DIRECTORY + currentFileName, dataToWrite);
                break; 
            }
            else {
                filesChecked += 1; 
                continue;
            }
            
        }
    }
}

