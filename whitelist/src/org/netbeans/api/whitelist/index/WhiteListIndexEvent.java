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
package org.netbeans.api.whitelist.index;

import java.net.URL;
import java.util.EventObject;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 * WhiteListIndexEvent is used to notify interested parties about
 * changes in persistent index of white list violations.
 * @author Tomas Zezula
 * @since 1.2
 */
public class WhiteListIndexEvent extends EventObject {

    private final URL root;

    WhiteListIndexEvent(@NonNull final Object source, @NonNull final URL root) {
        super(source);
        Parameters.notNull("root", root);   //NOI18N
        this.root = root;
    }

    /**
     * Returns a source root for which the index was changed.
     * @return the {@link URL} of the source root.
     */
    @NonNull
    public URL getRoot() {
        return root;
    }
}
