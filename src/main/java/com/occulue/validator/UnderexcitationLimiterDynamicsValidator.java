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

public class UnderexcitationLimiterDynamicsValidator {
		
	/**
	 * default constructor
	 */
	protected UnderexcitationLimiterDynamicsValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public UnderexcitationLimiterDynamicsValidator getInstance() {
		return new UnderexcitationLimiterDynamicsValidator();
	}
		
	/**
	 * handles creation validation for a UnderexcitationLimiterDynamics
	 */
	public void validate( CreateUnderexcitationLimiterDynamicsCommand underexcitationLimiterDynamics )throws Exception {
		Assert.notNull( underexcitationLimiterDynamics, "CreateUnderexcitationLimiterDynamicsCommand should not be null" );
//		Assert.isNull( underexcitationLimiterDynamics.getUnderexcitationLimiterDynamicsId(), "CreateUnderexcitationLimiterDynamicsCommand identifier should be null" );
	}

	/**
	 * handles update validation for a UnderexcitationLimiterDynamics
	 */
	public void validate( UpdateUnderexcitationLimiterDynamicsCommand underexcitationLimiterDynamics ) throws Exception {
		Assert.notNull( underexcitationLimiterDynamics, "UpdateUnderexcitationLimiterDynamicsCommand should not be null" );
		Assert.notNull( underexcitationLimiterDynamics.getUnderexcitationLimiterDynamicsId(), "UpdateUnderexcitationLimiterDynamicsCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a UnderexcitationLimiterDynamics
	 */
    public void validate( DeleteUnderexcitationLimiterDynamicsCommand underexcitationLimiterDynamics ) throws Exception {
		Assert.notNull( underexcitationLimiterDynamics, "{commandAlias} should not be null" );
		Assert.notNull( underexcitationLimiterDynamics.getUnderexcitationLimiterDynamicsId(), "DeleteUnderexcitationLimiterDynamicsCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a UnderexcitationLimiterDynamics
	 */
	public void validate( UnderexcitationLimiterDynamicsFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "UnderexcitationLimiterDynamicsFetchOneSummary should not be null" );
		Assert.notNull( summary.getUnderexcitationLimiterDynamicsId(), "UnderexcitationLimiterDynamicsFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign ExcitationSystemDynamics validation for a UnderexcitationLimiterDynamics
	 * 
	 * @param	command AssignExcitationSystemDynamicsToUnderexcitationLimiterDynamicsCommand
	 */	
	public void validate( AssignExcitationSystemDynamicsToUnderexcitationLimiterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignExcitationSystemDynamicsToUnderexcitationLimiterDynamicsCommand should not be null" );
		Assert.notNull( command.getUnderexcitationLimiterDynamicsId(), "AssignExcitationSystemDynamicsToUnderexcitationLimiterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignExcitationSystemDynamicsToUnderexcitationLimiterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign ExcitationSystemDynamics validation for a UnderexcitationLimiterDynamics
	 * 
	 * @param	command UnAssignExcitationSystemDynamicsFromUnderexcitationLimiterDynamicsCommand
	 */	
	public void validate( UnAssignExcitationSystemDynamicsFromUnderexcitationLimiterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignExcitationSystemDynamicsFromUnderexcitationLimiterDynamicsCommand should not be null" );
		Assert.notNull( command.getUnderexcitationLimiterDynamicsId(), "UnAssignExcitationSystemDynamicsFromUnderexcitationLimiterDynamicsCommand identifier should not be null" );
	}


}
