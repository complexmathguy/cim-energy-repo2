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

public class HydroPumpValidator {
		
	/**
	 * default constructor
	 */
	protected HydroPumpValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public HydroPumpValidator getInstance() {
		return new HydroPumpValidator();
	}
		
	/**
	 * handles creation validation for a HydroPump
	 */
	public void validate( CreateHydroPumpCommand hydroPump )throws Exception {
		Assert.notNull( hydroPump, "CreateHydroPumpCommand should not be null" );
//		Assert.isNull( hydroPump.getHydroPumpId(), "CreateHydroPumpCommand identifier should be null" );
	}

	/**
	 * handles update validation for a HydroPump
	 */
	public void validate( UpdateHydroPumpCommand hydroPump ) throws Exception {
		Assert.notNull( hydroPump, "UpdateHydroPumpCommand should not be null" );
		Assert.notNull( hydroPump.getHydroPumpId(), "UpdateHydroPumpCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a HydroPump
	 */
    public void validate( DeleteHydroPumpCommand hydroPump ) throws Exception {
		Assert.notNull( hydroPump, "{commandAlias} should not be null" );
		Assert.notNull( hydroPump.getHydroPumpId(), "DeleteHydroPumpCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a HydroPump
	 */
	public void validate( HydroPumpFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "HydroPumpFetchOneSummary should not be null" );
		Assert.notNull( summary.getHydroPumpId(), "HydroPumpFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign RotatingMachine validation for a HydroPump
	 * 
	 * @param	command AssignRotatingMachineToHydroPumpCommand
	 */	
	public void validate( AssignRotatingMachineToHydroPumpCommand command ) throws Exception {
		Assert.notNull( command, "AssignRotatingMachineToHydroPumpCommand should not be null" );
		Assert.notNull( command.getHydroPumpId(), "AssignRotatingMachineToHydroPumpCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignRotatingMachineToHydroPumpCommand assignment should not be null" );
	}

	/**
	 * handles unassign RotatingMachine validation for a HydroPump
	 * 
	 * @param	command UnAssignRotatingMachineFromHydroPumpCommand
	 */	
	public void validate( UnAssignRotatingMachineFromHydroPumpCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignRotatingMachineFromHydroPumpCommand should not be null" );
		Assert.notNull( command.getHydroPumpId(), "UnAssignRotatingMachineFromHydroPumpCommand identifier should not be null" );
	}
	/**
	 * handles assign HydroPowerPlant validation for a HydroPump
	 * 
	 * @param	command AssignHydroPowerPlantToHydroPumpCommand
	 */	
	public void validate( AssignHydroPowerPlantToHydroPumpCommand command ) throws Exception {
		Assert.notNull( command, "AssignHydroPowerPlantToHydroPumpCommand should not be null" );
		Assert.notNull( command.getHydroPumpId(), "AssignHydroPowerPlantToHydroPumpCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignHydroPowerPlantToHydroPumpCommand assignment should not be null" );
	}

	/**
	 * handles unassign HydroPowerPlant validation for a HydroPump
	 * 
	 * @param	command UnAssignHydroPowerPlantFromHydroPumpCommand
	 */	
	public void validate( UnAssignHydroPowerPlantFromHydroPumpCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignHydroPowerPlantFromHydroPumpCommand should not be null" );
		Assert.notNull( command.getHydroPumpId(), "UnAssignHydroPowerPlantFromHydroPumpCommand identifier should not be null" );
	}


}
