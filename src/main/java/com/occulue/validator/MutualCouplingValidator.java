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

public class MutualCouplingValidator {
		
	/**
	 * default constructor
	 */
	protected MutualCouplingValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public MutualCouplingValidator getInstance() {
		return new MutualCouplingValidator();
	}
		
	/**
	 * handles creation validation for a MutualCoupling
	 */
	public void validate( CreateMutualCouplingCommand mutualCoupling )throws Exception {
		Assert.notNull( mutualCoupling, "CreateMutualCouplingCommand should not be null" );
//		Assert.isNull( mutualCoupling.getMutualCouplingId(), "CreateMutualCouplingCommand identifier should be null" );
	}

	/**
	 * handles update validation for a MutualCoupling
	 */
	public void validate( UpdateMutualCouplingCommand mutualCoupling ) throws Exception {
		Assert.notNull( mutualCoupling, "UpdateMutualCouplingCommand should not be null" );
		Assert.notNull( mutualCoupling.getMutualCouplingId(), "UpdateMutualCouplingCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a MutualCoupling
	 */
    public void validate( DeleteMutualCouplingCommand mutualCoupling ) throws Exception {
		Assert.notNull( mutualCoupling, "{commandAlias} should not be null" );
		Assert.notNull( mutualCoupling.getMutualCouplingId(), "DeleteMutualCouplingCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a MutualCoupling
	 */
	public void validate( MutualCouplingFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "MutualCouplingFetchOneSummary should not be null" );
		Assert.notNull( summary.getMutualCouplingId(), "MutualCouplingFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign B0ch validation for a MutualCoupling
	 * 
	 * @param	command AssignB0chToMutualCouplingCommand
	 */	
	public void validate( AssignB0chToMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "AssignB0chToMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "AssignB0chToMutualCouplingCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignB0chToMutualCouplingCommand assignment should not be null" );
	}

	/**
	 * handles unassign B0ch validation for a MutualCoupling
	 * 
	 * @param	command UnAssignB0chFromMutualCouplingCommand
	 */	
	public void validate( UnAssignB0chFromMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignB0chFromMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "UnAssignB0chFromMutualCouplingCommand identifier should not be null" );
	}
	/**
	 * handles assign Distance11 validation for a MutualCoupling
	 * 
	 * @param	command AssignDistance11ToMutualCouplingCommand
	 */	
	public void validate( AssignDistance11ToMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "AssignDistance11ToMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "AssignDistance11ToMutualCouplingCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDistance11ToMutualCouplingCommand assignment should not be null" );
	}

	/**
	 * handles unassign Distance11 validation for a MutualCoupling
	 * 
	 * @param	command UnAssignDistance11FromMutualCouplingCommand
	 */	
	public void validate( UnAssignDistance11FromMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDistance11FromMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "UnAssignDistance11FromMutualCouplingCommand identifier should not be null" );
	}
	/**
	 * handles assign Distance12 validation for a MutualCoupling
	 * 
	 * @param	command AssignDistance12ToMutualCouplingCommand
	 */	
	public void validate( AssignDistance12ToMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "AssignDistance12ToMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "AssignDistance12ToMutualCouplingCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDistance12ToMutualCouplingCommand assignment should not be null" );
	}

	/**
	 * handles unassign Distance12 validation for a MutualCoupling
	 * 
	 * @param	command UnAssignDistance12FromMutualCouplingCommand
	 */	
	public void validate( UnAssignDistance12FromMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDistance12FromMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "UnAssignDistance12FromMutualCouplingCommand identifier should not be null" );
	}
	/**
	 * handles assign Distance21 validation for a MutualCoupling
	 * 
	 * @param	command AssignDistance21ToMutualCouplingCommand
	 */	
	public void validate( AssignDistance21ToMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "AssignDistance21ToMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "AssignDistance21ToMutualCouplingCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDistance21ToMutualCouplingCommand assignment should not be null" );
	}

	/**
	 * handles unassign Distance21 validation for a MutualCoupling
	 * 
	 * @param	command UnAssignDistance21FromMutualCouplingCommand
	 */	
	public void validate( UnAssignDistance21FromMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDistance21FromMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "UnAssignDistance21FromMutualCouplingCommand identifier should not be null" );
	}
	/**
	 * handles assign Distance22 validation for a MutualCoupling
	 * 
	 * @param	command AssignDistance22ToMutualCouplingCommand
	 */	
	public void validate( AssignDistance22ToMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "AssignDistance22ToMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "AssignDistance22ToMutualCouplingCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDistance22ToMutualCouplingCommand assignment should not be null" );
	}

	/**
	 * handles unassign Distance22 validation for a MutualCoupling
	 * 
	 * @param	command UnAssignDistance22FromMutualCouplingCommand
	 */	
	public void validate( UnAssignDistance22FromMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDistance22FromMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "UnAssignDistance22FromMutualCouplingCommand identifier should not be null" );
	}
	/**
	 * handles assign G0ch validation for a MutualCoupling
	 * 
	 * @param	command AssignG0chToMutualCouplingCommand
	 */	
	public void validate( AssignG0chToMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "AssignG0chToMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "AssignG0chToMutualCouplingCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignG0chToMutualCouplingCommand assignment should not be null" );
	}

