package pt.isep.nsheets.client.application.workbook;

import com.google.gwt.user.client.Timer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.addins.client.popupmenu.MaterialPopupMenu;
import gwt.material.design.addins.client.window.MaterialWindow;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.data.component.RowComponent;
import pt.isep.nsheets.shared.lapr4.green.s3.core.n1160911.CommentsExtension.CommentExtension;
import gwt.material.design.client.ui.*;
import gwt.material.design.client.ui.table.MaterialDataTable;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import javax.inject.Inject;
import pt.isep.nsheets.client.application.Lapr.Red.n1161018.Expression.ConditionalInfo;
import pt.isep.nsheets.client.application.Lapr.Red.n1161018.Expression.ConditionalManager;
import pt.isep.nsheets.client.application.lapr4.blue.s2.core.n1150344.filter.FilterController;
import pt.isep.nsheets.client.application.Lapr.Red.n1161018.Search.OptionService;
import pt.isep.nsheets.client.application.Lapr.Red.n1161018.Search.OptionType;
import pt.isep.nsheets.client.application.Lapr.Red.n1161018.ext.UIController;
import pt.isep.nsheets.client.application.chartWizzard.SpreadSheetSingleton;
import pt.isep.nsheets.client.application.lapr4.green.s3.core.n1161294.SetImageForm;
import pt.isep.nsheets.client.application.s1.n1160701.conditionalFormat.ConditionalFormatView;
import pt.isep.nsheets.shared.core.*;
import pt.isep.nsheets.shared.core.formula.BinaryOperator;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.Function;
import pt.isep.nsheets.shared.core.formula.compiler.ExpressionCompiler;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.core.formula.lang.*;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.macrolanguage.application.MacroLanguageController;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.macrolanguage.compiler.MacroCompilerManager;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang.MacroLanguage;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.macro.domain.Macro;
import pt.isep.nsheets.shared.services.SearchService;
import pt.isep.nsheets.shared.services.SearchServiceAsync;
import static gwt.material.design.jquery.client.api.JQuery.$;
import static jdk.nashorn.internal.runtime.JSType.toInteger;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.client.application.blue.s2.n1090657.WorkbookManager;
import pt.isep.nsheets.client.application.lapr4.blue.s3.ipc.n1151708.uploadform.CSVFileForm;
import pt.isep.nsheets.shared.core.formula.FunctionParameter;
import pt.isep.nsheets.shared.core.formula.Operator;
import pt.isep.nsheets.shared.core.formula.UnaryOperator;
import pt.isep.nsheets.shared.ext.Extension;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1161386.formula2.NaryOperator;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.WorkbookDTO;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.dto.lockDTO;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.services.SessionService;
import pt.isep.nsheets.shared.services.ExportSpreadSheetPartToXMLService;
import pt.isep.nsheets.shared.services.ExportSpreadSheetPartToXMLServiceAsync;
import pt.isep.nsheets.shared.services.ExportSpreadSheetToXMLService;
import pt.isep.nsheets.shared.services.ExportSpreadSheetToXMLServiceAsync;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.services.SessionServiceAsync;
import pt.isep.nsheets.shared.services.ExportToCSVFormatService;
import pt.isep.nsheets.shared.services.ExportToCSVFormatServiceAsync;
import pt.isep.nsheets.shared.services.ExportToPDFService;
import pt.isep.nsheets.shared.services.ExportToPDFServiceAsync;
import pt.isep.nsheets.shared.services.GlobalSearchAndReplaceService;
import pt.isep.nsheets.shared.services.GlobalSearchAndReplaceServiceAsync;
import pt.isep.nsheets.shared.services.ExportToXMLFormatService;
import pt.isep.nsheets.shared.services.ExportToXMLFormatServiceAsync;
import pt.isep.nsheets.shared.services.UploadCSVService;
import pt.isep.nsheets.shared.services.UploadCSVServiceAsync;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;
import pt.isep.nsheets.shared.services.WorkbooksService;
import pt.isep.nsheets.shared.services.WorkbooksServiceAsync;
import pt.isep.nsheets.shared.lapr4.green.s3.core.n1161294.ImageExtension;

// public class HomeView extends ViewImpl implements HomePresenter.MyView {
// public class WorkbookView extends NavigatedView implements WorkbookPresenter.MyView {
public class WorkbookView extends ViewWithUiHandlers<WorkbookUIHandlers> implements WorkbookPresenter.MyView {

    private UIController uiController = new UIController();

    private FilterController filterController = new FilterController();

    private String username = CurrentUser.username();

    public MaterialTextBox getFirstBox() {
        return firstBox;
    }

    public MaterialIcon getFirstButton() {
        return firstButton;
    }

    public MaterialIcon getSecondButton() {
        return secondtButton;
    }

    @UiField
    MaterialButton importCSVbutton;
    @UiField
    MaterialButton uploadFilebutton;
    @UiField
    MaterialPanel fileChooser;
    CSVFileForm form;
    @UiField
    MaterialWindow importWindow;
    @UiField
    MaterialLabel label_download;
    /*#########################################
    *ANDRÉ OLIVEIRA <1040862@isep.ipp.pt>
     *///#######################################
    @UiField
    MaterialIcon globalSearchAndReplace;

    @UiField
    MaterialWindow globalSearchAndReplaceWindow;

    @UiField
    MaterialTextBox globalRegExpressionTextBox1;

    @UiField
    MaterialTextBox globalRegExpressionTextBox2;

    @UiField
    MaterialTextBox globalRegExpressionTextBox3;

    @UiField
    MaterialButton globalStartSearchAndReplace;

//#################################
    @UiField
    MaterialButton activate;
    @UiField
    MaterialSwitch swState;

    @UiField
    MaterialButton swState2;
    @UiField
    MaterialWindow ac;
    @UiField
    MaterialSwitch swState4;

    @UiField
    MaterialListBox globalFirstOptions;

    //Telmo
    @UiField
    MaterialIcon menuAddIcon;

    @UiField
    MaterialIcon editButtonInfo;

    @UiField
    MaterialWindow windowEditWorkbook;

    @UiField
    MaterialTextBox nameBox;

    @UiField
    MaterialTextBox descBox;

    @UiField
    MaterialButton buttonEditWorkbook;

    @UiField
    MaterialIcon menuRemoveIcon;

    @UiField
    MaterialIcon selectSpreadIcon;

    @UiField
    MaterialWindow windowAddSpreadsheet;

    @UiField
    MaterialWindow windowRemoveSpreadsheet;

    @UiField
    MaterialWindow windowSelectSpreadsheet;

    @UiField
    MaterialButton buttonSlectActive;

    @UiField
    MaterialListValueBox<Spreadsheet> indexListBox;

    @UiField
    MaterialButton buttonRemoveWork;

    @UiField
    MaterialButton buttonCancelRemove;

    @UiField
    MaterialTextBox spreadLines;

    @UiField
    MaterialTextBox spreadColumns;

    @UiField
    MaterialButton buttonAddSpread, clearRange;

    @UiField
    HTMLPanel htmlPanel;

    @UiField
    MaterialTextBox firstBox;

    @UiField
    MaterialIcon firstButton;

    @UiField
    MaterialIcon secondtButton;

    @UiField
    MaterialIcon searchNReplace_btn;

    @UiField
    MaterialIcon sortButton;

    @UiField
    MaterialDataTable<SheetCell> customTable;

    @UiField
    MaterialPopupMenu popupMenu;

    @UiField
    MaterialWindow window;

    @UiField
    MaterialWindow windowRange;

    @UiField
    MaterialWindow searchNReplace_window;

    @UiField
    MaterialWindow replace_window;

    @UiField
    MaterialRadioButton radioValue;

    @UiField
    MaterialRadioButton radioValue1;

    @UiField
    MaterialRadioButton radioValue2;

    @UiField
    MaterialRadioButton radioValue3;

    @UiField
    MaterialWindow janela;

    @UiField
    MaterialButton ExportToPDF;

    @UiField
    MaterialButton startConditional;

    //bea
    @UiField
    MaterialLink conditionalFormat;

    @UiField
    MaterialWindow windowFormat;

    //bea
    @UiField
    MaterialComboBox<BinaryOperator> conditional;

    //bea
    @UiField
    MaterialComboBox<BinaryOperator> conditional2;

    //bea
    @UiField
    MaterialComboBox<BinaryOperator> conditional3;

    //bea
    @UiField
    MaterialTextBox numberConditional;

    //bea
    @UiField
    MaterialTextBox numberConditional2;

    //bea
    @UiField
    MaterialTextBox numberConditional3;

    //bea
    @UiField
    MaterialComboBox<Color> listColors;

    //bea
    @UiField
    MaterialComboBox<Color> listColors2;

    //bea
    @UiField
    MaterialComboBox<Color> listColors3;

    //bea
    @UiField
    MaterialComboBox<Color> listColors4;

    //bea
    @UiField
    MaterialComboBox<Style.BorderStyle> listBorder;

    //bea
    @UiField
    MaterialComboBox<Style.BorderStyle> listBorder2;

    //bea
    @UiField
    MaterialButton confirmConditional;

    //bea
    @UiField
    MaterialButton confirmConditional2;

    //bea
    @UiField
    MaterialButton confirmConditional3;

    //joana
    @UiField
    MaterialLink removeConditionalFormat;

    @UiField
    MaterialWindow windowPDF;

    @UiField
    MaterialTextBox fileName;

    @UiField
    MaterialButton startExportWorkbookPDF;

    @UiField
    MaterialButton startExportSpreadsheetPDF;

    @UiField
    MaterialButton startExportPartialSpreadsheetPDF;

    @UiField
    MaterialWindow windowSpreadsheetPDF;

    @UiField
    MaterialWindow windowPartialSpreadsheetPDF;

    @UiField
    MaterialButton ExportSpreadsheetPDF, ExportPartialSpreadsheetPDF;

    @UiField
    MaterialTextBox SpreadsheetNumber1, SpreadsheetNumber2, posicao1, posicao2;

    @UiField
    MaterialButton ExportToXML;

    @UiField
    MaterialWindow windowXML;

    @UiField
    MaterialTextBox fileName1;

    @UiField
    MaterialButton startExportWorkbookXML;

    @UiField
    MaterialButton startExportSpreadsheetXML;

    @UiField
    MaterialButton startExportPartOfSpreadsheetXML;

    @UiField
    MaterialWindow windowWorkbook1;

    @UiField
    MaterialTextBox changetagOne;

    @UiField
    MaterialTextBox changetagTwo;

    @UiField
    MaterialTextBox changetagThree;

    @UiField
    MaterialTextBox changetagFour;

    @UiField
    MaterialTextBox changetagFive;

    @UiField
    MaterialTextBox changetagSix;

    @UiField
    MaterialButton Exportx;

    @UiField
    MaterialWindow windowSpreadsheet1;

    @UiField
    MaterialTextBox changeTag1;

    @UiField
    MaterialTextBox changeTag2;

    @UiField
    MaterialTextBox changeTag3;

    @UiField
    MaterialTextBox changeTag4;

    @UiField
    MaterialTextBox changeTag5;

    @UiField
    MaterialTextBox changeTag6;

    @UiField
    MaterialTextBox changeSpread2;

    @UiField
    MaterialButton Exportm;

    @UiField
    MaterialWindow windowPart1;
@UiField
    MaterialTextBox change1tag;
    

    @UiField
    MaterialTextBox change2tag;

    @UiField
    MaterialTextBox change3tag;

    @UiField
    MaterialTextBox change4tag;

    @UiField
    MaterialTextBox change5tag;

