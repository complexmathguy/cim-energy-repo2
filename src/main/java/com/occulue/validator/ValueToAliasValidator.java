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

public class ValueToAliasValidator {
		
	/**
	 * default constructor
	 */
	protected ValueToAliasValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public ValueToAliasValidator getInstance() {
		return new ValueToAliasValidator();
	}
		
	/**
	 * handles creation validation for a ValueToAlias
	 */
	public void validate( CreateValueToAliasCommand valueToAlias )throws Exception {
		Assert.notNull( valueToAlias, "CreateValueToAliasCommand should not be null" );
//		Assert.isNull( valueToAlias.getValueToAliasId(), "CreateValueToAliasCommand identifier should be null" );
	}

	/**
	 * handles update validation for a ValueToAlias
	 */
	public void validate( UpdateValueToAliasCommand valueToAlias ) throws Exception {
		Assert.notNull( valueToAlias, "UpdateValueToAliasCommand should not be null" );
		Assert.notNull( valueToAlias.getValueToAliasId(), "UpdateValueToAliasCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a ValueToAlias
	 */
    public void validate( DeleteValueToAliasCommand valueToAlias ) throws Exception {
		Assert.notNull( valueToAlias, "{commandAlias} should not be null" );
		Assert.notNull( valueToAlias.getValueToAliasId(), "DeleteValueToAliasCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a ValueToAlias
	 */
	public void validate( ValueToAliasFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "ValueToAliasFetchOneSummary should not be null" );
		Assert.notNull( summary.getValueToAliasId(), "ValueToAliasFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Value validation for a ValueToAlias
	 * 
	 * @param	command AssignValueToValueToAliasCommand
	 */	
	public void validate( AssignValueToValueToAliasCommand command ) throws Exception {
		Assert.notNull( command, "AssignValueToValueToAliasCommand should not be null" );
		Assert.notNull( command.getValueToAliasId(), "AssignValueToValueToAliasCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignValueToValueToAliasCommand assignment should not be null" );
	}

	/**
	 * handles unassign Value validation for a ValueToAlias
	 * 
	 * @param	command UnAssignValueFromValueToAliasCommand
	 */	
	public void validate( UnAssignValueFromValueToAliasCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignValueFromValueToAliasCommand should not be null" );
		Assert.notNull( command.getValueToAliasId(), "UnAssignValueFromValueToAliasCommand identifier should not be null" );
	}

	/**
	 * handles add to ValueAliasSet validation for a ValueToAlias
	 * 
	 * @param	command AssignValueAliasSetToValueToAliasCommand
	 */	
	public void validate( AssignValueAliasSetToValueToAliasCommand command ) throws Exception {
		Assert.notNull( command, "AssignValueAliasSetToValueToAliasCommand should not be null" );
		Assert.notNull( command.getValueToAliasId(), "AssignValueAliasSetToValueToAliasCommand identifier should not be null" );
		Assert.notNull( command.getAddTo(), "AssignValueAliasSetToValueToAliasCommand addTo attribute should not be null" );
	}

	/**
	 * handles remove from ValueAliasSet validation for a ValueToAlias
	 * 
	 * @param	command RemoveValueAliasSetFromValueToAliasCommand
	 */	
	public void validate( RemoveValueAliasSetFromValueToAliasCommand command ) throws Exception {
		Assert.notNull( command, "RemoveValueAliasSetFromValueToAliasCommand should not be null" );
		Assert.notNull( command.getValueToAliasId(), "RemoveValueAliasSetFromValueToAliasCommand identifier should not be null" );
		Assert.notNull( command.getRemoveFrom(), "RemoveValueAliasSetFromValueToAliasCommand removeFrom attribute should not be null" );
		Assert.notNull( command.getRemoveFrom().getValueAliasSetId(), "RemoveValueAliasSetFromValueToAliasCommand removeFrom attribubte identifier should not be null" );
	}
	

}
