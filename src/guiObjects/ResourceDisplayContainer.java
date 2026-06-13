/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package guiObjects;

/**
 *
 * @author plcau
 */
import java.util.ArrayList; 
import java.util.Iterator; 
import Game.Resource; 
import Game.Game; 
import java.awt.GridBagConstraints; 
import javax.swing.JPanel; 
public class ResourceDisplayContainer extends javax.swing.JPanel {
    
    protected ArrayList<Resource> currentResourceTable; 
    public GridBagConstraints gridBagConstraints = new GridBagConstraints();
    
    /**
     * Creates new form ResourceDisplayComponent
     */
    public ResourceDisplayContainer() {
        
        initComponents();
        readNewResourceTable(Game.getCurrentScope().getBodyResourceStorage()); 
        
    }
    
    public void readNewResourceTable(ArrayList<Resource> resourceTable) {
        this.removeAll();
        // Buffer vertical space for the preferred size of the overall container for parent JScrollPane. 
        int verticalSpace = 0;
        // Buffer for Resource and JPanels produced by Resource.getResourceDisplayPanel() respectively.  
        Resource currentResource; 
        JPanel bufferPanel;
        
        // Sets the current focus resource table as resourceTable
        this.currentResourceTable = resourceTable; 
        
        // Iterate through all objects within the resource table. 
        for (int index = 0; index < resourceTable.size(); index++){
            
            // create GBCs for placement in Grid Bag Layout; 
            gridBagConstraints = new java.awt.GridBagConstraints();
            
            // Set all objects to be present within the first column (i.e all in the same column) 
            gridBagConstraints.gridx = 0; 
            
            //Sets all resource displays to fully span their full width
            gridBagConstraints.weightx = 1.0; 
            
            // Sets all resource displays to not fill vertical space (left for Glue object below) 
            gridBagConstraints.weighty = 0.0; 
            
            // Sets row value equal to the current index of this object within the input resource table. 
            gridBagConstraints.gridy = index;  
            
            //Sets components to anchor towarsd the North West;
            gridBagConstraints.anchor = GridBagConstraints.NORTHWEST; 
            
            // Then set components to fill horizontal (for redundancy) 
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL; 
        
            verticalSpace += 17; // Increment vertical space for preferredSpace for display
            
            // Update resourceDisplay of Resource; then get that resourceDisplay JPanel and add it to the overall container with GBCs defined above. 
            currentResource = resourceTable.get(index); 
            currentResource.updateResourceDisplay(index);
            bufferPanel = currentResource.getResourceDisplayPanel(); 
            add(bufferPanel,gridBagConstraints);
            
        }
        
        // Add spacing glue to the bottom of the layout 
        javax.swing.JPanel glue = new javax.swing.JPanel();
        glue.setOpaque(false); 
        java.awt.GridBagConstraints glueConstraints = new java.awt.GridBagConstraints();
        glueConstraints.gridx = 0; 
        glueConstraints.gridy = resourceTable.size(); // Set it to be present at the very last row of our display; i.e. index + 1; 
        glueConstraints.weightx = 1.0; // Fully span horizontally
        glueConstraints.weighty = 1.0; // Take highest priority within Grid Bag Layout vertically, take up all remaining space  
        glueConstraints.fill = GridBagConstraints.BOTH;
        add(glue, glueConstraints);
        
        setPreferredSize(new java.awt.Dimension(220,verticalSpace));
        
        
        // Update layout manager and redraw object. 
        this.validate(); 
        this.repaint();
        
    }
    
    public ArrayList<Resource> getCurrentResourceTable() {
        return this.currentResourceTable; 
    }
    
    
    public void clearResourceDisplay() {
        // Clear all objects from GUI first
        this.removeAll();
        
        // Then iterate through all resource objects and destroy their instances of the GUI to prevent memory leaks 
        Iterator resourceIterator = this.getCurrentResourceTable().iterator(); 
        Resource currentResource; 
        while (resourceIterator.hasNext()){
            currentResource = (Resource) resourceIterator.next(); 
            
            // Destroy GUI object linked to Resource object. 
            currentResource.destroyResourceDisplay();
        }
        // Add c

        // Validate layout
        
        
        this.validate();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(210, Integer.MAX_VALUE));
        setMinimumSize(new java.awt.Dimension(210, 0));
        setPreferredSize(new java.awt.Dimension(300, 100));
        setLayout(new java.awt.GridBagLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
