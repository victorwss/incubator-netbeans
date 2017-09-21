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

package org.netbeans.modules.java.ui;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import org.netbeans.modules.options.editor.spi.PreferencesCustomizer;
import org.openide.util.NbBundle;
import static org.netbeans.modules.java.ui.FmtOptions.*;

/**
 *
 * @author  phrebejk
 */
public class FmtSpaces extends JPanel implements TreeCellRenderer, MouseListener, KeyListener {

    private SpacesCategorySupport scs;
    private DefaultTreeModel model;
  
    private DefaultTreeCellRenderer dr = new DefaultTreeCellRenderer();    
    private JCheckBox renderer = new JCheckBox();
    
    /** Creates new form FmtSpaces */
    private FmtSpaces() {
        initComponents();
        model = createModel();
        cfgTree.setModel(model);
        cfgTree.setRootVisible(false);
        cfgTree.setShowsRootHandles(true);
        cfgTree.setCellRenderer(this);
        cfgTree.setEditable(false);
        cfgTree.addMouseListener(this);
        cfgTree.addKeyListener(this);
        
        dr.setIcon(null);
        dr.setOpenIcon(null);
        dr.setClosedIcon(null);
        
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        for( int i = root.getChildCount(); i >= 0; i-- ) {
            cfgTree.expandRow(i);
        }
    }
    
    public static PreferencesCustomizer.Factory getController() {
        return new PreferencesCustomizer.Factory() {
            public PreferencesCustomizer create(Preferences preferences) {
                return new SpacesCategorySupport(preferences, new FmtSpaces());
            }
        };
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        cfgTree = new javax.swing.JTree();

        setName(org.openide.util.NbBundle.getMessage(FmtSpaces.class, "LBL_Spaces")); // NOI18N
        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        cfgTree.setRootVisible(false);
        jScrollPane1.setViewportView(cfgTree);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree cfgTree;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
    // TreeCellRenderer implemetation ------------------------------------------
    
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        
        renderer.setBackground( selected ? dr.getBackgroundSelectionColor() : dr.getBackgroundNonSelectionColor() );
        renderer.setForeground( selected ? dr.getTextSelectionColor() : dr.getTextNonSelectionColor() );
        renderer.setEnabled( true );

        Object data = ((DefaultMutableTreeNode)value).getUserObject();
        
        if ( data instanceof Item ) {
            Item item = ((Item)data);
            
            if ( ((DefaultMutableTreeNode)value).getAllowsChildren() ) {
                Component c = dr.getTreeCellRendererComponent(tree, value, leaf, expanded, leaf, row, hasFocus);
                return c;
            }
            else {
                renderer.setText( item.displayName );
                renderer.setSelected( item.value );
            }
        }        
        else {
            Component c = dr.getTreeCellRendererComponent(tree, value, leaf, expanded, leaf, row, hasFocus);             
            return c;
        }

        return renderer;
    }
    
    
    // MouseListener implementation --------------------------------------------
    
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        TreePath path = cfgTree.getPathForLocation(e.getPoint().x, e.getPoint().y);
        if ( path != null ) {
            Rectangle r = cfgTree.getPathBounds(path);
            if (r != null) {
                if ( r.contains(p)) {
                    toggle( path );
                }
            }
        }
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
    
