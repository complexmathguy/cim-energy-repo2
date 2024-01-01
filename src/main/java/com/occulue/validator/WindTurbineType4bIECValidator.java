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

public class WindTurbineType4bIECValidator {
		
	/**
	 * default constructor
	 */
	protected WindTurbineType4bIECValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public WindTurbineType4bIECValidator getInstance() {
		return new WindTurbineType4bIECValidator();
	}
		
	/**
	 * handles creation validation for a WindTurbineType4bIEC
	 */
	public void validate( CreateWindTurbineType4bIECCommand windTurbineType4bIEC )throws Exception {
		Assert.notNull( windTurbineType4bIEC, "CreateWindTurbineType4bIECCommand should not be null" );
//		Assert.isNull( windTurbineType4bIEC.getWindTurbineType4bIECId(), "CreateWindTurbineType4bIECCommand identifier should be null" );
	}

	/**
	 * handles update validation for a WindTurbineType4bIEC
	 */
	public void validate( UpdateWindTurbineType4bIECCommand windTurbineType4bIEC ) throws Exception {
		Assert.notNull( windTurbineType4bIEC, "UpdateWindTurbineType4bIECCommand should not be null" );
		Assert.notNull( windTurbineType4bIEC.getWindTurbineType4bIECId(), "UpdateWindTurbineType4bIECCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a WindTurbineType4bIEC
	 */
    public void validate( DeleteWindTurbineType4bIECCommand windTurbineType4bIEC ) throws Exception {
		Assert.notNull( windTurbineType4bIEC, "{commandAlias} should not be null" );
		Assert.notNull( windTurbineType4bIEC.getWindTurbineType4bIECId(), "DeleteWindTurbineType4bIECCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a WindTurbineType4bIEC
	 */
	public void validate( WindTurbineType4bIECFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "WindTurbineType4bIECFetchOneSummary should not be null" );
		Assert.notNull( summary.getWindTurbineType4bIECId(), "WindTurbineType4bIECFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign WindMechIEC validation for a WindTurbineType4bIEC
	 * 
	 * @param	command AssignWindMechIECToWindTurbineType4bIECCommand
	 */	
	public void validate( AssignWindMechIECToWindTurbineType4bIECCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindMechIECToWindTurbineType4bIECCommand should not be null" );
		Assert.notNull( command.getWindTurbineType4bIECId(), "AssignWindMechIECToWindTurbineType4bIECCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindMechIECToWindTurbineType4bIECCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindMechIEC validation for a WindTurbineType4bIEC
	 * 
	 * @param	command UnAssignWindMechIECFromWindTurbineType4bIECCommand
	 */	
	public void validate( UnAssignWindMechIECFromWindTurbineType4bIECCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindMechIECFromWindTurbineType4bIECCommand should not be null" );
		Assert.notNull( command.getWindTurbineType4bIECId(), "UnAssignWindMechIECFromWindTurbineType4bIECCommand identifier should not be null" );
	}
	/**
	 * handles assign WindContPType4bIEC validation for a WindTurbineType4bIEC
	 * 
	 * @param	command AssignWindContPType4bIECToWindTurbineType4bIECCommand
	 */	
	public void validate( AssignWindContPType4bIECToWindTurbineType4bIECCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindContPType4bIECToWindTurbineType4bIECCommand should not be null" );
		Assert.notNull( command.getWindTurbineType4bIECId(), "AssignWindContPType4bIECToWindTurbineType4bIECCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindContPType4bIECToWindTurbineType4bIECCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindContPType4bIEC validation for a WindTurbineType4bIEC
	 * 
	 * @param	command UnAssignWindContPType4bIECFromWindTurbineType4bIECCommand
	 */	
	public void validate( UnAssignWindContPType4bIECFromWindTurbineType4bIECCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindContPType4bIECFromWindTurbineType4bIECCommand should not be null" );
		Assert.notNull( command.getWindTurbineType4bIECId(), "UnAssignWindContPType4bIECFromWindTurbineType4bIECCommand identifier should not be null" );
	}


}
