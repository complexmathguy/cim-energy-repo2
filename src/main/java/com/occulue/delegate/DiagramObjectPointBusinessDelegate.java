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
 * DiagramObjectPoint business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of DiagramObjectPoint related services in the case of a DiagramObjectPoint business related service failing.</li>
 * <li>Exposes a simpler, uniform DiagramObjectPoint interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill DiagramObjectPoint business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class DiagramObjectPointBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public DiagramObjectPointBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* DiagramObjectPoint Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	DiagramObjectPointBusinessDelegate
	*/
	public static DiagramObjectPointBusinessDelegate getDiagramObjectPointInstance() {
		return( new DiagramObjectPointBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createDiagramObjectPoint( CreateDiagramObjectPointCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getDiagramObjectPointId() == null )
				command.setDiagramObjectPointId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	DiagramObjectPointValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateDiagramObjectPointCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateDiagramObjectPointCommand of DiagramObjectPoint is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create DiagramObjectPoint - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateDiagramObjectPointCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateDiagramObjectPoint( UpdateDiagramObjectPointCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	DiagramObjectPointValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateDiagramObjectPointCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save DiagramObjectPoint - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteDiagramObjectPointCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteDiagramObjectPointCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	DiagramObjectPointValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteDiagramObjectPointCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete DiagramObjectPoint using Id = "  + command.getDiagramObjectPointId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the DiagramObjectPoint via DiagramObjectPointFetchOneSummary
     * @param 	summary DiagramObjectPointFetchOneSummary 
     * @return 	DiagramObjectPointFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public DiagramObjectPoint getDiagramObjectPoint( DiagramObjectPointFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "DiagramObjectPointFetchOneSummary arg cannot be null" );
    	
    	DiagramObjectPoint entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	DiagramObjectPointValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a DiagramObjectPoint
        	// --------------------------------------
        	CompletableFuture<DiagramObjectPoint> futureEntity = queryGateway.query(new FindDiagramObjectPointQuery( new LoadDiagramObjectPointFilter( summary.getDiagramObjectPointId() ) ), ResponseTypes.instanceOf(DiagramObjectPoint.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate DiagramObjectPoint with id " + summary.getDiagramObjectPointId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all DiagramObjectPoints
     *
     * @return 	List<DiagramObjectPoint> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<DiagramObjectPoint> getAllDiagramObjectPoint() 
    throws ProcessingException {
        List<DiagramObjectPoint> list = null;

        try {
        	CompletableFuture<List<DiagramObjectPoint>> futureList = queryGateway.query(new FindAllDiagramObjectPointQuery(), ResponseTypes.multipleInstancesOf(DiagramObjectPoint.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all DiagramObjectPoint";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

    /**
     * assign SequenceNumber on DiagramObjectPoint
     * @param		command AssignSequenceNumberToDiagramObjectPointCommand	
     * @exception	ProcessingException
     */     
	public void assignSequenceNumber( AssignSequenceNumberToDiagramObjectPointCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getDiagramObjectPointId() );
		
		IntegerProxyBusinessDelegate childDelegate 	= IntegerProxyBusinessDelegate.getIntegerProxyInstance();
		DiagramObjectPointBusinessDelegate parentDelegate = DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance();			
		UUID childId = command.getAssignment().getIntegerProxyId();
		IntegerProxy child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	DiagramObjectPointValidator.getInstance().validate( command );    

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
     * unAssign SequenceNumber on DiagramObjectPoint
     * @param		command UnAssignSequenceNumberFromDiagramObjectPointCommand
     * @exception	ProcessingException
     */     
	public void unAssignSequenceNumber( UnAssignSequenceNumberFromDiagramObjectPointCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DiagramObjectPointValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign SequenceNumber on DiagramObjectPoint";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign XPosition on DiagramObjectPoint
     * @param		command AssignXPositionToDiagramObjectPointCommand	
     * @exception	ProcessingException
     */     
	public void assignXPosition( AssignXPositionToDiagramObjectPointCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getDiagramObjectPointId() );
		
		Simple_FloatBusinessDelegate childDelegate 	= Simple_FloatBusinessDelegate.getSimple_FloatInstance();
		DiagramObjectPointBusinessDelegate parentDelegate = DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance();			
		UUID childId = command.getAssignment().getSimple_FloatId();
		Simple_Float child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	DiagramObjectPointValidator.getInstance().validate( command );    

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
     * unAssign XPosition on DiagramObjectPoint
     * @param		command UnAssignXPositionFromDiagramObjectPointCommand
     * @exception	ProcessingException
     */     
	public void unAssignXPosition( UnAssignXPositionFromDiagramObjectPointCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DiagramObjectPointValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign XPosition on DiagramObjectPoint";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign YPosition on DiagramObjectPoint
     * @param		command AssignYPositionToDiagramObjectPointCommand	
     * @exception	ProcessingException
     */     
	public void assignYPosition( AssignYPositionToDiagramObjectPointCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getDiagramObjectPointId() );
		
		Simple_FloatBusinessDelegate childDelegate 	= Simple_FloatBusinessDelegate.getSimple_FloatInstance();
		DiagramObjectPointBusinessDelegate parentDelegate = DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance();			
		UUID childId = command.getAssignment().getSimple_FloatId();
		Simple_Float child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	DiagramObjectPointValidator.getInstance().validate( command );    

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
     * unAssign YPosition on DiagramObjectPoint
     * @param		command UnAssignYPositionFromDiagramObjectPointCommand
     * @exception	ProcessingException
     */     
	public void unAssignYPosition( UnAssignYPositionFromDiagramObjectPointCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DiagramObjectPointValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign YPosition on DiagramObjectPoint";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign ZPosition on DiagramObjectPoint
     * @param		command AssignZPositionToDiagramObjectPointCommand	
     * @exception	ProcessingException
     */     
	public void assignZPosition( AssignZPositionToDiagramObjectPointCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getDiagramObjectPointId() );
		
		Simple_FloatBusinessDelegate childDelegate 	= Simple_FloatBusinessDelegate.getSimple_FloatInstance();
		DiagramObjectPointBusinessDelegate parentDelegate = DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance();			
		UUID childId = command.getAssignment().getSimple_FloatId();
		Simple_Float child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	DiagramObjectPointValidator.getInstance().validate( command );    

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
     * unAssign ZPosition on DiagramObjectPoint
     * @param		command UnAssignZPositionFromDiagramObjectPointCommand
     * @exception	ProcessingException
     */     
	public void unAssignZPosition( UnAssignZPositionFromDiagramObjectPointCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DiagramObjectPointValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign ZPosition on DiagramObjectPoint";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign DiagramObject on DiagramObjectPoint
     * @param		command AssignDiagramObjectToDiagramObjectPointCommand	
     * @exception	ProcessingException
     */     
	public void assignDiagramObject( AssignDiagramObjectToDiagramObjectPointCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getDiagramObjectPointId() );
		
		DiagramObjectBusinessDelegate childDelegate 	= DiagramObjectBusinessDelegate.getDiagramObjectInstance();
		DiagramObjectPointBusinessDelegate parentDelegate = DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance();			
		UUID childId = command.getAssignment().getDiagramObjectId();
		DiagramObject child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	DiagramObjectPointValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get DiagramObject using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign DiagramObject on DiagramObjectPoint
     * @param		command UnAssignDiagramObjectFromDiagramObjectPointCommand
     * @exception	ProcessingException
     */     
	public void unAssignDiagramObject( UnAssignDiagramObjectFromDiagramObjectPointCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DiagramObjectPointValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign DiagramObject on DiagramObjectPoint";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign DiagramObjectGluePoint on DiagramObjectPoint
     * @param		command AssignDiagramObjectGluePointToDiagramObjectPointCommand	
     * @exception	ProcessingException
     */     
	public void assignDiagramObjectGluePoint( AssignDiagramObjectGluePointToDiagramObjectPointCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getDiagramObjectPointId() );
		
		DiagramObjectGluePointBusinessDelegate childDelegate 	= DiagramObjectGluePointBusinessDelegate.getDiagramObjectGluePointInstance();
		DiagramObjectPointBusinessDelegate parentDelegate = DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance();			
		UUID childId = command.getAssignment().getDiagramObjectGluePointId();
		DiagramObjectGluePoint child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	DiagramObjectPointValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get DiagramObjectGluePoint using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign DiagramObjectGluePoint on DiagramObjectPoint
     * @param		command UnAssignDiagramObjectGluePointFromDiagramObjectPointCommand
     * @exception	ProcessingException
     */     
	public void unAssignDiagramObjectGluePoint( UnAssignDiagramObjectGluePointFromDiagramObjectPointCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	DiagramObjectPointValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign DiagramObjectGluePoint on DiagramObjectPoint";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	



	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		DiagramObjectPoint
	 */
	protected DiagramObjectPoint load( UUID id ) throws ProcessingException {
		diagramObjectPoint = DiagramObjectPointBusinessDelegate.getDiagramObjectPointInstance().getDiagramObjectPoint( new DiagramObjectPointFetchOneSummary(id) );	
		return diagramObjectPoint;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private DiagramObjectPoint diagramObjectPoint 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(DiagramObjectPointBusinessDelegate.class.getName());
    
}
