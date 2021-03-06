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
package org.netbeans.test.codegen;

/**
 * Golden file.
 *
 * @author  Pavel Flaska
 */
public class RenameTestField {

    // field which will be renamed
    String strToBeRenamed;

    /** Creates a new instance of RenameTestPass */
    public RenameTestField() {
        strToBeRenamed = new String("NetBeers");
    }

    public void differentOccurencies() {
        // occurence in 'for cycle'
        for (int i = 0; i < strToBeRenamed.length(); i++) {
            // do nothing.
        }
        strToBeRenamed += " is not a trademark.";
        String nuovo = strToBeRenamed;
        StringBuffer buf = new StringBuffer(strToBeRenamed);
        strToBeRenamed = "Do not change!";
        getLength(strToBeRenamed);
    }
    
    public int getLength(String s) {
        return s.length();
    }
}
