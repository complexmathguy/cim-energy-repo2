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

public class NonlinearShuntCompensatorPointValidator {
		
	/**
	 * default constructor
	 */
	protected NonlinearShuntCompensatorPointValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public NonlinearShuntCompensatorPointValidator getInstance() {
		return new NonlinearShuntCompensatorPointValidator();
	}
		
	/**
	 * handles creation validation for a NonlinearShuntCompensatorPoint
	 */
	public void validate( CreateNonlinearShuntCompensatorPointCommand nonlinearShuntCompensatorPoint )throws Exception {
		Assert.notNull( nonlinearShuntCompensatorPoint, "CreateNonlinearShuntCompensatorPointCommand should not be null" );
//		Assert.isNull( nonlinearShuntCompensatorPoint.getNonlinearShuntCompensatorPointId(), "CreateNonlinearShuntCompensatorPointCommand identifier should be null" );
	}

	/**
	 * handles update validation for a NonlinearShuntCompensatorPoint
	 */
	public void validate( UpdateNonlinearShuntCompensatorPointCommand nonlinearShuntCompensatorPoint ) throws Exception {
		Assert.notNull( nonlinearShuntCompensatorPoint, "UpdateNonlinearShuntCompensatorPointCommand should not be null" );
		Assert.notNull( nonlinearShuntCompensatorPoint.getNonlinearShuntCompensatorPointId(), "UpdateNonlinearShuntCompensatorPointCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a NonlinearShuntCompensatorPoint
	 */
    public void validate( DeleteNonlinearShuntCompensatorPointCommand nonlinearShuntCompensatorPoint ) throws Exception {
		Assert.notNull( nonlinearShuntCompensatorPoint, "{commandAlias} should not be null" );
		Assert.notNull( nonlinearShuntCompensatorPoint.getNonlinearShuntCompensatorPointId(), "DeleteNonlinearShuntCompensatorPointCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a NonlinearShuntCompensatorPoint
	 */
	public void validate( NonlinearShuntCompensatorPointFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "NonlinearShuntCompensatorPointFetchOneSummary should not be null" );
		Assert.notNull( summary.getNonlinearShuntCompensatorPointId(), "NonlinearShuntCompensatorPointFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign B validation for a NonlinearShuntCompensatorPoint
	 * 
	 * @param	command AssignBToNonlinearShuntCompensatorPointCommand
	 */	
	public void validate( AssignBToNonlinearShuntCompensatorPointCommand command ) throws Exception {
		Assert.notNull( command, "AssignBToNonlinearShuntCompensatorPointCommand should not be null" );
		Assert.notNull( command.getNonlinearShuntCompensatorPointId(), "AssignBToNonlinearShuntCompensatorPointCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignBToNonlinearShuntCompensatorPointCommand assignment should not be null" );
	}

	/**
	 * handles unassign B validation for a NonlinearShuntCompensatorPoint
	 * 
	 * @param	command UnAssignBFromNonlinearShuntCompensatorPointCommand
	 */	
	public void validate( UnAssignBFromNonlinearShuntCompensatorPointCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignBFromNonlinearShuntCompensatorPointCommand should not be null" );
		Assert.notNull( command.getNonlinearShuntCompensatorPointId(), "UnAssignBFromNonlinearShuntCompensatorPointCommand identifier should not be null" );
	}
	/**
	 * handles assign B0 validation for a NonlinearShuntCompensatorPoint
	 * 
	 * @param	command AssignB0ToNonlinearShuntCompensatorPointCommand
	 */	
	public void validate( AssignB0ToNonlinearShuntCompensatorPointCommand command ) throws Exception {
		Assert.notNull( command, "AssignB0ToNonlinearShuntCompensatorPointCommand should not be null" );
		Assert.notNull( command.getNonlinearShuntCompensatorPointId(), "AssignB0ToNonlinearShuntCompensatorPointCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignB0ToNonlinearShuntCompensatorPointCommand assignment should not be null" );
	}

	/**
	 * handles unassign B0 validation for a NonlinearShuntCompensatorPoint
	 * 
	 * @param	command UnAssignB0FromNonlinearShuntCompensatorPointCommand
	 */	
	public void validate( UnAssignB0FromNonlinearShuntCompensatorPointCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignB0FromNonlinearShuntCompensatorPointCommand should not be null" );
		Assert.notNull( command.getNonlinearShuntCompensatorPointId(), "UnAssignB0FromNonlinearShuntCompensatorPointCommand identifier should not be null" );
	}
	/**
	 * handles assign G validation for a NonlinearShuntCompensatorPoint
	 * 
	 * @param	command AssignGToNonlinearShuntCompensatorPointCommand
	 */	
	public void validate( AssignGToNonlinearShuntCompensatorPointCommand command ) throws Exception {
		Assert.notNull( command, "AssignGToNonlinearShuntCompensatorPointCommand should not be null" );
		Assert.notNull( command.getNonlinearShuntCompensatorPointId(), "AssignGToNonlinearShuntCompensatorPointCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignGToNonlinearShuntCompensatorPointCommand assignment should not be null" );
	}

	/**
	 * handles unassign G validation for a NonlinearShuntCompensatorPoint
	 * 
	 * @param	command UnAssignGFromNonlinearShuntCompensatorPointCommand
	 */	
	public void validate( UnAssignGFromNonlinearShuntCompensatorPointCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignGFromNonlinearShuntCompensatorPointCommand should not be null" );
		Assert.notNull( command.getNonlinearShuntCompensatorPointId(), "UnAssignGFromNonlinearShuntCompensatorPointCommand identifier should not be null" );
	}
	/**
	 * handles assign G0 validation for a NonlinearShuntCompensatorPoint
	 * 
	 * @param	command AssignG0ToNonlinearShuntCompensatorPointCommand
	 */	
	public void validate( AssignG0ToNonlinearShuntCompensatorPointCommand command ) throws Exception {
		Assert.notNull( command, "AssignG0ToNonlinearShuntCompensatorPointCommand should not be null" );
		Assert.notNull( command.getNonlinearShuntCompensatorPointId(), "AssignG0ToNonlinearShuntCompensatorPointCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignG0ToNonlinearShuntCompensatorPointCommand assignment should not be null" );
	}

	/**
	 * handles unassign G0 validation for a NonlinearShuntCompensatorPoint
	 * 
	 * @param	command UnAssignG0FromNonlinearShuntCompensatorPointCommand
	 */	
	public void validate( UnAssignG0FromNonlinearShuntCompensatorPointCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignG0FromNonlinearShuntCompensatorPointCommand should not be null" );
		Assert.notNull( command.getNonlinearShuntCompensatorPointId(), "UnAssignG0FromNonlinearShuntCompensatorPointCommand identifier should not be null" );
	}
	/**
	 * handles assign SectionNumber validation for a NonlinearShuntCompensatorPoint
	 * 
	 * @param	command AssignSectionNumberToNonlinearShuntCompensatorPointCommand
	 */	
	public void validate( AssignSectionNumberToNonlinearShuntCompensatorPointCommand command ) throws Exception {
		Assert.notNull( command, "AssignSectionNumberToNonlinearShuntCompensatorPointCommand should not be null" );
		Assert.notNull( command.getNonlinearShuntCompensatorPointId(), "AssignSectionNumberToNonlinearShuntCompensatorPointCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignSectionNumberToNonlinearShuntCompensatorPointCommand assignment should not be null" );
	}

	/**
	 * handles unassign SectionNumber validation for a NonlinearShuntCompensatorPoint
	 * 
	 * @param	command UnAssignSectionNumberFromNonlinearShuntCompensatorPointCommand
	 */	
	public void validate( UnAssignSectionNumberFromNonlinearShuntCompensatorPointCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignSectionNumberFromNonlinearShuntCompensatorPointCommand should not be null" );
		Assert.notNull( command.getNonlinearShuntCompensatorPointId(), "UnAssignSectionNumberFromNonlinearShuntCompensatorPointCommand identifier should not be null" );
	}
	/**
	 * handles assign NonlinearShuntCompensator validation for a NonlinearShuntCompensatorPoint
	 * 
	 * @param	command AssignNonlinearShuntCompensatorToNonlinearShuntCompensatorPointCommand
	 */	
	public void validate( AssignNonlinearShuntCompensatorToNonlinearShuntCompensatorPointCommand command ) throws Exception {
		Assert.notNull( command, "AssignNonlinearShuntCompensatorToNonlinearShuntCompensatorPointCommand should not be null" );
		Assert.notNull( command.getNonlinearShuntCompensatorPointId(), "AssignNonlinearShuntCompensatorToNonlinearShuntCompensatorPointCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignNonlinearShuntCompensatorToNonlinearShuntCompensatorPointCommand assignment should not be null" );
	}

	/**
	 * handles unassign NonlinearShuntCompensator validation for a NonlinearShuntCompensatorPoint
	 * 
	 * @param	command UnAssignNonlinearShuntCompensatorFromNonlinearShuntCompensatorPointCommand
	 */	
	public void validate( UnAssignNonlinearShuntCompensatorFromNonlinearShuntCompensatorPointCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignNonlinearShuntCompensatorFromNonlinearShuntCompensatorPointCommand should not be null" );
		Assert.notNull( command.getNonlinearShuntCompensatorPointId(), "UnAssignNonlinearShuntCompensatorFromNonlinearShuntCompensatorPointCommand identifier should not be null" );
	}


}
