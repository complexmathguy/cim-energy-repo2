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

public class ControlAreaGeneratingUnitValidator {
		
	/**
	 * default constructor
	 */
	protected ControlAreaGeneratingUnitValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public ControlAreaGeneratingUnitValidator getInstance() {
		return new ControlAreaGeneratingUnitValidator();
	}
		
	/**
	 * handles creation validation for a ControlAreaGeneratingUnit
	 */
	public void validate( CreateControlAreaGeneratingUnitCommand controlAreaGeneratingUnit )throws Exception {
		Assert.notNull( controlAreaGeneratingUnit, "CreateControlAreaGeneratingUnitCommand should not be null" );
//		Assert.isNull( controlAreaGeneratingUnit.getControlAreaGeneratingUnitId(), "CreateControlAreaGeneratingUnitCommand identifier should be null" );
	}

	/**
	 * handles update validation for a ControlAreaGeneratingUnit
	 */
	public void validate( UpdateControlAreaGeneratingUnitCommand controlAreaGeneratingUnit ) throws Exception {
		Assert.notNull( controlAreaGeneratingUnit, "UpdateControlAreaGeneratingUnitCommand should not be null" );
		Assert.notNull( controlAreaGeneratingUnit.getControlAreaGeneratingUnitId(), "UpdateControlAreaGeneratingUnitCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a ControlAreaGeneratingUnit
	 */
    public void validate( DeleteControlAreaGeneratingUnitCommand controlAreaGeneratingUnit ) throws Exception {
		Assert.notNull( controlAreaGeneratingUnit, "{commandAlias} should not be null" );
		Assert.notNull( controlAreaGeneratingUnit.getControlAreaGeneratingUnitId(), "DeleteControlAreaGeneratingUnitCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a ControlAreaGeneratingUnit
	 */
	public void validate( ControlAreaGeneratingUnitFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "ControlAreaGeneratingUnitFetchOneSummary should not be null" );
		Assert.notNull( summary.getControlAreaGeneratingUnitId(), "ControlAreaGeneratingUnitFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign ControlArea validation for a ControlAreaGeneratingUnit
	 * 
	 * @param	command AssignControlAreaToControlAreaGeneratingUnitCommand
	 */	
	public void validate( AssignControlAreaToControlAreaGeneratingUnitCommand command ) throws Exception {
		Assert.notNull( command, "AssignControlAreaToControlAreaGeneratingUnitCommand should not be null" );
		Assert.notNull( command.getControlAreaGeneratingUnitId(), "AssignControlAreaToControlAreaGeneratingUnitCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignControlAreaToControlAreaGeneratingUnitCommand assignment should not be null" );
	}

	/**
	 * handles unassign ControlArea validation for a ControlAreaGeneratingUnit
	 * 
	 * @param	command UnAssignControlAreaFromControlAreaGeneratingUnitCommand
	 */	
	public void validate( UnAssignControlAreaFromControlAreaGeneratingUnitCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignControlAreaFromControlAreaGeneratingUnitCommand should not be null" );
		Assert.notNull( command.getControlAreaGeneratingUnitId(), "UnAssignControlAreaFromControlAreaGeneratingUnitCommand identifier should not be null" );
	}
	/**
	 * handles assign GeneratingUnit validation for a ControlAreaGeneratingUnit
	 * 
	 * @param	command AssignGeneratingUnitToControlAreaGeneratingUnitCommand
	 */	
	public void validate( AssignGeneratingUnitToControlAreaGeneratingUnitCommand command ) throws Exception {
		Assert.notNull( command, "AssignGeneratingUnitToControlAreaGeneratingUnitCommand should not be null" );
		Assert.notNull( command.getControlAreaGeneratingUnitId(), "AssignGeneratingUnitToControlAreaGeneratingUnitCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignGeneratingUnitToControlAreaGeneratingUnitCommand assignment should not be null" );
	}

	/**
	 * handles unassign GeneratingUnit validation for a ControlAreaGeneratingUnit
	 * 
	 * @param	command UnAssignGeneratingUnitFromControlAreaGeneratingUnitCommand
	 */	
	public void validate( UnAssignGeneratingUnitFromControlAreaGeneratingUnitCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignGeneratingUnitFromControlAreaGeneratingUnitCommand should not be null" );
		Assert.notNull( command.getControlAreaGeneratingUnitId(), "UnAssignGeneratingUnitFromControlAreaGeneratingUnitCommand identifier should not be null" );
	}


}
