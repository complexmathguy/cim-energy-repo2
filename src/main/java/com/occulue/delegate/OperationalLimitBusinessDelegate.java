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
 * OperationalLimit business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of OperationalLimit related services in the case of a OperationalLimit business related service failing.</li>
 * <li>Exposes a simpler, uniform OperationalLimit interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill OperationalLimit business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class OperationalLimitBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public OperationalLimitBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* OperationalLimit Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	OperationalLimitBusinessDelegate
	*/
	public static OperationalLimitBusinessDelegate getOperationalLimitInstance() {
		return( new OperationalLimitBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createOperationalLimit( CreateOperationalLimitCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getOperationalLimitId() == null )
				command.setOperationalLimitId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	OperationalLimitValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateOperationalLimitCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateOperationalLimitCommand of OperationalLimit is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create OperationalLimit - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateOperationalLimitCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateOperationalLimit( UpdateOperationalLimitCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	OperationalLimitValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateOperationalLimitCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save OperationalLimit - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteOperationalLimitCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteOperationalLimitCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	OperationalLimitValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteOperationalLimitCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete OperationalLimit using Id = "  + command.getOperationalLimitId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the OperationalLimit via OperationalLimitFetchOneSummary
     * @param 	summary OperationalLimitFetchOneSummary 
     * @return 	OperationalLimitFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public OperationalLimit getOperationalLimit( OperationalLimitFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "OperationalLimitFetchOneSummary arg cannot be null" );
    	
    	OperationalLimit entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	OperationalLimitValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a OperationalLimit
        	// --------------------------------------
        	CompletableFuture<OperationalLimit> futureEntity = queryGateway.query(new FindOperationalLimitQuery( new LoadOperationalLimitFilter( summary.getOperationalLimitId() ) ), ResponseTypes.instanceOf(OperationalLimit.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate OperationalLimit with id " + summary.getOperationalLimitId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all OperationalLimits
     *
     * @return 	List<OperationalLimit> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<OperationalLimit> getAllOperationalLimit() 
    throws ProcessingException {
        List<OperationalLimit> list = null;

        try {
        	CompletableFuture<List<OperationalLimit>> futureList = queryGateway.query(new FindAllOperationalLimitQuery(), ResponseTypes.multipleInstancesOf(OperationalLimit.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all OperationalLimit";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

    /**
     * assign OperationalLimitType on OperationalLimit
     * @param		command AssignOperationalLimitTypeToOperationalLimitCommand	
     * @exception	ProcessingException
     */     
	public void assignOperationalLimitType( AssignOperationalLimitTypeToOperationalLimitCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getOperationalLimitId() );
		
		OperationalLimitTypeBusinessDelegate childDelegate 	= OperationalLimitTypeBusinessDelegate.getOperationalLimitTypeInstance();
		OperationalLimitBusinessDelegate parentDelegate = OperationalLimitBusinessDelegate.getOperationalLimitInstance();			
		UUID childId = command.getAssignment().getOperationalLimitTypeId();
		OperationalLimitType child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	OperationalLimitValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get OperationalLimitType using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign OperationalLimitType on OperationalLimit
     * @param		command UnAssignOperationalLimitTypeFromOperationalLimitCommand
     * @exception	ProcessingException
     */     
	public void unAssignOperationalLimitType( UnAssignOperationalLimitTypeFromOperationalLimitCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	OperationalLimitValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign OperationalLimitType on OperationalLimit";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	

    /**
     * add OperationalLimitSet to OperationalLimitSet 
     * @param		command AssignOperationalLimitSetToOperationalLimitCommand
     * @exception	ProcessingException
     */     
	public void addToOperationalLimitSet( AssignOperationalLimitSetToOperationalLimitCommand command ) throws ProcessingException {
		
		
		// -------------------------------------------
		// load the parent
		// -------------------------------------------
		load( command.getOperationalLimitId() );

		OperationalLimitSetBusinessDelegate childDelegate 	= OperationalLimitSetBusinessDelegate.getOperationalLimitSetInstance();
		OperationalLimitBusinessDelegate parentDelegate = OperationalLimitBusinessDelegate.getOperationalLimitInstance();		
		UUID childId = command.getAddTo().getOperationalLimitSetId();
		
		try {		
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	OperationalLimitValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );			
		}
		catch( Exception exc ) {
			final String msg = "Failed to add a OperationalLimitSet as OperationalLimitSet to OperationalLimit" ; 
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}

	}

    /**
     * remove OperationalLimitSet from OperationalLimitSet
     * @param		command RemoveOperationalLimitSetFromOperationalLimitCommand
     * @exception	ProcessingException
     */     	
	public void removeFromOperationalLimitSet( RemoveOperationalLimitSetFromOperationalLimitCommand command ) throws ProcessingException {		
		
		OperationalLimitSetBusinessDelegate childDelegate 	= OperationalLimitSetBusinessDelegate.getOperationalLimitSetInstance();
		UUID childId = command.getRemoveFrom().getOperationalLimitSetId();

		try {
			
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	OperationalLimitValidator.getInstance().validate( command );    

	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );

		}
		catch( Exception exc ) {
			final String msg = "Failed to remove child using Id " + childId; 
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}



	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		OperationalLimit
	 */
	protected OperationalLimit load( UUID id ) throws ProcessingException {
		operationalLimit = OperationalLimitBusinessDelegate.getOperationalLimitInstance().getOperationalLimit( new OperationalLimitFetchOneSummary(id) );	
		return operationalLimit;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private OperationalLimit operationalLimit 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(OperationalLimitBusinessDelegate.class.getName());
    
}
