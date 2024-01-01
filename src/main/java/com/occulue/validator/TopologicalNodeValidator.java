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

public class TopologicalNodeValidator {
		
	/**
	 * default constructor
	 */
	protected TopologicalNodeValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public TopologicalNodeValidator getInstance() {
		return new TopologicalNodeValidator();
	}
		
	/**
	 * handles creation validation for a TopologicalNode
	 */
	public void validate( CreateTopologicalNodeCommand topologicalNode )throws Exception {
		Assert.notNull( topologicalNode, "CreateTopologicalNodeCommand should not be null" );
//		Assert.isNull( topologicalNode.getTopologicalNodeId(), "CreateTopologicalNodeCommand identifier should be null" );
	}

	/**
	 * handles update validation for a TopologicalNode
	 */
	public void validate( UpdateTopologicalNodeCommand topologicalNode ) throws Exception {
		Assert.notNull( topologicalNode, "UpdateTopologicalNodeCommand should not be null" );
		Assert.notNull( topologicalNode.getTopologicalNodeId(), "UpdateTopologicalNodeCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a TopologicalNode
	 */
    public void validate( DeleteTopologicalNodeCommand topologicalNode ) throws Exception {
		Assert.notNull( topologicalNode, "{commandAlias} should not be null" );
		Assert.notNull( topologicalNode.getTopologicalNodeId(), "DeleteTopologicalNodeCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a TopologicalNode
	 */
	public void validate( TopologicalNodeFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "TopologicalNodeFetchOneSummary should not be null" );
		Assert.notNull( summary.getTopologicalNodeId(), "TopologicalNodeFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign BoundaryPoint validation for a TopologicalNode
	 * 
	 * @param	command AssignBoundaryPointToTopologicalNodeCommand
	 */	
	public void validate( AssignBoundaryPointToTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "AssignBoundaryPointToTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "AssignBoundaryPointToTopologicalNodeCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignBoundaryPointToTopologicalNodeCommand assignment should not be null" );
	}

	/**
	 * handles unassign BoundaryPoint validation for a TopologicalNode
	 * 
	 * @param	command UnAssignBoundaryPointFromTopologicalNodeCommand
	 */	
	public void validate( UnAssignBoundaryPointFromTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignBoundaryPointFromTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "UnAssignBoundaryPointFromTopologicalNodeCommand identifier should not be null" );
	}
	/**
	 * handles assign FromEndIsoCode validation for a TopologicalNode
	 * 
	 * @param	command AssignFromEndIsoCodeToTopologicalNodeCommand
	 */	
	public void validate( AssignFromEndIsoCodeToTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "AssignFromEndIsoCodeToTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "AssignFromEndIsoCodeToTopologicalNodeCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignFromEndIsoCodeToTopologicalNodeCommand assignment should not be null" );
	}

	/**
	 * handles unassign FromEndIsoCode validation for a TopologicalNode
	 * 
	 * @param	command UnAssignFromEndIsoCodeFromTopologicalNodeCommand
	 */	
	public void validate( UnAssignFromEndIsoCodeFromTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignFromEndIsoCodeFromTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "UnAssignFromEndIsoCodeFromTopologicalNodeCommand identifier should not be null" );
	}
	/**
	 * handles assign FromEndName validation for a TopologicalNode
	 * 
	 * @param	command AssignFromEndNameToTopologicalNodeCommand
	 */	
	public void validate( AssignFromEndNameToTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "AssignFromEndNameToTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "AssignFromEndNameToTopologicalNodeCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignFromEndNameToTopologicalNodeCommand assignment should not be null" );
	}

	/**
	 * handles unassign FromEndName validation for a TopologicalNode
	 * 
	 * @param	command UnAssignFromEndNameFromTopologicalNodeCommand
	 */	
	public void validate( UnAssignFromEndNameFromTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignFromEndNameFromTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "UnAssignFromEndNameFromTopologicalNodeCommand identifier should not be null" );
	}
	/**
	 * handles assign FromEndNameTso validation for a TopologicalNode
	 * 
	 * @param	command AssignFromEndNameTsoToTopologicalNodeCommand
	 */	
	public void validate( AssignFromEndNameTsoToTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "AssignFromEndNameTsoToTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "AssignFromEndNameTsoToTopologicalNodeCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignFromEndNameTsoToTopologicalNodeCommand assignment should not be null" );
	}

	/**
	 * handles unassign FromEndNameTso validation for a TopologicalNode
	 * 
	 * @param	command UnAssignFromEndNameTsoFromTopologicalNodeCommand
	 */	
	public void validate( UnAssignFromEndNameTsoFromTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignFromEndNameTsoFromTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "UnAssignFromEndNameTsoFromTopologicalNodeCommand identifier should not be null" );
	}
	/**
	 * handles assign ToEndIsoCode validation for a TopologicalNode
	 * 
	 * @param	command AssignToEndIsoCodeToTopologicalNodeCommand
	 */	
	public void validate( AssignToEndIsoCodeToTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "AssignToEndIsoCodeToTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "AssignToEndIsoCodeToTopologicalNodeCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignToEndIsoCodeToTopologicalNodeCommand assignment should not be null" );
	}

	/**
	 * handles unassign ToEndIsoCode validation for a TopologicalNode
	 * 
	 * @param	command UnAssignToEndIsoCodeFromTopologicalNodeCommand
	 */	
	public void validate( UnAssignToEndIsoCodeFromTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignToEndIsoCodeFromTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "UnAssignToEndIsoCodeFromTopologicalNodeCommand identifier should not be null" );
	}
	/**
	 * handles assign ToEndName validation for a TopologicalNode
	 * 
	 * @param	command AssignToEndNameToTopologicalNodeCommand
	 */	
	public void validate( AssignToEndNameToTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "AssignToEndNameToTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "AssignToEndNameToTopologicalNodeCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignToEndNameToTopologicalNodeCommand assignment should not be null" );
	}

