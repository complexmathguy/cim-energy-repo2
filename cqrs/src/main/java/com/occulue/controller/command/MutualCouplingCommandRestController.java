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
 * Implements Spring Controller command CQRS processing for entity MutualCoupling.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/MutualCoupling")
public class MutualCouplingCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a MutualCoupling.  if not key provided, calls create, otherwise calls save
     * @param		MutualCoupling	mutualCoupling
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateMutualCouplingCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = MutualCouplingBusinessDelegate.getMutualCouplingInstance().createMutualCoupling( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a MutualCoupling.  if no key provided, calls create, otherwise calls save
     * @param		MutualCoupling mutualCoupling
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateMutualCouplingCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateMutualCouplingCommand
			// -----------------------------------------------
			completableFuture = MutualCouplingBusinessDelegate.getMutualCouplingInstance().updateMutualCoupling(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "MutualCouplingController:update() - successfully update MutualCoupling - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a MutualCoupling entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID mutualCouplingId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteMutualCouplingCommand command = new DeleteMutualCouplingCommand( mutualCouplingId );

    	try {
        	MutualCouplingBusinessDelegate delegate = MutualCouplingBusinessDelegate.getMutualCouplingInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted MutualCoupling with key " + command.getMutualCouplingId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save B0ch on MutualCoupling
     * @param		command AssignB0chToMutualCouplingCommand
     */     
	@PutMapping("/assignB0ch")
	public void assignB0ch( @RequestBody AssignB0chToMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().assignB0ch( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign B0ch", exc );
        }
	}

    /**
     * unassign B0ch on MutualCoupling
     * @param		 command UnAssignB0chFromMutualCouplingCommand
     */     
	@PutMapping("/unAssignB0ch")
	public void unAssignB0ch( @RequestBody(required=true)  UnAssignB0chFromMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().unAssignB0ch( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign B0ch", exc );
		}
	}
	
    /**
     * save Distance11 on MutualCoupling
     * @param		command AssignDistance11ToMutualCouplingCommand
     */     
	@PutMapping("/assignDistance11")
	public void assignDistance11( @RequestBody AssignDistance11ToMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().assignDistance11( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Distance11", exc );
        }
	}

    /**
     * unassign Distance11 on MutualCoupling
     * @param		 command UnAssignDistance11FromMutualCouplingCommand
     */     
	@PutMapping("/unAssignDistance11")
	public void unAssignDistance11( @RequestBody(required=true)  UnAssignDistance11FromMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().unAssignDistance11( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Distance11", exc );
		}
	}
	
    /**
     * save Distance12 on MutualCoupling
     * @param		command AssignDistance12ToMutualCouplingCommand
     */     
	@PutMapping("/assignDistance12")
	public void assignDistance12( @RequestBody AssignDistance12ToMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().assignDistance12( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Distance12", exc );
        }
	}

    /**
     * unassign Distance12 on MutualCoupling
     * @param		 command UnAssignDistance12FromMutualCouplingCommand
     */     
	@PutMapping("/unAssignDistance12")
	public void unAssignDistance12( @RequestBody(required=true)  UnAssignDistance12FromMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().unAssignDistance12( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Distance12", exc );
		}
	}
	
    /**
     * save Distance21 on MutualCoupling
     * @param		command AssignDistance21ToMutualCouplingCommand
     */     
	@PutMapping("/assignDistance21")
	public void assignDistance21( @RequestBody AssignDistance21ToMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().assignDistance21( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Distance21", exc );
        }
	}

    /**
     * unassign Distance21 on MutualCoupling
     * @param		 command UnAssignDistance21FromMutualCouplingCommand
     */     
	@PutMapping("/unAssignDistance21")
	public void unAssignDistance21( @RequestBody(required=true)  UnAssignDistance21FromMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().unAssignDistance21( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Distance21", exc );
		}
	}
	
    /**
     * save Distance22 on MutualCoupling
     * @param		command AssignDistance22ToMutualCouplingCommand
     */     
	@PutMapping("/assignDistance22")
	public void assignDistance22( @RequestBody AssignDistance22ToMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().assignDistance22( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Distance22", exc );
        }
	}

    /**
     * unassign Distance22 on MutualCoupling
     * @param		 command UnAssignDistance22FromMutualCouplingCommand
     */     
	@PutMapping("/unAssignDistance22")
	public void unAssignDistance22( @RequestBody(required=true)  UnAssignDistance22FromMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().unAssignDistance22( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Distance22", exc );
		}
	}
	
    /**
     * save G0ch on MutualCoupling
     * @param		command AssignG0chToMutualCouplingCommand
     */     
	@PutMapping("/assignG0ch")
	public void assignG0ch( @RequestBody AssignG0chToMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().assignG0ch( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign G0ch", exc );
        }
	}

    /**
     * unassign G0ch on MutualCoupling
     * @param		 command UnAssignG0chFromMutualCouplingCommand
     */     
	@PutMapping("/unAssignG0ch")
	public void unAssignG0ch( @RequestBody(required=true)  UnAssignG0chFromMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().unAssignG0ch( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign G0ch", exc );
		}
	}
	
    /**
     * save R0 on MutualCoupling
     * @param		command AssignR0ToMutualCouplingCommand
     */     
	@PutMapping("/assignR0")
	public void assignR0( @RequestBody AssignR0ToMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().assignR0( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign R0", exc );
        }
	}

    /**
     * unassign R0 on MutualCoupling
     * @param		 command UnAssignR0FromMutualCouplingCommand
     */     
	@PutMapping("/unAssignR0")
	public void unAssignR0( @RequestBody(required=true)  UnAssignR0FromMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().unAssignR0( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign R0", exc );
		}
	}
	
    /**
     * save X0 on MutualCoupling
     * @param		command AssignX0ToMutualCouplingCommand
     */     
	@PutMapping("/assignX0")
	public void assignX0( @RequestBody AssignX0ToMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().assignX0( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign X0", exc );
        }
	}

    /**
     * unassign X0 on MutualCoupling
     * @param		 command UnAssignX0FromMutualCouplingCommand
     */     
	@PutMapping("/unAssignX0")
	public void unAssignX0( @RequestBody(required=true)  UnAssignX0FromMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().unAssignX0( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign X0", exc );
		}
	}
	
    /**
     * save Second_Terminal on MutualCoupling
     * @param		command AssignSecond_TerminalToMutualCouplingCommand
     */     
	@PutMapping("/assignSecond_Terminal")
	public void assignSecond_Terminal( @RequestBody AssignSecond_TerminalToMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().assignSecond_Terminal( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign Second_Terminal", exc );
        }
	}

    /**
     * unassign Second_Terminal on MutualCoupling
     * @param		 command UnAssignSecond_TerminalFromMutualCouplingCommand
     */     
	@PutMapping("/unAssignSecond_Terminal")
	public void unAssignSecond_Terminal( @RequestBody(required=true)  UnAssignSecond_TerminalFromMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().unAssignSecond_Terminal( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign Second_Terminal", exc );
		}
	}
	
    /**
     * save First_Terminal on MutualCoupling
     * @param		command AssignFirst_TerminalToMutualCouplingCommand
     */     
	@PutMapping("/assignFirst_Terminal")
	public void assignFirst_Terminal( @RequestBody AssignFirst_TerminalToMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().assignFirst_Terminal( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign First_Terminal", exc );
        }
	}

    /**
     * unassign First_Terminal on MutualCoupling
     * @param		 command UnAssignFirst_TerminalFromMutualCouplingCommand
     */     
	@PutMapping("/unAssignFirst_Terminal")
	public void unAssignFirst_Terminal( @RequestBody(required=true)  UnAssignFirst_TerminalFromMutualCouplingCommand command ) {
		try {
			MutualCouplingBusinessDelegate.getMutualCouplingInstance().unAssignFirst_Terminal( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign First_Terminal", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected MutualCoupling mutualCoupling = null;
    private static final Logger LOGGER = Logger.getLogger(MutualCouplingCommandRestController.class.getName());
    
}
