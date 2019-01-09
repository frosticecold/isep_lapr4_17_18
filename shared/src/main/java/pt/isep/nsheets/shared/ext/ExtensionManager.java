/*
 * Copyright (c) 2005 Einar Pehrson <einar@pehrson.nu>.
 *
 * This file is part of
 * CleanSheets - a spreadsheet application for the Java platform.
 *
 * CleanSheets is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * CleanSheets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CleanSheets; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package pt.isep.nsheets.shared.ext;

import pt.isep.nsheets.shared.lapr4.red.s1.ext.n1161018.YellowExtension.YellowCell;
import pt.isep.nsheets.shared.lapr4.red.s1.ext.n1161018.YellowExtension.YellowExtension;

import java.util.TreeMap;
import java.util.Map;
import java.util.Collection;
import pt.isep.nsheets.shared.lapr4.blue.s1.n1160701.StyleExtension.BorderExtension;
import pt.isep.nsheets.shared.lapr4.blue.s1.n1160701.StyleExtension.TextExtension;
import pt.isep.nsheets.shared.lapr4.blue.s1.n1160701.conditionalExtensions.ConditionalExtension;

/**
 * The class that manages extensions to the CleanSheets application.
 *
 * @author Einar Pehrson
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 */
public class ExtensionManager {

    /**
     * The singleton instance
     */
    private static ExtensionManager instance;

    /**
     * data sack
     */
    private RunTimeExtentionManager runTime = new RunTimeExtentionManager();

    /**
     * The extensions that have been loaded
     */
    private Map<String, Extension> extensionMap = new TreeMap<String, Extension>();

    /**
     * Creates the extension manager.
     */
    private ExtensionManager() {

        loadYellowExtension();
        loadConditionalExtension();
        loadTextColorExtension();

    }

    private void loadYellowExtension() {

        runTime.changeYellowExtensionColor(YellowExtension.NAME);
        extensionMap.put(YellowExtension.NAME, new YellowExtension());
    }

    private void loadConditionalExtension() {

        runTime.changeConditionalExtensionColor(ConditionalExtension.NAME);
        extensionMap.put(ConditionalExtension.NAME, new ConditionalExtension());
    }

    
    private void loadBorderExtension() {

        runTime.changeBorderExtension(BorderExtension.NAME);
        extensionMap.put(BorderExtension.NAME, new BorderExtension());
    }

   
    private void loadTextColorExtension() {

        runTime.changeTextColorExtension(TextExtension.NAME);
        extensionMap.put(TextExtension.NAME, new TextExtension());
    }



    /**
     * Returns the singleton instance.
     *
     * @return the singleton instance
     */
    public static ExtensionManager getInstance() {

        if (instance == null) {

            instance = new ExtensionManager();

        }
        return instance;
    }

    /**
     * Returns the extensions that have been loaded.
     *
     * @return the extensions that have been loaded
     */
    public Extension[] getExtensions() {
        Collection<Extension> extensions = extensionMap.values();
        return extensions.toArray(new Extension[extensions.size()]);
    }

    /**
     * Returns the extension with the given name.
     *
     * @param name name
     * @return the extension with the given name or null if none was found
     */
    public Extension getExtension(String name) {
        return extensionMap.get(name);
    }

    /**
     * Adds new extension to Extension Manager.
     *
     * @param newExtension: new extension to be added to Manager.
     * @return true: success. false: failure,
     */
    public boolean addExtension(Extension newExtension) {


        extensionMap.put(newExtension.getName(), newExtension);
        return true;
    }

    public RunTimeExtentionManager getRunTimeSettings() {

        return runTime;
    }

}
