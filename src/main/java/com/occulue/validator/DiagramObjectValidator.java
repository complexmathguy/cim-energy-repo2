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

public class DiagramObjectValidator {
		
	/**
	 * default constructor
	 */
	protected DiagramObjectValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public DiagramObjectValidator getInstance() {
		return new DiagramObjectValidator();
	}
		
	/**
	 * handles creation validation for a DiagramObject
	 */
	public void validate( CreateDiagramObjectCommand diagramObject )throws Exception {
		Assert.notNull( diagramObject, "CreateDiagramObjectCommand should not be null" );
//		Assert.isNull( diagramObject.getDiagramObjectId(), "CreateDiagramObjectCommand identifier should be null" );
	}

	/**
	 * handles update validation for a DiagramObject
	 */
	public void validate( UpdateDiagramObjectCommand diagramObject ) throws Exception {
		Assert.notNull( diagramObject, "UpdateDiagramObjectCommand should not be null" );
		Assert.notNull( diagramObject.getDiagramObjectId(), "UpdateDiagramObjectCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a DiagramObject
	 */
    public void validate( DeleteDiagramObjectCommand diagramObject ) throws Exception {
		Assert.notNull( diagramObject, "{commandAlias} should not be null" );
		Assert.notNull( diagramObject.getDiagramObjectId(), "DeleteDiagramObjectCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a DiagramObject
	 */
	public void validate( DiagramObjectFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "DiagramObjectFetchOneSummary should not be null" );
		Assert.notNull( summary.getDiagramObjectId(), "DiagramObjectFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign DrawingOrder validation for a DiagramObject
	 * 
	 * @param	command AssignDrawingOrderToDiagramObjectCommand
	 */	
	public void validate( AssignDrawingOrderToDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "AssignDrawingOrderToDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "AssignDrawingOrderToDiagramObjectCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDrawingOrderToDiagramObjectCommand assignment should not be null" );
	}

	/**
	 * handles unassign DrawingOrder validation for a DiagramObject
	 * 
	 * @param	command UnAssignDrawingOrderFromDiagramObjectCommand
	 */	
	public void validate( UnAssignDrawingOrderFromDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDrawingOrderFromDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "UnAssignDrawingOrderFromDiagramObjectCommand identifier should not be null" );
	}
	/**
	 * handles assign PolygonFlag validation for a DiagramObject
	 * 
	 * @param	command AssignPolygonFlagToDiagramObjectCommand
	 */	
	public void validate( AssignPolygonFlagToDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "AssignPolygonFlagToDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "AssignPolygonFlagToDiagramObjectCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignPolygonFlagToDiagramObjectCommand assignment should not be null" );
	}

	/**
	 * handles unassign PolygonFlag validation for a DiagramObject
	 * 
	 * @param	command UnAssignPolygonFlagFromDiagramObjectCommand
	 */	
	public void validate( UnAssignPolygonFlagFromDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignPolygonFlagFromDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "UnAssignPolygonFlagFromDiagramObjectCommand identifier should not be null" );
	}
	/**
	 * handles assign OffsetX validation for a DiagramObject
	 * 
	 * @param	command AssignOffsetXToDiagramObjectCommand
	 */	
	public void validate( AssignOffsetXToDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "AssignOffsetXToDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "AssignOffsetXToDiagramObjectCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignOffsetXToDiagramObjectCommand assignment should not be null" );
	}

	/**
	 * handles unassign OffsetX validation for a DiagramObject
	 * 
	 * @param	command UnAssignOffsetXFromDiagramObjectCommand
	 */	
	public void validate( UnAssignOffsetXFromDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignOffsetXFromDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "UnAssignOffsetXFromDiagramObjectCommand identifier should not be null" );
	}
	/**
	 * handles assign OffsetY validation for a DiagramObject
	 * 
	 * @param	command AssignOffsetYToDiagramObjectCommand
	 */	
	public void validate( AssignOffsetYToDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "AssignOffsetYToDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "AssignOffsetYToDiagramObjectCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignOffsetYToDiagramObjectCommand assignment should not be null" );
	}

	/**
	 * handles unassign OffsetY validation for a DiagramObject
	 * 
	 * @param	command UnAssignOffsetYFromDiagramObjectCommand
	 */	
	public void validate( UnAssignOffsetYFromDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignOffsetYFromDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "UnAssignOffsetYFromDiagramObjectCommand identifier should not be null" );
	}
	/**
	 * handles assign Rotation validation for a DiagramObject
	 * 
	 * @param	command AssignRotationToDiagramObjectCommand
	 */	
	public void validate( AssignRotationToDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "AssignRotationToDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "AssignRotationToDiagramObjectCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignRotationToDiagramObjectCommand assignment should not be null" );
	}

	/**
	 * handles unassign Rotation validation for a DiagramObject
	 * 
	 * @param	command UnAssignRotationFromDiagramObjectCommand
	 */	
	public void validate( UnAssignRotationFromDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignRotationFromDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "UnAssignRotationFromDiagramObjectCommand identifier should not be null" );
	}
	/**
	 * handles assign DiagramObjectStyle validation for a DiagramObject
	 * 
	 * @param	command AssignDiagramObjectStyleToDiagramObjectCommand
	 */	
	public void validate( AssignDiagramObjectStyleToDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "AssignDiagramObjectStyleToDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "AssignDiagramObjectStyleToDiagramObjectCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDiagramObjectStyleToDiagramObjectCommand assignment should not be null" );
	}

	/**
	 * handles unassign DiagramObjectStyle validation for a DiagramObject
	 * 
	 * @param	command UnAssignDiagramObjectStyleFromDiagramObjectCommand
	 */	
	public void validate( UnAssignDiagramObjectStyleFromDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDiagramObjectStyleFromDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "UnAssignDiagramObjectStyleFromDiagramObjectCommand identifier should not be null" );
	}
	/**
	 * handles assign Diagram validation for a DiagramObject
	 * 
	 * @param	command AssignDiagramToDiagramObjectCommand
	 */	
	public void validate( AssignDiagramToDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "AssignDiagramToDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "AssignDiagramToDiagramObjectCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDiagramToDiagramObjectCommand assignment should not be null" );
	}

	/**
	 * handles unassign Diagram validation for a DiagramObject
	 * 
	 * @param	command UnAssignDiagramFromDiagramObjectCommand
	 */	
	public void validate( UnAssignDiagramFromDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDiagramFromDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "UnAssignDiagramFromDiagramObjectCommand identifier should not be null" );
	}
	/**
	 * handles assign IdentifiedObject validation for a DiagramObject
	 * 
	 * @param	command AssignIdentifiedObjectToDiagramObjectCommand
	 */	
	public void validate( AssignIdentifiedObjectToDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "AssignIdentifiedObjectToDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "AssignIdentifiedObjectToDiagramObjectCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignIdentifiedObjectToDiagramObjectCommand assignment should not be null" );
	}

	/**
	 * handles unassign IdentifiedObject validation for a DiagramObject
	 * 
	 * @param	command UnAssignIdentifiedObjectFromDiagramObjectCommand
	 */	
	public void validate( UnAssignIdentifiedObjectFromDiagramObjectCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignIdentifiedObjectFromDiagramObjectCommand should not be null" );
		Assert.notNull( command.getDiagramObjectId(), "UnAssignIdentifiedObjectFromDiagramObjectCommand identifier should not be null" );
	}


}
