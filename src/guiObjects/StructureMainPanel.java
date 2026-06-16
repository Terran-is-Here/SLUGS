/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package guiObjects;


import java.util.ArrayList; 
import java.util.Iterator;
import java.awt.GridBagConstraints; 
import java.awt.Dimension; 
import Game.Structure; 
import Game.BuildableBody; 
/**
 * Main container object of StructureButtonGroups (and by extension; StructureButtons) 
 * @author plcau
 */
public class StructureMainPanel extends javax.swing.JPanel {
    
    private final static int INITIAL_HEIGHT = 60; 
    
    /**ArrayList of Structures to display overall in this panel.*/
    private ArrayList<Structure> structuresToDisplay; 
    
    /**BuildableBody this object is displaying the Structures of.*/
    private BuildableBody parentBuildableBody; 
    
    /**
     * Private constructor of StructureMainPanels; creates a new StrucureMainPanel object and initializes the JPanel. 
     * @param _structuresToDisplay ArrayList of Structure to Display.
     * @param _parentBuildableBody BuildableBody that this Object is displaying the structures of. 
     */
    private StructureMainPanel(ArrayList<Structure> _structuresToDisplay, BuildableBody _parentBuildableBody) {
        structuresToDisplay = _structuresToDisplay; 
        parentBuildableBody = _parentBuildableBody; 
        initComponents();
    }
    
    /**
     * Creates a new content JPanel formatted w/ StructureButtonGroups. Intended for using within MainBodyContainer. 
     * @param _structuresToDisplay ArrayList of Structure to Display.
     * @param _parentBuildableBody BuildableBody that this Object is displaying the structures of. 
     * @return Returns a StructureMainPanel (really a JPanel) full of StructureButtonGroups for use in the main game's GUI. 
     */
    public static StructureMainPanel newStructureMainPanel(ArrayList<Structure> _structuresToDisplay, BuildableBody _parentBuildableBody) {
        // Create new JPanel container; 
        StructureMainPanel currentPanel = new StructureMainPanel(_structuresToDisplay, _parentBuildableBody);
        currentPanel.addStructureButtonGroups();   
        return currentPanel; 
    }

    /**
     * Adds StructureButtonGroups onto a StructureMainPanel based on it's structuresToDisplay property. 
     */
    private void addStructureButtonGroups() {
        // Create an arrayList of keywords (objectType of Structures) that have already been processed; 
        ArrayList<String> keywordsProcessed = new ArrayList<>(); 
        GridBagConstraints objectConstraints; 
        
        // Height value used for dimensions of this overall object. 
        int totalHeight = StructureMainPanel.INITIAL_HEIGHT; 
        // Keep looping through the structures to display arraylist; only stop looping if no new keywords are deteted; 
        boolean newKeywordsFound = true;
        
        // Iterator and buffer values 
        Iterator structureIterator; 
        Structure currentStructure;
        StructureButtonGroup bufferPanel; 
        
        // ArrayList used as buffer for generating StructureButtonGroups. 
        ArrayList<Structure> bufferStructures = new ArrayList<>(); 
        
        // Current keywrods. 
        String currentKeyword = "";  // Loop variable for the current Keyword; 
        String currentDisplayKeyword = ""; // Loop variable for current display keyword; 
        String searchKeyword = ""; // Loop variable for current keyword being searched for; 
        String searchDisplayKeyword = ""; // Current display keyword; 
        
        // While new keywords are being found...
        
        while (newKeywordsFound) {
            
            // Reset loop variables; 
            structureIterator = this.getStructuresToDisplay().iterator();
            newKeywordsFound = false; 
            searchKeyword = ""; 
            bufferStructures = new ArrayList<>(); 
            
            // Loop through the entire structure array
            while (structureIterator.hasNext()) {
                // Get current Structure object and gets it's keyword. 
                currentStructure = (Structure) structureIterator.next(); 
                currentKeyword = currentStructure.getReferenceDataTableEntry().getObjectType(); 
                currentDisplayKeyword = currentStructure.getReferenceDataTableEntry().getObjectDisplayType(); 
                
                // If the current object already has a keyword we already processed; proceed to next object. 
                if (keywordsProcessed.contains(searchKeyword)) {
                    continue; 
                }
                
                // If the currentKeyword is empty; i.e. not yet populated with a value; and if this is a new keyword; set current keyword towards the search keyword.
                // This only happens if it is a new searchKeyword; in that case it is also a new category and as such plug it into the bufferStructures array. 
                if (searchKeyword.equals("") && !keywordsProcessed.contains(currentKeyword)) {
                    newKeywordsFound = true; 
                    searchKeyword = currentKeyword; 
                    searchDisplayKeyword = currentDisplayKeyword; 
                    bufferStructures.add(currentStructure); 
                    continue; 
                }
                
                // If the currentKeyword matches with the searchKeyWord; add it to bufferStructures
                if (currentKeyword.equals(searchKeyword)) {
                    bufferStructures.add(currentStructure); 
                }
                else { // If it isn't a match; continue. 
                    continue; 
                }
            }
            
            // If a new keyword is found; create a new StructureButtonGroup and add it to this object. 
            if (newKeywordsFound) {
                
                // Add newfound keyword into the keywords processed arrayList; 
                keywordsProcessed.add(searchKeyword); 
                objectConstraints = new GridBagConstraints(); 
                objectConstraints.gridx = 0; //Set to first cell in row; 
                objectConstraints.gridy = keywordsProcessed.size(); // Set to next unused column; i.e stack button groups vertically
                objectConstraints.weightx = 1.0;  // Fill content to size of grid bag cell
                objectConstraints.weighty = 0.0;  // Set content to not stretch vertically to fill content; 
                objectConstraints.fill = GridBagConstraints.HORIZONTAL; // Fill content to size of grid bag cell
                objectConstraints.anchor = GridBagConstraints.CENTER;  // Set content to be anchored towards the center
                //objectConstraints.insets = new java.awt.Insets(10,10,10,10);
                
                // Create new structure button group with Structures of the same type; with the heading set to the display version of the keyword. 
                bufferPanel = StructureButtonGroup.newStructureDisplayContainer(bufferStructures, searchDisplayKeyword); 
                
                // Set border; 
                bufferPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
                
                // Update total height for JScrollPane 
                totalHeight += bufferPanel.getPreferredSize().height; 
                
                // Add current bufferPanel
                add(bufferPanel, objectConstraints); 
            }
            
        }
        // Set preferred size = it's width; and the total preferred heights of the container objects. 
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, totalHeight));
    }
    public ArrayList<Structure> getStructuresToDisplay() {
        return this.structuresToDisplay; 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnBuildNewStructure = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        btnBuildNewStructure.setText("Build Structure");
        btnBuildNewStructure.addActionListener(this::btnBuildNewStructureActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(btnBuildNewStructure, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuildNewStructureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuildNewStructureActionPerformed
        // TODO add your handling code here:
        ConstructNewStructurePopupDialog.newConstructNewStructurePopup(parentBuildableBody, this);
    }//GEN-LAST:event_btnBuildNewStructureActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuildNewStructure;
    // End of variables declaration//GEN-END:variables
}
