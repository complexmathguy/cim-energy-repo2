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
 * Projector for TransformerEnd as outlined for the CQRS pattern.
 * 
 * Commands are handled by TransformerEndAggregate
 * 
 * @author your_name_here
 *
 */
@Component("transformerEnd-entity-projector")
public class TransformerEndEntityProjector {
		
	// core constructor
	public TransformerEndEntityProjector(TransformerEndRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a TransformerEnd
	 * 
     * @param	entity TransformerEnd
     */
    public TransformerEnd create( TransformerEnd entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a TransformerEnd
	 * 
     * @param	entity TransformerEnd
     */
    public TransformerEnd update( TransformerEnd entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a TransformerEnd
	 * 
     * @param	id		UUID
     */
    public TransformerEnd delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    TransformerEnd entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a EndNumber
     * 
     * @param	parentId	UUID
     * @param	assignment 	IntegerProxy 
     * @return	TransformerEnd
     */
    public TransformerEnd assignEndNumber( UUID parentId, IntegerProxy assignment ) {
	    LOGGER.info("assigning EndNumber as " + assignment.toString() );

	    TransformerEnd parentEntity = repository.findById( parentId ).get();
	    assignment = IntegerProxyProjector.find(assignment.getIntegerProxyId());
	    
	    // ------------------------------------------
		// assign the EndNumber to the parent entity
		// ------------------------------------------ 
	    parentEntity.setEndNumber( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the EndNumber
	 * 
	 * @param	parentId		UUID
	 * @return	TransformerEnd
	 */
	public TransformerEnd unAssignEndNumber( UUID parentId ) {
		TransformerEnd parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning EndNumber on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the EndNumber on the parent entithy
		// ------------------------------------------     
	    parentEntity.setEndNumber(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Grounded
     * 
     * @param	parentId	UUID
     * @param	assignment 	BooleanProxy 
     * @return	TransformerEnd
     */
    public TransformerEnd assignGrounded( UUID parentId, BooleanProxy assignment ) {
	    LOGGER.info("assigning Grounded as " + assignment.toString() );

	    TransformerEnd parentEntity = repository.findById( parentId ).get();
	    assignment = BooleanProxyProjector.find(assignment.getBooleanProxyId());
	    
	    // ------------------------------------------
		// assign the Grounded to the parent entity
		// ------------------------------------------ 
	    parentEntity.setGrounded( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Grounded
	 * 
	 * @param	parentId		UUID
	 * @return	TransformerEnd
	 */
	public TransformerEnd unAssignGrounded( UUID parentId ) {
		TransformerEnd parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Grounded on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Grounded on the parent entithy
		// ------------------------------------------     
	    parentEntity.setGrounded(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Rground
     * 
     * @param	parentId	UUID
     * @param	assignment 	Resistance 
     * @return	TransformerEnd
     */
    public TransformerEnd assignRground( UUID parentId, Resistance assignment ) {
	    LOGGER.info("assigning Rground as " + assignment.toString() );

	    TransformerEnd parentEntity = repository.findById( parentId ).get();
	    assignment = ResistanceProjector.find(assignment.getResistanceId());
	    
	    // ------------------------------------------
		// assign the Rground to the parent entity
		// ------------------------------------------ 
	    parentEntity.setRground( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Rground
	 * 
	 * @param	parentId		UUID
	 * @return	TransformerEnd
	 */
	public TransformerEnd unAssignRground( UUID parentId ) {
		TransformerEnd parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Rground on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Rground on the parent entithy
		// ------------------------------------------     
	    parentEntity.setRground(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Xground
     * 
     * @param	parentId	UUID
     * @param	assignment 	Reactance 
     * @return	TransformerEnd
     */
    public TransformerEnd assignXground( UUID parentId, Reactance assignment ) {
	    LOGGER.info("assigning Xground as " + assignment.toString() );

	    TransformerEnd parentEntity = repository.findById( parentId ).get();
	    assignment = ReactanceProjector.find(assignment.getReactanceId());
	    
	    // ------------------------------------------
		// assign the Xground to the parent entity
		// ------------------------------------------ 
	    parentEntity.setXground( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Xground
	 * 
	 * @param	parentId		UUID
	 * @return	TransformerEnd
	 */
	public TransformerEnd unAssignXground( UUID parentId ) {
		TransformerEnd parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Xground on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Xground on the parent entithy
		// ------------------------------------------     
	    parentEntity.setXground(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a BaseVoltage
     * 
     * @param	parentId	UUID
     * @param	assignment 	BaseVoltage 
     * @return	TransformerEnd
     */
    public TransformerEnd assignBaseVoltage( UUID parentId, BaseVoltage assignment ) {
	    LOGGER.info("assigning BaseVoltage as " + assignment.toString() );

	    TransformerEnd parentEntity = repository.findById( parentId ).get();
	    assignment = BaseVoltageProjector.find(assignment.getBaseVoltageId());
	    
	    // ------------------------------------------
		// assign the BaseVoltage to the parent entity
		// ------------------------------------------ 
	    parentEntity.setBaseVoltage( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the BaseVoltage
	 * 
	 * @param	parentId		UUID
	 * @return	TransformerEnd
	 */
	public TransformerEnd unAssignBaseVoltage( UUID parentId ) {
		TransformerEnd parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning BaseVoltage on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the BaseVoltage on the parent entithy
		// ------------------------------------------     
	    parentEntity.setBaseVoltage(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Terminal
     * 
     * @param	parentId	UUID
     * @param	assignment 	Terminal 
     * @return	TransformerEnd
     */
    public TransformerEnd assignTerminal( UUID parentId, Terminal assignment ) {
	    LOGGER.info("assigning Terminal as " + assignment.toString() );

	    TransformerEnd parentEntity = repository.findById( parentId ).get();
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
	 * @return	TransformerEnd
	 */
	public TransformerEnd unAssignTerminal( UUID parentId ) {
		TransformerEnd parentEntity = repository.findById(parentId).get();

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




    /**
     * Method to retrieve the TransformerEnd via an FindTransformerEndQuery
     * @return 	query	FindTransformerEndQuery
     */
    @SuppressWarnings("unused")
    public TransformerEnd find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a TransformerEnd - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all TransformerEnds
     *
     * @param	query	FindAllTransformerEndQuery 
     * @return 	List<TransformerEnd> 
     */
    @SuppressWarnings("unused")
    public List<TransformerEnd> findAll( FindAllTransformerEndQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all TransformerEnd - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final TransformerEndRepository repository;
    @Autowired
	@Qualifier(value = "phaseTapChanger-entity-projector")
	PhaseTapChangerEntityProjector PhaseTapChangerProjector;
    @Autowired
	@Qualifier(value = "integerProxy-entity-projector")
	IntegerProxyEntityProjector IntegerProxyProjector;
    @Autowired
	@Qualifier(value = "booleanProxy-entity-projector")
	BooleanProxyEntityProjector BooleanProxyProjector;
    @Autowired
	@Qualifier(value = "resistance-entity-projector")
	ResistanceEntityProjector ResistanceProjector;
    @Autowired
	@Qualifier(value = "reactance-entity-projector")
	ReactanceEntityProjector ReactanceProjector;
    @Autowired
	@Qualifier(value = "baseVoltage-entity-projector")
	BaseVoltageEntityProjector BaseVoltageProjector;
    @Autowired
	@Qualifier(value = "terminal-entity-projector")
	TerminalEntityProjector TerminalProjector;

    private static final Logger LOGGER 	= Logger.getLogger(TransformerEndEntityProjector.class.getName());

}
