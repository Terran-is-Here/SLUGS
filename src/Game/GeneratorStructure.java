/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;


import java.util.Iterator;

/**
 *
 * @author plcau
 */
class GeneratorStructure extends Structure { 
    
    private GeneratorStructure(
            String _identifierName, 
            int _structureAmount, 
            BuildableBody _parentBody, 
            boolean _enabledFlag, 
            double _innateOutputEfficiency, 
            double _innateInputEfficiency){
        identifierName = _identifierName; 
        structureAmount = _structureAmount; 
        parentBody = _parentBody; 
        enabledFlag = _enabledFlag; 
        innateOutputEfficiency = _innateOutputEfficiency;
        innateInputEfficiency = _innateInputEfficiency; 
    }
    
    public static GeneratorStructure newGeneratorStructure(
            String _identifierName, 
            int _structureAmount,
            BuildableBody _parentBody,
            boolean _enabledFlag,
            double _innateOutputEfficiency, 
            double _innateInputEfficiency) {
        
        GeneratorStructure outputStructure = new GeneratorStructure(
                _identifierName, 
                _structureAmount,
                _parentBody,
                _enabledFlag,
                _innateOutputEfficiency, 
                _innateInputEfficiency
        );
        return outputStructure; 
    };     
    
    /**
     * Updates the bodyResourceStorage amounts of parentBody. 
     * Updates it based on structureBaseOutputResources and structureAmount (scales Linearly)
     */
    @Override
    public void tickStructure() {
        // Gets an iterator to iterate through every resource object within the baseOutputResources ArrayList.
        Iterator outputResourceIterator = this.getStructureBaseOutputResources().iterator();
        Resource currentResource;
        Resource outputResource = Resource.newResource(null, 0); 
        double outputResourceAmount = 0;
        while (outputResourceIterator.hasNext()) {
            // Gets current output resource;
            currentResource = (Resource) outputResourceIterator.next();
            
            // Calculates current output via the following equation: Base Resource Output * Structure Amount * Efficiency
            outputResourceAmount = this.getStructureAmount() * currentResource.getResourceAmount() * this.getStructureOutputEfficiency(); 
            
            // Sets buffer outputResource to parameters defined in currentResource. 
            outputResource.setResourceName(currentResource.getResourceName());
            outputResource.setResourceAmount(outputResourceAmount);
            
            // Updates corresponding resource amounts in parentBuildableBody. 
            this.getParentBuildableBody().updateResourceAmount(outputResource);
        }
    }
    
    
    
    
    }


    
    
    

