**Joana Oliveira** (1161261) - Sprint 1 - IPC08.1 - Public Chat
===================================================

# 1. General Notes

*In this section you should register important notes regarding your work during the sprint. For instance, if you spend significant time helping a colleague or if you work in more than one feature increment.*

# 2. Requirements

IPC08.1 - Add a Page/View to allow all online users to exchange text messages(i.e., a public that room).
The chat should be active when the app is running and the chat data erases when the program stops running.

We can further specify this textual requirements as user stories.

Proposal:

US1 - As the Product Owner I want that all users should have a public chat room to exchange text messages.


US2 - As a user of the Application I want to be able to add a page/view to exchange text messages.

# 3. Analysis

The user must be authenticated in the application

## 3.4 Analysis Diagrams

**Use Cases**
![us.png](https://bitbucket.org/lei-isep/lapr4-18-2dc/raw/406d81852047087c53a68ed442f62fcf5d0a7216/docs/green/1161261/sp1/us.png)

**Domain Model (for this feature increment)**

- **Domain Model**
![ChatService.png](https://bitbucket.org/lei-isep/lapr4-18-2dc/raw/406d81852047087c53a68ed442f62fcf5d0a7216/docs/green/1161261/sp1/chatService.png)

**System Sequence Diagrams**

For US1

![analysis.png](https://bitbucket.org/lei-isep/lapr4-18-2dc/raw/4f20b49b13ee5a7387154e6d47629cad47b25836/docs/green/1161261/sp1/analysis.png)


For US2

![analysis2.png](https://bitbucket.org/lei-isep/lapr4-18-2dc/raw/4f20b49b13ee5a7387154e6d47629cad47b25836/docs/green/1161261/sp1/analysis2.png)

# 4. Design

## 4.1. Tests

There should be test:
* ChatController
* SendMessageController
* Message
* PublicChat


**Test Coverage**

## 4.2. Requirements Realization

For US1

![design.png](https://bitbucket.org/lei-isep/lapr4-18-2dc/raw/842ea53422d01161652b8e61a955cf53cf8b5e44/docs/green/1161261/sp1/design.png)

For US2

![design2.png](https://bitbucket.org/lei-isep/lapr4-18-2dc/raw/842ea53422d01161652b8e61a955cf53cf8b5e44/docs/green/1161261/sp1/design2.png)


## 4.3. Classes

*Present and describe the major classes of you solution.*

     -ChatView.java
     -ChatView.ui.xml
     -ChatPresenter.java

In the classes above were implemented the user interface implementation of this UC.

      -ChatService.java
      -ChatServiceAsync.java
      -ChatServiceImpl.java
      -web.xml

In the classes above were implemented the needed services to provide access to the Controller.

        -ChatController.java
        -SendChatController.java
        -CreateChatController.java

## 4.4. Design Patterns and Best Practices

By memory it apply/use:

-RepositoryFactory


-DTO

# 5. Implementation

**For US**
**UI: Button for send messages**
In the ChatView.ui.xml  it's declared as element with a tag *ui:field="btnChat"*.
In the corresponding class View(ChatView) we bind that button to the corresponding widget class:

@UiField
MaterialButton btnChat;

Now, it's added the code that invokes the server to add a new message when the user clicks in the button. To implement this behavior it's used GWT such as the SetPageTitleEvent.

It choosed to provide click event globally but to simple use the click event handler of the button and connect it to a method in the ChatPresenter.

Since Presenters should only depend on a View interface it added a new method to the ChatPresenter.MyView:

interface MyView extends View {
        void setContents(PublicChatDTO contents);

        void addClickHandler(ClickHandler ch);

        String getMessage();

        void getStringEmpty();
}

Then, it implemented the *addClickHandler* in the ChatView class and call this methods in the constructor of the ChatPresenter. In the constructor the handler class the server method that adds a new messages.

**Code Organization**

It followed the recommended organization for packages:  
- Code should be added (when possible) inside packages that identify the group, sprint, functional area and author;

-For instance, it used **lapr4.green.s1.ipc.n1161261**

The code for this sprint:
Project **server**

pt.isep.nsheets.server.**lapr4.green.s1.ipc.n1161261**.chat.application: contains the controllers
 -SendChatController
 -ChatController
 -CreateChatController

pt.isep.nsheets.server.**lapr4.green.s1.ipc.n1161261**.chat.domain: contains the domain classes
 -Message
 -PublicChat
 -Chat(Interface)

pt.isep.nsheets.server.**lapr4.green.s1.ipc.n1161261**.chat.persistence: contains the persistence classes
 -ChatReportingRepository

pt.isep.nsheets.server.**lapr4.white.s1.core.n4567890**.workbooks.persistence: contains the JPA classes
 -JpaChatReportingRepository
 -JpaChatRepository

pt.isep.nsheets.server.services: contains the service implentation classes
 -ChatServiceImpl

Project **shared**
**pt.isep.nsheets.shared.services**: This class is used to return database exceptions from the server, contains the services classes :
 -ChatService
 -ChatServiceAysnc
 -MessageDTO
 -PublicChatDTO

Project **NShests**
- Updated the classes: **pt.isep.nsheets.client.aaplication.chat.ChatView** and **pt.isep.nsheets.client.aaplication.chat.ChatPresenter**  

- Updated the file: **pt.isep.nsheets.client.aaplication.chat.ChatView.ui.xml**  


# 6. Work Log

 **Monday**

Yesterday I worked on:

1. -nothing -

**Tuesday**

Yesterday I worked on:

1. Analysis of code o project

2. Analysis of the user story IPC08.1 - Public Chat

3. Do documentation

**Wednesday**

Yesterday I worked on:

1. Analysis of code o project

2. Analysis of the user story IPC08.1 - Public Chat


**Thursday**
Yesterday I worked on:

1. Start Implementation

**Friday**
1. Implementation

**Saturday**
Yesterday I worked on:
1. Implementation

2. Fix some errors with database

**Sunday**
Yesterday I worked on:
1. Implementation

2. Fix some errors with database and project

**Monday**
Yesterday I worked on:
1. The end implementation of the UC
