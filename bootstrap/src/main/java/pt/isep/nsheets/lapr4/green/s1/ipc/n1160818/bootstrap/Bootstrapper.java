package pt.isep.nsheets.lapr4.green.s1.ipc.n1160818.bootstrap;

import eapli.framework.actions.Action;
import eapli.util.Strings;
import pt.isep.nsheets.lapr4.blue.s3.ipc.n1131485.bootstrap.SessionBootstrapper;

/**
 * Application bootstrapping data app
 *
 * @author Rui Almeida<1160818>
 */
public class Bootstrapper implements Action {

    @Override
    public boolean execute() {

        /**
         * Classes to bootstrap
         */
        final Action[] actions = {new UserBootstrapper(), new ChatBootstrapper(), new WorkbookDescriptionBootstrapper()/*, new WorkbookBootstraper()*/, new NoteBootstrapper(), new ReminderBootstrapper(),new SessionBootstrapper()};
        

        /**
         * Execute all boostrapping
         */
        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("Bootstrapping " + nameOfEntity(boot));
            ret &= boot.execute();
        }

        return ret;
    }
    /**
     * Returns the entity simple name YellowExtension.g UserBoostrapper returns User
     * @param boot
     * @return
     */
    private String nameOfEntity(final Action boot) {
        final String name = boot.getClass().getSimpleName();
        return Strings.left(name, name.length() - "Bootstrapper".length());
    }
}
