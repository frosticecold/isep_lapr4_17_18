package pt.isep.nsheets.client.application.Lapr.Red.n1161018.Search;

/**
 * OptionType.java
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 06/06/2018
 */
public enum OptionType {

    CHANGE,DONT_CHANGE,UNDEFINED;

    public static boolean sameType(OptionType t1, OptionType t2){

        if( t1 == t2){
            return true;
        }else{

            return false;
        }

    }
}
