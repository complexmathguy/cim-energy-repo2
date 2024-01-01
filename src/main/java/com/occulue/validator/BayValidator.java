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

public class BayValidator {
		
	/**
	 * default constructor
	 */
	protected BayValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public BayValidator getInstance() {
		return new BayValidator();
	}
		
	/**
	 * handles creation validation for a Bay
	 */
	public void validate( CreateBayCommand bay )throws Exception {
		Assert.notNull( bay, "CreateBayCommand should not be null" );
//		Assert.isNull( bay.getBayId(), "CreateBayCommand identifier should be null" );
	}

	/**
	 * handles update validation for a Bay
	 */
	public void validate( UpdateBayCommand bay ) throws Exception {
		Assert.notNull( bay, "UpdateBayCommand should not be null" );
		Assert.notNull( bay.getBayId(), "UpdateBayCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a Bay
	 */
    public void validate( DeleteBayCommand bay ) throws Exception {
		Assert.notNull( bay, "{commandAlias} should not be null" );
		Assert.notNull( bay.getBayId(), "DeleteBayCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a Bay
	 */
	public void validate( BayFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "BayFetchOneSummary should not be null" );
		Assert.notNull( summary.getBayId(), "BayFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign VoltageLevel validation for a Bay
	 * 
	 * @param	command AssignVoltageLevelToBayCommand
	 */	
	public void validate( AssignVoltageLevelToBayCommand command ) throws Exception {
		Assert.notNull( command, "AssignVoltageLevelToBayCommand should not be null" );
		Assert.notNull( command.getBayId(), "AssignVoltageLevelToBayCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignVoltageLevelToBayCommand assignment should not be null" );
	}

	/**
	 * handles unassign VoltageLevel validation for a Bay
	 * 
	 * @param	command UnAssignVoltageLevelFromBayCommand
	 */	
	public void validate( UnAssignVoltageLevelFromBayCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignVoltageLevelFromBayCommand should not be null" );
		Assert.notNull( command.getBayId(), "UnAssignVoltageLevelFromBayCommand identifier should not be null" );
	}


}
