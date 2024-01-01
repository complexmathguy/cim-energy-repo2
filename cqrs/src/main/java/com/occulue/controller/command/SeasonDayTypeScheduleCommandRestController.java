/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.controller.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.projector.*;

import com.occulue.controller.*;

/** 
 * Implements Spring Controller command CQRS processing for entity SeasonDayTypeSchedule.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/SeasonDayTypeSchedule")
public class SeasonDayTypeScheduleCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a SeasonDayTypeSchedule.  if not key provided, calls create, otherwise calls save
     * @param		SeasonDayTypeSchedule	seasonDayTypeSchedule
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateSeasonDayTypeScheduleCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = SeasonDayTypeScheduleBusinessDelegate.getSeasonDayTypeScheduleInstance().createSeasonDayTypeSchedule( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a SeasonDayTypeSchedule.  if no key provided, calls create, otherwise calls save
     * @param		SeasonDayTypeSchedule seasonDayTypeSchedule
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateSeasonDayTypeScheduleCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateSeasonDayTypeScheduleCommand
			// -----------------------------------------------
			completableFuture = SeasonDayTypeScheduleBusinessDelegate.getSeasonDayTypeScheduleInstance().updateSeasonDayTypeSchedule(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "SeasonDayTypeScheduleController:update() - successfully update SeasonDayTypeSchedule - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a SeasonDayTypeSchedule entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID seasonDayTypeScheduleId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteSeasonDayTypeScheduleCommand command = new DeleteSeasonDayTypeScheduleCommand( seasonDayTypeScheduleId );

    	try {
        	SeasonDayTypeScheduleBusinessDelegate delegate = SeasonDayTypeScheduleBusinessDelegate.getSeasonDayTypeScheduleInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted SeasonDayTypeSchedule with key " + command.getSeasonDayTypeScheduleId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save Season on SeasonDayTypeSchedule
     * @param		command AssignSeasonToSeasonDayTypeScheduleCommand
     */     
	@PutMapping("/assignSeason")
	public void assignSeason( @RequestBody AssignSeasonToSeasonDayTypeScheduleCommand command ) {
		try {
			SeasonDayTypeScheduleBusinessDelegate.getSeasonDayTypeScheduleInstance().assignSeason( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Season", exc );
        }
	}

    /**
     * unassign Season on SeasonDayTypeSchedule
     * @param		 command UnAssignSeasonFromSeasonDayTypeScheduleCommand
     */     
	@PutMapping("/unAssignSeason")
	public void unAssignSeason( @RequestBody(required=true)  UnAssignSeasonFromSeasonDayTypeScheduleCommand command ) {
		try {
			SeasonDayTypeScheduleBusinessDelegate.getSeasonDayTypeScheduleInstance().unAssignSeason( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Season", exc );
		}
	}
	
    /**
     * save DayType on SeasonDayTypeSchedule
     * @param		command AssignDayTypeToSeasonDayTypeScheduleCommand
     */     
	@PutMapping("/assignDayType")
	public void assignDayType( @RequestBody AssignDayTypeToSeasonDayTypeScheduleCommand command ) {
		try {
			SeasonDayTypeScheduleBusinessDelegate.getSeasonDayTypeScheduleInstance().assignDayType( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign DayType", exc );
        }
	}

    /**
     * unassign DayType on SeasonDayTypeSchedule
     * @param		 command UnAssignDayTypeFromSeasonDayTypeScheduleCommand
     */     
	@PutMapping("/unAssignDayType")
	public void unAssignDayType( @RequestBody(required=true)  UnAssignDayTypeFromSeasonDayTypeScheduleCommand command ) {
		try {
			SeasonDayTypeScheduleBusinessDelegate.getSeasonDayTypeScheduleInstance().unAssignDayType( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign DayType", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected SeasonDayTypeSchedule seasonDayTypeSchedule = null;
    private static final Logger LOGGER = Logger.getLogger(SeasonDayTypeScheduleCommandRestController.class.getName());
    
}
