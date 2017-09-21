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
package org.netbeans.lib.nbjavac.services;

import com.sun.tools.javac.comp.Enter;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.util.Context;
import org.netbeans.lib.nbjavac.services.NBTreeMaker.IndexedClassDecl;

/**
 *
 * @author lahvac
 */
public class NBEnter extends Enter {

    public static void preRegister(Context context) {
        context.put(enterKey, new Context.Factory<Enter>() {
            public Enter make(Context c) {
                return new NBEnter(c);
            }
        });
    }

    private final CancelService cancelService;

    public NBEnter(Context context) {
        super(context);
        cancelService = CancelService.instance(context);
    }

    @Override
    public void visitClassDef(JCClassDecl tree) {
        cancelService.abortIfCanceled();
        super.visitClassDef(tree);
    }

    @Override
    protected int getIndex(JCClassDecl clazz) {
        return clazz instanceof IndexedClassDecl ? ((IndexedClassDecl) clazz).index : -1;
    }
}
