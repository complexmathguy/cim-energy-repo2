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

public class OperationalLimitSetValidator {
		
	/**
	 * default constructor
	 */
	protected OperationalLimitSetValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public OperationalLimitSetValidator getInstance() {
		return new OperationalLimitSetValidator();
	}
		
	/**
	 * handles creation validation for a OperationalLimitSet
	 */
	public void validate( CreateOperationalLimitSetCommand operationalLimitSet )throws Exception {
		Assert.notNull( operationalLimitSet, "CreateOperationalLimitSetCommand should not be null" );
//		Assert.isNull( operationalLimitSet.getOperationalLimitSetId(), "CreateOperationalLimitSetCommand identifier should be null" );
	}

	/**
	 * handles update validation for a OperationalLimitSet
	 */
	public void validate( UpdateOperationalLimitSetCommand operationalLimitSet ) throws Exception {
		Assert.notNull( operationalLimitSet, "UpdateOperationalLimitSetCommand should not be null" );
		Assert.notNull( operationalLimitSet.getOperationalLimitSetId(), "UpdateOperationalLimitSetCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a OperationalLimitSet
	 */
    public void validate( DeleteOperationalLimitSetCommand operationalLimitSet ) throws Exception {
		Assert.notNull( operationalLimitSet, "{commandAlias} should not be null" );
		Assert.notNull( operationalLimitSet.getOperationalLimitSetId(), "DeleteOperationalLimitSetCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a OperationalLimitSet
	 */
	public void validate( OperationalLimitSetFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "OperationalLimitSetFetchOneSummary should not be null" );
		Assert.notNull( summary.getOperationalLimitSetId(), "OperationalLimitSetFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Equipment validation for a OperationalLimitSet
	 * 
	 * @param	command AssignEquipmentToOperationalLimitSetCommand
	 */	
	public void validate( AssignEquipmentToOperationalLimitSetCommand command ) throws Exception {
		Assert.notNull( command, "AssignEquipmentToOperationalLimitSetCommand should not be null" );
		Assert.notNull( command.getOperationalLimitSetId(), "AssignEquipmentToOperationalLimitSetCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignEquipmentToOperationalLimitSetCommand assignment should not be null" );
	}

	/**
	 * handles unassign Equipment validation for a OperationalLimitSet
	 * 
	 * @param	command UnAssignEquipmentFromOperationalLimitSetCommand
	 */	
	public void validate( UnAssignEquipmentFromOperationalLimitSetCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignEquipmentFromOperationalLimitSetCommand should not be null" );
		Assert.notNull( command.getOperationalLimitSetId(), "UnAssignEquipmentFromOperationalLimitSetCommand identifier should not be null" );
	}
	/**
	 * handles assign Terminal validation for a OperationalLimitSet
	 * 
	 * @param	command AssignTerminalToOperationalLimitSetCommand
	 */	
	public void validate( AssignTerminalToOperationalLimitSetCommand command ) throws Exception {
		Assert.notNull( command, "AssignTerminalToOperationalLimitSetCommand should not be null" );
		Assert.notNull( command.getOperationalLimitSetId(), "AssignTerminalToOperationalLimitSetCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTerminalToOperationalLimitSetCommand assignment should not be null" );
	}

	/**
	 * handles unassign Terminal validation for a OperationalLimitSet
	 * 
	 * @param	command UnAssignTerminalFromOperationalLimitSetCommand
	 */	
	public void validate( UnAssignTerminalFromOperationalLimitSetCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTerminalFromOperationalLimitSetCommand should not be null" );
		Assert.notNull( command.getOperationalLimitSetId(), "UnAssignTerminalFromOperationalLimitSetCommand identifier should not be null" );
	}


}
