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
package org.netbeans.jellytools.modules.xml.catalog.nodes;

/*
 * CatalogNode.java
 *
 * Created on 11/13/03 4:01 PM
 */

import org.netbeans.jellytools.actions.*;
import org.netbeans.jellytools.nodes.Node;
import javax.swing.tree.TreePath;
import org.netbeans.jemmy.operators.JTreeOperator;

/** CatalogNode Class
 * @author ms113234 */
public class CatalogNode extends AbstractNode {
    
    /* ACTIONS */
    private static class RefreshAction extends ActionNoBlock {
        public RefreshAction() {
            super(null, "Refresh");
        }
    }
    
    private static class AddLocalAction extends ActionNoBlock {
        public AddLocalAction() {
            super(null, "Add Local DTD or XML Schema...");
        }
    }
    
    private static class RemoveCatalogAction extends Action {
        public RemoveCatalogAction() {
            super(null, "Remove");
        }
    }
    
    
    private static final Action refreshAction = new RefreshAction();
    private static final Action removeCatalogAction = new RemoveCatalogAction();
    private static final Action customizeAction = new CustomizeAction();
    private static final Action propertiesAction = new PropertiesAction();
    private static final Action addLocalAction = new AddLocalAction();

    
    /** creates new CatalogNode
     * @param tree JTreeOperator of tree
     * @param treePath String tree path */
    public CatalogNode(JTreeOperator tree, String treePath) {
        super(tree, treePath);
    }
    
    /** creates new CatalogNode
     * @param tree JTreeOperator of tree
     * @param treePath TreePath of node */
    public CatalogNode(JTreeOperator tree, TreePath treePath) {
        super(tree, treePath);
    }
    
    /** creates new CatalogNode
     * @param parent parent Node
     * @param treePath String tree path from parent Node */
    public CatalogNode(Node parent, String treePath) {
        super(parent, treePath);
    }
    
    /** tests popup menu items for presence */
    public void verifyPopup() {
        verifyPopup(new Action[]{
            refreshAction,
            removeCatalogAction,
            customizeAction,
            propertiesAction
        });
    }
    
    public void addLocal(){
        addLocalAction.perform(this);
    }
    
    /** performs RefreshAction with this node */
    public void refresh() {
        refreshAction.perform(this);
    }
    
    /**
     * performs RemoveCatalogAction with this node
     */
    public void removeCatalog() {
        removeCatalogAction.perform(this);
    }
    
    /** performs CustomizeAction with this node */
    public void customize() {
        customizeAction.perform(this);
    }
    
    /** performs PropertiesAction with this node */
    public void properties() {
        propertiesAction.perform(this);
    }
    
    // LIB /////////////////////////////////////////////////////////////////////
    
    /** returns catalog entry node with given name or <code>null</code> */
    public CatalogEntryNode getCatalogEntry(String displayName) {
        return (CatalogEntryNode) getChild(displayName, CatalogEntryNode.class);
    }

}

