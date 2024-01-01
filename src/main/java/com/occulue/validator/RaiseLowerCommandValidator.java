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

public class RaiseLowerCommandValidator {
		
	/**
	 * default constructor
	 */
	protected RaiseLowerCommandValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public RaiseLowerCommandValidator getInstance() {
		return new RaiseLowerCommandValidator();
	}
		
	/**
	 * handles creation validation for a RaiseLowerCommand
	 */
	public void validate( CreateRaiseLowerCommandCommand raiseLowerCommand )throws Exception {
		Assert.notNull( raiseLowerCommand, "CreateRaiseLowerCommandCommand should not be null" );
//		Assert.isNull( raiseLowerCommand.getRaiseLowerCommandId(), "CreateRaiseLowerCommandCommand identifier should be null" );
	}

	/**
	 * handles update validation for a RaiseLowerCommand
	 */
	public void validate( UpdateRaiseLowerCommandCommand raiseLowerCommand ) throws Exception {
		Assert.notNull( raiseLowerCommand, "UpdateRaiseLowerCommandCommand should not be null" );
		Assert.notNull( raiseLowerCommand.getRaiseLowerCommandId(), "UpdateRaiseLowerCommandCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a RaiseLowerCommand
	 */
    public void validate( DeleteRaiseLowerCommandCommand raiseLowerCommand ) throws Exception {
		Assert.notNull( raiseLowerCommand, "{commandAlias} should not be null" );
		Assert.notNull( raiseLowerCommand.getRaiseLowerCommandId(), "DeleteRaiseLowerCommandCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a RaiseLowerCommand
	 */
	public void validate( RaiseLowerCommandFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "RaiseLowerCommandFetchOneSummary should not be null" );
		Assert.notNull( summary.getRaiseLowerCommandId(), "RaiseLowerCommandFetchOneSummary identifier should not be null" );
	}


	/**
	 * handles add to ValueAliasSet validation for a RaiseLowerCommand
	 * 
	 * @param	command AssignValueAliasSetToRaiseLowerCommandCommand
	 */	
	public void validate( AssignValueAliasSetToRaiseLowerCommandCommand command ) throws Exception {
		Assert.notNull( command, "AssignValueAliasSetToRaiseLowerCommandCommand should not be null" );
		Assert.notNull( command.getRaiseLowerCommandId(), "AssignValueAliasSetToRaiseLowerCommandCommand identifier should not be null" );
		Assert.notNull( command.getAddTo(), "AssignValueAliasSetToRaiseLowerCommandCommand addTo attribute should not be null" );
	}

	/**
	 * handles remove from ValueAliasSet validation for a RaiseLowerCommand
	 * 
	 * @param	command RemoveValueAliasSetFromRaiseLowerCommandCommand
	 */	
	public void validate( RemoveValueAliasSetFromRaiseLowerCommandCommand command ) throws Exception {
		Assert.notNull( command, "RemoveValueAliasSetFromRaiseLowerCommandCommand should not be null" );
		Assert.notNull( command.getRaiseLowerCommandId(), "RemoveValueAliasSetFromRaiseLowerCommandCommand identifier should not be null" );
		Assert.notNull( command.getRemoveFrom(), "RemoveValueAliasSetFromRaiseLowerCommandCommand removeFrom attribute should not be null" );
		Assert.notNull( command.getRemoveFrom().getValueAliasSetId(), "RemoveValueAliasSetFromRaiseLowerCommandCommand removeFrom attribubte identifier should not be null" );
	}
	

}
