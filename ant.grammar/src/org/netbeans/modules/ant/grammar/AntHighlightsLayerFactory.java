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

package org.netbeans.modules.ant.grammar;

import java.awt.Color;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.spi.editor.highlighting.HighlightsLayer;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.ZOrder;

/**
 * Highlights Ant property values.
 */
@MimeRegistration(service=HighlightsLayerFactory.class, mimeType="text/x-ant+xml")
public class AntHighlightsLayerFactory implements HighlightsLayerFactory {

    public @Override HighlightsLayer[] createLayers(final Context context) {
        AttributeSet _attrs = MimeLookup.getLookup("text/x-jsp").lookup(FontColorSettings.class).getTokenFontColors("expression-language"); // NOI18N
        final AttributeSet attrs;
        if (_attrs == null) {
            // Fallback from web.core.syntax/src/org/netbeans/modules/web/core/syntax/resources/fontsColors.xml:
            SimpleAttributeSet _sattrs = new SimpleAttributeSet();
            _sattrs.addAttribute(StyleConstants.Background, new Color(0xe3f2e1));
            attrs = _sattrs;
        } else {
            attrs = _attrs;
        }
        return new HighlightsLayer[] {HighlightsLayer.create("ant", ZOrder.SYNTAX_RACK.forPosition(10), true,
                new AntHighlightsContainer((AbstractDocument) context.getDocument(), attrs))}; // NOI18N
    }

}
