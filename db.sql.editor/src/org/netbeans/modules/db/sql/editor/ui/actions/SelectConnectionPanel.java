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

package org.netbeans.modules.db.sql.editor.ui.actions;

import java.awt.Dialog;
import org.netbeans.api.db.explorer.ConnectionManager;
import org.netbeans.api.db.explorer.DatabaseConnection;
import org.netbeans.api.db.explorer.support.DatabaseExplorerUIs;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.NbBundle;

/**
 *
 * @author Andrei Badea
 */
public class SelectConnectionPanel extends javax.swing.JPanel {

    private final boolean passwordRequired;

    private DialogDescriptor descriptor;
    private DatabaseConnection dbconn;

    public static DatabaseConnection selectConnection(boolean passwordRequired) {
        DatabaseConnection resConn = null;
        SelectConnectionPanel panel = new SelectConnectionPanel(passwordRequired);
        DialogDescriptor desc = new DialogDescriptor(panel, NbBundle.getMessage(SelectConnectionPanel.class, "MSG_SelectConnection"));
        desc.createNotificationLineSupport();
        panel.initialize(desc);
        Dialog dialog = DialogDisplayer.getDefault().createDialog(desc);
        dialog.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(SelectConnectionPanel.class, "ACSD_SelectConnection"));
        dialog.setVisible(true);
        dialog.dispose();

        if (desc.getValue() == DialogDescriptor.OK_OPTION) {
            return panel.dbconn;
        }

        // If the user cancels, keep the selection they started with, rather than setting it to null
        return resConn;
    }

    private SelectConnectionPanel(boolean passwordRequired) {
        this.dbconn = null;
        this.passwordRequired = passwordRequired;
        initComponents();
    }

    private void initialize(DialogDescriptor descriptor) {
        this.descriptor = descriptor;
        DatabaseExplorerUIs.connect(dbconnComboBox, ConnectionManager.getDefault());
        dbconnComboBox.setSelectedIndex(0);
        descriptor.getNotificationLineSupport().setWarningMessage(NbBundle.getMessage(SelectConnectionPanel.class, "ERR_SelectConnection"));
    }

    private void databaseConnectionChanged() {
        dbconn = null;
        Object selected = dbconnComboBox.getSelectedItem();
        if (!(selected instanceof DatabaseConnection)) {
            return;
        }
        dbconn = (DatabaseConnection) selected;
        
//        DatabaseURL url = DatabaseURL.detect(dbconn.getDatabaseURL());
        String errorMessage = null;
//        if (mySQLOnly && (url == null || url.getServer() != Server.MYSQL)) {
//            errorMessage = NbBundle.getMessage(SelectConnectionPanel.class, "ERR_UnknownServer");
//            dbconn = null;
//        }
//        if (dbconn != null) {
//            if (passwordRequired && dbconn.getPassword() == null) {
//                errorMessage = NbBundle.getMessage(SelectConnectionPanel.class, "ERR_NoPassword");
//            }
//        }
        setErrorMessage(errorMessage);
    }

    private void setErrorMessage(String message) {
        if (message == null) {
            descriptor.getNotificationLineSupport().clearMessages();
        } else {
            descriptor.getNotificationLineSupport().setErrorMessage(message);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dbconnLabel = new javax.swing.JLabel();
        dbconnComboBox = new javax.swing.JComboBox();

        org.openide.awt.Mnemonics.setLocalizedText(dbconnLabel, org.openide.util.NbBundle.getMessage(SelectConnectionPanel.class, "SelectConnectionPanel.dbconnLabel.text")); // NOI18N

        dbconnComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbconnComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dbconnLabel)
                    .addComponent(dbconnComboBox, 0, 518, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dbconnLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbconnComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dbconnComboBox.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(SelectConnectionPanel.class, "ChooseConnectionPanel.dbconnComboBox.AccessibleContext.accessibleName")); // NOI18N
        dbconnComboBox.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(SelectConnectionPanel.class, "ChooseConnectionPanel.dbconnComboBox.AccessibleContext.accessibleDescription")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

private void dbconnComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbconnComboBoxActionPerformed
        databaseConnectionChanged();
}//GEN-LAST:event_dbconnComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox dbconnComboBox;
    private javax.swing.JLabel dbconnLabel;
    // End of variables declaration//GEN-END:variables

}
