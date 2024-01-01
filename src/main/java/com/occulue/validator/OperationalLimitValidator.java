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

public class OperationalLimitValidator {
		
	/**
	 * default constructor
	 */
	protected OperationalLimitValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public OperationalLimitValidator getInstance() {
		return new OperationalLimitValidator();
	}
		
	/**
	 * handles creation validation for a OperationalLimit
	 */
	public void validate( CreateOperationalLimitCommand operationalLimit )throws Exception {
		Assert.notNull( operationalLimit, "CreateOperationalLimitCommand should not be null" );
//		Assert.isNull( operationalLimit.getOperationalLimitId(), "CreateOperationalLimitCommand identifier should be null" );
	}

	/**
	 * handles update validation for a OperationalLimit
	 */
	public void validate( UpdateOperationalLimitCommand operationalLimit ) throws Exception {
		Assert.notNull( operationalLimit, "UpdateOperationalLimitCommand should not be null" );
		Assert.notNull( operationalLimit.getOperationalLimitId(), "UpdateOperationalLimitCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a OperationalLimit
	 */
    public void validate( DeleteOperationalLimitCommand operationalLimit ) throws Exception {
		Assert.notNull( operationalLimit, "{commandAlias} should not be null" );
		Assert.notNull( operationalLimit.getOperationalLimitId(), "DeleteOperationalLimitCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a OperationalLimit
	 */
	public void validate( OperationalLimitFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "OperationalLimitFetchOneSummary should not be null" );
		Assert.notNull( summary.getOperationalLimitId(), "OperationalLimitFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign OperationalLimitType validation for a OperationalLimit
	 * 
	 * @param	command AssignOperationalLimitTypeToOperationalLimitCommand
	 */	
	public void validate( AssignOperationalLimitTypeToOperationalLimitCommand command ) throws Exception {
		Assert.notNull( command, "AssignOperationalLimitTypeToOperationalLimitCommand should not be null" );
		Assert.notNull( command.getOperationalLimitId(), "AssignOperationalLimitTypeToOperationalLimitCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignOperationalLimitTypeToOperationalLimitCommand assignment should not be null" );
	}

	/**
	 * handles unassign OperationalLimitType validation for a OperationalLimit
	 * 
	 * @param	command UnAssignOperationalLimitTypeFromOperationalLimitCommand
	 */	
	public void validate( UnAssignOperationalLimitTypeFromOperationalLimitCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignOperationalLimitTypeFromOperationalLimitCommand should not be null" );
		Assert.notNull( command.getOperationalLimitId(), "UnAssignOperationalLimitTypeFromOperationalLimitCommand identifier should not be null" );
	}

	/**
	 * handles add to OperationalLimitSet validation for a OperationalLimit
	 * 
	 * @param	command AssignOperationalLimitSetToOperationalLimitCommand
	 */	
	public void validate( AssignOperationalLimitSetToOperationalLimitCommand command ) throws Exception {
		Assert.notNull( command, "AssignOperationalLimitSetToOperationalLimitCommand should not be null" );
		Assert.notNull( command.getOperationalLimitId(), "AssignOperationalLimitSetToOperationalLimitCommand identifier should not be null" );
		Assert.notNull( command.getAddTo(), "AssignOperationalLimitSetToOperationalLimitCommand addTo attribute should not be null" );
	}

	/**
	 * handles remove from OperationalLimitSet validation for a OperationalLimit
	 * 
	 * @param	command RemoveOperationalLimitSetFromOperationalLimitCommand
	 */	
	public void validate( RemoveOperationalLimitSetFromOperationalLimitCommand command ) throws Exception {
		Assert.notNull( command, "RemoveOperationalLimitSetFromOperationalLimitCommand should not be null" );
		Assert.notNull( command.getOperationalLimitId(), "RemoveOperationalLimitSetFromOperationalLimitCommand identifier should not be null" );
		Assert.notNull( command.getRemoveFrom(), "RemoveOperationalLimitSetFromOperationalLimitCommand removeFrom attribute should not be null" );
		Assert.notNull( command.getRemoveFrom().getOperationalLimitSetId(), "RemoveOperationalLimitSetFromOperationalLimitCommand removeFrom attribubte identifier should not be null" );
	}
	

}
