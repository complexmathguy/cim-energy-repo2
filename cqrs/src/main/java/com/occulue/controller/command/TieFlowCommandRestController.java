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
 * Implements Spring Controller command CQRS processing for entity TieFlow.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/TieFlow")
public class TieFlowCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a TieFlow.  if not key provided, calls create, otherwise calls save
     * @param		TieFlow	tieFlow
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateTieFlowCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = TieFlowBusinessDelegate.getTieFlowInstance().createTieFlow( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a TieFlow.  if no key provided, calls create, otherwise calls save
     * @param		TieFlow tieFlow
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateTieFlowCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateTieFlowCommand
			// -----------------------------------------------
			completableFuture = TieFlowBusinessDelegate.getTieFlowInstance().updateTieFlow(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "TieFlowController:update() - successfully update TieFlow - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a TieFlow entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID tieFlowId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteTieFlowCommand command = new DeleteTieFlowCommand( tieFlowId );

    	try {
        	TieFlowBusinessDelegate delegate = TieFlowBusinessDelegate.getTieFlowInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted TieFlow with key " + command.getTieFlowId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save PositiveFlowIn on TieFlow
     * @param		command AssignPositiveFlowInToTieFlowCommand
     */     
	@PutMapping("/assignPositiveFlowIn")
	public void assignPositiveFlowIn( @RequestBody AssignPositiveFlowInToTieFlowCommand command ) {
		try {
			TieFlowBusinessDelegate.getTieFlowInstance().assignPositiveFlowIn( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign PositiveFlowIn", exc );
        }
	}

    /**
     * unassign PositiveFlowIn on TieFlow
     * @param		 command UnAssignPositiveFlowInFromTieFlowCommand
     */     
	@PutMapping("/unAssignPositiveFlowIn")
	public void unAssignPositiveFlowIn( @RequestBody(required=true)  UnAssignPositiveFlowInFromTieFlowCommand command ) {
		try {
			TieFlowBusinessDelegate.getTieFlowInstance().unAssignPositiveFlowIn( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign PositiveFlowIn", exc );
		}
	}
	
    /**
     * save ControlArea on TieFlow
     * @param		command AssignControlAreaToTieFlowCommand
     */     
	@PutMapping("/assignControlArea")
	public void assignControlArea( @RequestBody AssignControlAreaToTieFlowCommand command ) {
		try {
			TieFlowBusinessDelegate.getTieFlowInstance().assignControlArea( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign ControlArea", exc );
        }
	}

    /**
     * unassign ControlArea on TieFlow
     * @param		 command UnAssignControlAreaFromTieFlowCommand
     */     
	@PutMapping("/unAssignControlArea")
	public void unAssignControlArea( @RequestBody(required=true)  UnAssignControlAreaFromTieFlowCommand command ) {
		try {
			TieFlowBusinessDelegate.getTieFlowInstance().unAssignControlArea( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign ControlArea", exc );
		}
	}
	
    /**
     * save Terminal on TieFlow
     * @param		command AssignTerminalToTieFlowCommand
     */     
	@PutMapping("/assignTerminal")
	public void assignTerminal( @RequestBody AssignTerminalToTieFlowCommand command ) {
		try {
			TieFlowBusinessDelegate.getTieFlowInstance().assignTerminal( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Terminal", exc );
        }
	}

    /**
     * unassign Terminal on TieFlow
     * @param		 command UnAssignTerminalFromTieFlowCommand
     */     
	@PutMapping("/unAssignTerminal")
	public void unAssignTerminal( @RequestBody(required=true)  UnAssignTerminalFromTieFlowCommand command ) {
		try {
			TieFlowBusinessDelegate.getTieFlowInstance().unAssignTerminal( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Terminal", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected TieFlow tieFlow = null;
    private static final Logger LOGGER = Logger.getLogger(TieFlowCommandRestController.class.getName());
    
}
