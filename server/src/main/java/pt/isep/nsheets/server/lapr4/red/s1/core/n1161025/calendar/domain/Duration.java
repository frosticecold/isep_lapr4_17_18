package pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.domain;

import eapli.framework.domain.ValueObject;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;

/**
 *
 * @author MFerreira
 */
@Embeddable
class Duration implements ValueObject, Serializable {
    
    private Long durationInHours;
    
    public Duration(Long duration){
        this.durationInHours = duration;
    }
    
    protected Duration() {
        // for ORM
    }
    
    public Long duration(){
        return this.durationInHours;
    }

    @Override
    public String toString() {
        return "Duration: " + durationInHours + " hours.";
    }
    
    
}