	/**
	 * handles unassign G0ch validation for a MutualCoupling
	 * 
	 * @param	command UnAssignG0chFromMutualCouplingCommand
	 */	
	public void validate( UnAssignG0chFromMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignG0chFromMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "UnAssignG0chFromMutualCouplingCommand identifier should not be null" );
	}
	/**
	 * handles assign R0 validation for a MutualCoupling
	 * 
	 * @param	command AssignR0ToMutualCouplingCommand
	 */	
	public void validate( AssignR0ToMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "AssignR0ToMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "AssignR0ToMutualCouplingCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignR0ToMutualCouplingCommand assignment should not be null" );
	}

	/**
	 * handles unassign R0 validation for a MutualCoupling
	 * 
	 * @param	command UnAssignR0FromMutualCouplingCommand
	 */	
	public void validate( UnAssignR0FromMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignR0FromMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "UnAssignR0FromMutualCouplingCommand identifier should not be null" );
	}
	/**
	 * handles assign X0 validation for a MutualCoupling
	 * 
	 * @param	command AssignX0ToMutualCouplingCommand
	 */	
	public void validate( AssignX0ToMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "AssignX0ToMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "AssignX0ToMutualCouplingCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignX0ToMutualCouplingCommand assignment should not be null" );
	}

	/**
	 * handles unassign X0 validation for a MutualCoupling
	 * 
	 * @param	command UnAssignX0FromMutualCouplingCommand
	 */	
	public void validate( UnAssignX0FromMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignX0FromMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "UnAssignX0FromMutualCouplingCommand identifier should not be null" );
	}
	/**
	 * handles assign Second_Terminal validation for a MutualCoupling
	 * 
	 * @param	command AssignSecond_TerminalToMutualCouplingCommand
	 */	
	public void validate( AssignSecond_TerminalToMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "AssignSecond_TerminalToMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "AssignSecond_TerminalToMutualCouplingCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignSecond_TerminalToMutualCouplingCommand assignment should not be null" );
	}

	/**
	 * handles unassign Second_Terminal validation for a MutualCoupling
	 * 
	 * @param	command UnAssignSecond_TerminalFromMutualCouplingCommand
	 */	
	public void validate( UnAssignSecond_TerminalFromMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignSecond_TerminalFromMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "UnAssignSecond_TerminalFromMutualCouplingCommand identifier should not be null" );
	}
	/**
	 * handles assign First_Terminal validation for a MutualCoupling
	 * 
	 * @param	command AssignFirst_TerminalToMutualCouplingCommand
	 */	
	public void validate( AssignFirst_TerminalToMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "AssignFirst_TerminalToMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "AssignFirst_TerminalToMutualCouplingCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignFirst_TerminalToMutualCouplingCommand assignment should not be null" );
	}

	/**
	 * handles unassign First_Terminal validation for a MutualCoupling
	 * 
	 * @param	command UnAssignFirst_TerminalFromMutualCouplingCommand
	 */	
	public void validate( UnAssignFirst_TerminalFromMutualCouplingCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignFirst_TerminalFromMutualCouplingCommand should not be null" );
		Assert.notNull( command.getMutualCouplingId(), "UnAssignFirst_TerminalFromMutualCouplingCommand identifier should not be null" );
	}


}
