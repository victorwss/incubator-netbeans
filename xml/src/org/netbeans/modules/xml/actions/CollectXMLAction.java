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
package org.netbeans.modules.xml.actions;

import org.netbeans.modules.xml.util.Util;
import org.openide.util.HelpCtx;

/**
 *
 * @author Libor Kramolis
 * @version 0.1
 */
public class CollectXMLAction extends CollectSystemAction {
    private static final String FOLDER_PATH_XML_ACTIONS = "Loaders/text/xml/Actions"; //NOI18N

    /** Serial Version UID */
    private static final long serialVersionUID = 8562401343966139988L;

    /**
     * Getter for class
     */
    @Override
    protected Class getActionLookClass () {
        return XMLAction.class;
    }

    @Override
    protected void addRegisteredAction() {
        super.addRegisteredAction(FOLDER_PATH_XML_ACTIONS);
    }

    /* Getter for name
    */
    @Override
    public String getName () {
        return Util.THIS.getString (CollectXMLAction.class, "NAME_CollectXMLAction");
    }

    /* Getter for help.
    */
    @Override
    public HelpCtx getHelpCtx () {
        return new HelpCtx (CollectXMLAction.class);
    }

    /**
     * Interface XMLAction
     */
    public static interface XMLAction {}
}