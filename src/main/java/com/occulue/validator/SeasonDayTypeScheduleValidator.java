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

public class SeasonDayTypeScheduleValidator {
		
	/**
	 * default constructor
	 */
	protected SeasonDayTypeScheduleValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public SeasonDayTypeScheduleValidator getInstance() {
		return new SeasonDayTypeScheduleValidator();
	}
		
	/**
	 * handles creation validation for a SeasonDayTypeSchedule
	 */
	public void validate( CreateSeasonDayTypeScheduleCommand seasonDayTypeSchedule )throws Exception {
		Assert.notNull( seasonDayTypeSchedule, "CreateSeasonDayTypeScheduleCommand should not be null" );
//		Assert.isNull( seasonDayTypeSchedule.getSeasonDayTypeScheduleId(), "CreateSeasonDayTypeScheduleCommand identifier should be null" );
	}

	/**
	 * handles update validation for a SeasonDayTypeSchedule
	 */
	public void validate( UpdateSeasonDayTypeScheduleCommand seasonDayTypeSchedule ) throws Exception {
		Assert.notNull( seasonDayTypeSchedule, "UpdateSeasonDayTypeScheduleCommand should not be null" );
		Assert.notNull( seasonDayTypeSchedule.getSeasonDayTypeScheduleId(), "UpdateSeasonDayTypeScheduleCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a SeasonDayTypeSchedule
	 */
    public void validate( DeleteSeasonDayTypeScheduleCommand seasonDayTypeSchedule ) throws Exception {
		Assert.notNull( seasonDayTypeSchedule, "{commandAlias} should not be null" );
		Assert.notNull( seasonDayTypeSchedule.getSeasonDayTypeScheduleId(), "DeleteSeasonDayTypeScheduleCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a SeasonDayTypeSchedule
	 */
	public void validate( SeasonDayTypeScheduleFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "SeasonDayTypeScheduleFetchOneSummary should not be null" );
		Assert.notNull( summary.getSeasonDayTypeScheduleId(), "SeasonDayTypeScheduleFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Season validation for a SeasonDayTypeSchedule
	 * 
	 * @param	command AssignSeasonToSeasonDayTypeScheduleCommand
	 */	
	public void validate( AssignSeasonToSeasonDayTypeScheduleCommand command ) throws Exception {
		Assert.notNull( command, "AssignSeasonToSeasonDayTypeScheduleCommand should not be null" );
		Assert.notNull( command.getSeasonDayTypeScheduleId(), "AssignSeasonToSeasonDayTypeScheduleCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignSeasonToSeasonDayTypeScheduleCommand assignment should not be null" );
	}

	/**
	 * handles unassign Season validation for a SeasonDayTypeSchedule
	 * 
	 * @param	command UnAssignSeasonFromSeasonDayTypeScheduleCommand
	 */	
	public void validate( UnAssignSeasonFromSeasonDayTypeScheduleCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignSeasonFromSeasonDayTypeScheduleCommand should not be null" );
		Assert.notNull( command.getSeasonDayTypeScheduleId(), "UnAssignSeasonFromSeasonDayTypeScheduleCommand identifier should not be null" );
	}
	/**
	 * handles assign DayType validation for a SeasonDayTypeSchedule
	 * 
	 * @param	command AssignDayTypeToSeasonDayTypeScheduleCommand
	 */	
	public void validate( AssignDayTypeToSeasonDayTypeScheduleCommand command ) throws Exception {
		Assert.notNull( command, "AssignDayTypeToSeasonDayTypeScheduleCommand should not be null" );
		Assert.notNull( command.getSeasonDayTypeScheduleId(), "AssignDayTypeToSeasonDayTypeScheduleCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDayTypeToSeasonDayTypeScheduleCommand assignment should not be null" );
	}

	/**
	 * handles unassign DayType validation for a SeasonDayTypeSchedule
	 * 
	 * @param	command UnAssignDayTypeFromSeasonDayTypeScheduleCommand
	 */	
	public void validate( UnAssignDayTypeFromSeasonDayTypeScheduleCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDayTypeFromSeasonDayTypeScheduleCommand should not be null" );
		Assert.notNull( command.getSeasonDayTypeScheduleId(), "UnAssignDayTypeFromSeasonDayTypeScheduleCommand identifier should not be null" );
	}


}
