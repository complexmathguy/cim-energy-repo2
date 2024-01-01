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
 * Implements Spring Controller command CQRS processing for entity SvVoltage.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/SvVoltage")
public class SvVoltageCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a SvVoltage.  if not key provided, calls create, otherwise calls save
     * @param		SvVoltage	svVoltage
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateSvVoltageCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = SvVoltageBusinessDelegate.getSvVoltageInstance().createSvVoltage( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a SvVoltage.  if no key provided, calls create, otherwise calls save
     * @param		SvVoltage svVoltage
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateSvVoltageCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateSvVoltageCommand
			// -----------------------------------------------
			completableFuture = SvVoltageBusinessDelegate.getSvVoltageInstance().updateSvVoltage(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "SvVoltageController:update() - successfully update SvVoltage - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a SvVoltage entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID svVoltageId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteSvVoltageCommand command = new DeleteSvVoltageCommand( svVoltageId );

    	try {
        	SvVoltageBusinessDelegate delegate = SvVoltageBusinessDelegate.getSvVoltageInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted SvVoltage with key " + command.getSvVoltageId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save Angle on SvVoltage
     * @param		command AssignAngleToSvVoltageCommand
     */     
	@PutMapping("/assignAngle")
	public void assignAngle( @RequestBody AssignAngleToSvVoltageCommand command ) {
		try {
			SvVoltageBusinessDelegate.getSvVoltageInstance().assignAngle( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Angle", exc );
        }
	}

    /**
     * unassign Angle on SvVoltage
     * @param		 command UnAssignAngleFromSvVoltageCommand
     */     
	@PutMapping("/unAssignAngle")
	public void unAssignAngle( @RequestBody(required=true)  UnAssignAngleFromSvVoltageCommand command ) {
		try {
			SvVoltageBusinessDelegate.getSvVoltageInstance().unAssignAngle( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Angle", exc );
		}
	}
	
    /**
     * save V on SvVoltage
     * @param		command AssignVToSvVoltageCommand
     */     
	@PutMapping("/assignV")
	public void assignV( @RequestBody AssignVToSvVoltageCommand command ) {
		try {
			SvVoltageBusinessDelegate.getSvVoltageInstance().assignV( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign V", exc );
        }
	}

    /**
     * unassign V on SvVoltage
     * @param		 command UnAssignVFromSvVoltageCommand
     */     
	@PutMapping("/unAssignV")
	public void unAssignV( @RequestBody(required=true)  UnAssignVFromSvVoltageCommand command ) {
		try {
			SvVoltageBusinessDelegate.getSvVoltageInstance().unAssignV( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign V", exc );
		}
	}
	
    /**
     * save TopologicalNode on SvVoltage
     * @param		command AssignTopologicalNodeToSvVoltageCommand
     */     
	@PutMapping("/assignTopologicalNode")
	public void assignTopologicalNode( @RequestBody AssignTopologicalNodeToSvVoltageCommand command ) {
		try {
			SvVoltageBusinessDelegate.getSvVoltageInstance().assignTopologicalNode( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign TopologicalNode", exc );
        }
	}

    /**
     * unassign TopologicalNode on SvVoltage
     * @param		 command UnAssignTopologicalNodeFromSvVoltageCommand
     */     
	@PutMapping("/unAssignTopologicalNode")
	public void unAssignTopologicalNode( @RequestBody(required=true)  UnAssignTopologicalNodeFromSvVoltageCommand command ) {
		try {
			SvVoltageBusinessDelegate.getSvVoltageInstance().unAssignTopologicalNode( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign TopologicalNode", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected SvVoltage svVoltage = null;
    private static final Logger LOGGER = Logger.getLogger(SvVoltageCommandRestController.class.getName());
    
}
