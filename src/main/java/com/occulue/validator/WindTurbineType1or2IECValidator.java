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

public class WindTurbineType1or2IECValidator {
		
	/**
	 * default constructor
	 */
	protected WindTurbineType1or2IECValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public WindTurbineType1or2IECValidator getInstance() {
		return new WindTurbineType1or2IECValidator();
	}
		
	/**
	 * handles creation validation for a WindTurbineType1or2IEC
	 */
	public void validate( CreateWindTurbineType1or2IECCommand windTurbineType1or2IEC )throws Exception {
		Assert.notNull( windTurbineType1or2IEC, "CreateWindTurbineType1or2IECCommand should not be null" );
//		Assert.isNull( windTurbineType1or2IEC.getWindTurbineType1or2IECId(), "CreateWindTurbineType1or2IECCommand identifier should be null" );
	}

	/**
	 * handles update validation for a WindTurbineType1or2IEC
	 */
	public void validate( UpdateWindTurbineType1or2IECCommand windTurbineType1or2IEC ) throws Exception {
		Assert.notNull( windTurbineType1or2IEC, "UpdateWindTurbineType1or2IECCommand should not be null" );
		Assert.notNull( windTurbineType1or2IEC.getWindTurbineType1or2IECId(), "UpdateWindTurbineType1or2IECCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a WindTurbineType1or2IEC
	 */
    public void validate( DeleteWindTurbineType1or2IECCommand windTurbineType1or2IEC ) throws Exception {
		Assert.notNull( windTurbineType1or2IEC, "{commandAlias} should not be null" );
		Assert.notNull( windTurbineType1or2IEC.getWindTurbineType1or2IECId(), "DeleteWindTurbineType1or2IECCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a WindTurbineType1or2IEC
	 */
	public void validate( WindTurbineType1or2IECFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "WindTurbineType1or2IECFetchOneSummary should not be null" );
		Assert.notNull( summary.getWindTurbineType1or2IECId(), "WindTurbineType1or2IECFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign WindProtectionIEC validation for a WindTurbineType1or2IEC
	 * 
	 * @param	command AssignWindProtectionIECToWindTurbineType1or2IECCommand
	 */	
	public void validate( AssignWindProtectionIECToWindTurbineType1or2IECCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindProtectionIECToWindTurbineType1or2IECCommand should not be null" );
		Assert.notNull( command.getWindTurbineType1or2IECId(), "AssignWindProtectionIECToWindTurbineType1or2IECCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindProtectionIECToWindTurbineType1or2IECCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindProtectionIEC validation for a WindTurbineType1or2IEC
	 * 
	 * @param	command UnAssignWindProtectionIECFromWindTurbineType1or2IECCommand
	 */	
	public void validate( UnAssignWindProtectionIECFromWindTurbineType1or2IECCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindProtectionIECFromWindTurbineType1or2IECCommand should not be null" );
		Assert.notNull( command.getWindTurbineType1or2IECId(), "UnAssignWindProtectionIECFromWindTurbineType1or2IECCommand identifier should not be null" );
	}
	/**
	 * handles assign WindMechIEC validation for a WindTurbineType1or2IEC
	 * 
	 * @param	command AssignWindMechIECToWindTurbineType1or2IECCommand
	 */	
	public void validate( AssignWindMechIECToWindTurbineType1or2IECCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindMechIECToWindTurbineType1or2IECCommand should not be null" );
		Assert.notNull( command.getWindTurbineType1or2IECId(), "AssignWindMechIECToWindTurbineType1or2IECCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindMechIECToWindTurbineType1or2IECCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindMechIEC validation for a WindTurbineType1or2IEC
	 * 
	 * @param	command UnAssignWindMechIECFromWindTurbineType1or2IECCommand
	 */	
	public void validate( UnAssignWindMechIECFromWindTurbineType1or2IECCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindMechIECFromWindTurbineType1or2IECCommand should not be null" );
		Assert.notNull( command.getWindTurbineType1or2IECId(), "UnAssignWindMechIECFromWindTurbineType1or2IECCommand identifier should not be null" );
	}


}