    @UiField
    MaterialTextBox change6tag;

    @UiField
    MaterialTextBox minL;

    @UiField
    MaterialTextBox maxL;

    @UiField
    MaterialTextBox minC;

    @UiField
    MaterialTextBox maxC;

    @UiField
    MaterialButton ExportL;

    //AQUI
    @UiField
    MaterialButton ExportToCSV;

    @UiField
    MaterialWindow windowCSV;

    @UiField
    MaterialTextBox CSVfileName;

    @UiField
    MaterialButton startExportWorkbookCSV;

    @UiField
    MaterialButton startExportSpreadsheetCSV;

    @UiField
    MaterialButton startExportPartOfSpreadsheetCSV;

    @UiField
    MaterialWindow windowWorkbook;

    @UiField
    MaterialTextBox changeFieldSeparator;
    @UiField
    MaterialTextBox changeFieldSeparator1;
    @UiField
    MaterialTextBox changeFieldSeparator2;
    @UiField
    MaterialButton Export;

    @UiField
    MaterialWindow windowCSVExportSpreadsheet;

    @UiField
    MaterialTextBox changeSpreadsheet;

    @UiField
    MaterialButton exportworkbookcsv_button;

    @UiField
    MaterialWindow windowPart;

    //joana
    MaterialLabel widget;

    Stack<MaterialLabel> widgetList = new Stack<>();

    @UiField
    MaterialTextBox changeSpreadsheet1;
    @UiField
    MaterialTextBox minLinhas;
    @UiField
    MaterialTextBox maxLinhas;
    @UiField
    MaterialTextBox minColunas;
    @UiField
    MaterialTextBox maxColunas;

    @UiField
    MaterialButton Export2;
    /* Search window content (@author David Camelo <1161294@isep.ipp.pt>) */
    @UiField
    MaterialIcon searchButton;

    @UiField
    MaterialWindow popUpView;

    @UiField
    MaterialTextBox regExpressionTextBox;

    @UiField
    MaterialButton sendRegExpression;

    @UiField
    MaterialTextArea outputOfSearch;

   

 /* ----------------------- */
 /* -- Search window content -- */
    @UiField
    MaterialLink macroButton; // open macro window

    /* Macro usage window´s content */
    @UiField
    MaterialWindow macroWindow;

    @UiField
    MaterialTextArea macroCommands;

    @UiField
    MaterialListValueBox<Macro> macroList;

    @UiField
    MaterialButton executeMacro;

    @UiField
    MaterialWindow macroWindow2;
    @UiField
    MaterialListValueBox<Language> langList;

    @UiField
    MaterialButton langButton;

    @UiField
    MaterialButton resetMacro;

    /**
     * -- MACRO USAGE WINDOW CONTENT *
     *
     * By Pedro Emanuel 1131485
     */
    @UiField
    MaterialRadioButton ascendingButton;

    @UiField
    MaterialRadioButton descendingButton;

    @UiField
    MaterialTextBox firstAddress;
    
  

    @UiField
    MaterialTextBox secondAddress;

    @UiField
    MaterialIcon functionButton;

    @UiField
    MaterialWindow windowFunctions;

//    @UiField
//    MaterialButton applyFunction;
    @UiField
    MaterialButton selFunction;

    @UiField
    MaterialButton selLang;

    @UiField
    MaterialButton selButton;
//    @UiField
//    MaterialButton selPara;
    @UiField
    MaterialListValueBox<Language> languageListBox;

    @UiField
    MaterialListValueBox<Function> functionListBox;

    @UiField
    MaterialListValueBox<Operator> operatorListBox;

    @UiField
    MaterialTextBox formulaEditBox;
//    @UiField
//    MaterialTextBox functionParameters;
    @UiField
    MaterialButton CreateMacro;

    @UiField
    MaterialButton EditMacro;

//    @UiField
//    MaterialTextBox enterPara;
//    @UiField
//    MaterialLabel result;
    /**
     * Rui Almeida <1160818> Lang 07.2 - Visual Basic Macro Call
     * =========================================================
     */
    @UiField
    MaterialTextBox saveMacroName;

    @UiField
    MaterialButton saveMacro;

    @UiField
    MaterialButton viewMacros;

    @UiField
    MaterialButton removeMacro;

    /**
     * =========================================================
     */
    /**
     * David Camelo <1161294@isep.ipp.pt> Core - Set image
     * =========================================================
     */
    @UiField
    MaterialLink setImageButton;
    @UiField
    MaterialWindow setImageWindow;
    @UiField
    MaterialPanel setImagePanel;
    @UiField
    MaterialWindow showImageWindow;
    @UiField
    MaterialImage image;
    @UiField
    MaterialLink removeImageButton;
    /**
     * =========================================================
     */
    
    /* -- RAFAEL 1160911 -- */
    @UiField
    MaterialLink commentButton;

    @UiField
    MaterialButton saveComment;

    @UiField
    MaterialWindow insertCommentWindow;

    @UiField
    MaterialWindow showCommentWindow;

    @UiField
    MaterialLink openComment;

    @UiField
    MaterialLabel commentLabel2;

    @UiField
    MaterialTextBox comment;

    @UiField
    MaterialLabel commentLabel;

    @UiField
    MaterialLink removeCommentButton;

    /* ----------------------- */

    private String parameters;
    private String stringT;
    private int nParameters;
    private ArrayList<MaterialTextBox> texts = new ArrayList<>();
    private MaterialButton buttonApply = new MaterialButton("Apply");
    private MaterialButton buttonCheck = new MaterialButton("Check");
    private MaterialLabel finalResult = new MaterialLabel();
    private MaterialSection ms = new MaterialSection();
    private MaterialSection ms2 = new MaterialSection();
    private MaterialSection ms3 = new MaterialSection();
    private MaterialTextBox parameter1 = new MaterialTextBox();
    private MaterialTextBox parameter2 = new MaterialTextBox();
    private Function finalFunction = null;
    private Operator finalOperator = null;

    /**
     * Rui Ribeiro [1150344] Core03.2 Filter Range of Cells
     */
    @UiField
    MaterialTextBox filterFormula, filterStartCell, filterEndCell, filterColumn, filterStartCellRange, filterEndCellRange;

    @UiField
    MaterialButton filterApply, filterClear;

    HashSet<Integer> rowsSelectedIndex = new HashSet<>();

    /**
     * ENDOF Rui Ribeiro [1150344] Core03.2 Filter Range of Cells
     */
    /**
     * SEARCH N REPLACE
     */
    /**
     * SEARCH N REPLACE
     */
    @UiField
    MaterialListBox lstOptions, lstOptions2;

    @UiField
    MaterialButton startSearchNReplace;

    @UiField
    MaterialTextBox regExpressionTextBox2, regExpressionTextBox3;

    @UiField
    MaterialLabel reg1, reg2, reg3;

    @UiField
    MaterialCheckBox cbYes, cbApply, cbDontApply;

    @UiField
    MaterialTextBox cellFormat, cellFormat2;

    //======================================================================
    // Mario Dias 1151708
    /**
     * Extension combobox
     */
    @UiField
    MaterialComboBox<String> material_list_extension;
    //==============================

    @UiHandler("cbYes")
    void onCheckValue(ValueChangeEvent<Boolean> e) {

        OptionService.getInstance().setYesToAll(e.getValue());

    }

    @UiHandler("cbApply")
    void onCheckValueApply(ValueChangeEvent<Boolean> e) {

        replace(OptionType.CHANGE);
    }

    @UiHandler("cbDontApply")
    void onCheckValueDontApply(ValueChangeEvent<Boolean> e) {

        replace(OptionType.DONT_CHANGE);
    }

    @UiHandler({"lstOptions"})
    void onChangeListBox(ValueChangeEvent<String> e) {

        // 0 - UNDEFINED
        // 1 - NUMERIC
        // 2 - TEXT
        // 3 - BOOLEAN
        // 4 - DATE
        // 5 - MATRIX
        Value.Type selectedType = Value.Type.UNDEFINED;

        switch (lstOptions.getSelectedIndex()) {

            case 0:
                selectedType = Value.Type.UNDEFINED;
                break;
            case 1:
                selectedType = Value.Type.NUMERIC;
                break;
            case 2:
                selectedType = Value.Type.TEXT;
                break;
            case 3:
                selectedType = Value.Type.BOOLEAN;
                break;
            case 4:
                selectedType = Value.Type.DATE;
                break;
            case 5:
                selectedType = Value.Type.MATRIX;
                break;
            default:
                selectedType = Value.Type.UNDEFINED;
                break;
        }

        OptionService.getInstance().changeMyFilter(selectedType);

//   DEBUG     MaterialToast.fireToast("Selected Index: " + lstOptions2.getSelectedIndex());
    }

    @UiHandler({"lstOptions2"})
    void onChangeListBox2(ValueChangeEvent<String> e) {

        // 0 - UNDEFINED
        // 1 - NUMERIC
        // 2 - TEXT
        // 3 - BOOLEAN
        // 4 - DATE
        // 5 - MATRIX
        Value.Type selectedType = Value.Type.UNDEFINED;

        switch (lstOptions2.getSelectedIndex()) {
            case 0:
                selectedType = Value.Type.UNDEFINED;
                break;
            case 1:
                selectedType = Value.Type.NUMERIC;
                break;
            case 2:
                selectedType = Value.Type.TEXT;
                break;
            case 3:
                selectedType = Value.Type.BOOLEAN;
                break;
            case 4:
                selectedType = Value.Type.DATE;
                break;
            case 5:
                selectedType = Value.Type.MATRIX;
                break;
            default:
                selectedType = Value.Type.UNDEFINED;
                break;
        }

        OptionService.getInstance().changeMyFilter(selectedType);

//   DEBUG     MaterialToast.fireToast("Selected Index: " + lstOptions2.getSelectedIndex());
    }

    /**
     * /SEARCH N REPLACE
     */
    //======================================================================
    // Ra�l Correia - 1090657
    /**
     * Refresh button
     */
    @UiField
    MaterialIcon refreshButton;

    @UiField
    MaterialIcon saveButton;
    //==============================


    /* -- Macro window content -- */
    @UiHandler("radioValue")
    void onRadioValue(ValueChangeEvent<Boolean> e) {

        MaterialToast.fireToast("Color defined : " + "Red");

        uiController.changeYellowExtensionColor("RED");
    }

    @UiHandler("radioValue1")
    void onRadioValue1(ValueChangeEvent<Boolean> w) {

        MaterialToast.fireToast("Color defined : " + "Yellow");
        uiController.changeYellowExtensionColor("YELLOW");

    }

    @UiHandler("radioValue2")
    void onRadioValue2(ValueChangeEvent<Boolean> w) {

        MaterialToast.fireToast("Color defined : " + "Green");
        uiController.changeYellowExtensionColor("GREEN");

    }

    @UiHandler("radioValue3")
    void onRadioValue3(ValueChangeEvent<Boolean> w) {

        MaterialToast.fireToast("Color defined : " + "Brown");
        uiController.changeYellowExtensionColor("BROWN");

    }

    @Override
    public MaterialButton getExportButton() {
        return Export;
    }

    @Override
    public MaterialButton getExportXmlButton() {
        return Exportx;
    }

    @Override
    public MaterialButton getExportWorkbookcsvButton() {
        return exportworkbookcsv_button;
    }

    @Override
    public MaterialButton getExportXml1Button() {
        return Exportm;
    }

    @Override
    public MaterialButton getExport2Button() {
        return Export2;
    }

    @Override
    public MaterialButton getExportXml2Button() {
        return ExportL;
    }

