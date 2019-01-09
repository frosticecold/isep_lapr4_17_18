package pt.isep.nsheets.lapr4.green.s1.ipc.n1160818.bootstrap;

/**
 *
 * @author Rui Almeida<1160818>
 */
 @SuppressWarnings("squid:S106")
public class Bootstrap {

        public static void main(String[] args) {
            System.out.println("======================================");
            System.out.println("Bootstrapping nSheets data");
            System.out.println("(C) 2018");
            System.out.println("======================================");

            new Bootstrapper().execute();

            System.out.println("======================================");
            System.out.println("Bootstrap data done.");
            System.out.println("======================================");

            System.exit(0);
        }
}
