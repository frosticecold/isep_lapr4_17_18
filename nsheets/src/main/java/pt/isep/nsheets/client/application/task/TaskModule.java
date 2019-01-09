/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.task;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public class TaskModule extends AbstractPresenterModule{

    @Override
    protected void configure() {
      bindPresenter(TaskPresenter.class, TaskPresenter.MyView.class, TaskView.class, TaskPresenter.MyProxy.class);
    }
    
}
