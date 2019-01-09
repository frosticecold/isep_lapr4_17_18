/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package pt.isep.nsheets.client.application.workbook;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import pt.isep.nsheets.client.application.ApplicationPresenter;
import pt.isep.nsheets.client.event.SetPageTitleEvent;
import pt.isep.nsheets.client.place.NameTokens;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.services.SessionService;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.services.SessionServiceAsync;
import pt.isep.nsheets.shared.services.ExportToCSVService;
import pt.isep.nsheets.shared.services.ExportToCSVServiceAsync;
import pt.isep.nsheets.shared.services.ExportToXMLService;
import pt.isep.nsheets.shared.services.ExportToXMLServiceAsync;


public class WorkbookPresenter extends Presenter<WorkbookPresenter.MyView, WorkbookPresenter.MyProxy> {
    PlaceManager placeManager;
    ExportToCSVServiceAsync exportService;
    ExportToXMLServiceAsync export;
    SessionServiceAsync sessionService;

    @Inject
    WorkbookPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);
        this.placeManager = placeManager;
//        view.getExportWorkbookcsvButton().addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                exportService = GWT.create(ExportToCSVService.class);
//                AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
//                    @Override
//                    public void onFailure(Throwable caught) {
//                        MaterialToast.fireToast("Error exporting to CSV " + caught.getMessage());
//                    }
//
//                    @Override
//                    public void onSuccess(Boolean result) {
//                        if (result == true) {
//                            MaterialToast.fireToast("Error exporting to CSV ");
//                        } else {
//                            MaterialToast.fireToast("The exportation was made with success");
//                        }
//                    }
//
//                };
//                exportService.changeSeparator(view.getFieldSeparator().getText(), callback);
////                exportService.exportWorkbookToCSV(wb, view.getFileName2().getText(), callback);
//            }
//        });
//        view.getExport2Button().addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                exportService = GWT.create(ExportToCSVService.class);
//                AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
//                    @Override
//                    public void onFailure(Throwable caught) {
//                        MaterialToast.fireToast("Error exporting to CSV " + caught.getMessage());
//                    }
//
//                    @Override
//                    public void onSuccess(Boolean result) {
//                        if (result == true) {
//                            MaterialToast.fireToast("Error exporting to CSV ");
//                        } else {
//                            MaterialToast.fireToast("The exportation was made with success");
//                        }
//                    }
//
//                };
//                exportService.changeSeparator(view.getFieldSeparator1().getText(), callback);
//                MaterialToast.fireToast("abcd");
////                exportService.exportPartOfSpreadSheetToCSV(wb, view.getFileName2().getText(), view.getMinLinhas().getText(), view.getMaxLinhas().getText(), view.getMinColunas().getText(), view.getMaxColunas(), callback);
//            }
//        });
        view.getExportXml1Button().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                export = GWT.create(ExportToXMLService.class);
                AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        MaterialToast.fireToast("Error exporting to XML " + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result == true) {
                            MaterialToast.fireToast("Error exporting to XML ");
                        } else {
                            MaterialToast.fireToast("The exportation was made with success");
                        }
                    }

                };
                export.changetag1(view.gettag1().getText(), callback);
                export.changetag2(view.gettag2().getText(), callback);
                export.changetag3(view.gettag3().getText(), callback);
                export.changetag4(view.gettag4().getText(), callback);
                export.changetag5(view.gettag5().getText(), callback);
                export.changetag6(view.gettag6().getText(), callback);
//                export.exportWorkbookToXML(wb, view.getFileName2().getText(), callback);
            }
        });
                view.getExportXml2Button().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                export = GWT.create(ExportToXMLService.class);
                AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        MaterialToast.fireToast("Error exporting to XML " + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result == true) {
                            MaterialToast.fireToast("Error exporting to XML ");
                        } else {
                            MaterialToast.fireToast("The exportation was made with success");
                        }
                    }

                };
                export.changetag1(view.getTag1().getText(), callback);
                export.changetag2(view.getTag2().getText(), callback);
                export.changetag3(view.getTag3().getText(), callback);
                export.changetag4(view.getTag4().getText(), callback);
                export.changetag5(view.getTag5().getText(), callback);
                export.changetag6(view.getTag6().getText(), callback);