    @Override
    public MaterialTextBox getCSVfileName() {
        return CSVfileName;
    }

    @Override
    public MaterialTextBox getFileName1() {
        return fileName1;
    }

    @Override
    public MaterialTextBox gettag1() {
        return changetagOne;
    }

    @Override
    public MaterialTextBox gettag2() {
        return changetagTwo;
    }

    /**
     *
     * @return
     */
    @Override
    public MaterialTextBox gettag3() {
        return changetagThree;
    }

    @Override
    public MaterialTextBox gettag4() {
        return changetagFour;
    }

    @Override
    public MaterialTextBox gettag5() {
        return changetagFive;
    }

    @Override
    public MaterialTextBox gettag6() {
        return changetagSix;
    }

    @Override
    public MaterialTextBox getTag1() {
        return changeTag1;
    }

    @Override
    public MaterialTextBox getTag2() {
        return changeTag2;
    }

    @Override
    public MaterialTextBox getTag3() {
        return changeTag3;
    }

    @Override
    public MaterialTextBox getTag4() {
        return changeTag4;
    }

    @Override
    public MaterialTextBox getTag5() {
        return changeTag5;
    }

    @Override
    public MaterialTextBox getTag6() {
        return changeTag6;
    }

    @Override
    public MaterialTextBox get1Tag() {
        return change1tag;
    }

    @Override
    public MaterialTextBox get2Tag() {
        return change2tag;
    }

    @Override
    public MaterialTextBox get3Tag() {
        return change3tag;
    }

    @Override
    public MaterialTextBox get4Tag() {
        return change4tag;
    }

    @Override
    public MaterialTextBox get5Tag() {
        return change5tag;
    }

    @Override
    public MaterialTextBox get6Tag() {
        return change6tag;
    }

    @Override
    public MaterialTextBox getMinL() {
        return minL;
    }

    @Override
    public MaterialTextBox getMinC() {
        return minC;
    }

    @Override
    public MaterialTextBox getMaxL() {
        return maxL;
    }

    @Override
    public MaterialTextBox getMaxC() {
        return maxC;
    }

    @Override
    public MaterialTextBox getFieldSeparator1() {
        return changeFieldSeparator1;
    }

    @Override
    public MaterialTextBox getFieldSeparator() {
        return changeFieldSeparator;
    }

    @Override
    public MaterialTextBox getFieldSeparator2() {
        return changeFieldSeparator2;
    }

    @Override
    public MaterialTextBox getMinLinhas() {
        return minLinhas;
    }

    @Override
    public MaterialTextBox getMaxLinhas() {
        return maxLinhas;
    }

    @Override
    public MaterialTextBox getMaxColunas() {
        return maxColunas;
    }

    @Override
    public MaterialTextBox getMinColunas() {
        return minColunas;
    }

    @Override
    public MaterialTextBox getFilterFormula() {
        return filterFormula;
    }

    @Override
    public MaterialTextBox getFilterStartCell() {
        return null;
    }

    @Override
    public MaterialTextBox getFilterEndCell() {
        return null;
    }

    @Override
    public MaterialTextBox getFilterColumn() {
        return null;
    }

    @Override
    public MaterialButton getFilterApply() {
        return filterApply;
    }

    @Override
    public MaterialButton getFilterClear() {
        return filterClear;
    }

    interface Binder extends UiBinder<Widget, WorkbookView> {
    }

    private pt.isep.nsheets.shared.core.Cell activeCell = null;

    @Inject
    WorkbookView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        /*####################################
*ANDRÉ OLIVEIRA <1040862@isep.ipp.pt>
         *///##################################
        globalSearchAndReplace.addMouseOverHandler(event -> {
            globalSearchAndReplace.setTitle("Global search and replace");
        });

        globalSearchAndReplace.addClickHandler(event -> {
            globalStartSearchAndReplace.setEnabled(false);
            globalRegExpressionTextBox2.setEnabled(false);
            globalRegExpressionTextBox3.setEnabled(false);
            globalRegExpressionTextBox1.clear();
            globalRegExpressionTextBox2.clear();
            globalRegExpressionTextBox3.clear();
            globalSearchAndReplaceWindow.open();
            MaterialToast.fireToast("All queries in REGEX format");
        });

        globalRegExpressionTextBox1.addKeyPressHandler(event -> {
            globalStartSearchAndReplace.setEnabled(true);
            globalRegExpressionTextBox2.setEnabled(true);
            globalRegExpressionTextBox3.setEnabled(true);
            globalStartSearchAndReplace.setBackgroundColor(Color.BLUE);
        });

        globalRegExpressionTextBox2.addKeyPressHandler(event -> {
            globalStartSearchAndReplace.setText("SEARCH AND REPLACE");
            globalStartSearchAndReplace.setBackgroundColor(Color.GREEN);
        });

        globalRegExpressionTextBox3.addKeyPressHandler(event -> {
            globalStartSearchAndReplace.setBackgroundColor(Color.RED);
        });

        globalStartSearchAndReplace.addClickHandler(clickEvent -> {
            if (globalRegExpressionTextBox1.getText().equals("")) {
                MaterialToast.fireToast("Search query empty");
            } else {
                if (globalRegExpressionTextBox2.getText().equals("") && globalRegExpressionTextBox3.getText().equals("")) {
                    MaterialToast.fireToast("Searching...");
                    GlobalSearchAndReplaceServiceAsync Svc = GWT.create(GlobalSearchAndReplaceService.class);

                    AsyncCallback<List<String>> callback = new AsyncCallback<List<String>>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            MaterialToast.fireToast("Error! " + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(List<String> result) {
                            globalRegExpressionTextBox1.clear();
                            globalRegExpressionTextBox2.clear();
                            globalRegExpressionTextBox3.clear();
                            for (String string : result) {
                                MaterialToast.fireToast(string);
                            }
                        }
                    };
                    Svc.showOutputOfGlobalSearch(username, globalRegExpressionTextBox1.getValue(), callback);
                }
                if (!globalRegExpressionTextBox2.getText().equals("") && globalRegExpressionTextBox3.getText().equals("")) {
                    MaterialToast.fireToast("Searching and replacing...");
                    GlobalSearchAndReplaceServiceAsync Svc = GWT.create(GlobalSearchAndReplaceService.class);

                    AsyncCallback<List<String>> callback = new AsyncCallback<List<String>>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            MaterialToast.fireToast("Error! " + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(List<String> result) {
                            globalRegExpressionTextBox1.clear();
                            globalRegExpressionTextBox2.clear();
                            globalRegExpressionTextBox3.clear();
                            for (String string : result) {
                                MaterialToast.fireToast(string);
                            }
                        }

                    };
                    Svc.showOutputOfGlobalSearchAndReplace(username, globalRegExpressionTextBox1.getValue(), globalRegExpressionTextBox2.getValue(), callback);
                }
                if (globalRegExpressionTextBox2.getText().equals("") && !globalRegExpressionTextBox3.getText().equals("")) {
                    MaterialToast.fireToast("Searching in woorkbook " + globalRegExpressionTextBox3.getText());
                    GlobalSearchAndReplaceServiceAsync Svc = GWT.create(GlobalSearchAndReplaceService.class);

                    AsyncCallback<List<String>> callback = new AsyncCallback<List<String>>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            MaterialToast.fireToast("Error! " + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(List<String> result) {
                            globalRegExpressionTextBox1.clear();
                            globalRegExpressionTextBox2.clear();
                            globalRegExpressionTextBox3.clear();
                            for (String string : result) {
                                MaterialToast.fireToast(string);
                            }
                        }
                    };
                    Svc.showOutputOfWorkbookSearch(username, globalRegExpressionTextBox1.getValue(), globalRegExpressionTextBox3.getValue(), callback);
                }
                if (!globalRegExpressionTextBox2.getText().equals("") && !globalRegExpressionTextBox3.getText().equals("")) {
                    MaterialToast.fireToast("Searching replacing in woorkbook " + globalRegExpressionTextBox3.getText());
                    GlobalSearchAndReplaceServiceAsync Svc = GWT.create(GlobalSearchAndReplaceService.class);

                    AsyncCallback<List<String>> callback = new AsyncCallback<List<String>>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            MaterialToast.fireToast("Error! " + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(List<String> result) {
                            globalRegExpressionTextBox1.clear();
                            globalRegExpressionTextBox2.clear();
                            globalRegExpressionTextBox3.clear();
                            for (String string : result) {
                                MaterialToast.fireToast(string);
                            }
                        }
                    };
                    Svc.showOutputOfWorkbookSearchAndReplace(username, globalRegExpressionTextBox1.getValue(), globalRegExpressionTextBox3.getValue(), globalRegExpressionTextBox2.getValue(), callback);
                }
            }
        });

//####################################
        Extension[] extension_vec = uiController.getAllExtensions();
        for (int i = 0; i < extension_vec.length; i++) {
            material_list_extension.addItem(extension_vec[i].getExtensionType());
        }
        material_list_extension.addSelectionHandler(selectionEvent -> {
            String extension = material_list_extension.getSingleValue();
            if (extension.equals("Cell Background")) {
                window.open();
            }
            if (extension.equals("Conditional Formating")) {
                windowFormat.open();
            }
            if (extension.equals("Border Extension")) {
                windowFormat.open();
            }
            if (extension.equals("Text Extension")) {
                windowFormat.open();
            }
        });

        ExportToPDF.addClickHandler(event -> {
            windowPDF.open();
        });

        startExportSpreadsheetPDF.addClickHandler(
                clickEvent -> {
                    windowPDF.close();
                    windowSpreadsheetPDF.open();
                }
        );

        startExportPartialSpreadsheetPDF.addClickHandler(
                clickEvent -> {
                    windowPDF.close();
                    windowPartialSpreadsheetPDF.open();
                }
        );

        functionButton.addClickHandler(event -> {
            languageListBox.clear();
            functionListBox.clear();
            //functionParameters.clear();
            List<Language> ll = new ArrayList<Language>();
            ll = LanguageManager.getInstance().getLanguages();
            for (Language lang : ll) {
                languageListBox.addItem(lang, lang.getName());
            }
            windowFunctions.open();
        });

        filterApply.addClickHandler(event -> {
            if (filterFormula.getText().isEmpty() || filterColumn.getText().isEmpty() || filterStartCell.getText().isEmpty() || filterEndCell.getText().isEmpty()) {
                for (RowComponent<SheetCell> row : customTable.getRows()) {
                    MaterialToast.fireToast("Filter Cleared.");
                    row.setEnabled(true);
                }
            } else {
                HashSet<Integer> rowsToHide = new HashSet<>();
                if (filterController.isValidColumn(filterStartCell.getText(), filterEndCell.getText(), filterColumn.getText())) {
                    filterColumn.clearErrorOrSuccess();
                    try {
                        filterController.filter(filterStartCell.getText(), filterEndCell.getText(), filterColumn.getText(), WorkbookManager.getInstance().getCurrentActiveWorkbook().getSpreadsheet(0), filterFormula.getText(), rowsToHide);
                        filterFormula.setSuccess("Filtered the column: " + filterColumn.getText());
                        filterColumn.clearErrorOrSuccess();
                    } catch (FormulaCompilationException ex) {
                        filterFormula.setError("Invalid Formula.");
                    } catch (IllegalValueTypeException ex) {
                        filterFormula.setError("Could not apply a boolean formula to a Cell.");
                    }
                } else {
                    filterColumn.setError("This column does not belong in the specified range.");
                }
                rowsToHide.forEach((i) -> {
                    MaterialToast.fireToast("Disable the row: " + i);
                    customTable.getRow(i).setEnabled(false);
                });
            }
        });

