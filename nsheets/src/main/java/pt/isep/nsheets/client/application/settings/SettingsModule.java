package pt.isep.nsheets.client.application.settings;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 *
 * @author João Magalhães<1160763>
 */
public class SettingsModule extends AbstractPresenterModule{

    @Override
    protected void configure() {
        bindPresenter(SettingsPresenter.class, SettingsPresenter.MyView.class, SettingsView.class, SettingsPresenter.MyProxy.class);
    }
    
}
