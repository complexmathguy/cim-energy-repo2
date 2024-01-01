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
 * Implements Spring Controller command CQRS processing for entity DiagramObjectPoint.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/DiagramObjectPoint")
public class DiagramObjectPointCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a DiagramObjectPoint.  if not key provided, calls create, otherwise calls save
     * @param		DiagramObjectPoint	diagramObjectPoint
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateDiagramObjectPointCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().createDiagramObjectPoint( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a DiagramObjectPoint.  if no key provided, calls create, otherwise calls save
     * @param		DiagramObjectPoint diagramObjectPoint
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateDiagramObjectPointCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateDiagramObjectPointCommand
			// -----------------------------------------------
			completableFuture = DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().updateDiagramObjectPoint(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "DiagramObjectPointController:update() - successfully update DiagramObjectPoint - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a DiagramObjectPoint entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID diagramObjectPointId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteDiagramObjectPointCommand command = new DeleteDiagramObjectPointCommand( diagramObjectPointId );

    	try {
        	DiagramObjectPointBusinessDelegate delegate = DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted DiagramObjectPoint with key " + command.getDiagramObjectPointId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save SequenceNumber on DiagramObjectPoint
     * @param		command AssignSequenceNumberToDiagramObjectPointCommand
     */     
	@PutMapping("/assignSequenceNumber")
	public void assignSequenceNumber( @RequestBody AssignSequenceNumberToDiagramObjectPointCommand command ) {
		try {
			DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().assignSequenceNumber( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign SequenceNumber", exc );
        }
	}

    /**
     * unassign SequenceNumber on DiagramObjectPoint
     * @param		 command UnAssignSequenceNumberFromDiagramObjectPointCommand
     */     
	@PutMapping("/unAssignSequenceNumber")
	public void unAssignSequenceNumber( @RequestBody(required=true)  UnAssignSequenceNumberFromDiagramObjectPointCommand command ) {
		try {
			DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().unAssignSequenceNumber( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign SequenceNumber", exc );
		}
	}
	
    /**
     * save XPosition on DiagramObjectPoint
     * @param		command AssignXPositionToDiagramObjectPointCommand
     */     
	@PutMapping("/assignXPosition")
	public void assignXPosition( @RequestBody AssignXPositionToDiagramObjectPointCommand command ) {
		try {
			DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().assignXPosition( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign XPosition", exc );
        }
	}

    /**
     * unassign XPosition on DiagramObjectPoint
     * @param		 command UnAssignXPositionFromDiagramObjectPointCommand
     */     
	@PutMapping("/unAssignXPosition")
	public void unAssignXPosition( @RequestBody(required=true)  UnAssignXPositionFromDiagramObjectPointCommand command ) {
		try {
			DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().unAssignXPosition( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign XPosition", exc );
		}
	}
	
    /**
     * save YPosition on DiagramObjectPoint
     * @param		command AssignYPositionToDiagramObjectPointCommand
     */     
	@PutMapping("/assignYPosition")
	public void assignYPosition( @RequestBody AssignYPositionToDiagramObjectPointCommand command ) {
		try {
			DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().assignYPosition( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign YPosition", exc );
        }
	}

    /**
     * unassign YPosition on DiagramObjectPoint
     * @param		 command UnAssignYPositionFromDiagramObjectPointCommand
     */     
	@PutMapping("/unAssignYPosition")
	public void unAssignYPosition( @RequestBody(required=true)  UnAssignYPositionFromDiagramObjectPointCommand command ) {
		try {
			DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().unAssignYPosition( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign YPosition", exc );
		}
	}
	
    /**
     * save ZPosition on DiagramObjectPoint
     * @param		command AssignZPositionToDiagramObjectPointCommand
     */     
	@PutMapping("/assignZPosition")
	public void assignZPosition( @RequestBody AssignZPositionToDiagramObjectPointCommand command ) {
		try {
			DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().assignZPosition( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign ZPosition", exc );
        }
	}

    /**
     * unassign ZPosition on DiagramObjectPoint
     * @param		 command UnAssignZPositionFromDiagramObjectPointCommand
     */     
	@PutMapping("/unAssignZPosition")
	public void unAssignZPosition( @RequestBody(required=true)  UnAssignZPositionFromDiagramObjectPointCommand command ) {
		try {
			DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().unAssignZPosition( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign ZPosition", exc );
		}
	}
	
    /**
     * save DiagramObject on DiagramObjectPoint
     * @param		command AssignDiagramObjectToDiagramObjectPointCommand
     */     
	@PutMapping("/assignDiagramObject")
	public void assignDiagramObject( @RequestBody AssignDiagramObjectToDiagramObjectPointCommand command ) {
		try {
			DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().assignDiagramObject( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign DiagramObject", exc );
        }
	}

    /**
     * unassign DiagramObject on DiagramObjectPoint
     * @param		 command UnAssignDiagramObjectFromDiagramObjectPointCommand
     */     
	@PutMapping("/unAssignDiagramObject")
	public void unAssignDiagramObject( @RequestBody(required=true)  UnAssignDiagramObjectFromDiagramObjectPointCommand command ) {
		try {
			DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().unAssignDiagramObject( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign DiagramObject", exc );
		}
	}
	
    /**
     * save DiagramObjectGluePoint on DiagramObjectPoint
     * @param		command AssignDiagramObjectGluePointToDiagramObjectPointCommand
     */     
	@PutMapping("/assignDiagramObjectGluePoint")
	public void assignDiagramObjectGluePoint( @RequestBody AssignDiagramObjectGluePointToDiagramObjectPointCommand command ) {
		try {
			DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().assignDiagramObjectGluePoint( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign DiagramObjectGluePoint", exc );
        }
	}

    /**
     * unassign DiagramObjectGluePoint on DiagramObjectPoint
     * @param		 command UnAssignDiagramObjectGluePointFromDiagramObjectPointCommand
     */     
	@PutMapping("/unAssignDiagramObjectGluePoint")
	public void unAssignDiagramObjectGluePoint( @RequestBody(required=true)  UnAssignDiagramObjectGluePointFromDiagramObjectPointCommand command ) {
		try {
			DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().unAssignDiagramObjectGluePoint( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign DiagramObjectGluePoint", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected DiagramObjectPoint diagramObjectPoint = null;
    private static final Logger LOGGER = Logger.getLogger(DiagramObjectPointCommandRestController.class.getName());
    
}
