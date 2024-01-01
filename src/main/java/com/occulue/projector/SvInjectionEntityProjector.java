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
 * Projector for SvInjection as outlined for the CQRS pattern.
 * 
 * Commands are handled by SvInjectionAggregate
 * 
 * @author your_name_here
 *
 */
@Component("svInjection-entity-projector")
public class SvInjectionEntityProjector {
		
	// core constructor
	public SvInjectionEntityProjector(SvInjectionRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a SvInjection
	 * 
     * @param	entity SvInjection
     */
    public SvInjection create( SvInjection entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a SvInjection
	 * 
     * @param	entity SvInjection
     */
    public SvInjection update( SvInjection entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a SvInjection
	 * 
     * @param	id		UUID
     */
    public SvInjection delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    SvInjection entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a PInjection
     * 
     * @param	parentId	UUID
     * @param	assignment 	ActivePower 
     * @return	SvInjection
     */
    public SvInjection assignPInjection( UUID parentId, ActivePower assignment ) {
	    LOGGER.info("assigning PInjection as " + assignment.toString() );

	    SvInjection parentEntity = repository.findById( parentId ).get();
	    assignment = ActivePowerProjector.find(assignment.getActivePowerId());
	    
	    // ------------------------------------------
		// assign the PInjection to the parent entity
		// ------------------------------------------ 
	    parentEntity.setPInjection( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the PInjection
	 * 
	 * @param	parentId		UUID
	 * @return	SvInjection
	 */
	public SvInjection unAssignPInjection( UUID parentId ) {
		SvInjection parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning PInjection on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the PInjection on the parent entithy
		// ------------------------------------------     
	    parentEntity.setPInjection(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a QInjection
     * 
     * @param	parentId	UUID
     * @param	assignment 	ReactivePower 
     * @return	SvInjection
     */
    public SvInjection assignQInjection( UUID parentId, ReactivePower assignment ) {
	    LOGGER.info("assigning QInjection as " + assignment.toString() );

	    SvInjection parentEntity = repository.findById( parentId ).get();
	    assignment = ReactivePowerProjector.find(assignment.getReactivePowerId());
	    
	    // ------------------------------------------
		// assign the QInjection to the parent entity
		// ------------------------------------------ 
	    parentEntity.setQInjection( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the QInjection
	 * 
	 * @param	parentId		UUID
	 * @return	SvInjection
	 */
	public SvInjection unAssignQInjection( UUID parentId ) {
		SvInjection parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning QInjection on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the QInjection on the parent entithy
		// ------------------------------------------     
	    parentEntity.setQInjection(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a TopologicalNode
     * 
     * @param	parentId	UUID
     * @param	assignment 	TopologicalNode 
     * @return	SvInjection
     */
    public SvInjection assignTopologicalNode( UUID parentId, TopologicalNode assignment ) {
	    LOGGER.info("assigning TopologicalNode as " + assignment.toString() );

	    SvInjection parentEntity = repository.findById( parentId ).get();
	    assignment = TopologicalNodeProjector.find(assignment.getTopologicalNodeId());
	    
	    // ------------------------------------------
		// assign the TopologicalNode to the parent entity
		// ------------------------------------------ 
	    parentEntity.setTopologicalNode( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the TopologicalNode
	 * 
	 * @param	parentId		UUID
	 * @return	SvInjection
	 */
	public SvInjection unAssignTopologicalNode( UUID parentId ) {
		SvInjection parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning TopologicalNode on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the TopologicalNode on the parent entithy
		// ------------------------------------------     
	    parentEntity.setTopologicalNode(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the SvInjection via an FindSvInjectionQuery
     * @return 	query	FindSvInjectionQuery
     */
    @SuppressWarnings("unused")
    public SvInjection find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a SvInjection - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all SvInjections
     *
     * @param	query	FindAllSvInjectionQuery 
     * @return 	List<SvInjection> 
     */
    @SuppressWarnings("unused")
    public List<SvInjection> findAll( FindAllSvInjectionQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all SvInjection - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final SvInjectionRepository repository;
    @Autowired
	@Qualifier(value = "activePower-entity-projector")
	ActivePowerEntityProjector ActivePowerProjector;
    @Autowired
	@Qualifier(value = "reactivePower-entity-projector")
	ReactivePowerEntityProjector ReactivePowerProjector;
    @Autowired
	@Qualifier(value = "topologicalNode-entity-projector")
	TopologicalNodeEntityProjector TopologicalNodeProjector;

    private static final Logger LOGGER 	= Logger.getLogger(SvInjectionEntityProjector.class.getName());

}