        filterClear.addClickHandler(event -> {
            filterFormula.clear();
            for (RowComponent<SheetCell> row : customTable.getRows()) {
                row.setEnabled(true);
            }
        });

        selLang.addClickHandler(event -> {
            finalFunction = null;
            finalOperator = null;
            windowFunctions.remove(parameter1);
            windowFunctions.remove(parameter2);
            for (MaterialTextBox text : texts) {
                windowFunctions.remove(text);
            }
            texts.clear();
            windowFunctions.remove(buttonApply);
            windowFunctions.remove(buttonCheck);
            windowFunctions.remove(finalResult);
            functionListBox.clear();
            //functionParameters.clear();
            //operatorListBox.clear();
            Language l = languageListBox.getSelectedValue();
            List<Function> lf = new ArrayList<Function>();
            List<Operator> lo = new ArrayList<Operator>();
            UnaryOperator[] uo = l.getUnaryOperators();
            BinaryOperator[] bo = l.getBinaryOperators();
            NaryOperator[] no = l.getNaryOperators();
            Function[] f = l.getFunctions();
            for (int i = 0; i < uo.length; i++) {
                lo.add(uo[i]);
            }
            for (int i = 0; i < bo.length; i++) {
                lo.add(bo[i]);
            }
            for (int i = 0; i < no.length; i++) {
                lo.add(no[i]);
            }
            for (int i = 0; i < f.length; i++) {
                lf.add(f[i]);
            }
            for (Function func : lf) {
                functionListBox.addItem(func, func.getIdentifier());
            }
            for (Operator operator : lo) {
                operatorListBox.addItem(operator, operator.getIdentifier());
            }
            //result.setText("Este é o resultado");
//            Function func = functionListBox.getSelectedValue();
//            String parameters = l.getStarter() + func.getIdentifier() + "(";
//            FunctionParameter[] fp = func.getParameters();
//            for (int i = 0; i < fp.length; i++) {
//                if (i < fp.length - 1) {
//                    parameters += fp[i].getValueType() + ";";
//                } else {
//                    parameters += fp[i].getValueType();
//                }
//            }
//            parameters += ")";
//            functionParameters.setValue(parameters);

        });

        selFunction.addClickHandler(event -> {
            finalOperator = null;
            windowFunctions.remove(ms);
            windowFunctions.remove(ms2);
            windowFunctions.remove(ms3);
            windowFunctions.remove(parameter1);
            windowFunctions.remove(parameter2);
            for (MaterialTextBox text : texts) {
                windowFunctions.remove(text);
            }
            texts.clear();
            parameters = "";
            nParameters = 0;
            finalFunction = functionListBox.getSelectedValue();
            formulaEditBox.setText(formulaEditBox.getText().substring(0, formulaEditBox.asValueBoxBase().getCursorPos()) + finalFunction.getIdentifier() + formulaEditBox.getText().substring(formulaEditBox.asValueBoxBase().getCursorPos(), formulaEditBox.getText().length()));
//            Language l = languageListBox.getSelectedValue();
//            stringT = l.getStarter() + func.getIdentifier() + "(" + parameters + ")";
            //functionParameters.setValue(stringT);
//            String parameters = l.getStarter() + func.getIdentifier() + "(";
            FunctionParameter[] fp = finalFunction.getParameters();
            for (int i = 0; i < fp.length; i++) {
                MaterialTextBox mtb = new MaterialTextBox();
                mtb.setLabel("parameter" + (i + 1));
                texts.add(mtb);
            }
            windowFunctions.add(ms);
            windowFunctions.add(ms2);
            windowFunctions.add(ms3);
            for (MaterialTextBox b : texts) {
                windowFunctions.add(b);
            }

            windowFunctions.add(buttonApply);
            windowFunctions.add(buttonCheck);
            finalResult.setText("This is the result");
            windowFunctions.add(finalResult);
//            parameters += ")";
//            functionParameters.setValue(parameters);

        });

        selectSpreadIcon.addClickHandler(event -> {
            Workbook wb = WorkbookManager.getInstance().getCurrentActiveWorkbook();
            indexListBox.clear();
            if (wb != null) {
                windowSelectSpreadsheet.open();
                List<Spreadsheet> s = wb.getSpreadSheets();
                for (Spreadsheet s1 : s) {
                    indexListBox.add(s1);
                }
            }
        });

        buttonSlectActive.addClickHandler(event -> {
            Spreadsheet s3 = indexListBox.getSelectedValue();
            Workbook wb = WorkbookManager.getInstance().getCurrentActiveWorkbook();
            int count = 0;
            for (Spreadsheet s4 : wb.getSpreadSheets()) {
                if (s4.equals(s3)) {
                    wb.setActiveSpread(count);
                } else {
                    count++;
                }
            }
            windowSelectSpreadsheet.close();
        });

        editButtonInfo.addClickHandler(event -> {
            windowEditWorkbook.open();
        });

        selButton.addClickHandler(event -> {
            finalFunction = null;
            windowFunctions.remove(ms);
            windowFunctions.remove(ms2);
            windowFunctions.remove(ms3);
            windowFunctions.remove(parameter1);
            windowFunctions.remove(parameter2);
            for (MaterialTextBox text : texts) {
                windowFunctions.remove(text);
            }
            texts.clear();
            parameters = "";
            nParameters = 0;
            parameter1.setLabel("parameter1");
            parameter2.setLabel("parameter2");
            finalOperator = operatorListBox.getSelectedValue();
            formulaEditBox.setText(formulaEditBox.getText().substring(0, formulaEditBox.asValueBoxBase().getCursorPos()) + finalOperator.getIdentifier() + formulaEditBox.getText().substring(formulaEditBox.asValueBoxBase().getCursorPos(), formulaEditBox.getText().length()));
            windowFunctions.add(ms);
            windowFunctions.add(ms2);
            windowFunctions.add(ms3);
            windowFunctions.add(parameter1);
            windowFunctions.add(parameter2);
            windowFunctions.add(buttonApply);
            windowFunctions.add(buttonCheck);
            finalResult.setText("This is the result");
            windowFunctions.add(finalResult);
        });

//        selPara.addClickHandler(event -> {
//            String para = enterPara.getText();
//            Function func = functionListBox.getSelectedValue();
//            Language l = languageListBox.getSelectedValue();
//            if (nParameters == 0) {
//                parameters = para;
//                nParameters++;
//            } else {
//                parameters = parameters + ";" + para;
//                nParameters++;
//            }
//            stringT = l.getStarter() + func.getIdentifier() + "(" + parameters + ")";
//            functionParameters.setValue(stringT);
//        });
        buttonCheck.addClickHandler(event -> {
            if (finalFunction != null) {
                String text = "";
                //Function f = functionListBox.getSelectedValue();
                Language l = languageListBox.getSelectedValue();
                int i = 0;
                for (MaterialTextBox texttttt : texts) {
                    if (i == 0) {
                        parameters = texttttt.getText();
                        i++;
                    } else {
                        parameters = parameters + ";" + texttttt.getText();
                    }
                }
                formulaEditBox.setText(formulaEditBox.getText().substring(0, formulaEditBox.asValueBoxBase().getCursorPos()) + "(" + parameters + ")" + formulaEditBox.getText().substring(formulaEditBox.asValueBoxBase().getCursorPos(), formulaEditBox.getText().length()));
                stringT = l.getStarter() + finalFunction.getIdentifier() + "(" + parameters + ")";
                Spreadsheet s = new SpreadsheetImpl();
                try {
                    CellImpl cell = new CellImpl(s, new Address(), stringT);
                    finalResult.setTextColor(Color.GREEN);
                    text = cell.getValue().toString();

                } catch (FormulaCompilationException ex) {
                    finalResult.setTextColor(Color.RED);
                    text = ex.getClass().getSimpleName() + ": " + ex.getLocalizedMessage();
                }
                finalResult.setText(text);
            } else if (finalOperator != null) {
                String text = "";
                Language l = languageListBox.getSelectedValue();
                stringT = l.getStarter() + parameter1.getText() + finalOperator + parameter2.getText();
                Spreadsheet s = new SpreadsheetImpl();
                try {
                    CellImpl cell = new CellImpl(s, new Address(), stringT);
                    finalResult.setTextColor(Color.GREEN);
                    text = cell.getValue().toString();

                } catch (FormulaCompilationException ex) {
                    finalResult.setTextColor(Color.RED);
                    text = ex.getClass().getSimpleName() + ": " + ex.getLocalizedMessage();
                }
                finalResult.setText(text);
            }
        });
        swState2.addClickHandler(event -> {

            if (swState.getValue() == false) {
                uiController.changeYellowExtensionColor("WHITE");
                customTable.getView().setRedraw(true);
                customTable.getView().refresh();
            }
            if (swState.getValue() == true) {
                uiController.changeYellowExtensionColor("YELLOW");
                customTable.getView().setRedraw(true);
                customTable.getView().refresh();
            }
            if (swState4.getValue() == false) {
                image.setVisible(false);
            }
            if (swState4.getValue() == true) {
                image.setVisible(true);
            }

        });

        buttonApply.addClickHandler(event -> {
            if (finalFunction != null) {
                String text = "";
                //Function f = functionListBox.getSelectedValue();
                Language l = languageListBox.getSelectedValue();
                int i = 0;
                for (MaterialTextBox texttttt : texts) {
                    if (i == 0) {
                        parameters = texttttt.getText();
                        i++;
                    } else {
                        parameters = parameters + ";" + texttttt.getText();
                    }
                }
                stringT = l.getStarter() + finalFunction.getIdentifier() + "(" + parameters + ")";
            } else if (finalOperator != null) {
                String text = "";
                Language l = languageListBox.getSelectedValue();
                stringT = l.getStarter() + parameter1.getText() + finalOperator + parameter2.getText();
            }
            firstBox.setText("=" + formulaEditBox.getText());
            windowFunctions.close();
        });

        menuAddIcon.addClickHandler(event -> {
            windowAddSpreadsheet.open();
        });

        ExportToXML.addClickHandler(event -> {
            windowXML.open();
        });

        ExportToCSV.addClickHandler(event -> {
            windowCSV.open();
        });

        // Opens pop-up
        secondtButton.addClickHandler(event -> {

            window.open();

        });
        activate.addClickHandler(event -> {

            ac.open();

        });

        searchNReplace_btn.addClickHandler(event -> {

            searchNReplace_window.open();

        });

