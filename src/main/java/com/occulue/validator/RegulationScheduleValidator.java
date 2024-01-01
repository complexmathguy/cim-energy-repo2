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

public class RegulationScheduleValidator {
		
	/**
	 * default constructor
	 */
	protected RegulationScheduleValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public RegulationScheduleValidator getInstance() {
		return new RegulationScheduleValidator();
	}
		
	/**
	 * handles creation validation for a RegulationSchedule
	 */
	public void validate( CreateRegulationScheduleCommand regulationSchedule )throws Exception {
		Assert.notNull( regulationSchedule, "CreateRegulationScheduleCommand should not be null" );
//		Assert.isNull( regulationSchedule.getRegulationScheduleId(), "CreateRegulationScheduleCommand identifier should be null" );
	}

	/**
	 * handles update validation for a RegulationSchedule
	 */
	public void validate( UpdateRegulationScheduleCommand regulationSchedule ) throws Exception {
		Assert.notNull( regulationSchedule, "UpdateRegulationScheduleCommand should not be null" );
		Assert.notNull( regulationSchedule.getRegulationScheduleId(), "UpdateRegulationScheduleCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a RegulationSchedule
	 */
    public void validate( DeleteRegulationScheduleCommand regulationSchedule ) throws Exception {
		Assert.notNull( regulationSchedule, "{commandAlias} should not be null" );
		Assert.notNull( regulationSchedule.getRegulationScheduleId(), "DeleteRegulationScheduleCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a RegulationSchedule
	 */
	public void validate( RegulationScheduleFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "RegulationScheduleFetchOneSummary should not be null" );
		Assert.notNull( summary.getRegulationScheduleId(), "RegulationScheduleFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign RegulatingControl validation for a RegulationSchedule
	 * 
	 * @param	command AssignRegulatingControlToRegulationScheduleCommand
	 */	
	public void validate( AssignRegulatingControlToRegulationScheduleCommand command ) throws Exception {
		Assert.notNull( command, "AssignRegulatingControlToRegulationScheduleCommand should not be null" );
		Assert.notNull( command.getRegulationScheduleId(), "AssignRegulatingControlToRegulationScheduleCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignRegulatingControlToRegulationScheduleCommand assignment should not be null" );
	}

	/**
	 * handles unassign RegulatingControl validation for a RegulationSchedule
	 * 
	 * @param	command UnAssignRegulatingControlFromRegulationScheduleCommand
	 */	
	public void validate( UnAssignRegulatingControlFromRegulationScheduleCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignRegulatingControlFromRegulationScheduleCommand should not be null" );
		Assert.notNull( command.getRegulationScheduleId(), "UnAssignRegulatingControlFromRegulationScheduleCommand identifier should not be null" );
	}


}
