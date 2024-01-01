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
 * Implements Spring Controller command CQRS processing for entity DCTopologicalIsland.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/DCTopologicalIsland")
public class DCTopologicalIslandCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a DCTopologicalIsland.  if not key provided, calls create, otherwise calls save
     * @param		DCTopologicalIsland	dCTopologicalIsland
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateDCTopologicalIslandCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = DCTopologicalIslandBusinessDelegate.getDCTopologicalIslandInstance().createDCTopologicalIsland( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a DCTopologicalIsland.  if no key provided, calls create, otherwise calls save
     * @param		DCTopologicalIsland dCTopologicalIsland
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateDCTopologicalIslandCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateDCTopologicalIslandCommand
			// -----------------------------------------------
			completableFuture = DCTopologicalIslandBusinessDelegate.getDCTopologicalIslandInstance().updateDCTopologicalIsland(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "DCTopologicalIslandController:update() - successfully update DCTopologicalIsland - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a DCTopologicalIsland entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID dCTopologicalIslandId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteDCTopologicalIslandCommand command = new DeleteDCTopologicalIslandCommand( dCTopologicalIslandId );

    	try {
        	DCTopologicalIslandBusinessDelegate delegate = DCTopologicalIslandBusinessDelegate.getDCTopologicalIslandInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted DCTopologicalIsland with key " + command.getDCTopologicalIslandId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save DCTopologicalNodes on DCTopologicalIsland
     * @param		command AssignDCTopologicalNodesToDCTopologicalIslandCommand
     */     
	@PutMapping("/assignDCTopologicalNodes")
	public void assignDCTopologicalNodes( @RequestBody AssignDCTopologicalNodesToDCTopologicalIslandCommand command ) {
		try {
			DCTopologicalIslandBusinessDelegate.getDCTopologicalIslandInstance().assignDCTopologicalNodes( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign DCTopologicalNodes", exc );
        }
	}

    /**
     * unassign DCTopologicalNodes on DCTopologicalIsland
     * @param		 command UnAssignDCTopologicalNodesFromDCTopologicalIslandCommand
     */     
	@PutMapping("/unAssignDCTopologicalNodes")
	public void unAssignDCTopologicalNodes( @RequestBody(required=true)  UnAssignDCTopologicalNodesFromDCTopologicalIslandCommand command ) {
		try {
			DCTopologicalIslandBusinessDelegate.getDCTopologicalIslandInstance().unAssignDCTopologicalNodes( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign DCTopologicalNodes", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected DCTopologicalIsland dCTopologicalIsland = null;
    private static final Logger LOGGER = Logger.getLogger(DCTopologicalIslandCommandRestController.class.getName());
    
}