//                export.exportWorkbookToXML(wb, view.getFileName2().getText(), callback);
            }
        });
                   view.getExportXmlButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                export = GWT.create(ExportToXMLService.class);
                AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        MaterialToast.fireToast("Error exporting to XML " + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result == true) {
                            MaterialToast.fireToast("Error exporting to XML ");
                        } else {
                            MaterialToast.fireToast("The exportation was made with success");
                        }
                    }

                };
                export.changetag1(view.get1Tag().getText(), callback);
                export.changetag2(view.get2Tag().getText(), callback);
                export.changetag3(view.get3Tag().getText(), callback);
                export.changetag4(view.get4Tag().getText(), callback);
                export.changetag5(view.get5Tag().getText(), callback);
                export.changetag6(view.get6Tag().getText(), callback);
//                export.exportWorkbookToXML(wb, view.getFileName2().getText(), callback);
            }
        });
        
    }


    @Override
    protected void onReset() {
        super.onReset();

        getView().getFirstButton().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                // Let's test formulas...
//				String source=getView().getFirstBox().getText();
//
//				// Fetches a cell
//				Workbook workbook = new Workbook(1);
//				Spreadsheet sheet = workbook.getSpreadsheet(0);
//				Cell cell = sheet.getCell(new Address(0, 0));
//
//				Formula formula;
//				String result="";
//				try {
//					formula = FormulaCompiler.getInstance().compile(cell, source);
//
//					Expression expression = formula.getExpression();
//					result="Formula: " + expression + " = " + expression.evaluate();
//
//				} catch (FormulaCompilationException YellowExtension) {
//					// TODO Auto-generated catch block
//					//YellowExtension.printStackTrace();
//					result=YellowExtension.getMessage();
//				} catch (IllegalValueTypeException YellowExtension) {
//					// TODO Auto-generated catch block
//					// YellowExtension.printStackTrace();
//					result=YellowExtension.getMessage();
//				} finally {
//					getView().getResultLabel().setText(result);
//				}

                // Example on how to jump to another place
//				PlaceRequest request = new PlaceRequest.Builder().nameToken(NameTokens.about)
//						.with("name", result).build();
//
//				placeManager.revealPlace(request);
            }

        });

    }

    @Override
    protected void onReveal() {
        super.onReveal();

        SetPageTitleEvent.fire("Workbook", "The current Workbook", "", "", this);
    }

