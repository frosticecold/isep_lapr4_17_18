package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1131485.domain;

import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

import eapli.framework.domain.AggregateRoot;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.dto.lockDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Class represents a session on a certain workbook (if its is public), in other
 * words a class that will be persisted on the database and each entry
 * represents a session on the workbook to handle with the realtime update
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
@Entity
public class Session implements AggregateRoot<Long>, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Is session active?
     */
    private boolean active;

    /**
     * The workbook that holds the session
     */
    @OneToOne
    private Workbook wb;

    /**
     * Map that will store all the locks on this session
     *
     */
    private Map<lockDTO, Boolean> locks;


    /**
     * FOR ORM Only
     */
    protected Session() {

    }

    /**
     * Full construct
     */
    public Session(Workbook givenWorkbook) {
        locks = new HashMap<>();
        this.active = true;
        this.wb = givenWorkbook;
    }

    /**
     * Compares this object with another fo any type
     *
     * @param other
     * @return
     */
    @Override
    public boolean sameAs(Object other) {

        boolean flag = false;

        if (other instanceof Session) {
            if (this.is(((Session) other).id())) {
                flag = true;
            }
        }

        return flag;
    }

    /**
     * Compares id
     *
     * @param id the identity to check
     * @return
     */
    @Override
    public boolean is(Long id) {

        boolean flag = false;

        if (id.equals(this.id)) {
            flag = true;
        }

        return flag;
    }

    /**
     * Method that returns the "active" boolean to check if session is currently active
     *
     * @return
     */
    public boolean isActive() {

        return this.active;
    }

    /**
     * Returns id
     *
     * @return
     */
    @Override
    public Long id() {
        return this.id;
    }

    /**
     * Method to add or update a lock of the Cell
     *
     * @param dto
     * @return
     *
     */
    public void addNewLock(lockDTO dto) {

        if(!this.locks.containsKey(dto)) {
            this.locks.put(dto, true);
        }
        else {
            this.locks.replace(dto, true);
        }
    }

    /**
     * Method to disable an existing lock
     *
     * @param dto
     *
     * @return an integer for the failed cause, -1 - doesnt exist on map, 0- is already disabled, 1- on sucess
     */
    public void disableLock(lockDTO dto) {

        this.locks.replace(dto, true, false); //disable the wanted lock

    }

    /**
     * Method that closes this session
     */
    public void closeSession() {

        this.active = false;
    }

    /**
     * Returns current list of locks
     */
    public Map<lockDTO, Boolean> locks() {

        return this.locks;
    }

    /**
     * Override equals
     */
    @Override
    public boolean equals(Object other) {

        boolean flag = false;

        if(other instanceof Session) {
            Session tmp = (Session) other;
            if(tmp.wb.equals(this.wb)) {
                flag = true;
            }
        }

        return flag;
    }

    /**
     * Reopens session
     */
    public void opensSession() {

        this.active = true;
    }
}
