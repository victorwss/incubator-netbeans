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
package org.netbeans.modules.refactoring.java.ui;

import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/** Action that invokes Pull Up refactoring.
 *
 * @author Jan Becicka
 */
@ActionID(id = "org.netbeans.modules.refactoring.java.api.ui.PullUpAction", category = "Refactoring")
@ActionRegistration(displayName = "#LBL_PullUp_Action", lazy = false)
@ActionReferences({
    @ActionReference(path = "Editors/text/x-java/RefactoringActions", name = "PullUpAction", position = 500),
    @ActionReference(path = "Shortcuts", name = "OCS-U")
})
public class PullUpAction extends JavaRefactoringGlobalAction {

    /**
     * Creates a new instance of PullUpAction
     */
    public PullUpAction() {
        super(NbBundle.getMessage(PullUpAction.class, "LBL_PullUp_Action"), null);
        putValue("noIconInMenu", Boolean.TRUE); // NOI18N
    }
    
    @Override
    public final void performAction(Lookup context) {
        JavaActionsImplementationFactory.doPullUp(context);
    }
    
    @Override
    public org.openide.util.HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }

    @Override
    protected boolean enable(Lookup context) {
        return JavaActionsImplementationFactory.canPullUp(context);
    }
}
