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

package interp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class Cmd {

    private final List<String> names = new ArrayList<String>();

    private Interp interp;

    protected Cmd(String name, String... more) {
        names.add(name);
        names.addAll(Arrays.asList(more));
    }

    public abstract void run(String[] args);

    public abstract void help();

    void setInterp(Interp interp) {
        this.interp = interp;
    }

    final String name() {
        return names.get(0);
    }

    final Collection<String> names() {
        return names;
    }

    protected final Interp interp() {
        return interp;
    }

    /* OLD
    protected final void print(String msg) {
        interp.print(msg);
    }
    protected final void println(String msg) {
        interp.println(msg);
    }
    protected final void printf(String fmt, Object ... args) {
        interp.printf(fmt, args);
    }

    protected final void error(String fmt, Object... args) {
        interp.error(fmt, args);
    }
    */
}
