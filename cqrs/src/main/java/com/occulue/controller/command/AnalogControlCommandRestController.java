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
 * Implements Spring Controller command CQRS processing for entity AnalogControl.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/AnalogControl")
public class AnalogControlCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a AnalogControl.  if not key provided, calls create, otherwise calls save
     * @param		AnalogControl	analogControl
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateAnalogControlCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = AnalogControlBusinessDelegate.getAnalogControlInstance().createAnalogControl( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a AnalogControl.  if no key provided, calls create, otherwise calls save
     * @param		AnalogControl analogControl
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateAnalogControlCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateAnalogControlCommand
			// -----------------------------------------------
			completableFuture = AnalogControlBusinessDelegate.getAnalogControlInstance().updateAnalogControl(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "AnalogControlController:update() - successfully update AnalogControl - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a AnalogControl entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID analogControlId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteAnalogControlCommand command = new DeleteAnalogControlCommand( analogControlId );

    	try {
        	AnalogControlBusinessDelegate delegate = AnalogControlBusinessDelegate.getAnalogControlInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted AnalogControl with key " + command.getAnalogControlId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save MaxValue on AnalogControl
     * @param		command AssignMaxValueToAnalogControlCommand
     */     
	@PutMapping("/assignMaxValue")
	public void assignMaxValue( @RequestBody AssignMaxValueToAnalogControlCommand command ) {
		try {
			AnalogControlBusinessDelegate.getAnalogControlInstance().assignMaxValue( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign MaxValue", exc );
        }
	}

    /**
     * unassign MaxValue on AnalogControl
     * @param		 command UnAssignMaxValueFromAnalogControlCommand
     */     
	@PutMapping("/unAssignMaxValue")
	public void unAssignMaxValue( @RequestBody(required=true)  UnAssignMaxValueFromAnalogControlCommand command ) {
		try {
			AnalogControlBusinessDelegate.getAnalogControlInstance().unAssignMaxValue( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign MaxValue", exc );
		}
	}
	
    /**
     * save MinValue on AnalogControl
     * @param		command AssignMinValueToAnalogControlCommand
     */     
	@PutMapping("/assignMinValue")
	public void assignMinValue( @RequestBody AssignMinValueToAnalogControlCommand command ) {
		try {
			AnalogControlBusinessDelegate.getAnalogControlInstance().assignMinValue( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign MinValue", exc );
        }
	}

    /**
     * unassign MinValue on AnalogControl
     * @param		 command UnAssignMinValueFromAnalogControlCommand
     */     
	@PutMapping("/unAssignMinValue")
	public void unAssignMinValue( @RequestBody(required=true)  UnAssignMinValueFromAnalogControlCommand command ) {
		try {
			AnalogControlBusinessDelegate.getAnalogControlInstance().unAssignMinValue( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign MinValue", exc );
		}
	}
	
    /**
     * save AnalogValue on AnalogControl
     * @param		command AssignAnalogValueToAnalogControlCommand
     */     
	@PutMapping("/assignAnalogValue")
	public void assignAnalogValue( @RequestBody AssignAnalogValueToAnalogControlCommand command ) {
		try {
			AnalogControlBusinessDelegate.getAnalogControlInstance().assignAnalogValue( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign AnalogValue", exc );
        }
	}

    /**
     * unassign AnalogValue on AnalogControl
     * @param		 command UnAssignAnalogValueFromAnalogControlCommand
     */     
	@PutMapping("/unAssignAnalogValue")
	public void unAssignAnalogValue( @RequestBody(required=true)  UnAssignAnalogValueFromAnalogControlCommand command ) {
		try {
			AnalogControlBusinessDelegate.getAnalogControlInstance().unAssignAnalogValue( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign AnalogValue", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected AnalogControl analogControl = null;
    private static final Logger LOGGER = Logger.getLogger(AnalogControlCommandRestController.class.getName());
    
}
