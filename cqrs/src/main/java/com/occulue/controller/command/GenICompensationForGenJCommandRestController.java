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
 * Implements Spring Controller command CQRS processing for entity GenICompensationForGenJ.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/GenICompensationForGenJ")
public class GenICompensationForGenJCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a GenICompensationForGenJ.  if not key provided, calls create, otherwise calls save
     * @param		GenICompensationForGenJ	genICompensationForGenJ
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateGenICompensationForGenJCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = GenICompensationForGenJBusinessDelegate.getGenICompensationForGenJInstance().createGenICompensationForGenJ( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a GenICompensationForGenJ.  if no key provided, calls create, otherwise calls save
     * @param		GenICompensationForGenJ genICompensationForGenJ
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateGenICompensationForGenJCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateGenICompensationForGenJCommand
			// -----------------------------------------------
			completableFuture = GenICompensationForGenJBusinessDelegate.getGenICompensationForGenJInstance().updateGenICompensationForGenJ(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "GenICompensationForGenJController:update() - successfully update GenICompensationForGenJ - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a GenICompensationForGenJ entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID genICompensationForGenJId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteGenICompensationForGenJCommand command = new DeleteGenICompensationForGenJCommand( genICompensationForGenJId );

    	try {
        	GenICompensationForGenJBusinessDelegate delegate = GenICompensationForGenJBusinessDelegate.getGenICompensationForGenJInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted GenICompensationForGenJ with key " + command.getGenICompensationForGenJId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save Rcij on GenICompensationForGenJ
     * @param		command AssignRcijToGenICompensationForGenJCommand
     */     
	@PutMapping("/assignRcij")
	public void assignRcij( @RequestBody AssignRcijToGenICompensationForGenJCommand command ) {
		try {
			GenICompensationForGenJBusinessDelegate.getGenICompensationForGenJInstance().assignRcij( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Rcij", exc );
        }
	}

    /**
     * unassign Rcij on GenICompensationForGenJ
     * @param		 command UnAssignRcijFromGenICompensationForGenJCommand
     */     
	@PutMapping("/unAssignRcij")
	public void unAssignRcij( @RequestBody(required=true)  UnAssignRcijFromGenICompensationForGenJCommand command ) {
		try {
			GenICompensationForGenJBusinessDelegate.getGenICompensationForGenJInstance().unAssignRcij( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Rcij", exc );
		}
	}
	
    /**
     * save Xcij on GenICompensationForGenJ
     * @param		command AssignXcijToGenICompensationForGenJCommand
     */     
	@PutMapping("/assignXcij")
	public void assignXcij( @RequestBody AssignXcijToGenICompensationForGenJCommand command ) {
		try {
			GenICompensationForGenJBusinessDelegate.getGenICompensationForGenJInstance().assignXcij( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Xcij", exc );
        }
	}

    /**
     * unassign Xcij on GenICompensationForGenJ
     * @param		 command UnAssignXcijFromGenICompensationForGenJCommand
     */     
	@PutMapping("/unAssignXcij")
	public void unAssignXcij( @RequestBody(required=true)  UnAssignXcijFromGenICompensationForGenJCommand command ) {
		try {
			GenICompensationForGenJBusinessDelegate.getGenICompensationForGenJInstance().unAssignXcij( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Xcij", exc );
		}
	}
	
    /**
     * save SynchronousMachineDynamics on GenICompensationForGenJ
     * @param		command AssignSynchronousMachineDynamicsToGenICompensationForGenJCommand
     */     
	@PutMapping("/assignSynchronousMachineDynamics")
	public void assignSynchronousMachineDynamics( @RequestBody AssignSynchronousMachineDynamicsToGenICompensationForGenJCommand command ) {
		try {
			GenICompensationForGenJBusinessDelegate.getGenICompensationForGenJInstance().assignSynchronousMachineDynamics( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign SynchronousMachineDynamics", exc );
        }
	}

    /**
     * unassign SynchronousMachineDynamics on GenICompensationForGenJ
     * @param		 command UnAssignSynchronousMachineDynamicsFromGenICompensationForGenJCommand
     */     
	@PutMapping("/unAssignSynchronousMachineDynamics")
	public void unAssignSynchronousMachineDynamics( @RequestBody(required=true)  UnAssignSynchronousMachineDynamicsFromGenICompensationForGenJCommand command ) {
		try {
			GenICompensationForGenJBusinessDelegate.getGenICompensationForGenJInstance().unAssignSynchronousMachineDynamics( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign SynchronousMachineDynamics", exc );
		}
	}
	
    /**
     * save VcompIEEEType2 on GenICompensationForGenJ
     * @param		command AssignVcompIEEEType2ToGenICompensationForGenJCommand
     */     
	@PutMapping("/assignVcompIEEEType2")
	public void assignVcompIEEEType2( @RequestBody AssignVcompIEEEType2ToGenICompensationForGenJCommand command ) {
		try {
			GenICompensationForGenJBusinessDelegate.getGenICompensationForGenJInstance().assignVcompIEEEType2( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign VcompIEEEType2", exc );
        }
	}

    /**
     * unassign VcompIEEEType2 on GenICompensationForGenJ
     * @param		 command UnAssignVcompIEEEType2FromGenICompensationForGenJCommand
     */     
	@PutMapping("/unAssignVcompIEEEType2")
	public void unAssignVcompIEEEType2( @RequestBody(required=true)  UnAssignVcompIEEEType2FromGenICompensationForGenJCommand command ) {
		try {
			GenICompensationForGenJBusinessDelegate.getGenICompensationForGenJInstance().unAssignVcompIEEEType2( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign VcompIEEEType2", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected GenICompensationForGenJ genICompensationForGenJ = null;
    private static final Logger LOGGER = Logger.getLogger(GenICompensationForGenJCommandRestController.class.getName());
    
}
