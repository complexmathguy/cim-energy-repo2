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
 * Projector for WindGenTurbineType3IEC as outlined for the CQRS pattern.
 * 
 * Commands are handled by WindGenTurbineType3IECAggregate
 * 
 * @author your_name_here
 *
 */
@Component("windGenTurbineType3IEC-entity-projector")
public class WindGenTurbineType3IECEntityProjector {
		
	// core constructor
	public WindGenTurbineType3IECEntityProjector(WindGenTurbineType3IECRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a WindGenTurbineType3IEC
	 * 
     * @param	entity WindGenTurbineType3IEC
     */
    public WindGenTurbineType3IEC create( WindGenTurbineType3IEC entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a WindGenTurbineType3IEC
	 * 
     * @param	entity WindGenTurbineType3IEC
     */
    public WindGenTurbineType3IEC update( WindGenTurbineType3IEC entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a WindGenTurbineType3IEC
	 * 
     * @param	id		UUID
     */
    public WindGenTurbineType3IEC delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    WindGenTurbineType3IEC entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Dipmax
     * 
     * @param	parentId	UUID
     * @param	assignment 	PU 
     * @return	WindGenTurbineType3IEC
     */
    public WindGenTurbineType3IEC assignDipmax( UUID parentId, PU assignment ) {
	    LOGGER.info("assigning Dipmax as " + assignment.toString() );

	    WindGenTurbineType3IEC parentEntity = repository.findById( parentId ).get();
	    assignment = PUProjector.find(assignment.getPUId());
	    
	    // ------------------------------------------
		// assign the Dipmax to the parent entity
		// ------------------------------------------ 
	    parentEntity.setDipmax( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Dipmax
	 * 
	 * @param	parentId		UUID
	 * @return	WindGenTurbineType3IEC
	 */
	public WindGenTurbineType3IEC unAssignDipmax( UUID parentId ) {
		WindGenTurbineType3IEC parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Dipmax on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Dipmax on the parent entithy
		// ------------------------------------------     
	    parentEntity.setDipmax(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Diqmax
     * 
     * @param	parentId	UUID
     * @param	assignment 	PU 
     * @return	WindGenTurbineType3IEC
     */
    public WindGenTurbineType3IEC assignDiqmax( UUID parentId, PU assignment ) {
	    LOGGER.info("assigning Diqmax as " + assignment.toString() );

	    WindGenTurbineType3IEC parentEntity = repository.findById( parentId ).get();
	    assignment = PUProjector.find(assignment.getPUId());
	    
	    // ------------------------------------------
		// assign the Diqmax to the parent entity
		// ------------------------------------------ 
	    parentEntity.setDiqmax( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Diqmax
	 * 
	 * @param	parentId		UUID
	 * @return	WindGenTurbineType3IEC
	 */
	public WindGenTurbineType3IEC unAssignDiqmax( UUID parentId ) {
		WindGenTurbineType3IEC parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Diqmax on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Diqmax on the parent entithy
		// ------------------------------------------     
	    parentEntity.setDiqmax(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a WindMechIEC
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindMechIEC 
     * @return	WindGenTurbineType3IEC
     */
    public WindGenTurbineType3IEC assignWindMechIEC( UUID parentId, WindMechIEC assignment ) {
	    LOGGER.info("assigning WindMechIEC as " + assignment.toString() );

	    WindGenTurbineType3IEC parentEntity = repository.findById( parentId ).get();
	    assignment = WindMechIECProjector.find(assignment.getWindMechIECId());
	    
	    // ------------------------------------------
		// assign the WindMechIEC to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindMechIEC( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindMechIEC
	 * 
	 * @param	parentId		UUID
	 * @return	WindGenTurbineType3IEC
	 */
	public WindGenTurbineType3IEC unAssignWindMechIEC( UUID parentId ) {
		WindGenTurbineType3IEC parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindMechIEC on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindMechIEC on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindMechIEC(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a WindContPitchAngleIEC
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindContPitchAngleIEC 
     * @return	WindGenTurbineType3IEC
     */
    public WindGenTurbineType3IEC assignWindContPitchAngleIEC( UUID parentId, WindContPitchAngleIEC assignment ) {
	    LOGGER.info("assigning WindContPitchAngleIEC as " + assignment.toString() );

	    WindGenTurbineType3IEC parentEntity = repository.findById( parentId ).get();
	    assignment = WindContPitchAngleIECProjector.find(assignment.getWindContPitchAngleIECId());
	    
	    // ------------------------------------------
		// assign the WindContPitchAngleIEC to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindContPitchAngleIEC( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindContPitchAngleIEC
	 * 
	 * @param	parentId		UUID
	 * @return	WindGenTurbineType3IEC
	 */
	public WindGenTurbineType3IEC unAssignWindContPitchAngleIEC( UUID parentId ) {
		WindGenTurbineType3IEC parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindContPitchAngleIEC on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindContPitchAngleIEC on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindContPitchAngleIEC(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a WindAeroLinearIEC
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindAeroLinearIEC 
     * @return	WindGenTurbineType3IEC
     */
    public WindGenTurbineType3IEC assignWindAeroLinearIEC( UUID parentId, WindAeroLinearIEC assignment ) {
	    LOGGER.info("assigning WindAeroLinearIEC as " + assignment.toString() );

	    WindGenTurbineType3IEC parentEntity = repository.findById( parentId ).get();
	    assignment = WindAeroLinearIECProjector.find(assignment.getWindAeroLinearIECId());
	    
	    // ------------------------------------------
		// assign the WindAeroLinearIEC to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindAeroLinearIEC( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindAeroLinearIEC
	 * 
	 * @param	parentId		UUID
	 * @return	WindGenTurbineType3IEC
	 */
	public WindGenTurbineType3IEC unAssignWindAeroLinearIEC( UUID parentId ) {
		WindGenTurbineType3IEC parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindAeroLinearIEC on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindAeroLinearIEC on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindAeroLinearIEC(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a WindContPType3IEC
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindContPType3IEC 
     * @return	WindGenTurbineType3IEC
     */
    public WindGenTurbineType3IEC assignWindContPType3IEC( UUID parentId, WindContPType3IEC assignment ) {
	    LOGGER.info("assigning WindContPType3IEC as " + assignment.toString() );

	    WindGenTurbineType3IEC parentEntity = repository.findById( parentId ).get();
	    assignment = WindContPType3IECProjector.find(assignment.getWindContPType3IECId());
	    
	    // ------------------------------------------
		// assign the WindContPType3IEC to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindContPType3IEC( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindContPType3IEC
	 * 
	 * @param	parentId		UUID
	 * @return	WindGenTurbineType3IEC
	 */
	public WindGenTurbineType3IEC unAssignWindContPType3IEC( UUID parentId ) {
		WindGenTurbineType3IEC parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindContPType3IEC on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindContPType3IEC on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindContPType3IEC(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the WindGenTurbineType3IEC via an FindWindGenTurbineType3IECQuery
     * @return 	query	FindWindGenTurbineType3IECQuery
     */
    @SuppressWarnings("unused")
    public WindGenTurbineType3IEC find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a WindGenTurbineType3IEC - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all WindGenTurbineType3IECs
     *
     * @param	query	FindAllWindGenTurbineType3IECQuery 
     * @return 	List<WindGenTurbineType3IEC> 
     */
    @SuppressWarnings("unused")
    public List<WindGenTurbineType3IEC> findAll( FindAllWindGenTurbineType3IECQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all WindGenTurbineType3IEC - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final WindGenTurbineType3IECRepository repository;
    @Autowired
	@Qualifier(value = "pU-entity-projector")
	PUEntityProjector PUProjector;
    @Autowired
	@Qualifier(value = "windMechIEC-entity-projector")
	WindMechIECEntityProjector WindMechIECProjector;
    @Autowired
	@Qualifier(value = "windContPitchAngleIEC-entity-projector")
	WindContPitchAngleIECEntityProjector WindContPitchAngleIECProjector;
    @Autowired
	@Qualifier(value = "windAeroLinearIEC-entity-projector")
	WindAeroLinearIECEntityProjector WindAeroLinearIECProjector;
    @Autowired
	@Qualifier(value = "windContPType3IEC-entity-projector")
	WindContPType3IECEntityProjector WindContPType3IECProjector;

    private static final Logger LOGGER 	= Logger.getLogger(WindGenTurbineType3IECEntityProjector.class.getName());

}
