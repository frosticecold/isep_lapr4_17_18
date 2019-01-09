package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public interface GlobalSearchAndReplaceServiceAsync {

    void showOutputOfGlobalSearch(String username, String regularExpression, AsyncCallback<List<String>> callback);

    void showOutputOfGlobalSearchAndReplace(String username, String regularExpression, String replace, AsyncCallback<List<String>> callback);

    void showOutputOfWorkbookSearch(String username, String regularExpression, String workbookExpression, AsyncCallback<List<String>> callback);

    void showOutputOfWorkbookSearchAndReplace(String username, String regularExpression, String workbookExpression, String replace, AsyncCallback<List<String>> callback);

}
