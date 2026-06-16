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
    
    /**Current focused BuildableBody in terms of displaying it to GUI*/
    static BuildableBody currentScope; 
    
    /**Boolean to check if a save file already exists.*/
    public static Boolean saveFileLoadFlag = false; 
    
    /**Boolean to check if a more major GUI refresh is required (i.e. redrawing the Structures table; etc.)*/
    public static Boolean contentUpdateFlag = false; 
    
    /**Boolean to check if the game is currently paused or not*/
    public static Boolean isPausedFlag = false; 
    
    /**Internal game date tracking variable*/; 
    public static int gameDate =0; 
    
    // Global resource ArrayList accessible across all BuildableBodies; (probably meant to be used for resources) 
    static private ArrayList<Resource> globalResources = new ArrayList<>(); 
    
    // Main game container GUI object; 
    public static GameRootGui GameContainer; 
    
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
        
        // Start game setting initial data fields; 
        startGame(); 
        
        

        // Create GUI and set it visible after things are loaded
        GameContainer = new GameRootGui(); 
        
        // Set Java Swing GUI to EDT (should be separate from main thread) 
        java.awt.EventQueue.invokeLater(() -> GameContainer.setVisible(true));
        
        // Start main game loop on main thread. 
        startGameLoop();
        
    }
    
    /**
     * Loads important game data such as reference data tables, raises and ends execution if files do not properly exist. 
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
    
    /**
     * Initializes the game's initial parameters. 
     */
    public static void startGame() {
        // Attempt to load save data and set it to be the variables of our BuildableBodyContainerTable. 
        // Sets saveFileLoadFlag to true if a file is successfully loaded. 
        GameData.loadSaveData();
        if (!saveFileLoadFlag) {
            
            // Iterator buffers; 
            ReferenceDataEntry referenceDataEntry; 
            Iterator referenceDataEntryIterator; 
            
            // Generate initial set of BuildableBody Objects
            Iterator buildableBodyReferenceDataEntryIterator = Game.getBuildableBodyReferenceTable().iterator(); 
            
            // Iterate and initialize all BuildableBodies within BuildableBodyReferenceTable; 
            while (buildableBodyReferenceDataEntryIterator.hasNext()){
                
                // Get reference data entry and initialize new entry. 
                referenceDataEntry = (ReferenceDataEntry) buildableBodyReferenceDataEntryIterator.next(); 
                BuildableBody.initializeNewBuildableBody(referenceDataEntry.getIdentifier());
            }
            setCurrentScope(Game.getBuildableBodyContainerTable().get(0));
            // Set some intial structures just for bootstrapping on the first planet. 
            Game.getCurrentScope().getBodyResourceStorage().add(Resource.newResource("wood", 100));
            Game.getCurrentScope().getBodyResourceStorage().add(Resource.newResource("stone_brick", 20));
            Game.getCurrentScope().getBodyResourceStorage().add(Resource.newResource("coal", 20));
        }
        // Get the first indexed object and set as current game scope. 
        setCurrentScope(Game.getBuildableBodyContainerTable().get(0));    
    }
    
    
    // Game runspeed settings; used for main game loop ticking 
    private final static int TICKS_PER_SECOND = 1;
    /**
     * Main game loop, runs using a Delta Time-based gameloop.
     */
    public static void startGameLoop() {
        // Get current system time 
        long timeOfLastLoop = System.nanoTime(); 
        
        // Get expected delta value between time measurements (in units of ns) 
        final long IdealDeltaTime = 1000000000 /TICKS_PER_SECOND; 
        long sleepTime; 
        // Buffer values
        long timeNow, deltaTime, lastFPSInterval = 0;
        while (true) {
            // Get current time; 
            timeNow = System.nanoTime(); 
            
            // Calculate change in time since previous measurement; 
            deltaTime = timeNow - timeOfLastLoop; 
            // And set last time loop to current time; 
            timeOfLastLoop = timeNow; 
            
            // Determine total time since last game "tick" ;
            lastFPSInterval += deltaTime; 
            
            // Check of interval since last tick versus the ideal TPS time is greater than or equal to; in which case we tick immediately. 
            if (lastFPSInterval >= IdealDeltaTime) {
                lastFPSInterval -= IdealDeltaTime; 
                if (!isPausedFlag) {
                tickGame(); 
                }
            }
            
            try {
                // Calculate optimal sleep step S.T we dont hog the cpu; 
                // Calculate delta time between last loop time and current time; then add ideal TPS time (division for ns -> ms) 
                sleepTime  = (System.nanoTime()- timeOfLastLoop + IdealDeltaTime) /1000000; 
                Thread.sleep(sleepTime);
            }
            catch (java.lang.Exception e) {}
        }
        
    }
    
    /**
     * Main game loop tick step. 
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
            currentBuildableBody.refreshBodyResources();
        }
        
        
        // Then, update Game UI properties.  
        //First, update resource table
        
        // Then update game UI 
        Utilities.quickSort(getCurrentScope().getBodyResourceStorage()); 
        GameContainer.resourceDisplayPanel.readNewResourceTable(getCurrentScope().getBodyResourceStorage());
        GameContainer.updateUI();
        
        // Check if other more graphically-intensive steps need an update
        if (contentUpdateFlag) {
            contentUpdateFlag = false;
            GameContainer.mainBodyContainer.updateStructureDisplayPanel();
        }
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
    }
    
    public static void setNewCurrentScope(BuildableBody scopeToMoveTo) {
        setCurrentScope(scopeToMoveTo); 
        GameContainer.updateMainGamePanel();
    }
    
    public static BuildableBody getCurrentScope() {
        
        return currentScope; 
    }
    
    }

    

