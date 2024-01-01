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
 * EquivalentInjection business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of EquivalentInjection related services in the case of a EquivalentInjection business related service failing.</li>
 * <li>Exposes a simpler, uniform EquivalentInjection interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill EquivalentInjection business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class EquivalentInjectionBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public EquivalentInjectionBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* EquivalentInjection Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	EquivalentInjectionBusinessDelegate
	*/
	public static EquivalentInjectionBusinessDelegate getEquivalentInjectionInstance() {
		return( new EquivalentInjectionBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createEquivalentInjection( CreateEquivalentInjectionCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getEquivalentInjectionId() == null )
				command.setEquivalentInjectionId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	EquivalentInjectionValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateEquivalentInjectionCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateEquivalentInjectionCommand of EquivalentInjection is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create EquivalentInjection - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateEquivalentInjectionCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateEquivalentInjection( UpdateEquivalentInjectionCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	EquivalentInjectionValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateEquivalentInjectionCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save EquivalentInjection - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteEquivalentInjectionCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteEquivalentInjectionCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	EquivalentInjectionValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteEquivalentInjectionCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete EquivalentInjection using Id = "  + command.getEquivalentInjectionId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the EquivalentInjection via EquivalentInjectionFetchOneSummary
     * @param 	summary EquivalentInjectionFetchOneSummary 
     * @return 	EquivalentInjectionFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public EquivalentInjection getEquivalentInjection( EquivalentInjectionFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "EquivalentInjectionFetchOneSummary arg cannot be null" );
    	
    	EquivalentInjection entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	EquivalentInjectionValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a EquivalentInjection
        	// --------------------------------------
        	CompletableFuture<EquivalentInjection> futureEntity = queryGateway.query(new FindEquivalentInjectionQuery( new LoadEquivalentInjectionFilter( summary.getEquivalentInjectionId() ) ), ResponseTypes.instanceOf(EquivalentInjection.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate EquivalentInjection with id " + summary.getEquivalentInjectionId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all EquivalentInjections
     *
     * @return 	List<EquivalentInjection> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<EquivalentInjection> getAllEquivalentInjection() 
    throws ProcessingException {
        List<EquivalentInjection> list = null;

        try {
        	CompletableFuture<List<EquivalentInjection>> futureList = queryGateway.query(new FindAllEquivalentInjectionQuery(), ResponseTypes.multipleInstancesOf(EquivalentInjection.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all EquivalentInjection";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

    /**
     * assign MaxP on EquivalentInjection
     * @param		command AssignMaxPToEquivalentInjectionCommand	
     * @exception	ProcessingException
     */     
	public void assignMaxP( AssignMaxPToEquivalentInjectionCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getEquivalentInjectionId() );
		
		ActivePowerBusinessDelegate childDelegate 	= ActivePowerBusinessDelegate.getActivePowerInstance();
		EquivalentInjectionBusinessDelegate parentDelegate = EquivalentInjectionBusinessDelegate.getEquivalentInjectionInstance();			
		UUID childId = command.getAssignment().getActivePowerId();
		ActivePower child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get ActivePower using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign MaxP on EquivalentInjection
     * @param		command UnAssignMaxPFromEquivalentInjectionCommand
     * @exception	ProcessingException
     */     
	public void unAssignMaxP( UnAssignMaxPFromEquivalentInjectionCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign MaxP on EquivalentInjection";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign MaxQ on EquivalentInjection
     * @param		command AssignMaxQToEquivalentInjectionCommand	
     * @exception	ProcessingException
     */     
	public void assignMaxQ( AssignMaxQToEquivalentInjectionCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getEquivalentInjectionId() );
		
		ReactivePowerBusinessDelegate childDelegate 	= ReactivePowerBusinessDelegate.getReactivePowerInstance();
		EquivalentInjectionBusinessDelegate parentDelegate = EquivalentInjectionBusinessDelegate.getEquivalentInjectionInstance();			
		UUID childId = command.getAssignment().getReactivePowerId();
		ReactivePower child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get ReactivePower using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign MaxQ on EquivalentInjection
     * @param		command UnAssignMaxQFromEquivalentInjectionCommand
     * @exception	ProcessingException
     */     
	public void unAssignMaxQ( UnAssignMaxQFromEquivalentInjectionCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign MaxQ on EquivalentInjection";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign MinP on EquivalentInjection
     * @param		command AssignMinPToEquivalentInjectionCommand	
     * @exception	ProcessingException
     */     
	public void assignMinP( AssignMinPToEquivalentInjectionCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getEquivalentInjectionId() );
		
		ActivePowerBusinessDelegate childDelegate 	= ActivePowerBusinessDelegate.getActivePowerInstance();
		EquivalentInjectionBusinessDelegate parentDelegate = EquivalentInjectionBusinessDelegate.getEquivalentInjectionInstance();			
		UUID childId = command.getAssignment().getActivePowerId();
		ActivePower child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get ActivePower using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign MinP on EquivalentInjection
     * @param		command UnAssignMinPFromEquivalentInjectionCommand
     * @exception	ProcessingException
     */     
	public void unAssignMinP( UnAssignMinPFromEquivalentInjectionCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign MinP on EquivalentInjection";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign MinQ on EquivalentInjection
     * @param		command AssignMinQToEquivalentInjectionCommand	
     * @exception	ProcessingException
     */     
	public void assignMinQ( AssignMinQToEquivalentInjectionCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getEquivalentInjectionId() );
		
		ReactivePowerBusinessDelegate childDelegate 	= ReactivePowerBusinessDelegate.getReactivePowerInstance();
		EquivalentInjectionBusinessDelegate parentDelegate = EquivalentInjectionBusinessDelegate.getEquivalentInjectionInstance();			
		UUID childId = command.getAssignment().getReactivePowerId();
		ReactivePower child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get ReactivePower using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign MinQ on EquivalentInjection
     * @param		command UnAssignMinQFromEquivalentInjectionCommand
     * @exception	ProcessingException
     */     
	public void unAssignMinQ( UnAssignMinQFromEquivalentInjectionCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign MinQ on EquivalentInjection";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign R on EquivalentInjection
     * @param		command AssignRToEquivalentInjectionCommand	
     * @exception	ProcessingException
     */     
	public void assignR( AssignRToEquivalentInjectionCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getEquivalentInjectionId() );
		
		ResistanceBusinessDelegate childDelegate 	= ResistanceBusinessDelegate.getResistanceInstance();
		EquivalentInjectionBusinessDelegate parentDelegate = EquivalentInjectionBusinessDelegate.getEquivalentInjectionInstance();			
		UUID childId = command.getAssignment().getResistanceId();
		Resistance child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get Resistance using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign R on EquivalentInjection
     * @param		command UnAssignRFromEquivalentInjectionCommand
     * @exception	ProcessingException
     */     
	public void unAssignR( UnAssignRFromEquivalentInjectionCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign R on EquivalentInjection";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign R0 on EquivalentInjection
     * @param		command AssignR0ToEquivalentInjectionCommand	
     * @exception	ProcessingException
     */     
	public void assignR0( AssignR0ToEquivalentInjectionCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getEquivalentInjectionId() );
		
		ResistanceBusinessDelegate childDelegate 	= ResistanceBusinessDelegate.getResistanceInstance();
		EquivalentInjectionBusinessDelegate parentDelegate = EquivalentInjectionBusinessDelegate.getEquivalentInjectionInstance();			
		UUID childId = command.getAssignment().getResistanceId();
		Resistance child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get Resistance using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign R0 on EquivalentInjection
     * @param		command UnAssignR0FromEquivalentInjectionCommand
     * @exception	ProcessingException
     */     
	public void unAssignR0( UnAssignR0FromEquivalentInjectionCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign R0 on EquivalentInjection";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign R2 on EquivalentInjection
     * @param		command AssignR2ToEquivalentInjectionCommand	
     * @exception	ProcessingException
     */     
	public void assignR2( AssignR2ToEquivalentInjectionCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getEquivalentInjectionId() );
		
		ResistanceBusinessDelegate childDelegate 	= ResistanceBusinessDelegate.getResistanceInstance();
		EquivalentInjectionBusinessDelegate parentDelegate = EquivalentInjectionBusinessDelegate.getEquivalentInjectionInstance();			
		UUID childId = command.getAssignment().getResistanceId();
		Resistance child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get Resistance using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign R2 on EquivalentInjection
     * @param		command UnAssignR2FromEquivalentInjectionCommand
     * @exception	ProcessingException
     */     
	public void unAssignR2( UnAssignR2FromEquivalentInjectionCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign R2 on EquivalentInjection";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign RegulationCapability on EquivalentInjection
     * @param		command AssignRegulationCapabilityToEquivalentInjectionCommand	
     * @exception	ProcessingException
     */     
	public void assignRegulationCapability( AssignRegulationCapabilityToEquivalentInjectionCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getEquivalentInjectionId() );
		
		BooleanProxyBusinessDelegate childDelegate 	= BooleanProxyBusinessDelegate.getBooleanProxyInstance();
		EquivalentInjectionBusinessDelegate parentDelegate = EquivalentInjectionBusinessDelegate.getEquivalentInjectionInstance();			
		UUID childId = command.getAssignment().getBooleanProxyId();
		BooleanProxy child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    

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
     * unAssign RegulationCapability on EquivalentInjection
     * @param		command UnAssignRegulationCapabilityFromEquivalentInjectionCommand
     * @exception	ProcessingException
     */     
	public void unAssignRegulationCapability( UnAssignRegulationCapabilityFromEquivalentInjectionCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign RegulationCapability on EquivalentInjection";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign X on EquivalentInjection
     * @param		command AssignXToEquivalentInjectionCommand	
     * @exception	ProcessingException
     */     
	public void assignX( AssignXToEquivalentInjectionCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getEquivalentInjectionId() );
		
		ReactanceBusinessDelegate childDelegate 	= ReactanceBusinessDelegate.getReactanceInstance();
		EquivalentInjectionBusinessDelegate parentDelegate = EquivalentInjectionBusinessDelegate.getEquivalentInjectionInstance();			
		UUID childId = command.getAssignment().getReactanceId();
		Reactance child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get Reactance using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign X on EquivalentInjection
     * @param		command UnAssignXFromEquivalentInjectionCommand
     * @exception	ProcessingException
     */     
	public void unAssignX( UnAssignXFromEquivalentInjectionCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign X on EquivalentInjection";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign X0 on EquivalentInjection
     * @param		command AssignX0ToEquivalentInjectionCommand	
     * @exception	ProcessingException
     */     
	public void assignX0( AssignX0ToEquivalentInjectionCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getEquivalentInjectionId() );
		
		ReactanceBusinessDelegate childDelegate 	= ReactanceBusinessDelegate.getReactanceInstance();
		EquivalentInjectionBusinessDelegate parentDelegate = EquivalentInjectionBusinessDelegate.getEquivalentInjectionInstance();			
		UUID childId = command.getAssignment().getReactanceId();
		Reactance child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get Reactance using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign X0 on EquivalentInjection
     * @param		command UnAssignX0FromEquivalentInjectionCommand
     * @exception	ProcessingException
     */     
	public void unAssignX0( UnAssignX0FromEquivalentInjectionCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign X0 on EquivalentInjection";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign X2 on EquivalentInjection
     * @param		command AssignX2ToEquivalentInjectionCommand	
     * @exception	ProcessingException
     */     
	public void assignX2( AssignX2ToEquivalentInjectionCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getEquivalentInjectionId() );
		
		ReactanceBusinessDelegate childDelegate 	= ReactanceBusinessDelegate.getReactanceInstance();
		EquivalentInjectionBusinessDelegate parentDelegate = EquivalentInjectionBusinessDelegate.getEquivalentInjectionInstance();			
		UUID childId = command.getAssignment().getReactanceId();
		Reactance child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get Reactance using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign X2 on EquivalentInjection
     * @param		command UnAssignX2FromEquivalentInjectionCommand
     * @exception	ProcessingException
     */     
	public void unAssignX2( UnAssignX2FromEquivalentInjectionCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign X2 on EquivalentInjection";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign ReactiveCapabilityCurve on EquivalentInjection
     * @param		command AssignReactiveCapabilityCurveToEquivalentInjectionCommand	
     * @exception	ProcessingException
     */     
	public void assignReactiveCapabilityCurve( AssignReactiveCapabilityCurveToEquivalentInjectionCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getEquivalentInjectionId() );
		
		ReactiveCapabilityCurveBusinessDelegate childDelegate 	= ReactiveCapabilityCurveBusinessDelegate.getReactiveCapabilityCurveInstance();
		EquivalentInjectionBusinessDelegate parentDelegate = EquivalentInjectionBusinessDelegate.getEquivalentInjectionInstance();			
		UUID childId = command.getAssignment().getReactiveCapabilityCurveId();
		ReactiveCapabilityCurve child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get ReactiveCapabilityCurve using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign ReactiveCapabilityCurve on EquivalentInjection
     * @param		command UnAssignReactiveCapabilityCurveFromEquivalentInjectionCommand
     * @exception	ProcessingException
     */     
	public void unAssignReactiveCapabilityCurve( UnAssignReactiveCapabilityCurveFromEquivalentInjectionCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	EquivalentInjectionValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign ReactiveCapabilityCurve on EquivalentInjection";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	



	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		EquivalentInjection
	 */
	protected EquivalentInjection load( UUID id ) throws ProcessingException {
		equivalentInjection = EquivalentInjectionBusinessDelegate.getEquivalentInjectionInstance().getEquivalentInjection( new EquivalentInjectionFetchOneSummary(id) );	
		return equivalentInjection;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private EquivalentInjection equivalentInjection 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(EquivalentInjectionBusinessDelegate.class.getName());
    
}
