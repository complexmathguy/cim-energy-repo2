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

public class NonConformLoadValidator {
		
	/**
	 * default constructor
	 */
	protected NonConformLoadValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public NonConformLoadValidator getInstance() {
		return new NonConformLoadValidator();
	}
		
	/**
	 * handles creation validation for a NonConformLoad
	 */
	public void validate( CreateNonConformLoadCommand nonConformLoad )throws Exception {
		Assert.notNull( nonConformLoad, "CreateNonConformLoadCommand should not be null" );
//		Assert.isNull( nonConformLoad.getNonConformLoadId(), "CreateNonConformLoadCommand identifier should be null" );
	}

	/**
	 * handles update validation for a NonConformLoad
	 */
	public void validate( UpdateNonConformLoadCommand nonConformLoad ) throws Exception {
		Assert.notNull( nonConformLoad, "UpdateNonConformLoadCommand should not be null" );
		Assert.notNull( nonConformLoad.getNonConformLoadId(), "UpdateNonConformLoadCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a NonConformLoad
	 */
    public void validate( DeleteNonConformLoadCommand nonConformLoad ) throws Exception {
		Assert.notNull( nonConformLoad, "{commandAlias} should not be null" );
		Assert.notNull( nonConformLoad.getNonConformLoadId(), "DeleteNonConformLoadCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a NonConformLoad
	 */
	public void validate( NonConformLoadFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "NonConformLoadFetchOneSummary should not be null" );
		Assert.notNull( summary.getNonConformLoadId(), "NonConformLoadFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign LoadGroup validation for a NonConformLoad
	 * 
	 * @param	command AssignLoadGroupToNonConformLoadCommand
	 */	
	public void validate( AssignLoadGroupToNonConformLoadCommand command ) throws Exception {
		Assert.notNull( command, "AssignLoadGroupToNonConformLoadCommand should not be null" );
		Assert.notNull( command.getNonConformLoadId(), "AssignLoadGroupToNonConformLoadCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignLoadGroupToNonConformLoadCommand assignment should not be null" );
	}

	/**
	 * handles unassign LoadGroup validation for a NonConformLoad
	 * 
	 * @param	command UnAssignLoadGroupFromNonConformLoadCommand
	 */	
	public void validate( UnAssignLoadGroupFromNonConformLoadCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignLoadGroupFromNonConformLoadCommand should not be null" );
		Assert.notNull( command.getNonConformLoadId(), "UnAssignLoadGroupFromNonConformLoadCommand identifier should not be null" );
	}


}
