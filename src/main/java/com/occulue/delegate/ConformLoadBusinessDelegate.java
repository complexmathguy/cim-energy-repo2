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
 * ConformLoad business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of ConformLoad related services in the case of a ConformLoad business related service failing.</li>
 * <li>Exposes a simpler, uniform ConformLoad interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill ConformLoad business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class ConformLoadBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public ConformLoadBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* ConformLoad Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	ConformLoadBusinessDelegate
	*/
	public static ConformLoadBusinessDelegate getConformLoadInstance() {
		return( new ConformLoadBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createConformLoad( CreateConformLoadCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getConformLoadId() == null )
				command.setConformLoadId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	ConformLoadValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateConformLoadCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateConformLoadCommand of ConformLoad is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create ConformLoad - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateConformLoadCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateConformLoad( UpdateConformLoadCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	ConformLoadValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateConformLoadCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save ConformLoad - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteConformLoadCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteConformLoadCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	ConformLoadValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteConformLoadCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete ConformLoad using Id = "  + command.getConformLoadId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the ConformLoad via ConformLoadFetchOneSummary
     * @param 	summary ConformLoadFetchOneSummary 
     * @return 	ConformLoadFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public ConformLoad getConformLoad( ConformLoadFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "ConformLoadFetchOneSummary arg cannot be null" );
    	
    	ConformLoad entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	ConformLoadValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a ConformLoad
        	// --------------------------------------
        	CompletableFuture<ConformLoad> futureEntity = queryGateway.query(new FindConformLoadQuery( new LoadConformLoadFilter( summary.getConformLoadId() ) ), ResponseTypes.instanceOf(ConformLoad.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate ConformLoad with id " + summary.getConformLoadId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all ConformLoads
     *
     * @return 	List<ConformLoad> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<ConformLoad> getAllConformLoad() 
    throws ProcessingException {
        List<ConformLoad> list = null;

        try {
        	CompletableFuture<List<ConformLoad>> futureList = queryGateway.query(new FindAllConformLoadQuery(), ResponseTypes.multipleInstancesOf(ConformLoad.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all ConformLoad";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

    /**
     * assign LoadGroup on ConformLoad
     * @param		command AssignLoadGroupToConformLoadCommand	
     * @exception	ProcessingException
     */     
	public void assignLoadGroup( AssignLoadGroupToConformLoadCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getConformLoadId() );
		
		ConformLoadGroupBusinessDelegate childDelegate 	= ConformLoadGroupBusinessDelegate.getConformLoadGroupInstance();
		ConformLoadBusinessDelegate parentDelegate = ConformLoadBusinessDelegate.getConformLoadInstance();			
		UUID childId = command.getAssignment().getConformLoadGroupId();
		ConformLoadGroup child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ConformLoadValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get ConformLoadGroup using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign LoadGroup on ConformLoad
     * @param		command UnAssignLoadGroupFromConformLoadCommand
     * @exception	ProcessingException
     */     
	public void unAssignLoadGroup( UnAssignLoadGroupFromConformLoadCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ConformLoadValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign LoadGroup on ConformLoad";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	



	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		ConformLoad
	 */
	protected ConformLoad load( UUID id ) throws ProcessingException {
		conformLoad = ConformLoadBusinessDelegate.getConformLoadInstance().getConformLoad( new ConformLoadFetchOneSummary(id) );	
		return conformLoad;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private ConformLoad conformLoad 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(ConformLoadBusinessDelegate.class.getName());
    
}
