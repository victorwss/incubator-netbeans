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

package org.openide.filesystems;

import org.netbeans.junit.MockServices;
import org.netbeans.junit.NbTestCase;

/**
 *
 * @author Jaroslav Tulach <jaroslav.tulach@netbeans.org>
 */
public class RepositoryGetDefaultTest extends NbTestCase {

    public RepositoryGetDefaultTest(String name) {
        super(name);
    }
    
    
    public void testLookupPossibleWhenRepoCreated() {
        MockServices.setServices(NbRep.class);
        
        Repository r1 = Repository.getDefault();
        
        assertSame("The same repository is obtained", r1, FS.my);
    }
    
    public static final class NbRep extends Repository {
        public NbRep() {
            super(new FS());
        }
    }
    
    private static final class FS extends MultiFileSystem {
        static Repository my;

        @Override
        public void addNotify() {
            super.addNotify();
            
            my = Repository.getDefault();
        }
    }
}
