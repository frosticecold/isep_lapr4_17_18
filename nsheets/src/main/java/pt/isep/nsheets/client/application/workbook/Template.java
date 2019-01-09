package pt.isep.nsheets.client.application.workbook;

import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * Template.java
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 31/05/2018
 */
interface Template extends SafeHtmlTemplates {

    @Template("<div style=\"color:{0}\">{1}</div>")
    SafeHtml div(String url, String text);
}