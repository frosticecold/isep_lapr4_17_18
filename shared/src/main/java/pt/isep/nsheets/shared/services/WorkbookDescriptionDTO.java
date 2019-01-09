package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.io.Serializable;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.WorkbookDTO;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1090657.ipc.AccessList;

@SuppressWarnings("serial")
public class WorkbookDescriptionDTO implements IsSerializable, Serializable {

    private static final long serialVersionUID = -2268051510053749620L;
    private String name;
    private String description;
    private WorkbookDTO workbook;
    private AccessList listOfAccess;
    private String ownerEmail;

    private transient Boolean isEditable = true;

    public WorkbookDescriptionDTO(WorkbookDescriptionDTO currentDto, Workbook workbook) {
        this.name = currentDto.name;
        this.description = currentDto.description;
        this.workbook = workbook.toDTO();
        this.listOfAccess = currentDto.listOfAccess;
    }

    public WorkbookDescriptionDTO(String name, String description, Workbook workbook, AccessList access, String ownerEmail) {
        this.name = name;
        this.description = description;
        this.workbook = workbook.toDTO();
        this.listOfAccess = access;
        this.ownerEmail = ownerEmail;
    }

    public WorkbookDescriptionDTO(String name, String description, WorkbookDTO workbook) {
        this.name = name;
        this.description = description;
        this.workbook = workbook;
    }

    public WorkbookDescriptionDTO(String name, String description, String ownerEmail) {
        this.name = name;
        this.description = description;
        this.workbook = new WorkbookDTO();
        this.listOfAccess = new AccessList();
        this.ownerEmail = ownerEmail;
    }

    // It is mandatory to have a default constructor with no arguments to be serializable!
    public WorkbookDescriptionDTO() {
        this.name = "";
        this.description = "";
        this.workbook = new WorkbookDTO();
        this.listOfAccess = new AccessList();
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public WorkbookDTO getWorkbook() {
        return this.workbook;
    }

    public AccessList getListOfAccess() {
        return listOfAccess;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setReadOnlyAccess() {
        this.isEditable = false;
    }

    public Boolean isEditable() {
        return isEditable;
    }
}
