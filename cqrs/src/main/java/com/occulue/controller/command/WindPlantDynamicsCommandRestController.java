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
 * Implements Spring Controller command CQRS processing for entity WindPlantDynamics.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/WindPlantDynamics")
public class WindPlantDynamicsCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a WindPlantDynamics.  if not key provided, calls create, otherwise calls save
     * @param		WindPlantDynamics	windPlantDynamics
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateWindPlantDynamicsCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = WindPlantDynamicsBusinessDelegate.getWindPlantDynamicsInstance().createWindPlantDynamics( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a WindPlantDynamics.  if no key provided, calls create, otherwise calls save
     * @param		WindPlantDynamics windPlantDynamics
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateWindPlantDynamicsCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateWindPlantDynamicsCommand
			// -----------------------------------------------
			completableFuture = WindPlantDynamicsBusinessDelegate.getWindPlantDynamicsInstance().updateWindPlantDynamics(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "WindPlantDynamicsController:update() - successfully update WindPlantDynamics - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a WindPlantDynamics entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID windPlantDynamicsId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteWindPlantDynamicsCommand command = new DeleteWindPlantDynamicsCommand( windPlantDynamicsId );

    	try {
        	WindPlantDynamicsBusinessDelegate delegate = WindPlantDynamicsBusinessDelegate.getWindPlantDynamicsInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted WindPlantDynamics with key " + command.getWindPlantDynamicsId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save RemoteInputSignal on WindPlantDynamics
     * @param		command AssignRemoteInputSignalToWindPlantDynamicsCommand
     */     
	@PutMapping("/assignRemoteInputSignal")
	public void assignRemoteInputSignal( @RequestBody AssignRemoteInputSignalToWindPlantDynamicsCommand command ) {
		try {
			WindPlantDynamicsBusinessDelegate.getWindPlantDynamicsInstance().assignRemoteInputSignal( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign RemoteInputSignal", exc );
        }
	}

    /**
     * unassign RemoteInputSignal on WindPlantDynamics
     * @param		 command UnAssignRemoteInputSignalFromWindPlantDynamicsCommand
     */     
	@PutMapping("/unAssignRemoteInputSignal")
	public void unAssignRemoteInputSignal( @RequestBody(required=true)  UnAssignRemoteInputSignalFromWindPlantDynamicsCommand command ) {
		try {
			WindPlantDynamicsBusinessDelegate.getWindPlantDynamicsInstance().unAssignRemoteInputSignal( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign RemoteInputSignal", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected WindPlantDynamics windPlantDynamics = null;
    private static final Logger LOGGER = Logger.getLogger(WindPlantDynamicsCommandRestController.class.getName());
    
}
