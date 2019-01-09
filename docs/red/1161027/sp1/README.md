**Pedro Almeida** (1161027) - Sprint 1 - Core04.1
===============================

# 1. General Notes

My feature as some problems and it´s not fully functional. Because my use case needed another use case, i saw me forced to create a way to resolve my problems.Therefore, with the minimum time that i had to read the problem, understand the program ,understand what i need to do, do it and solve the problem of the use case dependency, all of that caused to a defficient use case implementation.

# 2. Requirements

CORE 04-1:

The application should have a new menu option to launch a wizard to help the user create a bar chart. The wizard should have 2 steps. In the first step, the user should input the name of the chart and the range of cells that contains the data for the plot of the chart. The user should also select if the data is in the rows or columns of the range and if the first row or the first column are to be considered labels. In the second step the wizard should display a preview of the chart. The wizard should allow the user to move between steps 1 and 2. If the wizard is confirmed the cell in the left upper corner of the range should have a mark (e.g., icon) that indicates that the cell has a chart associated with it. A popup menu option in the cell should provide access to the chart.

Proposal:

US1 - As an user i want to select the active workbook and, after that, select a sheetspread from that workbook.

US2 - As an user i want to create a chart based on that same spreadsheet,and have the option to save that save chart and edit it.

# 3. Analysis

For this feature increment, since it is the first one to be developed in a new project I need to:

- Understand how the application works and also understand the key aspects of GWT, since it is the main technology behind the application

- Understand how the Home Page is implemented (for instance, how the UI gets the Workbook Descriptions that are displayed)

- Understand how to integrate a relational database into the project (Will be assuming JPA since it is studied in EAPLI)

## 3.1 GWT and Project Structure

**Modules**. From the pom.xml file we can see that the application is composed of 5 modules:
- **server**. It is the "server part" of the web application.
- **shared**. It contains code that is shared between the client (i.e., web application) and the server.
- **nsheets**. It is the web application (i.e., Client).
- **util**. This is the same module as the one of EAPLI.
- **framework**. This is the same module as the one of EAPLI.

