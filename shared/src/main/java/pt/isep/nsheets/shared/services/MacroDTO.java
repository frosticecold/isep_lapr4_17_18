/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.services;

/**
 *
 * @author utilizador
 */
public class MacroDTO {

    String nome;
    String languagename;
    String command;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLanguagename() {
        return languagename;
    }

    public void setLanguagename(String languagename) {
        this.languagename = languagename;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public MacroDTO(String nome, String languagename, String command) {
        this.nome = nome;
        this.languagename = languagename;
        this.command = command;
    }
}
