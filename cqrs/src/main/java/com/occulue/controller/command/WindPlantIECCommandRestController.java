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
 * Implements Spring Controller command CQRS processing for entity WindPlantIEC.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/WindPlantIEC")
public class WindPlantIECCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a WindPlantIEC.  if not key provided, calls create, otherwise calls save
     * @param		WindPlantIEC	windPlantIEC
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateWindPlantIECCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = WindPlantIECBusinessDelegate.getWindPlantIECInstance().createWindPlantIEC( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a WindPlantIEC.  if no key provided, calls create, otherwise calls save
     * @param		WindPlantIEC windPlantIEC
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateWindPlantIECCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateWindPlantIECCommand
			// -----------------------------------------------
			completableFuture = WindPlantIECBusinessDelegate.getWindPlantIECInstance().updateWindPlantIEC(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "WindPlantIECController:update() - successfully update WindPlantIEC - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a WindPlantIEC entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID windPlantIECId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteWindPlantIECCommand command = new DeleteWindPlantIECCommand( windPlantIECId );

    	try {
        	WindPlantIECBusinessDelegate delegate = WindPlantIECBusinessDelegate.getWindPlantIECInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted WindPlantIEC with key " + command.getWindPlantIECId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save WindPlantFreqPcontrolIEC on WindPlantIEC
     * @param		command AssignWindPlantFreqPcontrolIECToWindPlantIECCommand
     */     
	@PutMapping("/assignWindPlantFreqPcontrolIEC")
	public void assignWindPlantFreqPcontrolIEC( @RequestBody AssignWindPlantFreqPcontrolIECToWindPlantIECCommand command ) {
		try {
			WindPlantIECBusinessDelegate.getWindPlantIECInstance().assignWindPlantFreqPcontrolIEC( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign WindPlantFreqPcontrolIEC", exc );
        }
	}

    /**
     * unassign WindPlantFreqPcontrolIEC on WindPlantIEC
     * @param		 command UnAssignWindPlantFreqPcontrolIECFromWindPlantIECCommand
     */     
	@PutMapping("/unAssignWindPlantFreqPcontrolIEC")
	public void unAssignWindPlantFreqPcontrolIEC( @RequestBody(required=true)  UnAssignWindPlantFreqPcontrolIECFromWindPlantIECCommand command ) {
		try {
			WindPlantIECBusinessDelegate.getWindPlantIECInstance().unAssignWindPlantFreqPcontrolIEC( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign WindPlantFreqPcontrolIEC", exc );
		}
	}
	
    /**
     * save WindPlantReactiveControlIEC on WindPlantIEC
     * @param		command AssignWindPlantReactiveControlIECToWindPlantIECCommand
     */     
	@PutMapping("/assignWindPlantReactiveControlIEC")
	public void assignWindPlantReactiveControlIEC( @RequestBody AssignWindPlantReactiveControlIECToWindPlantIECCommand command ) {
		try {
			WindPlantIECBusinessDelegate.getWindPlantIECInstance().assignWindPlantReactiveControlIEC( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign WindPlantReactiveControlIEC", exc );
        }
	}

    /**
     * unassign WindPlantReactiveControlIEC on WindPlantIEC
     * @param		 command UnAssignWindPlantReactiveControlIECFromWindPlantIECCommand
     */     
	@PutMapping("/unAssignWindPlantReactiveControlIEC")
	public void unAssignWindPlantReactiveControlIEC( @RequestBody(required=true)  UnAssignWindPlantReactiveControlIECFromWindPlantIECCommand command ) {
		try {
			WindPlantIECBusinessDelegate.getWindPlantIECInstance().unAssignWindPlantReactiveControlIEC( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign WindPlantReactiveControlIEC", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected WindPlantIEC windPlantIEC = null;
    private static final Logger LOGGER = Logger.getLogger(WindPlantIECCommandRestController.class.getName());
    
}