From [GWT Overview](http://www.gwtproject.org/overview.html): *"The GWT SDK contains the Java API libraries, compiler, and development server. It lets you write client-side applications in Java and deploy them as JavaScript."*

Therefore:
  - The project is totally developed in Java, event for the UI parts.
  - GWT uses a technique know as "transpilation" to translate Java code to Javascript. This is totally transparent to the user
  - A GWT application is comprised of "GWT modules" (see [GWT Tutorial](http://www.gwtproject.org/doc/latest/tutorial/create.html)). These GWT modules are described in .gwt.xml files.
   The nsheets project contains a .gwt.xml file named nsheets.gwt.xml (nsheets/src/main/resources/pt/isep/nsheets/nsheets.gwt.xml). One of the important contents of the file is the specification of the entry point of the application. However, since the application uses the [GWTP framework](http://dev.arcbees.com/gwtp/) the entry point is automatically provided (no need to specify it in the .gwt.xml file). In this case what is specified is the GIN client module pt.isep.nsheets.client.gin.ClientModule:

	    <extend-configuration-property name="gin.ginjector.modules"
                                   value="pt.isep.nsheets.client.gin.ClientModule"/>

   It is from this **ClientModule** that the application starts.
   Another important content of a .gwt.xml file is setting the paths for translatable code, .i.e., java code that should be translated to javascript. Usually the default source path is the client subpackage underneath where the .gwt.xml File is stored. In this case every code inside package pt.isep.nsheets.client and pt.isep.nsheets.shared will be translated to javascript.

	<!-- Specify the paths for translatable code                    -->
    <source path='client'/>
    <source path='shared'/>

   The shared package is where shared code between server and client should reside. See [GWT - What to put in the shared folder?](https://stackoverflow.com/questions/5664601/gwt-what-to-put-in-the-shared-folder?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa) and also [using GWT RPC](http://www.gwtproject.org/doc/latest/tutorial/RPC.html).

   In this project the shared, server and client (i.e, nsheets) code are separated also in Maven modules (but they could all be in the same project/maven module).

## 3.2 Application Startup and GWTP

As described before the entry point for the application is the class **pt.isep.nsheets.client.gin.ClientModule**.

GWTP follows the MVP (Model-View-Presenter) pattern. It uses [GIN dependency injection](http://dev.arcbees.com/gwtp/core/presenters/gin-bindings.html) to put together the parts of each MVP. How the GWTP structures the application and uses GIN to bind all the required elements is described in [GWTP Beginner's Tutorial](http://dev.arcbees.com/gwtp/tutorials/index.html).

We can see that **ClientModule** installs the base presenter of the application:

	    install(new ApplicationModule());

The **ApplicationModule** module install all the other modules of the application:

	    install(new HomeModule());
		install(new MenuModule());
		install(new AboutModule());
		install(new WorkbookModule());

Each module represents an MVP page in the application.

In this MVP pattern each presenter defines a specific interface that is use to communicate with the UI (i.e., the View). Therefore the presenter can be fully isolated from dependencies related to the UI. For instance, the View interface that is defined by the ApplicationPresenter only has one method:

	interface MyView extends View {
    		void setPageTitle(String title, String description, String link, String specification);
    }

In this specific case the only type that is "shared" between Presenter and View is the String.

The View class is where all the UI code should be implemented. In GWT it is possible to create UI elements programmatically (see [GWT Build the UI](http://www.gwtproject.org/doc/latest/tutorial/buildui.html)). The UI can also be described in .ui.xml files using [UIBinder](http://www.gwtproject.org/doc/latest/DevGuideUiBinder.html). The NSheets project is using [GWT Material Design](https://github.com/GwtMaterialDesign/gwt-material) and therefore all the UI widgets are from that library.

In the case of the Application module we can see that there is a ApplicationView.ui.xml. This file declares some widgets. The attribute ui:field can be used to specify an id that can be then used to bind that element to a class in the code. For instance, in ApplicationView.ui.xml:

	<m:MaterialPanel ui:field="panel">
		<m:MaterialLabel ui:field="title" text="NSheets" fontSize="2.3em"/>
		<m:MaterialLabel ui:field="description" text="A Sophisticated Web Spreadsheet Application." fontSize="1.1em"/>
	</m:MaterialPanel>

It is set the ui:field attribute for two existing labels. In the code (ApplicationView.java) one can bind to Widgets classes. For instance:

	@UiField
    MaterialLabel title, description;

Then we can use this instances to access the widgets link in:

	@Override
	public void setPageTitle(String title, String description, String link, String specification) {
        this.title.setText(title);
        this.description.setText(description);
        new MaterialAnimation().transition(Transition.BOUNCEINLEFT).animate(this.title);
        new MaterialAnimation().transition(Transition.BOUNCEINLEFT).animate(this.description);
    }

## 3.3 Server and RPC

The Home page displays what seems to be Workbooks that should reside in the server.

In the method **onReveal** the Home presenter invokes a WorkbookService asynchronously. It uses the base communication mechanism of GWT called [GWT RPC](http://www.gwtproject.org/doc/latest/tutorial/RPC.html).

Basically, it requires the definition of an interface for the service. In this case:

	@RemoteServiceRelativePath("workbooksService")
	public interface WorkbooksService extends RemoteService {
		ArrayList<WorkbookDescriptionDTO> getWorkbooks();
	}

Note: The @RemoteServiceRelativePath annotation associates the service with a default path relative to the module base URL.

When an RPC is invoked since it is always executed asynchronously we have to prove a callback:

	// Make the call to the stock price service.
	workbooksSvc.getWorkbooks(callback);

The callback is simple a class that provides two methods, one for a successful result and the other for a failure:

	// Set up the callback object.
	AsyncCallback<ArrayList<WorkbookDescriptionDTO>> callback = new AsyncCallback<ArrayList<WorkbookDescriptionDTO>>() {
		public void onFailure(Throwable caught) {
			// TODO: Do something with errors.
		}
		public void onSuccess(ArrayList<WorkbookDescriptionDTO> result) {
			refreshView(result);
		}
	};

Since the interface is code that must be accessed by both server and client code it should reside in the **shared** project.

The interface must be implemented in the **server**. The implementation can be very simple, like the one presented in the project. In this case the server simply returns always the same objects:

	@Override
	public ArrayList<WorkbookDescriptionDTO> getWorkbooks() {
	    ArrayList<WorkbookDescriptionDTO> workbooks = new ArrayList<WorkbookDescriptionDTO>();
	    WorkbookDescriptionDTO wb=new WorkbookDescriptionDTO("workbook1", "Este workbook contem uma lista...");
	    workbooks.add(wb);
		WorkbookDescriptionDTO wb2=new WorkbookDescriptionDTO("workbook notas", "Este workbook contem notas de disciplinas...");
	    workbooks.add(wb2);
		return workbooks;
	}

Since the service is a servlet it must be declared in the **web.xml** file of the project (see file nsheets/src/main/webapp/WEB-INF/web.xml).

	<!-- Servlets for the workbooks -->
	<servlet>
		<servlet-name>workbooksServiceServlet</servlet-name>
		<servlet-class>pt.isep.nsheets.server.services.WorkbooksServiceImpl</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>workbooksServiceServlet</servlet-name>
		<!-- The first "part" of the url is the name of the GWT module as in "rename-to" in .gwt.xml -->
		<url-pattern>/nsheets/workbooksService</url-pattern>
	</servlet-mapping>


## 3.4 Analysis Diagrams

Before understaing my use case, i needed to undestand the flow of the application itself.

* A workbook has a list of spreadsheets ,and a spreadsheet belongs to a workbook.

* A workbook has a list of cells, and each cell belongs to a workbook.

* A cell has an address.

* An address consists of a row and a column, both integers.


# 4. Design

This shoud be the real proposal to solve the problem:

![Analysis SD](sd.png)

However,because of all the tasks that we were asked to do in a short amount of time, the design is not optimal. Although is similar to what is presented, there are some major flaws  on the code itself.
To obtain get workbook that is active, i have created a singleton that has a workbook on it.
With that singleton, on the **workbookview** class, whenever the class was loaded, the workbook that was active was send to that singleton and, with that, i could get the current spreadsheet of the active workbook.
Essentially, the singleton worked as a sort of a database because the use case
that was needed to get the current workbook was not implemented and i was forced to create a soslution to solve that problem, and that´s the reason why the use case is so lackluster, because i have lost the majority of the time undersating the problem, the program and arranje a solution for the problem caused by the use case that was not implemented.

## 4.1. Tests
Unfortunatly, i was unable to do unit tests for this iteration.However,all methods were tested on the UI.

## 4.3. Classes

The solution that i presented in the sequence diagram is the best one so i think that is better to explain that one instead:

 * There is an interface called **chartcreator**.This interface exists because in the future there are many types of charts, and to make this more modular as possible i thought that the existance of a generic interface that was implemented by the classes that created the charts.

* There is a class called **barChartGenerator** that implements the interface talked before. This class implements the method that the interface has, that create a matrix with the information needed to create a chart.

* **ChartWizzardController** is the controller of the use case, that communicates with the ServiceImpl class.

* **The ServiceImpl** class implements the interfce service, that has all the methods to be overriten.

* For the UI, we have the **chartWizzardModule**, **ChartWizzardView** and **ChartWizzardPresenter**, that operates as described above.

## 4.4. Design Patterns and Best Practices

By memory we apply/use:
- Singleton
- DTO
- MVP

* The singleton Pattern was applyed to have the current workbook, because that use case was not implelented.

* The DTO practise was used to present information to the UI because the GWT don´t let us use classes that are being presisted on the database.

# 5. Final Remarks

*In this section present your views regarding alternatives, extra work and future work on the issue.*

In the future, it will be necessary another class to create the other type of chart.

# 6. Work Log

Commits most significative:

[use case Core04.1:add UI comunication Issue#10](https://bitbucket.org/lei-isep/lapr4-18-2dc/commits/6b87d64aafa17695cd73ff71919dbff71280d6b0#chg-nsheets/src/main/java/pt/isep/nsheets/client/application/chartWizzard/ChartWizzardView.java)

[model implementation](https://bitbucket.org/lei-isep/lapr4-18-2dc/commits/d3267cce8392efea8cc35887948e25df66dbf5ec)

[use case Core04.1:UI refinment Issue#10](https://bitbucket.org/lei-isep/lapr4-18-2dc/commits/fb0cf6037348a0d9fa975aff65160da8a29bb4e8)

[Core 04.1 #10-add singleton to have active workbook](https://bitbucket.org/lei-isep/lapr4-18-2dc/commits/27ed5092a3d08c900e4c304eb403907ceaccd255)
