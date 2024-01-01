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
 * Implements Spring Controller command CQRS processing for entity HydroPump.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/HydroPump")
public class HydroPumpCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a HydroPump.  if not key provided, calls create, otherwise calls save
     * @param		HydroPump	hydroPump
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateHydroPumpCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = HydroPumpBusinessDelegate.getHydroPumpInstance().createHydroPump( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a HydroPump.  if no key provided, calls create, otherwise calls save
     * @param		HydroPump hydroPump
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateHydroPumpCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateHydroPumpCommand
			// -----------------------------------------------
			completableFuture = HydroPumpBusinessDelegate.getHydroPumpInstance().updateHydroPump(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "HydroPumpController:update() - successfully update HydroPump - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a HydroPump entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID hydroPumpId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteHydroPumpCommand command = new DeleteHydroPumpCommand( hydroPumpId );

    	try {
        	HydroPumpBusinessDelegate delegate = HydroPumpBusinessDelegate.getHydroPumpInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted HydroPump with key " + command.getHydroPumpId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save RotatingMachine on HydroPump
     * @param		command AssignRotatingMachineToHydroPumpCommand
     */     
	@PutMapping("/assignRotatingMachine")
	public void assignRotatingMachine( @RequestBody AssignRotatingMachineToHydroPumpCommand command ) {
		try {
			HydroPumpBusinessDelegate.getHydroPumpInstance().assignRotatingMachine( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign RotatingMachine", exc );
        }
	}

    /**
     * unassign RotatingMachine on HydroPump
     * @param		 command UnAssignRotatingMachineFromHydroPumpCommand
     */     
	@PutMapping("/unAssignRotatingMachine")
	public void unAssignRotatingMachine( @RequestBody(required=true)  UnAssignRotatingMachineFromHydroPumpCommand command ) {
		try {
			HydroPumpBusinessDelegate.getHydroPumpInstance().unAssignRotatingMachine( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign RotatingMachine", exc );
		}
	}
	
    /**
     * save HydroPowerPlant on HydroPump
     * @param		command AssignHydroPowerPlantToHydroPumpCommand
     */     
	@PutMapping("/assignHydroPowerPlant")
	public void assignHydroPowerPlant( @RequestBody AssignHydroPowerPlantToHydroPumpCommand command ) {
		try {
			HydroPumpBusinessDelegate.getHydroPumpInstance().assignHydroPowerPlant( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign HydroPowerPlant", exc );
        }
	}

    /**
     * unassign HydroPowerPlant on HydroPump
     * @param		 command UnAssignHydroPowerPlantFromHydroPumpCommand
     */     
	@PutMapping("/unAssignHydroPowerPlant")
	public void unAssignHydroPowerPlant( @RequestBody(required=true)  UnAssignHydroPowerPlantFromHydroPumpCommand command ) {
		try {
			HydroPumpBusinessDelegate.getHydroPumpInstance().unAssignHydroPowerPlant( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign HydroPowerPlant", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected HydroPump hydroPump = null;
    private static final Logger LOGGER = Logger.getLogger(HydroPumpCommandRestController.class.getName());
    
}
