package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import pt.isep.nsheets.shared.core.Workbook;

@RemoteServiceRelativePath("searchNReplaceService")
public interface SearchNReplaceService extends RemoteService {

    public String manel(Workbook ola);


}
