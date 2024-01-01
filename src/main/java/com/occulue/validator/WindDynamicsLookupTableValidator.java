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

public class WindDynamicsLookupTableValidator {
		
	/**
	 * default constructor
	 */
	protected WindDynamicsLookupTableValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public WindDynamicsLookupTableValidator getInstance() {
		return new WindDynamicsLookupTableValidator();
	}
		
	/**
	 * handles creation validation for a WindDynamicsLookupTable
	 */
	public void validate( CreateWindDynamicsLookupTableCommand windDynamicsLookupTable )throws Exception {
		Assert.notNull( windDynamicsLookupTable, "CreateWindDynamicsLookupTableCommand should not be null" );
//		Assert.isNull( windDynamicsLookupTable.getWindDynamicsLookupTableId(), "CreateWindDynamicsLookupTableCommand identifier should be null" );
	}

	/**
	 * handles update validation for a WindDynamicsLookupTable
	 */
	public void validate( UpdateWindDynamicsLookupTableCommand windDynamicsLookupTable ) throws Exception {
		Assert.notNull( windDynamicsLookupTable, "UpdateWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( windDynamicsLookupTable.getWindDynamicsLookupTableId(), "UpdateWindDynamicsLookupTableCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a WindDynamicsLookupTable
	 */
    public void validate( DeleteWindDynamicsLookupTableCommand windDynamicsLookupTable ) throws Exception {
		Assert.notNull( windDynamicsLookupTable, "{commandAlias} should not be null" );
		Assert.notNull( windDynamicsLookupTable.getWindDynamicsLookupTableId(), "DeleteWindDynamicsLookupTableCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a WindDynamicsLookupTable
	 */
	public void validate( WindDynamicsLookupTableFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "WindDynamicsLookupTableFetchOneSummary should not be null" );
		Assert.notNull( summary.getWindDynamicsLookupTableId(), "WindDynamicsLookupTableFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Input validation for a WindDynamicsLookupTable
	 * 
	 * @param	command AssignInputToWindDynamicsLookupTableCommand
	 */	
	public void validate( AssignInputToWindDynamicsLookupTableCommand command ) throws Exception {
		Assert.notNull( command, "AssignInputToWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( command.getWindDynamicsLookupTableId(), "AssignInputToWindDynamicsLookupTableCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignInputToWindDynamicsLookupTableCommand assignment should not be null" );
	}

	/**
	 * handles unassign Input validation for a WindDynamicsLookupTable
	 * 
	 * @param	command UnAssignInputFromWindDynamicsLookupTableCommand
	 */	
	public void validate( UnAssignInputFromWindDynamicsLookupTableCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignInputFromWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( command.getWindDynamicsLookupTableId(), "UnAssignInputFromWindDynamicsLookupTableCommand identifier should not be null" );
	}
	/**
	 * handles assign Output validation for a WindDynamicsLookupTable
	 * 
	 * @param	command AssignOutputToWindDynamicsLookupTableCommand
	 */	
	public void validate( AssignOutputToWindDynamicsLookupTableCommand command ) throws Exception {
		Assert.notNull( command, "AssignOutputToWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( command.getWindDynamicsLookupTableId(), "AssignOutputToWindDynamicsLookupTableCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignOutputToWindDynamicsLookupTableCommand assignment should not be null" );
	}

	/**
	 * handles unassign Output validation for a WindDynamicsLookupTable
	 * 
	 * @param	command UnAssignOutputFromWindDynamicsLookupTableCommand
	 */	
	public void validate( UnAssignOutputFromWindDynamicsLookupTableCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignOutputFromWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( command.getWindDynamicsLookupTableId(), "UnAssignOutputFromWindDynamicsLookupTableCommand identifier should not be null" );
	}
	/**
	 * handles assign Sequence validation for a WindDynamicsLookupTable
	 * 
	 * @param	command AssignSequenceToWindDynamicsLookupTableCommand
	 */	
	public void validate( AssignSequenceToWindDynamicsLookupTableCommand command ) throws Exception {
		Assert.notNull( command, "AssignSequenceToWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( command.getWindDynamicsLookupTableId(), "AssignSequenceToWindDynamicsLookupTableCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignSequenceToWindDynamicsLookupTableCommand assignment should not be null" );
	}

	/**
	 * handles unassign Sequence validation for a WindDynamicsLookupTable
	 * 
	 * @param	command UnAssignSequenceFromWindDynamicsLookupTableCommand
	 */	
	public void validate( UnAssignSequenceFromWindDynamicsLookupTableCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignSequenceFromWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( command.getWindDynamicsLookupTableId(), "UnAssignSequenceFromWindDynamicsLookupTableCommand identifier should not be null" );
	}
	/**
	 * handles assign WindContRotorRIEC validation for a WindDynamicsLookupTable
	 * 
	 * @param	command AssignWindContRotorRIECToWindDynamicsLookupTableCommand
	 */	
	public void validate( AssignWindContRotorRIECToWindDynamicsLookupTableCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindContRotorRIECToWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( command.getWindDynamicsLookupTableId(), "AssignWindContRotorRIECToWindDynamicsLookupTableCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindContRotorRIECToWindDynamicsLookupTableCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindContRotorRIEC validation for a WindDynamicsLookupTable
	 * 
	 * @param	command UnAssignWindContRotorRIECFromWindDynamicsLookupTableCommand
	 */	
	public void validate( UnAssignWindContRotorRIECFromWindDynamicsLookupTableCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindContRotorRIECFromWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( command.getWindDynamicsLookupTableId(), "UnAssignWindContRotorRIECFromWindDynamicsLookupTableCommand identifier should not be null" );
	}
	/**
	 * handles assign WindContCurrLimIEC validation for a WindDynamicsLookupTable
	 * 
	 * @param	command AssignWindContCurrLimIECToWindDynamicsLookupTableCommand
	 */	
	public void validate( AssignWindContCurrLimIECToWindDynamicsLookupTableCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindContCurrLimIECToWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( command.getWindDynamicsLookupTableId(), "AssignWindContCurrLimIECToWindDynamicsLookupTableCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindContCurrLimIECToWindDynamicsLookupTableCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindContCurrLimIEC validation for a WindDynamicsLookupTable
	 * 
	 * @param	command UnAssignWindContCurrLimIECFromWindDynamicsLookupTableCommand
	 */	
	public void validate( UnAssignWindContCurrLimIECFromWindDynamicsLookupTableCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindContCurrLimIECFromWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( command.getWindDynamicsLookupTableId(), "UnAssignWindContCurrLimIECFromWindDynamicsLookupTableCommand identifier should not be null" );
	}
	/**
	 * handles assign WindPlantFreqPcontrolIEC validation for a WindDynamicsLookupTable
	 * 
	 * @param	command AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableCommand
	 */	
	public void validate( AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( command.getWindDynamicsLookupTableId(), "AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindPlantFreqPcontrolIECToWindDynamicsLookupTableCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindPlantFreqPcontrolIEC validation for a WindDynamicsLookupTable
	 * 
	 * @param	command UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableCommand
	 */	
	public void validate( UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( command.getWindDynamicsLookupTableId(), "UnAssignWindPlantFreqPcontrolIECFromWindDynamicsLookupTableCommand identifier should not be null" );
	}
	/**
	 * handles assign WindContPType3IEC validation for a WindDynamicsLookupTable
	 * 
	 * @param	command AssignWindContPType3IECToWindDynamicsLookupTableCommand
	 */	
	public void validate( AssignWindContPType3IECToWindDynamicsLookupTableCommand command ) throws Exception {
		Assert.notNull( command, "AssignWindContPType3IECToWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( command.getWindDynamicsLookupTableId(), "AssignWindContPType3IECToWindDynamicsLookupTableCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignWindContPType3IECToWindDynamicsLookupTableCommand assignment should not be null" );
	}

	/**
	 * handles unassign WindContPType3IEC validation for a WindDynamicsLookupTable
	 * 
	 * @param	command UnAssignWindContPType3IECFromWindDynamicsLookupTableCommand
	 */	
	public void validate( UnAssignWindContPType3IECFromWindDynamicsLookupTableCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignWindContPType3IECFromWindDynamicsLookupTableCommand should not be null" );
		Assert.notNull( command.getWindDynamicsLookupTableId(), "UnAssignWindContPType3IECFromWindDynamicsLookupTableCommand identifier should not be null" );
	}


}
