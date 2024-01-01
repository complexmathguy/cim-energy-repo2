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

public class WindPlantIECValidator {
		
	/**
	 * default constructor
	 */
	protected WindPlantIECValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public WindPlantIECValidator getInstance() {
		return new WindPlantIECValidator();
	}
		
	/**
	 * handles creation validation for a WindPlantIEC
	 */
	public void validate( CreateWindPlantIECCommand windPlantIEC )throws Exception {
		Assert.notNull( windPlantIEC, "CreateWindPlantIECCommand should not be null" );
//		Assert.isNull( windPlantIEC.getWindPlantIECId(), "CreateWindPlantIECCommand identifier should be null" );
	}

	/**
	 * handles update validation for a WindPlantIEC
	 */
	public void validate( UpdateWindPlantIECCommand windPlantIEC ) throws Exception {
		Assert.notNull( windPlantIEC, "UpdateWindPlantIECCommand should not be null" );
		Assert.notNull( windPlantIEC.getWindPlantIECId(), "UpdateWindPlantIECCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a WindPlantIEC
	 */
    public void validate( DeleteWindPlantIECCommand windPlantIEC ) throws Exception {
		Assert.notNull( windPlantIEC, "{commandAlias} should not be null" );
		Assert.notNull( windPlantIEC.getWindPlantIECId(), "DeleteWindPlantIECCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a WindPlantIEC
	 */
	public void validate( WindPlantIECFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "WindPlantIECFetchOneSummary should not be null" );
		Assert.notNull( summary.getWindPlantIECId(), "WindPlantIECFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign WindPlantFreqPcontrolIEC validation for a WindPlantIEC
	 * 
	 * @param	command AssignWindPlantFreqPcontrolIECToWindPlantIECCommand
	 */	
	public void validate( AssignWindPlantFreqPcontrolIECToWindPlantIECCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindPlantFreqPcontrolIECToWindPlantIECCommand should not be null" );
		Assert.notNull( command.getWindPlantIECId(), "AssignWindPlantFreqPcontrolIECToWindPlantIECCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindPlantFreqPcontrolIECToWindPlantIECCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindPlantFreqPcontrolIEC validation for a WindPlantIEC
	 * 
	 * @param	command UnAssignWindPlantFreqPcontrolIECFromWindPlantIECCommand
	 */	
	public void validate( UnAssignWindPlantFreqPcontrolIECFromWindPlantIECCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindPlantFreqPcontrolIECFromWindPlantIECCommand should not be null" );
		Assert.notNull( command.getWindPlantIECId(), "UnAssignWindPlantFreqPcontrolIECFromWindPlantIECCommand identifier should not be null" );
	}
	/**
	 * handles assign WindPlantReactiveControlIEC validation for a WindPlantIEC
	 * 
	 * @param	command AssignWindPlantReactiveControlIECToWindPlantIECCommand
	 */	
	public void validate( AssignWindPlantReactiveControlIECToWindPlantIECCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindPlantReactiveControlIECToWindPlantIECCommand should not be null" );
		Assert.notNull( command.getWindPlantIECId(), "AssignWindPlantReactiveControlIECToWindPlantIECCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindPlantReactiveControlIECToWindPlantIECCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindPlantReactiveControlIEC validation for a WindPlantIEC
	 * 
	 * @param	command UnAssignWindPlantReactiveControlIECFromWindPlantIECCommand
	 */	
	public void validate( UnAssignWindPlantReactiveControlIECFromWindPlantIECCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindPlantReactiveControlIECFromWindPlantIECCommand should not be null" );
		Assert.notNull( command.getWindPlantIECId(), "UnAssignWindPlantReactiveControlIECFromWindPlantIECCommand identifier should not be null" );
	}


}
