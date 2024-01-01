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

public class WindGenTurbineType3IECValidator {
		
	/**
	 * default constructor
	 */
	protected WindGenTurbineType3IECValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public WindGenTurbineType3IECValidator getInstance() {
		return new WindGenTurbineType3IECValidator();
	}
		
	/**
	 * handles creation validation for a WindGenTurbineType3IEC
	 */
	public void validate( CreateWindGenTurbineType3IECCommand windGenTurbineType3IEC )throws Exception {
		Assert.notNull( windGenTurbineType3IEC, "CreateWindGenTurbineType3IECCommand should not be null" );
//		Assert.isNull( windGenTurbineType3IEC.getWindGenTurbineType3IECId(), "CreateWindGenTurbineType3IECCommand identifier should be null" );
	}

	/**
	 * handles update validation for a WindGenTurbineType3IEC
	 */
	public void validate( UpdateWindGenTurbineType3IECCommand windGenTurbineType3IEC ) throws Exception {
		Assert.notNull( windGenTurbineType3IEC, "UpdateWindGenTurbineType3IECCommand should not be null" );
		Assert.notNull( windGenTurbineType3IEC.getWindGenTurbineType3IECId(), "UpdateWindGenTurbineType3IECCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a WindGenTurbineType3IEC
	 */
    public void validate( DeleteWindGenTurbineType3IECCommand windGenTurbineType3IEC ) throws Exception {
		Assert.notNull( windGenTurbineType3IEC, "{commandAlias} should not be null" );
		Assert.notNull( windGenTurbineType3IEC.getWindGenTurbineType3IECId(), "DeleteWindGenTurbineType3IECCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a WindGenTurbineType3IEC
	 */
	public void validate( WindGenTurbineType3IECFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "WindGenTurbineType3IECFetchOneSummary should not be null" );
		Assert.notNull( summary.getWindGenTurbineType3IECId(), "WindGenTurbineType3IECFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Dipmax validation for a WindGenTurbineType3IEC
	 * 
	 * @param	command AssignDipmaxToWindGenTurbineType3IECCommand
	 */	
	public void validate( AssignDipmaxToWindGenTurbineType3IECCommand command ) throws Exception {
		Assert.notNull( command, "AssignDipmaxToWindGenTurbineType3IECCommand should not be null" );
		Assert.notNull( command.getWindGenTurbineType3IECId(), "AssignDipmaxToWindGenTurbineType3IECCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDipmaxToWindGenTurbineType3IECCommand assignment should not be null" );
	}

	/**
	 * handles unassign Dipmax validation for a WindGenTurbineType3IEC
	 * 
	 * @param	command UnAssignDipmaxFromWindGenTurbineType3IECCommand
	 */	
	public void validate( UnAssignDipmaxFromWindGenTurbineType3IECCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDipmaxFromWindGenTurbineType3IECCommand should not be null" );
		Assert.notNull( command.getWindGenTurbineType3IECId(), "UnAssignDipmaxFromWindGenTurbineType3IECCommand identifier should not be null" );
	}
	/**
	 * handles assign Diqmax validation for a WindGenTurbineType3IEC
	 * 
	 * @param	command AssignDiqmaxToWindGenTurbineType3IECCommand
	 */	
	public void validate( AssignDiqmaxToWindGenTurbineType3IECCommand command ) throws Exception {
		Assert.notNull( command, "AssignDiqmaxToWindGenTurbineType3IECCommand should not be null" );
		Assert.notNull( command.getWindGenTurbineType3IECId(), "AssignDiqmaxToWindGenTurbineType3IECCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignDiqmaxToWindGenTurbineType3IECCommand assignment should not be null" );
	}

	/**
	 * handles unassign Diqmax validation for a WindGenTurbineType3IEC
	 * 
	 * @param	command UnAssignDiqmaxFromWindGenTurbineType3IECCommand
	 */	
	public void validate( UnAssignDiqmaxFromWindGenTurbineType3IECCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignDiqmaxFromWindGenTurbineType3IECCommand should not be null" );
		Assert.notNull( command.getWindGenTurbineType3IECId(), "UnAssignDiqmaxFromWindGenTurbineType3IECCommand identifier should not be null" );
	}
	/**
	 * handles assign WindMechIEC validation for a WindGenTurbineType3IEC
	 * 
	 * @param	command AssignWindMechIECToWindGenTurbineType3IECCommand
	 */	
	public void validate( AssignWindMechIECToWindGenTurbineType3IECCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindMechIECToWindGenTurbineType3IECCommand should not be null" );
		Assert.notNull( command.getWindGenTurbineType3IECId(), "AssignWindMechIECToWindGenTurbineType3IECCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindMechIECToWindGenTurbineType3IECCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindMechIEC validation for a WindGenTurbineType3IEC
	 * 
	 * @param	command UnAssignWindMechIECFromWindGenTurbineType3IECCommand
	 */	
	public void validate( UnAssignWindMechIECFromWindGenTurbineType3IECCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindMechIECFromWindGenTurbineType3IECCommand should not be null" );
		Assert.notNull( command.getWindGenTurbineType3IECId(), "UnAssignWindMechIECFromWindGenTurbineType3IECCommand identifier should not be null" );
	}
	/**
	 * handles assign WindContPitchAngleIEC validation for a WindGenTurbineType3IEC
	 * 
	 * @param	command AssignWindContPitchAngleIECToWindGenTurbineType3IECCommand
	 */	
	public void validate( AssignWindContPitchAngleIECToWindGenTurbineType3IECCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindContPitchAngleIECToWindGenTurbineType3IECCommand should not be null" );
		Assert.notNull( command.getWindGenTurbineType3IECId(), "AssignWindContPitchAngleIECToWindGenTurbineType3IECCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindContPitchAngleIECToWindGenTurbineType3IECCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindContPitchAngleIEC validation for a WindGenTurbineType3IEC
	 * 
	 * @param	command UnAssignWindContPitchAngleIECFromWindGenTurbineType3IECCommand
	 */	
	public void validate( UnAssignWindContPitchAngleIECFromWindGenTurbineType3IECCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindContPitchAngleIECFromWindGenTurbineType3IECCommand should not be null" );
		Assert.notNull( command.getWindGenTurbineType3IECId(), "UnAssignWindContPitchAngleIECFromWindGenTurbineType3IECCommand identifier should not be null" );
	}
	/**
	 * handles assign WindAeroLinearIEC validation for a WindGenTurbineType3IEC
	 * 
	 * @param	command AssignWindAeroLinearIECToWindGenTurbineType3IECCommand
	 */	
	public void validate( AssignWindAeroLinearIECToWindGenTurbineType3IECCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindAeroLinearIECToWindGenTurbineType3IECCommand should not be null" );
		Assert.notNull( command.getWindGenTurbineType3IECId(), "AssignWindAeroLinearIECToWindGenTurbineType3IECCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindAeroLinearIECToWindGenTurbineType3IECCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindAeroLinearIEC validation for a WindGenTurbineType3IEC
	 * 
	 * @param	command UnAssignWindAeroLinearIECFromWindGenTurbineType3IECCommand
	 */	
	public void validate( UnAssignWindAeroLinearIECFromWindGenTurbineType3IECCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindAeroLinearIECFromWindGenTurbineType3IECCommand should not be null" );
		Assert.notNull( command.getWindGenTurbineType3IECId(), "UnAssignWindAeroLinearIECFromWindGenTurbineType3IECCommand identifier should not be null" );
	}
	/**
	 * handles assign WindContPType3IEC validation for a WindGenTurbineType3IEC
	 * 
	 * @param	command AssignWindContPType3IECToWindGenTurbineType3IECCommand
	 */	
	public void validate( AssignWindContPType3IECToWindGenTurbineType3IECCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindContPType3IECToWindGenTurbineType3IECCommand should not be null" );
		Assert.notNull( command.getWindGenTurbineType3IECId(), "AssignWindContPType3IECToWindGenTurbineType3IECCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindContPType3IECToWindGenTurbineType3IECCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindContPType3IEC validation for a WindGenTurbineType3IEC
	 * 
	 * @param	command UnAssignWindContPType3IECFromWindGenTurbineType3IECCommand
	 */	
	public void validate( UnAssignWindContPType3IECFromWindGenTurbineType3IECCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindContPType3IECFromWindGenTurbineType3IECCommand should not be null" );
		Assert.notNull( command.getWindGenTurbineType3IECId(), "UnAssignWindContPType3IECFromWindGenTurbineType3IECCommand identifier should not be null" );
	}


}
