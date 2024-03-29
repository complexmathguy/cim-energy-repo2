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
 * Implements Spring Controller command CQRS processing for entity WindTurbineType3or4Dynamics.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/WindTurbineType3or4Dynamics")
public class WindTurbineType3or4DynamicsCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a WindTurbineType3or4Dynamics.  if not key provided, calls create, otherwise calls save
     * @param		WindTurbineType3or4Dynamics	windTurbineType3or4Dynamics
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateWindTurbineType3or4DynamicsCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = WindTurbineType3or4DynamicsBusinessDelegate.getWindTurbineType3or4DynamicsInstance().createWindTurbineType3or4Dynamics( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a WindTurbineType3or4Dynamics.  if no key provided, calls create, otherwise calls save
     * @param		WindTurbineType3or4Dynamics windTurbineType3or4Dynamics
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateWindTurbineType3or4DynamicsCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateWindTurbineType3or4DynamicsCommand
			// -----------------------------------------------
			completableFuture = WindTurbineType3or4DynamicsBusinessDelegate.getWindTurbineType3or4DynamicsInstance().updateWindTurbineType3or4Dynamics(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "WindTurbineType3or4DynamicsController:update() - successfully update WindTurbineType3or4Dynamics - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a WindTurbineType3or4Dynamics entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID windTurbineType3or4DynamicsId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteWindTurbineType3or4DynamicsCommand command = new DeleteWindTurbineType3or4DynamicsCommand( windTurbineType3or4DynamicsId );

    	try {
        	WindTurbineType3or4DynamicsBusinessDelegate delegate = WindTurbineType3or4DynamicsBusinessDelegate.getWindTurbineType3or4DynamicsInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted WindTurbineType3or4Dynamics with key " + command.getWindTurbineType3or4DynamicsId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save EnergySource on WindTurbineType3or4Dynamics
     * @param		command AssignEnergySourceToWindTurbineType3or4DynamicsCommand
     */     
	@PutMapping("/assignEnergySource")
	public void assignEnergySource( @RequestBody AssignEnergySourceToWindTurbineType3or4DynamicsCommand command ) {
		try {
			WindTurbineType3or4DynamicsBusinessDelegate.getWindTurbineType3or4DynamicsInstance().assignEnergySource( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign EnergySource", exc );
        }
	}

    /**
     * unassign EnergySource on WindTurbineType3or4Dynamics
     * @param		 command UnAssignEnergySourceFromWindTurbineType3or4DynamicsCommand
     */     
	@PutMapping("/unAssignEnergySource")
	public void unAssignEnergySource( @RequestBody(required=true)  UnAssignEnergySourceFromWindTurbineType3or4DynamicsCommand command ) {
		try {
			WindTurbineType3or4DynamicsBusinessDelegate.getWindTurbineType3or4DynamicsInstance().unAssignEnergySource( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign EnergySource", exc );
		}
	}
	
    /**
     * save WindPlantDynamics on WindTurbineType3or4Dynamics
     * @param		command AssignWindPlantDynamicsToWindTurbineType3or4DynamicsCommand
     */     
	@PutMapping("/assignWindPlantDynamics")
	public void assignWindPlantDynamics( @RequestBody AssignWindPlantDynamicsToWindTurbineType3or4DynamicsCommand command ) {
		try {
			WindTurbineType3or4DynamicsBusinessDelegate.getWindTurbineType3or4DynamicsInstance().assignWindPlantDynamics( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign WindPlantDynamics", exc );
        }
	}

    /**
     * unassign WindPlantDynamics on WindTurbineType3or4Dynamics
     * @param		 command UnAssignWindPlantDynamicsFromWindTurbineType3or4DynamicsCommand
     */     
	@PutMapping("/unAssignWindPlantDynamics")
	public void unAssignWindPlantDynamics( @RequestBody(required=true)  UnAssignWindPlantDynamicsFromWindTurbineType3or4DynamicsCommand command ) {
		try {
			WindTurbineType3or4DynamicsBusinessDelegate.getWindTurbineType3or4DynamicsInstance().unAssignWindPlantDynamics( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign WindPlantDynamics", exc );
		}
	}
	
    /**
     * save RemoteInputSignal on WindTurbineType3or4Dynamics
     * @param		command AssignRemoteInputSignalToWindTurbineType3or4DynamicsCommand
     */     
	@PutMapping("/assignRemoteInputSignal")
	public void assignRemoteInputSignal( @RequestBody AssignRemoteInputSignalToWindTurbineType3or4DynamicsCommand command ) {
		try {
			WindTurbineType3or4DynamicsBusinessDelegate.getWindTurbineType3or4DynamicsInstance().assignRemoteInputSignal( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign RemoteInputSignal", exc );
        }
	}

    /**
     * unassign RemoteInputSignal on WindTurbineType3or4Dynamics
     * @param		 command UnAssignRemoteInputSignalFromWindTurbineType3or4DynamicsCommand
     */     
	@PutMapping("/unAssignRemoteInputSignal")
	public void unAssignRemoteInputSignal( @RequestBody(required=true)  UnAssignRemoteInputSignalFromWindTurbineType3or4DynamicsCommand command ) {
		try {
			WindTurbineType3or4DynamicsBusinessDelegate.getWindTurbineType3or4DynamicsInstance().unAssignRemoteInputSignal( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign RemoteInputSignal", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected WindTurbineType3or4Dynamics windTurbineType3or4Dynamics = null;
    private static final Logger LOGGER = Logger.getLogger(WindTurbineType3or4DynamicsCommandRestController.class.getName());
    
}
