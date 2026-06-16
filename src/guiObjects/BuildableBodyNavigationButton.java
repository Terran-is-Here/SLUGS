/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guiObjects;

import javax.swing.ImageIcon;
import java.awt.Image; 
import javax.swing.JButton; 
import Game.BuildableBody; 
import Game.Game; 
import javax.swing.SwingConstants; 
import java.awt.Dimension; 
/**
 *
 * @author plcau
 */
public class BuildableBodyNavigationButton{
    
    public final static int BUTTON_IMAGE_HEIGHT = 78; 
    public final static int BUTTON_IMAGE_WIDTH = 78; 
    protected BuildableBody destinationBuildableBody;
    protected JButton buildableBodyNavigationButton; 
    protected ImageIcon buttonIcon; 
    
    private BuildableBodyNavigationButton(BuildableBody _destinationBuildableBody) {
        destinationBuildableBody = _destinationBuildableBody; 
        
        buildableBodyNavigationButton = new JButton(); 
    } 
    
    public static BuildableBodyNavigationButton newBuildableBodyNavigationButton(BuildableBody _destinationBuildableBody) {
        BuildableBodyNavigationButton output = new BuildableBodyNavigationButton(_destinationBuildableBody); 
        output.updateButton();
        return output;
    }
    
    /**
     * Updates/Creates the Icon and Display text of a navigation button; as well as setting it as the destination for updating Game's context view. 
     */
    protected void updateButton() {
        ImageIcon newIcon; 
        Image scaledImage; 
        JButton currentButton = this.getBuildableBodyNavigationButton(); 
        
        
        currentButton.setText(this.getDestinationBuildableBody().getDisplayName());
        // Get ImageIcon from directory
        newIcon = new ImageIcon(getClass().getResource(this.getDestinationBuildableBody().getReferenceDataTableEntry().getObjectIconDirectory())); 
        
        // Get Image object; then scale it to have a size of BUTTON_IMAGE_HEIGHT x BUTTON_IMAGE_WIDTH. 
        scaledImage = newIcon.getImage().getScaledInstance(BUTTON_IMAGE_WIDTH, BUTTON_IMAGE_HEIGHT, 0);
        
        // Then set it to the button's Image Icon. 
        newIcon = new ImageIcon(scaledImage); 
        this.setImageIcon(newIcon);
        
        // Set icon to currently scaled icon; 
        currentButton.setIcon(this.getImageIcon());
        
        currentButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        currentButton.setHorizontalTextPosition(SwingConstants.CENTER);
        
        currentButton.addActionListener(this::buttonOnClick);
    }
    
    
    protected void buttonOnClick(java.awt.event.ActionEvent e) {
        Game.setNewCurrentScope(this.destinationBuildableBody);
        Game.contentUpdateFlag = true; 
    }
    
    
    
    protected JButton getBuildableBodyNavigationButton() {
        return this.buildableBodyNavigationButton;
    }
    
    protected BuildableBody getDestinationBuildableBody() {
        return this.destinationBuildableBody;
    }
    
    protected void setImageIcon(ImageIcon newImageIcon) {
        this.buttonIcon = newImageIcon; 
    }
    
    protected ImageIcon getImageIcon() {
        return this.buttonIcon; 
    }
    
    public JButton getJButton() {
        return this.buildableBodyNavigationButton; 
    }
}
