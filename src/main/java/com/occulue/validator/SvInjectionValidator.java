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

public class SvInjectionValidator {
		
	/**
	 * default constructor
	 */
	protected SvInjectionValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public SvInjectionValidator getInstance() {
		return new SvInjectionValidator();
	}
		
	/**
	 * handles creation validation for a SvInjection
	 */
	public void validate( CreateSvInjectionCommand svInjection )throws Exception {
		Assert.notNull( svInjection, "CreateSvInjectionCommand should not be null" );
//		Assert.isNull( svInjection.getSvInjectionId(), "CreateSvInjectionCommand identifier should be null" );
	}

	/**
	 * handles update validation for a SvInjection
	 */
	public void validate( UpdateSvInjectionCommand svInjection ) throws Exception {
		Assert.notNull( svInjection, "UpdateSvInjectionCommand should not be null" );
		Assert.notNull( svInjection.getSvInjectionId(), "UpdateSvInjectionCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a SvInjection
	 */
    public void validate( DeleteSvInjectionCommand svInjection ) throws Exception {
		Assert.notNull( svInjection, "{commandAlias} should not be null" );
		Assert.notNull( svInjection.getSvInjectionId(), "DeleteSvInjectionCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a SvInjection
	 */
	public void validate( SvInjectionFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "SvInjectionFetchOneSummary should not be null" );
		Assert.notNull( summary.getSvInjectionId(), "SvInjectionFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign PInjection validation for a SvInjection
	 * 
	 * @param	command AssignPInjectionToSvInjectionCommand
	 */	
	public void validate( AssignPInjectionToSvInjectionCommand command ) throws Exception {
		Assert.notNull( command, "AssignPInjectionToSvInjectionCommand should not be null" );
		Assert.notNull( command.getSvInjectionId(), "AssignPInjectionToSvInjectionCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignPInjectionToSvInjectionCommand assignment should not be null" );
	}

	/**
	 * handles unassign PInjection validation for a SvInjection
	 * 
	 * @param	command UnAssignPInjectionFromSvInjectionCommand
	 */	
	public void validate( UnAssignPInjectionFromSvInjectionCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignPInjectionFromSvInjectionCommand should not be null" );
		Assert.notNull( command.getSvInjectionId(), "UnAssignPInjectionFromSvInjectionCommand identifier should not be null" );
	}
	/**
	 * handles assign QInjection validation for a SvInjection
	 * 
	 * @param	command AssignQInjectionToSvInjectionCommand
	 */	
	public void validate( AssignQInjectionToSvInjectionCommand command ) throws Exception {
		Assert.notNull( command, "AssignQInjectionToSvInjectionCommand should not be null" );
		Assert.notNull( command.getSvInjectionId(), "AssignQInjectionToSvInjectionCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignQInjectionToSvInjectionCommand assignment should not be null" );
	}

	/**
	 * handles unassign QInjection validation for a SvInjection
	 * 
	 * @param	command UnAssignQInjectionFromSvInjectionCommand
	 */	
	public void validate( UnAssignQInjectionFromSvInjectionCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignQInjectionFromSvInjectionCommand should not be null" );
		Assert.notNull( command.getSvInjectionId(), "UnAssignQInjectionFromSvInjectionCommand identifier should not be null" );
	}
	/**
	 * handles assign TopologicalNode validation for a SvInjection
	 * 
	 * @param	command AssignTopologicalNodeToSvInjectionCommand
	 */	
	public void validate( AssignTopologicalNodeToSvInjectionCommand command ) throws Exception {
		Assert.notNull( command, "AssignTopologicalNodeToSvInjectionCommand should not be null" );
		Assert.notNull( command.getSvInjectionId(), "AssignTopologicalNodeToSvInjectionCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignTopologicalNodeToSvInjectionCommand assignment should not be null" );
	}

	/**
	 * handles unassign TopologicalNode validation for a SvInjection
	 * 
	 * @param	command UnAssignTopologicalNodeFromSvInjectionCommand
	 */	
	public void validate( UnAssignTopologicalNodeFromSvInjectionCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignTopologicalNodeFromSvInjectionCommand should not be null" );
		Assert.notNull( command.getSvInjectionId(), "UnAssignTopologicalNodeFromSvInjectionCommand identifier should not be null" );
	}


}
