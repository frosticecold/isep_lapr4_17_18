/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package pt.isep.nsheets.client.place;

import org.junit.Test;

import pt.isep.nsheets.client.place.NameTokens;

import static org.junit.Assert.assertEquals;

/**
 * @author Randy May
 *         Date: 2016-04-11
 */
public class NameTokensTest {
    @Test
    public void testHomeToken() {
        assertEquals("home", NameTokens.home);
    }
    
    /**
     * @author Pedro Emanuel
     *      1131485@isep.ipp.pt
     *      31-05-2018
     */
    @Test
    public void testMacroToken() {
        
        assertEquals("macro", NameTokens.macro);
    }
    
    /**
     * @author Joana Oliveira
     *      1161261@isep.ipp.pt
     *      04-06-2015
     */
    @Test
    public void testChatToken() {
        
        assertEquals("chat", NameTokens.chat);
    }
}