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
 * ProprietaryParameterDynamics business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of ProprietaryParameterDynamics related services in the case of a ProprietaryParameterDynamics business related service failing.</li>
 * <li>Exposes a simpler, uniform ProprietaryParameterDynamics interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill ProprietaryParameterDynamics business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class ProprietaryParameterDynamicsBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public ProprietaryParameterDynamicsBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* ProprietaryParameterDynamics Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	ProprietaryParameterDynamicsBusinessDelegate
	*/
	public static ProprietaryParameterDynamicsBusinessDelegate getProprietaryParameterDynamicsInstance() {
		return( new ProprietaryParameterDynamicsBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createProprietaryParameterDynamics( CreateProprietaryParameterDynamicsCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getProprietaryParameterDynamicsId() == null )
				command.setProprietaryParameterDynamicsId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateProprietaryParameterDynamicsCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateProprietaryParameterDynamicsCommand of ProprietaryParameterDynamics is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create ProprietaryParameterDynamics - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateProprietaryParameterDynamicsCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateProprietaryParameterDynamics( UpdateProprietaryParameterDynamicsCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateProprietaryParameterDynamicsCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save ProprietaryParameterDynamics - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteProprietaryParameterDynamicsCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteProprietaryParameterDynamicsCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteProprietaryParameterDynamicsCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete ProprietaryParameterDynamics using Id = "  + command.getProprietaryParameterDynamicsId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the ProprietaryParameterDynamics via ProprietaryParameterDynamicsFetchOneSummary
     * @param 	summary ProprietaryParameterDynamicsFetchOneSummary 
     * @return 	ProprietaryParameterDynamicsFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public ProprietaryParameterDynamics getProprietaryParameterDynamics( ProprietaryParameterDynamicsFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "ProprietaryParameterDynamicsFetchOneSummary arg cannot be null" );
    	
    	ProprietaryParameterDynamics entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	ProprietaryParameterDynamicsValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a ProprietaryParameterDynamics
        	// --------------------------------------
        	CompletableFuture<ProprietaryParameterDynamics> futureEntity = queryGateway.query(new FindProprietaryParameterDynamicsQuery( new LoadProprietaryParameterDynamicsFilter( summary.getProprietaryParameterDynamicsId() ) ), ResponseTypes.instanceOf(ProprietaryParameterDynamics.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate ProprietaryParameterDynamics with id " + summary.getProprietaryParameterDynamicsId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all ProprietaryParameterDynamicss
     *
     * @return 	List<ProprietaryParameterDynamics> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<ProprietaryParameterDynamics> getAllProprietaryParameterDynamics() 
    throws ProcessingException {
        List<ProprietaryParameterDynamics> list = null;

        try {
        	CompletableFuture<List<ProprietaryParameterDynamics>> futureList = queryGateway.query(new FindAllProprietaryParameterDynamicsQuery(), ResponseTypes.multipleInstancesOf(ProprietaryParameterDynamics.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all ProprietaryParameterDynamics";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

    /**
     * assign BooleanParameterValue on ProprietaryParameterDynamics
     * @param		command AssignBooleanParameterValueToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignBooleanParameterValue( AssignBooleanParameterValueToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		BooleanProxyBusinessDelegate childDelegate 	= BooleanProxyBusinessDelegate.getBooleanProxyInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getBooleanProxyId();
		BooleanProxy child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

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
     * unAssign BooleanParameterValue on ProprietaryParameterDynamics
     * @param		command UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignBooleanParameterValue( UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign BooleanParameterValue on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign FloatParameterValue on ProprietaryParameterDynamics
     * @param		command AssignFloatParameterValueToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignFloatParameterValue( AssignFloatParameterValueToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		Simple_FloatBusinessDelegate childDelegate 	= Simple_FloatBusinessDelegate.getSimple_FloatInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getSimple_FloatId();
		Simple_Float child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

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
     * unAssign FloatParameterValue on ProprietaryParameterDynamics
     * @param		command UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignFloatParameterValue( UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign FloatParameterValue on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign IntegerParameterValue on ProprietaryParameterDynamics
     * @param		command AssignIntegerParameterValueToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignIntegerParameterValue( AssignIntegerParameterValueToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		IntegerProxyBusinessDelegate childDelegate 	= IntegerProxyBusinessDelegate.getIntegerProxyInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getIntegerProxyId();
		IntegerProxy child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

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
     * unAssign IntegerParameterValue on ProprietaryParameterDynamics
     * @param		command UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignIntegerParameterValue( UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign IntegerParameterValue on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign ParameterNumber on ProprietaryParameterDynamics
     * @param		command AssignParameterNumberToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignParameterNumber( AssignParameterNumberToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		IntegerProxyBusinessDelegate childDelegate 	= IntegerProxyBusinessDelegate.getIntegerProxyInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getIntegerProxyId();
		IntegerProxy child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

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
     * unAssign ParameterNumber on ProprietaryParameterDynamics
     * @param		command UnAssignParameterNumberFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignParameterNumber( UnAssignParameterNumberFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign ParameterNumber on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign PFVArControllerType1UserDefined on ProprietaryParameterDynamics
     * @param		command AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignPFVArControllerType1UserDefined( AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		PFVArControllerType1UserDefinedBusinessDelegate childDelegate 	= PFVArControllerType1UserDefinedBusinessDelegate.getPFVArControllerType1UserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getPFVArControllerType1UserDefinedId();
		PFVArControllerType1UserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get PFVArControllerType1UserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign PFVArControllerType1UserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignPFVArControllerType1UserDefined( UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign PFVArControllerType1UserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign VoltageCompensatorUserDefined on ProprietaryParameterDynamics
     * @param		command AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignVoltageCompensatorUserDefined( AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		VoltageCompensatorUserDefinedBusinessDelegate childDelegate 	= VoltageCompensatorUserDefinedBusinessDelegate.getVoltageCompensatorUserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getVoltageCompensatorUserDefinedId();
		VoltageCompensatorUserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get VoltageCompensatorUserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign VoltageCompensatorUserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignVoltageCompensatorUserDefined( UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign VoltageCompensatorUserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign MechanicalLoadUserDefined on ProprietaryParameterDynamics
     * @param		command AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignMechanicalLoadUserDefined( AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		MechanicalLoadUserDefinedBusinessDelegate childDelegate 	= MechanicalLoadUserDefinedBusinessDelegate.getMechanicalLoadUserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getMechanicalLoadUserDefinedId();
		MechanicalLoadUserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get MechanicalLoadUserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign MechanicalLoadUserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignMechanicalLoadUserDefined( UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign MechanicalLoadUserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign ExcitationSystemUserDefined on ProprietaryParameterDynamics
     * @param		command AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignExcitationSystemUserDefined( AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		ExcitationSystemUserDefinedBusinessDelegate childDelegate 	= ExcitationSystemUserDefinedBusinessDelegate.getExcitationSystemUserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getExcitationSystemUserDefinedId();
		ExcitationSystemUserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get ExcitationSystemUserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign ExcitationSystemUserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignExcitationSystemUserDefined( UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign ExcitationSystemUserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign AsynchronousMachineUserDefined on ProprietaryParameterDynamics
     * @param		command AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignAsynchronousMachineUserDefined( AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		AsynchronousMachineUserDefinedBusinessDelegate childDelegate 	= AsynchronousMachineUserDefinedBusinessDelegate.getAsynchronousMachineUserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getAsynchronousMachineUserDefinedId();
		AsynchronousMachineUserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get AsynchronousMachineUserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign AsynchronousMachineUserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignAsynchronousMachineUserDefined( UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign AsynchronousMachineUserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign DiscontinuousExcitationControlUserDefined on ProprietaryParameterDynamics
     * @param		command AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignDiscontinuousExcitationControlUserDefined( AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		DiscontinuousExcitationControlUserDefinedBusinessDelegate childDelegate 	= DiscontinuousExcitationControlUserDefinedBusinessDelegate.getDiscontinuousExcitationControlUserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getDiscontinuousExcitationControlUserDefinedId();
		DiscontinuousExcitationControlUserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get DiscontinuousExcitationControlUserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign DiscontinuousExcitationControlUserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignDiscontinuousExcitationControlUserDefined( UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign DiscontinuousExcitationControlUserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign TurbineGovernorUserDefined on ProprietaryParameterDynamics
     * @param		command AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignTurbineGovernorUserDefined( AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		TurbineGovernorUserDefinedBusinessDelegate childDelegate 	= TurbineGovernorUserDefinedBusinessDelegate.getTurbineGovernorUserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getTurbineGovernorUserDefinedId();
		TurbineGovernorUserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get TurbineGovernorUserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign TurbineGovernorUserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignTurbineGovernorUserDefined( UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign TurbineGovernorUserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign VoltageAdjusterUserDefined on ProprietaryParameterDynamics
     * @param		command AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignVoltageAdjusterUserDefined( AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		VoltageAdjusterUserDefinedBusinessDelegate childDelegate 	= VoltageAdjusterUserDefinedBusinessDelegate.getVoltageAdjusterUserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getVoltageAdjusterUserDefinedId();
		VoltageAdjusterUserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get VoltageAdjusterUserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign VoltageAdjusterUserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignVoltageAdjusterUserDefined( UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign VoltageAdjusterUserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign SynchronousMachineUserDefined on ProprietaryParameterDynamics
     * @param		command AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignSynchronousMachineUserDefined( AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		SynchronousMachineUserDefinedBusinessDelegate childDelegate 	= SynchronousMachineUserDefinedBusinessDelegate.getSynchronousMachineUserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getSynchronousMachineUserDefinedId();
		SynchronousMachineUserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get SynchronousMachineUserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign SynchronousMachineUserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignSynchronousMachineUserDefined( UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign SynchronousMachineUserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign UnderexcitationLimiterUserDefined on ProprietaryParameterDynamics
     * @param		command AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignUnderexcitationLimiterUserDefined( AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		UnderexcitationLimiterUserDefinedBusinessDelegate childDelegate 	= UnderexcitationLimiterUserDefinedBusinessDelegate.getUnderexcitationLimiterUserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getUnderexcitationLimiterUserDefinedId();
		UnderexcitationLimiterUserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get UnderexcitationLimiterUserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign UnderexcitationLimiterUserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignUnderexcitationLimiterUserDefined( UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign UnderexcitationLimiterUserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign TurbineLoadControllerUserDefined on ProprietaryParameterDynamics
     * @param		command AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignTurbineLoadControllerUserDefined( AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		TurbineLoadControllerUserDefinedBusinessDelegate childDelegate 	= TurbineLoadControllerUserDefinedBusinessDelegate.getTurbineLoadControllerUserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getTurbineLoadControllerUserDefinedId();
		TurbineLoadControllerUserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get TurbineLoadControllerUserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign TurbineLoadControllerUserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignTurbineLoadControllerUserDefined( UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign TurbineLoadControllerUserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign OverexcitationLimiterUserDefined on ProprietaryParameterDynamics
     * @param		command AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignOverexcitationLimiterUserDefined( AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		OverexcitationLimiterUserDefinedBusinessDelegate childDelegate 	= OverexcitationLimiterUserDefinedBusinessDelegate.getOverexcitationLimiterUserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getOverexcitationLimiterUserDefinedId();
		OverexcitationLimiterUserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get OverexcitationLimiterUserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign OverexcitationLimiterUserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignOverexcitationLimiterUserDefined( UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign OverexcitationLimiterUserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign PowerSystemStabilizerUserDefined on ProprietaryParameterDynamics
     * @param		command AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignPowerSystemStabilizerUserDefined( AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		PowerSystemStabilizerUserDefinedBusinessDelegate childDelegate 	= PowerSystemStabilizerUserDefinedBusinessDelegate.getPowerSystemStabilizerUserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getPowerSystemStabilizerUserDefinedId();
		PowerSystemStabilizerUserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get PowerSystemStabilizerUserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign PowerSystemStabilizerUserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignPowerSystemStabilizerUserDefined( UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign PowerSystemStabilizerUserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign LoadUserDefined on ProprietaryParameterDynamics
     * @param		command AssignLoadUserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignLoadUserDefined( AssignLoadUserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		LoadUserDefinedBusinessDelegate childDelegate 	= LoadUserDefinedBusinessDelegate.getLoadUserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getLoadUserDefinedId();
		LoadUserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get LoadUserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign LoadUserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignLoadUserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignLoadUserDefined( UnAssignLoadUserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign LoadUserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign PFVArControllerType2UserDefined on ProprietaryParameterDynamics
     * @param		command AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignPFVArControllerType2UserDefined( AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		PFVArControllerType2UserDefinedBusinessDelegate childDelegate 	= PFVArControllerType2UserDefinedBusinessDelegate.getPFVArControllerType2UserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getPFVArControllerType2UserDefinedId();
		PFVArControllerType2UserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get PFVArControllerType2UserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign PFVArControllerType2UserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignPFVArControllerType2UserDefined( UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign PFVArControllerType2UserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign WindType3or4UserDefined on ProprietaryParameterDynamics
     * @param		command AssignWindType3or4UserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignWindType3or4UserDefined( AssignWindType3or4UserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		WindType3or4UserDefinedBusinessDelegate childDelegate 	= WindType3or4UserDefinedBusinessDelegate.getWindType3or4UserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getWindType3or4UserDefinedId();
		WindType3or4UserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get WindType3or4UserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign WindType3or4UserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignWindType3or4UserDefined( UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign WindType3or4UserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign WindPlantUserDefined on ProprietaryParameterDynamics
     * @param		command AssignWindPlantUserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignWindPlantUserDefined( AssignWindPlantUserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		WindPlantUserDefinedBusinessDelegate childDelegate 	= WindPlantUserDefinedBusinessDelegate.getWindPlantUserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getWindPlantUserDefinedId();
		WindPlantUserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get WindPlantUserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign WindPlantUserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignWindPlantUserDefined( UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign WindPlantUserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign WindType1or2UserDefined on ProprietaryParameterDynamics
     * @param		command AssignWindType1or2UserDefinedToProprietaryParameterDynamicsCommand	
     * @exception	ProcessingException
     */     
	public void assignWindType1or2UserDefined( AssignWindType1or2UserDefinedToProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getProprietaryParameterDynamicsId() );
		
		WindType1or2UserDefinedBusinessDelegate childDelegate 	= WindType1or2UserDefinedBusinessDelegate.getWindType1or2UserDefinedInstance();
		ProprietaryParameterDynamicsBusinessDelegate parentDelegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();			
		UUID childId = command.getAssignment().getWindType1or2UserDefinedId();
		WindType1or2UserDefined child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get WindType1or2UserDefined using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign WindType1or2UserDefined on ProprietaryParameterDynamics
     * @param		command UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsCommand
     * @exception	ProcessingException
     */     
	public void unAssignWindType1or2UserDefined( UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ProprietaryParameterDynamicsValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign WindType1or2UserDefined on ProprietaryParameterDynamics";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	



	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		ProprietaryParameterDynamics
	 */
	protected ProprietaryParameterDynamics load( UUID id ) throws ProcessingException {
		proprietaryParameterDynamics = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().getProprietaryParameterDynamics( new ProprietaryParameterDynamicsFetchOneSummary(id) );	
		return proprietaryParameterDynamics;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private ProprietaryParameterDynamics proprietaryParameterDynamics 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(ProprietaryParameterDynamicsBusinessDelegate.class.getName());
    
}
