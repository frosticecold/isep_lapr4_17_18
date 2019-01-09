/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.lapr4.blue.s3.ipc.n1150344.profile;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public class ProfileModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindPresenter(ProfilePresenter.class, ProfilePresenter.MyView.class, ProfileView.class, ProfilePresenter.MyProxy.class);
    }
    
}
