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
 * Implements Spring Controller command CQRS processing for entity AnalogLimit.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/AnalogLimit")
public class AnalogLimitCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a AnalogLimit.  if not key provided, calls create, otherwise calls save
     * @param		AnalogLimit	analogLimit
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateAnalogLimitCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = AnalogLimitBusinessDelegate.getAnalogLimitInstance().createAnalogLimit( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a AnalogLimit.  if no key provided, calls create, otherwise calls save
     * @param		AnalogLimit analogLimit
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateAnalogLimitCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateAnalogLimitCommand
			// -----------------------------------------------
			completableFuture = AnalogLimitBusinessDelegate.getAnalogLimitInstance().updateAnalogLimit(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "AnalogLimitController:update() - successfully update AnalogLimit - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a AnalogLimit entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID analogLimitId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteAnalogLimitCommand command = new DeleteAnalogLimitCommand( analogLimitId );

    	try {
        	AnalogLimitBusinessDelegate delegate = AnalogLimitBusinessDelegate.getAnalogLimitInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted AnalogLimit with key " + command.getAnalogLimitId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save Value on AnalogLimit
     * @param		command AssignValueToAnalogLimitCommand
     */     
	@PutMapping("/assignValue")
	public void assignValue( @RequestBody AssignValueToAnalogLimitCommand command ) {
		try {
			AnalogLimitBusinessDelegate.getAnalogLimitInstance().assignValue( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Value", exc );
        }
	}

    /**
     * unassign Value on AnalogLimit
     * @param		 command UnAssignValueFromAnalogLimitCommand
     */     
	@PutMapping("/unAssignValue")
	public void unAssignValue( @RequestBody(required=true)  UnAssignValueFromAnalogLimitCommand command ) {
		try {
			AnalogLimitBusinessDelegate.getAnalogLimitInstance().unAssignValue( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Value", exc );
		}
	}
	

    /**
     * save LimitSet on AnalogLimit
     * @param		command AssignLimitSetToAnalogLimitCommand
     */     
	@PutMapping("/addToLimitSet")
	public void addToLimitSet( @RequestBody(required=true) AssignLimitSetToAnalogLimitCommand command ) {
		try {
			AnalogLimitBusinessDelegate.getAnalogLimitInstance().addToLimitSet( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to add to Set LimitSet", exc );
		}
	}

    /**
     * remove LimitSet on AnalogLimit
     * @param		command RemoveLimitSetFromAnalogLimitCommand
     */     	
	@PutMapping("/removeFromLimitSet")
	public void removeFromLimitSet( 	@RequestBody(required=true) RemoveLimitSetFromAnalogLimitCommand command )
	{		
		try {
			AnalogLimitBusinessDelegate.getAnalogLimitInstance().removeFromLimitSet( command );
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to remove from Set LimitSet", exc );
		}
	}


//************************************************************************    
// Attributes
//************************************************************************
    protected AnalogLimit analogLimit = null;
    private static final Logger LOGGER = Logger.getLogger(AnalogLimitCommandRestController.class.getName());
    
}
