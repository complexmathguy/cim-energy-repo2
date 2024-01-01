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
 * RemoteInputSignal business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of RemoteInputSignal related services in the case of a RemoteInputSignal business related service failing.</li>
 * <li>Exposes a simpler, uniform RemoteInputSignal interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill RemoteInputSignal business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class RemoteInputSignalBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public RemoteInputSignalBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* RemoteInputSignal Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	RemoteInputSignalBusinessDelegate
	*/
	public static RemoteInputSignalBusinessDelegate getRemoteInputSignalInstance() {
		return( new RemoteInputSignalBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createRemoteInputSignal( CreateRemoteInputSignalCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getRemoteInputSignalId() == null )
				command.setRemoteInputSignalId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	RemoteInputSignalValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateRemoteInputSignalCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateRemoteInputSignalCommand of RemoteInputSignal is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create RemoteInputSignal - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateRemoteInputSignalCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateRemoteInputSignal( UpdateRemoteInputSignalCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	RemoteInputSignalValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateRemoteInputSignalCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save RemoteInputSignal - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteRemoteInputSignalCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteRemoteInputSignalCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	RemoteInputSignalValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteRemoteInputSignalCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete RemoteInputSignal using Id = "  + command.getRemoteInputSignalId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the RemoteInputSignal via RemoteInputSignalFetchOneSummary
     * @param 	summary RemoteInputSignalFetchOneSummary 
     * @return 	RemoteInputSignalFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public RemoteInputSignal getRemoteInputSignal( RemoteInputSignalFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "RemoteInputSignalFetchOneSummary arg cannot be null" );
    	
    	RemoteInputSignal entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	RemoteInputSignalValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a RemoteInputSignal
        	// --------------------------------------
        	CompletableFuture<RemoteInputSignal> futureEntity = queryGateway.query(new FindRemoteInputSignalQuery( new LoadRemoteInputSignalFilter( summary.getRemoteInputSignalId() ) ), ResponseTypes.instanceOf(RemoteInputSignal.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate RemoteInputSignal with id " + summary.getRemoteInputSignalId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all RemoteInputSignals
     *
     * @return 	List<RemoteInputSignal> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<RemoteInputSignal> getAllRemoteInputSignal() 
    throws ProcessingException {
        List<RemoteInputSignal> list = null;

        try {
        	CompletableFuture<List<RemoteInputSignal>> futureList = queryGateway.query(new FindAllRemoteInputSignalQuery(), ResponseTypes.multipleInstancesOf(RemoteInputSignal.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all RemoteInputSignal";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

    /**
     * assign Terminal on RemoteInputSignal
     * @param		command AssignTerminalToRemoteInputSignalCommand	
     * @exception	ProcessingException
     */     
	public void assignTerminal( AssignTerminalToRemoteInputSignalCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getRemoteInputSignalId() );
		
		TerminalBusinessDelegate childDelegate 	= TerminalBusinessDelegate.getTerminalInstance();
		RemoteInputSignalBusinessDelegate parentDelegate = RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance();			
		UUID childId = command.getAssignment().getTerminalId();
		Terminal child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	RemoteInputSignalValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get Terminal using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign Terminal on RemoteInputSignal
     * @param		command UnAssignTerminalFromRemoteInputSignalCommand
     * @exception	ProcessingException
     */     
	public void unAssignTerminal( UnAssignTerminalFromRemoteInputSignalCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	RemoteInputSignalValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign Terminal on RemoteInputSignal";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign DiscontinuousExcitationControlDynamics on RemoteInputSignal
     * @param		command AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalCommand	
     * @exception	ProcessingException
     */     
	public void assignDiscontinuousExcitationControlDynamics( AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getRemoteInputSignalId() );
		
		DiscontinuousExcitationControlDynamicsBusinessDelegate childDelegate 	= DiscontinuousExcitationControlDynamicsBusinessDelegate.getDiscontinuousExcitationControlDynamicsInstance();
		RemoteInputSignalBusinessDelegate parentDelegate = RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance();			
		UUID childId = command.getAssignment().getDiscontinuousExcitationControlDynamicsId();
		DiscontinuousExcitationControlDynamics child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	RemoteInputSignalValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get DiscontinuousExcitationControlDynamics using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign DiscontinuousExcitationControlDynamics on RemoteInputSignal
     * @param		command UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalCommand
     * @exception	ProcessingException
     */     
	public void unAssignDiscontinuousExcitationControlDynamics( UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	RemoteInputSignalValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign DiscontinuousExcitationControlDynamics on RemoteInputSignal";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign PowerSystemStabilizerDynamics on RemoteInputSignal
     * @param		command AssignPowerSystemStabilizerDynamicsToRemoteInputSignalCommand	
     * @exception	ProcessingException
     */     
	public void assignPowerSystemStabilizerDynamics( AssignPowerSystemStabilizerDynamicsToRemoteInputSignalCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getRemoteInputSignalId() );
		
		PowerSystemStabilizerDynamicsBusinessDelegate childDelegate 	= PowerSystemStabilizerDynamicsBusinessDelegate.getPowerSystemStabilizerDynamicsInstance();
		RemoteInputSignalBusinessDelegate parentDelegate = RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance();			
		UUID childId = command.getAssignment().getPowerSystemStabilizerDynamicsId();
		PowerSystemStabilizerDynamics child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	RemoteInputSignalValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get PowerSystemStabilizerDynamics using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign PowerSystemStabilizerDynamics on RemoteInputSignal
     * @param		command UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalCommand
     * @exception	ProcessingException
     */     
	public void unAssignPowerSystemStabilizerDynamics( UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	RemoteInputSignalValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign PowerSystemStabilizerDynamics on RemoteInputSignal";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign VoltageCompensatorDynamics on RemoteInputSignal
     * @param		command AssignVoltageCompensatorDynamicsToRemoteInputSignalCommand	
     * @exception	ProcessingException
     */     
	public void assignVoltageCompensatorDynamics( AssignVoltageCompensatorDynamicsToRemoteInputSignalCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getRemoteInputSignalId() );
		
		VoltageCompensatorDynamicsBusinessDelegate childDelegate 	= VoltageCompensatorDynamicsBusinessDelegate.getVoltageCompensatorDynamicsInstance();
		RemoteInputSignalBusinessDelegate parentDelegate = RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance();			
		UUID childId = command.getAssignment().getVoltageCompensatorDynamicsId();
		VoltageCompensatorDynamics child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	RemoteInputSignalValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get VoltageCompensatorDynamics using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign VoltageCompensatorDynamics on RemoteInputSignal
     * @param		command UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalCommand
     * @exception	ProcessingException
     */     
	public void unAssignVoltageCompensatorDynamics( UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	RemoteInputSignalValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign VoltageCompensatorDynamics on RemoteInputSignal";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign UnderexcitationLimiterDynamics on RemoteInputSignal
     * @param		command AssignUnderexcitationLimiterDynamicsToRemoteInputSignalCommand	
     * @exception	ProcessingException
     */     
	public void assignUnderexcitationLimiterDynamics( AssignUnderexcitationLimiterDynamicsToRemoteInputSignalCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getRemoteInputSignalId() );
		
		UnderexcitationLimiterDynamicsBusinessDelegate childDelegate 	= UnderexcitationLimiterDynamicsBusinessDelegate.getUnderexcitationLimiterDynamicsInstance();
		RemoteInputSignalBusinessDelegate parentDelegate = RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance();			
		UUID childId = command.getAssignment().getUnderexcitationLimiterDynamicsId();
		UnderexcitationLimiterDynamics child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	RemoteInputSignalValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get UnderexcitationLimiterDynamics using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign UnderexcitationLimiterDynamics on RemoteInputSignal
     * @param		command UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalCommand
     * @exception	ProcessingException
     */     
	public void unAssignUnderexcitationLimiterDynamics( UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	RemoteInputSignalValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign UnderexcitationLimiterDynamics on RemoteInputSignal";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign PFVArControllerType1Dynamics on RemoteInputSignal
     * @param		command AssignPFVArControllerType1DynamicsToRemoteInputSignalCommand	
     * @exception	ProcessingException
     */     
	public void assignPFVArControllerType1Dynamics( AssignPFVArControllerType1DynamicsToRemoteInputSignalCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getRemoteInputSignalId() );
		
		PFVArControllerType1DynamicsBusinessDelegate childDelegate 	= PFVArControllerType1DynamicsBusinessDelegate.getPFVArControllerType1DynamicsInstance();
		RemoteInputSignalBusinessDelegate parentDelegate = RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance();			
		UUID childId = command.getAssignment().getPFVArControllerType1DynamicsId();
		PFVArControllerType1Dynamics child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	RemoteInputSignalValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get PFVArControllerType1Dynamics using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign PFVArControllerType1Dynamics on RemoteInputSignal
     * @param		command UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalCommand
     * @exception	ProcessingException
     */     
	public void unAssignPFVArControllerType1Dynamics( UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	RemoteInputSignalValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign PFVArControllerType1Dynamics on RemoteInputSignal";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	



	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		RemoteInputSignal
	 */
	protected RemoteInputSignal load( UUID id ) throws ProcessingException {
		remoteInputSignal = RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().getRemoteInputSignal( new RemoteInputSignalFetchOneSummary(id) );	
		return remoteInputSignal;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private RemoteInputSignal remoteInputSignal 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(RemoteInputSignalBusinessDelegate.class.getName());
    
}
