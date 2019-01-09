/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.chartWizzard;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.BarChart;
import com.googlecode.gwt.charts.client.corechart.BarChartOptions;
import com.googlecode.gwt.charts.client.corechart.ColumnChartOptions;
import com.googlecode.gwt.charts.client.options.Animation;
import com.googlecode.gwt.charts.client.options.AnimationEasing;
import com.googlecode.gwt.charts.client.options.Bar;
import com.googlecode.gwt.charts.client.options.Gridlines;
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.Legend;
import com.googlecode.gwt.charts.client.options.LegendAlignment;
import com.googlecode.gwt.charts.client.options.LegendPosition;
import com.googlecode.gwt.charts.client.options.TextPosition;
import com.googlecode.gwt.charts.client.options.VAxis;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.client.base.validator.RegExValidator;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialAnchorButton;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialCardTitle;
import gwt.material.design.client.ui.MaterialRadioButton;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;
import java.util.Iterator;
import java.util.SortedSet;
import javax.inject.Inject;
import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.Cell;

/**
 *
 * @author pedro
 */
public class ChartWizzardView extends ViewImpl implements ChartWizzardPresenter.MyView {

    private static final int ENTER_TIME = 700;
    private static final int EXIT_TIME = 500;
    private boolean isLoop;
    private BarChart chart;
    private int[][] matrix;
    private final String[] countries = new String[]{"Austria", "Bulgaria", "Denmark", "Greece"};
    private static boolean edit = false;

    interface Binder extends UiBinder<Widget, ChartWizzardView> {
    }

    class CellValidator extends RegExValidator {

        public CellValidator() {
            super("[0-9]'-'[0-9]", "The cell pattern should be \"[0-9][0-9]*\", i.e: \"0-6\"");
        }
    }

    class RequiredValidator extends RegExValidator {

        public RequiredValidator() {
            super(".+", "Required");
        }
    }

    @UiField
    MaterialCardContent cardContent;

    @UiField
    MaterialCard chart_card, edit_card;

    @UiField
    MaterialAnchorButton chart_button, edit_button;

    @UiField
    MaterialButton save_btn, save_chart_btn;

    @UiField
    MaterialTextBox name_textbox, start_textbox, end_textbox;

    @UiField
    MaterialRadioButton row_r_btn, col_r_btn;

    @UiField
    MaterialCardTitle m;

    @UiHandler("chart_button")
    void click_chart(ClickEvent e) {
        chart_card.setVisible(false);
        edit_card.setVisible(true);

        enterChartCard();
    }

    @UiHandler("edit_button")
    void click_edit(ClickEvent e) {
        edit_card.setVisible(false);
        chart_card.setVisible(true);

        enterEditCard(false);
    }

    @UiHandler("start_textbox")
    void typingStart(KeyPressEvent e) {
        start_textbox.validate();
    }

    @UiHandler("end_textbox")
    void typingEnd(KeyPressEvent e) {
        end_textbox.validate();
    }

    @UiHandler("save_btn")
    void click_save(ClickEvent e) {
        if (!edit) {
            if (enableElements(false)) {
                m.setText(name_textbox.getText());
                initialize();
                save_btn.setText("Edit");
                save_btn.setIconType(IconType.CREATE);
                edit = true;
            }

        } else {
            if (enableElements(true)) {

                save_btn.setText("Save");
                save_btn.setIconType(IconType.SAVE);
                edit = false;
            }

        }
    }

