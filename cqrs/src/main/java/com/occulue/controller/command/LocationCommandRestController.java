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
 * Implements Spring Controller command CQRS processing for entity Location.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Location")
public class LocationCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a Location.  if not key provided, calls create, otherwise calls save
     * @param		Location	location
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateLocationCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = LocationBusinessDelegate.getLocationInstance().createLocation( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a Location.  if no key provided, calls create, otherwise calls save
     * @param		Location location
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateLocationCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateLocationCommand
			// -----------------------------------------------
			completableFuture = LocationBusinessDelegate.getLocationInstance().updateLocation(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "LocationController:update() - successfully update Location - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a Location entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID locationId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteLocationCommand command = new DeleteLocationCommand( locationId );

    	try {
        	LocationBusinessDelegate delegate = LocationBusinessDelegate.getLocationInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted Location with key " + command.getLocationId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save PowerSystemResources on Location
     * @param		command AssignPowerSystemResourcesToLocationCommand
     */     
	@PutMapping("/assignPowerSystemResources")
	public void assignPowerSystemResources( @RequestBody AssignPowerSystemResourcesToLocationCommand command ) {
		try {
			LocationBusinessDelegate.getLocationInstance().assignPowerSystemResources( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign PowerSystemResources", exc );
        }
	}

    /**
     * unassign PowerSystemResources on Location
     * @param		 command UnAssignPowerSystemResourcesFromLocationCommand
     */     
	@PutMapping("/unAssignPowerSystemResources")
	public void unAssignPowerSystemResources( @RequestBody(required=true)  UnAssignPowerSystemResourcesFromLocationCommand command ) {
		try {
			LocationBusinessDelegate.getLocationInstance().unAssignPowerSystemResources( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign PowerSystemResources", exc );
		}
	}
	
    /**
     * save CoordinateSystem on Location
     * @param		command AssignCoordinateSystemToLocationCommand
     */     
	@PutMapping("/assignCoordinateSystem")
	public void assignCoordinateSystem( @RequestBody AssignCoordinateSystemToLocationCommand command ) {
		try {
			LocationBusinessDelegate.getLocationInstance().assignCoordinateSystem( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign CoordinateSystem", exc );
        }
	}

    /**
     * unassign CoordinateSystem on Location
     * @param		 command UnAssignCoordinateSystemFromLocationCommand
     */     
	@PutMapping("/unAssignCoordinateSystem")
	public void unAssignCoordinateSystem( @RequestBody(required=true)  UnAssignCoordinateSystemFromLocationCommand command ) {
		try {
			LocationBusinessDelegate.getLocationInstance().unAssignCoordinateSystem( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign CoordinateSystem", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected Location location = null;
    private static final Logger LOGGER = Logger.getLogger(LocationCommandRestController.class.getName());
    
}
