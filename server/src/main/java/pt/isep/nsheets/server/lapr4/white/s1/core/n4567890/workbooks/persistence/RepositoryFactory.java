/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence;

import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1131485.persistence.SessionRepository;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;

import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.persistence.ChatReportingRepository;
import pt.isep.nsheets.server.lapr4.blue.s1.lang.n1131485.macros.persistence.MacroRepository;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1161261.agenda.persistence.AgendaRepository;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161261.chat.persistence.ChatRepository;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161838.workbooks.persistence.WorkbookRepository;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.persistence.ReminderRepository;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.persistence.EventRepository;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.persistence.NoteRepository;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161025.privatechat.persistence.PrivateChatRepository;
import pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.persistence.TaskRepository;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

    PersistenceSettings setSettings(PersistenceSettings settings);

    WorkbookDescriptionRepository workbookDescriptions();

    UserRepository users();

    NoteRepository notes();

    EventRepository events();

    WorkbookRepository workbooks();

    ChatReportingRepository chats();

    ChatRepository publicChat();

    MacroRepository macros();

    PrivateChatRepository privateChat();

    TaskRepository tasks();

    AgendaRepository agendas();

    ReminderRepository reminders();

    SessionRepository workbookSessions();
}
