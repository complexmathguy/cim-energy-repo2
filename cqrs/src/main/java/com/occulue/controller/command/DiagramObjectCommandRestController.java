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
 * Implements Spring Controller command CQRS processing for entity DiagramObject.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/DiagramObject")
public class DiagramObjectCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a DiagramObject.  if not key provided, calls create, otherwise calls save
     * @param		DiagramObject	diagramObject
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateDiagramObjectCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = DiagramObjectBusinessDelegate.getDiagramObjectInstance().createDiagramObject( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a DiagramObject.  if no key provided, calls create, otherwise calls save
     * @param		DiagramObject diagramObject
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateDiagramObjectCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateDiagramObjectCommand
			// -----------------------------------------------
			completableFuture = DiagramObjectBusinessDelegate.getDiagramObjectInstance().updateDiagramObject(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "DiagramObjectController:update() - successfully update DiagramObject - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a DiagramObject entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID diagramObjectId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteDiagramObjectCommand command = new DeleteDiagramObjectCommand( diagramObjectId );

    	try {
        	DiagramObjectBusinessDelegate delegate = DiagramObjectBusinessDelegate.getDiagramObjectInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted DiagramObject with key " + command.getDiagramObjectId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save DrawingOrder on DiagramObject
     * @param		command AssignDrawingOrderToDiagramObjectCommand
     */     
	@PutMapping("/assignDrawingOrder")
	public void assignDrawingOrder( @RequestBody AssignDrawingOrderToDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().assignDrawingOrder( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign DrawingOrder", exc );
        }
	}

    /**
     * unassign DrawingOrder on DiagramObject
     * @param		 command UnAssignDrawingOrderFromDiagramObjectCommand
     */     
	@PutMapping("/unAssignDrawingOrder")
	public void unAssignDrawingOrder( @RequestBody(required=true)  UnAssignDrawingOrderFromDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().unAssignDrawingOrder( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign DrawingOrder", exc );
		}
	}
	
    /**
     * save PolygonFlag on DiagramObject
     * @param		command AssignPolygonFlagToDiagramObjectCommand
     */     
	@PutMapping("/assignPolygonFlag")
	public void assignPolygonFlag( @RequestBody AssignPolygonFlagToDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().assignPolygonFlag( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign PolygonFlag", exc );
        }
	}

    /**
     * unassign PolygonFlag on DiagramObject
     * @param		 command UnAssignPolygonFlagFromDiagramObjectCommand
     */     
	@PutMapping("/unAssignPolygonFlag")
	public void unAssignPolygonFlag( @RequestBody(required=true)  UnAssignPolygonFlagFromDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().unAssignPolygonFlag( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign PolygonFlag", exc );
		}
	}
	
    /**
     * save OffsetX on DiagramObject
     * @param		command AssignOffsetXToDiagramObjectCommand
     */     
	@PutMapping("/assignOffsetX")
	public void assignOffsetX( @RequestBody AssignOffsetXToDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().assignOffsetX( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign OffsetX", exc );
        }
	}

    /**
     * unassign OffsetX on DiagramObject
     * @param		 command UnAssignOffsetXFromDiagramObjectCommand
     */     
	@PutMapping("/unAssignOffsetX")
	public void unAssignOffsetX( @RequestBody(required=true)  UnAssignOffsetXFromDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().unAssignOffsetX( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign OffsetX", exc );
		}
	}
	
    /**
     * save OffsetY on DiagramObject
     * @param		command AssignOffsetYToDiagramObjectCommand
     */     
	@PutMapping("/assignOffsetY")
	public void assignOffsetY( @RequestBody AssignOffsetYToDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().assignOffsetY( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign OffsetY", exc );
        }
	}

    /**
     * unassign OffsetY on DiagramObject
     * @param		 command UnAssignOffsetYFromDiagramObjectCommand
     */     
	@PutMapping("/unAssignOffsetY")
	public void unAssignOffsetY( @RequestBody(required=true)  UnAssignOffsetYFromDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().unAssignOffsetY( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign OffsetY", exc );
		}
	}
	
    /**
     * save Rotation on DiagramObject
     * @param		command AssignRotationToDiagramObjectCommand
     */     
	@PutMapping("/assignRotation")
	public void assignRotation( @RequestBody AssignRotationToDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().assignRotation( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Rotation", exc );
        }
	}

    /**
     * unassign Rotation on DiagramObject
     * @param		 command UnAssignRotationFromDiagramObjectCommand
     */     
	@PutMapping("/unAssignRotation")
	public void unAssignRotation( @RequestBody(required=true)  UnAssignRotationFromDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().unAssignRotation( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Rotation", exc );
		}
	}
	
    /**
     * save DiagramObjectStyle on DiagramObject
     * @param		command AssignDiagramObjectStyleToDiagramObjectCommand
     */     
	@PutMapping("/assignDiagramObjectStyle")
	public void assignDiagramObjectStyle( @RequestBody AssignDiagramObjectStyleToDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().assignDiagramObjectStyle( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign DiagramObjectStyle", exc );
        }
	}

    /**
     * unassign DiagramObjectStyle on DiagramObject
     * @param		 command UnAssignDiagramObjectStyleFromDiagramObjectCommand
     */     
	@PutMapping("/unAssignDiagramObjectStyle")
	public void unAssignDiagramObjectStyle( @RequestBody(required=true)  UnAssignDiagramObjectStyleFromDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().unAssignDiagramObjectStyle( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign DiagramObjectStyle", exc );
		}
	}
	
    /**
     * save Diagram on DiagramObject
     * @param		command AssignDiagramToDiagramObjectCommand
     */     
	@PutMapping("/assignDiagram")
	public void assignDiagram( @RequestBody AssignDiagramToDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().assignDiagram( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Diagram", exc );
        }
	}

    /**
     * unassign Diagram on DiagramObject
     * @param		 command UnAssignDiagramFromDiagramObjectCommand
     */     
	@PutMapping("/unAssignDiagram")
	public void unAssignDiagram( @RequestBody(required=true)  UnAssignDiagramFromDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().unAssignDiagram( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Diagram", exc );
		}
	}
	
    /**
     * save IdentifiedObject on DiagramObject
     * @param		command AssignIdentifiedObjectToDiagramObjectCommand
     */     
	@PutMapping("/assignIdentifiedObject")
	public void assignIdentifiedObject( @RequestBody AssignIdentifiedObjectToDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().assignIdentifiedObject( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign IdentifiedObject", exc );
        }
	}

    /**
     * unassign IdentifiedObject on DiagramObject
     * @param		 command UnAssignIdentifiedObjectFromDiagramObjectCommand
     */     
	@PutMapping("/unAssignIdentifiedObject")
	public void unAssignIdentifiedObject( @RequestBody(required=true)  UnAssignIdentifiedObjectFromDiagramObjectCommand command ) {
		try {
			DiagramObjectBusinessDelegate.getDiagramObjectInstance().unAssignIdentifiedObject( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign IdentifiedObject", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected DiagramObject diagramObject = null;
    private static final Logger LOGGER = Logger.getLogger(DiagramObjectCommandRestController.class.getName());
    
}
