/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.delegate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.util.concurrent.*;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.validator.*;

/**
 * DiagramObject business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of DiagramObject related services in the case of a DiagramObject business related service failing.</li>
 * <li>Exposes a simpler, uniform DiagramObject interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill DiagramObject business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class DiagramObjectBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public DiagramObjectBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* DiagramObject Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	DiagramObjectBusinessDelegate
	*/
	public static DiagramObjectBusinessDelegate getDiagramObjectInstance() {
		return( new DiagramObjectBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createDiagramObject( CreateDiagramObjectCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getDiagramObjectId() == null )
				command.setDiagramObjectId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	DiagramObjectValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateDiagramObjectCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateDiagramObjectCommand of DiagramObject is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create DiagramObject - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateDiagramObjectCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateDiagramObject( UpdateDiagramObjectCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	DiagramObjectValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateDiagramObjectCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save DiagramObject - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteDiagramObjectCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteDiagramObjectCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	DiagramObjectValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteDiagramObjectCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete DiagramObject using Id = "  + command.getDiagramObjectId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the DiagramObject via DiagramObjectFetchOneSummary
     * @param 	summary DiagramObjectFetchOneSummary 
     * @return 	DiagramObjectFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public DiagramObject getDiagramObject( DiagramObjectFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "DiagramObjectFetchOneSummary arg cannot be null" );
    	
    	DiagramObject entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	DiagramObjectValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a DiagramObject
        	// --------------------------------------
        	CompletableFuture<DiagramObject> futureEntity = queryGateway.query(new FindDiagramObjectQuery( new LoadDiagramObjectFilter( summary.getDiagramObjectId() ) ), ResponseTypes.instanceOf(DiagramObject.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate DiagramObject with id " + summary.getDiagramObjectId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all DiagramObjects
     *
     * @return 	List<DiagramObject> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<DiagramObject> getAllDiagramObject() 
    throws ProcessingException {
        List<DiagramObject> list = null;

        try {
        	CompletableFuture<List<DiagramObject>> futureList = queryGateway.query(new FindAllDiagramObjectQuery(), ResponseTypes.multipleInstancesOf(DiagramObject.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all DiagramObject";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

    /**
     * assign DrawingOrder on DiagramObject
     * @param		command AssignDrawingOrderToDiagramObjectCommand	
     * @exception	ProcessingException
     */     
	public void assignDrawingOrder( AssignDrawingOrderToDiagramObjectCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getDiagramObjectId() );
		
		IntegerProxyBusinessDelegate childDelegate 	= IntegerProxyBusinessDelegate.getIntegerProxyInstance();
		DiagramObjectBusinessDelegate parentDelegate = DiagramObjectBusinessDelegate.getDiagramObjectInstance();			
		UUID childId = command.getAssignment().getIntegerProxyId();
		IntegerProxy child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get IntegerProxy using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign DrawingOrder on DiagramObject
     * @param		command UnAssignDrawingOrderFromDiagramObjectCommand
     * @exception	ProcessingException
     */     
	public void unAssignDrawingOrder( UnAssignDrawingOrderFromDiagramObjectCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign DrawingOrder on DiagramObject";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign PolygonFlag on DiagramObject
     * @param		command AssignPolygonFlagToDiagramObjectCommand	
     * @exception	ProcessingException
     */     
	public void assignPolygonFlag( AssignPolygonFlagToDiagramObjectCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getDiagramObjectId() );
		
		BooleanProxyBusinessDelegate childDelegate 	= BooleanProxyBusinessDelegate.getBooleanProxyInstance();
		DiagramObjectBusinessDelegate parentDelegate = DiagramObjectBusinessDelegate.getDiagramObjectInstance();			
		UUID childId = command.getAssignment().getBooleanProxyId();
		BooleanProxy child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get BooleanProxy using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign PolygonFlag on DiagramObject
     * @param		command UnAssignPolygonFlagFromDiagramObjectCommand
     * @exception	ProcessingException
     */     
	public void unAssignPolygonFlag( UnAssignPolygonFlagFromDiagramObjectCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign PolygonFlag on DiagramObject";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign OffsetX on DiagramObject
     * @param		command AssignOffsetXToDiagramObjectCommand	
     * @exception	ProcessingException
     */     
	public void assignOffsetX( AssignOffsetXToDiagramObjectCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getDiagramObjectId() );
		
		Simple_FloatBusinessDelegate childDelegate 	= Simple_FloatBusinessDelegate.getSimple_FloatInstance();
		DiagramObjectBusinessDelegate parentDelegate = DiagramObjectBusinessDelegate.getDiagramObjectInstance();			
		UUID childId = command.getAssignment().getSimple_FloatId();
		Simple_Float child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get Simple_Float using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign OffsetX on DiagramObject
     * @param		command UnAssignOffsetXFromDiagramObjectCommand
     * @exception	ProcessingException
     */     
	public void unAssignOffsetX( UnAssignOffsetXFromDiagramObjectCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign OffsetX on DiagramObject";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign OffsetY on DiagramObject
     * @param		command AssignOffsetYToDiagramObjectCommand	
     * @exception	ProcessingException
     */     
	public void assignOffsetY( AssignOffsetYToDiagramObjectCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getDiagramObjectId() );
		
		Simple_FloatBusinessDelegate childDelegate 	= Simple_FloatBusinessDelegate.getSimple_FloatInstance();
		DiagramObjectBusinessDelegate parentDelegate = DiagramObjectBusinessDelegate.getDiagramObjectInstance();			
		UUID childId = command.getAssignment().getSimple_FloatId();
		Simple_Float child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get Simple_Float using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign OffsetY on DiagramObject
     * @param		command UnAssignOffsetYFromDiagramObjectCommand
     * @exception	ProcessingException
     */     
	public void unAssignOffsetY( UnAssignOffsetYFromDiagramObjectCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign OffsetY on DiagramObject";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign Rotation on DiagramObject
     * @param		command AssignRotationToDiagramObjectCommand	
     * @exception	ProcessingException
     */     
	public void assignRotation( AssignRotationToDiagramObjectCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getDiagramObjectId() );
		
		AngleDegreesBusinessDelegate childDelegate 	= AngleDegreesBusinessDelegate.getAngleDegreesInstance();
		DiagramObjectBusinessDelegate parentDelegate = DiagramObjectBusinessDelegate.getDiagramObjectInstance();			
		UUID childId = command.getAssignment().getAngleDegreesId();
		AngleDegrees child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get AngleDegrees using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign Rotation on DiagramObject
     * @param		command UnAssignRotationFromDiagramObjectCommand
     * @exception	ProcessingException
     */     
	public void unAssignRotation( UnAssignRotationFromDiagramObjectCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign Rotation on DiagramObject";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign DiagramObjectStyle on DiagramObject
     * @param		command AssignDiagramObjectStyleToDiagramObjectCommand	
     * @exception	ProcessingException
     */     
	public void assignDiagramObjectStyle( AssignDiagramObjectStyleToDiagramObjectCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getDiagramObjectId() );
		
		DiagramObjectStyleBusinessDelegate childDelegate 	= DiagramObjectStyleBusinessDelegate.getDiagramObjectStyleInstance();
		DiagramObjectBusinessDelegate parentDelegate = DiagramObjectBusinessDelegate.getDiagramObjectInstance();			
		UUID childId = command.getAssignment().getDiagramObjectStyleId();
		DiagramObjectStyle child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get DiagramObjectStyle using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign DiagramObjectStyle on DiagramObject
     * @param		command UnAssignDiagramObjectStyleFromDiagramObjectCommand
     * @exception	ProcessingException
     */     
	public void unAssignDiagramObjectStyle( UnAssignDiagramObjectStyleFromDiagramObjectCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign DiagramObjectStyle on DiagramObject";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign Diagram on DiagramObject
     * @param		command AssignDiagramToDiagramObjectCommand	
     * @exception	ProcessingException
     */     
	public void assignDiagram( AssignDiagramToDiagramObjectCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getDiagramObjectId() );
		
		DiagramBusinessDelegate childDelegate 	= DiagramBusinessDelegate.getDiagramInstance();
		DiagramObjectBusinessDelegate parentDelegate = DiagramObjectBusinessDelegate.getDiagramObjectInstance();			
		UUID childId = command.getAssignment().getDiagramId();
		Diagram child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get Diagram using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign Diagram on DiagramObject
     * @param		command UnAssignDiagramFromDiagramObjectCommand
     * @exception	ProcessingException
     */     
	public void unAssignDiagram( UnAssignDiagramFromDiagramObjectCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign Diagram on DiagramObject";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign IdentifiedObject on DiagramObject
     * @param		command AssignIdentifiedObjectToDiagramObjectCommand	
     * @exception	ProcessingException
     */     
	public void assignIdentifiedObject( AssignIdentifiedObjectToDiagramObjectCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getDiagramObjectId() );
		
		IdentifiedObjectBusinessDelegate childDelegate 	= IdentifiedObjectBusinessDelegate.getIdentifiedObjectInstance();
		DiagramObjectBusinessDelegate parentDelegate = DiagramObjectBusinessDelegate.getDiagramObjectInstance();			
		UUID childId = command.getAssignment().getIdentifiedObjectId();
		IdentifiedObject child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get IdentifiedObject using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign IdentifiedObject on DiagramObject
     * @param		command UnAssignIdentifiedObjectFromDiagramObjectCommand
     * @exception	ProcessingException
     */     
	public void unAssignIdentifiedObject( UnAssignIdentifiedObjectFromDiagramObjectCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DiagramObjectValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign IdentifiedObject on DiagramObject";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	



	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		DiagramObject
	 */
	protected DiagramObject load( UUID id ) throws ProcessingException {
		diagramObject = DiagramObjectBusinessDelegate.getDiagramObjectInstance().getDiagramObject( new DiagramObjectFetchOneSummary(id) );	
		return diagramObject;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private DiagramObject diagramObject 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(DiagramObjectBusinessDelegate.class.getName());
    
}
