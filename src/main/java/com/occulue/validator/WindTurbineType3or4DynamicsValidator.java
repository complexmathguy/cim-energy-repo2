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

public class WindTurbineType3or4DynamicsValidator {
		
	/**
	 * default constructor
	 */
	protected WindTurbineType3or4DynamicsValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public WindTurbineType3or4DynamicsValidator getInstance() {
		return new WindTurbineType3or4DynamicsValidator();
	}
		
	/**
	 * handles creation validation for a WindTurbineType3or4Dynamics
	 */
	public void validate( CreateWindTurbineType3or4DynamicsCommand windTurbineType3or4Dynamics )throws Exception {
		Assert.notNull( windTurbineType3or4Dynamics, "CreateWindTurbineType3or4DynamicsCommand should not be null" );
//		Assert.isNull( windTurbineType3or4Dynamics.getWindTurbineType3or4DynamicsId(), "CreateWindTurbineType3or4DynamicsCommand identifier should be null" );
	}

	/**
	 * handles update validation for a WindTurbineType3or4Dynamics
	 */
	public void validate( UpdateWindTurbineType3or4DynamicsCommand windTurbineType3or4Dynamics ) throws Exception {
		Assert.notNull( windTurbineType3or4Dynamics, "UpdateWindTurbineType3or4DynamicsCommand should not be null" );
		Assert.notNull( windTurbineType3or4Dynamics.getWindTurbineType3or4DynamicsId(), "UpdateWindTurbineType3or4DynamicsCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a WindTurbineType3or4Dynamics
	 */
    public void validate( DeleteWindTurbineType3or4DynamicsCommand windTurbineType3or4Dynamics ) throws Exception {
		Assert.notNull( windTurbineType3or4Dynamics, "{commandAlias} should not be null" );
		Assert.notNull( windTurbineType3or4Dynamics.getWindTurbineType3or4DynamicsId(), "DeleteWindTurbineType3or4DynamicsCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a WindTurbineType3or4Dynamics
	 */
	public void validate( WindTurbineType3or4DynamicsFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "WindTurbineType3or4DynamicsFetchOneSummary should not be null" );
		Assert.notNull( summary.getWindTurbineType3or4DynamicsId(), "WindTurbineType3or4DynamicsFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign EnergySource validation for a WindTurbineType3or4Dynamics
	 * 
	 * @param	command AssignEnergySourceToWindTurbineType3or4DynamicsCommand
	 */	
	public void validate( AssignEnergySourceToWindTurbineType3or4DynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignEnergySourceToWindTurbineType3or4DynamicsCommand should not be null" );
		Assert.notNull( command.getWindTurbineType3or4DynamicsId(), "AssignEnergySourceToWindTurbineType3or4DynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignEnergySourceToWindTurbineType3or4DynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign EnergySource validation for a WindTurbineType3or4Dynamics
	 * 
	 * @param	command UnAssignEnergySourceFromWindTurbineType3or4DynamicsCommand
	 */	
	public void validate( UnAssignEnergySourceFromWindTurbineType3or4DynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignEnergySourceFromWindTurbineType3or4DynamicsCommand should not be null" );
		Assert.notNull( command.getWindTurbineType3or4DynamicsId(), "UnAssignEnergySourceFromWindTurbineType3or4DynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign WindPlantDynamics validation for a WindTurbineType3or4Dynamics
	 * 
	 * @param	command AssignWindPlantDynamicsToWindTurbineType3or4DynamicsCommand
	 */	
	public void validate( AssignWindPlantDynamicsToWindTurbineType3or4DynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindPlantDynamicsToWindTurbineType3or4DynamicsCommand should not be null" );
		Assert.notNull( command.getWindTurbineType3or4DynamicsId(), "AssignWindPlantDynamicsToWindTurbineType3or4DynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindPlantDynamicsToWindTurbineType3or4DynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindPlantDynamics validation for a WindTurbineType3or4Dynamics
	 * 
	 * @param	command UnAssignWindPlantDynamicsFromWindTurbineType3or4DynamicsCommand
	 */	
	public void validate( UnAssignWindPlantDynamicsFromWindTurbineType3or4DynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindPlantDynamicsFromWindTurbineType3or4DynamicsCommand should not be null" );
		Assert.notNull( command.getWindTurbineType3or4DynamicsId(), "UnAssignWindPlantDynamicsFromWindTurbineType3or4DynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign RemoteInputSignal validation for a WindTurbineType3or4Dynamics
	 * 
	 * @param	command AssignRemoteInputSignalToWindTurbineType3or4DynamicsCommand
	 */	
	public void validate( AssignRemoteInputSignalToWindTurbineType3or4DynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignRemoteInputSignalToWindTurbineType3or4DynamicsCommand should not be null" );
		Assert.notNull( command.getWindTurbineType3or4DynamicsId(), "AssignRemoteInputSignalToWindTurbineType3or4DynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignRemoteInputSignalToWindTurbineType3or4DynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign RemoteInputSignal validation for a WindTurbineType3or4Dynamics
	 * 
	 * @param	command UnAssignRemoteInputSignalFromWindTurbineType3or4DynamicsCommand
	 */	
	public void validate( UnAssignRemoteInputSignalFromWindTurbineType3or4DynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignRemoteInputSignalFromWindTurbineType3or4DynamicsCommand should not be null" );
		Assert.notNull( command.getWindTurbineType3or4DynamicsId(), "UnAssignRemoteInputSignalFromWindTurbineType3or4DynamicsCommand identifier should not be null" );
	}


}
