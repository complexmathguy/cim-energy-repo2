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
 * Projector for ProprietaryParameterDynamics as outlined for the CQRS pattern.
 * 
 * Commands are handled by ProprietaryParameterDynamicsAggregate
 * 
 * @author your_name_here
 *
 */
@Component("proprietaryParameterDynamics-entity-projector")
public class ProprietaryParameterDynamicsEntityProjector {
		
	// core constructor
	public ProprietaryParameterDynamicsEntityProjector(ProprietaryParameterDynamicsRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a ProprietaryParameterDynamics
	 * 
     * @param	entity ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics create( ProprietaryParameterDynamics entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a ProprietaryParameterDynamics
	 * 
     * @param	entity ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics update( ProprietaryParameterDynamics entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a ProprietaryParameterDynamics
	 * 
     * @param	id		UUID
     */
    public ProprietaryParameterDynamics delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    ProprietaryParameterDynamics entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a BooleanParameterValue
     * 
     * @param	parentId	UUID
     * @param	assignment 	BooleanProxy 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignBooleanParameterValue( UUID parentId, BooleanProxy assignment ) {
	    LOGGER.info("assigning BooleanParameterValue as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = BooleanProxyProjector.find(assignment.getBooleanProxyId());
	    
	    // ------------------------------------------
		// assign the BooleanParameterValue to the parent entity
		// ------------------------------------------ 
	    parentEntity.setBooleanParameterValue( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the BooleanParameterValue
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignBooleanParameterValue( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning BooleanParameterValue on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the BooleanParameterValue on the parent entithy
		// ------------------------------------------     
	    parentEntity.setBooleanParameterValue(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a FloatParameterValue
     * 
     * @param	parentId	UUID
     * @param	assignment 	Simple_Float 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignFloatParameterValue( UUID parentId, Simple_Float assignment ) {
	    LOGGER.info("assigning FloatParameterValue as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = Simple_FloatProjector.find(assignment.getSimple_FloatId());
	    
	    // ------------------------------------------
		// assign the FloatParameterValue to the parent entity
		// ------------------------------------------ 
	    parentEntity.setFloatParameterValue( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the FloatParameterValue
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignFloatParameterValue( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning FloatParameterValue on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the FloatParameterValue on the parent entithy
		// ------------------------------------------     
	    parentEntity.setFloatParameterValue(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a IntegerParameterValue
     * 
     * @param	parentId	UUID
     * @param	assignment 	IntegerProxy 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignIntegerParameterValue( UUID parentId, IntegerProxy assignment ) {
	    LOGGER.info("assigning IntegerParameterValue as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = IntegerProxyProjector.find(assignment.getIntegerProxyId());
	    
	    // ------------------------------------------
		// assign the IntegerParameterValue to the parent entity
		// ------------------------------------------ 
	    parentEntity.setIntegerParameterValue( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the IntegerParameterValue
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignIntegerParameterValue( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning IntegerParameterValue on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the IntegerParameterValue on the parent entithy
		// ------------------------------------------     
	    parentEntity.setIntegerParameterValue(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a ParameterNumber
     * 
     * @param	parentId	UUID
     * @param	assignment 	IntegerProxy 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignParameterNumber( UUID parentId, IntegerProxy assignment ) {
	    LOGGER.info("assigning ParameterNumber as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = IntegerProxyProjector.find(assignment.getIntegerProxyId());
	    
	    // ------------------------------------------
		// assign the ParameterNumber to the parent entity
		// ------------------------------------------ 
	    parentEntity.setParameterNumber( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the ParameterNumber
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignParameterNumber( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning ParameterNumber on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the ParameterNumber on the parent entithy
		// ------------------------------------------     
	    parentEntity.setParameterNumber(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a PFVArControllerType1UserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	PFVArControllerType1UserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignPFVArControllerType1UserDefined( UUID parentId, PFVArControllerType1UserDefined assignment ) {
	    LOGGER.info("assigning PFVArControllerType1UserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = PFVArControllerType1UserDefinedProjector.find(assignment.getPFVArControllerType1UserDefinedId());
	    
	    // ------------------------------------------
		// assign the PFVArControllerType1UserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setPFVArControllerType1UserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the PFVArControllerType1UserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignPFVArControllerType1UserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning PFVArControllerType1UserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the PFVArControllerType1UserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setPFVArControllerType1UserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a VoltageCompensatorUserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	VoltageCompensatorUserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignVoltageCompensatorUserDefined( UUID parentId, VoltageCompensatorUserDefined assignment ) {
	    LOGGER.info("assigning VoltageCompensatorUserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = VoltageCompensatorUserDefinedProjector.find(assignment.getVoltageCompensatorUserDefinedId());
	    
	    // ------------------------------------------
		// assign the VoltageCompensatorUserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setVoltageCompensatorUserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the VoltageCompensatorUserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignVoltageCompensatorUserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning VoltageCompensatorUserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the VoltageCompensatorUserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setVoltageCompensatorUserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a MechanicalLoadUserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	MechanicalLoadUserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignMechanicalLoadUserDefined( UUID parentId, MechanicalLoadUserDefined assignment ) {
	    LOGGER.info("assigning MechanicalLoadUserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = MechanicalLoadUserDefinedProjector.find(assignment.getMechanicalLoadUserDefinedId());
	    
	    // ------------------------------------------
		// assign the MechanicalLoadUserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setMechanicalLoadUserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the MechanicalLoadUserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignMechanicalLoadUserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning MechanicalLoadUserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the MechanicalLoadUserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setMechanicalLoadUserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a ExcitationSystemUserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	ExcitationSystemUserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignExcitationSystemUserDefined( UUID parentId, ExcitationSystemUserDefined assignment ) {
	    LOGGER.info("assigning ExcitationSystemUserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = ExcitationSystemUserDefinedProjector.find(assignment.getExcitationSystemUserDefinedId());
	    
	    // ------------------------------------------
		// assign the ExcitationSystemUserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setExcitationSystemUserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the ExcitationSystemUserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignExcitationSystemUserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning ExcitationSystemUserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the ExcitationSystemUserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setExcitationSystemUserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a AsynchronousMachineUserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	AsynchronousMachineUserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignAsynchronousMachineUserDefined( UUID parentId, AsynchronousMachineUserDefined assignment ) {
	    LOGGER.info("assigning AsynchronousMachineUserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = AsynchronousMachineUserDefinedProjector.find(assignment.getAsynchronousMachineUserDefinedId());
	    
	    // ------------------------------------------
		// assign the AsynchronousMachineUserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setAsynchronousMachineUserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the AsynchronousMachineUserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignAsynchronousMachineUserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning AsynchronousMachineUserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the AsynchronousMachineUserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setAsynchronousMachineUserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a DiscontinuousExcitationControlUserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	DiscontinuousExcitationControlUserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignDiscontinuousExcitationControlUserDefined( UUID parentId, DiscontinuousExcitationControlUserDefined assignment ) {
	    LOGGER.info("assigning DiscontinuousExcitationControlUserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = DiscontinuousExcitationControlUserDefinedProjector.find(assignment.getDiscontinuousExcitationControlUserDefinedId());
	    
	    // ------------------------------------------
		// assign the DiscontinuousExcitationControlUserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setDiscontinuousExcitationControlUserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the DiscontinuousExcitationControlUserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignDiscontinuousExcitationControlUserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning DiscontinuousExcitationControlUserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the DiscontinuousExcitationControlUserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setDiscontinuousExcitationControlUserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a TurbineGovernorUserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	TurbineGovernorUserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignTurbineGovernorUserDefined( UUID parentId, TurbineGovernorUserDefined assignment ) {
	    LOGGER.info("assigning TurbineGovernorUserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = TurbineGovernorUserDefinedProjector.find(assignment.getTurbineGovernorUserDefinedId());
	    
	    // ------------------------------------------
		// assign the TurbineGovernorUserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setTurbineGovernorUserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the TurbineGovernorUserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignTurbineGovernorUserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning TurbineGovernorUserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the TurbineGovernorUserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setTurbineGovernorUserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a VoltageAdjusterUserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	VoltageAdjusterUserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignVoltageAdjusterUserDefined( UUID parentId, VoltageAdjusterUserDefined assignment ) {
	    LOGGER.info("assigning VoltageAdjusterUserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = VoltageAdjusterUserDefinedProjector.find(assignment.getVoltageAdjusterUserDefinedId());
	    
	    // ------------------------------------------
		// assign the VoltageAdjusterUserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setVoltageAdjusterUserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the VoltageAdjusterUserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignVoltageAdjusterUserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning VoltageAdjusterUserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the VoltageAdjusterUserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setVoltageAdjusterUserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a SynchronousMachineUserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	SynchronousMachineUserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignSynchronousMachineUserDefined( UUID parentId, SynchronousMachineUserDefined assignment ) {
	    LOGGER.info("assigning SynchronousMachineUserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = SynchronousMachineUserDefinedProjector.find(assignment.getSynchronousMachineUserDefinedId());
	    
	    // ------------------------------------------
		// assign the SynchronousMachineUserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setSynchronousMachineUserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the SynchronousMachineUserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignSynchronousMachineUserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning SynchronousMachineUserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the SynchronousMachineUserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setSynchronousMachineUserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a UnderexcitationLimiterUserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	UnderexcitationLimiterUserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignUnderexcitationLimiterUserDefined( UUID parentId, UnderexcitationLimiterUserDefined assignment ) {
	    LOGGER.info("assigning UnderexcitationLimiterUserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = UnderexcitationLimiterUserDefinedProjector.find(assignment.getUnderexcitationLimiterUserDefinedId());
	    
	    // ------------------------------------------
		// assign the UnderexcitationLimiterUserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setUnderexcitationLimiterUserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the UnderexcitationLimiterUserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignUnderexcitationLimiterUserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning UnderexcitationLimiterUserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the UnderexcitationLimiterUserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setUnderexcitationLimiterUserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a TurbineLoadControllerUserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	TurbineLoadControllerUserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignTurbineLoadControllerUserDefined( UUID parentId, TurbineLoadControllerUserDefined assignment ) {
	    LOGGER.info("assigning TurbineLoadControllerUserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = TurbineLoadControllerUserDefinedProjector.find(assignment.getTurbineLoadControllerUserDefinedId());
	    
	    // ------------------------------------------
		// assign the TurbineLoadControllerUserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setTurbineLoadControllerUserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the TurbineLoadControllerUserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignTurbineLoadControllerUserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning TurbineLoadControllerUserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the TurbineLoadControllerUserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setTurbineLoadControllerUserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a OverexcitationLimiterUserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	OverexcitationLimiterUserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignOverexcitationLimiterUserDefined( UUID parentId, OverexcitationLimiterUserDefined assignment ) {
	    LOGGER.info("assigning OverexcitationLimiterUserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = OverexcitationLimiterUserDefinedProjector.find(assignment.getOverexcitationLimiterUserDefinedId());
	    
	    // ------------------------------------------
		// assign the OverexcitationLimiterUserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setOverexcitationLimiterUserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the OverexcitationLimiterUserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignOverexcitationLimiterUserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning OverexcitationLimiterUserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the OverexcitationLimiterUserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setOverexcitationLimiterUserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a PowerSystemStabilizerUserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	PowerSystemStabilizerUserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignPowerSystemStabilizerUserDefined( UUID parentId, PowerSystemStabilizerUserDefined assignment ) {
	    LOGGER.info("assigning PowerSystemStabilizerUserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = PowerSystemStabilizerUserDefinedProjector.find(assignment.getPowerSystemStabilizerUserDefinedId());
	    
	    // ------------------------------------------
		// assign the PowerSystemStabilizerUserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setPowerSystemStabilizerUserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the PowerSystemStabilizerUserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignPowerSystemStabilizerUserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning PowerSystemStabilizerUserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the PowerSystemStabilizerUserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setPowerSystemStabilizerUserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a LoadUserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	LoadUserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignLoadUserDefined( UUID parentId, LoadUserDefined assignment ) {
	    LOGGER.info("assigning LoadUserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = LoadUserDefinedProjector.find(assignment.getLoadUserDefinedId());
	    
	    // ------------------------------------------
		// assign the LoadUserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setLoadUserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the LoadUserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignLoadUserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning LoadUserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the LoadUserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setLoadUserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a PFVArControllerType2UserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	PFVArControllerType2UserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignPFVArControllerType2UserDefined( UUID parentId, PFVArControllerType2UserDefined assignment ) {
	    LOGGER.info("assigning PFVArControllerType2UserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = PFVArControllerType2UserDefinedProjector.find(assignment.getPFVArControllerType2UserDefinedId());
	    
	    // ------------------------------------------
		// assign the PFVArControllerType2UserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setPFVArControllerType2UserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the PFVArControllerType2UserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignPFVArControllerType2UserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning PFVArControllerType2UserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the PFVArControllerType2UserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setPFVArControllerType2UserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a WindType3or4UserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindType3or4UserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignWindType3or4UserDefined( UUID parentId, WindType3or4UserDefined assignment ) {
	    LOGGER.info("assigning WindType3or4UserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = WindType3or4UserDefinedProjector.find(assignment.getWindType3or4UserDefinedId());
	    
	    // ------------------------------------------
		// assign the WindType3or4UserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindType3or4UserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindType3or4UserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignWindType3or4UserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindType3or4UserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindType3or4UserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindType3or4UserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a WindPlantUserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindPlantUserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignWindPlantUserDefined( UUID parentId, WindPlantUserDefined assignment ) {
	    LOGGER.info("assigning WindPlantUserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = WindPlantUserDefinedProjector.find(assignment.getWindPlantUserDefinedId());
	    
	    // ------------------------------------------
		// assign the WindPlantUserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindPlantUserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindPlantUserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignWindPlantUserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindPlantUserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindPlantUserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindPlantUserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a WindType1or2UserDefined
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindType1or2UserDefined 
     * @return	ProprietaryParameterDynamics
     */
    public ProprietaryParameterDynamics assignWindType1or2UserDefined( UUID parentId, WindType1or2UserDefined assignment ) {
	    LOGGER.info("assigning WindType1or2UserDefined as " + assignment.toString() );

	    ProprietaryParameterDynamics parentEntity = repository.findById( parentId ).get();
	    assignment = WindType1or2UserDefinedProjector.find(assignment.getWindType1or2UserDefinedId());
	    
	    // ------------------------------------------
		// assign the WindType1or2UserDefined to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindType1or2UserDefined( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindType1or2UserDefined
	 * 
	 * @param	parentId		UUID
	 * @return	ProprietaryParameterDynamics
	 */
	public ProprietaryParameterDynamics unAssignWindType1or2UserDefined( UUID parentId ) {
		ProprietaryParameterDynamics parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindType1or2UserDefined on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindType1or2UserDefined on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindType1or2UserDefined(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the ProprietaryParameterDynamics via an FindProprietaryParameterDynamicsQuery
     * @return 	query	FindProprietaryParameterDynamicsQuery
     */
    @SuppressWarnings("unused")
    public ProprietaryParameterDynamics find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a ProprietaryParameterDynamics - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all ProprietaryParameterDynamicss
     *
     * @param	query	FindAllProprietaryParameterDynamicsQuery 
     * @return 	List<ProprietaryParameterDynamics> 
     */
    @SuppressWarnings("unused")
    public List<ProprietaryParameterDynamics> findAll( FindAllProprietaryParameterDynamicsQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all ProprietaryParameterDynamics - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final ProprietaryParameterDynamicsRepository repository;
    @Autowired
	@Qualifier(value = "booleanProxy-entity-projector")
	BooleanProxyEntityProjector BooleanProxyProjector;
    @Autowired
	@Qualifier(value = "simple_Float-entity-projector")
	Simple_FloatEntityProjector Simple_FloatProjector;
    @Autowired
	@Qualifier(value = "integerProxy-entity-projector")
	IntegerProxyEntityProjector IntegerProxyProjector;
    @Autowired
	@Qualifier(value = "pFVArControllerType1UserDefined-entity-projector")
	PFVArControllerType1UserDefinedEntityProjector PFVArControllerType1UserDefinedProjector;
    @Autowired
	@Qualifier(value = "voltageCompensatorUserDefined-entity-projector")
	VoltageCompensatorUserDefinedEntityProjector VoltageCompensatorUserDefinedProjector;
    @Autowired
	@Qualifier(value = "mechanicalLoadUserDefined-entity-projector")
	MechanicalLoadUserDefinedEntityProjector MechanicalLoadUserDefinedProjector;
    @Autowired
	@Qualifier(value = "excitationSystemUserDefined-entity-projector")
	ExcitationSystemUserDefinedEntityProjector ExcitationSystemUserDefinedProjector;
    @Autowired
	@Qualifier(value = "asynchronousMachineUserDefined-entity-projector")
	AsynchronousMachineUserDefinedEntityProjector AsynchronousMachineUserDefinedProjector;
    @Autowired
	@Qualifier(value = "discontinuousExcitationControlUserDefined-entity-projector")
	DiscontinuousExcitationControlUserDefinedEntityProjector DiscontinuousExcitationControlUserDefinedProjector;
    @Autowired
	@Qualifier(value = "turbineGovernorUserDefined-entity-projector")
	TurbineGovernorUserDefinedEntityProjector TurbineGovernorUserDefinedProjector;
    @Autowired
	@Qualifier(value = "voltageAdjusterUserDefined-entity-projector")
	VoltageAdjusterUserDefinedEntityProjector VoltageAdjusterUserDefinedProjector;
    @Autowired
	@Qualifier(value = "synchronousMachineUserDefined-entity-projector")
	SynchronousMachineUserDefinedEntityProjector SynchronousMachineUserDefinedProjector;
    @Autowired
	@Qualifier(value = "underexcitationLimiterUserDefined-entity-projector")
	UnderexcitationLimiterUserDefinedEntityProjector UnderexcitationLimiterUserDefinedProjector;
    @Autowired
	@Qualifier(value = "turbineLoadControllerUserDefined-entity-projector")
	TurbineLoadControllerUserDefinedEntityProjector TurbineLoadControllerUserDefinedProjector;
    @Autowired
	@Qualifier(value = "overexcitationLimiterUserDefined-entity-projector")
	OverexcitationLimiterUserDefinedEntityProjector OverexcitationLimiterUserDefinedProjector;
    @Autowired
	@Qualifier(value = "powerSystemStabilizerUserDefined-entity-projector")
	PowerSystemStabilizerUserDefinedEntityProjector PowerSystemStabilizerUserDefinedProjector;
    @Autowired
	@Qualifier(value = "loadUserDefined-entity-projector")
	LoadUserDefinedEntityProjector LoadUserDefinedProjector;
    @Autowired
	@Qualifier(value = "pFVArControllerType2UserDefined-entity-projector")
	PFVArControllerType2UserDefinedEntityProjector PFVArControllerType2UserDefinedProjector;
    @Autowired
	@Qualifier(value = "windType3or4UserDefined-entity-projector")
	WindType3or4UserDefinedEntityProjector WindType3or4UserDefinedProjector;
    @Autowired
	@Qualifier(value = "windPlantUserDefined-entity-projector")
	WindPlantUserDefinedEntityProjector WindPlantUserDefinedProjector;
    @Autowired
	@Qualifier(value = "windType1or2UserDefined-entity-projector")
	WindType1or2UserDefinedEntityProjector WindType1or2UserDefinedProjector;

    private static final Logger LOGGER 	= Logger.getLogger(ProprietaryParameterDynamicsEntityProjector.class.getName());

}
