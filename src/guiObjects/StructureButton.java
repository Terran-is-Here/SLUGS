package guiObjects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author plcau
 */
import java.awt.Dialog;
import javax.swing.JDialog;
import Game.Structure; 
import javax.swing.JButton; 
public class StructureButton{
    
    private String buttonDisplayText; 
    private Structure currentStructure; 
    private JButton structureButton; 
    
    private StructureButton(String _buttonDisplayText, Structure _currentStructure) {
        
        buttonDisplayText = buttonDisplayText; 
        currentStructure = currentStructure; 
        
        structureButton = new JButton(buttonDisplayText);
        structureButton.addActionListener(this::structureButtonClicked);
    }; 
    
    public static StructureButton newStructureButton(String _buttonDisplayText, Structure _currentStructure) {
        StructureButton output = new StructureButton (_buttonDisplayText, _currentStructure); 
        return output; 
    };
    
    private void structureButtonClicked(java.awt.event.ActionEvent evt) {
        
    }
    
    
    public String getButtonDisplayText() {
        return this.buttonDisplayText;
    }
    
    public Structure getCurrentStructure() {
        return this.currentStructure; 
    }
    
    public JButton getStructureButton() {
        return this.structureButton; 
    }
}