    // KeyListener implementation ----------------------------------------------

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER ) {

            if ( e.getSource() instanceof JTree ) {
                JTree tree = (JTree) e.getSource();
                TreePath path = tree.getSelectionPath();

                if ( toggle( path )) {
                    e.consume();
                }
            }
        }
    }
    
    // Private methods ---------------------------------------------------------
    
    private DefaultTreeModel createModel() {
        
        Item[] categories = new Item[] {
            new Item("BeforeKeywords",                          // NOI18N
                new Item(spaceBeforeWhile),
                new Item(spaceBeforeElse),
                new Item(spaceBeforeCatch),
                new Item(spaceBeforeFinally) ),
    
            new Item("BeforeParentheses",                       // NOI18N
                new Item(spaceBeforeMethodDeclParen),
                new Item(spaceBeforeMethodCallParen),
                new Item(spaceBeforeIfParen),
                new Item(spaceBeforeForParen),
                new Item(spaceBeforeWhileParen),
                new Item(spaceBeforeTryParen),
                new Item(spaceBeforeCatchParen),
                new Item(spaceBeforeSwitchParen),
                new Item(spaceBeforeSynchronizedParen),
                new Item(spaceBeforeAnnotationParen) ),
    
            new Item("AroundOperators",                         // NOI18N
                new Item(spaceAroundUnaryOps),
                new Item(spaceAroundBinaryOps),
                new Item(spaceAroundTernaryOps),
                new Item(spaceAroundAssignOps),
                new Item(spaceAroundAnnotationValueAssignOps),
                new Item(spaceAroundLambdaArrow),
                new Item(spaceAroundMethodReferenceDoubleColon)),
            
            new Item("BeforeLeftBraces",                        // NOI18N
                new Item(spaceBeforeClassDeclLeftBrace),
                new Item(spaceBeforeMethodDeclLeftBrace),
                new Item(spaceBeforeIfLeftBrace),
                new Item(spaceBeforeElseLeftBrace),
                new Item(spaceBeforeWhileLeftBrace),
                new Item(spaceBeforeForLeftBrace),
                new Item(spaceBeforeDoLeftBrace),
                new Item(spaceBeforeSwitchLeftBrace),
                new Item(spaceBeforeTryLeftBrace),
                new Item(spaceBeforeCatchLeftBrace),
                new Item(spaceBeforeFinallyLeftBrace),
                new Item(spaceBeforeSynchronizedLeftBrace),
                new Item(spaceBeforeStaticInitLeftBrace),
                new Item(spaceBeforeArrayInitLeftBrace) ),

            new Item("WithinParentheses",                       // NOI18N
                new Item(spaceWithinParens),
                new Item(spaceWithinMethodDeclParens),
                new Item(spaceWithinLambdaParens),
                new Item(spaceWithinMethodCallParens),
                new Item(spaceWithinIfParens),
                new Item(spaceWithinForParens),
                new Item(spaceWithinWhileParens),
                new Item(spaceWithinSwitchParens),
                new Item(spaceWithinTryParens),
                new Item(spaceWithinCatchParens),
                new Item(spaceWithinSynchronizedParens),
                new Item(spaceWithinTypeCastParens),
                new Item(spaceWithinAnnotationParens),
                new Item(spaceWithinBraces),
                new Item(spaceWithinArrayInitBrackets),
                new Item(spaceWithinArrayIndexBrackets) ),
                                    
             new Item("Other",                                  // NOI18N
                new Item(spaceBeforeComma),
                new Item(spaceAfterComma),
                new Item(spaceBeforeSemi),
                new Item(spaceAfterSemi),
                new Item(spaceBeforeColon),
                new Item(spaceAfterColon),
                new Item(spaceAfterTypeCast) )
                
        };
         
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root", true); // NOI18N
        DefaultTreeModel dtm = new DefaultTreeModel( root );
        
        
        for( Item item : categories ) {
            DefaultMutableTreeNode cn = new DefaultMutableTreeNode( item, true );
            root.add(cn);
            for ( Item si : item.items ) {
                DefaultMutableTreeNode in = new DefaultMutableTreeNode( si, false );
                cn.add(in);
            }
        }
        
        return dtm;
    }
    
    private boolean toggle(TreePath treePath) {
        
        if( treePath == null ) {
            return false;
        }

        Object o = ((DefaultMutableTreeNode)treePath.getLastPathComponent()).getUserObject();

        DefaultTreeModel dtm = (DefaultTreeModel)cfgTree.getModel();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();

        if ( o instanceof Item ) {
            Item item = (Item)o;
            
            if ( node.getAllowsChildren() ) {
                return false;
            }
            
            item.value = !item.value;            
            dtm.nodeChanged(node);
            dtm.nodeChanged(node.getParent());
            scs.notifyChanged();
        }
        
        return false;
    }
    
    // Innerclasses ------------------------------------------------------------
    
    private static class Item {
        
        String id;        
        String displayName;        
        boolean value;        
        Item[] items;
        
        public Item(String id, Item... items) {
            this.id = id;
            this.items = items;
            this.displayName = NbBundle.getMessage(FmtSpaces.class, "LBL_" + id ); // NOI18N            
        }

        @Override
        public String toString() {
            return displayName;
        }
        
    }
    
    private static final class SpacesCategorySupport extends CategorySupport {

        public SpacesCategorySupport(Preferences preferences, FmtSpaces panel) {
            super(preferences, "spaces", panel, //NOI18N
                  NbBundle.getMessage( FmtSpaces.class ,"SAMPLE_Spaces"), // NOI18N
                  new String[] {FmtOptions.placeCatchOnNewLine, Boolean.FALSE.toString()},
                  new String[] {FmtOptions.placeElseOnNewLine, Boolean.FALSE.toString()},
                  new String[] {FmtOptions.placeWhileOnNewLine, Boolean.FALSE.toString()},
                  new String[] {FmtOptions.placeFinallyOnNewLine, Boolean.FALSE.toString()},
                  new String[] { FmtOptions.blankLinesBeforeClass, "0" });
            panel.scs = this;
        }

        @Override
        protected void addListeners() {
            // Should not do anything
        }

        @Override
        protected void loadFrom(Preferences preferences) {
            for (Item item : getAllItems()) {
                boolean df = FmtOptions.getDefaultAsBoolean(item.id);
                item.value = preferences.getBoolean(item.id, df);
            }
        }

//        @Override
//        public void applyChanges() {
//            storeTo(preferences);
//        }
//
//        @Override
//        public JComponent getComponent(Lookup masterLookup) {
//            panel = (FmtSpaces)super.getComponent(masterLookup);
//            return panel;
//        }

        @Override
        protected void storeTo(Preferences preferences) {
            for (Item item : getAllItems()) {
                boolean df = FmtOptions.getDefaultAsBoolean(item.id);
                if (df == item.value)
                    preferences.remove(item.id);
                else
                    preferences.putBoolean(item.id, item.value);
            }
        }
        
        private List<Item> getAllItems() {
            List<Item> result = new LinkedList<FmtSpaces.Item>();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) ((FmtSpaces) panel).model.getRoot();
            Enumeration children = root.depthFirstEnumeration();
            while( children.hasMoreElements() ) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) children.nextElement();
                Object o = node.getUserObject();
                if (o instanceof Item) {
                    Item item = (Item) o;
                    if ( item.items == null || item.items.length == 0 ) {
                        result.add( item );
                    }
                }
            }            
            return result;
        }
    }
}
