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
 * Projector for RemoteInputSignal as outlined for the CQRS pattern.
 * 
 * Commands are handled by RemoteInputSignalAggregate
 * 
 * @author your_name_here
 *
 */
@Component("remoteInputSignal-entity-projector")
public class RemoteInputSignalEntityProjector {
		
	// core constructor
	public RemoteInputSignalEntityProjector(RemoteInputSignalRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a RemoteInputSignal
	 * 
     * @param	entity RemoteInputSignal
     */
    public RemoteInputSignal create( RemoteInputSignal entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a RemoteInputSignal
	 * 
     * @param	entity RemoteInputSignal
     */
    public RemoteInputSignal update( RemoteInputSignal entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a RemoteInputSignal
	 * 
     * @param	id		UUID
     */
    public RemoteInputSignal delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    RemoteInputSignal entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Terminal
     * 
     * @param	parentId	UUID
     * @param	assignment 	Terminal 
     * @return	RemoteInputSignal
     */
    public RemoteInputSignal assignTerminal( UUID parentId, Terminal assignment ) {
	    LOGGER.info("assigning Terminal as " + assignment.toString() );

	    RemoteInputSignal parentEntity = repository.findById( parentId ).get();
	    assignment = TerminalProjector.find(assignment.getTerminalId());
	    
	    // ------------------------------------------
		// assign the Terminal to the parent entity
		// ------------------------------------------ 
	    parentEntity.setTerminal( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Terminal
	 * 
	 * @param	parentId		UUID
	 * @return	RemoteInputSignal
	 */
	public RemoteInputSignal unAssignTerminal( UUID parentId ) {
		RemoteInputSignal parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Terminal on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Terminal on the parent entithy
		// ------------------------------------------     
	    parentEntity.setTerminal(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a DiscontinuousExcitationControlDynamics
     * 
     * @param	parentId	UUID
     * @param	assignment 	DiscontinuousExcitationControlDynamics 
     * @return	RemoteInputSignal
     */
    public RemoteInputSignal assignDiscontinuousExcitationControlDynamics( UUID parentId, DiscontinuousExcitationControlDynamics assignment ) {
	    LOGGER.info("assigning DiscontinuousExcitationControlDynamics as " + assignment.toString() );

	    RemoteInputSignal parentEntity = repository.findById( parentId ).get();
	    assignment = DiscontinuousExcitationControlDynamicsProjector.find(assignment.getDiscontinuousExcitationControlDynamicsId());
	    
	    // ------------------------------------------
		// assign the DiscontinuousExcitationControlDynamics to the parent entity
		// ------------------------------------------ 
	    parentEntity.setDiscontinuousExcitationControlDynamics( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the DiscontinuousExcitationControlDynamics
	 * 
	 * @param	parentId		UUID
	 * @return	RemoteInputSignal
	 */
	public RemoteInputSignal unAssignDiscontinuousExcitationControlDynamics( UUID parentId ) {
		RemoteInputSignal parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning DiscontinuousExcitationControlDynamics on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the DiscontinuousExcitationControlDynamics on the parent entithy
		// ------------------------------------------     
	    parentEntity.setDiscontinuousExcitationControlDynamics(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a PowerSystemStabilizerDynamics
     * 
     * @param	parentId	UUID
     * @param	assignment 	PowerSystemStabilizerDynamics 
     * @return	RemoteInputSignal
     */
    public RemoteInputSignal assignPowerSystemStabilizerDynamics( UUID parentId, PowerSystemStabilizerDynamics assignment ) {
	    LOGGER.info("assigning PowerSystemStabilizerDynamics as " + assignment.toString() );

	    RemoteInputSignal parentEntity = repository.findById( parentId ).get();
	    assignment = PowerSystemStabilizerDynamicsProjector.find(assignment.getPowerSystemStabilizerDynamicsId());
	    
	    // ------------------------------------------
		// assign the PowerSystemStabilizerDynamics to the parent entity
		// ------------------------------------------ 
	    parentEntity.setPowerSystemStabilizerDynamics( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the PowerSystemStabilizerDynamics
	 * 
	 * @param	parentId		UUID
	 * @return	RemoteInputSignal
	 */
	public RemoteInputSignal unAssignPowerSystemStabilizerDynamics( UUID parentId ) {
		RemoteInputSignal parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning PowerSystemStabilizerDynamics on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the PowerSystemStabilizerDynamics on the parent entithy
		// ------------------------------------------     
	    parentEntity.setPowerSystemStabilizerDynamics(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a VoltageCompensatorDynamics
     * 
     * @param	parentId	UUID
     * @param	assignment 	VoltageCompensatorDynamics 
     * @return	RemoteInputSignal
     */
    public RemoteInputSignal assignVoltageCompensatorDynamics( UUID parentId, VoltageCompensatorDynamics assignment ) {
	    LOGGER.info("assigning VoltageCompensatorDynamics as " + assignment.toString() );

	    RemoteInputSignal parentEntity = repository.findById( parentId ).get();
	    assignment = VoltageCompensatorDynamicsProjector.find(assignment.getVoltageCompensatorDynamicsId());
	    
	    // ------------------------------------------
		// assign the VoltageCompensatorDynamics to the parent entity
		// ------------------------------------------ 
	    parentEntity.setVoltageCompensatorDynamics( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the VoltageCompensatorDynamics
	 * 
	 * @param	parentId		UUID
	 * @return	RemoteInputSignal
	 */
	public RemoteInputSignal unAssignVoltageCompensatorDynamics( UUID parentId ) {
		RemoteInputSignal parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning VoltageCompensatorDynamics on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the VoltageCompensatorDynamics on the parent entithy
		// ------------------------------------------     
	    parentEntity.setVoltageCompensatorDynamics(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a UnderexcitationLimiterDynamics
     * 
     * @param	parentId	UUID
     * @param	assignment 	UnderexcitationLimiterDynamics 
     * @return	RemoteInputSignal
     */
    public RemoteInputSignal assignUnderexcitationLimiterDynamics( UUID parentId, UnderexcitationLimiterDynamics assignment ) {
	    LOGGER.info("assigning UnderexcitationLimiterDynamics as " + assignment.toString() );

	    RemoteInputSignal parentEntity = repository.findById( parentId ).get();
	    assignment = UnderexcitationLimiterDynamicsProjector.find(assignment.getUnderexcitationLimiterDynamicsId());
	    
	    // ------------------------------------------
		// assign the UnderexcitationLimiterDynamics to the parent entity
		// ------------------------------------------ 
	    parentEntity.setUnderexcitationLimiterDynamics( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the UnderexcitationLimiterDynamics
	 * 
	 * @param	parentId		UUID
	 * @return	RemoteInputSignal
	 */
	public RemoteInputSignal unAssignUnderexcitationLimiterDynamics( UUID parentId ) {
		RemoteInputSignal parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning UnderexcitationLimiterDynamics on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the UnderexcitationLimiterDynamics on the parent entithy
		// ------------------------------------------     
	    parentEntity.setUnderexcitationLimiterDynamics(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a PFVArControllerType1Dynamics
     * 
     * @param	parentId	UUID
     * @param	assignment 	PFVArControllerType1Dynamics 
     * @return	RemoteInputSignal
     */
    public RemoteInputSignal assignPFVArControllerType1Dynamics( UUID parentId, PFVArControllerType1Dynamics assignment ) {
	    LOGGER.info("assigning PFVArControllerType1Dynamics as " + assignment.toString() );

	    RemoteInputSignal parentEntity = repository.findById( parentId ).get();
	    assignment = PFVArControllerType1DynamicsProjector.find(assignment.getPFVArControllerType1DynamicsId());
	    
	    // ------------------------------------------
		// assign the PFVArControllerType1Dynamics to the parent entity
		// ------------------------------------------ 
	    parentEntity.setPFVArControllerType1Dynamics( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the PFVArControllerType1Dynamics
	 * 
	 * @param	parentId		UUID
	 * @return	RemoteInputSignal
	 */
	public RemoteInputSignal unAssignPFVArControllerType1Dynamics( UUID parentId ) {
		RemoteInputSignal parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning PFVArControllerType1Dynamics on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the PFVArControllerType1Dynamics on the parent entithy
		// ------------------------------------------     
	    parentEntity.setPFVArControllerType1Dynamics(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the RemoteInputSignal via an FindRemoteInputSignalQuery
     * @return 	query	FindRemoteInputSignalQuery
     */
    @SuppressWarnings("unused")
    public RemoteInputSignal find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a RemoteInputSignal - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all RemoteInputSignals
     *
     * @param	query	FindAllRemoteInputSignalQuery 
     * @return 	List<RemoteInputSignal> 
     */
    @SuppressWarnings("unused")
    public List<RemoteInputSignal> findAll( FindAllRemoteInputSignalQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all RemoteInputSignal - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final RemoteInputSignalRepository repository;
    @Autowired
	@Qualifier(value = "terminal-entity-projector")
	TerminalEntityProjector TerminalProjector;
    @Autowired
	@Qualifier(value = "discontinuousExcitationControlDynamics-entity-projector")
	DiscontinuousExcitationControlDynamicsEntityProjector DiscontinuousExcitationControlDynamicsProjector;
    @Autowired
	@Qualifier(value = "powerSystemStabilizerDynamics-entity-projector")
	PowerSystemStabilizerDynamicsEntityProjector PowerSystemStabilizerDynamicsProjector;
    @Autowired
	@Qualifier(value = "voltageCompensatorDynamics-entity-projector")
	VoltageCompensatorDynamicsEntityProjector VoltageCompensatorDynamicsProjector;
    @Autowired
	@Qualifier(value = "underexcitationLimiterDynamics-entity-projector")
	UnderexcitationLimiterDynamicsEntityProjector UnderexcitationLimiterDynamicsProjector;
    @Autowired
	@Qualifier(value = "pFVArControllerType1Dynamics-entity-projector")
	PFVArControllerType1DynamicsEntityProjector PFVArControllerType1DynamicsProjector;
    @Autowired
	@Qualifier(value = "windPlantDynamics-entity-projector")
	WindPlantDynamicsEntityProjector WindPlantDynamicsProjector;

    private static final Logger LOGGER 	= Logger.getLogger(RemoteInputSignalEntityProjector.class.getName());

}
