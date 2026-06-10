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

public class Game {
    
    //FILE LOCATIONS FOR LOAD DATA
    final static String STRUCTURE_DATA_FILE = "StructureDataFile.txt"; 
    final static String RESOURCE_DATA_FILE = "ResourceDataFile.txt"; 
    final static String BUILDABLEBODY_DATA_FILE = "BuildableBodyDataFile.txt"; 
    final static String PLANET_DATA_FILE = ""; 
    
    // Game runspeed settings
    private static int targetMillis = 1000/30, 
            lastTime = (int)System.currentTimeMillis(),
            targetTime = lastTime + targetMillis;
    
    /**
     * Reference HashMap containing all information on the base information of Structure objects. 
     */
    
    public static ArrayList<ReferenceDataEntry> structureReferenceTable = new ArrayList<>(); 
    public static ArrayList<ReferenceDataEntry> resourceReferenceTable = new ArrayList<>(); 
    public static ArrayList<ReferenceDataEntry> buildableBodyReferenceTable = new ArrayList<>(); 
    public static ArrayList<BuildableBody> buildableBodyTable = new ArrayList<>(); 
    public static void main(String[] args) {
        loadData(); 
        BuildableBody test = BuildableBody.initializeNewBuildableBody("earth"); 
        Structure test2 = Structure.newStructure("iron_mine", 2, test); 
        Resource test3 = Resource.newResource("iron_ore", 100); 
        test.getBodyResourceStorage().add(test3); 
        System.out.println("testing!!");
        Resource.debugResourceArrayList(test.getBodyResourceStorage());
        System.out.println("testing2!!");
        Resource.debugResourceArrayList(test2.getStructureBaseInputResources());
        System.out.println("testing3!!");
        test2.tickStructure();
        System.out.println("testing4!!");
        Resource.debugResourceArrayList(test.getBodyResourceStorage());
        
    }
    
    /**
     * Loads important game data such as reference hashMaps, raises and ends execution if files do not properly exist. 
     */
    public static void loadData() {
        resourceReferenceTable = GameData.readResourceDataFile(RESOURCE_DATA_FILE); 
        structureReferenceTable = GameData.readStructureDataFile(STRUCTURE_DATA_FILE);
        buildableBodyReferenceTable = GameData.readBuildableBodyDataFile(BUILDABLEBODY_DATA_FILE); 
        System.out.println("File loading successful.");
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

    
    }

    

