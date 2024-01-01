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

public class DCConverterUnitValidator {
		
	/**
	 * default constructor
	 */
	protected DCConverterUnitValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public DCConverterUnitValidator getInstance() {
		return new DCConverterUnitValidator();
	}
		
	/**
	 * handles creation validation for a DCConverterUnit
	 */
	public void validate( CreateDCConverterUnitCommand dCConverterUnit )throws Exception {
		Assert.notNull( dCConverterUnit, "CreateDCConverterUnitCommand should not be null" );
//		Assert.isNull( dCConverterUnit.getDCConverterUnitId(), "CreateDCConverterUnitCommand identifier should be null" );
	}

	/**
	 * handles update validation for a DCConverterUnit
	 */
	public void validate( UpdateDCConverterUnitCommand dCConverterUnit ) throws Exception {
		Assert.notNull( dCConverterUnit, "UpdateDCConverterUnitCommand should not be null" );
		Assert.notNull( dCConverterUnit.getDCConverterUnitId(), "UpdateDCConverterUnitCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a DCConverterUnit
	 */
    public void validate( DeleteDCConverterUnitCommand dCConverterUnit ) throws Exception {
		Assert.notNull( dCConverterUnit, "{commandAlias} should not be null" );
		Assert.notNull( dCConverterUnit.getDCConverterUnitId(), "DeleteDCConverterUnitCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a DCConverterUnit
	 */
	public void validate( DCConverterUnitFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "DCConverterUnitFetchOneSummary should not be null" );
		Assert.notNull( summary.getDCConverterUnitId(), "DCConverterUnitFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Substation validation for a DCConverterUnit
	 * 
	 * @param	command AssignSubstationToDCConverterUnitCommand
	 */	
	public void validate( AssignSubstationToDCConverterUnitCommand command ) throws Exception {
		Assert.notNull( command, "AssignSubstationToDCConverterUnitCommand should not be null" );
		Assert.notNull( command.getDCConverterUnitId(), "AssignSubstationToDCConverterUnitCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignSubstationToDCConverterUnitCommand assignment should not be null" );
	}

	/**
	 * handles unassign Substation validation for a DCConverterUnit
	 * 
	 * @param	command UnAssignSubstationFromDCConverterUnitCommand
	 */	
	public void validate( UnAssignSubstationFromDCConverterUnitCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignSubstationFromDCConverterUnitCommand should not be null" );
		Assert.notNull( command.getDCConverterUnitId(), "UnAssignSubstationFromDCConverterUnitCommand identifier should not be null" );
	}


}
