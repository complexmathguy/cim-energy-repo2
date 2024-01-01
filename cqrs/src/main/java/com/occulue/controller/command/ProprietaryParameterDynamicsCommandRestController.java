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
 * Implements Spring Controller command CQRS processing for entity ProprietaryParameterDynamics.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/ProprietaryParameterDynamics")
public class ProprietaryParameterDynamicsCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a ProprietaryParameterDynamics.  if not key provided, calls create, otherwise calls save
     * @param		ProprietaryParameterDynamics	proprietaryParameterDynamics
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateProprietaryParameterDynamicsCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().createProprietaryParameterDynamics( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a ProprietaryParameterDynamics.  if no key provided, calls create, otherwise calls save
     * @param		ProprietaryParameterDynamics proprietaryParameterDynamics
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateProprietaryParameterDynamicsCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateProprietaryParameterDynamicsCommand
			// -----------------------------------------------
			completableFuture = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().updateProprietaryParameterDynamics(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "ProprietaryParameterDynamicsController:update() - successfully update ProprietaryParameterDynamics - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a ProprietaryParameterDynamics entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID proprietaryParameterDynamicsId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteProprietaryParameterDynamicsCommand command = new DeleteProprietaryParameterDynamicsCommand( proprietaryParameterDynamicsId );

    	try {
        	ProprietaryParameterDynamicsBusinessDelegate delegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted ProprietaryParameterDynamics with key " + command.getProprietaryParameterDynamicsId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save BooleanParameterValue on ProprietaryParameterDynamics
     * @param		command AssignBooleanParameterValueToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignBooleanParameterValue")
	public void assignBooleanParameterValue( @RequestBody AssignBooleanParameterValueToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignBooleanParameterValue( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign BooleanParameterValue", exc );
        }
	}

    /**
     * unassign BooleanParameterValue on ProprietaryParameterDynamics
     * @param		 command UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignBooleanParameterValue")
	public void unAssignBooleanParameterValue( @RequestBody(required=true)  UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignBooleanParameterValue( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign BooleanParameterValue", exc );
		}
	}
	
    /**
     * save FloatParameterValue on ProprietaryParameterDynamics
     * @param		command AssignFloatParameterValueToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignFloatParameterValue")
	public void assignFloatParameterValue( @RequestBody AssignFloatParameterValueToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignFloatParameterValue( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign FloatParameterValue", exc );
        }
	}

    /**
     * unassign FloatParameterValue on ProprietaryParameterDynamics
     * @param		 command UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignFloatParameterValue")
	public void unAssignFloatParameterValue( @RequestBody(required=true)  UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignFloatParameterValue( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign FloatParameterValue", exc );
		}
	}
	
    /**
     * save IntegerParameterValue on ProprietaryParameterDynamics
     * @param		command AssignIntegerParameterValueToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignIntegerParameterValue")
	public void assignIntegerParameterValue( @RequestBody AssignIntegerParameterValueToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignIntegerParameterValue( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign IntegerParameterValue", exc );
        }
	}

    /**
     * unassign IntegerParameterValue on ProprietaryParameterDynamics
     * @param		 command UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignIntegerParameterValue")
	public void unAssignIntegerParameterValue( @RequestBody(required=true)  UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignIntegerParameterValue( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign IntegerParameterValue", exc );
		}
	}
	
    /**
     * save ParameterNumber on ProprietaryParameterDynamics
     * @param		command AssignParameterNumberToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignParameterNumber")
	public void assignParameterNumber( @RequestBody AssignParameterNumberToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignParameterNumber( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign ParameterNumber", exc );
        }
	}

    /**
     * unassign ParameterNumber on ProprietaryParameterDynamics
     * @param		 command UnAssignParameterNumberFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignParameterNumber")
	public void unAssignParameterNumber( @RequestBody(required=true)  UnAssignParameterNumberFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignParameterNumber( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign ParameterNumber", exc );
		}
	}
	
    /**
     * save PFVArControllerType1UserDefined on ProprietaryParameterDynamics
     * @param		command AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignPFVArControllerType1UserDefined")
	public void assignPFVArControllerType1UserDefined( @RequestBody AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignPFVArControllerType1UserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign PFVArControllerType1UserDefined", exc );
        }
	}

    /**
     * unassign PFVArControllerType1UserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignPFVArControllerType1UserDefined")
	public void unAssignPFVArControllerType1UserDefined( @RequestBody(required=true)  UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignPFVArControllerType1UserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign PFVArControllerType1UserDefined", exc );
		}
	}
	
    /**
     * save VoltageCompensatorUserDefined on ProprietaryParameterDynamics
     * @param		command AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignVoltageCompensatorUserDefined")
	public void assignVoltageCompensatorUserDefined( @RequestBody AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignVoltageCompensatorUserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign VoltageCompensatorUserDefined", exc );
        }
	}

    /**
     * unassign VoltageCompensatorUserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignVoltageCompensatorUserDefined")
	public void unAssignVoltageCompensatorUserDefined( @RequestBody(required=true)  UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignVoltageCompensatorUserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign VoltageCompensatorUserDefined", exc );
		}
	}
	
    /**
     * save MechanicalLoadUserDefined on ProprietaryParameterDynamics
     * @param		command AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignMechanicalLoadUserDefined")
	public void assignMechanicalLoadUserDefined( @RequestBody AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignMechanicalLoadUserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign MechanicalLoadUserDefined", exc );
        }
	}

    /**
     * unassign MechanicalLoadUserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignMechanicalLoadUserDefined")
	public void unAssignMechanicalLoadUserDefined( @RequestBody(required=true)  UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignMechanicalLoadUserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign MechanicalLoadUserDefined", exc );
		}
	}
	
    /**
     * save ExcitationSystemUserDefined on ProprietaryParameterDynamics
     * @param		command AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignExcitationSystemUserDefined")
	public void assignExcitationSystemUserDefined( @RequestBody AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignExcitationSystemUserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign ExcitationSystemUserDefined", exc );
        }
	}

    /**
     * unassign ExcitationSystemUserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignExcitationSystemUserDefined")
	public void unAssignExcitationSystemUserDefined( @RequestBody(required=true)  UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignExcitationSystemUserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign ExcitationSystemUserDefined", exc );
		}
	}
	
    /**
     * save AsynchronousMachineUserDefined on ProprietaryParameterDynamics
     * @param		command AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignAsynchronousMachineUserDefined")
	public void assignAsynchronousMachineUserDefined( @RequestBody AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignAsynchronousMachineUserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign AsynchronousMachineUserDefined", exc );
        }
	}

    /**
     * unassign AsynchronousMachineUserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignAsynchronousMachineUserDefined")
	public void unAssignAsynchronousMachineUserDefined( @RequestBody(required=true)  UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignAsynchronousMachineUserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign AsynchronousMachineUserDefined", exc );
		}
	}
	
    /**
     * save DiscontinuousExcitationControlUserDefined on ProprietaryParameterDynamics
     * @param		command AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignDiscontinuousExcitationControlUserDefined")
	public void assignDiscontinuousExcitationControlUserDefined( @RequestBody AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignDiscontinuousExcitationControlUserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign DiscontinuousExcitationControlUserDefined", exc );
        }
	}

    /**
     * unassign DiscontinuousExcitationControlUserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignDiscontinuousExcitationControlUserDefined")
	public void unAssignDiscontinuousExcitationControlUserDefined( @RequestBody(required=true)  UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignDiscontinuousExcitationControlUserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign DiscontinuousExcitationControlUserDefined", exc );
		}
	}
	
    /**
     * save TurbineGovernorUserDefined on ProprietaryParameterDynamics
     * @param		command AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignTurbineGovernorUserDefined")
	public void assignTurbineGovernorUserDefined( @RequestBody AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignTurbineGovernorUserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign TurbineGovernorUserDefined", exc );
        }
	}

    /**
     * unassign TurbineGovernorUserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignTurbineGovernorUserDefined")
	public void unAssignTurbineGovernorUserDefined( @RequestBody(required=true)  UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignTurbineGovernorUserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign TurbineGovernorUserDefined", exc );
		}
	}
	
    /**
     * save VoltageAdjusterUserDefined on ProprietaryParameterDynamics
     * @param		command AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignVoltageAdjusterUserDefined")
	public void assignVoltageAdjusterUserDefined( @RequestBody AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignVoltageAdjusterUserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign VoltageAdjusterUserDefined", exc );
        }
	}

    /**
     * unassign VoltageAdjusterUserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignVoltageAdjusterUserDefined")
	public void unAssignVoltageAdjusterUserDefined( @RequestBody(required=true)  UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignVoltageAdjusterUserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign VoltageAdjusterUserDefined", exc );
		}
	}
	
    /**
     * save SynchronousMachineUserDefined on ProprietaryParameterDynamics
     * @param		command AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignSynchronousMachineUserDefined")
	public void assignSynchronousMachineUserDefined( @RequestBody AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignSynchronousMachineUserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign SynchronousMachineUserDefined", exc );
        }
	}

    /**
     * unassign SynchronousMachineUserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignSynchronousMachineUserDefined")
	public void unAssignSynchronousMachineUserDefined( @RequestBody(required=true)  UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignSynchronousMachineUserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign SynchronousMachineUserDefined", exc );
		}
	}
	
    /**
     * save UnderexcitationLimiterUserDefined on ProprietaryParameterDynamics
     * @param		command AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignUnderexcitationLimiterUserDefined")
	public void assignUnderexcitationLimiterUserDefined( @RequestBody AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignUnderexcitationLimiterUserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign UnderexcitationLimiterUserDefined", exc );
        }
	}

    /**
     * unassign UnderexcitationLimiterUserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignUnderexcitationLimiterUserDefined")
	public void unAssignUnderexcitationLimiterUserDefined( @RequestBody(required=true)  UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignUnderexcitationLimiterUserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign UnderexcitationLimiterUserDefined", exc );
		}
	}
	
    /**
     * save TurbineLoadControllerUserDefined on ProprietaryParameterDynamics
     * @param		command AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignTurbineLoadControllerUserDefined")
	public void assignTurbineLoadControllerUserDefined( @RequestBody AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignTurbineLoadControllerUserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign TurbineLoadControllerUserDefined", exc );
        }
	}

    /**
     * unassign TurbineLoadControllerUserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignTurbineLoadControllerUserDefined")
	public void unAssignTurbineLoadControllerUserDefined( @RequestBody(required=true)  UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignTurbineLoadControllerUserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign TurbineLoadControllerUserDefined", exc );
		}
	}
	
    /**
     * save OverexcitationLimiterUserDefined on ProprietaryParameterDynamics
     * @param		command AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignOverexcitationLimiterUserDefined")
	public void assignOverexcitationLimiterUserDefined( @RequestBody AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignOverexcitationLimiterUserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign OverexcitationLimiterUserDefined", exc );
        }
	}

    /**
     * unassign OverexcitationLimiterUserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignOverexcitationLimiterUserDefined")
	public void unAssignOverexcitationLimiterUserDefined( @RequestBody(required=true)  UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignOverexcitationLimiterUserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign OverexcitationLimiterUserDefined", exc );
		}
	}
	
    /**
     * save PowerSystemStabilizerUserDefined on ProprietaryParameterDynamics
     * @param		command AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignPowerSystemStabilizerUserDefined")
	public void assignPowerSystemStabilizerUserDefined( @RequestBody AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignPowerSystemStabilizerUserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign PowerSystemStabilizerUserDefined", exc );
        }
	}

    /**
     * unassign PowerSystemStabilizerUserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignPowerSystemStabilizerUserDefined")
	public void unAssignPowerSystemStabilizerUserDefined( @RequestBody(required=true)  UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignPowerSystemStabilizerUserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign PowerSystemStabilizerUserDefined", exc );
		}
	}
	
    /**
     * save LoadUserDefined on ProprietaryParameterDynamics
     * @param		command AssignLoadUserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignLoadUserDefined")
	public void assignLoadUserDefined( @RequestBody AssignLoadUserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignLoadUserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign LoadUserDefined", exc );
        }
	}

    /**
     * unassign LoadUserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignLoadUserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignLoadUserDefined")
	public void unAssignLoadUserDefined( @RequestBody(required=true)  UnAssignLoadUserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignLoadUserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign LoadUserDefined", exc );
		}
	}
	
    /**
     * save PFVArControllerType2UserDefined on ProprietaryParameterDynamics
     * @param		command AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignPFVArControllerType2UserDefined")
	public void assignPFVArControllerType2UserDefined( @RequestBody AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignPFVArControllerType2UserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign PFVArControllerType2UserDefined", exc );
        }
	}

    /**
     * unassign PFVArControllerType2UserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignPFVArControllerType2UserDefined")
	public void unAssignPFVArControllerType2UserDefined( @RequestBody(required=true)  UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignPFVArControllerType2UserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign PFVArControllerType2UserDefined", exc );
		}
	}
	
    /**
     * save WindType3or4UserDefined on ProprietaryParameterDynamics
     * @param		command AssignWindType3or4UserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignWindType3or4UserDefined")
	public void assignWindType3or4UserDefined( @RequestBody AssignWindType3or4UserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignWindType3or4UserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign WindType3or4UserDefined", exc );
        }
	}

    /**
     * unassign WindType3or4UserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignWindType3or4UserDefined")
	public void unAssignWindType3or4UserDefined( @RequestBody(required=true)  UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignWindType3or4UserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign WindType3or4UserDefined", exc );
		}
	}
	
    /**
     * save WindPlantUserDefined on ProprietaryParameterDynamics
     * @param		command AssignWindPlantUserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignWindPlantUserDefined")
	public void assignWindPlantUserDefined( @RequestBody AssignWindPlantUserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignWindPlantUserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign WindPlantUserDefined", exc );
        }
	}

    /**
     * unassign WindPlantUserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignWindPlantUserDefined")
	public void unAssignWindPlantUserDefined( @RequestBody(required=true)  UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignWindPlantUserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign WindPlantUserDefined", exc );
		}
	}
	
    /**
     * save WindType1or2UserDefined on ProprietaryParameterDynamics
     * @param		command AssignWindType1or2UserDefinedToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignWindType1or2UserDefined")
	public void assignWindType1or2UserDefined( @RequestBody AssignWindType1or2UserDefinedToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignWindType1or2UserDefined( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign WindType1or2UserDefined", exc );
        }
	}

    /**
     * unassign WindType1or2UserDefined on ProprietaryParameterDynamics
     * @param		 command UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignWindType1or2UserDefined")
	public void unAssignWindType1or2UserDefined( @RequestBody(required=true)  UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignWindType1or2UserDefined( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign WindType1or2UserDefined", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected ProprietaryParameterDynamics proprietaryParameterDynamics = null;
    private static final Logger LOGGER = Logger.getLogger(ProprietaryParameterDynamicsCommandRestController.class.getName());
    
}
