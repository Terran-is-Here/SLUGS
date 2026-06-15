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
public class Vehicle extends BuildableBody{
    
    protected ArrayList<Resource> vehicleCargo; 
    
    
    protected Vehicle(String _identifierName,
            ArrayList<Resource> _bodyResourceStorage,
            ArrayList<Resource> _bodyResourceDeposits, 
            ArrayList<Structure> _bodyStructures, 
            ArrayList<Vehicle> _bodyVehicle, 
            ReferenceDataEntry _referenceDataEntry,
            BuildableBody _parentBuildableBody) {
            
            super(_identifierName, _bodyResourceStorage, _bodyResourceDeposits, _bodyStructures, _bodyVehicle, _referenceDataEntry, _parentBuildableBody); 
    }
}
