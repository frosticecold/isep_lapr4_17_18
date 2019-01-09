/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161838.workbooks.application;

import com.google.gwt.thirdparty.guava.common.collect.Iterables;
import eapli.framework.application.Controller;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

/**
 *
 * @author Joao Rocha<1161838>
 */
public class ListFilteredWorkbooksController implements Controller {

    /**
     * Controller's constructor
     */
    public ListFilteredWorkbooksController() {
    }

    /**
     * Method that gets a list of workbook filtered by a string It sees if the
     * current workbookdescription has the word filter on the name or
     * description if it has the workbookdescription object is added to the
     * ArrayList
     *
     * @param wds
     * @param filter
     * @return
     */
    public ArrayList<WorkbookDescriptionDTO> findFilteredWorkbookDescriptions(ArrayList<WorkbookDescriptionDTO> wds, String filter) {
        ArrayList<WorkbookDescriptionDTO> filteredWorkbooks = new ArrayList<>();

        Pattern pattern = Pattern.compile(filter);
        Matcher matcher;
        
        if (filter.isEmpty()) {
            return wds;
        }

        for (WorkbookDescriptionDTO workbookDesc : wds) {
            boolean flag = true;

            matcher = pattern.matcher(workbookDesc.getName());
            if (!matcher.matches()) {
                matcher = pattern.matcher(workbookDesc.getDescription());
                if (!matcher.matches()) {
                    flag = false;
                }
            }

            if (flag) {
                filteredWorkbooks.add(workbookDesc);
            }
        }

        return filteredWorkbooks;
    }
}
