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
 * Implements Spring Controller command CQRS processing for entity WindTurbineType1or2IEC.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/WindTurbineType1or2IEC")
public class WindTurbineType1or2IECCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a WindTurbineType1or2IEC.  if not key provided, calls create, otherwise calls save
     * @param		WindTurbineType1or2IEC	windTurbineType1or2IEC
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateWindTurbineType1or2IECCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = WindTurbineType1or2IECBusinessDelegate.getWindTurbineType1or2IECInstance().createWindTurbineType1or2IEC( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a WindTurbineType1or2IEC.  if no key provided, calls create, otherwise calls save
     * @param		WindTurbineType1or2IEC windTurbineType1or2IEC
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateWindTurbineType1or2IECCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateWindTurbineType1or2IECCommand
			// -----------------------------------------------
			completableFuture = WindTurbineType1or2IECBusinessDelegate.getWindTurbineType1or2IECInstance().updateWindTurbineType1or2IEC(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "WindTurbineType1or2IECController:update() - successfully update WindTurbineType1or2IEC - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a WindTurbineType1or2IEC entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID windTurbineType1or2IECId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteWindTurbineType1or2IECCommand command = new DeleteWindTurbineType1or2IECCommand( windTurbineType1or2IECId );

    	try {
        	WindTurbineType1or2IECBusinessDelegate delegate = WindTurbineType1or2IECBusinessDelegate.getWindTurbineType1or2IECInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted WindTurbineType1or2IEC with key " + command.getWindTurbineType1or2IECId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save WindProtectionIEC on WindTurbineType1or2IEC
     * @param		command AssignWindProtectionIECToWindTurbineType1or2IECCommand
     */     
	@PutMapping("/assignWindProtectionIEC")
	public void assignWindProtectionIEC( @RequestBody AssignWindProtectionIECToWindTurbineType1or2IECCommand command ) {
		try {
			WindTurbineType1or2IECBusinessDelegate.getWindTurbineType1or2IECInstance().assignWindProtectionIEC( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign WindProtectionIEC", exc );
        }
	}

    /**
     * unassign WindProtectionIEC on WindTurbineType1or2IEC
     * @param		 command UnAssignWindProtectionIECFromWindTurbineType1or2IECCommand
     */     
	@PutMapping("/unAssignWindProtectionIEC")
	public void unAssignWindProtectionIEC( @RequestBody(required=true)  UnAssignWindProtectionIECFromWindTurbineType1or2IECCommand command ) {
		try {
			WindTurbineType1or2IECBusinessDelegate.getWindTurbineType1or2IECInstance().unAssignWindProtectionIEC( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign WindProtectionIEC", exc );
		}
	}
	
    /**
     * save WindMechIEC on WindTurbineType1or2IEC
     * @param		command AssignWindMechIECToWindTurbineType1or2IECCommand
     */     
	@PutMapping("/assignWindMechIEC")
	public void assignWindMechIEC( @RequestBody AssignWindMechIECToWindTurbineType1or2IECCommand command ) {
		try {
			WindTurbineType1or2IECBusinessDelegate.getWindTurbineType1or2IECInstance().assignWindMechIEC( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign WindMechIEC", exc );
        }
	}

    /**
     * unassign WindMechIEC on WindTurbineType1or2IEC
     * @param		 command UnAssignWindMechIECFromWindTurbineType1or2IECCommand
     */     
	@PutMapping("/unAssignWindMechIEC")
	public void unAssignWindMechIEC( @RequestBody(required=true)  UnAssignWindMechIECFromWindTurbineType1or2IECCommand command ) {
		try {
			WindTurbineType1or2IECBusinessDelegate.getWindTurbineType1or2IECInstance().unAssignWindMechIEC( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign WindMechIEC", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected WindTurbineType1or2IEC windTurbineType1or2IEC = null;
    private static final Logger LOGGER = Logger.getLogger(WindTurbineType1or2IECCommandRestController.class.getName());
    
}