//    @Override
//    public void exportWorkbookToCSV(Workbook wb, String fileName) {
//        exportService = GWT.create(ExportToCSVService.class);
//        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                MaterialToast.fireToast("Error exporting to CSV " + caught.getMessage());
//            }
//
//            @Override
//            public void onSuccess(Boolean result) {
//                if (result == true) {
//                    MaterialToast.fireToast("Error exporting to CSV ");
//                } else {
//                    MaterialToast.fireToast("The exportation was made with success");
//                }
//            }
//
//        };
//        exportService.exportWorkbookToCSV(wb, fileName, callback);
//    }
//    @Override
//    public void changeLineSeparator(String a) {
//        exportService = GWT.create(ExportToCSVService.class);
//        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                MaterialToast.fireToast("Error changing the line separator" + caught.getMessage());
//            }
//
//            @Override
//            public void onSuccess(Boolean result) {
//                if (result == true) {
//                    MaterialToast.fireToast("Error changing the line separator ");
//                } else {
//                    MaterialToast.fireToast("The change was made");
//                }
//            }
//
//        };
//        exportService.changeSeparator(a, callback);
//    }
//
//    @Override
//    public void exportWorkbookToXML(Workbook wb, String fileName) {
//        exportService = GWT.create(ExportToXMLService.class);
//        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                MaterialToast.fireToast("Error exporting to XML " + caught.getMessage());
//            }
//
//            @Override
//            public void onSuccess(Boolean result) {
//                if (result == true) {
//                    MaterialToast.fireToast("Error exporting to XML ");
//                } else {
//                    MaterialToast.fireToast("The exportation was made with success");
//                }
//            }
//
//        };
//        export.exportWorkbookToXML(wb, fileName, callback);
//    }
//    @Override
//    public void exportSpreadSheetToCSV(Spreadsheet wb, String fileName) {
//        exportService = GWT.create(ExportToCSVService.class);
//        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                MaterialToast.fireToast("Error exporting to CSV " + caught.getMessage());
//            }
//
//            @Override
//            public void onSuccess(Boolean result) {
//                if (result == true) {
//                    MaterialToast.fireToast("Error exporting to CSV ");
//                } else {
//                    MaterialToast.fireToast("The exportation was made with success");
//                }
//            }
//
//        };
//        exportService.exportSpreadSheetToCSV(wb, fileName, callback);
//    }
//    @Override
//    public void exportPartOfSpreadSheetToCSV(Spreadsheet wb, String fileName, int li, int lf, int ci, int cf) {
//        exportService = GWT.create(ExportToCSVService.class);
//        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                MaterialToast.fireToast("Error exporting to CSV " + caught.getMessage());
//            }
//
//            @Override
//            public void onSuccess(Boolean result) {
//                if (result == true) {
//                    MaterialToast.fireToast("Error exporting to CSV ");
//                } else {
//                    MaterialToast.fireToast("The exportation was made with success");
//                }
//            }
//
//        };
//        exportService.exportPartOfSpreadSheetToCSV(wb, fileName, li, lf, ci, cf, callback);
//    }
//    @Override
//    public void exportSpreadsheetToXML(Spreadsheet s, String fileName) {
//         export = GWT.create(ExportToXMLService.class);
//        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                MaterialToast.fireToast("Error exporting to XML " + caught.getMessage());
//            }
//
//            @Override
//            public void onSuccess(Boolean result) {
//                if (result == true) {
//                    MaterialToast.fireToast("Error exporting to XML ");
//                } else {
//                    MaterialToast.fireToast("The exportation was made with success");
//                }
//            }
//
//        };
//        export.exportSpreadSheetToXML(s, fileName, callback);
//    }
//    
//
//    @Override
//    public void exportPartOfSpreadsheetToXML(Spreadsheet wb, String fileName, int il, int fl, int ic, int fc) {
//        export = GWT.create(ExportToXMLService.class);
//        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                MaterialToast.fireToast("Error exporting to XML " + caught.getMessage());
//            }
//
//            @Override
//            public void onSuccess(Boolean result) {
//                if (result == true) {
//                    MaterialToast.fireToast("Error exporting to XML ");
//                } else {
//                    MaterialToast.fireToast("The exportation was made with success");
//                }
//            }
//
//        };
//        export.exportPartOfSpreadSheetToXML(wb, fileName, il, fl, ic, fc, callback);
//    }
    interface MyView extends View {

        MaterialButton getExportWorkbookcsvButton();

        MaterialButton getExport2Button();

        MaterialTextBox getCSVfileName();

        MaterialTextBox getFieldSeparator1();

        MaterialTextBox getFieldSeparator();

        MaterialTextBox getFieldSeparator2();

        MaterialTextBox getMinLinhas();

        MaterialTextBox getMaxLinhas();

        MaterialTextBox getMinColunas();

        MaterialTextBox getMaxColunas();

        MaterialTextBox getFirstBox();

        MaterialIcon getFirstButton();

        MaterialButton getExportButton();

        MaterialButton getExportXmlButton();

        MaterialButton getExportXml1Button();

        MaterialButton getExportXml2Button();

        MaterialTextBox getFileName1();

        MaterialTextBox gettag1();
        
       MaterialTextBox gettag2();
       MaterialTextBox gettag3();
       MaterialTextBox gettag4();
       MaterialTextBox gettag5();
       MaterialTextBox gettag6();
       MaterialTextBox getTag1();
       MaterialTextBox getTag2();
       MaterialTextBox getTag3();
       MaterialTextBox getTag4();
       MaterialTextBox getTag5();
       MaterialTextBox getTag6();
       MaterialTextBox get1Tag();
       MaterialTextBox get2Tag();
       MaterialTextBox get3Tag();
       MaterialTextBox get4Tag();
       MaterialTextBox get5Tag();
       MaterialTextBox get6Tag();
       MaterialTextBox getMinL();
       MaterialTextBox getMinC();
       MaterialTextBox getMaxL();
       MaterialTextBox getMaxC();
       
       /**
        * Filter Range of Cells - Core 03.2
        * @return 
        */
       MaterialTextBox getFilterFormula();
       MaterialTextBox getFilterStartCell();
       MaterialTextBox getFilterEndCell();
       MaterialTextBox getFilterColumn();
       MaterialButton getFilterApply();
       MaterialButton getFilterClear();
    }



@ProxyStandard
@NameToken(NameTokens.workbook)
@NoGatekeeper
interface MyProxy extends ProxyPlace<WorkbookPresenter> {
}
}
