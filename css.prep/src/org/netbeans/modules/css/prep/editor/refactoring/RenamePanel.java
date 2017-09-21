/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.css.prep.editor.refactoring;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.modules.refactoring.spi.ui.CustomRefactoringPanel;

/**
 * Rename refactoring parameters panel
 *
 * @author  Pavel Flaska
 */
public class RenamePanel extends JPanel implements CustomRefactoringPanel {

    private final transient String oldName;
    private final transient ChangeListener parent;
    
    private boolean initialized;
    
    /** Creates new form RenamePanelName */
    public RenamePanel(String oldName, ChangeListener parent, String name, boolean editable, boolean showUpdateReferences) {
        setName(name);
        this.oldName = oldName;
        this.parent = parent;
        initComponents();
        updateReferencesCheckBox.setVisible(showUpdateReferences);
        nameField.setEnabled(editable);
        nameField.requestFocus();
        nameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent event) {
                RenamePanel.this.parent.stateChanged(null);
            }
            @Override
            public void insertUpdate(DocumentEvent event) {
                RenamePanel.this.parent.stateChanged(null);
            }
            @Override
            public void removeUpdate(DocumentEvent event) {
                RenamePanel.this.parent.stateChanged(null);
            }
        });
    }

    @Override
    public void initialize() {
        if (initialized)
            return ;
        //put initialization code here
        initialized = true;
    }
    
    public @Override void requestFocus() {
        nameField.requestFocus();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        label = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        warningLabel = new javax.swing.JLabel();
        textCheckBox = new javax.swing.JCheckBox();
        updateReferencesCheckBox = new javax.swing.JCheckBox();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 11, 11));
        setLayout(new java.awt.GridBagLayout());

        label.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label.setLabelFor(nameField);
        org.openide.awt.Mnemonics.setLocalizedText(label, org.openide.util.NbBundle.getMessage(RenamePanel.class, "LBL_NewName")); // NOI18N
        add(label, new java.awt.GridBagConstraints());

        nameField.setText(oldName);
        nameField.selectAll();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(nameField, gridBagConstraints);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/netbeans/modules/css/prep/editor/refactoring/Bundle"); // NOI18N
        nameField.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_nameField")); // NOI18N

        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(warningLabel, org.openide.util.NbBundle.getMessage(RenamePanel.class, "LBL_NonAccurateRefactoringWarning")); // NOI18N
        jPanel1.add(warningLabel, java.awt.BorderLayout.NORTH);
        warningLabel.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(RenamePanel.class, "LBL_NonAccurateRefactoringWarning")); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        add(jPanel1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(textCheckBox, org.openide.util.NbBundle.getBundle(RenamePanel.class).getString("LBL_RenameComments")); // NOI18N
        textCheckBox.setActionCommand(org.openide.util.NbBundle.getMessage(RenamePanel.class, "LBL_RenameComments")); // NOI18N
        textCheckBox.setEnabled(false);
        textCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                textCheckBoxItemStateChanged(evt);
            }
        });
        textCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(textCheckBox, gridBagConstraints);
        textCheckBox.getAccessibleContext().setAccessibleDescription(textCheckBox.getText());

        org.openide.awt.Mnemonics.setLocalizedText(updateReferencesCheckBox, org.openide.util.NbBundle.getBundle(RenamePanel.class).getString("LBL_RenameWithoutRefactoring")); // NOI18N
        updateReferencesCheckBox.setEnabled(false);
        updateReferencesCheckBox.setMargin(new java.awt.Insets(2, 2, 0, 2));
        updateReferencesCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateReferencesCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(updateReferencesCheckBox, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void updateReferencesCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateReferencesCheckBoxActionPerformed
        textCheckBox.setEnabled(!updateReferencesCheckBox.isSelected());
    }//GEN-LAST:event_updateReferencesCheckBoxActionPerformed

    private void textCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_textCheckBoxItemStateChanged
        // used for change default value for searchInComments check-box.                                                  
        // The value is persisted and then used as default in next IDE run.
//        Boolean b = evt.getStateChange() == ItemEvent.SELECTED ? Boolean.TRUE : Boolean.FALSE;
//        RefactoringModule.setOption("searchInComments.rename", b);
    }//GEN-LAST:event_textCheckBoxItemStateChanged

    private void textCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCheckBoxActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_textCheckBoxActionPerformed
                                                             
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label;
    private javax.swing.JTextField nameField;
    private javax.swing.JCheckBox textCheckBox;
    private javax.swing.JCheckBox updateReferencesCheckBox;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables

    public String getNameValue() {
        return nameField.getText();
    }
    
    public boolean searchJavadoc() {
        return textCheckBox.isSelected();
    }
    
    public boolean isUpdateReferences() {
        if (updateReferencesCheckBox.isVisible() && updateReferencesCheckBox.isSelected())
            return false;
        return true;
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
