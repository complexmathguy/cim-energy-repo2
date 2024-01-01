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

public class CurveDataValidator {
		
	/**
	 * default constructor
	 */
	protected CurveDataValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public CurveDataValidator getInstance() {
		return new CurveDataValidator();
	}
		
	/**
	 * handles creation validation for a CurveData
	 */
	public void validate( CreateCurveDataCommand curveData )throws Exception {
		Assert.notNull( curveData, "CreateCurveDataCommand should not be null" );
//		Assert.isNull( curveData.getCurveDataId(), "CreateCurveDataCommand identifier should be null" );
	}

	/**
	 * handles update validation for a CurveData
	 */
	public void validate( UpdateCurveDataCommand curveData ) throws Exception {
		Assert.notNull( curveData, "UpdateCurveDataCommand should not be null" );
		Assert.notNull( curveData.getCurveDataId(), "UpdateCurveDataCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a CurveData
	 */
    public void validate( DeleteCurveDataCommand curveData ) throws Exception {
		Assert.notNull( curveData, "{commandAlias} should not be null" );
		Assert.notNull( curveData.getCurveDataId(), "DeleteCurveDataCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a CurveData
	 */
	public void validate( CurveDataFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "CurveDataFetchOneSummary should not be null" );
		Assert.notNull( summary.getCurveDataId(), "CurveDataFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Xvalue validation for a CurveData
	 * 
	 * @param	command AssignXvalueToCurveDataCommand
	 */	
	public void validate( AssignXvalueToCurveDataCommand command ) throws Exception {
		Assert.notNull( command, "AssignXvalueToCurveDataCommand should not be null" );
		Assert.notNull( command.getCurveDataId(), "AssignXvalueToCurveDataCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignXvalueToCurveDataCommand assignment should not be null" );
	}

	/**
	 * handles unassign Xvalue validation for a CurveData
	 * 
	 * @param	command UnAssignXvalueFromCurveDataCommand
	 */	
	public void validate( UnAssignXvalueFromCurveDataCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignXvalueFromCurveDataCommand should not be null" );
		Assert.notNull( command.getCurveDataId(), "UnAssignXvalueFromCurveDataCommand identifier should not be null" );
	}
	/**
	 * handles assign Y1value validation for a CurveData
	 * 
	 * @param	command AssignY1valueToCurveDataCommand
	 */	
	public void validate( AssignY1valueToCurveDataCommand command ) throws Exception {
		Assert.notNull( command, "AssignY1valueToCurveDataCommand should not be null" );
		Assert.notNull( command.getCurveDataId(), "AssignY1valueToCurveDataCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignY1valueToCurveDataCommand assignment should not be null" );
	}

	/**
	 * handles unassign Y1value validation for a CurveData
	 * 
	 * @param	command UnAssignY1valueFromCurveDataCommand
	 */	
	public void validate( UnAssignY1valueFromCurveDataCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignY1valueFromCurveDataCommand should not be null" );
		Assert.notNull( command.getCurveDataId(), "UnAssignY1valueFromCurveDataCommand identifier should not be null" );
	}
	/**
	 * handles assign Y2value validation for a CurveData
	 * 
	 * @param	command AssignY2valueToCurveDataCommand
	 */	
	public void validate( AssignY2valueToCurveDataCommand command ) throws Exception {
		Assert.notNull( command, "AssignY2valueToCurveDataCommand should not be null" );
		Assert.notNull( command.getCurveDataId(), "AssignY2valueToCurveDataCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignY2valueToCurveDataCommand assignment should not be null" );
	}

	/**
	 * handles unassign Y2value validation for a CurveData
	 * 
	 * @param	command UnAssignY2valueFromCurveDataCommand
	 */	
	public void validate( UnAssignY2valueFromCurveDataCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignY2valueFromCurveDataCommand should not be null" );
		Assert.notNull( command.getCurveDataId(), "UnAssignY2valueFromCurveDataCommand identifier should not be null" );
	}
	/**
	 * handles assign Curve validation for a CurveData
	 * 
	 * @param	command AssignCurveToCurveDataCommand
	 */	
	public void validate( AssignCurveToCurveDataCommand command ) throws Exception {
		Assert.notNull( command, "AssignCurveToCurveDataCommand should not be null" );
		Assert.notNull( command.getCurveDataId(), "AssignCurveToCurveDataCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignCurveToCurveDataCommand assignment should not be null" );
	}

	/**
	 * handles unassign Curve validation for a CurveData
	 * 
	 * @param	command UnAssignCurveFromCurveDataCommand
	 */	
	public void validate( UnAssignCurveFromCurveDataCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignCurveFromCurveDataCommand should not be null" );
		Assert.notNull( command.getCurveDataId(), "UnAssignCurveFromCurveDataCommand identifier should not be null" );
	}


}
