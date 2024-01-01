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

public class EnergySourceValidator {
		
	/**
	 * default constructor
	 */
	protected EnergySourceValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public EnergySourceValidator getInstance() {
		return new EnergySourceValidator();
	}
		
	/**
	 * handles creation validation for a EnergySource
	 */
	public void validate( CreateEnergySourceCommand energySource )throws Exception {
		Assert.notNull( energySource, "CreateEnergySourceCommand should not be null" );
//		Assert.isNull( energySource.getEnergySourceId(), "CreateEnergySourceCommand identifier should be null" );
	}

	/**
	 * handles update validation for a EnergySource
	 */
	public void validate( UpdateEnergySourceCommand energySource ) throws Exception {
		Assert.notNull( energySource, "UpdateEnergySourceCommand should not be null" );
		Assert.notNull( energySource.getEnergySourceId(), "UpdateEnergySourceCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a EnergySource
	 */
    public void validate( DeleteEnergySourceCommand energySource ) throws Exception {
		Assert.notNull( energySource, "{commandAlias} should not be null" );
		Assert.notNull( energySource.getEnergySourceId(), "DeleteEnergySourceCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a EnergySource
	 */
	public void validate( EnergySourceFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "EnergySourceFetchOneSummary should not be null" );
		Assert.notNull( summary.getEnergySourceId(), "EnergySourceFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign EnergySchedulingType validation for a EnergySource
	 * 
	 * @param	command AssignEnergySchedulingTypeToEnergySourceCommand
	 */	
	public void validate( AssignEnergySchedulingTypeToEnergySourceCommand command ) throws Exception {
		Assert.notNull( command, "AssignEnergySchedulingTypeToEnergySourceCommand should not be null" );
		Assert.notNull( command.getEnergySourceId(), "AssignEnergySchedulingTypeToEnergySourceCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignEnergySchedulingTypeToEnergySourceCommand assignment should not be null" );
	}

	/**
	 * handles unassign EnergySchedulingType validation for a EnergySource
	 * 
	 * @param	command UnAssignEnergySchedulingTypeFromEnergySourceCommand
	 */	
	public void validate( UnAssignEnergySchedulingTypeFromEnergySourceCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignEnergySchedulingTypeFromEnergySourceCommand should not be null" );
		Assert.notNull( command.getEnergySourceId(), "UnAssignEnergySchedulingTypeFromEnergySourceCommand identifier should not be null" );
	}


}
