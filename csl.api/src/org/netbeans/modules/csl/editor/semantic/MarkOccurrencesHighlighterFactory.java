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
package org.netbeans.modules.csl.editor.semantic;

import java.util.Collection;
import java.util.Collections;
import org.netbeans.modules.csl.api.OccurrencesFinder;
import org.netbeans.modules.csl.core.AbstractTaskFactory;
import org.netbeans.modules.csl.core.Language;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.SchedulerTask;

/**
 * This file is originally from Retouche, the Java Support 
 * infrastructure in NetBeans. I have modified the file as little
 * as possible to make merging Retouche fixes back as simple as
 * possible. 
 *
 *
 * @author Jan Lahoda
 */
public class MarkOccurrencesHighlighterFactory extends AbstractTaskFactory {

    /** Creates a new instance of SemanticHighlighterFactory */
    public MarkOccurrencesHighlighterFactory() {
        super(false);
    }

    @Override
    public Collection<? extends SchedulerTask> createTasks(Language l, Snapshot snapshot) {
        OccurrencesFinder finder = l.getOccurrencesFinder();
        if (finder != null) {
            return Collections.singleton(new MarkOccurrencesHighlighter(l, snapshot));
        } else {
            return null;
        }
    }

}
