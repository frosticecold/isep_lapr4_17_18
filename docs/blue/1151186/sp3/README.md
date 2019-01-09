**Carlos Açoreira** (1151186) - Sprint 3 - IPC 043
===================================================

# 1. General Notes

In the general note, this use case was not very tasking, i already had experience creating and opening files to be used in new software.

The import/export of CSV files was indeed very simple to analyse and implement, the CLs was not possible. I tried to talk with the previous developer who tried to implement this before, but we reached the conslusion that this application did not support any type of CLS files due to difference in main domain of "how" a workbook is built.   

# 2. Requirements

**IPC05.3 - Import/Export XML**

It should be possible to import and export in both formats: XML.

## Proposal

As a user i want to be abl o import or export XML files.

# 3. Analysis

The objective is simple, to be able to choose a file to upload into the main application or to download the current book.

To this a stategy of impor/export was implemented, as well a method that is asble to read and write a new file into the server.

A request as well an reponse from the the server was needed, so it was made asyncronous services to be able to have communication between server client.

- UI to support both import and export capabilities.
- A reader and a writer to be able to create or upload new files to the client.
- A controller to divide the reposnsabilties.
- Services to establish communication between client-server.

# 3.1 Project Structure

**Modules**. From the pom.xml file we can see that the application is composed of 5 modules:  
- **server**. It is the "server part" of the web application.  
- **shared**. It contains code that is shared between the client (i.e., web application) and the server.   
- **nsheets**. It is the web application (i.e., Client).  
- **util**. This is the same module as the one of EAPLI.  
- **framework**. This is the same module as the one of EAPLI.

## 3.2. Analysis Diagrams

# 4. Design

## 4.1. Tests

## 4.2. Requirements Realization

## 4.3. Classes

## 4.4. Design Patterns and Best Practises

The design patterns used are the following:
* Strategy

Best pratices used:
* Single Responsability Principle
* Dependency Inversion Principle
* High Cohesion
* Low Coupling

# 5. Implementation

## Code Organization

### Note:

# 6. Integration/Demonstration


# 7. Final Remarks

# 8. Work Log

[Iniciate the export to xml](https://bitbucket.org/lei-isep/lapr4-18-2dc/commits/2096fa8379c663a6a3cdc203ce65f19ddb3e35b2)



[Update to export CSV file format.](https://bitbucket.org/lei-isep/lapr4-18-2dc/commits/2bb78fab52065bd688ccd5a32357a0f05d820432)
