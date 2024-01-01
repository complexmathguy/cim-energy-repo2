/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.validator;

import org.springframework.util.Assert;

import com.occulue.api.*;

public class RotatingMachineValidator {
		
	/**
	 * default constructor
	 */
	protected RotatingMachineValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public RotatingMachineValidator getInstance() {
		return new RotatingMachineValidator();
	}
		
	/**
	 * handles creation validation for a RotatingMachine
	 */
	public void validate( CreateRotatingMachineCommand rotatingMachine )throws Exception {
		Assert.notNull( rotatingMachine, "CreateRotatingMachineCommand should not be null" );
//		Assert.isNull( rotatingMachine.getRotatingMachineId(), "CreateRotatingMachineCommand identifier should be null" );
	}

	/**
	 * handles update validation for a RotatingMachine
	 */
	public void validate( UpdateRotatingMachineCommand rotatingMachine ) throws Exception {
		Assert.notNull( rotatingMachine, "UpdateRotatingMachineCommand should not be null" );
		Assert.notNull( rotatingMachine.getRotatingMachineId(), "UpdateRotatingMachineCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a RotatingMachine
	 */
    public void validate( DeleteRotatingMachineCommand rotatingMachine ) throws Exception {
		Assert.notNull( rotatingMachine, "{commandAlias} should not be null" );
		Assert.notNull( rotatingMachine.getRotatingMachineId(), "DeleteRotatingMachineCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a RotatingMachine
	 */
	public void validate( RotatingMachineFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "RotatingMachineFetchOneSummary should not be null" );
		Assert.notNull( summary.getRotatingMachineId(), "RotatingMachineFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign RatedPowerFactor validation for a RotatingMachine
	 * 
	 * @param	command AssignRatedPowerFactorToRotatingMachineCommand
	 */	
	public void validate( AssignRatedPowerFactorToRotatingMachineCommand command ) throws Exception {
		Assert.notNull( command, "AssignRatedPowerFactorToRotatingMachineCommand should not be null" );
		Assert.notNull( command.getRotatingMachineId(), "AssignRatedPowerFactorToRotatingMachineCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignRatedPowerFactorToRotatingMachineCommand assignment should not be null" );
	}

	/**
	 * handles unassign RatedPowerFactor validation for a RotatingMachine
	 * 
	 * @param	command UnAssignRatedPowerFactorFromRotatingMachineCommand
	 */	
	public void validate( UnAssignRatedPowerFactorFromRotatingMachineCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignRatedPowerFactorFromRotatingMachineCommand should not be null" );
		Assert.notNull( command.getRotatingMachineId(), "UnAssignRatedPowerFactorFromRotatingMachineCommand identifier should not be null" );
	}
	/**
	 * handles assign RatedS validation for a RotatingMachine
	 * 
	 * @param	command AssignRatedSToRotatingMachineCommand
	 */	
	public void validate( AssignRatedSToRotatingMachineCommand command ) throws Exception {
		Assert.notNull( command, "AssignRatedSToRotatingMachineCommand should not be null" );
		Assert.notNull( command.getRotatingMachineId(), "AssignRatedSToRotatingMachineCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignRatedSToRotatingMachineCommand assignment should not be null" );
	}

	/**
	 * handles unassign RatedS validation for a RotatingMachine
	 * 
	 * @param	command UnAssignRatedSFromRotatingMachineCommand
	 */	
	public void validate( UnAssignRatedSFromRotatingMachineCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignRatedSFromRotatingMachineCommand should not be null" );
		Assert.notNull( command.getRotatingMachineId(), "UnAssignRatedSFromRotatingMachineCommand identifier should not be null" );
	}
	/**
	 * handles assign RatedU validation for a RotatingMachine
	 * 
	 * @param	command AssignRatedUToRotatingMachineCommand
	 */	
	public void validate( AssignRatedUToRotatingMachineCommand command ) throws Exception {
		Assert.notNull( command, "AssignRatedUToRotatingMachineCommand should not be null" );
		Assert.notNull( command.getRotatingMachineId(), "AssignRatedUToRotatingMachineCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignRatedUToRotatingMachineCommand assignment should not be null" );
	}

	/**
	 * handles unassign RatedU validation for a RotatingMachine
	 * 
	 * @param	command UnAssignRatedUFromRotatingMachineCommand
	 */	
	public void validate( UnAssignRatedUFromRotatingMachineCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignRatedUFromRotatingMachineCommand should not be null" );
		Assert.notNull( command.getRotatingMachineId(), "UnAssignRatedUFromRotatingMachineCommand identifier should not be null" );
	}
	/**
	 * handles assign GeneratingUnit validation for a RotatingMachine
	 * 
	 * @param	command AssignGeneratingUnitToRotatingMachineCommand
	 */	
	public void validate( AssignGeneratingUnitToRotatingMachineCommand command ) throws Exception {
		Assert.notNull( command, "AssignGeneratingUnitToRotatingMachineCommand should not be null" );
		Assert.notNull( command.getRotatingMachineId(), "AssignGeneratingUnitToRotatingMachineCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignGeneratingUnitToRotatingMachineCommand assignment should not be null" );
	}

	/**
	 * handles unassign GeneratingUnit validation for a RotatingMachine
	 * 
	 * @param	command UnAssignGeneratingUnitFromRotatingMachineCommand
	 */	
	public void validate( UnAssignGeneratingUnitFromRotatingMachineCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignGeneratingUnitFromRotatingMachineCommand should not be null" );
		Assert.notNull( command.getRotatingMachineId(), "UnAssignGeneratingUnitFromRotatingMachineCommand identifier should not be null" );
	}


}
