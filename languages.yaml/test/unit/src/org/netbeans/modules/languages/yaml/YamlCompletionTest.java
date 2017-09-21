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
package org.netbeans.modules.languages.yaml;

import org.netbeans.modules.csl.api.CodeCompletionHandler.QueryType;

/**
 *
 * @author Tor Norbye
 */
public class YamlCompletionTest extends YamlTestBase {

    public YamlCompletionTest(String testName) {
        super(testName);
    }

    public void testPrefix1() throws Exception {
        checkPrefix("testfiles/test3.yaml");
    }

    public void testAutoQuery1() throws Exception {
        assertAutoQuery(QueryType.NONE, "foo^", "o");
    }

    public void testCompletion1() throws Exception {
        checkCompletion("testfiles/test3.yaml", "Height:^ 5'11'' (180 cm)", false);
    }

    public void testCompletion2() throws Exception {
        checkCompletion("testfiles/test3.yaml", "Heig^ht: 5'11'' (180 cm)", false);
    }
}
