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

public class ACDCConverterDCTerminalValidator {
		
	/**
	 * default constructor
	 */
	protected ACDCConverterDCTerminalValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public ACDCConverterDCTerminalValidator getInstance() {
		return new ACDCConverterDCTerminalValidator();
	}
		
	/**
	 * handles creation validation for a ACDCConverterDCTerminal
	 */
	public void validate( CreateACDCConverterDCTerminalCommand aCDCConverterDCTerminal )throws Exception {
		Assert.notNull( aCDCConverterDCTerminal, "CreateACDCConverterDCTerminalCommand should not be null" );
//		Assert.isNull( aCDCConverterDCTerminal.getACDCConverterDCTerminalId(), "CreateACDCConverterDCTerminalCommand identifier should be null" );
	}

	/**
	 * handles update validation for a ACDCConverterDCTerminal
	 */
	public void validate( UpdateACDCConverterDCTerminalCommand aCDCConverterDCTerminal ) throws Exception {
		Assert.notNull( aCDCConverterDCTerminal, "UpdateACDCConverterDCTerminalCommand should not be null" );
		Assert.notNull( aCDCConverterDCTerminal.getACDCConverterDCTerminalId(), "UpdateACDCConverterDCTerminalCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a ACDCConverterDCTerminal
	 */
    public void validate( DeleteACDCConverterDCTerminalCommand aCDCConverterDCTerminal ) throws Exception {
		Assert.notNull( aCDCConverterDCTerminal, "{commandAlias} should not be null" );
		Assert.notNull( aCDCConverterDCTerminal.getACDCConverterDCTerminalId(), "DeleteACDCConverterDCTerminalCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a ACDCConverterDCTerminal
	 */
	public void validate( ACDCConverterDCTerminalFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "ACDCConverterDCTerminalFetchOneSummary should not be null" );
		Assert.notNull( summary.getACDCConverterDCTerminalId(), "ACDCConverterDCTerminalFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign DCConductingEquipment validation for a ACDCConverterDCTerminal
	 * 
	 * @param	command AssignDCConductingEquipmentToACDCConverterDCTerminalCommand
	 */	
	public void validate( AssignDCConductingEquipmentToACDCConverterDCTerminalCommand command ) throws Exception {
		Assert.notNull( command, "AssignDCConductingEquipmentToACDCConverterDCTerminalCommand should not be null" );
		Assert.notNull( command.getACDCConverterDCTerminalId(), "AssignDCConductingEquipmentToACDCConverterDCTerminalCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDCConductingEquipmentToACDCConverterDCTerminalCommand assignment should not be null" );
	}

	/**
	 * handles unassign DCConductingEquipment validation for a ACDCConverterDCTerminal
	 * 
	 * @param	command UnAssignDCConductingEquipmentFromACDCConverterDCTerminalCommand
	 */	
	public void validate( UnAssignDCConductingEquipmentFromACDCConverterDCTerminalCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDCConductingEquipmentFromACDCConverterDCTerminalCommand should not be null" );
		Assert.notNull( command.getACDCConverterDCTerminalId(), "UnAssignDCConductingEquipmentFromACDCConverterDCTerminalCommand identifier should not be null" );
	}


}
