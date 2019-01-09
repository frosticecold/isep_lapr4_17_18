/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa;

import pt.isep.nsheets.server.lapr4.blue.s1.lang.n1131485.macros.persistence.MacroRepository;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1131485.persistence.SessionRepository;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.persistence.AgendaRepository;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161838.workbooks.persistence.WorkbookRepository;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.persistence.ReminderRepository;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.persistence.EventRepository;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.persistence.NoteRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.RepositoryFactory;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.WorkbookDescriptionRepository;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.persistence.ChatReportingRepository;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.persistence.ChatRepository;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.persistence.PrivateChatRepository;
import pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.persistence.TaskRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    private PersistenceSettings settings = null;

    public PersistenceSettings setSettings(PersistenceSettings settings) {
        return this.settings = settings;
    }

    @Override
    public WorkbookDescriptionRepository workbookDescriptions() {
        return new JpaWorkbookDescriptionRepository(this.settings);
    }

    @Override
    public UserRepository users() {
        return new JpaUserRepository(this.settings);
    }

    @Override
    public EventRepository events() {
        return new JpaEventRepository(this.settings);
    }

    @Override
    public AgendaRepository agendas() {
        return new JpaAgendaRepository(this.settings);
    }

    @Override
    public ReminderRepository reminders() {
        return new JpaReminderRepository(this.settings);
    }

    @Override
    public WorkbookRepository workbooks() {
        return new JpaWorkbookRepository(this.settings);
    }

    @Override
    public ChatReportingRepository chats() {
        return new JpaChatReportingRepository(this.settings);
    }

    @Override
    public MacroRepository macros() {
        return new JpaMacroRepository(this.settings);
    }

    @Override
    public ChatRepository publicChat() {
        return new JpaChatRepository(settings);
    }

    @Override
    public PrivateChatRepository privateChat() {
        return new JpaPrivateChatRepository(settings);
    }

    @Override
    public NoteRepository notes() {
        return new JpaNoteRepository(this.settings);
    }

    @Override
    public TaskRepository tasks() {
        return new JpaTaskRepository(this.settings);
    }

    @Override
    public SessionRepository workbookSessions() {
        return new JpaSessionRepository(this.settings);
    }
}
