/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

/**
 *
 * @author Joao Rocha <1161838>
 */
public class WorkbookDescService {
    
    public WorkbookDescriptionDTO getWorkbookDescription(WorkbookDescriptionDTO dto){
        return new WorkbookDescriptionDTO();
    }
}
