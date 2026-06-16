/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package guiObjects;


import Game.Structure;
import java.util.ArrayList; 
import java.awt.GridBagConstraints; 
import java.awt.Insets;
import java.awt.Dimension; 
/**
 * A group of Button objects with the same Category. 
 * @author plcau
 */
public class StructureButtonGroup extends javax.swing.JPanel {
    
    /**ArrayList representing the current Strucutres this ButtonGroup will display.*/
    private ArrayList<Structure> structureArrayList; 
    
    /**How many buttons that are to be displayed per row.*/
    private static final int BUTTONS_PER_ROW = 6;
    
    /** The category title of this structureButtonGroup*/ 
    private String categoryTitle; 
    
    /**Preferred size factory for every row of buttons to add to this JPanel's preferred size.*/
    private static int VERTICAL_SIZE_FACTOR = 50; 
    
    /**
     * Private constructor method for making a new StructureButtonGroup. 
     * @param _structureArrayList Array of structures to display within the button group
     * @param _categoryTitle String to use as a title for this Button group.
     */
    private StructureButtonGroup(ArrayList<Structure> _structureArrayList, String _categoryTitle) {
        structureArrayList = _structureArrayList; 
        categoryTitle = _categoryTitle; 
        initComponents();
        
        // Set header text equal to t
        lblStructureCategoryHeader.setText(categoryTitle);
    }
    
    public ArrayList<Structure> getStructureArrayList() {
        return this.structureArrayList; 
    }
    
    /**
     * Creates a new StructureButtonGroup of StructureButton objects using _structureArrayList.
     * @param _structureArrayList ArrayList of Structure Objects to create and link StructureButtons.
     * @param _categoryTitle Title of this StructureButtonGroup on the GUI. 
     * @return Returns a JPanel object representing a group of StructureButtons. 
     */
    public static StructureButtonGroup newStructureDisplayContainer(ArrayList<Structure> _structureArrayList, String _categoryTitle) {
        
        // Create new StructureButtonGroup Object for output
        StructureButtonGroup newContainer = new StructureButtonGroup(_structureArrayList, _categoryTitle); 
        
        // Buffer variables for loop; 
        Structure currentStructure; 
        StructureButton bufferStructureButton; 
        int index;
        // GBC object for layout; 
        GridBagConstraints currentObjectGridBagConstraints; 
        
        // Set up structure buttons within PnlButtonContainer; 
        for (index = 0; index < _structureArrayList.size(); index++) {
            // Set up GBCs for display objects. 
            currentObjectGridBagConstraints = new GridBagConstraints(); 
            
            // Make each button have 5px of padding on all sides around them in Grid Bag Layout. 
            currentObjectGridBagConstraints.insets = new Insets(5,5,5,5); 
            
            // Set position based on the current index of this button; with every row forming after every BUTTONS_PER_ROW objects. 
            currentObjectGridBagConstraints.gridx = (index%BUTTONS_PER_ROW) + 1;
            currentObjectGridBagConstraints.gridy = index/BUTTONS_PER_ROW; 
            
            // Set anchor to northwest and make the objects fill horizontally
            currentObjectGridBagConstraints.anchor = GridBagConstraints.NORTHWEST; 
            currentObjectGridBagConstraints.fill = GridBagConstraints.HORIZONTAL; 
            
            // Get current structure object from index; 
            currentStructure = newContainer.getStructureArrayList().get(index); 
            
            // Update (or create) a structure button linked to this object; 
            currentStructure.updateStructureButton();
            
            // Get the structure's StructureButton and add it to the PnlButtonContainer. 
            bufferStructureButton = currentStructure.getStructureButton(); 
            newContainer.PnlButtonContainer.add(bufferStructureButton.getStructureJButton(), currentObjectGridBagConstraints);
        }
        
        // Calculate effective number of rows to use for calulating effects on layout; 
        int rows = index/BUTTONS_PER_ROW; 
        
        // Get vertical spacing for layout and apply it to layouts / dimensions; 
        int verticalSpacing = (rows+1) * VERTICAL_SIZE_FACTOR; 
        
        // Set the container of ButtonObject's Dimensions scaled off verticalSpacing
        newContainer.PnlButtonContainer.setPreferredSize(new Dimension(600, verticalSpacing));
        newContainer.PnlButtonContainer.setMinimumSize(new Dimension(600, verticalSpacing));
        
        // Set the container of ButtonObject's Container's Dimensions scaled off verticalSpacing
        newContainer.setPreferredSize(new Dimension (600, verticalSpacing + 60));
        
        // Revalidate layouts and redraw objects. 
        newContainer.PnlButtonContainer.validate();
        newContainer.PnlButtonContainer.repaint();
        newContainer.validate();
        newContainer.repaint();
        return newContainer; 
    }
    
    protected javax.swing.JPanel getPnlButtonContainer(){
        return this.PnlButtonContainer; 
    }

    
    public String getCategoryTitle() {
        return this.categoryTitle; 
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

        lblStructureCategoryHeader = new javax.swing.JLabel();
        PnlButtonContainer = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        lblStructureCategoryHeader.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblStructureCategoryHeader.setText("Structure Category");
        add(lblStructureCategoryHeader, new java.awt.GridBagConstraints());

        PnlButtonContainer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PnlButtonContainer.setMaximumSize(new java.awt.Dimension(600, 2147483647));
        PnlButtonContainer.setMinimumSize(new java.awt.Dimension(600, 0));
        PnlButtonContainer.setPreferredSize(new java.awt.Dimension(600, 280));
        PnlButtonContainer.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        add(PnlButtonContainer, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JPanel PnlButtonContainer;
    private javax.swing.JLabel lblStructureCategoryHeader;
    // End of variables declaration//GEN-END:variables
}
