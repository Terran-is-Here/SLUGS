package guiObjects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author plcau
 */
import Game.Game;
import Game.Structure; 
import javax.swing.JButton; 
public class StructureButton{
    
    private String buttonDisplayText; 
    private Structure currentStructure; 
    private JButton structureButton; 
    
    private StructureButton(String _buttonDisplayText, Structure _currentStructure) {
        
        buttonDisplayText = _buttonDisplayText; 
        currentStructure = _currentStructure; 
        
        structureButton = new JButton(buttonDisplayText);
        structureButton.setToolTipText(currentStructure.getDisplayDescription());
        structureButton.addActionListener(this::structureButtonClicked);
        
    }; 
    
    public static StructureButton newStructureButton(String _buttonDisplayText, Structure _currentStructure) {
        StructureButton output = new StructureButton (_buttonDisplayText, _currentStructure); 
        return output; 
    };
    
    
    private void structureButtonClicked(java.awt.event.ActionEvent evt) {
        StructurePopupDialog.newStructurePopupDialogWindow(this.getCurrentStructure(), this.getStructureJButton());
    }
    
    public void setDisplayText(String newDisplayText) {
        this.buttonDisplayText = newDisplayText;
        this.getStructureJButton().setText(this.getButtonDisplayText());
    }
    
    public String getButtonDisplayText() {
        return this.buttonDisplayText;
    }
    
    public Structure getCurrentStructure() {
        return this.currentStructure; 
    }
    
    public JButton getStructureJButton() {
        return this.structureButton; 
    }
}
