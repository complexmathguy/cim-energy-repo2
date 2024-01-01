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
 * Implements Spring Controller command CQRS processing for entity OperationalLimitSet.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/OperationalLimitSet")
public class OperationalLimitSetCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a OperationalLimitSet.  if not key provided, calls create, otherwise calls save
     * @param		OperationalLimitSet	operationalLimitSet
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateOperationalLimitSetCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = OperationalLimitSetBusinessDelegate.getOperationalLimitSetInstance().createOperationalLimitSet( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a OperationalLimitSet.  if no key provided, calls create, otherwise calls save
     * @param		OperationalLimitSet operationalLimitSet
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateOperationalLimitSetCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateOperationalLimitSetCommand
			// -----------------------------------------------
			completableFuture = OperationalLimitSetBusinessDelegate.getOperationalLimitSetInstance().updateOperationalLimitSet(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "OperationalLimitSetController:update() - successfully update OperationalLimitSet - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a OperationalLimitSet entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID operationalLimitSetId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteOperationalLimitSetCommand command = new DeleteOperationalLimitSetCommand( operationalLimitSetId );

    	try {
        	OperationalLimitSetBusinessDelegate delegate = OperationalLimitSetBusinessDelegate.getOperationalLimitSetInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted OperationalLimitSet with key " + command.getOperationalLimitSetId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save Equipment on OperationalLimitSet
     * @param		command AssignEquipmentToOperationalLimitSetCommand
     */     
	@PutMapping("/assignEquipment")
	public void assignEquipment( @RequestBody AssignEquipmentToOperationalLimitSetCommand command ) {
		try {
			OperationalLimitSetBusinessDelegate.getOperationalLimitSetInstance().assignEquipment( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Equipment", exc );
        }
	}

    /**
     * unassign Equipment on OperationalLimitSet
     * @param		 command UnAssignEquipmentFromOperationalLimitSetCommand
     */     
	@PutMapping("/unAssignEquipment")
	public void unAssignEquipment( @RequestBody(required=true)  UnAssignEquipmentFromOperationalLimitSetCommand command ) {
		try {
			OperationalLimitSetBusinessDelegate.getOperationalLimitSetInstance().unAssignEquipment( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Equipment", exc );
		}
	}
	
    /**
     * save Terminal on OperationalLimitSet
     * @param		command AssignTerminalToOperationalLimitSetCommand
     */     
	@PutMapping("/assignTerminal")
	public void assignTerminal( @RequestBody AssignTerminalToOperationalLimitSetCommand command ) {
		try {
			OperationalLimitSetBusinessDelegate.getOperationalLimitSetInstance().assignTerminal( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Terminal", exc );
        }
	}

    /**
     * unassign Terminal on OperationalLimitSet
     * @param		 command UnAssignTerminalFromOperationalLimitSetCommand
     */     
	@PutMapping("/unAssignTerminal")
	public void unAssignTerminal( @RequestBody(required=true)  UnAssignTerminalFromOperationalLimitSetCommand command ) {
		try {
			OperationalLimitSetBusinessDelegate.getOperationalLimitSetInstance().unAssignTerminal( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Terminal", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected OperationalLimitSet operationalLimitSet = null;
    private static final Logger LOGGER = Logger.getLogger(OperationalLimitSetCommandRestController.class.getName());
    
}
