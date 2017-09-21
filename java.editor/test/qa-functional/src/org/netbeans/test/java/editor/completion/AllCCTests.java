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

package org.netbeans.test.java.editor.completion;

import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.spi.editor.completion.CompletionProvider;

/**
 *
 * @author jp159440
 */
public class AllCCTests extends CompletionTestPerformer{
   
    
   
   
    /** Creates a new instance of AllCCTests */
    public AllCCTests(String name) {
        super(name);
    
    }
  
    public void testAllSymbols() throws Exception {
        new CompletionTestCase(this).test(outputWriter, logWriter, "heap", false, getDataDir(),"cp-prj-1", "org/netbeans/test/editor/allcompletion/AllSymbols.java", 28,CompletionProvider.COMPLETION_ALL_QUERY_TYPE);        
    }
    
    public void testFilteredSymbols() throws Exception {
        new CompletionTestCase(this).test(outputWriter, logWriter, "AbstractB", false, getDataDir(),"cp-prj-1", "org/netbeans/test/editor/allcompletion/AllSymbols.java", 28,CompletionProvider.COMPLETION_ALL_QUERY_TYPE);        
    }
    
    public static Test suite() {
        return NbModuleSuite.create(
                NbModuleSuite.createConfiguration(AllCCTests.class).enableModules(".*").clusters(".*"));
    }
    
}
