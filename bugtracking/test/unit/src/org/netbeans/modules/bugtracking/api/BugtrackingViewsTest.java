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

package org.netbeans.modules.bugtracking.api;

import java.util.logging.Level;
import org.netbeans.junit.NbTestCase;
import org.netbeans.junit.NbTestSuite;
import org.openide.util.test.MockLookup;

/**
 *
 * @author tomas
 */
public class BugtrackingViewsTest extends NbTestCase {

    public static junit.framework.Test suite() {
        NbTestSuite suite = new NbTestSuite();
        suite.addTestSuite(UtilTestCase.class);
        suite.addTest(new IssueTest("_testOpen"));
//        suite.addTest(new QueryTest("_testOpen"));
        return suite;
    }

    
    public BugtrackingViewsTest(String arg0) {
        super(arg0);
    }

    @Override
    protected Level logLevel() {
        return Level.ALL;
    }   
    
    @Override
    protected void setUp() throws Exception {    
        MockLookup.setLayersAndInstances();
        APITestConnector.init();
    }

    @Override
    protected void tearDown() throws Exception { }
    
}
