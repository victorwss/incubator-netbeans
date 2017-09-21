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

package org.netbeans.modules.refactoring.spi.impl;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import org.netbeans.modules.refactoring.api.Problem;
import org.netbeans.modules.refactoring.api.ProblemDetails;
import org.netbeans.modules.refactoring.spi.ui.RefactoringUI;
import org.openide.util.Cancellable;
import org.openide.util.NbBundle;

/**
 *
 * @author  Jan Becicka
 */
public class ProblemComponent extends javax.swing.JPanel {
    
    private static final Logger LOGGER = Logger.getLogger(ProblemComponent.class.getName());
    
    private Problem problem;
    private ProblemDetails details;
    private RefactoringUI ui;
    private static double buttonWidth;
    
    /**
     * Creates new form ProblemComponent 
     */
    public ProblemComponent(Problem problem, RefactoringUI ui, boolean single) {
        initComponents();
        this.ui = ui;
        icon.setIcon(problem.isFatal()?ErrorPanel.getFatalErrorIcon():ErrorPanel.getNonfatalErrorIcon());
        problemDescription.setText(problem.getMessage());
        this.problem = problem;
        this.details = problem.getDetails();
        //setLightBackground();
        if (!single && details != null) {
            org.openide.awt.Mnemonics.setLocalizedText(showDetails, details.getDetailsHint());
            showDetails.setPreferredSize(new Dimension((int) buttonWidth, (int) showDetails.getMinimumSize().getHeight()));
        } else {
            showDetails.setVisible(false);
        }
        
        problemDescription.addHyperlinkListener(new HyperlinkListener() {

            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED && Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(e.getURL().toURI());
                    } catch (IOException | URISyntaxException ex) {
                        LOGGER.log(Level.INFO, "Desktop.browse failed: ", ex); // NOI18N
                    }
                }
            }
        });
    }
    
    static void initButtonSize(Problem problem) {
        buttonWidth = -1.0;
        while (problem != null) {
            ProblemDetails pdi = problem.getDetails();
            if (pdi != null) {
                buttonWidth = Math.max(new JButton(pdi.getDetailsHint()).getMinimumSize().getWidth(), buttonWidth);
            }
            problem = problem.getNext();
        }
        
    }
    
    public void setLightBackground() {
//        Color bgColor = SystemColor.control.brighter();
//        setBackground(bgColor);
        problemDescription.setBackground(getBackground());
        icon.setBackground(getBackground());
        //showDetails.setBackground(Color.WHITE);
    }
    
    public void setDarkBackground() {
        //Color bgColor =  new Color(240, 240, 240);
        Color bgColor = getDarker(getBackground());
        setBackground(bgColor);
        problemDescription.setBackground(bgColor);
        icon.setBackground(bgColor);
        //showDetails.setBackground(bgColor);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        icon = new javax.swing.JLabel();
        problemDescription = new javax.swing.JTextPane();
        showDetails = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        setLayout(new java.awt.GridBagLayout());

        icon.setBackground(javax.swing.UIManager.getDefaults().getColor("TextArea.background"));
        icon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 6, 1, 6));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(icon, gridBagConstraints);

        problemDescription.setEditable(false);
        problemDescription.setContentType("text/html"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(problemDescription, gridBagConstraints);

        showDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDetailsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(showDetails, gridBagConstraints);
        showDetails.getAccessibleContext().setAccessibleName(showDetails.getText());
        showDetails.getAccessibleContext().setAccessibleDescription(showDetails.getText());
    }// </editor-fold>//GEN-END:initComponents

    private void showDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDetailsActionPerformed
         Container c = this;
         while (!(c instanceof ParametersPanel)) {
             c = c.getParent();
         }
         final ParametersPanel parametersPanel = (ParametersPanel) c;
         Cancellable doCloseParent = new Cancellable() {
            @Override
             public boolean cancel() {
                 parametersPanel.cancel.doClick();
                 return true;
             }
         };
         ProblemDetails details = problem.getDetails();
         if (details != null) {
             details.showDetails(new CallbackAction(ui), doCloseParent);
         }
    }//GEN-LAST:event_showDetailsActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icon;
    private javax.swing.JTextPane problemDescription;
    private javax.swing.JButton showDetails;
    // End of variables declaration//GEN-END:variables
    
    static class CallbackAction extends AbstractAction {
        RefactoringUI ui;
        
        public CallbackAction(RefactoringUI ui) {
            super(MessageFormat.format(NbBundle.getMessage(ProblemComponent.class, "LBL_Rerun"), new Object[]{ui.getName()}));
            this.ui = ui;
        }
        
        @Override
        public void actionPerformed(ActionEvent event) {
            new RefactoringPanel(ui).setVisible(true);
        }
    }
    
    private static final float ALTERNATE_ROW_DARKER_FACTOR = 0.96f;
    
    private static Color getDarker(Color c) {
        if (c.equals(Color.WHITE)) {
            return new Color(244, 244, 244);
        }

        return getSafeColor((int) (c.getRed() * ALTERNATE_ROW_DARKER_FACTOR), (int) (c.getGreen() * ALTERNATE_ROW_DARKER_FACTOR),
                            (int) (c.getBlue() * ALTERNATE_ROW_DARKER_FACTOR));
    }
    
    private static Color getSafeColor(int red, int green, int blue) {
        red = Math.max(red, 0);
        red = Math.min(red, 255);
        green = Math.max(green, 0);
        green = Math.min(green, 255);
        blue = Math.max(blue, 0);
        blue = Math.min(blue, 255);

        return new Color(red, green, blue);
    }
}
