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
 * Implements Spring Controller command CQRS processing for entity OperationalLimit.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/OperationalLimit")
public class OperationalLimitCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a OperationalLimit.  if not key provided, calls create, otherwise calls save
     * @param		OperationalLimit	operationalLimit
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateOperationalLimitCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = OperationalLimitBusinessDelegate.getOperationalLimitInstance().createOperationalLimit( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a OperationalLimit.  if no key provided, calls create, otherwise calls save
     * @param		OperationalLimit operationalLimit
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateOperationalLimitCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateOperationalLimitCommand
			// -----------------------------------------------
			completableFuture = OperationalLimitBusinessDelegate.getOperationalLimitInstance().updateOperationalLimit(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "OperationalLimitController:update() - successfully update OperationalLimit - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a OperationalLimit entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID operationalLimitId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteOperationalLimitCommand command = new DeleteOperationalLimitCommand( operationalLimitId );

    	try {
        	OperationalLimitBusinessDelegate delegate = OperationalLimitBusinessDelegate.getOperationalLimitInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted OperationalLimit with key " + command.getOperationalLimitId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save OperationalLimitType on OperationalLimit
     * @param		command AssignOperationalLimitTypeToOperationalLimitCommand
     */     
	@PutMapping("/assignOperationalLimitType")
	public void assignOperationalLimitType( @RequestBody AssignOperationalLimitTypeToOperationalLimitCommand command ) {
		try {
			OperationalLimitBusinessDelegate.getOperationalLimitInstance().assignOperationalLimitType( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign OperationalLimitType", exc );
        }
	}

    /**
     * unassign OperationalLimitType on OperationalLimit
     * @param		 command UnAssignOperationalLimitTypeFromOperationalLimitCommand
     */     
	@PutMapping("/unAssignOperationalLimitType")
	public void unAssignOperationalLimitType( @RequestBody(required=true)  UnAssignOperationalLimitTypeFromOperationalLimitCommand command ) {
		try {
			OperationalLimitBusinessDelegate.getOperationalLimitInstance().unAssignOperationalLimitType( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign OperationalLimitType", exc );
		}
	}
	

    /**
     * save OperationalLimitSet on OperationalLimit
     * @param		command AssignOperationalLimitSetToOperationalLimitCommand
     */     
	@PutMapping("/addToOperationalLimitSet")
	public void addToOperationalLimitSet( @RequestBody(required=true) AssignOperationalLimitSetToOperationalLimitCommand command ) {
		try {
			OperationalLimitBusinessDelegate.getOperationalLimitInstance().addToOperationalLimitSet( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to add to Set OperationalLimitSet", exc );
		}
	}

    /**
     * remove OperationalLimitSet on OperationalLimit
     * @param		command RemoveOperationalLimitSetFromOperationalLimitCommand
     */     	
	@PutMapping("/removeFromOperationalLimitSet")
	public void removeFromOperationalLimitSet( 	@RequestBody(required=true) RemoveOperationalLimitSetFromOperationalLimitCommand command )
	{		
		try {
			OperationalLimitBusinessDelegate.getOperationalLimitInstance().removeFromOperationalLimitSet( command );
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to remove from Set OperationalLimitSet", exc );
		}
	}


//************************************************************************    
// Attributes
//************************************************************************
    protected OperationalLimit operationalLimit = null;
    private static final Logger LOGGER = Logger.getLogger(OperationalLimitCommandRestController.class.getName());
    
}
