package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain;

/**
 * @author alexandrebraganca
 */
import eapli.framework.domain.AggregateRoot;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1090657.ipc.AccessList;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

/**
 * Contact
 *
 * @author ATB
 */
/**
 * Modified on 10/06/2018
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
@Entity
public class WorkbookDescription implements AggregateRoot<Long>, Serializable {

    // ORM primary key
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    @OneToOne
    private Workbook workbook;

    private String ownerEmail = "";

    @OneToOne(cascade = CascadeType.ALL)
    private AccessList listOfAccess;

    public WorkbookDescription(String name, String description, Workbook workbook) throws IllegalArgumentException {
        if (name == null || description == null) {
            throw new IllegalArgumentException("name or description must be non-null");
        }
        this.name = name;
        this.description = description;
        this.workbook = workbook;
        this.listOfAccess = new AccessList();
        this.ownerEmail="";
    }

    public WorkbookDescription(String name, String description) throws IllegalArgumentException {
        if (name == null || description == null) {
            throw new IllegalArgumentException("name or description must be non-null");
        }
        this.name = name;
        this.description = description;
        this.workbook = new Workbook();
        this.listOfAccess = new AccessList();
        this.ownerEmail="";
    }
    //private Workbook workbookId;

    // It is mandatory to have a default constructor with no arguments to be
    // serializable and for ORM!
    public WorkbookDescription() {
        this.name = "";
        this.description = "";
        this.workbook = new Workbook();
        this.listOfAccess = new AccessList();
    }

    /**
     * public static WorkbookDescription fromDTO(WorkbookDescriptionDTO dto)
     * throws IllegalArgumentException { return new
     * WorkbookDescription(dto.getName(), dto.getDescription()); }
     *
     */
    public Workbook getWorkbook() {
        return workbook;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public AccessList getAccessList() {
        return this.listOfAccess;
    }

    public String getOwnerEmail() {
        return this.ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    /**
     * Changes WorkbookDescription name
     *
     * @param name
     * @return
     */
    public boolean changeName(String name) {
        if (name.isEmpty()) {
            return false;
        }
        this.name = name;
        return true;
    }

    /**
     * Changes WorkbookDescription description
     *
     * @param description
     * @return
     */
    public boolean changeDescription(String description) {
        if (description.isEmpty()) {
            return false;
        }
        this.description = description;
        return true;
    }

    @Override
    public String toString() {
        if (this.name == null) {
            return super.toString();
        } else {
            return this.name + " " + this.description;
        }
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof WorkbookDescription)) {
            return false;
        }

        final WorkbookDescription that = (WorkbookDescription) other;
        if (this == that) {
            return true;
        }
        if (!this.name.equals(that.name)) {
            return false;
        }
        return this.description.equals(that.description);
    }

    @Override
    public boolean is(Long id) {
        return (this.id.compareTo(id) == 0);
    }

    @Override
    public Long id() {
        return this.id;
    }

    public WorkbookDescriptionDTO toDTO() {
        return new WorkbookDescriptionDTO(this.name, this.description, this.workbook, this.listOfAccess, this.ownerEmail);
    }

    public void fromDTO(WorkbookDescriptionDTO dto) {
        this.description = dto.getDescription();
        this.name = dto.getName();
        this.listOfAccess.copyOtherAccessList(dto.getListOfAccess());
        this.workbook = new Workbook(dto.getWorkbook());
        this.ownerEmail = dto.getOwnerEmail();
    }

}