        sortButton.addClickHandler(
                clickEvent -> {
                    janela.open();
                }
        );
        startExportWorkbookCSV.addClickHandler(
                clickEvent -> {
                    //                    windowCSV.close();
//                    windowWorkbook.open();
                    String name = CSVfileName.getText();
                    WorkbookDTO wb = activeCell.getSpreadsheet().getWorkbook().toDTO();
                    int rows = 0;
                    int cols = 0;
                    for (SpreadsheetDTO spreadsheet : wb.getSpreadsheets()) {
                        if (rows < spreadsheet.getRows()) {
                            rows = spreadsheet.getRows();
                        }
                        if (cols < spreadsheet.getColumns()) {
                            cols = spreadsheet.getColumns();
                        }
                    }

                    String[][][] workbook = new String[wb.createdSpreadsheets][rows][cols];
                    int sheet = 0;
                    for (SpreadsheetDTO spreadsheet : wb.getSpreadsheets()) {
                        workbook[sheet] = spreadsheet.getContent();
                    }
                    ExportToCSVFormatServiceAsync export = GWT.create(ExportToCSVFormatService.class);
                    AsyncCallback<String> callback = new AsyncCallback<String>() {
                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error");
                }

                @Override
                public void onSuccess(String result) {
                    result = result.substring(1);
                    String url = GWT.getHostPageBaseURL();
                    String link = url + result;
                    label_download.setText(link);
                }
            };
                    export.exportWorkbook(workbook, name, callback);
                }
        );

        importCSVbutton.addClickHandler(clickEvent -> {
            importWindow.open();
        });
        uploadFilebutton.addClickHandler(clickEvent -> {
            UploadCSVServiceAsync importFile = GWT.create(UploadCSVService.class);
            AsyncCallback<String[][]> callback = new AsyncCallback<String[][]>() {
                @Override
                public void onFailure(Throwable caught) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void onSuccess(String[][] result) {
                    if (result != null) {
                        MaterialToast.fireToast("Success!");
                        try {
                            activeCell.getSpreadsheet().getWorkbook().addSpreadsheet(result);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        MaterialToast.fireToast("Error importing!");
                    }
                }
            };

            String path = form.getImagePath().split("name=")[1];
            if (!path.isEmpty()) {
                try {
                    importFile.uploadCSV(path, callback);
                } catch (Exception ex) {
                    MaterialToast.fireToast("Error Reading");
                }
            } else {
                MaterialToast.fireToast("Error empty");
            }
        });

        importWindow.addOpenHandler(new OpenHandler() {
            public void onOpen(OpenEvent event) {
                fileChooser.clear();
                form = new CSVFileForm(fileChooser);
                form.onModuleLoad();
            }
        });

        startExportSpreadsheetCSV.addClickHandler(clickEvent -> {
            windowCSV.close();
            windowCSVExportSpreadsheet.open();
        }
        );

        startExportPartOfSpreadsheetCSV.addClickHandler(
                clickEvent -> {
                    windowCSV.close();
                    windowPart.open();
                }
        );
        startExportWorkbookXML.addClickHandler(
                clickEvent -> {
                    String name = fileName1.getText();
                    WorkbookDTO wb = activeCell.getSpreadsheet().getWorkbook().toDTO();
                    int rows = 0;
                    int cols = 0;
                    for (SpreadsheetDTO spreadsheet : wb.getSpreadsheets()) {
                        if (rows < spreadsheet.getRows()) {
                            rows = spreadsheet.getRows();
                        }
                        if (cols < spreadsheet.getColumns()) {
                            cols = spreadsheet.getColumns();
                        }
                    }

                    String[][][] workbook = new String[wb.createdSpreadsheets][rows][cols];
                    int sheet = 0;
                    for (SpreadsheetDTO spreadsheet : wb.getSpreadsheets()) {
                        workbook[sheet] = spreadsheet.getContent();
                    }
                    ExportToXMLFormatServiceAsync export = GWT.create(ExportToXMLFormatService.class);
                    AsyncCallback<String> callback = new AsyncCallback<String>() {
                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error");
                }

                @Override
                public void onSuccess(String result) {
                    result = result.substring(1);
                    String url = GWT.getHostPageBaseURL();
                    String link = url + result;
                    label_download.setText(link);
                }
            };
                    export.exportWorkbook(workbook, name, callback);
                    windowXML.close();
                    windowSpreadsheet1.open();
                }
        );

        startExportSpreadsheetXML.addClickHandler(
                clickEvent -> {
                windowXML.close();
                windowSpreadsheet1.open();
                });
       
        
        Exportm.addClickHandler(
               clickEvent -> {
                    
//                    String name = spreadSheet2XMLtext.getText();
//                    SpreadsheetDTO spreadsheet = activeCell.getSpreadsheet().toDTO();
//                    int rows = 0;
//                    int cols = 0;
//                    
//                        if (rows < spreadsheet.getRows()) {
//                            rows = spreadsheet.getRows();
//                        }
//                        if (cols < spreadsheet.getColumns()) {
//                            cols = spreadsheet.getColumns();
//                        }
//                    
//                    ExportSpreadSheetToXMLServiceAsync export = GWT.create(ExportSpreadSheetToXMLService.class);
//                    AsyncCallback<String> callback = new AsyncCallback<String>() {
//                @Override
//                public void onFailure(Throwable caught) {
//                    MaterialToast.fireToast("Error");
//                }
//
//                @Override
//                public void onSuccess(String result) {
//                    result = result.substring(1);
//                    String url = GWT.getHostPageBaseURL();
//                    String link = url + result;
//                    label_download.setText(link);
//                }
//            };
//                    export.exportSpreadSheet(activeCell.getSpreadsheet(), name, callback);
//                     windowXML.close();
//                     windowSpreadsheet1.open();

                    windowXML.close();
                    windowSpreadsheet1.open();
                }
        );
        
        ExportL.addClickHandler(
               clickEvent -> {
                    
//                    String name = spreadSheet2XMLtext2.getText();
//                    SpreadsheetDTO spreadsheet = activeCell.getSpreadsheet().toDTO();
//                    int srow = toInteger(minL.getValue());
//                    int scol = toInteger(minC.getValue());;
//                    int frow = toInteger(maxL.getValue());
//                    int fcol = toInteger(maxC.getValue());;
//                    
//                        
//                    
//                    ExportSpreadSheetPartToXMLServiceAsync export = GWT.create(ExportSpreadSheetPartToXMLService.class);
//                    AsyncCallback<String> callback = new AsyncCallback<String>() {
//                @Override
//                public void onFailure(Throwable caught) {
//                    MaterialToast.fireToast("Error");
//                }
//
//                @Override
//                public void onSuccess(String result) {
//                    result = result.substring(1);
//                    String url = GWT.getHostPageBaseURL();
//                    String link = url + result;
//                    label_download.setText(link);
//                }
//            };
//                    export.exportSpreadSheetPart(name, activeCell.getSpreadsheet(), srow, frow, scol, fcol, callback);
//                     windowXML.close();
//                     windowSpreadsheet1.open();
                }
        );
        
        
        startExportPartOfSpreadsheetXML.addClickHandler(
                clickEvent -> {
                    windowXML.close();
                    windowPart1.open();
                }
        );

        firstButton.addClickHandler(event -> {
            if (activeCell != null) {
                String result = "";
                try {
                    activeCell.setContent(firstBox.getText());
                } catch (FormulaCompilationException e) {
                    // TODO Auto-generated catch block
                    // YellowExtension.printStackTrace();
                    result = e.getMessage();
                } finally {
                    // resultLabel.setText(result);

                    // refresh the table...
                    customTable.getView().setRedraw(true);
                    customTable.getView().refresh();

                    // refresh the active cell
//
                    //this.setActiveCell(activeCell, widget);
                    SpreadSheetSingleton.getInstance().wb = activeCell.getSpreadsheet().getWorkbook();
                }

            }
            // Window.alert("Hello");
        });

        /*
         * Macro window button click handlers (@author Pedro Emanuel Coelho
         * 1131485@isep.ipp.pt )
         */
        //Event when user changes the index on listbox
        macroList.addValueChangeHandler(selectEvent -> {

            MaterialToast.fireToast("Selected Macro : " + macroList.getSelectedValue().name());

        });

        macroButton.addClickHandler(clickEvent -> {
            MacroLanguageController ctrl = new MacroLanguageController();

            macroWindow.setTitle("Macro Window - NSHEETS");

            macroList.clear();

            Macro macro = new Macro("MACRO321");

            macro.addCommand("3+3\n2*2\n;one comment\nsum(E1:E3)\n{3+3;2*2;6*4}");

            macroList.addItem(macro, macro.toString());

            langList.clear();

            for (MacroLanguage l : ctrl.getAllMacroLanguages()) {

                langList.addItem(l, l.getName());

            }

            MaterialToast.fireToast("TOTAL OF MACROS LOADED : " + macroList.getItemCount());

            if (!macro.commands().isEmpty()) {
                macroCommands.setText(macro.commands());
            }

            macroWindow.open();
            macroWindow2.close();
        });

        //button that changes the macro language
        langButton.addClickHandler(event -> {

            Macro m = macroList.getSelectedValue();

            int i = macroList.getSelectedIndex();

            m.resetMacro();

            macroCommands.setText("");

            Language l = langList.getSelectedValue();

            m.changeLanguage(l);

            MaterialToast.fireToast("Macro language changed to " + m.language().getName());

            macroList.removeItem(i);

            macroList.addItem(m, m.toString());

            macroList.reload();
        });

        /**
         * Rui Almeida <1160818> Lang 07.2 - Visual Basic Macro Call START
         * =========================================================
         */
        /**
         * Saves a macro to wb event
         */
        saveMacro.addClickHandler(event -> {

            if (saveMacroName.getText().isEmpty()) {
                MaterialToast.fireToast("Macro name is empty!");
            } else {
                if (macroCommands.getText().isEmpty()) {
                    MaterialToast.fireToast("There are no commands to add to the macro!");
                } else {
                    Macro macro = new Macro(saveMacroName.getText());
                    macro.addCommand(macroCommands.getText());

                    boolean flag = false;

                    for (Macro m : wb.macros()) {
                        if (m.name().equalsIgnoreCase(saveMacroName.getText())) {
                            flag = true;
                        }
                    }

                    if (!flag) {
                        if (wb.addMacro(macro)) {
                            MaterialToast.fireToast("Macro " + macro.name() + " added to the workbook.");
                            saveMacroName.clear();
                            macroCommands.clear();
                        }
                    } else {
                        if (flag) {
                            MaterialToast.fireToast("Could not add macro: name already exists!");
                        } else {
                            MaterialToast.fireToast("Could not add macro!");
                        }
                    }
                }
            }
        });

        /**
         * View all saved macros event
         */
        viewMacros.addClickHandler(event -> {
            StringBuilder s = new StringBuilder();
            s.append("LIST OF MACROS\n================\n");
            for (Macro m : wb.macros()) {
                s.append("Macro name: " + m.name() + "\n" + "Macro commands: " + m.commands() + "\n----\n");
            }
            Window.alert(s.toString());
        });

        /**
         * Removes a macro from wb
         */
        removeMacro.addClickHandler(event -> {
            String macroName = saveMacroName.getText();

            if (wb.removeMacro(macroName)) {
                MaterialToast.fireToast("Macro " + macroName + " was removed with success.");
            } else {
                MaterialToast.fireToast("Could not find/delete macro " + macroName + "!");
            }
        });

        /**
         * Lang 07.2 MACRO CALL END
         * =========================================================
         */
        executeMacro.addClickHandler(event -> {

            Macro macro = macroList.getSelectedValue();

            macro.addCommand(macroCommands.getText());

            ExpressionCompiler compiler = MacroCompilerManager.getInstance().getCompiler(macro.language().getName());

            try {
                Expression expression = compiler.compile(activeCell, macro.commands());

                Value value = expression.evaluate();

                activeCell.setContent(value.toString());
                customTable.getView().setRedraw(true);
                customTable.getView().refresh();

                MaterialToast.fireToast("Result of Macro : " + value.toString()); //to show on Window, as requirement

            } catch (FormulaCompilationException | IllegalValueTypeException ex) {
                MaterialToast.fireToast(ex.getMessage());
            }
        });

        resetMacro.addClickHandler(event -> {

            macroList.reload();

            Macro m = macroList.getSelectedValue();

            m.resetMacro();

            macroCommands.setText("");

            MaterialToast.fireToast("Reset on macro done with sucess!");

            //persist workbook
        });

        /* -- MACRO WINDOW HANDLERS -- */

 /*
         * Search window button click handlers (@author David Camelo
         * <1161294@isep.ipp.pt>)
         */
        searchButton.addClickHandler(clickEvent -> {
            final String EMPTY = "";

            outputOfSearch.setText(EMPTY);
            regExpressionTextBox.setText(EMPTY);

            popUpView.open();
        });


        /*.addClickHandler(clickEvent -> {
            final String EMPTY = "";

            outputOfSearch.setText(EMPTY);
            regExpressionTextBox.setText(EMPTY);

            popUpView.open();
        });
         */
        startExportWorkbookPDF.addClickHandler(clickEvent -> {
            // Window.alert("Not Suported Yet!");
            WorkbookDescriptionDTO currentDescription = WorkbookManager.getInstance().getCurrentDescription();
            WorkbookDTO wbdto = currentDescription.getWorkbook();
            //WorkbookDTO wbDTO = wb.toDTO();
            /*
            List<Spreadsheet> list = wb.getSpreadSheets();
            List<SpreadsheetDTO> listDTO = new ArrayList<>();

            for (Spreadsheet s : list) {
                SpreadsheetDTO sDTO = s.toDTO();
                String[][] lul = new String[s.getRowCount()][s.getColumnCount()];

                for (int i = 0; i < s.getRowCount(); i++) {
                    for (int j = 0; j < s.getColumnCount(); j++) {
                        String ze = s.getCell(j, i).getContent();
                        lul[i][j] = ze;
                    }
                }

                sDTO.setContent(lul);
                wbDTO.getSpreadsheets().add(sDTO);
            
            }
             */
            //MaterialToast.fireToast("Numero sheets "+wbDTO.getCreatedSpreadsheets());

            ExportToPDFServiceAsync exportPDF = GWT.create(ExportToPDFService.class);
            AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("An internal error has occurred while exporting to pdf! " + caught.getMessage());
                }

                @Override
                public void onSuccess(Boolean result) {
                    if (result == true) {
                        MaterialToast.fireToast("Export to PDF successful!");
                    } else if (result == false) {
                        MaterialToast.fireToast("Export to PDF failed");
                    }
                }

            };
            exportPDF.exportWorkbookToPDF(wbdto, fileName.getText(), callback);
        });

        ExportSpreadsheetPDF.addClickHandler(clickEvent -> {
            WorkbookDescriptionDTO currentDescription = WorkbookManager.getInstance().getCurrentDescription();
            WorkbookDTO wbdto = currentDescription.getWorkbook();
            int n = Integer.parseInt(SpreadsheetNumber1.getValue());
            ExportToPDFServiceAsync exportPDF = GWT.create(ExportToPDFService.class);
            AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("An internal error has occurred while exporting to pdf! " + caught.getMessage());
                }

                @Override
                public void onSuccess(Boolean result) {
                    if (result == true) {
                        MaterialToast.fireToast("Export to PDF successful!");
                    } else if (result == false) {
                        MaterialToast.fireToast("Export to PDF failed");
                    }
                }

            };
            exportPDF.exportSpreadsheetToPDF(wbdto, n, fileName.getText(), callback);
        });

        ExportPartialSpreadsheetPDF.addClickHandler(clickEvent -> {
            //   Window.alert("Not Suported Yet!");
//        
            String pos1 = posicao1.getText();
            MaterialToast.fireToast("START:" + pos1);
            String pos2 = posicao2.getText();
            MaterialToast.fireToast("END:" + pos2);
            int n = Integer.parseInt(SpreadsheetNumber2.getValue());
            //Spreadsheet s =  wb.getSpreadsheet(n);

            WorkbookDescriptionDTO currentDescription = WorkbookManager.getInstance().getCurrentDescription();
            WorkbookDTO wbdto = currentDescription.getWorkbook();

            ExportToPDFServiceAsync exportPDF = GWT.create(ExportToPDFService.class);
            AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("An internal error has occurred while exporting to pdf! " + caught.getMessage());
                }

                @Override
                public void onSuccess(Boolean result) {
                    if (result == true) {
                        MaterialToast.fireToast("Export to PDF successful!");
                    } else if (result == false) {
                        MaterialToast.fireToast("Export to PDF failed");
                    }
                }

            };
            exportPDF.exportPartOfSpreadsheet(wbdto, n, fileName.getText(), pos1, pos2, callback);

        });

        sendRegExpression.addClickHandler(clickEvent -> {

            Spreadsheet sh_test = activeCell.getSpreadsheet();

            // ------------------------ TEMP. SOLUTION ------------------------------------
            Window.alert("Number os spreadSheets before:" + sh_test.getWorkbook().getSpreadsheetCount());

            String contents[][] = { // second spreadsheet
                /*    A    B    C     D   E    F     G */
                {"1", "-1", "1", "1", "2", "2", "3"}, //1
                {"5", "3", "1", "-1", "-4", "3", "2"}, // 2
                {"1", "-2", "3", "-2", "5", "-1", "-2"}};           // 3

            try {
                sh_test.getWorkbook().addSpreadsheet(contents);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Window.alert("Number os spreadSheets after:" + sh_test.getWorkbook().getSpreadsheetCount());

            // ------------------------ TEMP. SOLUTION ------------------------------------
            if (regExpressionTextBox.getText().equals("")) {
                MaterialToast.fireToast("Textbox empty");
            } else {
                final String NEW_LINE = "\n";
                final String EMPTY = "";

                outputOfSearch.setText(EMPTY);

                OptionService.getInstance().setMyWb(sh_test.getWorkbook());
                OptionService.getInstance().setMySS(sh_test);

                startSearch(sh_test, 0, 0);

            }

            if (outputOfSearch.equals("")) {
                MaterialToast.fireToast("Nothing Found with regex:" + regExpressionTextBox.getText());
                outputOfSearch.setText("Nothing found...");
            }
        });
        /* -- Search window button click handlers -- */

 /* SEARCH N REPLACE - DAVID BLANQUETT<1161018@isep.ipp.pt> */
        startSearchNReplace.addClickHandler(clickEvent -> {


            /* Checks if the textboxes are empty. */
            if (checkUIDetails(regExpressionTextBox2, regExpressionTextBox3)) {


                /* UI DETAILS : RESETS CBOX TO DEFAULT VALUE */
                cbYes.setValue(true);

                searchNReplace(activeCell.getSpreadsheet());

            }

        });
        /* /SEARCH N REPLACE - DAVID BLANQUETT<1161018@isep.ipp.pt> */

 /* -- Search window button click handlers -- */
        // It is possible to create your own custom renderer per table
        // When you use the BaseRenderer you can override certain draw
        // methods to create elements the way you would like.
        customTable.getView().setRenderer(new SheetRenderer<SheetCell>());

        /* conditional format window  (@author Beatriz Ferreira <1160701@isep.ipp.pt>)*/
        ConditionalFormatView cf = new ConditionalFormatView();
        List<BinaryOperator> operators = cf.listBinaryOperations();
        List<Color> colors = cf.listColor();
        List<Style.BorderStyle> borders = cf.listBorders();
        for (Color c : colors) {
            listColors.addItem(c);
            listColors2.addItem(c);
            listColors3.addItem(c);
            listColors4.addItem(c);

        }

        for (BinaryOperator b : operators) {
            conditional.addItem(b);
            conditional2.addItem(b);
            conditional3.addItem(b);
        }

        for (Style.BorderStyle border : borders) {
            listBorder.addItem(border);
            listBorder2.addItem(border);
        }

        /* conditional format window  (@author Beatriz Ferreira <1160701@isep.ipp.pt>)
        * changed by David Blanquett <1161018@isep.ipp.pt>
        * */
        conditionalFormat.addClickHandler(event -> {
            if (activeCell != null) {

                windowRange.open();

                //                 ConditionalManager.getInstace().newInstane();
//                windowFormat.open();
//                String cellInfo = "Atual Cell: " + activeCell.toString() + " Content:" + activeCell.getContent();
//                MaterialToast.fireToast(cellInfo);
            }

        });

        startConditional.addClickHandler(event -> {

            windowRange.close();
            windowFormat.open();

            List<Cell> range = new ArrayList<>();

            String _cell = cellFormat.getText();

            if (_cell.contains("_cell")) {

                range = getRange(_cell);

            } else if (!filterStartCellRange.getText().isEmpty() && !filterEndCellRange.getText().isEmpty()) {

                String startCell = filterStartCellRange.getText();
                String endCell = filterEndCellRange.getText();

                if (cellsAreValid(startCell, endCell)) {

                    range = getRange(startCell, endCell);

                } else {

                    Window.alert("Invalid regex for cells");
                    Window.alert("Using " + activeCell.toString() + " as range.");
                    range.add(activeCell);
                }

            } else {

                Window.alert("Using " + activeCell.toString() + " as range.");
                range.add(activeCell);

            }

            //    Window.alert("TOTAL: " + range.size());
            ConditionalManager.getInstace().setRangedCells(range);

//            /* __ DEBUG __*/
//            for( Cell c : range){
//                Window.alert("RANGE: @ " + c.toString());
//            }
//            /* __ /DEBUG __*/
        });

        /* conditional format window  (@author Beatriz Ferreira <1160701@isep.ipp.pt>)*/
        confirmConditional.addClickHandler(event -> {
            if (activeCell != null) {

                BinaryOperator operator = null;
                String n = null;

                if (!numberConditional.getText().isEmpty()) {
                    operator = conditional.getSingleValue();
                    n = numberConditional.getValue();

                } else {

                    String expression = cellFormat2.getText();

                    operator = toOperator(expression);
                    n = toValue(expression, operator.getIdentifier());

                }

                Color cT = listColors.getSingleValue();
                Color cF = listColors2.getSingleValue();

                List<Cell> cL = ConditionalManager.getInstace().getRangedCells();

                for (Cell c : cL) {

                    ConditionalInfo info = new ConditionalInfo(cT, cF, c, operator, n);

                    ConditionalManager.getInstace().addChangedCells(c, info);

//
                }

                customTable.getView().setRedraw(true);
                customTable.getView().refresh();

                windowFormat.close();

            }
        });

        clearRange.addClickHandler(event -> {

            ConditionalManager.getInstace().newInstane();
            customTable.getView().setRedraw(true);
            customTable.getView().refresh();
            MaterialToast.fireToast("Deleted all ranges");

        });

        /* conditional format window  (@author Beatriz Ferreira <1160701@isep.ipp.pt>)*/
        confirmConditional2.addClickHandler(event -> {
            if (activeCell != null) {
                BinaryOperator operator = conditional2.getSingleValue();
                String n = numberConditional2.getValue();

                Style.BorderStyle bT = listBorder.getSingleValue();
                Style.BorderStyle bF = listBorder2.getSingleValue();

                cf.setBorderStyle(activeCell, operator, n, bT, bF, widget);

                windowFormat.close();
            }
        });

        /* conditional format window  (@author Beatriz Ferreira <1160701@isep.ipp.pt>)*/
        confirmConditional3.addClickHandler(event -> {
            if (activeCell != null) {
                BinaryOperator operator = conditional3.getSingleValue();
                String n = numberConditional3.getValue();

                Color cT = listColors3.getSingleValue();
                Color cF = listColors4.getSingleValue();

                ConditionalInfo info = new ConditionalInfo(cT, cF, activeCell, operator, n);

                ConditionalManager.getInstace().addChangedCells(activeCell, info);

                cf.setTextColor(activeCell, operator, n, cT, cF, widget);
                windowFormat.close();

            }
        });

        removeConditionalFormat.addClickHandler(event -> {
            if (activeCell != null) {

                widget.setBackgroundColor(Color.TRANSPARENT);
                widget.setTextColor(Color.BLACK);
                widget.setBorder(Style.BorderStyle.NONE.getCssName());
                setActiveCell(activeCell, widget);

                MaterialToast.fireToast("Delete Sucess!");

            }

        }
        );

        /**
         * 1090657 possible way to get currentActiveCell
         *
         */
        initWorkbook();

        // Set the visible range of the table for pager (later)
        customTable.setVisibleRange(0, 2001);

        // Configure the tables long press duration configuration.
        // The short press is when a click is held less than this duration.
        customTable.setLongPressDuration(400);

        customTable.addRowContextMenuHandler(event -> {
            // Firing Row Context will automatically select the row where it was right
            // clicked
            customTable.selectRow($(event.getRow()).asElement(), true);
            popupMenu.setSelected(event.getModel());
            // Get the PageX and getPageY
            popupMenu.setPopupPosition(event.getMouseEvent().getPageX(), event.getMouseEvent().getPageY());
            popupMenu.open();
        });

        // Added access to ToolPanel to add icon widget
        Panel panel = customTable.getScaffolding().getToolPanel();
        panel.clear();
        panel.setVisible(false);

        customTable.getTableTitle().setText("The Future Worksheet!");
        /**
         * 1090657 Ra�l Correia Adding a refresh button to see current workbook
         */
        refreshButton.addClickHandler(event
                -> {

            Boolean isEditable = WorkbookManager.getInstance().getCurrentDescription().isEditable();
            int size = customTable.getColumns().size();
            for (int i = 0; i < size; i++) {
                customTable.removeColumn(0);
            }
            Workbook wb = WorkbookManager.getInstance().getCurrentActiveWorkbook();
            if (wb != null) {

                Spreadsheet sh = wb.getActiveSpread();

                int columnNumber = 0;

                // Add the columns...
                customTable.addColumn(new SheetWidgetColumn(-1, this));
                for (int i = 0; i < sh.getColumnCount(); ++i) {

                    // Add a column for the column :-)
                    customTable.addColumn(new SheetWidgetColumn(columnNumber, this));

                    ++columnNumber;
                }

                // int rowIndex = 0;
                List<SheetCell> rows = new ArrayList<>();
                for (int k = 0; k < sh.getRowCount(); k++) {
                    rows.add(new SheetCell(sh, k));
                }
                customTable.setRowData(0, rows);
                customTable.getView().setRedraw(true);
                customTable.getView().refresh();

                if (isEditable == false) {
                    customTable.getRows().stream().forEach(x -> x.setEnabled(false));
                }
            }
        }
        );
        saveButton.addClickHandler(event -> {
            Workbook wb = WorkbookManager.getInstance().getCurrentActiveWorkbook();
            Boolean isEditable = WorkbookManager.getInstance().getCurrentDescription().isEditable();
            if (isEditable && wb != null) {
                WorkbookDescriptionDTO currentdto = WorkbookManager.getInstance().getCurrentDescription();
                WorkbookDescriptionDTO novoDTO = new WorkbookDescriptionDTO(currentdto, wb);

                WorkbooksServiceAsync wbservice = GWT.create(WorkbooksService.class);
                AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        MaterialToast.fireToast("There were problems....");
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            MaterialToast.fireToast("Saved the workbook successfully!");
                        } else {
                            MaterialToast.fireToast("Failed saving workbook.");
                        }
                    }
                };

                wbservice.saveWorkbook(novoDTO, callback);
            }
        });

        buttonRemoveWork.addClickHandler(event -> {
            Workbook wb = WorkbookManager.getInstance().getCurrentActiveWorkbook();
            Boolean isEditable = WorkbookManager.getInstance().getCurrentDescription().isEditable();
            if (isEditable && wb != null) {
                WorkbookDescriptionDTO currentdto = WorkbookManager.getInstance().getCurrentDescription();
                WorkbookDescriptionDTO novoDTO = new WorkbookDescriptionDTO(currentdto, wb);

                WorkbooksServiceAsync wbservice = GWT.create(WorkbooksService.class);
                AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        MaterialToast.fireToast("There were problems....");
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            MaterialToast.fireToast("Remove the workbook successfully!");
                        } else {
                            MaterialToast.fireToast("Failed removing workbook.");
                        }
                    }
                };
                wbservice.deleteWorkbook(novoDTO, callback);
            }
        });

        /**
         * Set Image (David Camelo <1161294@isep.ipp.pt>)
         */
        setImageButton.addClickHandler(event -> {
            setImagePanel.clear();
            SetImageForm form = new SetImageForm(setImagePanel, uiController, activeCell);
            Logger logger = Logger.getLogger("logger");
            logger.log(Level.SEVERE, "1Entering...");
            if (!uiController.validAddImageExtension(activeCell)) {
                MaterialToast.fireToast("Existing image will be overwritten!");
            }

            form.onModuleLoad();
            setImageWindow.open();
        });

        customTable.addClickHandler(event -> {
            Logger logger = Logger.getLogger("logger");
            logger.log(Level.SEVERE, "2Entering...");
            logger.log(Level.SEVERE, activeCell.getAddress().toString());

            // Hook up an error handler, so that we can be informed if the image fails
            // to load.
            image.addErrorHandler(new ErrorHandler() {
                public void onError(ErrorEvent event) {
                    MaterialToast.fireToast("An error occurred while loading.");
                }
            });

            if (!uiController.validAddImageExtension(activeCell)) {
                String path = uiController.pathOfImage(activeCell);
                logger.log(Level.SEVERE, "Path: " + path);
                image.setUrl(path);
                showImageWindow.open();
            }

        });

        removeImageButton.addClickHandler(event -> {
            uiController.removeImageExtension(activeCell);
        });

        /**
         * =============================================================================================================
         */
        
        commentButton.addClickHandler(event -> {
            insertCommentWindow.open();
            comment.getText();

        });

        saveComment.addClickHandler(event -> {
            uiController.addCommentExtension(new CommentExtension(activeCell, comment.getText()));
             if (activeCell != null) {

                widget.setBackgroundColor(Color.RED);
             
                setActiveCell(activeCell, widget);

                MaterialToast.fireToast("Comment Added!");

            }

        });

        removeCommentButton.addClickHandler(event -> {
            uiController.removeCommentExtension(activeCell);
             if (activeCell != null) {

                widget.setBackgroundColor(Color.TRANSPARENT);
          
                setActiveCell(activeCell, widget);

            

            }
        });

        openComment.addClickHandler(event -> {
            commentLabel.setText("Comment for the active cell");
            commentLabel2.setText(uiController.commentCell(activeCell));

            showCommentWindow.open();
        });

        
        
        buttonAddSpread.addClickHandler(event -> {
            Workbook wb = WorkbookManager.getInstance().getCurrentActiveWorkbook();
            int rows = Integer.parseInt(spreadLines.getText());
            int columns = Integer.parseInt(spreadColumns.getText());
            String[][] content = new String[rows][columns];
            int it = 1;
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    content[row][column] = String.valueOf(it);
                    it++;
                }
            }
            try {
                wb.addSpreadsheet(content);
            } catch (Exception ex) {
                MaterialToast.fireToast("Error adding spreadsheet!" + ex.getMessage());
            }
            if (wb != null) {
                WorkbookDescriptionDTO currentdto = WorkbookManager.getInstance().getCurrentDescription();
                WorkbookDescriptionDTO novoDTO = new WorkbookDescriptionDTO(currentdto, wb);

                WorkbooksServiceAsync wbservice = GWT.create(WorkbooksService.class);
                AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        MaterialToast.fireToast("There were problems....");
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            MaterialToast.fireToast("Saved the workbook successfully!");
                        } else {
                            MaterialToast.fireToast("Failed saving workbook.");
                        }
                    }
                };

                wbservice.saveWorkbook(novoDTO, callback);
            }
            if (wb != null) {
                int size = customTable.getColumns().size();
                for (int i = 0; i < size; i++) {
                    customTable.removeColumn(0);
                }
                int count = wb.getSpreadsheetCount();
                wb.setActiveSpread(count - 1);
                Spreadsheet sh = wb.getSpreadsheet(count - 1);

                int columnNumber = 0;

                // Add the columns...
                customTable.addColumn(new SheetWidgetColumn(-1, this));
                for (int i = 0; i < sh.getColumnCount(); ++i) {

                    // Add a column for the column :-)
                    customTable.addColumn(new SheetWidgetColumn(columnNumber, this));

                    ++columnNumber;
                }

                // int rowIndex = 0;
                List<SheetCell> rows1 = new ArrayList<>();
                for (int k = 0; k < sh.getRowCount(); k++) {
                    rows1.add(new SheetCell(sh, k));
                }
                customTable.setRowData(0, rows1);
                customTable.getView().setRedraw(true);
                customTable.getView().refresh();
            }
        });

        menuRemoveIcon.addClickHandler(event -> {
            windowRemoveSpreadsheet.open();
        });

        buttonCancelRemove.addClickHandler(event -> {
            windowRemoveSpreadsheet.close();
        });
    }

    private List<Cell> getRange(String startCell, String endCell) {

        List<Cell> cellList = new ArrayList<>(); // all Cells
        List<Cell> finalCellList = new ArrayList<>(); // all Cells

        Cell startCellImp = null;
        Cell endCellImp = null;

        Spreadsheet s = activeCell.getSpreadsheet();

        for (int i = 0; i < activeCell.getSpreadsheet().getRowCount(); i++) {

            for (Cell c : activeCell.getSpreadsheet().getRow(i)) {

                if (c.toString().equals(startCell)) {

                    startCellImp = c;
                }

                if (c.toString().equals(endCell)) {

                    endCellImp = c;
                }

                cellList.add(c);

            }

        }

        Collections.sort(cellList);

        for (Cell c : cellList) {

            if (startCell != null && endCellImp != null) {
                if (c.compareTo(startCellImp) >= 0 && c.compareTo(endCellImp) <= 0) {

                    finalCellList.add(c);

                }
            }

        }

        return finalCellList;

    }

    private boolean cellsAreValid(String startCell, String endCell) {

        String cellPattern = "[A-Z][1-9]";

        return startCell.matches(cellPattern) && endCell.matches(cellPattern);
    }

    private List<Cell> getRange(String cell) {

        List<Cell> cellList = new ArrayList<>();

        Spreadsheet s = activeCell.getSpreadsheet();

        ConditionalFormatView cf = new ConditionalFormatView();
        BinaryOperator op = toOperator(cell);
        String value = toValue(cell, op.getIdentifier());

        MaterialToast.fireToast("OPERATOR: " + op.getIdentifier() + "\nValue: " + value);

        for (int i = 0; i < activeCell.getSpreadsheet().getRowCount(); i++) {

            for (Cell c : activeCell.getSpreadsheet().getRow(i)) {

                if (c.toString().equals("A1")) {
                    //     Window.alert("Está a ecnontrar...");
                }

                if (cf.evaluate(c, op, value)) {

                    cellList.add(c);
                }
            }

        }

        return cellList;

    }

    private String toValue(String cell, String op) {

        cell.replaceAll(" ", "");

        String[] c = cell.split(op);

        return c[1];
    }

    private BinaryOperator toOperator(String cell) {

        if (cell.contains("<")) {

            if (cell.contains("=")) {

                return new LessThanOrEqual();

            }

            return new LessThan();

        } else if (cell.contains(">")) {

            if (cell.contains("=")) {

                return new GreaterThanOrEqual();

            }

            return new GreaterThan();

        } else if (cell.contains("=")) {

            return new Equal();

        }

        return new Equal();
    }

    private void startSearch(Spreadsheet sh_test, int row, int col) {

        if (col == -1) {
            Window.alert("Search Ended.");

            return;

        }

        SearchServiceAsync searchService = GWT.create(SearchService.class);
        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable throwable) {
                MaterialToast.fireToast("Impossible to search:  " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {

                    MaterialToast.fireToast("FOUND @" + activeCell.getSpreadsheet().getCell(OptionService.getInstance().getRealCol(), OptionService.getInstance().getnRow()).toString());

                    outputOfSearch.setText(outputOfSearch.getText() + sh_test.getCell(col, row).getAddress().toString() + " @SPREADSHEET: " + OptionService.getInstance().getSheetIndex() + "\n");

                    startSearch(OptionService.getInstance().getMySS(), OptionService.getInstance().getnRow(), OptionService.getInstance().getnCol());

                } else {

                    // DEBUG: MaterialToast.fireToast("Not found" );
                    startSearch(OptionService.getInstance().getMySS(), OptionService.getInstance().getnRow(), OptionService.getInstance().getnCol());

                }

            }
        };

        if (cellFilter(sh_test.getCell(col, row).getValue().getType())) {

            String formula = "";
            if (sh_test.getCell(col, row).getFormula() != null) {
                formula = sh_test.getCell(col, row).getFormula().toString();
            }

            searchService.matchByPattern(regExpressionTextBox.getText(),
                    sh_test.getCell(col, row).getContent(), formula, callback);

        } else {

            startSearch(OptionService.getInstance().getMySS(), OptionService.getInstance().getnRow(), OptionService.getInstance().getnCol());

        }

    }

    /**
     * Checks if the textboxes are empty.
     *
     * @author: David Blanquett<1161018@isep.ipp.pt>
     *
     * @param regExpressionTextBox2: REGEX
     * @param regExpressionTextBox3: REPLACE BY
     * @return: their state of emptiness
     */
    private boolean checkUIDetails(MaterialTextBox regExpressionTextBox2, MaterialTextBox regExpressionTextBox3) {

        if (regExpressionTextBox3.getText().isEmpty()) {

            MaterialToast.fireToast("WORD BOX IS EMPTY: INSERT A TOKEN");
            return false;

        } else if (regExpressionTextBox2.getText().isEmpty()) {

            MaterialToast.fireToast("REGEX BOX IS EMPTY: INSERT A REGULAR EXPRESSION");
            return false;
        }

        return true;

    }

    /**
     * Starts the search N Replace feature.
     *
     * @author: David Blanquett<1161018@isep.ipp.pt>
     *
     * @param sh_test: the active spreadsheet.
     */
    private void searchNReplace(Spreadsheet sh_test) {

        // ------------------------ TEMP. SOLUTION ------------------------------------
        Window.alert("Number os spreadSheets before:" + sh_test.getWorkbook().getSpreadsheetCount());

        String contents[][] = { // second spreadsheet
            /*    A    B    C     D   E    F     G */
            {"1", "-1", "1", "1", "2", "2", "3"}, //1
            {"5", "3", "1", "-1", "-4", "3", "2"}, // 2
            {"1", "-2", "3", "-2", "5", "-1", "-2"}};           // 3

        try {
            sh_test.getWorkbook().addSpreadsheet(contents);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Window.alert("Number os spreadSheets after:" + sh_test.getWorkbook().getSpreadsheetCount());

        // ------------------------ TEMP. SOLUTION ------------------------------------
        OptionService.getInstance().setMySS(sh_test);
        OptionService.getInstance().setMyWb(sh_test.getWorkbook());

        /* starts recursive method */
        next(0, 0, sh_test);

    }

    /**
     * Recursive method: Processes if a content of a cell( spreadsheet(col,row)
     * ) is described by a regex.
     *
     * @author: David Blanquett<1161018@isep.ipp.pt>
     *
     * @param col: the column
     * @param row: the row
     * @param sh_test: the spreadsheet
     */
    public void next(int col, int row, Spreadsheet sh_test) {

        Window.alert("COL,ROW: " + sh_test.getCell(col, row).toString()); //-- DEBUG

        /* SINCE THE METHOD IS RECURSIVE IT NEEDS A STOPPING CONDITION */
        if (col == -1) {

            Window.alert("Search N' Replaced finished.");
            return;
        }


        /* UI DETAILS */
        reg3.setText("SpreadSheet" + OptionService.getInstance().getSheetIndex() + " @ " + sh_test.getCell(new Address(col, row)).toString());
        reg1.setText(sh_test.getCell(new Address(col, row)).getContent());
        /* /UI DETAILS */


 /* CREATES THE SEARCH SERVICE */
        SearchServiceAsync searchService = GWT.create(SearchService.class
        );
        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable throwable) {
                MaterialToast.fireToast("Impossible to search:  " + throwable.getMessage());

            }

            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {

                    //         Window.alert("ENCONTROU");            //   -- DEBUG

                    /* UI DETAILS */
                    reg2.setText(regExpressionTextBox3.getText());
                    /* /UI DETAILS */

 /* Checks if the yesToAll checkbox is enabled */
                    if (OptionService.getInstance().isYesToAll()) {

                        replace(OptionType.CHANGE);

                    } else {

                        /* asks the user if he wants to replace or not. */
                        replace_window.open();
                    }

                } else {

                    MaterialToast.fireToast("Not Found"); //  --- DEBUG

                    /* SEARCHS NEXT CELL */
                    next(OptionService.getInstance().getnCol(), OptionService.getInstance().getnRow(), OptionService.getInstance().getMySS());

                }

            }
        };

        /* IF THE CELL IS FROM THE REQUIRED TYPE IT PROCESSES THE SEARCH */
        if (cellFilter(sh_test.getCell(col, row).getValue().getType())) {
            searchService.matchByPattern(regExpressionTextBox2.getText(),
                    sh_test.getCell(col, row).getContent(), "", callback);
        } else {

            //   Window.alert("Nao é do tipo"); //  -- DEBUG

            /* CHECKS NEXT CELL*/
            next(OptionService.getInstance().getnCol(), OptionService.getInstance().getnRow(), OptionService.getInstance().getMySS());

        }

    }

    /**
     * Method used to filter a Cell according to its Type.
     *
     *
     * @author: David Blanquett<1161018@isep.ipp.pt>
     * @param type: the type of the cell
     * @return: true if its the select type, otherwise it returns false.
     */
    private boolean cellFilter(Value.Type type) {

        if (OptionService.getInstance().currentSelectedType() == Value.Type.UNDEFINED) {

            return true;
        }

        return (type == OptionService.getInstance().currentSelectedType());

    }

    /**
     * Replaces the content of the cell.
     *
     * @author: David Blanquett <1161018@isep.ipp.pt>
     * @param change: if it should ne be changed or not.
     */
    private void replace(OptionType change) {


        /* UI DETAILS: RESET ELEMENTS STATE */
        cbApply.setValue(false);
        cbDontApply.setValue(false);
        replace_window.close();
        /* /UI DETAILS */

        if (change == OptionType.CHANGE) {

            updateCurrent();

        }

        /* CALLS NEXT CELL */
        next(OptionService.getInstance().getnCol(), OptionService.getInstance().getnRow(), OptionService.getInstance().getMySS());

    }

    /**
     * Updates the content of the cell.
     *
     * @author: David Blanquett <1161018@isep.ipp.pt>
     */
    private void updateCurrent() {

        try {
            getActiveCell().getSpreadsheet().getCell(new Address(OptionService.getInstance().getRealCol(), OptionService.getInstance().getnRow())).setContent(reg2.getText());
        } catch (FormulaCompilationException e) {
            e.printStackTrace();
        }

        customTable.getView().setRedraw(true);
        customTable.getView().refresh();

    }

    public pt.isep.nsheets.shared.core.Cell getActiveCell() {
        return this.activeCell;
    }

    public MaterialDataTable<SheetCell> getTable() {
        return customTable;

    }

    public class SheetCell {

        private Spreadsheet sheet;
        public int row = -1;

        public SheetCell(Spreadsheet sheet, int row) {
            this.row = row;
            this.sheet = sheet;
        }

        public pt.isep.nsheets.shared.core.Cell getCell(int column) {
            return this.sheet.getCell(column, this.row);
        }

    }

    Workbook wb;

    void initWorkbook() {

        // Test the initialization of an Workbook
        String contents[][][] = {{ // first spreadsheet
            {"10", "-9", "8", "7", "1", "2", "3"}, {"8", "7", "6", "5", "4", "3", "2"},
            {"1", "2", "3", "4", "5", "6", "7"},}};

        wb = new Workbook(contents);
        SpreadSheetSingleton.getInstance().wb = wb;
        Spreadsheet sh = wb.getSpreadsheet(0);

        int columnNumber = 0;

        // Add the columns...
        customTable.addColumn(new SheetWidgetColumn(-1, this));
        for (int i = 0; i < sh.getColumnCount(); ++i) {

            // Add a column for the column :-)
            customTable.addColumn(new SheetWidgetColumn(columnNumber, this));

            ++columnNumber;
        }

        // int rowIndex = 0;
        List<SheetCell> rows = new ArrayList<>();
        for (int k = 0; k < sh.getRowCount(); k++) {
            rows.add(new SheetCell(sh, k));
        }
        customTable.setRowData(0, rows);

    }

    public void setActiveCell(Cell cell, MaterialLabel widget) {

        this.widget = widget;
        this.activeCell = cell;

        /**
         * Check if cell is already locked
         *
         * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
         */
        //public lockDTO(Workbook wb, String title, int row, int column)
        lockDTO dto = new lockDTO(wb, activeCell.getSpreadsheet().getTitle(), activeCell.getAddress().getRow(), activeCell.getAddress().getColumn());

        SessionServiceAsync sessionService = GWT.create(SessionService.class);

        AsyncCallback<Boolean> callback3 = new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error on session service " + caught.getMessage());
            }

            @Override
            public void onSuccess(Boolean result) {
                MaterialToast.fireToast("Sucess");
            }

        };

        //using service to lock and unlock Cell
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable throwable) {
                MaterialToast.fireToast("Error on session " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Void result) {

                MaterialToast.fireToast("Sucess");
            }
        };

        Timer t = new Timer() {
            @Override
            public void run() {
                sessionService.unlockCell(dto, callback);
            }
        };

        sessionService.lockCell(dto, callback);

        System.out.println("Locked Cell");

        t.schedule(dto.timer() * 1000); // 10 secs

        System.out.println("Unlocked Cell");

        this.customTable.getTableTitle().setText(cell.toString() + ": " + cell.getContent());
        this.firstBox.setText(cell.getContent());
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        SessionServiceAsync sessionService = GWT.create(SessionService.class);

        //method to open a workbook session
        AsyncCallback<Void> callback2 = new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable throwable) {
                MaterialToast.fireToast("Error on session " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Void aVoid) {
                MaterialToast.fireToast("Sucess on the creation of new session");
            }
        };

        sessionService.openSession(wb, callback2);

        // table.getTableTitle().setText("The Future Worksheet!");
    }

    /**
     * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
     */
    @Override
    protected void onDetach() {

        SessionServiceAsync sessionService = GWT.create(SessionService.class);

        //method to open a workbook session
        AsyncCallback<Void> callback2 = new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable throwable) {
                MaterialToast.fireToast("Error on session " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Void aVoid) {
                MaterialToast.fireToast("Sucess on the creation of new session");
            }
        };

        sessionService.closeSession(wb, callback2);

        super.onDetach();
    }

}
