/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.projector;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.repository.*;

/**
 * Projector for PU as outlined for the CQRS pattern.
 * 
 * Commands are handled by PUAggregate
 * 
 * @author your_name_here
 *
 */
@Component("pU-entity-projector")
public class PUEntityProjector {
		
	// core constructor
	public PUEntityProjector(PURepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a PU
	 * 
     * @param	entity PU
     */
    public PU create( PU entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a PU
	 * 
     * @param	entity PU
     */
    public PU update( PU entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a PU
	 * 
     * @param	id		UUID
     */
    public PU delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    PU entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Value
     * 
     * @param	parentId	UUID
     * @param	assignment 	FloatProxy 
     * @return	PU
     */
    public PU assignValue( UUID parentId, FloatProxy assignment ) {
	    LOGGER.info("assigning Value as " + assignment.toString() );

	    PU parentEntity = repository.findById( parentId ).get();
	    assignment = FloatProxyProjector.find(assignment.getFloatProxyId());
	    
	    // ------------------------------------------
		// assign the Value to the parent entity
		// ------------------------------------------ 
	    parentEntity.setValue( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Value
	 * 
	 * @param	parentId		UUID
	 * @return	PU
	 */
	public PU unAssignValue( UUID parentId ) {
		PU parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Value on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Value on the parent entithy
		// ------------------------------------------     
	    parentEntity.setValue(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the PU via an FindPUQuery
     * @return 	query	FindPUQuery
     */
    @SuppressWarnings("unused")
    public PU find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a PU - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all PUs
     *
     * @param	query	FindAllPUQuery 
     * @return 	List<PU> 
     */
    @SuppressWarnings("unused")
    public List<PU> findAll( FindAllPUQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all PU - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final PURepository repository;
    @Autowired
	@Qualifier(value = "floatProxy-entity-projector")
	FloatProxyEntityProjector FloatProxyProjector;
    @Autowired
	@Qualifier(value = "externalNetworkInjection-entity-projector")
	ExternalNetworkInjectionEntityProjector ExternalNetworkInjectionProjector;
    @Autowired
	@Qualifier(value = "synchronousMachine-entity-projector")
	SynchronousMachineEntityProjector SynchronousMachineProjector;
    @Autowired
	@Qualifier(value = "rotatingMachineDynamics-entity-projector")
	RotatingMachineDynamicsEntityProjector RotatingMachineDynamicsProjector;
    @Autowired
	@Qualifier(value = "synchronousMachineTimeConstantReactance-entity-projector")
	SynchronousMachineTimeConstantReactanceEntityProjector SynchronousMachineTimeConstantReactanceProjector;
    @Autowired
	@Qualifier(value = "synchronousMachineEquivalentCircuit-entity-projector")
	SynchronousMachineEquivalentCircuitEntityProjector SynchronousMachineEquivalentCircuitProjector;
    @Autowired
	@Qualifier(value = "asynchronousMachineTimeConstantReactance-entity-projector")
	AsynchronousMachineTimeConstantReactanceEntityProjector AsynchronousMachineTimeConstantReactanceProjector;
    @Autowired
	@Qualifier(value = "asynchronousMachineEquivalentCircuit-entity-projector")
	AsynchronousMachineEquivalentCircuitEntityProjector AsynchronousMachineEquivalentCircuitProjector;
    @Autowired
	@Qualifier(value = "govHydroIEEE0-entity-projector")
	GovHydroIEEE0EntityProjector GovHydroIEEE0Projector;
    @Autowired
	@Qualifier(value = "govHydroIEEE2-entity-projector")
	GovHydroIEEE2EntityProjector GovHydroIEEE2Projector;
    @Autowired
	@Qualifier(value = "govCT1-entity-projector")
	GovCT1EntityProjector GovCT1Projector;
    @Autowired
	@Qualifier(value = "govCT2-entity-projector")
	GovCT2EntityProjector GovCT2Projector;
    @Autowired
	@Qualifier(value = "govGAST-entity-projector")
	GovGASTEntityProjector GovGASTProjector;
    @Autowired
	@Qualifier(value = "govGAST1-entity-projector")
	GovGAST1EntityProjector GovGAST1Projector;
    @Autowired
	@Qualifier(value = "govGAST2-entity-projector")
	GovGAST2EntityProjector GovGAST2Projector;
    @Autowired
	@Qualifier(value = "govGAST3-entity-projector")
	GovGAST3EntityProjector GovGAST3Projector;
    @Autowired
	@Qualifier(value = "govGAST4-entity-projector")
	GovGAST4EntityProjector GovGAST4Projector;
    @Autowired
	@Qualifier(value = "govGASTWD-entity-projector")
	GovGASTWDEntityProjector GovGASTWDProjector;
    @Autowired
	@Qualifier(value = "govHydro1-entity-projector")
	GovHydro1EntityProjector GovHydro1Projector;
    @Autowired
	@Qualifier(value = "govHydro3-entity-projector")
	GovHydro3EntityProjector GovHydro3Projector;
    @Autowired
	@Qualifier(value = "govHydro4-entity-projector")
	GovHydro4EntityProjector GovHydro4Projector;
    @Autowired
	@Qualifier(value = "govHydroFrancis-entity-projector")
	GovHydroFrancisEntityProjector GovHydroFrancisProjector;
    @Autowired
	@Qualifier(value = "govHydroPelton-entity-projector")
	GovHydroPeltonEntityProjector GovHydroPeltonProjector;
    @Autowired
	@Qualifier(value = "govHydroPID2-entity-projector")
	GovHydroPID2EntityProjector GovHydroPID2Projector;
    @Autowired
	@Qualifier(value = "govHydroWEH-entity-projector")
	GovHydroWEHEntityProjector GovHydroWEHProjector;
    @Autowired
	@Qualifier(value = "govHydroWPID-entity-projector")
	GovHydroWPIDEntityProjector GovHydroWPIDProjector;
    @Autowired
	@Qualifier(value = "govSteam0-entity-projector")
	GovSteam0EntityProjector GovSteam0Projector;
    @Autowired
	@Qualifier(value = "govSteam2-entity-projector")
	GovSteam2EntityProjector GovSteam2Projector;
    @Autowired
	@Qualifier(value = "govSteamCC-entity-projector")
	GovSteamCCEntityProjector GovSteamCCProjector;
    @Autowired
	@Qualifier(value = "govSteamEU-entity-projector")
	GovSteamEUEntityProjector GovSteamEUProjector;
    @Autowired
	@Qualifier(value = "govSteamFV3-entity-projector")
	GovSteamFV3EntityProjector GovSteamFV3Projector;
    @Autowired
	@Qualifier(value = "govSteamFV4-entity-projector")
	GovSteamFV4EntityProjector GovSteamFV4Projector;
    @Autowired
	@Qualifier(value = "turbLCFB1-entity-projector")
	TurbLCFB1EntityProjector TurbLCFB1Projector;
    @Autowired
	@Qualifier(value = "excIEEEAC1A-entity-projector")
	ExcIEEEAC1AEntityProjector ExcIEEEAC1AProjector;
    @Autowired
	@Qualifier(value = "excIEEEAC2A-entity-projector")
	ExcIEEEAC2AEntityProjector ExcIEEEAC2AProjector;
    @Autowired
	@Qualifier(value = "excIEEEAC3A-entity-projector")
	ExcIEEEAC3AEntityProjector ExcIEEEAC3AProjector;
    @Autowired
	@Qualifier(value = "excIEEEAC4A-entity-projector")
	ExcIEEEAC4AEntityProjector ExcIEEEAC4AProjector;
    @Autowired
	@Qualifier(value = "excIEEEAC5A-entity-projector")
	ExcIEEEAC5AEntityProjector ExcIEEEAC5AProjector;
    @Autowired
	@Qualifier(value = "excIEEEAC6A-entity-projector")
	ExcIEEEAC6AEntityProjector ExcIEEEAC6AProjector;
    @Autowired
	@Qualifier(value = "excIEEEAC7B-entity-projector")
	ExcIEEEAC7BEntityProjector ExcIEEEAC7BProjector;
    @Autowired
	@Qualifier(value = "excIEEEDC2A-entity-projector")
	ExcIEEEDC2AEntityProjector ExcIEEEDC2AProjector;
    @Autowired
	@Qualifier(value = "excIEEEDC3A-entity-projector")
	ExcIEEEDC3AEntityProjector ExcIEEEDC3AProjector;
    @Autowired
	@Qualifier(value = "excIEEEST1A-entity-projector")
	ExcIEEEST1AEntityProjector ExcIEEEST1AProjector;
    @Autowired
	@Qualifier(value = "excIEEEST2A-entity-projector")
	ExcIEEEST2AEntityProjector ExcIEEEST2AProjector;
    @Autowired
	@Qualifier(value = "excIEEEST3A-entity-projector")
	ExcIEEEST3AEntityProjector ExcIEEEST3AProjector;
    @Autowired
	@Qualifier(value = "excIEEEST4B-entity-projector")
	ExcIEEEST4BEntityProjector ExcIEEEST4BProjector;
    @Autowired
	@Qualifier(value = "excIEEEST6B-entity-projector")
	ExcIEEEST6BEntityProjector ExcIEEEST6BProjector;
    @Autowired
	@Qualifier(value = "excAC1A-entity-projector")
	ExcAC1AEntityProjector ExcAC1AProjector;
    @Autowired
	@Qualifier(value = "excAC2A-entity-projector")
	ExcAC2AEntityProjector ExcAC2AProjector;
    @Autowired
	@Qualifier(value = "excAC3A-entity-projector")
	ExcAC3AEntityProjector ExcAC3AProjector;
    @Autowired
	@Qualifier(value = "excAC8B-entity-projector")
	ExcAC8BEntityProjector ExcAC8BProjector;
    @Autowired
	@Qualifier(value = "excANS-entity-projector")
	ExcANSEntityProjector ExcANSProjector;
    @Autowired
	@Qualifier(value = "excAVR1-entity-projector")
	ExcAVR1EntityProjector ExcAVR1Projector;
    @Autowired
	@Qualifier(value = "excAVR4-entity-projector")
	ExcAVR4EntityProjector ExcAVR4Projector;
    @Autowired
	@Qualifier(value = "excAVR5-entity-projector")
	ExcAVR5EntityProjector ExcAVR5Projector;
    @Autowired
	@Qualifier(value = "excAVR7-entity-projector")
	ExcAVR7EntityProjector ExcAVR7Projector;
    @Autowired
	@Qualifier(value = "excBBC-entity-projector")
	ExcBBCEntityProjector ExcBBCProjector;
    @Autowired
	@Qualifier(value = "excDC1A-entity-projector")
	ExcDC1AEntityProjector ExcDC1AProjector;
    @Autowired
	@Qualifier(value = "excDC3A1-entity-projector")
	ExcDC3A1EntityProjector ExcDC3A1Projector;
    @Autowired
	@Qualifier(value = "excELIN1-entity-projector")
	ExcELIN1EntityProjector ExcELIN1Projector;
    @Autowired
	@Qualifier(value = "excELIN2-entity-projector")
	ExcELIN2EntityProjector ExcELIN2Projector;
    @Autowired
	@Qualifier(value = "excHU-entity-projector")
	ExcHUEntityProjector ExcHUProjector;
    @Autowired
	@Qualifier(value = "excOEX3T-entity-projector")
	ExcOEX3TEntityProjector ExcOEX3TProjector;
    @Autowired
	@Qualifier(value = "excPIC-entity-projector")
	ExcPICEntityProjector ExcPICProjector;
    @Autowired
	@Qualifier(value = "excREXS-entity-projector")
	ExcREXSEntityProjector ExcREXSProjector;
    @Autowired
	@Qualifier(value = "excSK-entity-projector")
	ExcSKEntityProjector ExcSKProjector;
    @Autowired
	@Qualifier(value = "excST3A-entity-projector")
	ExcST3AEntityProjector ExcST3AProjector;
    @Autowired
	@Qualifier(value = "excST6B-entity-projector")
	ExcST6BEntityProjector ExcST6BProjector;
    @Autowired
	@Qualifier(value = "overexcLimIEEE-entity-projector")
	OverexcLimIEEEEntityProjector OverexcLimIEEEProjector;
    @Autowired
	@Qualifier(value = "overexcLim2-entity-projector")
	OverexcLim2EntityProjector OverexcLim2Projector;
    @Autowired
	@Qualifier(value = "overexcLimX1-entity-projector")
	OverexcLimX1EntityProjector OverexcLimX1Projector;
    @Autowired
	@Qualifier(value = "underexcLimIEEE1-entity-projector")
	UnderexcLimIEEE1EntityProjector UnderexcLimIEEE1Projector;
    @Autowired
	@Qualifier(value = "underexcLimIEEE2-entity-projector")
	UnderexcLimIEEE2EntityProjector UnderexcLimIEEE2Projector;
    @Autowired
	@Qualifier(value = "underexcLimX1-entity-projector")
	UnderexcLimX1EntityProjector UnderexcLimX1Projector;
    @Autowired
	@Qualifier(value = "underexcLimX2-entity-projector")
	UnderexcLimX2EntityProjector UnderexcLimX2Projector;
    @Autowired
	@Qualifier(value = "pssIEEE2B-entity-projector")
	PssIEEE2BEntityProjector PssIEEE2BProjector;
    @Autowired
	@Qualifier(value = "pssIEEE3B-entity-projector")
	PssIEEE3BEntityProjector PssIEEE3BProjector;
    @Autowired
	@Qualifier(value = "pssIEEE4B-entity-projector")
	PssIEEE4BEntityProjector PssIEEE4BProjector;
    @Autowired
	@Qualifier(value = "pss1-entity-projector")
	Pss1EntityProjector Pss1Projector;
    @Autowired
	@Qualifier(value = "pss1A-entity-projector")
	Pss1AEntityProjector Pss1AProjector;
    @Autowired
	@Qualifier(value = "pss2B-entity-projector")
	Pss2BEntityProjector Pss2BProjector;
    @Autowired
	@Qualifier(value = "pss2ST-entity-projector")
	Pss2STEntityProjector Pss2STProjector;
    @Autowired
	@Qualifier(value = "pss5-entity-projector")
	Pss5EntityProjector Pss5Projector;
    @Autowired
	@Qualifier(value = "pssELIN2-entity-projector")
	PssELIN2EntityProjector PssELIN2Projector;
    @Autowired
	@Qualifier(value = "pssPTIST1-entity-projector")
	PssPTIST1EntityProjector PssPTIST1Projector;
    @Autowired
	@Qualifier(value = "pssPTIST3-entity-projector")
	PssPTIST3EntityProjector PssPTIST3Projector;
    @Autowired
	@Qualifier(value = "pssSB4-entity-projector")
	PssSB4EntityProjector PssSB4Projector;
    @Autowired
	@Qualifier(value = "pssSH-entity-projector")
	PssSHEntityProjector PssSHProjector;
    @Autowired
	@Qualifier(value = "discExcContIEEEDEC1A-entity-projector")
	DiscExcContIEEEDEC1AEntityProjector DiscExcContIEEEDEC1AProjector;
    @Autowired
	@Qualifier(value = "discExcContIEEEDEC2A-entity-projector")
	DiscExcContIEEEDEC2AEntityProjector DiscExcContIEEEDEC2AProjector;
    @Autowired
	@Qualifier(value = "pFVArType1IEEEPFController-entity-projector")
	PFVArType1IEEEPFControllerEntityProjector PFVArType1IEEEPFControllerProjector;
    @Autowired
	@Qualifier(value = "pFVArType1IEEEVArController-entity-projector")
	PFVArType1IEEEVArControllerEntityProjector PFVArType1IEEEVArControllerProjector;
    @Autowired
	@Qualifier(value = "vAdjIEEE-entity-projector")
	VAdjIEEEEntityProjector VAdjIEEEProjector;
    @Autowired
	@Qualifier(value = "pFVArType2IEEEPFController-entity-projector")
	PFVArType2IEEEPFControllerEntityProjector PFVArType2IEEEPFControllerProjector;
    @Autowired
	@Qualifier(value = "pFVArType2IEEEVArController-entity-projector")
	PFVArType2IEEEVArControllerEntityProjector PFVArType2IEEEVArControllerProjector;
    @Autowired
	@Qualifier(value = "pFVArType2Common1-entity-projector")
	PFVArType2Common1EntityProjector PFVArType2Common1Projector;
    @Autowired
	@Qualifier(value = "vCompIEEEType1-entity-projector")
	VCompIEEEType1EntityProjector VCompIEEEType1Projector;
    @Autowired
	@Qualifier(value = "genICompensationForGenJ-entity-projector")
	GenICompensationForGenJEntityProjector GenICompensationForGenJProjector;
    @Autowired
	@Qualifier(value = "windAeroLinearIEC-entity-projector")
	WindAeroLinearIECEntityProjector WindAeroLinearIECProjector;
    @Autowired
	@Qualifier(value = "windContCurrLimIEC-entity-projector")
	WindContCurrLimIECEntityProjector WindContCurrLimIECProjector;
    @Autowired
	@Qualifier(value = "windContPType3IEC-entity-projector")
	WindContPType3IECEntityProjector WindContPType3IECProjector;
    @Autowired
	@Qualifier(value = "windContPitchAngleIEC-entity-projector")
	WindContPitchAngleIECEntityProjector WindContPitchAngleIECProjector;
    @Autowired
	@Qualifier(value = "windContQIEC-entity-projector")
	WindContQIECEntityProjector WindContQIECProjector;
    @Autowired
	@Qualifier(value = "windContRotorRIEC-entity-projector")
	WindContRotorRIECEntityProjector WindContRotorRIECProjector;
    @Autowired
	@Qualifier(value = "windGenTurbineType3IEC-entity-projector")
	WindGenTurbineType3IECEntityProjector WindGenTurbineType3IECProjector;
    @Autowired
	@Qualifier(value = "windGenType4IEC-entity-projector")
	WindGenType4IECEntityProjector WindGenType4IECProjector;
    @Autowired
	@Qualifier(value = "windMechIEC-entity-projector")
	WindMechIECEntityProjector WindMechIECProjector;
    @Autowired
	@Qualifier(value = "windPitchContEmulIEC-entity-projector")
	WindPitchContEmulIECEntityProjector WindPitchContEmulIECProjector;
    @Autowired
	@Qualifier(value = "windPlantFreqPcontrolIEC-entity-projector")
	WindPlantFreqPcontrolIECEntityProjector WindPlantFreqPcontrolIECProjector;
    @Autowired
	@Qualifier(value = "windPlantReactiveControlIEC-entity-projector")
	WindPlantReactiveControlIECEntityProjector WindPlantReactiveControlIECProjector;
    @Autowired
	@Qualifier(value = "windProtectionIEC-entity-projector")
	WindProtectionIECEntityProjector WindProtectionIECProjector;
    @Autowired
	@Qualifier(value = "loadMotor-entity-projector")
	LoadMotorEntityProjector LoadMotorProjector;

    private static final Logger LOGGER 	= Logger.getLogger(PUEntityProjector.class.getName());

}
