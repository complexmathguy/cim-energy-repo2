/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.controller.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.projector.*;

import com.occulue.controller.*;

/** 
 * Implements Spring Controller command CQRS processing for entity WindDynamicsLookupTable.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/WindDynamicsLookupTable")
public class WindDynamicsLookupTableCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a WindDynamicsLookupTable.  if not key provided, calls create, otherwise calls save
     * @param		WindDynamicsLookupTable	windDynamicsLookupTable
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateWindDynamicsLookupTableCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().createWindDynamicsLookupTable( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a WindDynamicsLookupTable.  if no key provided, calls create, otherwise calls save
     * @param		WindDynamicsLookupTable windDynamicsLookupTable
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateWindDynamicsLookupTableCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateWindDynamicsLookupTableCommand
			// -----------------------------------------------
			completableFuture = WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().updateWindDynamicsLookupTable(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "WindDynamicsLookupTableController:update() - successfully update WindDynamicsLookupTable - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a WindDynamicsLookupTable entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID windDynamicsLookupTableId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteWindDynamicsLookupTableCommand command = new DeleteWindDynamicsLookupTableCommand( windDynamicsLookupTableId );

    	try {
        	WindDynamicsLookupTableBusinessDelegate delegate = WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted WindDynamicsLookupTable with key " + command.getWindDynamicsLookupTableId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save Input on WindDynamicsLookupTable
     * @param		command AssignInputToWindDynamicsLookupTableCommand
     */     
	@PutMapping("/assignInput")
	public void assignInput( @RequestBody AssignInputToWindDynamicsLookupTableCommand command ) {
		try {
			WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().assignInput( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Input", exc );
        }
	}

    /**
     * unassign Input on WindDynamicsLookupTable
     * @param		 command UnAssignInputFromWindDynamicsLookupTableCommand
     */     
	@PutMapping("/unAssignInput")
	public void unAssignInput( @RequestBody(required=true)  UnAssignInputFromWindDynamicsLookupTableCommand command ) {
		try {
			WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().unAssignInput( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Input", exc );
		}
	}
	
    /**
     * save Output on WindDynamicsLookupTable
     * @param		command AssignOutputToWindDynamicsLookupTableCommand
     */     
	@PutMapping("/assignOutput")
	public void assignOutput( @RequestBody AssignOutputToWindDynamicsLookupTableCommand command ) {
		try {
			WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().assignOutput( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Output", exc );
        }
	}

    /**
     * unassign Output on WindDynamicsLookupTable
     * @param		 command UnAssignOutputFromWindDynamicsLookupTableCommand
     */     
	@PutMapping("/unAssignOutput")
	public void unAssignOutput( @RequestBody(required=true)  UnAssignOutputFromWindDynamicsLookupTableCommand command ) {
		try {
			WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().unAssignOutput( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Output", exc );
		}
	}
	
    /**
     * save Sequence on WindDynamicsLookupTable
     * @param		command AssignSequenceToWindDynamicsLookupTableCommand
     */     
	@PutMapping("/assignSequence")
	public void assignSequence( @RequestBody AssignSequenceToWindDynamicsLookupTableCommand command ) {
		try {
			WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().assignSequence( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Sequence", exc );
        }
	}

    /**
     * unassign Sequence on WindDynamicsLookupTable
     * @param		 command UnAssignSequenceFromWindDynamicsLookupTableCommand
     */     
	@PutMapping("/unAssignSequence")
	public void unAssignSequence( @RequestBody(required=true)  UnAssignSequenceFromWindDynamicsLookupTableCommand command ) {
		try {
			WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().unAssignSequence( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Sequence", exc );
		}
	}
	
    /**
     * save WindContRotorRIEC on WindDynamicsLookupTable
     * @param		command AssignWindContRotorRIECToWindDynamicsLookupTableCommand
     */     
	@PutMapping("/assignWindContRotorRIEC")
	public void assignWindContRotorRIEC( @RequestBody AssignWindContRotorRIECToWindDynamicsLookupTableCommand command ) {
		try {
			WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().assignWindContRotorRIEC( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign WindContRotorRIEC", exc );
        }
	}

    /**
     * unassign WindContRotorRIEC on WindDynamicsLookupTable
     * @param		 command UnAssignWindContRotorRIECFromWindDynamicsLookupTableCommand
     */     
	@PutMapping("/unAssignWindContRotorRIEC")
	public void unAssignWindContRotorRIEC( @RequestBody(required=true)  UnAssignWindContRotorRIECFromWindDynamicsLookupTableCommand command ) {
		try {
			WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().unAssignWindContRotorRIEC( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign WindContRotorRIEC", exc );
		}
	}
	
    /**
     * save WindContCurrLimIEC on WindDynamicsLookupTable
     * @param		command AssignWindContCurrLimIECToWindDynamicsLookupTableCommand
     */     
	@PutMapping("/assignWindContCurrLimIEC")
	public void assignWindContCurrLimIEC( @RequestBody AssignWindContCurrLimIECToWindDynamicsLookupTableCommand command ) {
		try {
			WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().assignWindContCurrLimIEC( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign WindContCurrLimIEC", exc );
        }
	}

    /**
     * unassign WindContCurrLimIEC on WindDynamicsLookupTable
     * @param		 command UnAssignWindContCurrLimIECFromWindDynamicsLookupTableCommand
     */     
	@PutMapping("/unAssignWindContCurrLimIEC")
	public void unAssignWindContCurrLimIEC( @RequestBody(required=true)  UnAssignWindContCurrLimIECFromWindDynamicsLookupTableCommand command ) {
		try {
			WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().unAssignWindContCurrLimIEC( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign WindContCurrLimIEC", exc );
		}
	}
	
    /**
     * save WindPlantFreqPcontrolIEC on WindDynamicsLookupTable
     * @param		command AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableCommand
     */     
	@PutMapping("/assignWindPlantFreqPcontrolIEC")
	public void assignWindPlantFreqPcontrolIEC( @RequestBody AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableCommand command ) {
		try {
			WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().assignWindPlantFreqPcontrolIEC( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign WindPlantFreqPcontrolIEC", exc );
        }
	}

    /**
     * unassign WindPlantFreqPcontrolIEC on WindDynamicsLookupTable
     * @param		 command UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableCommand
     */     
	@PutMapping("/unAssignWindPlantFreqPcontrolIEC")
	public void unAssignWindPlantFreqPcontrolIEC( @RequestBody(required=true)  UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableCommand command ) {
		try {
			WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().unAssignWindPlantFreqPcontrolIEC( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign WindPlantFreqPcontrolIEC", exc );
		}
	}
	
    /**
     * save WindContPType3IEC on WindDynamicsLookupTable
     * @param		command AssignWindContPType3IECToWindDynamicsLookupTableCommand
     */     
	@PutMapping("/assignWindContPType3IEC")
	public void assignWindContPType3IEC( @RequestBody AssignWindContPType3IECToWindDynamicsLookupTableCommand command ) {
		try {
			WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().assignWindContPType3IEC( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign WindContPType3IEC", exc );
        }
	}

    /**
     * unassign WindContPType3IEC on WindDynamicsLookupTable
     * @param		 command UnAssignWindContPType3IECFromWindDynamicsLookupTableCommand
     */     
	@PutMapping("/unAssignWindContPType3IEC")
	public void unAssignWindContPType3IEC( @RequestBody(required=true)  UnAssignWindContPType3IECFromWindDynamicsLookupTableCommand command ) {
		try {
			WindDynamicsLookupTableBusinessDelegate.getWindDynamicsLookupTableInstance().unAssignWindContPType3IEC( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign WindContPType3IEC", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected WindDynamicsLookupTable windDynamicsLookupTable = null;
    private static final Logger LOGGER = Logger.getLogger(WindDynamicsLookupTableCommandRestController.class.getName());
    
}
