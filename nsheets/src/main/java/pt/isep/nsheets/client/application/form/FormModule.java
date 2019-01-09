package pt.isep.nsheets.client.application.form;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public class FormModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(FormPresenter.class, FormPresenter.MyView.class, FormView.class, FormPresenter.MyProxy.class);
	}

}
