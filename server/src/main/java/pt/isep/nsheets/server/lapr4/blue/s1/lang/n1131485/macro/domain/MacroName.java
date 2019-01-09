package pt.isep.nsheets.server.lapr4.blue.s1.lang.n1131485.macro.domain;

import eapli.framework.domain.DomainEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Pedro Emanuel Coelho 1131485@isep.ipp.pt
 */
@Entity
public class MacroName implements DomainEntity<Long>, Serializable {

    @Id
    @GeneratedValue
    private Long id = null;

    private String name;

    // It is mandatory to have a default constructor with no arguments to be
    // serializable and for ORM!
    protected MacroName() {
        this.name = "";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean sameAs(Object other) {

        boolean r = false;

        if (other.getClass() == this.getClass()) {

            MacroName macroName = (MacroName) other;

            r = this.is(macroName.id());
        }

        return r;
    }

    @Override
    public boolean is(Long id) {
        return id.equals(this.id);
    }

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public String toString() {

        if (this.name == null) {
            return super.toString();
        } else {
            return this.name;
        }
    }

}
