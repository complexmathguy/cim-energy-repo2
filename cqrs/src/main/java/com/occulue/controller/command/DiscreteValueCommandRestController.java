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
 * Implements Spring Controller command CQRS processing for entity DiscreteValue.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/DiscreteValue")
public class DiscreteValueCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a DiscreteValue.  if not key provided, calls create, otherwise calls save
     * @param		DiscreteValue	discreteValue
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateDiscreteValueCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = DiscreteValueBusinessDelegate.getDiscreteValueInstance().createDiscreteValue( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a DiscreteValue.  if no key provided, calls create, otherwise calls save
     * @param		DiscreteValue discreteValue
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateDiscreteValueCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateDiscreteValueCommand
			// -----------------------------------------------
			completableFuture = DiscreteValueBusinessDelegate.getDiscreteValueInstance().updateDiscreteValue(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "DiscreteValueController:update() - successfully update DiscreteValue - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a DiscreteValue entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID discreteValueId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteDiscreteValueCommand command = new DeleteDiscreteValueCommand( discreteValueId );

    	try {
        	DiscreteValueBusinessDelegate delegate = DiscreteValueBusinessDelegate.getDiscreteValueInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted DiscreteValue with key " + command.getDiscreteValueId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save Value on DiscreteValue
     * @param		command AssignValueToDiscreteValueCommand
     */     
	@PutMapping("/assignValue")
	public void assignValue( @RequestBody AssignValueToDiscreteValueCommand command ) {
		try {
			DiscreteValueBusinessDelegate.getDiscreteValueInstance().assignValue( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Value", exc );
        }
	}

    /**
     * unassign Value on DiscreteValue
     * @param		 command UnAssignValueFromDiscreteValueCommand
     */     
	@PutMapping("/unAssignValue")
	public void unAssignValue( @RequestBody(required=true)  UnAssignValueFromDiscreteValueCommand command ) {
		try {
			DiscreteValueBusinessDelegate.getDiscreteValueInstance().unAssignValue( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Value", exc );
		}
	}
	
    /**
     * save Discrete on DiscreteValue
     * @param		command AssignDiscreteToDiscreteValueCommand
     */     
	@PutMapping("/assignDiscrete")
	public void assignDiscrete( @RequestBody AssignDiscreteToDiscreteValueCommand command ) {
		try {
			DiscreteValueBusinessDelegate.getDiscreteValueInstance().assignDiscrete( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Discrete", exc );
        }
	}

    /**
     * unassign Discrete on DiscreteValue
     * @param		 command UnAssignDiscreteFromDiscreteValueCommand
     */     
	@PutMapping("/unAssignDiscrete")
	public void unAssignDiscrete( @RequestBody(required=true)  UnAssignDiscreteFromDiscreteValueCommand command ) {
		try {
			DiscreteValueBusinessDelegate.getDiscreteValueInstance().unAssignDiscrete( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Discrete", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected DiscreteValue discreteValue = null;
    private static final Logger LOGGER = Logger.getLogger(DiscreteValueCommandRestController.class.getName());
    
}
