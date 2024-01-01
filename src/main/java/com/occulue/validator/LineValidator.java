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

public class LineValidator {
		
	/**
	 * default constructor
	 */
	protected LineValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public LineValidator getInstance() {
		return new LineValidator();
	}
		
	/**
	 * handles creation validation for a Line
	 */
	public void validate( CreateLineCommand line )throws Exception {
		Assert.notNull( line, "CreateLineCommand should not be null" );
//		Assert.isNull( line.getLineId(), "CreateLineCommand identifier should be null" );
	}

	/**
	 * handles update validation for a Line
	 */
	public void validate( UpdateLineCommand line ) throws Exception {
		Assert.notNull( line, "UpdateLineCommand should not be null" );
		Assert.notNull( line.getLineId(), "UpdateLineCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a Line
	 */
    public void validate( DeleteLineCommand line ) throws Exception {
		Assert.notNull( line, "{commandAlias} should not be null" );
		Assert.notNull( line.getLineId(), "DeleteLineCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a Line
	 */
	public void validate( LineFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "LineFetchOneSummary should not be null" );
		Assert.notNull( summary.getLineId(), "LineFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Region validation for a Line
	 * 
	 * @param	command AssignRegionToLineCommand
	 */	
	public void validate( AssignRegionToLineCommand command ) throws Exception {
		Assert.notNull( command, "AssignRegionToLineCommand should not be null" );
		Assert.notNull( command.getLineId(), "AssignRegionToLineCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignRegionToLineCommand assignment should not be null" );
	}

	/**
	 * handles unassign Region validation for a Line
	 * 
	 * @param	command UnAssignRegionFromLineCommand
	 */	
	public void validate( UnAssignRegionFromLineCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignRegionFromLineCommand should not be null" );
		Assert.notNull( command.getLineId(), "UnAssignRegionFromLineCommand identifier should not be null" );
	}


}
