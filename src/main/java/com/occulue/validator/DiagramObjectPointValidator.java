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

public class DiagramObjectPointValidator {
		
	/**
	 * default constructor
	 */
	protected DiagramObjectPointValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public DiagramObjectPointValidator getInstance() {
		return new DiagramObjectPointValidator();
	}
		
	/**
	 * handles creation validation for a DiagramObjectPoint
	 */
	public void validate( CreateDiagramObjectPointCommand diagramObjectPoint )throws Exception {
		Assert.notNull( diagramObjectPoint, "CreateDiagramObjectPointCommand should not be null" );
//		Assert.isNull( diagramObjectPoint.getDiagramObjectPointId(), "CreateDiagramObjectPointCommand identifier should be null" );
	}

	/**
	 * handles update validation for a DiagramObjectPoint
	 */
	public void validate( UpdateDiagramObjectPointCommand diagramObjectPoint ) throws Exception {
		Assert.notNull( diagramObjectPoint, "UpdateDiagramObjectPointCommand should not be null" );
		Assert.notNull( diagramObjectPoint.getDiagramObjectPointId(), "UpdateDiagramObjectPointCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a DiagramObjectPoint
	 */
    public void validate( DeleteDiagramObjectPointCommand diagramObjectPoint ) throws Exception {
		Assert.notNull( diagramObjectPoint, "{commandAlias} should not be null" );
		Assert.notNull( diagramObjectPoint.getDiagramObjectPointId(), "DeleteDiagramObjectPointCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a DiagramObjectPoint
	 */
	public void validate( DiagramObjectPointFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "DiagramObjectPointFetchOneSummary should not be null" );
		Assert.notNull( summary.getDiagramObjectPointId(), "DiagramObjectPointFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign SequenceNumber validation for a DiagramObjectPoint
	 * 
	 * @param	command AssignSequenceNumberToDiagramObjectPointCommand
	 */	
	public void validate( AssignSequenceNumberToDiagramObjectPointCommand command ) throws Exception {
		Assert.notNull( command, "AssignSequenceNumberToDiagramObjectPointCommand should not be null" );
		Assert.notNull( command.getDiagramObjectPointId(), "AssignSequenceNumberToDiagramObjectPointCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignSequenceNumberToDiagramObjectPointCommand assignment should not be null" );
	}

	/**
	 * handles unassign SequenceNumber validation for a DiagramObjectPoint
	 * 
	 * @param	command UnAssignSequenceNumberFromDiagramObjectPointCommand
	 */	
	public void validate( UnAssignSequenceNumberFromDiagramObjectPointCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignSequenceNumberFromDiagramObjectPointCommand should not be null" );
		Assert.notNull( command.getDiagramObjectPointId(), "UnAssignSequenceNumberFromDiagramObjectPointCommand identifier should not be null" );
	}
	/**
	 * handles assign XPosition validation for a DiagramObjectPoint
	 * 
	 * @param	command AssignXPositionToDiagramObjectPointCommand
	 */	
	public void validate( AssignXPositionToDiagramObjectPointCommand command ) throws Exception {
		Assert.notNull( command, "AssignXPositionToDiagramObjectPointCommand should not be null" );
		Assert.notNull( command.getDiagramObjectPointId(), "AssignXPositionToDiagramObjectPointCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignXPositionToDiagramObjectPointCommand assignment should not be null" );
	}

	/**
	 * handles unassign XPosition validation for a DiagramObjectPoint
	 * 
	 * @param	command UnAssignXPositionFromDiagramObjectPointCommand
	 */	
	public void validate( UnAssignXPositionFromDiagramObjectPointCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignXPositionFromDiagramObjectPointCommand should not be null" );
		Assert.notNull( command.getDiagramObjectPointId(), "UnAssignXPositionFromDiagramObjectPointCommand identifier should not be null" );
	}
	/**
	 * handles assign YPosition validation for a DiagramObjectPoint
	 * 
	 * @param	command AssignYPositionToDiagramObjectPointCommand
	 */	
	public void validate( AssignYPositionToDiagramObjectPointCommand command ) throws Exception {
		Assert.notNull( command, "AssignYPositionToDiagramObjectPointCommand should not be null" );
		Assert.notNull( command.getDiagramObjectPointId(), "AssignYPositionToDiagramObjectPointCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignYPositionToDiagramObjectPointCommand assignment should not be null" );
	}

	/**
	 * handles unassign YPosition validation for a DiagramObjectPoint
	 * 
	 * @param	command UnAssignYPositionFromDiagramObjectPointCommand
	 */	
	public void validate( UnAssignYPositionFromDiagramObjectPointCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignYPositionFromDiagramObjectPointCommand should not be null" );
		Assert.notNull( command.getDiagramObjectPointId(), "UnAssignYPositionFromDiagramObjectPointCommand identifier should not be null" );
	}
	/**
	 * handles assign ZPosition validation for a DiagramObjectPoint
	 * 
	 * @param	command AssignZPositionToDiagramObjectPointCommand
	 */	
	public void validate( AssignZPositionToDiagramObjectPointCommand command ) throws Exception {
		Assert.notNull( command, "AssignZPositionToDiagramObjectPointCommand should not be null" );
		Assert.notNull( command.getDiagramObjectPointId(), "AssignZPositionToDiagramObjectPointCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignZPositionToDiagramObjectPointCommand assignment should not be null" );
	}

	/**
	 * handles unassign ZPosition validation for a DiagramObjectPoint
	 * 
	 * @param	command UnAssignZPositionFromDiagramObjectPointCommand
	 */	
	public void validate( UnAssignZPositionFromDiagramObjectPointCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignZPositionFromDiagramObjectPointCommand should not be null" );
		Assert.notNull( command.getDiagramObjectPointId(), "UnAssignZPositionFromDiagramObjectPointCommand identifier should not be null" );
	}
	/**
	 * handles assign DiagramObject validation for a DiagramObjectPoint
	 * 
	 * @param	command AssignDiagramObjectToDiagramObjectPointCommand
	 */	
	public void validate( AssignDiagramObjectToDiagramObjectPointCommand command ) throws Exception {
		Assert.notNull( command, "AssignDiagramObjectToDiagramObjectPointCommand should not be null" );
		Assert.notNull( command.getDiagramObjectPointId(), "AssignDiagramObjectToDiagramObjectPointCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDiagramObjectToDiagramObjectPointCommand assignment should not be null" );
	}

	/**
	 * handles unassign DiagramObject validation for a DiagramObjectPoint
	 * 
	 * @param	command UnAssignDiagramObjectFromDiagramObjectPointCommand
	 */	
	public void validate( UnAssignDiagramObjectFromDiagramObjectPointCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDiagramObjectFromDiagramObjectPointCommand should not be null" );
		Assert.notNull( command.getDiagramObjectPointId(), "UnAssignDiagramObjectFromDiagramObjectPointCommand identifier should not be null" );
	}
	/**
	 * handles assign DiagramObjectGluePoint validation for a DiagramObjectPoint
	 * 
	 * @param	command AssignDiagramObjectGluePointToDiagramObjectPointCommand
	 */	
	public void validate( AssignDiagramObjectGluePointToDiagramObjectPointCommand command ) throws Exception {
		Assert.notNull( command, "AssignDiagramObjectGluePointToDiagramObjectPointCommand should not be null" );
		Assert.notNull( command.getDiagramObjectPointId(), "AssignDiagramObjectGluePointToDiagramObjectPointCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDiagramObjectGluePointToDiagramObjectPointCommand assignment should not be null" );
	}

	/**
	 * handles unassign DiagramObjectGluePoint validation for a DiagramObjectPoint
	 * 
	 * @param	command UnAssignDiagramObjectGluePointFromDiagramObjectPointCommand
	 */	
	public void validate( UnAssignDiagramObjectGluePointFromDiagramObjectPointCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDiagramObjectGluePointFromDiagramObjectPointCommand should not be null" );
		Assert.notNull( command.getDiagramObjectPointId(), "UnAssignDiagramObjectGluePointFromDiagramObjectPointCommand identifier should not be null" );
	}


}
