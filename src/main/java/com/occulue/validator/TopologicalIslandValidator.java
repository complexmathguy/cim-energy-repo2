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

public class TopologicalIslandValidator {
		
	/**
	 * default constructor
	 */
	protected TopologicalIslandValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public TopologicalIslandValidator getInstance() {
		return new TopologicalIslandValidator();
	}
		
	/**
	 * handles creation validation for a TopologicalIsland
	 */
	public void validate( CreateTopologicalIslandCommand topologicalIsland )throws Exception {
		Assert.notNull( topologicalIsland, "CreateTopologicalIslandCommand should not be null" );
//		Assert.isNull( topologicalIsland.getTopologicalIslandId(), "CreateTopologicalIslandCommand identifier should be null" );
	}

	/**
	 * handles update validation for a TopologicalIsland
	 */
	public void validate( UpdateTopologicalIslandCommand topologicalIsland ) throws Exception {
		Assert.notNull( topologicalIsland, "UpdateTopologicalIslandCommand should not be null" );
		Assert.notNull( topologicalIsland.getTopologicalIslandId(), "UpdateTopologicalIslandCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a TopologicalIsland
	 */
    public void validate( DeleteTopologicalIslandCommand topologicalIsland ) throws Exception {
		Assert.notNull( topologicalIsland, "{commandAlias} should not be null" );
		Assert.notNull( topologicalIsland.getTopologicalIslandId(), "DeleteTopologicalIslandCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a TopologicalIsland
	 */
	public void validate( TopologicalIslandFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "TopologicalIslandFetchOneSummary should not be null" );
		Assert.notNull( summary.getTopologicalIslandId(), "TopologicalIslandFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign TopologicalNodes validation for a TopologicalIsland
	 * 
	 * @param	command AssignTopologicalNodesToTopologicalIslandCommand
	 */	
	public void validate( AssignTopologicalNodesToTopologicalIslandCommand command ) throws Exception {
		Assert.notNull( command, "AssignTopologicalNodesToTopologicalIslandCommand should not be null" );
		Assert.notNull( command.getTopologicalIslandId(), "AssignTopologicalNodesToTopologicalIslandCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTopologicalNodesToTopologicalIslandCommand assignment should not be null" );
	}

	/**
	 * handles unassign TopologicalNodes validation for a TopologicalIsland
	 * 
	 * @param	command UnAssignTopologicalNodesFromTopologicalIslandCommand
	 */	
	public void validate( UnAssignTopologicalNodesFromTopologicalIslandCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTopologicalNodesFromTopologicalIslandCommand should not be null" );
		Assert.notNull( command.getTopologicalIslandId(), "UnAssignTopologicalNodesFromTopologicalIslandCommand identifier should not be null" );
	}
	/**
	 * handles assign AngleRefTopologicalNode validation for a TopologicalIsland
	 * 
	 * @param	command AssignAngleRefTopologicalNodeToTopologicalIslandCommand
	 */	
	public void validate( AssignAngleRefTopologicalNodeToTopologicalIslandCommand command ) throws Exception {
		Assert.notNull( command, "AssignAngleRefTopologicalNodeToTopologicalIslandCommand should not be null" );
		Assert.notNull( command.getTopologicalIslandId(), "AssignAngleRefTopologicalNodeToTopologicalIslandCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignAngleRefTopologicalNodeToTopologicalIslandCommand assignment should not be null" );
	}

	/**
	 * handles unassign AngleRefTopologicalNode validation for a TopologicalIsland
	 * 
	 * @param	command UnAssignAngleRefTopologicalNodeFromTopologicalIslandCommand
	 */	
	public void validate( UnAssignAngleRefTopologicalNodeFromTopologicalIslandCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignAngleRefTopologicalNodeFromTopologicalIslandCommand should not be null" );
		Assert.notNull( command.getTopologicalIslandId(), "UnAssignAngleRefTopologicalNodeFromTopologicalIslandCommand identifier should not be null" );
	}


}
