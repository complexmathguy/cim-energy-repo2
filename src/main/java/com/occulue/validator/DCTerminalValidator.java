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

public class DCTerminalValidator {
		
	/**
	 * default constructor
	 */
	protected DCTerminalValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public DCTerminalValidator getInstance() {
		return new DCTerminalValidator();
	}
		
	/**
	 * handles creation validation for a DCTerminal
	 */
	public void validate( CreateDCTerminalCommand dCTerminal )throws Exception {
		Assert.notNull( dCTerminal, "CreateDCTerminalCommand should not be null" );
//		Assert.isNull( dCTerminal.getDCTerminalId(), "CreateDCTerminalCommand identifier should be null" );
	}

	/**
	 * handles update validation for a DCTerminal
	 */
	public void validate( UpdateDCTerminalCommand dCTerminal ) throws Exception {
		Assert.notNull( dCTerminal, "UpdateDCTerminalCommand should not be null" );
		Assert.notNull( dCTerminal.getDCTerminalId(), "UpdateDCTerminalCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a DCTerminal
	 */
    public void validate( DeleteDCTerminalCommand dCTerminal ) throws Exception {
		Assert.notNull( dCTerminal, "{commandAlias} should not be null" );
		Assert.notNull( dCTerminal.getDCTerminalId(), "DeleteDCTerminalCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a DCTerminal
	 */
	public void validate( DCTerminalFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "DCTerminalFetchOneSummary should not be null" );
		Assert.notNull( summary.getDCTerminalId(), "DCTerminalFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign DCConductingEquipment validation for a DCTerminal
	 * 
	 * @param	command AssignDCConductingEquipmentToDCTerminalCommand
	 */	
	public void validate( AssignDCConductingEquipmentToDCTerminalCommand command ) throws Exception {
		Assert.notNull( command, "AssignDCConductingEquipmentToDCTerminalCommand should not be null" );
		Assert.notNull( command.getDCTerminalId(), "AssignDCConductingEquipmentToDCTerminalCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDCConductingEquipmentToDCTerminalCommand assignment should not be null" );
	}

	/**
	 * handles unassign DCConductingEquipment validation for a DCTerminal
	 * 
	 * @param	command UnAssignDCConductingEquipmentFromDCTerminalCommand
	 */	
	public void validate( UnAssignDCConductingEquipmentFromDCTerminalCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDCConductingEquipmentFromDCTerminalCommand should not be null" );
		Assert.notNull( command.getDCTerminalId(), "UnAssignDCConductingEquipmentFromDCTerminalCommand identifier should not be null" );
	}


}