	/**
	 * handles unassign ToEndName validation for a TopologicalNode
	 * 
	 * @param	command UnAssignToEndNameFromTopologicalNodeCommand
	 */	
	public void validate( UnAssignToEndNameFromTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignToEndNameFromTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "UnAssignToEndNameFromTopologicalNodeCommand identifier should not be null" );
	}
	/**
	 * handles assign ToEndNameTso validation for a TopologicalNode
	 * 
	 * @param	command AssignToEndNameTsoToTopologicalNodeCommand
	 */	
	public void validate( AssignToEndNameTsoToTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "AssignToEndNameTsoToTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "AssignToEndNameTsoToTopologicalNodeCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignToEndNameTsoToTopologicalNodeCommand assignment should not be null" );
	}

	/**
	 * handles unassign ToEndNameTso validation for a TopologicalNode
	 * 
	 * @param	command UnAssignToEndNameTsoFromTopologicalNodeCommand
	 */	
	public void validate( UnAssignToEndNameTsoFromTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignToEndNameTsoFromTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "UnAssignToEndNameTsoFromTopologicalNodeCommand identifier should not be null" );
	}
	/**
	 * handles assign ConnectivityNodeContainer validation for a TopologicalNode
	 * 
	 * @param	command AssignConnectivityNodeContainerToTopologicalNodeCommand
	 */	
	public void validate( AssignConnectivityNodeContainerToTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "AssignConnectivityNodeContainerToTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "AssignConnectivityNodeContainerToTopologicalNodeCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignConnectivityNodeContainerToTopologicalNodeCommand assignment should not be null" );
	}

	/**
	 * handles unassign ConnectivityNodeContainer validation for a TopologicalNode
	 * 
	 * @param	command UnAssignConnectivityNodeContainerFromTopologicalNodeCommand
	 */	
	public void validate( UnAssignConnectivityNodeContainerFromTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignConnectivityNodeContainerFromTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "UnAssignConnectivityNodeContainerFromTopologicalNodeCommand identifier should not be null" );
	}
	/**
	 * handles assign BaseVoltage validation for a TopologicalNode
	 * 
	 * @param	command AssignBaseVoltageToTopologicalNodeCommand
	 */	
	public void validate( AssignBaseVoltageToTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "AssignBaseVoltageToTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "AssignBaseVoltageToTopologicalNodeCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignBaseVoltageToTopologicalNodeCommand assignment should not be null" );
	}

	/**
	 * handles unassign BaseVoltage validation for a TopologicalNode
	 * 
	 * @param	command UnAssignBaseVoltageFromTopologicalNodeCommand
	 */	
	public void validate( UnAssignBaseVoltageFromTopologicalNodeCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignBaseVoltageFromTopologicalNodeCommand should not be null" );
		Assert.notNull( command.getTopologicalNodeId(), "UnAssignBaseVoltageFromTopologicalNodeCommand identifier should not be null" );
	}


}
