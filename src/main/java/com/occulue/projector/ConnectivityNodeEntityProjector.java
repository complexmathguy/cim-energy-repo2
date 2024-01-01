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
 * Projector for ConnectivityNode as outlined for the CQRS pattern.
 * 
 * Commands are handled by ConnectivityNodeAggregate
 * 
 * @author your_name_here
 *
 */
@Component("connectivityNode-entity-projector")
public class ConnectivityNodeEntityProjector {
		
	// core constructor
	public ConnectivityNodeEntityProjector(ConnectivityNodeRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a ConnectivityNode
	 * 
     * @param	entity ConnectivityNode
     */
    public ConnectivityNode create( ConnectivityNode entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a ConnectivityNode
	 * 
     * @param	entity ConnectivityNode
     */
    public ConnectivityNode update( ConnectivityNode entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a ConnectivityNode
	 * 
     * @param	id		UUID
     */
    public ConnectivityNode delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    ConnectivityNode entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a BoundaryPoint
     * 
     * @param	parentId	UUID
     * @param	assignment 	BooleanProxy 
     * @return	ConnectivityNode
     */
    public ConnectivityNode assignBoundaryPoint( UUID parentId, BooleanProxy assignment ) {
	    LOGGER.info("assigning BoundaryPoint as " + assignment.toString() );

	    ConnectivityNode parentEntity = repository.findById( parentId ).get();
	    assignment = BooleanProxyProjector.find(assignment.getBooleanProxyId());
	    
	    // ------------------------------------------
		// assign the BoundaryPoint to the parent entity
		// ------------------------------------------ 
	    parentEntity.setBoundaryPoint( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the BoundaryPoint
	 * 
	 * @param	parentId		UUID
	 * @return	ConnectivityNode
	 */
	public ConnectivityNode unAssignBoundaryPoint( UUID parentId ) {
		ConnectivityNode parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning BoundaryPoint on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the BoundaryPoint on the parent entithy
		// ------------------------------------------     
	    parentEntity.setBoundaryPoint(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a FromEndIsoCode
     * 
     * @param	parentId	UUID
     * @param	assignment 	StringProxy 
     * @return	ConnectivityNode
     */
    public ConnectivityNode assignFromEndIsoCode( UUID parentId, StringProxy assignment ) {
	    LOGGER.info("assigning FromEndIsoCode as " + assignment.toString() );

	    ConnectivityNode parentEntity = repository.findById( parentId ).get();
	    assignment = StringProxyProjector.find(assignment.getStringProxyId());
	    
	    // ------------------------------------------
		// assign the FromEndIsoCode to the parent entity
		// ------------------------------------------ 
	    parentEntity.setFromEndIsoCode( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the FromEndIsoCode
	 * 
	 * @param	parentId		UUID
	 * @return	ConnectivityNode
	 */
	public ConnectivityNode unAssignFromEndIsoCode( UUID parentId ) {
		ConnectivityNode parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning FromEndIsoCode on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the FromEndIsoCode on the parent entithy
		// ------------------------------------------     
	    parentEntity.setFromEndIsoCode(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a FromEndName
     * 
     * @param	parentId	UUID
     * @param	assignment 	StringProxy 
     * @return	ConnectivityNode
     */
    public ConnectivityNode assignFromEndName( UUID parentId, StringProxy assignment ) {
	    LOGGER.info("assigning FromEndName as " + assignment.toString() );

	    ConnectivityNode parentEntity = repository.findById( parentId ).get();
	    assignment = StringProxyProjector.find(assignment.getStringProxyId());
	    
	    // ------------------------------------------
		// assign the FromEndName to the parent entity
		// ------------------------------------------ 
	    parentEntity.setFromEndName( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the FromEndName
	 * 
	 * @param	parentId		UUID
	 * @return	ConnectivityNode
	 */
	public ConnectivityNode unAssignFromEndName( UUID parentId ) {
		ConnectivityNode parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning FromEndName on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the FromEndName on the parent entithy
		// ------------------------------------------     
	    parentEntity.setFromEndName(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a FromEndNameTso
     * 
     * @param	parentId	UUID
     * @param	assignment 	StringProxy 
     * @return	ConnectivityNode
     */
    public ConnectivityNode assignFromEndNameTso( UUID parentId, StringProxy assignment ) {
	    LOGGER.info("assigning FromEndNameTso as " + assignment.toString() );

	    ConnectivityNode parentEntity = repository.findById( parentId ).get();
	    assignment = StringProxyProjector.find(assignment.getStringProxyId());
	    
	    // ------------------------------------------
		// assign the FromEndNameTso to the parent entity
		// ------------------------------------------ 
	    parentEntity.setFromEndNameTso( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the FromEndNameTso
	 * 
	 * @param	parentId		UUID
	 * @return	ConnectivityNode
	 */
	public ConnectivityNode unAssignFromEndNameTso( UUID parentId ) {
		ConnectivityNode parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning FromEndNameTso on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the FromEndNameTso on the parent entithy
		// ------------------------------------------     
	    parentEntity.setFromEndNameTso(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a ToEndIsoCode
     * 
     * @param	parentId	UUID
     * @param	assignment 	StringProxy 
     * @return	ConnectivityNode
     */
    public ConnectivityNode assignToEndIsoCode( UUID parentId, StringProxy assignment ) {
	    LOGGER.info("assigning ToEndIsoCode as " + assignment.toString() );

	    ConnectivityNode parentEntity = repository.findById( parentId ).get();
	    assignment = StringProxyProjector.find(assignment.getStringProxyId());
	    
	    // ------------------------------------------
		// assign the ToEndIsoCode to the parent entity
		// ------------------------------------------ 
	    parentEntity.setToEndIsoCode( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the ToEndIsoCode
	 * 
	 * @param	parentId		UUID
	 * @return	ConnectivityNode
	 */
	public ConnectivityNode unAssignToEndIsoCode( UUID parentId ) {
		ConnectivityNode parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning ToEndIsoCode on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the ToEndIsoCode on the parent entithy
		// ------------------------------------------     
	    parentEntity.setToEndIsoCode(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a ToEndName
     * 
     * @param	parentId	UUID
     * @param	assignment 	StringProxy 
     * @return	ConnectivityNode
     */
    public ConnectivityNode assignToEndName( UUID parentId, StringProxy assignment ) {
	    LOGGER.info("assigning ToEndName as " + assignment.toString() );

	    ConnectivityNode parentEntity = repository.findById( parentId ).get();
	    assignment = StringProxyProjector.find(assignment.getStringProxyId());
	    
	    // ------------------------------------------
		// assign the ToEndName to the parent entity
		// ------------------------------------------ 
	    parentEntity.setToEndName( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the ToEndName
	 * 
	 * @param	parentId		UUID
	 * @return	ConnectivityNode
	 */
	public ConnectivityNode unAssignToEndName( UUID parentId ) {
		ConnectivityNode parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning ToEndName on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the ToEndName on the parent entithy
		// ------------------------------------------     
	    parentEntity.setToEndName(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a ToEndNameTso
     * 
     * @param	parentId	UUID
     * @param	assignment 	StringProxy 
     * @return	ConnectivityNode
     */
    public ConnectivityNode assignToEndNameTso( UUID parentId, StringProxy assignment ) {
	    LOGGER.info("assigning ToEndNameTso as " + assignment.toString() );

	    ConnectivityNode parentEntity = repository.findById( parentId ).get();
	    assignment = StringProxyProjector.find(assignment.getStringProxyId());
	    
	    // ------------------------------------------
		// assign the ToEndNameTso to the parent entity
		// ------------------------------------------ 
	    parentEntity.setToEndNameTso( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the ToEndNameTso
	 * 
	 * @param	parentId		UUID
	 * @return	ConnectivityNode
	 */
	public ConnectivityNode unAssignToEndNameTso( UUID parentId ) {
		ConnectivityNode parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning ToEndNameTso on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the ToEndNameTso on the parent entithy
		// ------------------------------------------     
	    parentEntity.setToEndNameTso(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a ConnectivityNodeContainer
     * 
     * @param	parentId	UUID
     * @param	assignment 	ConnectivityNodeContainer 
     * @return	ConnectivityNode
     */
    public ConnectivityNode assignConnectivityNodeContainer( UUID parentId, ConnectivityNodeContainer assignment ) {
	    LOGGER.info("assigning ConnectivityNodeContainer as " + assignment.toString() );

	    ConnectivityNode parentEntity = repository.findById( parentId ).get();
	    assignment = ConnectivityNodeContainerProjector.find(assignment.getConnectivityNodeContainerId());
	    
	    // ------------------------------------------
		// assign the ConnectivityNodeContainer to the parent entity
		// ------------------------------------------ 
	    parentEntity.setConnectivityNodeContainer( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the ConnectivityNodeContainer
	 * 
	 * @param	parentId		UUID
	 * @return	ConnectivityNode
	 */
	public ConnectivityNode unAssignConnectivityNodeContainer( UUID parentId ) {
		ConnectivityNode parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning ConnectivityNodeContainer on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the ConnectivityNodeContainer on the parent entithy
		// ------------------------------------------     
	    parentEntity.setConnectivityNodeContainer(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the ConnectivityNode via an FindConnectivityNodeQuery
     * @return 	query	FindConnectivityNodeQuery
     */
    @SuppressWarnings("unused")
    public ConnectivityNode find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a ConnectivityNode - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all ConnectivityNodes
     *
     * @param	query	FindAllConnectivityNodeQuery 
     * @return 	List<ConnectivityNode> 
     */
    @SuppressWarnings("unused")
    public List<ConnectivityNode> findAll( FindAllConnectivityNodeQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all ConnectivityNode - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final ConnectivityNodeRepository repository;
    @Autowired
	@Qualifier(value = "booleanProxy-entity-projector")
	BooleanProxyEntityProjector BooleanProxyProjector;
    @Autowired
	@Qualifier(value = "stringProxy-entity-projector")
	StringProxyEntityProjector StringProxyProjector;
    @Autowired
	@Qualifier(value = "connectivityNodeContainer-entity-projector")
	ConnectivityNodeContainerEntityProjector ConnectivityNodeContainerProjector;

    private static final Logger LOGGER 	= Logger.getLogger(ConnectivityNodeEntityProjector.class.getName());

}
