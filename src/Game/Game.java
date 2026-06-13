/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

/**
 *
 * @author plcau
 */
import guiObjects.*;
import java.util.ArrayList;
import java.util.Iterator; 
public class Game {
    
    // File locations for reference data tables. 
    // Directories are parsed DATA_FILE_DIRECTORY\?_DATA_FILE.txt;
    final static String DATA_FILE_DIRECTORY = "datafiles\\"; 
    final static String STRUCTURE_DATA_FILE = "structures.txt"; 
    final static String RESOURCE_DATA_FILE = "resources.txt"; 
    final static String BUILDABLEBODY_DATA_FILE = "buildablebodies.txt";
    
    // Curent focused BuildableBody and or Vehicle (if applicable). 
    static BuildableBody currentScope; 
    
    // Boolean to check if there already exists a save file (will probably be reworked); 
    static Boolean saveFileLoadFlag = false; 
    
    // Internal game date object for keeping track of time (not ticks) 
    public static int gameDate =0; 
    
    // Game header title; will probably be replaced and added to it's own txt file. 
    final static public String GAME_NAME = "Space Logistics Utilitarian Guidance System (S.L.U.G.S) v0.1.3"; 
    
    // Global resource ArrayList accessible across all BuildableBodies; (probably meant to be used for resources) 
    static private ArrayList<Resource> globalResources = new ArrayList<>(); 
    
    // Game runspeed settings; used for main game loop ticking 
    private final static int TICKS_PER_MINUTE = 30; 
    private static int targetMillis = 1000/TICKS_PER_MINUTE, 
            lastTime = (int)System.currentTimeMillis(),
            targetTime = lastTime + targetMillis;
    
    public static GameGuiContainer GameContainer; 
    
    
    // ArrayLists which represent reference tables for objects of their name within the game. 
    private static ArrayList<ReferenceDataEntry> structureReferenceTable = new ArrayList<>(); 
    private static ArrayList<ReferenceDataEntry> resourceReferenceTable = new ArrayList<>(); 
    private static ArrayList<ReferenceDataEntry> buildableBodyReferenceTable = new ArrayList<>(); 
    
    // Arraylist representing the list of all current buildableBodies present within the game. 
    public static ArrayList<BuildableBody> buildableBodyTable = new ArrayList<>(); 
    
    //Main game function 
    public static void main(String[] args) {
        
        // Attempt to load and initialize game data for now. 
        loadData(); 
        
        //startGame; 
        startGame(); 
        // For now, set initial scope to be the first object created. In terms of testing; create this. 
        
        // Add 10 iron mines to test.
        getCurrentScope().getBodyStructures().add(Structure.newStructure("iron_mine", 10, getCurrentScope())); 
        getCurrentScope().getBodyStructures().add(Structure.newStructure("iron_furnace", 1, getCurrentScope())); 
        

        // Create GUI and set it visible after things are loaded
        GameContainer = new GameGuiContainer(); 
        
        // probably have this be further down 
        java.awt.EventQueue.invokeLater(() -> GameContainer.setVisible(true));
        
        
    }
    
    /**
     * Loads important game data such as reference hashMaps, raises and ends execution if files do not properly exist. 
     */
    public static void loadData() {
        // Load data reference tables from their respective files; 
        resourceReferenceTable = GameData.readDataFileAsReferenceTable(DATA_FILE_DIRECTORY + RESOURCE_DATA_FILE);
        structureReferenceTable = GameData.readDataFileAsReferenceTable(DATA_FILE_DIRECTORY + STRUCTURE_DATA_FILE);
        buildableBodyReferenceTable = GameData.readDataFileAsReferenceTable(DATA_FILE_DIRECTORY + BUILDABLEBODY_DATA_FILE); 
        
        // Then quicksort reference tables such that we can use binarysearch on them for more efficient parsing. 
        Utilities.quickSort(resourceReferenceTable);
        Utilities.quickSort(structureReferenceTable);
        Utilities.quickSort(buildableBodyReferenceTable);
        }
    
    
    public static void startGame() {
        // Checks if a save game has already been loaded. 
        // If not; instantialize new game. 
        ReferenceDataEntry referenceDataEntry; 
        Iterator referenceDataEntryIterator; 
        BuildableBody buffer; 
        if (!saveFileLoadFlag) {
            // Generate initial set of BuildableBody Objects
            referenceDataEntryIterator = Game.getBuildableBodyReferenceTable().iterator(); 
            
            while (referenceDataEntryIterator.hasNext()){
                referenceDataEntry = (ReferenceDataEntry) referenceDataEntryIterator.next(); 
                buffer = BuildableBody.initializeNewBuildableBody(referenceDataEntry.getIdentifier());
            }
            // Get the first indexed object and set as current game scope. 
            setCurrentScope(Game.getBuildableBodyContainerTable().get(0));
        }
    }
    
    /**
     * Main game loop. 
     */
    public static void tickGame() {
        
        gameDate++; 
        Iterator gameBuildableBodyIterator = buildableBodyTable.iterator(); 
        Iterator structureIterator; 
        BuildableBody currentBuildableBody; 
        Structure currentStructure; 
        // Update all internal storages by iterating through all bodies present; then iterating across all structures within and ticking them.
        while (gameBuildableBodyIterator.hasNext()) {
            
            currentBuildableBody = (BuildableBody) gameBuildableBodyIterator.next();
            structureIterator = currentBuildableBody.getBodyStructures().iterator(); 
            while (structureIterator.hasNext()) {
                currentStructure = (Structure) structureIterator.next(); 
                currentStructure.tickStructure();
            }
        }
        
        
        // Then, update Game UI properties.  
        //First, update resource table
        
        // Then update game UI 
        
        GameContainer.resourceDisplayPanel.readNewResourceTable(getCurrentScope().getBodyResourceStorage());
        GameContainer.updateUI();
        
    }
    
    /**
     * Function that returns the reference table specifically for Structure objects. 
     * @return 
     */
    public static ArrayList<ReferenceDataEntry> getStructureReferenceTable() {
        return structureReferenceTable; 
    }
    
    /**
     * Function that returns the reference table specifically for Resource objects. 
     * @return 
     */
    public static ArrayList<ReferenceDataEntry> getResourceReferenceTable() {
        return resourceReferenceTable;
    }
    
    /**
     * Function that returns the reference table specifically for BuildableBody objects. 
     * @return 
     */
    public static ArrayList<ReferenceDataEntry> getBuildableBodyReferenceTable() {
        return buildableBodyReferenceTable; 
    }; 
    
    public static ArrayList<BuildableBody> getBuildableBodyContainerTable() {
        return buildableBodyTable; 
    }
    
    
    
    public static void setCurrentScope(BuildableBody scopeToMoveTo) {
        
        currentScope = scopeToMoveTo; 
        // Probably add GUI update logic here..?
    }
    
    public static BuildableBody getCurrentScope() {
        
        return currentScope; 
    }
    
    }

    

