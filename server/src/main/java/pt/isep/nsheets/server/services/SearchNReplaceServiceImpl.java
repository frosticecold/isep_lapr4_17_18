package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.services.SearchNReplaceService;

public class SearchNReplaceServiceImpl extends RemoteServiceServlet implements SearchNReplaceService {


    @Override
    public String manel(Workbook ola) {
        return "o";
    }
}
