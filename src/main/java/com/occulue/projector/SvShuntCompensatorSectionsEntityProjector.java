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
 * Projector for SvShuntCompensatorSections as outlined for the CQRS pattern.
 * 
 * Commands are handled by SvShuntCompensatorSectionsAggregate
 * 
 * @author your_name_here
 *
 */
@Component("svShuntCompensatorSections-entity-projector")
public class SvShuntCompensatorSectionsEntityProjector {
		
	// core constructor
	public SvShuntCompensatorSectionsEntityProjector(SvShuntCompensatorSectionsRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a SvShuntCompensatorSections
	 * 
     * @param	entity SvShuntCompensatorSections
     */
    public SvShuntCompensatorSections create( SvShuntCompensatorSections entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a SvShuntCompensatorSections
	 * 
     * @param	entity SvShuntCompensatorSections
     */
    public SvShuntCompensatorSections update( SvShuntCompensatorSections entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a SvShuntCompensatorSections
	 * 
     * @param	id		UUID
     */
    public SvShuntCompensatorSections delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    SvShuntCompensatorSections entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Sections
     * 
     * @param	parentId	UUID
     * @param	assignment 	Simple_Float 
     * @return	SvShuntCompensatorSections
     */
    public SvShuntCompensatorSections assignSections( UUID parentId, Simple_Float assignment ) {
	    LOGGER.info("assigning Sections as " + assignment.toString() );

	    SvShuntCompensatorSections parentEntity = repository.findById( parentId ).get();
	    assignment = Simple_FloatProjector.find(assignment.getSimple_FloatId());
	    
	    // ------------------------------------------
		// assign the Sections to the parent entity
		// ------------------------------------------ 
	    parentEntity.setSections( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Sections
	 * 
	 * @param	parentId		UUID
	 * @return	SvShuntCompensatorSections
	 */
	public SvShuntCompensatorSections unAssignSections( UUID parentId ) {
		SvShuntCompensatorSections parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Sections on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Sections on the parent entithy
		// ------------------------------------------     
	    parentEntity.setSections(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a ShuntCompensator
     * 
     * @param	parentId	UUID
     * @param	assignment 	ShuntCompensator 
     * @return	SvShuntCompensatorSections
     */
    public SvShuntCompensatorSections assignShuntCompensator( UUID parentId, ShuntCompensator assignment ) {
	    LOGGER.info("assigning ShuntCompensator as " + assignment.toString() );

	    SvShuntCompensatorSections parentEntity = repository.findById( parentId ).get();
	    assignment = ShuntCompensatorProjector.find(assignment.getShuntCompensatorId());
	    
	    // ------------------------------------------
		// assign the ShuntCompensator to the parent entity
		// ------------------------------------------ 
	    parentEntity.setShuntCompensator( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the ShuntCompensator
	 * 
	 * @param	parentId		UUID
	 * @return	SvShuntCompensatorSections
	 */
	public SvShuntCompensatorSections unAssignShuntCompensator( UUID parentId ) {
		SvShuntCompensatorSections parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning ShuntCompensator on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the ShuntCompensator on the parent entithy
		// ------------------------------------------     
	    parentEntity.setShuntCompensator(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the SvShuntCompensatorSections via an FindSvShuntCompensatorSectionsQuery
     * @return 	query	FindSvShuntCompensatorSectionsQuery
     */
    @SuppressWarnings("unused")
    public SvShuntCompensatorSections find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a SvShuntCompensatorSections - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all SvShuntCompensatorSectionss
     *
     * @param	query	FindAllSvShuntCompensatorSectionsQuery 
     * @return 	List<SvShuntCompensatorSections> 
     */
    @SuppressWarnings("unused")
    public List<SvShuntCompensatorSections> findAll( FindAllSvShuntCompensatorSectionsQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all SvShuntCompensatorSections - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final SvShuntCompensatorSectionsRepository repository;
    @Autowired
	@Qualifier(value = "simple_Float-entity-projector")
	Simple_FloatEntityProjector Simple_FloatProjector;
    @Autowired
	@Qualifier(value = "shuntCompensator-entity-projector")
	ShuntCompensatorEntityProjector ShuntCompensatorProjector;

    private static final Logger LOGGER 	= Logger.getLogger(SvShuntCompensatorSectionsEntityProjector.class.getName());

}
