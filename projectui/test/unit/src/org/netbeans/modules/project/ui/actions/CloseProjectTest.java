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

package org.netbeans.modules.project.ui.actions;

import javax.swing.Action;
import org.netbeans.modules.project.ui.actions.TestSupport.TestProject;
import org.openide.util.Lookup;

/**
 *
 * @author Jaroslav Tulach <jaroslav.tulach@netbeans.org>
 */
public class CloseProjectTest extends LookupSensitiveActionBase {
    public CloseProjectTest(String testName) {
        super(testName);
    }            

    protected Action create(Lookup context) {
        return new CloseProject(context);
    }

    @Override
    protected void enhanceProject(TestProject prj) {
    }

}
