package pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence;

import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;

import java.util.Properties;

/**
 * @author Rui Almeida<1160818>
 */
public class GetPropertiesService {

    /**
     * Gets the persistence settings
     * @return the settings
     */
    public static PersistenceSettings getPersistenceSettings() {

        Properties props = new Properties();

        props.put("persistence.repositoryFactory",
                "pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa.JpaRepositoryFactory");
        props.put("persistence.persistenceUnit", "lapr4.NSheetsPU");

        return new PersistenceSettings(props);
    }
}
