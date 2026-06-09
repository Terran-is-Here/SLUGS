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
    
    /**
     * Reference HashMap containing all information on the base information of Structure objects. 
     */
    
    public static ArrayList<ReferenceDataEntry> structureReferenceTable = new ArrayList<>(); 
    public static ArrayList<ReferenceDataEntry> resourceReferenceTable = new ArrayList<>(); 
    public static ArrayList<ReferenceDataEntry> buildableBodyReferenceTable = new ArrayList<>(); 
    
    public static void main(String[] args) {
        loadData(); 
    }
    
    /**
     * Loads important game data such as reference hashMaps, raises and ends execution if files do not properly exist. 
     */
    public static void loadData() {
        structureReferenceTable = GameData.readStructureDataFile(STRUCTURE_DATA_FILE);
        resourceReferenceTable = GameData.readResourceDataFile(RESOURCE_DATA_FILE); 
        buildableBodyReferenceTable = GameData.readBuildableBodyDataFile(BUILDABLEBODY_DATA_FILE); 
        }
    
    public static ArrayList<ReferenceDataEntry> getStructureReferenceTable() {
        return structureReferenceTable; 
    }
    
    public static ArrayList<ReferenceDataEntry> getResourceReferenceTable() {
        return resourceReferenceTable;
    }
    
    public static ArrayList<ReferenceDataEntry> getBuildableBodyReferenceTable() {
        return buildableBodyReferenceTable; 
    }; 

    
    }

    

