package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Widget;

import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1150344.core.Form;

/**
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */

@RemoteServiceRelativePath("form")
public interface FormService extends RemoteService {
	public boolean addWidget(HTMLTable.Cell cell, Widget widget);

	public void removeRow(int rowIndex);

	public boolean isNewForm();

	public Form getForm();
}
