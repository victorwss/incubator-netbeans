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
package org.netbeans.modules.javafx2.editor.parser;

import org.netbeans.modules.javafx2.editor.completion.model.FxmlParserResult;
import org.netbeans.modules.parsing.spi.IndexingAwareParserResultTask;
import org.netbeans.modules.parsing.spi.TaskIndexingMode;

/**
 *
 * @author sdedic
 */
public abstract class FxParserTask extends IndexingAwareParserResultTask<FxmlParserResult> {
    public enum Phase {
        PARSED,
        RESOLVED
    };
    
    /**
     * The offset where the parsing will stop
     */
    private final int     stopParsingOffset;
    
    private final Phase   toPhase;
    
    protected FxParserTask() {
        this(Phase.RESOLVED, TaskIndexingMode.ALLOWED_DURING_SCAN, -1);
    }
    
    protected FxParserTask(Phase toPhase, TaskIndexingMode indexingMode, int stopAt) {
        super(indexingMode);
        this.toPhase = toPhase;
        this.stopParsingOffset = stopAt;
    }
    
    public Phase getToPhase() {
        return toPhase;
    }
}
