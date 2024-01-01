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
 * Projector for WindDynamicsLookupTable as outlined for the CQRS pattern.
 * 
 * Commands are handled by WindDynamicsLookupTableAggregate
 * 
 * @author your_name_here
 *
 */
@Component("windDynamicsLookupTable-entity-projector")
public class WindDynamicsLookupTableEntityProjector {
		
	// core constructor
	public WindDynamicsLookupTableEntityProjector(WindDynamicsLookupTableRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a WindDynamicsLookupTable
	 * 
     * @param	entity WindDynamicsLookupTable
     */
    public WindDynamicsLookupTable create( WindDynamicsLookupTable entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a WindDynamicsLookupTable
	 * 
     * @param	entity WindDynamicsLookupTable
     */
    public WindDynamicsLookupTable update( WindDynamicsLookupTable entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a WindDynamicsLookupTable
	 * 
     * @param	id		UUID
     */
    public WindDynamicsLookupTable delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    WindDynamicsLookupTable entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Input
     * 
     * @param	parentId	UUID
     * @param	assignment 	Simple_Float 
     * @return	WindDynamicsLookupTable
     */
    public WindDynamicsLookupTable assignInput( UUID parentId, Simple_Float assignment ) {
	    LOGGER.info("assigning Input as " + assignment.toString() );

	    WindDynamicsLookupTable parentEntity = repository.findById( parentId ).get();
	    assignment = Simple_FloatProjector.find(assignment.getSimple_FloatId());
	    
	    // ------------------------------------------
		// assign the Input to the parent entity
		// ------------------------------------------ 
	    parentEntity.setInput( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Input
	 * 
	 * @param	parentId		UUID
	 * @return	WindDynamicsLookupTable
	 */
	public WindDynamicsLookupTable unAssignInput( UUID parentId ) {
		WindDynamicsLookupTable parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Input on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Input on the parent entithy
		// ------------------------------------------     
	    parentEntity.setInput(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Output
     * 
     * @param	parentId	UUID
     * @param	assignment 	Simple_Float 
     * @return	WindDynamicsLookupTable
     */
    public WindDynamicsLookupTable assignOutput( UUID parentId, Simple_Float assignment ) {
	    LOGGER.info("assigning Output as " + assignment.toString() );

	    WindDynamicsLookupTable parentEntity = repository.findById( parentId ).get();
	    assignment = Simple_FloatProjector.find(assignment.getSimple_FloatId());
	    
	    // ------------------------------------------
		// assign the Output to the parent entity
		// ------------------------------------------ 
	    parentEntity.setOutput( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Output
	 * 
	 * @param	parentId		UUID
	 * @return	WindDynamicsLookupTable
	 */
	public WindDynamicsLookupTable unAssignOutput( UUID parentId ) {
		WindDynamicsLookupTable parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Output on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Output on the parent entithy
		// ------------------------------------------     
	    parentEntity.setOutput(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Sequence
     * 
     * @param	parentId	UUID
     * @param	assignment 	IntegerProxy 
     * @return	WindDynamicsLookupTable
     */
    public WindDynamicsLookupTable assignSequence( UUID parentId, IntegerProxy assignment ) {
	    LOGGER.info("assigning Sequence as " + assignment.toString() );

	    WindDynamicsLookupTable parentEntity = repository.findById( parentId ).get();
	    assignment = IntegerProxyProjector.find(assignment.getIntegerProxyId());
	    
	    // ------------------------------------------
		// assign the Sequence to the parent entity
		// ------------------------------------------ 
	    parentEntity.setSequence( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Sequence
	 * 
	 * @param	parentId		UUID
	 * @return	WindDynamicsLookupTable
	 */
	public WindDynamicsLookupTable unAssignSequence( UUID parentId ) {
		WindDynamicsLookupTable parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Sequence on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Sequence on the parent entithy
		// ------------------------------------------     
	    parentEntity.setSequence(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a WindContRotorRIEC
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindContRotorRIEC 
     * @return	WindDynamicsLookupTable
     */
    public WindDynamicsLookupTable assignWindContRotorRIEC( UUID parentId, WindContRotorRIEC assignment ) {
	    LOGGER.info("assigning WindContRotorRIEC as " + assignment.toString() );

	    WindDynamicsLookupTable parentEntity = repository.findById( parentId ).get();
	    assignment = WindContRotorRIECProjector.find(assignment.getWindContRotorRIECId());
	    
	    // ------------------------------------------
		// assign the WindContRotorRIEC to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindContRotorRIEC( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindContRotorRIEC
	 * 
	 * @param	parentId		UUID
	 * @return	WindDynamicsLookupTable
	 */
	public WindDynamicsLookupTable unAssignWindContRotorRIEC( UUID parentId ) {
		WindDynamicsLookupTable parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindContRotorRIEC on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindContRotorRIEC on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindContRotorRIEC(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a WindContCurrLimIEC
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindContCurrLimIEC 
     * @return	WindDynamicsLookupTable
     */
    public WindDynamicsLookupTable assignWindContCurrLimIEC( UUID parentId, WindContCurrLimIEC assignment ) {
	    LOGGER.info("assigning WindContCurrLimIEC as " + assignment.toString() );

	    WindDynamicsLookupTable parentEntity = repository.findById( parentId ).get();
	    assignment = WindContCurrLimIECProjector.find(assignment.getWindContCurrLimIECId());
	    
	    // ------------------------------------------
		// assign the WindContCurrLimIEC to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindContCurrLimIEC( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindContCurrLimIEC
	 * 
	 * @param	parentId		UUID
	 * @return	WindDynamicsLookupTable
	 */
	public WindDynamicsLookupTable unAssignWindContCurrLimIEC( UUID parentId ) {
		WindDynamicsLookupTable parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindContCurrLimIEC on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindContCurrLimIEC on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindContCurrLimIEC(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a WindPlantFreqPcontrolIEC
     * 
     * @param	parentId	UUID
     * @param	assignment 	WindPlantFreqPcontrolIEC 
     * @return	WindDynamicsLookupTable
     */
    public WindDynamicsLookupTable assignWindPlantFreqPcontrolIEC( UUID parentId, WindPlantFreqPcontrolIEC assignment ) {
	    LOGGER.info("assigning WindPlantFreqPcontrolIEC as " + assignment.toString() );

	    WindDynamicsLookupTable parentEntity = repository.findById( parentId ).get();
	    assignment = WindPlantFreqPcontrolIECProjector.find(assignment.getWindPlantFreqPcontrolIECId());
	    
	    // ------------------------------------------
		// assign the WindPlantFreqPcontrolIEC to the parent entity
		// ------------------------------------------ 
	    parentEntity.setWindPlantFreqPcontrolIEC( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the WindPlantFreqPcontrolIEC
	 * 
	 * @param	parentId		UUID
	 * @return	WindDynamicsLookupTable
	 */
	public WindDynamicsLookupTable unAssignWindPlantFreqPcontrolIEC( UUID parentId ) {
		WindDynamicsLookupTable parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning WindPlantFreqPcontrolIEC on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the WindPlantFreqPcontrolIEC on the parent entithy
		// ------------------------------------------     
	    parentEntity.setWindPlantFreqPcontrolIEC(null);

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
     * @return	WindDynamicsLookupTable
     */
    public WindDynamicsLookupTable assignWindContPType3IEC( UUID parentId, WindContPType3IEC assignment ) {
	    LOGGER.info("assigning WindContPType3IEC as " + assignment.toString() );

	    WindDynamicsLookupTable parentEntity = repository.findById( parentId ).get();
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
	 * @return	WindDynamicsLookupTable
	 */
	public WindDynamicsLookupTable unAssignWindContPType3IEC( UUID parentId ) {
		WindDynamicsLookupTable parentEntity = repository.findById(parentId).get();

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
     * Method to retrieve the WindDynamicsLookupTable via an FindWindDynamicsLookupTableQuery
     * @return 	query	FindWindDynamicsLookupTableQuery
     */
    @SuppressWarnings("unused")
    public WindDynamicsLookupTable find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a WindDynamicsLookupTable - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all WindDynamicsLookupTables
     *
     * @param	query	FindAllWindDynamicsLookupTableQuery 
     * @return 	List<WindDynamicsLookupTable> 
     */
    @SuppressWarnings("unused")
    public List<WindDynamicsLookupTable> findAll( FindAllWindDynamicsLookupTableQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all WindDynamicsLookupTable - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final WindDynamicsLookupTableRepository repository;
    @Autowired
	@Qualifier(value = "simple_Float-entity-projector")
	Simple_FloatEntityProjector Simple_FloatProjector;
    @Autowired
	@Qualifier(value = "integerProxy-entity-projector")
	IntegerProxyEntityProjector IntegerProxyProjector;
    @Autowired
	@Qualifier(value = "windContRotorRIEC-entity-projector")
	WindContRotorRIECEntityProjector WindContRotorRIECProjector;
    @Autowired
	@Qualifier(value = "windContCurrLimIEC-entity-projector")
	WindContCurrLimIECEntityProjector WindContCurrLimIECProjector;
    @Autowired
	@Qualifier(value = "windPlantFreqPcontrolIEC-entity-projector")
	WindPlantFreqPcontrolIECEntityProjector WindPlantFreqPcontrolIECProjector;
    @Autowired
	@Qualifier(value = "windContPType3IEC-entity-projector")
	WindContPType3IECEntityProjector WindContPType3IECProjector;

    private static final Logger LOGGER 	= Logger.getLogger(WindDynamicsLookupTableEntityProjector.class.getName());

}
