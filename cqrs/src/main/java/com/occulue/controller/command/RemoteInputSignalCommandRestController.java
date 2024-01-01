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
 * Implements Spring Controller command CQRS processing for entity RemoteInputSignal.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/RemoteInputSignal")
public class RemoteInputSignalCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a RemoteInputSignal.  if not key provided, calls create, otherwise calls save
     * @param		RemoteInputSignal	remoteInputSignal
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateRemoteInputSignalCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().createRemoteInputSignal( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a RemoteInputSignal.  if no key provided, calls create, otherwise calls save
     * @param		RemoteInputSignal remoteInputSignal
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateRemoteInputSignalCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateRemoteInputSignalCommand
			// -----------------------------------------------
			completableFuture = RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().updateRemoteInputSignal(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "RemoteInputSignalController:update() - successfully update RemoteInputSignal - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a RemoteInputSignal entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID remoteInputSignalId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteRemoteInputSignalCommand command = new DeleteRemoteInputSignalCommand( remoteInputSignalId );

    	try {
        	RemoteInputSignalBusinessDelegate delegate = RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted RemoteInputSignal with key " + command.getRemoteInputSignalId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save Terminal on RemoteInputSignal
     * @param		command AssignTerminalToRemoteInputSignalCommand
     */     
	@PutMapping("/assignTerminal")
	public void assignTerminal( @RequestBody AssignTerminalToRemoteInputSignalCommand command ) {
		try {
			RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().assignTerminal( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Terminal", exc );
        }
	}

    /**
     * unassign Terminal on RemoteInputSignal
     * @param		 command UnAssignTerminalFromRemoteInputSignalCommand
     */     
	@PutMapping("/unAssignTerminal")
	public void unAssignTerminal( @RequestBody(required=true)  UnAssignTerminalFromRemoteInputSignalCommand command ) {
		try {
			RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().unAssignTerminal( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Terminal", exc );
		}
	}
	
    /**
     * save DiscontinuousExcitationControlDynamics on RemoteInputSignal
     * @param		command AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalCommand
     */     
	@PutMapping("/assignDiscontinuousExcitationControlDynamics")
	public void assignDiscontinuousExcitationControlDynamics( @RequestBody AssignDiscontinuousExcitationControlDynamicsToRemoteInputSignalCommand command ) {
		try {
			RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().assignDiscontinuousExcitationControlDynamics( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign DiscontinuousExcitationControlDynamics", exc );
        }
	}

    /**
     * unassign DiscontinuousExcitationControlDynamics on RemoteInputSignal
     * @param		 command UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalCommand
     */     
	@PutMapping("/unAssignDiscontinuousExcitationControlDynamics")
	public void unAssignDiscontinuousExcitationControlDynamics( @RequestBody(required=true)  UnAssignDiscontinuousExcitationControlDynamicsFromRemoteInputSignalCommand command ) {
		try {
			RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().unAssignDiscontinuousExcitationControlDynamics( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign DiscontinuousExcitationControlDynamics", exc );
		}
	}
	
    /**
     * save PowerSystemStabilizerDynamics on RemoteInputSignal
     * @param		command AssignPowerSystemStabilizerDynamicsToRemoteInputSignalCommand
     */     
	@PutMapping("/assignPowerSystemStabilizerDynamics")
	public void assignPowerSystemStabilizerDynamics( @RequestBody AssignPowerSystemStabilizerDynamicsToRemoteInputSignalCommand command ) {
		try {
			RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().assignPowerSystemStabilizerDynamics( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign PowerSystemStabilizerDynamics", exc );
        }
	}

    /**
     * unassign PowerSystemStabilizerDynamics on RemoteInputSignal
     * @param		 command UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalCommand
     */     
	@PutMapping("/unAssignPowerSystemStabilizerDynamics")
	public void unAssignPowerSystemStabilizerDynamics( @RequestBody(required=true)  UnAssignPowerSystemStabilizerDynamicsFromRemoteInputSignalCommand command ) {
		try {
			RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().unAssignPowerSystemStabilizerDynamics( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign PowerSystemStabilizerDynamics", exc );
		}
	}
	
    /**
     * save VoltageCompensatorDynamics on RemoteInputSignal
     * @param		command AssignVoltageCompensatorDynamicsToRemoteInputSignalCommand
     */     
	@PutMapping("/assignVoltageCompensatorDynamics")
	public void assignVoltageCompensatorDynamics( @RequestBody AssignVoltageCompensatorDynamicsToRemoteInputSignalCommand command ) {
		try {
			RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().assignVoltageCompensatorDynamics( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign VoltageCompensatorDynamics", exc );
        }
	}

    /**
     * unassign VoltageCompensatorDynamics on RemoteInputSignal
     * @param		 command UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalCommand
     */     
	@PutMapping("/unAssignVoltageCompensatorDynamics")
	public void unAssignVoltageCompensatorDynamics( @RequestBody(required=true)  UnAssignVoltageCompensatorDynamicsFromRemoteInputSignalCommand command ) {
		try {
			RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().unAssignVoltageCompensatorDynamics( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign VoltageCompensatorDynamics", exc );
		}
	}
	
    /**
     * save UnderexcitationLimiterDynamics on RemoteInputSignal
     * @param		command AssignUnderexcitationLimiterDynamicsToRemoteInputSignalCommand
     */     
	@PutMapping("/assignUnderexcitationLimiterDynamics")
	public void assignUnderexcitationLimiterDynamics( @RequestBody AssignUnderexcitationLimiterDynamicsToRemoteInputSignalCommand command ) {
		try {
			RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().assignUnderexcitationLimiterDynamics( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign UnderexcitationLimiterDynamics", exc );
        }
	}

    /**
     * unassign UnderexcitationLimiterDynamics on RemoteInputSignal
     * @param		 command UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalCommand
     */     
	@PutMapping("/unAssignUnderexcitationLimiterDynamics")
	public void unAssignUnderexcitationLimiterDynamics( @RequestBody(required=true)  UnAssignUnderexcitationLimiterDynamicsFromRemoteInputSignalCommand command ) {
		try {
			RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().unAssignUnderexcitationLimiterDynamics( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign UnderexcitationLimiterDynamics", exc );
		}
	}
	
    /**
     * save PFVArControllerType1Dynamics on RemoteInputSignal
     * @param		command AssignPFVArControllerType1DynamicsToRemoteInputSignalCommand
     */     
	@PutMapping("/assignPFVArControllerType1Dynamics")
	public void assignPFVArControllerType1Dynamics( @RequestBody AssignPFVArControllerType1DynamicsToRemoteInputSignalCommand command ) {
		try {
			RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().assignPFVArControllerType1Dynamics( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign PFVArControllerType1Dynamics", exc );
        }
	}

    /**
     * unassign PFVArControllerType1Dynamics on RemoteInputSignal
     * @param		 command UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalCommand
     */     
	@PutMapping("/unAssignPFVArControllerType1Dynamics")
	public void unAssignPFVArControllerType1Dynamics( @RequestBody(required=true)  UnAssignPFVArControllerType1DynamicsFromRemoteInputSignalCommand command ) {
		try {
			RemoteInputSignalBusinessDelegate.getRemoteInputSignalInstance().unAssignPFVArControllerType1Dynamics( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign PFVArControllerType1Dynamics", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected RemoteInputSignal remoteInputSignal = null;
    private static final Logger LOGGER = Logger.getLogger(RemoteInputSignalCommandRestController.class.getName());
    
}
