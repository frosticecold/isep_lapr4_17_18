**Carlos Açoreira** (1151186) - Sprint 1 - Lang04.1
===============================
# 1. General Notes

The Language should be close to Visual Basic

# 2. Requirements

Lang04.1-The application should have an option to launch a wizard to aid the user in calling functions in formulas.
This new window should display a list of possible functions.
The construction of this list should be made dynamically based on the self-description of the functions.
When a function is selected in the list its syntax should be displayed in a edit box.
The "syntax" should include the name of the function and its parameters.
For example, for the factorial function, that only has one parameter, the following text should be displayed in the edit box "= FACT(Number)".
The window should also contain an area to display a text describing the selected function (i.e., help text).
The window should have an "Apply" and a "Cancel" button.
If the user selects the "Apply" button the text of the syntax of the function should be written in the "formula bar".

## Proposal
UC 1 - As an end user i want to access all availble functions.

UC 2 - As an end user i want to select what language to write my function in.

UC 3 - As an end user i want to be able to edit the function in the wizzard.

UC 4 - As an end user i want to be able to automaticly put the function in the formul bar.
# 3. Analysis

For this feature, since it is the first one to developed I need to:

- Understand how the application works and also understand the key aspects of GWT, since it is the main technology behind the application  



# 3.1 Project Structure
**Modules**. From the pom.xml file we can see that the application is composed of 5 modules:  
- **server**. It is the "server part" of the web application.  
- **shared**. It contains code that is shared between the client (i.e., web application) and the server.   
- **nsheets**. It is the web application (i.e., Client).  
- **util**. This is the same module as the one of EAPLI.  
- **framework**. This is the same module as the one of EAPLI.

My code will reside in the shared and on the web folder since it is an utility for the client that needs to get information from the server.
## 3.2. Analysis Diagrams
**Use Cases**

This use case description is very simple, there is no need to explain more than the image.

![Use Cases](BasicWizard.png)


# 4. Design

## 4.1. Tests

## 4.2. Requirements Realization

## 4.3. Classes

## 4.4. Design Patterns and Best Practises

# 5. Implementation

**For US1**

**For US2**

**Code Organization**

# 6. Integration/Demonstration

# 7. Final Remarks

# 8. Work Log

Commits:

- [Started documentation](https://bitbucket.org/lei-isep/lapr4-18-2dc/commits/9fa3e87576ae529cf1bdcc5e8710c21ced506ce5)

- [Final UI implementation](https://bitbucket.org/lei-isep/lapr4-18-2dc/commits/fec2e628b0dabe6473bf1bb2e981f002334f00f2)
