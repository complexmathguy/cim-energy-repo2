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
 * RegularTimePoint business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of RegularTimePoint related services in the case of a RegularTimePoint business related service failing.</li>
 * <li>Exposes a simpler, uniform RegularTimePoint interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill RegularTimePoint business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class RegularTimePointBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public RegularTimePointBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* RegularTimePoint Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	RegularTimePointBusinessDelegate
	*/
	public static RegularTimePointBusinessDelegate getRegularTimePointInstance() {
		return( new RegularTimePointBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createRegularTimePoint( CreateRegularTimePointCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getRegularTimePointId() == null )
				command.setRegularTimePointId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	RegularTimePointValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateRegularTimePointCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateRegularTimePointCommand of RegularTimePoint is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create RegularTimePoint - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateRegularTimePointCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateRegularTimePoint( UpdateRegularTimePointCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	RegularTimePointValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateRegularTimePointCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save RegularTimePoint - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteRegularTimePointCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteRegularTimePointCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	RegularTimePointValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteRegularTimePointCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete RegularTimePoint using Id = "  + command.getRegularTimePointId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the RegularTimePoint via RegularTimePointFetchOneSummary
     * @param 	summary RegularTimePointFetchOneSummary 
     * @return 	RegularTimePointFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public RegularTimePoint getRegularTimePoint( RegularTimePointFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "RegularTimePointFetchOneSummary arg cannot be null" );
    	
    	RegularTimePoint entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	RegularTimePointValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a RegularTimePoint
        	// --------------------------------------
        	CompletableFuture<RegularTimePoint> futureEntity = queryGateway.query(new FindRegularTimePointQuery( new LoadRegularTimePointFilter( summary.getRegularTimePointId() ) ), ResponseTypes.instanceOf(RegularTimePoint.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate RegularTimePoint with id " + summary.getRegularTimePointId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all RegularTimePoints
     *
     * @return 	List<RegularTimePoint> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<RegularTimePoint> getAllRegularTimePoint() 
    throws ProcessingException {
        List<RegularTimePoint> list = null;

        try {
        	CompletableFuture<List<RegularTimePoint>> futureList = queryGateway.query(new FindAllRegularTimePointQuery(), ResponseTypes.multipleInstancesOf(RegularTimePoint.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all RegularTimePoint";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

    /**
     * assign SequenceNumber on RegularTimePoint
     * @param		command AssignSequenceNumberToRegularTimePointCommand	
     * @exception	ProcessingException
     */     
	public void assignSequenceNumber( AssignSequenceNumberToRegularTimePointCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getRegularTimePointId() );
		
		IntegerProxyBusinessDelegate childDelegate 	= IntegerProxyBusinessDelegate.getIntegerProxyInstance();
		RegularTimePointBusinessDelegate parentDelegate = RegularTimePointBusinessDelegate.getRegularTimePointInstance();			
		UUID childId = command.getAssignment().getIntegerProxyId();
		IntegerProxy child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	RegularTimePointValidator.getInstance().validate( command );    

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
     * unAssign SequenceNumber on RegularTimePoint
     * @param		command UnAssignSequenceNumberFromRegularTimePointCommand
     * @exception	ProcessingException
     */     
	public void unAssignSequenceNumber( UnAssignSequenceNumberFromRegularTimePointCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	RegularTimePointValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign SequenceNumber on RegularTimePoint";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign Value1 on RegularTimePoint
     * @param		command AssignValue1ToRegularTimePointCommand	
     * @exception	ProcessingException
     */     
	public void assignValue1( AssignValue1ToRegularTimePointCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getRegularTimePointId() );
		
		Simple_FloatBusinessDelegate childDelegate 	= Simple_FloatBusinessDelegate.getSimple_FloatInstance();
		RegularTimePointBusinessDelegate parentDelegate = RegularTimePointBusinessDelegate.getRegularTimePointInstance();			
		UUID childId = command.getAssignment().getSimple_FloatId();
		Simple_Float child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	RegularTimePointValidator.getInstance().validate( command );    

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
     * unAssign Value1 on RegularTimePoint
     * @param		command UnAssignValue1FromRegularTimePointCommand
     * @exception	ProcessingException
     */     
	public void unAssignValue1( UnAssignValue1FromRegularTimePointCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	RegularTimePointValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign Value1 on RegularTimePoint";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign Value2 on RegularTimePoint
     * @param		command AssignValue2ToRegularTimePointCommand	
     * @exception	ProcessingException
     */     
	public void assignValue2( AssignValue2ToRegularTimePointCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getRegularTimePointId() );
		
		Simple_FloatBusinessDelegate childDelegate 	= Simple_FloatBusinessDelegate.getSimple_FloatInstance();
		RegularTimePointBusinessDelegate parentDelegate = RegularTimePointBusinessDelegate.getRegularTimePointInstance();			
		UUID childId = command.getAssignment().getSimple_FloatId();
		Simple_Float child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	RegularTimePointValidator.getInstance().validate( command );    

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
     * unAssign Value2 on RegularTimePoint
     * @param		command UnAssignValue2FromRegularTimePointCommand
     * @exception	ProcessingException
     */     
	public void unAssignValue2( UnAssignValue2FromRegularTimePointCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	RegularTimePointValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign Value2 on RegularTimePoint";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign IntervalSchedule on RegularTimePoint
     * @param		command AssignIntervalScheduleToRegularTimePointCommand	
     * @exception	ProcessingException
     */     
	public void assignIntervalSchedule( AssignIntervalScheduleToRegularTimePointCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getRegularTimePointId() );
		
		RegularIntervalScheduleBusinessDelegate childDelegate 	= RegularIntervalScheduleBusinessDelegate.getRegularIntervalScheduleInstance();
		RegularTimePointBusinessDelegate parentDelegate = RegularTimePointBusinessDelegate.getRegularTimePointInstance();			
		UUID childId = command.getAssignment().getRegularIntervalScheduleId();
		RegularIntervalSchedule child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	RegularTimePointValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get RegularIntervalSchedule using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign IntervalSchedule on RegularTimePoint
     * @param		command UnAssignIntervalScheduleFromRegularTimePointCommand
     * @exception	ProcessingException
     */     
	public void unAssignIntervalSchedule( UnAssignIntervalScheduleFromRegularTimePointCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	RegularTimePointValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign IntervalSchedule on RegularTimePoint";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	



	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		RegularTimePoint
	 */
	protected RegularTimePoint load( UUID id ) throws ProcessingException {
		regularTimePoint = RegularTimePointBusinessDelegate.getRegularTimePointInstance().getRegularTimePoint( new RegularTimePointFetchOneSummary(id) );	
		return regularTimePoint;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private RegularTimePoint regularTimePoint 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(RegularTimePointBusinessDelegate.class.getName());
    
}
