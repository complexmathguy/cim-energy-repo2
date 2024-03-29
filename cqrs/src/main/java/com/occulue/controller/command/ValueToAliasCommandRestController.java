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
 * Implements Spring Controller command CQRS processing for entity ValueToAlias.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/ValueToAlias")
public class ValueToAliasCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a ValueToAlias.  if not key provided, calls create, otherwise calls save
     * @param		ValueToAlias	valueToAlias
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateValueToAliasCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = ValueToAliasBusinessDelegate.getValueToAliasInstance().createValueToAlias( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a ValueToAlias.  if no key provided, calls create, otherwise calls save
     * @param		ValueToAlias valueToAlias
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateValueToAliasCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateValueToAliasCommand
			// -----------------------------------------------
			completableFuture = ValueToAliasBusinessDelegate.getValueToAliasInstance().updateValueToAlias(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "ValueToAliasController:update() - successfully update ValueToAlias - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a ValueToAlias entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID valueToAliasId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteValueToAliasCommand command = new DeleteValueToAliasCommand( valueToAliasId );

    	try {
        	ValueToAliasBusinessDelegate delegate = ValueToAliasBusinessDelegate.getValueToAliasInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted ValueToAlias with key " + command.getValueToAliasId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save Value on ValueToAlias
     * @param		command AssignValueToValueToAliasCommand
     */     
	@PutMapping("/assignValue")
	public void assignValue( @RequestBody AssignValueToValueToAliasCommand command ) {
		try {
			ValueToAliasBusinessDelegate.getValueToAliasInstance().assignValue( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Value", exc );
        }
	}

    /**
     * unassign Value on ValueToAlias
     * @param		 command UnAssignValueFromValueToAliasCommand
     */     
	@PutMapping("/unAssignValue")
	public void unAssignValue( @RequestBody(required=true)  UnAssignValueFromValueToAliasCommand command ) {
		try {
			ValueToAliasBusinessDelegate.getValueToAliasInstance().unAssignValue( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Value", exc );
		}
	}
	

    /**
     * save ValueAliasSet on ValueToAlias
     * @param		command AssignValueAliasSetToValueToAliasCommand
     */     
	@PutMapping("/addToValueAliasSet")
	public void addToValueAliasSet( @RequestBody(required=true) AssignValueAliasSetToValueToAliasCommand command ) {
		try {
			ValueToAliasBusinessDelegate.getValueToAliasInstance().addToValueAliasSet( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to add to Set ValueAliasSet", exc );
		}
	}

    /**
     * remove ValueAliasSet on ValueToAlias
     * @param		command RemoveValueAliasSetFromValueToAliasCommand
     */     	
	@PutMapping("/removeFromValueAliasSet")
	public void removeFromValueAliasSet( 	@RequestBody(required=true) RemoveValueAliasSetFromValueToAliasCommand command )
	{		
		try {
			ValueToAliasBusinessDelegate.getValueToAliasInstance().removeFromValueAliasSet( command );
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to remove from Set ValueAliasSet", exc );
		}
	}


//************************************************************************    
// Attributes
//************************************************************************
    protected ValueToAlias valueToAlias = null;
    private static final Logger LOGGER = Logger.getLogger(ValueToAliasCommandRestController.class.getName());
    
}