    @Inject
    ChartWizzardView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        initialize();
    }

    private void initialize() {
        enterEditCard(true);
        chart_card.setVisible(false);
        edit_button.setVisible(false);
        addValidators();
        ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
        chartLoader.loadApi(new Runnable() {

            @Override
            public void run() {
                chart = new BarChart();
                cardContent.add(chart);
                setLoop();
            }
        });
    }

    private void addValidators() {
        name_textbox.addValidator(new RequiredValidator());

    }

    private boolean validateForm() {
        return !(!name_textbox.validate());
    }

    private void setLoop() {
        Window.alert("Chart has been saved.");
        Timer timer = new Timer() {

            @Override
            public void run() {
                if (isLoop) {
                    drawChart(null);
                    isLoop = false;
                } else {
                    SortedSet<Cell> sc = SpreadSheetSingleton.getInstance().getSheet().getCells(changeAddress(start_textbox.getText()), changeAddress(end_textbox.getText()));
                    int row = changeAddress(end_textbox.getText()).getRow();
                    int collumn = changeAddress(end_textbox.getText()).getColumn();
                    matrix = new int[row + 1][collumn + 1];
                    Cell a[] = new Cell[sc.size()];

                    Iterator<Cell> it = sc.iterator();
                    int k = 0;
                    while (it.hasNext()) {

                        Cell curCell = it.next();
                        a[k] = curCell;
                        k++;
                    }
                    int cont = 0;
                    for (int i = 0; i < row + 1; i++) {
                        for (int j = 0; j < collumn + 1; j++) {
                            matrix[i][j] = Integer.parseInt(a[cont].getContent());
                            cont++;
                        }
                    }

                    drawChart(matrix);
                    isLoop = true;
                }

            }
        };
        timer.scheduleRepeating(
                1000);
    }

    private Address changeAddress(String a1) {
        String[] address = a1.split("-");
        int coluna = Integer.parseInt(address[0]);
        int linha = Integer.parseInt(address[1]);
        return new Address(coluna, linha);

    }

    private void drawChart(int[][] matrix) {

        // Prepare the data
        DataTable dataTable = DataTable.create();
        dataTable.addColumn(ColumnType.STRING, "Year");
        for (int i = 0; i < countries.length; i++) {
            dataTable.addColumn(ColumnType.NUMBER, countries[i]);
        }

        dataTable.addRows(matrix.length);
        char letter = 'A';
        for (int i = 0; i < matrix.length; i++) {
            dataTable.setValue(i, 0, String.valueOf(letter));
            letter++;
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                dataTable.setValue(row, col + 1, matrix[row][col]);
            }
        }

        // Draw the chart
        chart.draw(dataTable, getOptions());
    }

    private BarChartOptions getOptions() {
        // Grid Lines
        Gridlines lines = Gridlines.create();
        lines.setColor("fff");

        // Text Positions X and Y Axis
        HAxis hAxis = HAxis.create();
        hAxis.setTextPosition(TextPosition.NONE);

        VAxis vAxis = VAxis.create();
        vAxis.setGridlines(lines);
        hAxis.setGridlines(lines);

        // Legend
        Legend legend = Legend.create();
        legend.setPosition(LegendPosition.NONE);
        legend.setAligment(LegendAlignment.START);

        // Set options
        BarChartOptions options = BarChartOptions.create();
        options.setHAxis(HAxis.create("Cups"));
        options.setVAxis(VAxis.create("Year"));
        options.setColors("#2196f3", "#42a5f5", "#64b5f6", "#90caf9");
        options.setVAxis(vAxis);
        options.setHAxis(hAxis);
        options.setLegend(legend);

        // Set Animation
        Animation animation = Animation.create();
        animation.setDuration(500);
        animation.setEasing(AnimationEasing.OUT);
        options.setAnimation(animation);

        // Set Bar
        Bar bar = Bar.create();
        bar.setGroupWidth("50%");

        return options;
    }

    private void enterEditCard(boolean firstTime) {
        animate(chart_card, Transition.SLIDEOUTRIGHT, chart_button, edit_button, false, firstTime, EXIT_TIME);
        animate(edit_card, Transition.SLIDEINLEFT, chart_button, edit_button, true, firstTime, ENTER_TIME);

    }

    private void enterChartCard() {
        animate(edit_card, Transition.SLIDEOUTLEFT, edit_button, chart_button, false, false, EXIT_TIME);
        animate(chart_card, Transition.SLIDEINRIGHT, edit_button, chart_button, true, false, ENTER_TIME);

    }

    private void animate(MaterialCard card, Transition transition, MaterialAnchorButton btnIn, MaterialAnchorButton btnOut, boolean setVisible, boolean firstTime, int time) {

        if (!firstTime) {

            MaterialAnimation animation = new MaterialAnimation();
            animation.setTransition(transition);
            animation.setDelay(0);
            animation.setDuration(time);
            animation.setInfinite(false);

            if (!setVisible) {
                animation.animate(card);
                animateButton(btnOut, false);

                Timer timer = new Timer() {

                    public void run() {
                        card.setVisible(false);
                    }
                };
                timer.schedule(time);
            } else {
                animation.animate(card);
                card.setVisible(true);
                animateButton(btnIn, true);
            }
        }

    }

    private void animateButton(MaterialAnchorButton btn, boolean setVisible) {

        MaterialAnimation animation = new MaterialAnimation();

        animation.setDuration(700);
        animation.setInfinite(false);

        if (setVisible) {
            btn.setVisible(false);
            animation.setTransition(Transition.ROTATEIN);
            Timer timer = new Timer() {

                public void run() {
                    btn.setVisible(true);
                    animation.animate(btn);
                }
            };
            timer.schedule(200);
        } else {
            animation.setTransition(Transition.ROTATEOUT);
            animation.animate(btn);
            Timer timer = new Timer() {

                public void run() {
                    btn.setVisible(false);

                }
            };
            timer.schedule(700);

        }

    }

    private boolean enableElements(boolean enable) {

        if (!enable) {
            if (!validateForm()) {
                return false;
            } else if (start_textbox.getValue().length() == 0) {
                MaterialToast.fireToast("Invalid Start");
                return false;
            } else {
                return true;
            }
        }
        name_textbox.setEnabled(enable);
        end_textbox.setEnabled(enable);
        start_textbox.setEnabled(enable);
        row_r_btn.setEnabled(enable);
        col_r_btn.setEnabled(enable);
        return true;
    }

}
