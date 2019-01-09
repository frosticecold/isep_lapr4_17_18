package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1150344.core;

import java.io.Serializable;
import java.util.HashMap;

import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Widget;

public class Form implements Serializable {
	
	String formTitle;
	
	HashMap<HTMLTable.Cell, Widget> formInfo;
	
	//Workbook wb = CurrentWorkbook.getWorkbook();
	
	public Form() {
		formInfo = new HashMap<>();
		formTitle = "";
	}
	
	public Form(String title) {
		formInfo = new HashMap<>();
		formTitle = "";
	}
	
	public boolean addWidget(HTMLTable.Cell cell, Widget widget) {
		if(cell != null) {
			formInfo.put(cell, widget);
			return true;
		}
		return false;
	}
	
	public void removeRow(int rowIndex) {
		for(HTMLTable.Cell cell : formInfo.keySet()) {
			if(cell.getRowIndex() == rowIndex) {
				formInfo.remove(cell, formInfo.get(cell));
			}
		}
	}
	
	public boolean isNewForm() {
		return formInfo.isEmpty();
	}
	
	public Form getForm() {
		return this;
	}
}