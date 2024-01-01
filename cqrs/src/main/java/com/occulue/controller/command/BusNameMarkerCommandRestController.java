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
 * Implements Spring Controller command CQRS processing for entity BusNameMarker.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/BusNameMarker")
public class BusNameMarkerCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a BusNameMarker.  if not key provided, calls create, otherwise calls save
     * @param		BusNameMarker	busNameMarker
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateBusNameMarkerCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = BusNameMarkerBusinessDelegate.getBusNameMarkerInstance().createBusNameMarker( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a BusNameMarker.  if no key provided, calls create, otherwise calls save
     * @param		BusNameMarker busNameMarker
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateBusNameMarkerCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateBusNameMarkerCommand
			// -----------------------------------------------
			completableFuture = BusNameMarkerBusinessDelegate.getBusNameMarkerInstance().updateBusNameMarker(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "BusNameMarkerController:update() - successfully update BusNameMarker - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a BusNameMarker entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID busNameMarkerId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteBusNameMarkerCommand command = new DeleteBusNameMarkerCommand( busNameMarkerId );

    	try {
        	BusNameMarkerBusinessDelegate delegate = BusNameMarkerBusinessDelegate.getBusNameMarkerInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted BusNameMarker with key " + command.getBusNameMarkerId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save Priority on BusNameMarker
     * @param		command AssignPriorityToBusNameMarkerCommand
     */     
	@PutMapping("/assignPriority")
	public void assignPriority( @RequestBody AssignPriorityToBusNameMarkerCommand command ) {
		try {
			BusNameMarkerBusinessDelegate.getBusNameMarkerInstance().assignPriority( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Priority", exc );
        }
	}

    /**
     * unassign Priority on BusNameMarker
     * @param		 command UnAssignPriorityFromBusNameMarkerCommand
     */     
	@PutMapping("/unAssignPriority")
	public void unAssignPriority( @RequestBody(required=true)  UnAssignPriorityFromBusNameMarkerCommand command ) {
		try {
			BusNameMarkerBusinessDelegate.getBusNameMarkerInstance().unAssignPriority( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Priority", exc );
		}
	}
	
    /**
     * save ReportingGroup on BusNameMarker
     * @param		command AssignReportingGroupToBusNameMarkerCommand
     */     
	@PutMapping("/assignReportingGroup")
	public void assignReportingGroup( @RequestBody AssignReportingGroupToBusNameMarkerCommand command ) {
		try {
			BusNameMarkerBusinessDelegate.getBusNameMarkerInstance().assignReportingGroup( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign ReportingGroup", exc );
        }
	}

    /**
     * unassign ReportingGroup on BusNameMarker
     * @param		 command UnAssignReportingGroupFromBusNameMarkerCommand
     */     
	@PutMapping("/unAssignReportingGroup")
	public void unAssignReportingGroup( @RequestBody(required=true)  UnAssignReportingGroupFromBusNameMarkerCommand command ) {
		try {
			BusNameMarkerBusinessDelegate.getBusNameMarkerInstance().unAssignReportingGroup( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign ReportingGroup", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected BusNameMarker busNameMarker = null;
    private static final Logger LOGGER = Logger.getLogger(BusNameMarkerCommandRestController.class.getName());
    
}
