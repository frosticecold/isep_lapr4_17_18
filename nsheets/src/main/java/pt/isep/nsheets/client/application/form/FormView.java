package pt.isep.nsheets.client.application.form;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
//import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1150344.core.Form;

/**
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */

class FormView extends ViewImpl implements FormPresenter.MyView {

	@UiField
	HTMLPanel window;

	@UiField
	MaterialTextBox labelTextBox, textAreaBox, buttonBox;

	@UiField(provided = true)
	FlexTable formTable;

	// Form f = new Form();

	HTMLTable.Cell activeCell = null;

	interface Binder extends UiBinder<Widget, FormView> {
	}

	@Inject
	FormView(Binder uiBinder) {
		initForm();
		initWidget(uiBinder.createAndBindUi(this));
	}

	private void initForm() {
		formTable = new FlexTable();

		formTable.setText(0, 0, "");
		formTable.setText(0, 1, "");
		formTable.getColumnFormatter().setWidth(0, "150px");
		formTable.getColumnFormatter().setWidth(1, "150px");
		formTable.insertRow(1);

		formTable.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				activeCell = formTable.getCellForEvent(event);
				// formTable.getCellFormatter().setStyleName(activeCell.getRowIndex(),
				// activeCell.getCellIndex(), "selected");
			}
		});
	}

	@UiHandler("addRowButton")
	void addRowClick(ClickEvent event) {
		addRow(formTable);
	}

	private void addRow(FlexTable form) {
		int numRows = form.getRowCount();
		form.setText(numRows, 0, "");
		form.setText(numRows, 1, "");
		form.insertRow(numRows);
	}

	@UiHandler("removeRowButton")
	void removeRowClick(ClickEvent event) {
		removeRow(formTable);
	}

	/**
	 * Remove a row from the flex table.
	 */
	private void removeRow(FlexTable form) {
		int numRows = form.getRowCount();
		if (numRows > 1) {
			form.removeRow(numRows - 1);
			// f.removeRow(numRows - 1);
		}
	}

	@UiHandler("addLabelButton")
	void addLabelClick(ClickEvent event) {
		addLabel(formTable);
	}

	private void addLabel(FlexTable form) {
		MaterialLabel label = new MaterialLabel(labelTextBox.getText());
		form.setWidget(activeCell.getRowIndex(), activeCell.getCellIndex(), label);
		labelTextBox.clear();
		// f.addWidget(activeCell, label);
	}

	@UiHandler("addTextAreaButton")
	void addTextAreaClick(ClickEvent event) {
		addTextArea(formTable);
	}

	private void addTextArea(FlexTable form) {
		FormTextBox textBox = new FormTextBox(textAreaBox.getText(), "prompt");
		form.setWidget(activeCell.getRowIndex(), activeCell.getCellIndex(), textBox);
		textAreaBox.clear();
		// f.addWidget(activeCell, textBox);
	}

	@UiHandler("addButtonButton")
	void addButtonClick(ClickEvent event) {
		addButton(formTable);
	}

	private void addButton(FlexTable form) {
		MaterialButton button = new MaterialButton(buttonBox.getText());
		button.setWaves(WavesType.DEFAULT);
		form.setWidget(activeCell.getRowIndex(), activeCell.getCellIndex(), button);
		// f.addWidget(activeCell, button);
	}

}
