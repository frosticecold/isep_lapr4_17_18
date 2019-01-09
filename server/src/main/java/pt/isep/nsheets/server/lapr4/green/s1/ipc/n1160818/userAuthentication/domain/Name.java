package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Name implements ValueObject, Serializable {

    private String name;

    protected Name() {}

    public Name(String name) {
        if (Strings.isNullOrEmpty(name)) throw new IllegalArgumentException();

        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return this.name.equals(name1.toString());
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}
