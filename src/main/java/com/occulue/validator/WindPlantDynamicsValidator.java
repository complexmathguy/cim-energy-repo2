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

public class WindPlantDynamicsValidator {
		
	/**
	 * default constructor
	 */
	protected WindPlantDynamicsValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public WindPlantDynamicsValidator getInstance() {
		return new WindPlantDynamicsValidator();
	}
		
	/**
	 * handles creation validation for a WindPlantDynamics
	 */
	public void validate( CreateWindPlantDynamicsCommand windPlantDynamics )throws Exception {
		Assert.notNull( windPlantDynamics, "CreateWindPlantDynamicsCommand should not be null" );
//		Assert.isNull( windPlantDynamics.getWindPlantDynamicsId(), "CreateWindPlantDynamicsCommand identifier should be null" );
	}

	/**
	 * handles update validation for a WindPlantDynamics
	 */
	public void validate( UpdateWindPlantDynamicsCommand windPlantDynamics ) throws Exception {
		Assert.notNull( windPlantDynamics, "UpdateWindPlantDynamicsCommand should not be null" );
		Assert.notNull( windPlantDynamics.getWindPlantDynamicsId(), "UpdateWindPlantDynamicsCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a WindPlantDynamics
	 */
    public void validate( DeleteWindPlantDynamicsCommand windPlantDynamics ) throws Exception {
		Assert.notNull( windPlantDynamics, "{commandAlias} should not be null" );
		Assert.notNull( windPlantDynamics.getWindPlantDynamicsId(), "DeleteWindPlantDynamicsCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a WindPlantDynamics
	 */
	public void validate( WindPlantDynamicsFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "WindPlantDynamicsFetchOneSummary should not be null" );
		Assert.notNull( summary.getWindPlantDynamicsId(), "WindPlantDynamicsFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign RemoteInputSignal validation for a WindPlantDynamics
	 * 
	 * @param	command AssignRemoteInputSignalToWindPlantDynamicsCommand
	 */	
	public void validate( AssignRemoteInputSignalToWindPlantDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignRemoteInputSignalToWindPlantDynamicsCommand should not be null" );
		Assert.notNull( command.getWindPlantDynamicsId(), "AssignRemoteInputSignalToWindPlantDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignRemoteInputSignalToWindPlantDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign RemoteInputSignal validation for a WindPlantDynamics
	 * 
	 * @param	command UnAssignRemoteInputSignalFromWindPlantDynamicsCommand
	 */	
	public void validate( UnAssignRemoteInputSignalFromWindPlantDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignRemoteInputSignalFromWindPlantDynamicsCommand should not be null" );
		Assert.notNull( command.getWindPlantDynamicsId(), "UnAssignRemoteInputSignalFromWindPlantDynamicsCommand identifier should not be null" );
	}


}
