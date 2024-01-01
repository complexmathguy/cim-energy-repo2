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

public class VoltageAdjusterDynamicsValidator {
		
	/**
	 * default constructor
	 */
	protected VoltageAdjusterDynamicsValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public VoltageAdjusterDynamicsValidator getInstance() {
		return new VoltageAdjusterDynamicsValidator();
	}
		
	/**
	 * handles creation validation for a VoltageAdjusterDynamics
	 */
	public void validate( CreateVoltageAdjusterDynamicsCommand voltageAdjusterDynamics )throws Exception {
		Assert.notNull( voltageAdjusterDynamics, "CreateVoltageAdjusterDynamicsCommand should not be null" );
//		Assert.isNull( voltageAdjusterDynamics.getVoltageAdjusterDynamicsId(), "CreateVoltageAdjusterDynamicsCommand identifier should be null" );
	}

	/**
	 * handles update validation for a VoltageAdjusterDynamics
	 */
	public void validate( UpdateVoltageAdjusterDynamicsCommand voltageAdjusterDynamics ) throws Exception {
		Assert.notNull( voltageAdjusterDynamics, "UpdateVoltageAdjusterDynamicsCommand should not be null" );
		Assert.notNull( voltageAdjusterDynamics.getVoltageAdjusterDynamicsId(), "UpdateVoltageAdjusterDynamicsCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a VoltageAdjusterDynamics
	 */
    public void validate( DeleteVoltageAdjusterDynamicsCommand voltageAdjusterDynamics ) throws Exception {
		Assert.notNull( voltageAdjusterDynamics, "{commandAlias} should not be null" );
		Assert.notNull( voltageAdjusterDynamics.getVoltageAdjusterDynamicsId(), "DeleteVoltageAdjusterDynamicsCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a VoltageAdjusterDynamics
	 */
	public void validate( VoltageAdjusterDynamicsFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "VoltageAdjusterDynamicsFetchOneSummary should not be null" );
		Assert.notNull( summary.getVoltageAdjusterDynamicsId(), "VoltageAdjusterDynamicsFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign PFVArControllerType1Dynamics validation for a VoltageAdjusterDynamics
	 * 
	 * @param	command AssignPFVArControllerType1DynamicsToVoltageAdjusterDynamicsCommand
	 */	
	public void validate( AssignPFVArControllerType1DynamicsToVoltageAdjusterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignPFVArControllerType1DynamicsToVoltageAdjusterDynamicsCommand should not be null" );
		Assert.notNull( command.getVoltageAdjusterDynamicsId(), "AssignPFVArControllerType1DynamicsToVoltageAdjusterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignPFVArControllerType1DynamicsToVoltageAdjusterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign PFVArControllerType1Dynamics validation for a VoltageAdjusterDynamics
	 * 
	 * @param	command UnAssignPFVArControllerType1DynamicsFromVoltageAdjusterDynamicsCommand
	 */	
	public void validate( UnAssignPFVArControllerType1DynamicsFromVoltageAdjusterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignPFVArControllerType1DynamicsFromVoltageAdjusterDynamicsCommand should not be null" );
		Assert.notNull( command.getVoltageAdjusterDynamicsId(), "UnAssignPFVArControllerType1DynamicsFromVoltageAdjusterDynamicsCommand identifier should not be null" );
	}


}
