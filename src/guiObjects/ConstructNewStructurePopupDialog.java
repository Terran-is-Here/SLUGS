/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package guiObjects;

/**
 *
 * @author plcau
 */
import Game.ReferenceDataEntry; 
import Game.Structure;
import Game.BuildableBody; 
import Game.GameData;
import Game.Utilities; 
import static guiObjects.StructurePopupDialog.structureDialogWithResources;
import java.awt.Dialog;
import java.awt.Component; 
import java.util.ArrayList; 
import java.util.Iterator; 
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
public class ConstructNewStructurePopupDialog extends javax.swing.JPanel {
    
    BuildableBody parentBuildableBody; 
    ArrayList<String> choiceIdentifiers; 
    ArrayList<String> choiceDisplayText; 
    Structure currentStructure; 
    int selectedIndex; 
    JDialog currentPopup; 
    /**
     * Creates new form StructureNewStructurePopupDialog
     */
    
    
    private ConstructNewStructurePopupDialog(BuildableBody _parentBuildableBody) {
        parentBuildableBody = _parentBuildableBody; 
        initComponents();
        setComboBoxChoices();
    }
    
    public static ConstructNewStructurePopupDialog newConstructNewStructurePopupDialog(BuildableBody _parentBuildableBody) {
        return new ConstructNewStructurePopupDialog(_parentBuildableBody); 
    }
    
    public static void newConstructNewStructurePopup(BuildableBody _parentBuildableBody, Component parentComponent) {
        // Create new JDialog object, using SwingUtilities to get the highest most window ancestor of the parent component. 
        JDialog currentPopup = new JDialog(SwingUtilities.getWindowAncestor(parentComponent), Dialog.ModalityType.APPLICATION_MODAL);
        ConstructNewStructurePopupDialog newPopup =ConstructNewStructurePopupDialog.newConstructNewStructurePopupDialog(_parentBuildableBody);
        // Add this class's JPanel into the Frame of the JDialog object and sets it to the east; 
        currentPopup.add(newPopup, java.awt.BorderLayout.WEST); 
        
        // Prevent resizing; 
        currentPopup.setResizable(false);
        
        // Set title to be the display name of the current structure; 
        currentPopup.setTitle("New Build Order");
        
        // Calculates layout
        currentPopup.pack(); 
        
        currentPopup.setLocationRelativeTo(SwingUtilities.getWindowAncestor(parentComponent));
        newPopup.setCurrentPopup(currentPopup);
        currentPopup.setVisible(true);
    }
    
    
    protected void setComboBoxChoices() {
        // Get ArrayList of Identifiers signifying potentially buildable structures within this BuildableBody. 
        this.setChoiceIdentifiers(parentBuildableBody.findBuildableStructures()); 
        
        
        if (!this.getChoiceIdentifiers().isEmpty()) {
        // Iterate through all identifiers and get display text entries. 
        ArrayList<String> buffer = new ArrayList<>(); 
        Iterator choiceIdentifierIterator = this.getChoiceIdentifiers().iterator(); 
        String currentIdentifier, temp; 
        while (choiceIdentifierIterator.hasNext()) {
            // Get current identifier; 
            currentIdentifier = (String) choiceIdentifierIterator.next(); 
            
            // Then get current display name from the associated reference data entry of the object; 
            temp = GameData.fetchReferenceDataEntry(Game.Game.getStructureReferenceTable(), currentIdentifier).getDisplayName(); 
            buffer.add(temp); 
        }
        // Set choiceDisplayText arrayList to be all displayText value of associated identifiers within the same index in choiceIdentifiers. 
        this.setChoiceDisplayText(buffer);  
        
        String[] toSetAsChoiceDisplay = new String[this.getChoiceDisplayText().size()-1]; 
        toSetAsChoiceDisplay = this.getChoiceDisplayText().toArray(toSetAsChoiceDisplay); 
        cmbxStructureChoices.setModel(new javax.swing.DefaultComboBoxModel( toSetAsChoiceDisplay));
        cmbxStructureChoices.setSelectedIndex(0);
        } 
        else {
            String[] toSetASChoiceDisplay = {"No build options available"}; 
            cmbxStructureChoices.setModel(new javax.swing.DefaultComboBoxModel(toSetASChoiceDisplay)); 
            btnBuildStructure.setEnabled(false);
            cmbxStructureChoices.setEnabled(false);
            
        }
        
        
    }
    
    public ArrayList<String> getChoiceIdentifiers() {
        return this.choiceIdentifiers; 
    }
    
    public ArrayList<String> getChoiceDisplayText() {
        return this.choiceDisplayText; 
    }
    
    public void setChoiceIdentifiers(ArrayList<String> newIdentifiers) {
        this.choiceIdentifiers = newIdentifiers; 
    }
    
    public void setChoiceDisplayText(ArrayList<String> newDisplayText) {
        this.choiceDisplayText = newDisplayText; 
    }
    
    protected void setCurrentPopup(JDialog newPopup) {
        this.currentPopup = newPopup; 
    }
    
    protected JDialog getCurrentPopup() {
        return this.currentPopup; 
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

        cmbxStructureChoices = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnBuildStructure = new javax.swing.JButton();
        bcnCancelBuild = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        cmbxStructureChoices.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "testing", " " }));
        cmbxStructureChoices.addActionListener(this::cmbxStructureChoicesActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(cmbxStructureChoices, gridBagConstraints);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Construct New Structure");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jLabel1, gridBagConstraints);

        btnBuildStructure.setText("Build");
        btnBuildStructure.addActionListener(this::btnBuildStructureActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 14, 3, 14);
        add(btnBuildStructure, gridBagConstraints);

        bcnCancelBuild.setText("Cancel");
        bcnCancelBuild.addActionListener(this::bcnCancelBuildActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 14, 3, 14);
        add(bcnCancelBuild, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbxStructureChoicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbxStructureChoicesActionPerformed
        // TODO add your handling code here:
        selectedIndex = cmbxStructureChoices.getSelectedIndex();
        this.currentStructure =  Game.Structure.newStructure(this.getChoiceIdentifiers().get(this.selectedIndex), 0, this.parentBuildableBody);
    }//GEN-LAST:event_cmbxStructureChoicesActionPerformed

    private void btnBuildStructureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuildStructureActionPerformed
        // TODO add your handling code here:
        String displayMessage = "How many " + this.getChoiceDisplayText().get(this.selectedIndex)+ "(s) to build?"; 
        int structuresToBuild = structureDialogWithResources(displayMessage,this.currentStructure, "Build Costs:", "Are you sure? ");
        
        // As convention; 
        if (structuresToBuild == -1) {
            JOptionPane.showMessageDialog(null, "Build order cancelled.", "Result", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            // Attempt to build structures based off currentStructure and structuresToBuild. 
            BuildableBody.buildStructure(this.currentStructure.getParentBuildableBody(), this.currentStructure, structuresToBuild);
            
        }
    }//GEN-LAST:event_btnBuildStructureActionPerformed

    private void bcnCancelBuildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcnCancelBuildActionPerformed
        this.getCurrentPopup().setVisible(false);
        this.getCurrentPopup().dispose();
    }//GEN-LAST:event_bcnCancelBuildActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bcnCancelBuild;
    private javax.swing.JButton btnBuildStructure;
    private javax.swing.JComboBox<String> cmbxStructureChoices;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
