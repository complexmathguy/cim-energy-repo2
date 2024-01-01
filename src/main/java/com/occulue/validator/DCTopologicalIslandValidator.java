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

public class DCTopologicalIslandValidator {
		
	/**
	 * default constructor
	 */
	protected DCTopologicalIslandValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public DCTopologicalIslandValidator getInstance() {
		return new DCTopologicalIslandValidator();
	}
		
	/**
	 * handles creation validation for a DCTopologicalIsland
	 */
	public void validate( CreateDCTopologicalIslandCommand dCTopologicalIsland )throws Exception {
		Assert.notNull( dCTopologicalIsland, "CreateDCTopologicalIslandCommand should not be null" );
//		Assert.isNull( dCTopologicalIsland.getDCTopologicalIslandId(), "CreateDCTopologicalIslandCommand identifier should be null" );
	}

	/**
	 * handles update validation for a DCTopologicalIsland
	 */
	public void validate( UpdateDCTopologicalIslandCommand dCTopologicalIsland ) throws Exception {
		Assert.notNull( dCTopologicalIsland, "UpdateDCTopologicalIslandCommand should not be null" );
		Assert.notNull( dCTopologicalIsland.getDCTopologicalIslandId(), "UpdateDCTopologicalIslandCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a DCTopologicalIsland
	 */
    public void validate( DeleteDCTopologicalIslandCommand dCTopologicalIsland ) throws Exception {
		Assert.notNull( dCTopologicalIsland, "{commandAlias} should not be null" );
		Assert.notNull( dCTopologicalIsland.getDCTopologicalIslandId(), "DeleteDCTopologicalIslandCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a DCTopologicalIsland
	 */
	public void validate( DCTopologicalIslandFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "DCTopologicalIslandFetchOneSummary should not be null" );
		Assert.notNull( summary.getDCTopologicalIslandId(), "DCTopologicalIslandFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign DCTopologicalNodes validation for a DCTopologicalIsland
	 * 
	 * @param	command AssignDCTopologicalNodesToDCTopologicalIslandCommand
	 */	
	public void validate( AssignDCTopologicalNodesToDCTopologicalIslandCommand command ) throws Exception {
		Assert.notNull( command, "AssignDCTopologicalNodesToDCTopologicalIslandCommand should not be null" );
		Assert.notNull( command.getDCTopologicalIslandId(), "AssignDCTopologicalNodesToDCTopologicalIslandCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDCTopologicalNodesToDCTopologicalIslandCommand assignment should not be null" );
	}

	/**
	 * handles unassign DCTopologicalNodes validation for a DCTopologicalIsland
	 * 
	 * @param	command UnAssignDCTopologicalNodesFromDCTopologicalIslandCommand
	 */	
	public void validate( UnAssignDCTopologicalNodesFromDCTopologicalIslandCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDCTopologicalNodesFromDCTopologicalIslandCommand should not be null" );
		Assert.notNull( command.getDCTopologicalIslandId(), "UnAssignDCTopologicalNodesFromDCTopologicalIslandCommand identifier should not be null" );
	}


}
