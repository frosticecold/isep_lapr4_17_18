/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.List;
import java.util.Properties;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1040862.GlobalSearch.application.GlobalSearchAndReplaceController;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.shared.services.GlobalSearchAndReplaceService;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public class GlobalSearchAndReplaceServiceImpl extends RemoteServiceServlet implements GlobalSearchAndReplaceService {

    private PersistenceSettings getPersistenceSettings() {

        Properties props = new Properties();
        props.put("persistence.repositoryFactory",
                "pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa.JpaRepositoryFactory");
        props.put("persistence.persistenceUnit", "lapr4.NSheetsPU");

        return new PersistenceSettings(props);
    }

    @Override
    public List<String> showOutputOfGlobalSearch(String username, String regularExpression) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        GlobalSearchAndReplaceController controller = new GlobalSearchAndReplaceController();
        List<String> list = controller.searchGlobal(username, regularExpression, null, null);

        return list;
    }

    @Override
    public List<String> showOutputOfGlobalSearchAndReplace(String username, String regularExpression, String replace) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        GlobalSearchAndReplaceController controller = new GlobalSearchAndReplaceController();
        List<String> list = controller.searchGlobal(username, regularExpression, replace, null);

        return list;
    }

    @Override
    public List<String> showOutputOfWorkbookSearch(String username, String regularExpression, String workbookExpression) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        GlobalSearchAndReplaceController controller = new GlobalSearchAndReplaceController();
        List<String> list = controller.searchGlobal(username, regularExpression, null, workbookExpression);

        return list;
    }

    @Override
    public List<String> showOutputOfWorkbookSearchAndReplace(String username, String regularExpression, String workbookExpression, String replace) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        GlobalSearchAndReplaceController controller = new GlobalSearchAndReplaceController();
        List<String> list = controller.searchGlobal(username, regularExpression, replace, workbookExpression);

        return list;
    }

}
