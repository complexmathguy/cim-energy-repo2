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
 * Implements Spring Controller command CQRS processing for entity VisibilityLayer.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/VisibilityLayer")
public class VisibilityLayerCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a VisibilityLayer.  if not key provided, calls create, otherwise calls save
     * @param		VisibilityLayer	visibilityLayer
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateVisibilityLayerCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = VisibilityLayerBusinessDelegate.getVisibilityLayerInstance().createVisibilityLayer( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a VisibilityLayer.  if no key provided, calls create, otherwise calls save
     * @param		VisibilityLayer visibilityLayer
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateVisibilityLayerCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateVisibilityLayerCommand
			// -----------------------------------------------
			completableFuture = VisibilityLayerBusinessDelegate.getVisibilityLayerInstance().updateVisibilityLayer(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "VisibilityLayerController:update() - successfully update VisibilityLayer - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a VisibilityLayer entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID visibilityLayerId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteVisibilityLayerCommand command = new DeleteVisibilityLayerCommand( visibilityLayerId );

    	try {
        	VisibilityLayerBusinessDelegate delegate = VisibilityLayerBusinessDelegate.getVisibilityLayerInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted VisibilityLayer with key " + command.getVisibilityLayerId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save DrawingOrder on VisibilityLayer
     * @param		command AssignDrawingOrderToVisibilityLayerCommand
     */     
	@PutMapping("/assignDrawingOrder")
	public void assignDrawingOrder( @RequestBody AssignDrawingOrderToVisibilityLayerCommand command ) {
		try {
			VisibilityLayerBusinessDelegate.getVisibilityLayerInstance().assignDrawingOrder( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign DrawingOrder", exc );
        }
	}

    /**
     * unassign DrawingOrder on VisibilityLayer
     * @param		 command UnAssignDrawingOrderFromVisibilityLayerCommand
     */     
	@PutMapping("/unAssignDrawingOrder")
	public void unAssignDrawingOrder( @RequestBody(required=true)  UnAssignDrawingOrderFromVisibilityLayerCommand command ) {
		try {
			VisibilityLayerBusinessDelegate.getVisibilityLayerInstance().unAssignDrawingOrder( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign DrawingOrder", exc );
		}
	}
	
    /**
     * save VisibleObjects on VisibilityLayer
     * @param		command AssignVisibleObjectsToVisibilityLayerCommand
     */     
	@PutMapping("/assignVisibleObjects")
	public void assignVisibleObjects( @RequestBody AssignVisibleObjectsToVisibilityLayerCommand command ) {
		try {
			VisibilityLayerBusinessDelegate.getVisibilityLayerInstance().assignVisibleObjects( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign VisibleObjects", exc );
        }
	}

    /**
     * unassign VisibleObjects on VisibilityLayer
     * @param		 command UnAssignVisibleObjectsFromVisibilityLayerCommand
     */     
	@PutMapping("/unAssignVisibleObjects")
	public void unAssignVisibleObjects( @RequestBody(required=true)  UnAssignVisibleObjectsFromVisibilityLayerCommand command ) {
		try {
			VisibilityLayerBusinessDelegate.getVisibilityLayerInstance().unAssignVisibleObjects( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign VisibleObjects", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected VisibilityLayer visibilityLayer = null;
    private static final Logger LOGGER = Logger.getLogger(VisibilityLayerCommandRestController.class.getName());
    
}
