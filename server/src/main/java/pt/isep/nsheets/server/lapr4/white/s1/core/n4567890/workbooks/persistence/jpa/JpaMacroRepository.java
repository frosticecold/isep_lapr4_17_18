package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa;

import pt.isep.nsheets.server.lapr4.blue.s1.lang.n1131485.macros.persistence.MacroRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.macro.domain.Macro;

/**
 *
 * @author Pedro Emanuel Coelho 1131485@isep.ipp.pt
 */
public class JpaMacroRepository extends NSheetsJpaRepositoryBase<Macro, Long> implements MacroRepository{
    
        JpaMacroRepository(PersistenceSettings settings){
        super(settings);
    }
}
