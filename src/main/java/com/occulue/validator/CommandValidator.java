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

public class CommandValidator {
		
	/**
	 * default constructor
	 */
	protected CommandValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public CommandValidator getInstance() {
		return new CommandValidator();
	}
		
	/**
	 * handles creation validation for a Command
	 */
	public void validate( CreateCommandCommand command )throws Exception {
		Assert.notNull( command, "CreateCommandCommand should not be null" );
//		Assert.isNull( command.getCommandId(), "CreateCommandCommand identifier should be null" );
	}

	/**
	 * handles update validation for a Command
	 */
	public void validate( UpdateCommandCommand command ) throws Exception {
		Assert.notNull( command, "UpdateCommandCommand should not be null" );
		Assert.notNull( command.getCommandId(), "UpdateCommandCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a Command
	 */
    public void validate( DeleteCommandCommand command ) throws Exception {
		Assert.notNull( command, "{commandAlias} should not be null" );
		Assert.notNull( command.getCommandId(), "DeleteCommandCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a Command
	 */
	public void validate( CommandFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "CommandFetchOneSummary should not be null" );
		Assert.notNull( summary.getCommandId(), "CommandFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign NormalValue validation for a Command
	 * 
	 * @param	command AssignNormalValueToCommandCommand
	 */	
	public void validate( AssignNormalValueToCommandCommand command ) throws Exception {
		Assert.notNull( command, "AssignNormalValueToCommandCommand should not be null" );
		Assert.notNull( command.getCommandId(), "AssignNormalValueToCommandCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignNormalValueToCommandCommand assignment should not be null" );
	}

	/**
	 * handles unassign NormalValue validation for a Command
	 * 
	 * @param	command UnAssignNormalValueFromCommandCommand
	 */	
	public void validate( UnAssignNormalValueFromCommandCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignNormalValueFromCommandCommand should not be null" );
		Assert.notNull( command.getCommandId(), "UnAssignNormalValueFromCommandCommand identifier should not be null" );
	}
	/**
	 * handles assign Value validation for a Command
	 * 
	 * @param	command AssignValueToCommandCommand
	 */	
	public void validate( AssignValueToCommandCommand command ) throws Exception {
		Assert.notNull( command, "AssignValueToCommandCommand should not be null" );
		Assert.notNull( command.getCommandId(), "AssignValueToCommandCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignValueToCommandCommand assignment should not be null" );
	}

	/**
	 * handles unassign Value validation for a Command
	 * 
	 * @param	command UnAssignValueFromCommandCommand
	 */	
	public void validate( UnAssignValueFromCommandCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignValueFromCommandCommand should not be null" );
		Assert.notNull( command.getCommandId(), "UnAssignValueFromCommandCommand identifier should not be null" );
	}

	/**
	 * handles add to ValueAliasSet validation for a Command
	 * 
	 * @param	command AssignValueAliasSetToCommandCommand
	 */	
	public void validate( AssignValueAliasSetToCommandCommand command ) throws Exception {
		Assert.notNull( command, "AssignValueAliasSetToCommandCommand should not be null" );
		Assert.notNull( command.getCommandId(), "AssignValueAliasSetToCommandCommand identifier should not be null" );
		Assert.notNull( command.getAddTo(), "AssignValueAliasSetToCommandCommand addTo attribute should not be null" );
	}

	/**
	 * handles remove from ValueAliasSet validation for a Command
	 * 
	 * @param	command RemoveValueAliasSetFromCommandCommand
	 */	
	public void validate( RemoveValueAliasSetFromCommandCommand command ) throws Exception {
		Assert.notNull( command, "RemoveValueAliasSetFromCommandCommand should not be null" );
		Assert.notNull( command.getCommandId(), "RemoveValueAliasSetFromCommandCommand identifier should not be null" );
		Assert.notNull( command.getRemoveFrom(), "RemoveValueAliasSetFromCommandCommand removeFrom attribute should not be null" );
		Assert.notNull( command.getRemoveFrom().getValueAliasSetId(), "RemoveValueAliasSetFromCommandCommand removeFrom attribubte identifier should not be null" );
	}
	
	/**
	 * handles add to DiscreteValue validation for a Command
	 * 
	 * @param	command AssignDiscreteValueToCommandCommand
	 */	
	public void validate( AssignDiscreteValueToCommandCommand command ) throws Exception {
		Assert.notNull( command, "AssignDiscreteValueToCommandCommand should not be null" );
		Assert.notNull( command.getCommandId(), "AssignDiscreteValueToCommandCommand identifier should not be null" );
		Assert.notNull( command.getAddTo(), "AssignDiscreteValueToCommandCommand addTo attribute should not be null" );
	}

	/**
	 * handles remove from DiscreteValue validation for a Command
	 * 
	 * @param	command RemoveDiscreteValueFromCommandCommand
	 */	
	public void validate( RemoveDiscreteValueFromCommandCommand command ) throws Exception {
		Assert.notNull( command, "RemoveDiscreteValueFromCommandCommand should not be null" );
		Assert.notNull( command.getCommandId(), "RemoveDiscreteValueFromCommandCommand identifier should not be null" );
		Assert.notNull( command.getRemoveFrom(), "RemoveDiscreteValueFromCommandCommand removeFrom attribute should not be null" );
		Assert.notNull( command.getRemoveFrom().getDiscreteValueId(), "RemoveDiscreteValueFromCommandCommand removeFrom attribubte identifier should not be null" );
	}
	

}
