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
 * Implements Spring Controller command CQRS processing for entity VoltageLevel.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/VoltageLevel")
public class VoltageLevelCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a VoltageLevel.  if not key provided, calls create, otherwise calls save
     * @param		VoltageLevel	voltageLevel
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateVoltageLevelCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = VoltageLevelBusinessDelegate.getVoltageLevelInstance().createVoltageLevel( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a VoltageLevel.  if no key provided, calls create, otherwise calls save
     * @param		VoltageLevel voltageLevel
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateVoltageLevelCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateVoltageLevelCommand
			// -----------------------------------------------
			completableFuture = VoltageLevelBusinessDelegate.getVoltageLevelInstance().updateVoltageLevel(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "VoltageLevelController:update() - successfully update VoltageLevel - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a VoltageLevel entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID voltageLevelId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteVoltageLevelCommand command = new DeleteVoltageLevelCommand( voltageLevelId );

    	try {
        	VoltageLevelBusinessDelegate delegate = VoltageLevelBusinessDelegate.getVoltageLevelInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted VoltageLevel with key " + command.getVoltageLevelId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save HighVoltageLimit on VoltageLevel
     * @param		command AssignHighVoltageLimitToVoltageLevelCommand
     */     
	@PutMapping("/assignHighVoltageLimit")
	public void assignHighVoltageLimit( @RequestBody AssignHighVoltageLimitToVoltageLevelCommand command ) {
		try {
			VoltageLevelBusinessDelegate.getVoltageLevelInstance().assignHighVoltageLimit( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign HighVoltageLimit", exc );
        }
	}

    /**
     * unassign HighVoltageLimit on VoltageLevel
     * @param		 command UnAssignHighVoltageLimitFromVoltageLevelCommand
     */     
	@PutMapping("/unAssignHighVoltageLimit")
	public void unAssignHighVoltageLimit( @RequestBody(required=true)  UnAssignHighVoltageLimitFromVoltageLevelCommand command ) {
		try {
			VoltageLevelBusinessDelegate.getVoltageLevelInstance().unAssignHighVoltageLimit( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign HighVoltageLimit", exc );
		}
	}
	
    /**
     * save LowVoltageLimit on VoltageLevel
     * @param		command AssignLowVoltageLimitToVoltageLevelCommand
     */     
	@PutMapping("/assignLowVoltageLimit")
	public void assignLowVoltageLimit( @RequestBody AssignLowVoltageLimitToVoltageLevelCommand command ) {
		try {
			VoltageLevelBusinessDelegate.getVoltageLevelInstance().assignLowVoltageLimit( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign LowVoltageLimit", exc );
        }
	}

    /**
     * unassign LowVoltageLimit on VoltageLevel
     * @param		 command UnAssignLowVoltageLimitFromVoltageLevelCommand
     */     
	@PutMapping("/unAssignLowVoltageLimit")
	public void unAssignLowVoltageLimit( @RequestBody(required=true)  UnAssignLowVoltageLimitFromVoltageLevelCommand command ) {
		try {
			VoltageLevelBusinessDelegate.getVoltageLevelInstance().unAssignLowVoltageLimit( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign LowVoltageLimit", exc );
		}
	}
	
    /**
     * save BaseVoltage on VoltageLevel
     * @param		command AssignBaseVoltageToVoltageLevelCommand
     */     
	@PutMapping("/assignBaseVoltage")
	public void assignBaseVoltage( @RequestBody AssignBaseVoltageToVoltageLevelCommand command ) {
		try {
			VoltageLevelBusinessDelegate.getVoltageLevelInstance().assignBaseVoltage( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign BaseVoltage", exc );
        }
	}

    /**
     * unassign BaseVoltage on VoltageLevel
     * @param		 command UnAssignBaseVoltageFromVoltageLevelCommand
     */     
	@PutMapping("/unAssignBaseVoltage")
	public void unAssignBaseVoltage( @RequestBody(required=true)  UnAssignBaseVoltageFromVoltageLevelCommand command ) {
		try {
			VoltageLevelBusinessDelegate.getVoltageLevelInstance().unAssignBaseVoltage( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign BaseVoltage", exc );
		}
	}
	
    /**
     * save Substation on VoltageLevel
     * @param		command AssignSubstationToVoltageLevelCommand
     */     
	@PutMapping("/assignSubstation")
	public void assignSubstation( @RequestBody AssignSubstationToVoltageLevelCommand command ) {
		try {
			VoltageLevelBusinessDelegate.getVoltageLevelInstance().assignSubstation( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Substation", exc );
        }
	}

    /**
     * unassign Substation on VoltageLevel
     * @param		 command UnAssignSubstationFromVoltageLevelCommand
     */     
	@PutMapping("/unAssignSubstation")
	public void unAssignSubstation( @RequestBody(required=true)  UnAssignSubstationFromVoltageLevelCommand command ) {
		try {
			VoltageLevelBusinessDelegate.getVoltageLevelInstance().unAssignSubstation( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Substation", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected VoltageLevel voltageLevel = null;
    private static final Logger LOGGER = Logger.getLogger(VoltageLevelCommandRestController.class.getName());
    
}
