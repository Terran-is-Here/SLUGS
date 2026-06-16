package guiObjects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import Game.Game;
import Game.Structure; 
import javax.swing.JButton; 

/**
 * StructureButton object which implements a popup system to manipulate a Structure's settings (building, demolishing and disabling). 
 * @author plcau
 */
public class StructureButton{
    
    /** Current button display text*/
    private String buttonDisplayText; 
    
    /** Current Structure object this button points towards / gets it's data from*/
    private Structure currentStructure; 
    
    /**The actual JButton object*/
    private JButton structureButton; 
    
    /**
     * Constructs a new StructureButton object based off a display text and the current structure. 
     * @param _buttonDisplayText Text to set as the display text of this button; 
     * @param _currentStructure Current structure object this button uses for it's methods (i.e. build/demolish orders) 
     */
    private StructureButton(String _buttonDisplayText, Structure _currentStructure) {
        
        buttonDisplayText = _buttonDisplayText; 
        currentStructure = _currentStructure; 
        
        // Create new JButton and set it's description to the Structure's Display Description
        structureButton = new JButton(buttonDisplayText);
        structureButton.setToolTipText(currentStructure.getDisplayDescription());
        
        // Add listener for when this button is clicked to create a new popup dialog window. 
        structureButton.addActionListener(this::structureButtonClicked);
        
    }; 
    
    /**
     * Creates a new StructureButton object with a popup related to manipulating _currentStructure.
     * @param _buttonDisplayText Text to set as the display text of this button; 
     * @param _currentStructure Structure that this button directly links towards for manipulation (build orders, etc.) 
     * @return 
     */
    public static StructureButton newStructureButton(String _buttonDisplayText, Structure _currentStructure) {
        StructureButton output = new StructureButton (_buttonDisplayText, _currentStructure); 
        return output; 
    };
    
    /**
     * Creates a new popup dialog window for manipulating the structure this button links to. 
     * @param evt 
     */
    private void structureButtonClicked(java.awt.event.ActionEvent evt) {
        StructurePopupDialog.newStructurePopupDialogWindow(this.getCurrentStructure(), this.getStructureJButton());
    }
    
    /**
     * Sets the current button display text (as well as buttonDisplayText) towards newDisplayText.
     * @param newDisplayText A string that represents the new text this button displays. 
     */
    public void setDisplayText(String newDisplayText) {
        this.buttonDisplayText = newDisplayText;
        this.getStructureJButton().setText(this.getButtonDisplayText());
    }
    
    /**
     * Gets the current button's display text.
     * @return A String representing the current button's display text.
     */
    public String getButtonDisplayText() {
        return this.buttonDisplayText;
    }
    
    /**
     * Gets the current button's Structure. 
     * @return A Structure Object representing the button's current Structure object. 
     */
    public Structure getCurrentStructure() {
        return this.currentStructure; 
    }
    
    /**
     * Gets the current button's actual JButton object.
     * @return A Swing JButton representing the actual button.
     */
    public JButton getStructureJButton() {
        return this.structureButton; 
    }
}
