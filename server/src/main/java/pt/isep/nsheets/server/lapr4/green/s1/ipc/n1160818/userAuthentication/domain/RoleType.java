package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain;

import eapli.framework.domain.ValueObject;


import java.io.Serializable;

/**
 * Level of permissions a user has
 *
 * @author Rui Almeida<1160818>
 */
public enum RoleType {
    ADMIN {
        @Override
        public String toString() { return "Admin"; }
    },
    USER {
        @Override
        public String toString() { return "User"; }
    }
}