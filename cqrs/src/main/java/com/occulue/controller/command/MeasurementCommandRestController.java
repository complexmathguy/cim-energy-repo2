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
 * Implements Spring Controller command CQRS processing for entity Measurement.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Measurement")
public class MeasurementCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a Measurement.  if not key provided, calls create, otherwise calls save
     * @param		Measurement	measurement
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateMeasurementCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = MeasurementBusinessDelegate.getMeasurementInstance().createMeasurement( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a Measurement.  if no key provided, calls create, otherwise calls save
     * @param		Measurement measurement
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateMeasurementCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateMeasurementCommand
			// -----------------------------------------------
			completableFuture = MeasurementBusinessDelegate.getMeasurementInstance().updateMeasurement(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "MeasurementController:update() - successfully update Measurement - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a Measurement entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID measurementId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteMeasurementCommand command = new DeleteMeasurementCommand( measurementId );

    	try {
        	MeasurementBusinessDelegate delegate = MeasurementBusinessDelegate.getMeasurementInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted Measurement with key " + command.getMeasurementId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save MeasurementType on Measurement
     * @param		command AssignMeasurementTypeToMeasurementCommand
     */     
	@PutMapping("/assignMeasurementType")
	public void assignMeasurementType( @RequestBody AssignMeasurementTypeToMeasurementCommand command ) {
		try {
			MeasurementBusinessDelegate.getMeasurementInstance().assignMeasurementType( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign MeasurementType", exc );
        }
	}

    /**
     * unassign MeasurementType on Measurement
     * @param		 command UnAssignMeasurementTypeFromMeasurementCommand
     */     
	@PutMapping("/unAssignMeasurementType")
	public void unAssignMeasurementType( @RequestBody(required=true)  UnAssignMeasurementTypeFromMeasurementCommand command ) {
		try {
			MeasurementBusinessDelegate.getMeasurementInstance().unAssignMeasurementType( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign MeasurementType", exc );
		}
	}
	
    /**
     * save PowerSystemResource on Measurement
     * @param		command AssignPowerSystemResourceToMeasurementCommand
     */     
	@PutMapping("/assignPowerSystemResource")
	public void assignPowerSystemResource( @RequestBody AssignPowerSystemResourceToMeasurementCommand command ) {
		try {
			MeasurementBusinessDelegate.getMeasurementInstance().assignPowerSystemResource( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign PowerSystemResource", exc );
        }
	}

    /**
     * unassign PowerSystemResource on Measurement
     * @param		 command UnAssignPowerSystemResourceFromMeasurementCommand
     */     
	@PutMapping("/unAssignPowerSystemResource")
	public void unAssignPowerSystemResource( @RequestBody(required=true)  UnAssignPowerSystemResourceFromMeasurementCommand command ) {
		try {
			MeasurementBusinessDelegate.getMeasurementInstance().unAssignPowerSystemResource( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign PowerSystemResource", exc );
		}
	}
	
    /**
     * save Terminal on Measurement
     * @param		command AssignTerminalToMeasurementCommand
     */     
	@PutMapping("/assignTerminal")
	public void assignTerminal( @RequestBody AssignTerminalToMeasurementCommand command ) {
		try {
			MeasurementBusinessDelegate.getMeasurementInstance().assignTerminal( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Terminal", exc );
        }
	}

    /**
     * unassign Terminal on Measurement
     * @param		 command UnAssignTerminalFromMeasurementCommand
     */     
	@PutMapping("/unAssignTerminal")
	public void unAssignTerminal( @RequestBody(required=true)  UnAssignTerminalFromMeasurementCommand command ) {
		try {
			MeasurementBusinessDelegate.getMeasurementInstance().unAssignTerminal( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Terminal", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected Measurement measurement = null;
    private static final Logger LOGGER = Logger.getLogger(MeasurementCommandRestController.class.getName());
    
}
