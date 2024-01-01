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
 * Projector for Command as outlined for the CQRS pattern.
 * 
 * Commands are handled by CommandAggregate
 * 
 * @author your_name_here
 *
 */
@Component("command-entity-projector")
public class CommandEntityProjector {
		
	// core constructor
	public CommandEntityProjector(CommandRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a Command
	 * 
     * @param	entity Command
     */
    public Command create( Command entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a Command
	 * 
     * @param	entity Command
     */
    public Command update( Command entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a Command
	 * 
     * @param	id		UUID
     */
    public Command delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    Command entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a NormalValue
     * 
     * @param	parentId	UUID
     * @param	assignment 	IntegerProxy 
     * @return	Command
     */
    public Command assignNormalValue( UUID parentId, IntegerProxy assignment ) {
	    LOGGER.info("assigning NormalValue as " + assignment.toString() );

	    Command parentEntity = repository.findById( parentId ).get();
	    assignment = IntegerProxyProjector.find(assignment.getIntegerProxyId());
	    
	    // ------------------------------------------
		// assign the NormalValue to the parent entity
		// ------------------------------------------ 
	    parentEntity.setNormalValue( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the NormalValue
	 * 
	 * @param	parentId		UUID
	 * @return	Command
	 */
	public Command unAssignNormalValue( UUID parentId ) {
		Command parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning NormalValue on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the NormalValue on the parent entithy
		// ------------------------------------------     
	    parentEntity.setNormalValue(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Value
     * 
     * @param	parentId	UUID
     * @param	assignment 	IntegerProxy 
     * @return	Command
     */
    public Command assignValue( UUID parentId, IntegerProxy assignment ) {
	    LOGGER.info("assigning Value as " + assignment.toString() );

	    Command parentEntity = repository.findById( parentId ).get();
	    assignment = IntegerProxyProjector.find(assignment.getIntegerProxyId());
	    
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
	 * @return	Command
	 */
	public Command unAssignValue( UUID parentId ) {
		Command parentEntity = repository.findById(parentId).get();

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


    /*
     * Add to the ValueAliasSet
     * 
     * @param	parentID	UUID
     * @param	addTo		childType
     * @return	Command
     */
    public Command addToValueAliasSet( UUID parentId, ValueAliasSet addTo ) {
	    LOGGER.info("handling AssignValueAliasSetToCommandEvent - " );
	    
	    Command parentEntity = repository.findById(parentId).get();
	    ValueAliasSet child = ValueAliasSetProjector.find(addTo.getValueAliasSetId());
	    
	    parentEntity.getValueAliasSet().add( child );

	    // ------------------------------------------
    	// save 
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

    /*
     * Remove from the ValueAliasSet
     * 
     * @param	parentID	UUID
     * @param	removeFrom	childType
     * @return	Command
     */
	public Command removeFromValueAliasSet( UUID parentId, ValueAliasSet removeFrom ) {
	    LOGGER.info("handling RemoveValueAliasSetFromCommandEvent " );
	
	    Command parentEntity = repository.findById(parentId).get();
	    ValueAliasSet child = ValueAliasSetProjector.find(removeFrom.getValueAliasSetId());
	    
	    parentEntity.getValueAliasSet().remove( child );
	
	    // ------------------------------------------
		// save
		// ------------------------------------------ 
	    update(parentEntity);
	    
	    return parentEntity;
	}

    /*
     * Add to the DiscreteValue
     * 
     * @param	parentID	UUID
     * @param	addTo		childType
     * @return	Command
     */
    public Command addToDiscreteValue( UUID parentId, DiscreteValue addTo ) {
	    LOGGER.info("handling AssignDiscreteValueToCommandEvent - " );
	    
	    Command parentEntity = repository.findById(parentId).get();
	    DiscreteValue child = DiscreteValueProjector.find(addTo.getDiscreteValueId());
	    
	    parentEntity.getDiscreteValue().add( child );

	    // ------------------------------------------
    	// save 
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

    /*
     * Remove from the DiscreteValue
     * 
     * @param	parentID	UUID
     * @param	removeFrom	childType
     * @return	Command
     */
	public Command removeFromDiscreteValue( UUID parentId, DiscreteValue removeFrom ) {
	    LOGGER.info("handling RemoveDiscreteValueFromCommandEvent " );
	
	    Command parentEntity = repository.findById(parentId).get();
	    DiscreteValue child = DiscreteValueProjector.find(removeFrom.getDiscreteValueId());
	    
	    parentEntity.getDiscreteValue().remove( child );
	
	    // ------------------------------------------
		// save
		// ------------------------------------------ 
	    update(parentEntity);
	    
	    return parentEntity;
	}



    /**
     * Method to retrieve the Command via an FindCommandQuery
     * @return 	query	FindCommandQuery
     */
    @SuppressWarnings("unused")
    public Command find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a Command - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all Commands
     *
     * @param	query	FindAllCommandQuery 
     * @return 	List<Command> 
     */
    @SuppressWarnings("unused")
    public List<Command> findAll( FindAllCommandQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all Command - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final CommandRepository repository;
    @Autowired
	@Qualifier(value = "integerProxy-entity-projector")
	IntegerProxyEntityProjector IntegerProxyProjector;
    @Autowired
	@Qualifier(value = "valueAliasSet-entity-projector")
	ValueAliasSetEntityProjector ValueAliasSetProjector;
    @Autowired
	@Qualifier(value = "discreteValue-entity-projector")
	DiscreteValueEntityProjector DiscreteValueProjector;

    private static final Logger LOGGER 	= Logger.getLogger(CommandEntityProjector.class.getName());

}
