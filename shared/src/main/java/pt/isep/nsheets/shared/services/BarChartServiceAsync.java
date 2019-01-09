/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pt.isep.nsheets.shared.core.Address;

/**
 *
 * @author pedro
 */
public interface BarChartServiceAsync {

    void getName(BarChartCreatorDTO bDto, AsyncCallback<String> callback);

    void isRowLabel(AsyncCallback<Boolean> callback);

    void getFirstAddress(AsyncCallback<Address> callback);

    void getSecondAddress(AsyncCallback<Address> callback);
}
