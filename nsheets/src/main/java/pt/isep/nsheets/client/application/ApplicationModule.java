
/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package pt.isep.nsheets.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import pt.isep.nsheets.client.application.NoteMenu.NoteModule;
import pt.isep.nsheets.client.application.lapr4.blue.s3.ipc.n1150344.profile.ProfileModule;
import pt.isep.nsheets.client.application.about.AboutModule;
import pt.isep.nsheets.client.application.chat.ChatModule;
import pt.isep.nsheets.client.application.calendar.EventCalendarModule;
import pt.isep.nsheets.client.application.chartWizzard.ChartWizzardModule;
import pt.isep.nsheets.client.application.form.FormModule;
import pt.isep.nsheets.client.application.home.HomeModule;
import pt.isep.nsheets.client.application.login.LoginModule;
import pt.isep.nsheets.client.application.menu.MenuModule;
import pt.isep.nsheets.client.application.newListPage.NewListPageModule;
import pt.isep.nsheets.client.application.newNotePage.NewNotePageModule;
import pt.isep.nsheets.client.application.privateChat.PrivateChatModule;
import pt.isep.nsheets.client.application.reminder.ReminderModule;
import pt.isep.nsheets.client.application.settings.SettingsModule;
import pt.isep.nsheets.client.application.signUp.SignUpModule;
import pt.isep.nsheets.client.application.task.TaskModule;
import pt.isep.nsheets.client.application.workbook.WorkbookModule;

public class ApplicationModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new HomeModule());
        install(new MenuModule());
        install(new AboutModule());
        install(new ChartWizzardModule());
        install(new WorkbookModule());
        install(new EventCalendarModule());
        install(new TaskModule());
        install(new ChatModule());
        install(new LoginModule());
        install(new SettingsModule());
        install(new NewNotePageModule());
        install(new NewListPageModule());
        install(new FormModule());
        install(new SignUpModule());
        install(new ReminderModule());
        install(new ProfileModule());

        install(new PrivateChatModule());
        install(new NoteModule());


        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class, ApplicationPresenter.MyProxy.class);
    }
}
