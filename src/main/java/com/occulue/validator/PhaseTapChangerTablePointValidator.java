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

public class PhaseTapChangerTablePointValidator {
		
	/**
	 * default constructor
	 */
	protected PhaseTapChangerTablePointValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public PhaseTapChangerTablePointValidator getInstance() {
		return new PhaseTapChangerTablePointValidator();
	}
		
	/**
	 * handles creation validation for a PhaseTapChangerTablePoint
	 */
	public void validate( CreatePhaseTapChangerTablePointCommand phaseTapChangerTablePoint )throws Exception {
		Assert.notNull( phaseTapChangerTablePoint, "CreatePhaseTapChangerTablePointCommand should not be null" );
//		Assert.isNull( phaseTapChangerTablePoint.getPhaseTapChangerTablePointId(), "CreatePhaseTapChangerTablePointCommand identifier should be null" );
	}

	/**
	 * handles update validation for a PhaseTapChangerTablePoint
	 */
	public void validate( UpdatePhaseTapChangerTablePointCommand phaseTapChangerTablePoint ) throws Exception {
		Assert.notNull( phaseTapChangerTablePoint, "UpdatePhaseTapChangerTablePointCommand should not be null" );
		Assert.notNull( phaseTapChangerTablePoint.getPhaseTapChangerTablePointId(), "UpdatePhaseTapChangerTablePointCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a PhaseTapChangerTablePoint
	 */
    public void validate( DeletePhaseTapChangerTablePointCommand phaseTapChangerTablePoint ) throws Exception {
		Assert.notNull( phaseTapChangerTablePoint, "{commandAlias} should not be null" );
		Assert.notNull( phaseTapChangerTablePoint.getPhaseTapChangerTablePointId(), "DeletePhaseTapChangerTablePointCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a PhaseTapChangerTablePoint
	 */
	public void validate( PhaseTapChangerTablePointFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "PhaseTapChangerTablePointFetchOneSummary should not be null" );
		Assert.notNull( summary.getPhaseTapChangerTablePointId(), "PhaseTapChangerTablePointFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Angle validation for a PhaseTapChangerTablePoint
	 * 
	 * @param	command AssignAngleToPhaseTapChangerTablePointCommand
	 */	
	public void validate( AssignAngleToPhaseTapChangerTablePointCommand command ) throws Exception {
		Assert.notNull( command, "AssignAngleToPhaseTapChangerTablePointCommand should not be null" );
		Assert.notNull( command.getPhaseTapChangerTablePointId(), "AssignAngleToPhaseTapChangerTablePointCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignAngleToPhaseTapChangerTablePointCommand assignment should not be null" );
	}

	/**
	 * handles unassign Angle validation for a PhaseTapChangerTablePoint
	 * 
	 * @param	command UnAssignAngleFromPhaseTapChangerTablePointCommand
	 */	
	public void validate( UnAssignAngleFromPhaseTapChangerTablePointCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignAngleFromPhaseTapChangerTablePointCommand should not be null" );
		Assert.notNull( command.getPhaseTapChangerTablePointId(), "UnAssignAngleFromPhaseTapChangerTablePointCommand identifier should not be null" );
	}
	/**
	 * handles assign PhaseTapChangerTable validation for a PhaseTapChangerTablePoint
	 * 
	 * @param	command AssignPhaseTapChangerTableToPhaseTapChangerTablePointCommand
	 */	
	public void validate( AssignPhaseTapChangerTableToPhaseTapChangerTablePointCommand command ) throws Exception {
		Assert.notNull( command, "AssignPhaseTapChangerTableToPhaseTapChangerTablePointCommand should not be null" );
		Assert.notNull( command.getPhaseTapChangerTablePointId(), "AssignPhaseTapChangerTableToPhaseTapChangerTablePointCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignPhaseTapChangerTableToPhaseTapChangerTablePointCommand assignment should not be null" );
	}

	/**
	 * handles unassign PhaseTapChangerTable validation for a PhaseTapChangerTablePoint
	 * 
	 * @param	command UnAssignPhaseTapChangerTableFromPhaseTapChangerTablePointCommand
	 */	
	public void validate( UnAssignPhaseTapChangerTableFromPhaseTapChangerTablePointCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignPhaseTapChangerTableFromPhaseTapChangerTablePointCommand should not be null" );
		Assert.notNull( command.getPhaseTapChangerTablePointId(), "UnAssignPhaseTapChangerTableFromPhaseTapChangerTablePointCommand identifier should not be null" );
	}


}
