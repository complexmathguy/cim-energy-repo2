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
 * WindDynamicsLookupTable business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of WindDynamicsLookupTable related services in the case of a WindDynamicsLookupTable business related service failing.</li>
 * <li>Exposes a simpler, uniform WindDynamicsLookupTable interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill WindDynamicsLookupTable business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class WindDynamicsLookupTableBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public WindDynamicsLookupTableBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* WindDynamicsLookupTable Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	WindDynamicsLookupTableBusinessDelegate
	*/
	public static WindDynamicsLookupTableBusinessDelegate getWindDynamicsLookupTableInstance() {
		return( new WindDynamicsLookupTableBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createWindDynamicsLookupTable( CreateWindDynamicsLookupTableCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getWindDynamicsLookupTableId() == null )
				command.setWindDynamicsLookupTableId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	WindDynamicsLookupTableValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateWindDynamicsLookupTableCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateWindDynamicsLookupTableCommand of WindDynamicsLookupTable is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create WindDynamicsLookupTable - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateWindDynamicsLookupTableCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateWindDynamicsLookupTable( UpdateWindDynamicsLookupTableCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	WindDynamicsLookupTableValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateWindDynamicsLookupTableCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save WindDynamicsLookupTable - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteWindDynamicsLookupTableCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteWindDynamicsLookupTableCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	WindDynamicsLookupTableValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteWindDynamicsLookupTableCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete WindDynamicsLookupTable using Id = "  + command.getWindDynamicsLookupTableId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the WindDynamicsLookupTable via WindDynamicsLookupTableFetchOneSummary
     * @param 	summary WindDynamicsLookupTableFetchOneSummary 
     * @return 	WindDynamicsLookupTableFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public WindDynamicsLookupTable getWindDynamicsLookupTable( WindDynamicsLookupTableFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "WindDynamicsLookupTableFetchOneSummary arg cannot be null" );
    	
    	WindDynamicsLookupTable entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	WindDynamicsLookupTableValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a WindDynamicsLookupTable
        	// --------------------------------------
        	CompletableFuture<WindDynamicsLookupTable> futureEntity = queryGateway.query(new FindWindDynamicsLookupTableQuery( new LoadWindDynamicsLookupTableFilter( summary.getWindDynamicsLookupTableId() ) ), ResponseTypes.instanceOf(WindDynamicsLookupTable.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate WindDynamicsLookupTable with id " + summary.getWindDynamicsLookupTableId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all WindDynamicsLookupTables
     *
     * @return 	List<WindDynamicsLookupTable> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<WindDynamicsLookupTable> getAllWindDynamicsLookupTable() 
    throws ProcessingException {
        List<WindDynamicsLookupTable> list = null;

        try {
        	CompletableFuture<List<WindDynamicsLookupTable>> futureList = queryGateway.query(new FindAllWindDynamicsLookupTableQuery(), ResponseTypes.multipleInstancesOf(WindDynamicsLookupTable.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all WindDynamicsLookupTable";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

    /**
     * assign Input on WindDynamicsLookupTable
     * @param		command AssignInputToWindDynamicsLookupTableCommand	
     * @exception	ProcessingException
     */     
	public void assignInput( AssignInputToWindDynamicsLookupTableCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getWindDynamicsLookupTableId() );
		
		Simple_FloatBusinessDelegate childDelegate 	= Simple_FloatBusinessDelegate.getSimple_FloatInstance();
		WindDynamicsLookupTableBusinessDelegate parentDelegate = WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance();			
		UUID childId = command.getAssignment().getSimple_FloatId();
		Simple_Float child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	WindDynamicsLookupTableValidator.getInstance().validate( command );    

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
     * unAssign Input on WindDynamicsLookupTable
     * @param		command UnAssignInputFromWindDynamicsLookupTableCommand
     * @exception	ProcessingException
     */     
	public void unAssignInput( UnAssignInputFromWindDynamicsLookupTableCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	WindDynamicsLookupTableValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign Input on WindDynamicsLookupTable";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign Output on WindDynamicsLookupTable
     * @param		command AssignOutputToWindDynamicsLookupTableCommand	
     * @exception	ProcessingException
     */     
	public void assignOutput( AssignOutputToWindDynamicsLookupTableCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getWindDynamicsLookupTableId() );
		
		Simple_FloatBusinessDelegate childDelegate 	= Simple_FloatBusinessDelegate.getSimple_FloatInstance();
		WindDynamicsLookupTableBusinessDelegate parentDelegate = WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance();			
		UUID childId = command.getAssignment().getSimple_FloatId();
		Simple_Float child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	WindDynamicsLookupTableValidator.getInstance().validate( command );    

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
     * unAssign Output on WindDynamicsLookupTable
     * @param		command UnAssignOutputFromWindDynamicsLookupTableCommand
     * @exception	ProcessingException
     */     
	public void unAssignOutput( UnAssignOutputFromWindDynamicsLookupTableCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	WindDynamicsLookupTableValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign Output on WindDynamicsLookupTable";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign Sequence on WindDynamicsLookupTable
     * @param		command AssignSequenceToWindDynamicsLookupTableCommand	
     * @exception	ProcessingException
     */     
	public void assignSequence( AssignSequenceToWindDynamicsLookupTableCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getWindDynamicsLookupTableId() );
		
		IntegerProxyBusinessDelegate childDelegate 	= IntegerProxyBusinessDelegate.getIntegerProxyInstance();
		WindDynamicsLookupTableBusinessDelegate parentDelegate = WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance();			
		UUID childId = command.getAssignment().getIntegerProxyId();
		IntegerProxy child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	WindDynamicsLookupTableValidator.getInstance().validate( command );    

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
     * unAssign Sequence on WindDynamicsLookupTable
     * @param		command UnAssignSequenceFromWindDynamicsLookupTableCommand
     * @exception	ProcessingException
     */     
	public void unAssignSequence( UnAssignSequenceFromWindDynamicsLookupTableCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	WindDynamicsLookupTableValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign Sequence on WindDynamicsLookupTable";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign WindContRotorRIEC on WindDynamicsLookupTable
     * @param		command AssignWindContRotorRIECToWindDynamicsLookupTableCommand	
     * @exception	ProcessingException
     */     
	public void assignWindContRotorRIEC( AssignWindContRotorRIECToWindDynamicsLookupTableCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getWindDynamicsLookupTableId() );
		
		WindContRotorRIECBusinessDelegate childDelegate 	= WindContRotorRIECBusinessDelegate.getWindContRotorRIECInstance();
		WindDynamicsLookupTableBusinessDelegate parentDelegate = WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance();			
		UUID childId = command.getAssignment().getWindContRotorRIECId();
		WindContRotorRIEC child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	WindDynamicsLookupTableValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get WindContRotorRIEC using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign WindContRotorRIEC on WindDynamicsLookupTable
     * @param		command UnAssignWindContRotorRIECFromWindDynamicsLookupTableCommand
     * @exception	ProcessingException
     */     
	public void unAssignWindContRotorRIEC( UnAssignWindContRotorRIECFromWindDynamicsLookupTableCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	WindDynamicsLookupTableValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign WindContRotorRIEC on WindDynamicsLookupTable";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign WindContCurrLimIEC on WindDynamicsLookupTable
     * @param		command AssignWindContCurrLimIECToWindDynamicsLookupTableCommand	
     * @exception	ProcessingException
     */     
	public void assignWindContCurrLimIEC( AssignWindContCurrLimIECToWindDynamicsLookupTableCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getWindDynamicsLookupTableId() );
		
		WindContCurrLimIECBusinessDelegate childDelegate 	= WindContCurrLimIECBusinessDelegate.getWindContCurrLimIECInstance();
		WindDynamicsLookupTableBusinessDelegate parentDelegate = WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance();			
		UUID childId = command.getAssignment().getWindContCurrLimIECId();
		WindContCurrLimIEC child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	WindDynamicsLookupTableValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get WindContCurrLimIEC using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign WindContCurrLimIEC on WindDynamicsLookupTable
     * @param		command UnAssignWindContCurrLimIECFromWindDynamicsLookupTableCommand
     * @exception	ProcessingException
     */     
	public void unAssignWindContCurrLimIEC( UnAssignWindContCurrLimIECFromWindDynamicsLookupTableCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	WindDynamicsLookupTableValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign WindContCurrLimIEC on WindDynamicsLookupTable";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign WindPlantFreqPcontrolIEC on WindDynamicsLookupTable
     * @param		command AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableCommand	
     * @exception	ProcessingException
     */     
	public void assignWindPlantFreqPcontrolIEC( AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getWindDynamicsLookupTableId() );
		
		WindPlantFreqPcontrolIECBusinessDelegate childDelegate 	= WindPlantFreqPcontrolIECBusinessDelegate.getWindPlantFreqPcontrolIECInstance();
		WindDynamicsLookupTableBusinessDelegate parentDelegate = WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance();			
		UUID childId = command.getAssignment().getWindPlantFreqPcontrolIECId();
		WindPlantFreqPcontrolIEC child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	WindDynamicsLookupTableValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get WindPlantFreqPcontrolIEC using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign WindPlantFreqPcontrolIEC on WindDynamicsLookupTable
     * @param		command UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableCommand
     * @exception	ProcessingException
     */     
	public void unAssignWindPlantFreqPcontrolIEC( UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	WindDynamicsLookupTableValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign WindPlantFreqPcontrolIEC on WindDynamicsLookupTable";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	
    /**
     * assign WindContPType3IEC on WindDynamicsLookupTable
     * @param		command AssignWindContPType3IECToWindDynamicsLookupTableCommand	
     * @exception	ProcessingException
     */     
	public void assignWindContPType3IEC( AssignWindContPType3IECToWindDynamicsLookupTableCommand command ) throws ProcessingException {

		// --------------------------------------------
		// load the parent
		// --------------------------------------------
		load( command.getWindDynamicsLookupTableId() );
		
		WindContPType3IECBusinessDelegate childDelegate 	= WindContPType3IECBusinessDelegate.getWindContPType3IECInstance();
		WindDynamicsLookupTableBusinessDelegate parentDelegate = WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance();			
		UUID childId = command.getAssignment().getWindContPType3IECId();
		WindContPType3IEC child = null;
		
		try {
			// --------------------------------------
	    	// best to validate the command now
	    	// --------------------------------------    
	    	WindDynamicsLookupTableValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );

		}
        catch( Throwable exc ) {
			final String msg = "Failed to get WindContPType3IEC using id " + childId;
			LOGGER.log( Level.WARNING,  msg );
			throw new ProcessingException( msg, exc );
        }
	}

    /**
     * unAssign WindContPType3IEC on WindDynamicsLookupTable
     * @param		command UnAssignWindContPType3IECFromWindDynamicsLookupTableCommand
     * @exception	ProcessingException
     */     
	public void unAssignWindContPType3IEC( UnAssignWindContPType3IECFromWindDynamicsLookupTableCommand command ) throws ProcessingException {

		try {
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	WindDynamicsLookupTableValidator.getInstance().validate( command );    
	
	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );
		}
		catch( Exception exc ) {
			final String msg = "Failed to unassign WindContPType3IEC on WindDynamicsLookupTable";
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}
	



	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		WindDynamicsLookupTable
	 */
	protected WindDynamicsLookupTable load( UUID id ) throws ProcessingException {
		windDynamicsLookupTable = WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().getWindDynamicsLookupTable( new WindDynamicsLookupTableFetchOneSummary(id) );	
		return windDynamicsLookupTable;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private WindDynamicsLookupTable windDynamicsLookupTable 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(WindDynamicsLookupTableBusinessDelegate.class.getName());
    
}
