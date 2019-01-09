/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
@RemoteServiceRelativePath("globalsearchandreplaceservice")
public interface GlobalSearchAndReplaceService extends RemoteService {

    List<String> showOutputOfGlobalSearch(String username, String regularExpression);

    List<String> showOutputOfGlobalSearchAndReplace(String username, String regularExpression, String replace);

    List<String> showOutputOfWorkbookSearch(String username, String regularExpression, String workbookExpression);

    List<String> showOutputOfWorkbookSearchAndReplace(String username, String regularExpression, String workbookExpression, String replace);

}
