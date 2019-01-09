/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.lapr4.blue.s3.ipc.n1150344.profile;

import com.google.gwt.user.client.ui.FlexTable;
import com.gwtplatform.mvp.client.UiHandlers;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public interface ProfileUiHandlers extends UiHandlers {

    void onSaveChanges(String username, String name, String imagePath);

    void onDeleteAccount();

    void loadUsers(FlexTable table);
}
