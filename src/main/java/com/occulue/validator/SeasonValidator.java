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

public class SeasonValidator {
		
	/**
	 * default constructor
	 */
	protected SeasonValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public SeasonValidator getInstance() {
		return new SeasonValidator();
	}
		
	/**
	 * handles creation validation for a Season
	 */
	public void validate( CreateSeasonCommand season )throws Exception {
		Assert.notNull( season, "CreateSeasonCommand should not be null" );
//		Assert.isNull( season.getSeasonId(), "CreateSeasonCommand identifier should be null" );
	}

	/**
	 * handles update validation for a Season
	 */
	public void validate( UpdateSeasonCommand season ) throws Exception {
		Assert.notNull( season, "UpdateSeasonCommand should not be null" );
		Assert.notNull( season.getSeasonId(), "UpdateSeasonCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a Season
	 */
    public void validate( DeleteSeasonCommand season ) throws Exception {
		Assert.notNull( season, "{commandAlias} should not be null" );
		Assert.notNull( season.getSeasonId(), "DeleteSeasonCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a Season
	 */
	public void validate( SeasonFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "SeasonFetchOneSummary should not be null" );
		Assert.notNull( summary.getSeasonId(), "SeasonFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign EndDate validation for a Season
	 * 
	 * @param	command AssignEndDateToSeasonCommand
	 */	
	public void validate( AssignEndDateToSeasonCommand command ) throws Exception {
		Assert.notNull( command, "AssignEndDateToSeasonCommand should not be null" );
		Assert.notNull( command.getSeasonId(), "AssignEndDateToSeasonCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignEndDateToSeasonCommand assignment should not be null" );
	}

	/**
	 * handles unassign EndDate validation for a Season
	 * 
	 * @param	command UnAssignEndDateFromSeasonCommand
	 */	
	public void validate( UnAssignEndDateFromSeasonCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignEndDateFromSeasonCommand should not be null" );
		Assert.notNull( command.getSeasonId(), "UnAssignEndDateFromSeasonCommand identifier should not be null" );
	}
	/**
	 * handles assign StartDate validation for a Season
	 * 
	 * @param	command AssignStartDateToSeasonCommand
	 */	
	public void validate( AssignStartDateToSeasonCommand command ) throws Exception {
		Assert.notNull( command, "AssignStartDateToSeasonCommand should not be null" );
		Assert.notNull( command.getSeasonId(), "AssignStartDateToSeasonCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignStartDateToSeasonCommand assignment should not be null" );
	}

	/**
	 * handles unassign StartDate validation for a Season
	 * 
	 * @param	command UnAssignStartDateFromSeasonCommand
	 */	
	public void validate( UnAssignStartDateFromSeasonCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignStartDateFromSeasonCommand should not be null" );
		Assert.notNull( command.getSeasonId(), "UnAssignStartDateFromSeasonCommand identifier should not be null" );
	}


}
