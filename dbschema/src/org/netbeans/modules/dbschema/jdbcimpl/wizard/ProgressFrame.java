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

package org.netbeans.modules.dbschema.jdbcimpl.wizard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;

import org.openide.util.NbBundle;

public class ProgressFrame extends javax.swing.JFrame {

    ResourceBundle bundle = NbBundle.getBundle("org.netbeans.modules.dbschema.jdbcimpl.resources.Bundle"); //NOI18N
    public PropertyChangeSupport propertySupport;
    private ProgressHandle progressHandle;
    private JComponent progressComponent;
    private int workunits;
    private boolean finished = false;
    
    /** Creates new form ProgressFrame */
    public ProgressFrame() {
        propertySupport = new PropertyChangeSupport(this);
        
        initComponents ();
        this.getAccessibleContext().setAccessibleDescription(bundle.getString("ACS_ProgressFrameTabA11yDesc"));  // NOI18N
        okButton.getAccessibleContext().setAccessibleDescription(bundle.getString("ACS_CancelButtonA11yDesc"));  // NOI18N
        
        progressHandle = ProgressHandleFactory.createHandle(null);
        progressComponent = ProgressHandleFactory.createProgressComponent(progressHandle);
        progressPanel.add(progressComponent);
        progressHandle.start();

        javax.swing.ImageIcon ideIcon = new javax.swing.ImageIcon("/org/netbeans/core/resources/frames/ide.gif"); //NOI18N
        setIconImage(ideIcon.getImage());

        java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(380, 150);
        setLocation(dim.width/2 - 190, dim.height/2 - 80);
    }
    
    public void dispose() {
        if (!finished) {
            progressHandle.finish();
            finished = true;
        }
        super.dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        progressPanel = new javax.swing.JPanel();
        msgLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setTitle(bundle.getString("Title"));
        setResizable(false);
        progressPanel.setLayout(new java.awt.BorderLayout());

        progressPanel.setMinimumSize(new java.awt.Dimension(20, 20));
        progressPanel.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 11, 11);
        getContentPane().add(progressPanel, gridBagConstraints);

        msgLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        msgLabel.setText(bundle.getString("PreparingToCapture"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(6, 11, 6, 11);
        getContentPane().add(msgLabel, gridBagConstraints);

        okButton.setText(bundle.getString("Close"));
        okButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 11, 11);
        getContentPane().add(okButton, gridBagConstraints);

    }
    // </editor-fold>//GEN-END:initComponents

  private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
    setVisible(false);
    propertySupport.firePropertyChange("cancel", null, Boolean.TRUE); //NOI18N
    
    dispose();    
  }//GEN-LAST:event_okButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel msgLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel progressPanel;
    // End of variables declaration//GEN-END:variables

    public void setMaximum(final int max) {
        progressHandle.switchToDeterminate(max);
        workunits = max;
    }
    
    public void setValue(final int value) {
        String message;
        
        progressHandle.progress(value);
        
        if (value >= workunits) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    String message = MessageFormat.format(bundle.getString("Complete"), new String[] {Integer.toString(value)}); //NOI18N
                    msgLabel.setText(message);
                }
            });
        }
    }
    
    public void setMessage(String msg) {
        msgLabel.setText(msg);
    }    
    
    public void finishProgress() {
        // running in the event thread ensures synchronized access to the finished field
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                progressHandle.finish();
                finished = true;
            }
        });
    }
    
    //========== property change support needed for progressbar ==========
    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertySupport.addPropertyChangeListener (l);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertySupport.removePropertyChangeListener (l);
    }
}
