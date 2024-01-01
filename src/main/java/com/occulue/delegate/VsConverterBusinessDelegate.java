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
 * VsConverter business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of VsConverter related services in the case of a VsConverter business related service failing.</li>
 * <li>Exposes a simpler, uniform VsConverter interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill VsConverter business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class VsConverterBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public VsConverterBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* VsConverter Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	VsConverterBusinessDelegate
	*/
	public static VsConverterBusinessDelegate getVsConverterInstance() {
		return( new VsConverterBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createVsConverter( CreateVsConverterCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getVsConverterId() == null )
				command.setVsConverterId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	VsConverterValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateVsConverterCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateVsConverterCommand of VsConverter is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create VsConverter - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateVsConverterCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateVsConverter( UpdateVsConverterCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	VsConverterValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateVsConverterCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save VsConverter - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteVsConverterCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteVsConverterCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	VsConverterValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteVsConverterCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete VsConverter using Id = "  + command.getVsConverterId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the VsConverter via VsConverterFetchOneSummary
     * @param 	summary VsConverterFetchOneSummary 
     * @return 	VsConverterFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public VsConverter getVsConverter( VsConverterFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "VsConverterFetchOneSummary arg cannot be null" );
    	
    	VsConverter entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	VsConverterValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a VsConverter
        	// --------------------------------------
        	CompletableFuture<VsConverter> futureEntity = queryGateway.query(new FindVsConverterQuery( new LoadVsConverterFilter( summary.getVsConverterId() ) ), ResponseTypes.instanceOf(VsConverter.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate VsConverter with id " + summary.getVsConverterId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all VsConverters
     *
     * @return 	List<VsConverter> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<VsConverter> getAllVsConverter() 
    throws ProcessingException {
        List<VsConverter> list = null;

        try {
        	CompletableFuture<List<VsConverter>> futureList = queryGateway.query(new FindAllVsConverterQuery(), ResponseTypes.multipleInstancesOf(VsConverter.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all VsConverter";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

    /**
     * assign MaxModulationIndex on VsConverter
     * @param		command AssignMaxModulationIndexToVsConverterCommand	
     * @exception	ProcessingException
     */     
	public void assignMaxModulationIndex( AssignMaxModulationIndexToVsConverterCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getVsConverterId() );
		
		Simple_FloatBusinessDelegate childDelegate 	= Simple_FloatBusinessDelegate.getSimple_FloatInstance();
		VsConverterBusinessDelegate parentDelegate = VsConverterBusinessDelegate.getVsConverterInstance();			
		UUID childId = command.getAssignment().getSimple_FloatId();
		Simple_Float child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	VsConverterValidator.getInstance().validate( command );    

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
     * unAssign MaxModulationIndex on VsConverter
     * @param		command UnAssignMaxModulationIndexFromVsConverterCommand
     * @exception	ProcessingException
     */     
	public void unAssignMaxModulationIndex( UnAssignMaxModulationIndexFromVsConverterCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	VsConverterValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign MaxModulationIndex on VsConverter";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign MaxValveCurrent on VsConverter
     * @param		command AssignMaxValveCurrentToVsConverterCommand	
     * @exception	ProcessingException
     */     
	public void assignMaxValveCurrent( AssignMaxValveCurrentToVsConverterCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getVsConverterId() );
		
		CurrentFlowBusinessDelegate childDelegate 	= CurrentFlowBusinessDelegate.getCurrentFlowInstance();
		VsConverterBusinessDelegate parentDelegate = VsConverterBusinessDelegate.getVsConverterInstance();			
		UUID childId = command.getAssignment().getCurrentFlowId();
		CurrentFlow child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	VsConverterValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get CurrentFlow using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign MaxValveCurrent on VsConverter
     * @param		command UnAssignMaxValveCurrentFromVsConverterCommand
     * @exception	ProcessingException
     */     
	public void unAssignMaxValveCurrent( UnAssignMaxValveCurrentFromVsConverterCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	VsConverterValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign MaxValveCurrent on VsConverter";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign CapabilityCurve on VsConverter
     * @param		command AssignCapabilityCurveToVsConverterCommand	
     * @exception	ProcessingException
     */     
	public void assignCapabilityCurve( AssignCapabilityCurveToVsConverterCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getVsConverterId() );
		
		VsCapabilityCurveBusinessDelegate childDelegate 	= VsCapabilityCurveBusinessDelegate.getVsCapabilityCurveInstance();
		VsConverterBusinessDelegate parentDelegate = VsConverterBusinessDelegate.getVsConverterInstance();			
		UUID childId = command.getAssignment().getVsCapabilityCurveId();
		VsCapabilityCurve child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	VsConverterValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get VsCapabilityCurve using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign CapabilityCurve on VsConverter
     * @param		command UnAssignCapabilityCurveFromVsConverterCommand
     * @exception	ProcessingException
     */     
	public void unAssignCapabilityCurve( UnAssignCapabilityCurveFromVsConverterCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	VsConverterValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign CapabilityCurve on VsConverter";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	



	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		VsConverter
	 */
	protected VsConverter load( UUID id ) throws ProcessingException {
		vsConverter = VsConverterBusinessDelegate.getVsConverterInstance().getVsConverter( new VsConverterFetchOneSummary(id) );	
		return vsConverter;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private VsConverter vsConverter 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(VsConverterBusinessDelegate.class.getName());
    
}
