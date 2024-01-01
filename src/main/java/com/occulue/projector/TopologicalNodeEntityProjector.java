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
 * Projector for TopologicalNode as outlined for the CQRS pattern.
 * 
 * Commands are handled by TopologicalNodeAggregate
 * 
 * @author your_name_here
 *
 */
@Component("topologicalNode-entity-projector")
public class TopologicalNodeEntityProjector {
		
	// core constructor
	public TopologicalNodeEntityProjector(TopologicalNodeRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a TopologicalNode
	 * 
     * @param	entity TopologicalNode
     */
    public TopologicalNode create( TopologicalNode entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a TopologicalNode
	 * 
     * @param	entity TopologicalNode
     */
    public TopologicalNode update( TopologicalNode entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a TopologicalNode
	 * 
     * @param	id		UUID
     */
    public TopologicalNode delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    TopologicalNode entity = repository.findById(id).get();
	    
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
     * @return	TopologicalNode
     */
    public TopologicalNode assignBoundaryPoint( UUID parentId, BooleanProxy assignment ) {
	    LOGGER.info("assigning BoundaryPoint as " + assignment.toString() );

	    TopologicalNode parentEntity = repository.findById( parentId ).get();
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
	 * @return	TopologicalNode
	 */
	public TopologicalNode unAssignBoundaryPoint( UUID parentId ) {
		TopologicalNode parentEntity = repository.findById(parentId).get();

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
     * @return	TopologicalNode
     */
    public TopologicalNode assignFromEndIsoCode( UUID parentId, StringProxy assignment ) {
	    LOGGER.info("assigning FromEndIsoCode as " + assignment.toString() );

	    TopologicalNode parentEntity = repository.findById( parentId ).get();
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
	 * @return	TopologicalNode
	 */
	public TopologicalNode unAssignFromEndIsoCode( UUID parentId ) {
		TopologicalNode parentEntity = repository.findById(parentId).get();

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
     * @return	TopologicalNode
     */
    public TopologicalNode assignFromEndName( UUID parentId, StringProxy assignment ) {
	    LOGGER.info("assigning FromEndName as " + assignment.toString() );

	    TopologicalNode parentEntity = repository.findById( parentId ).get();
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
	 * @return	TopologicalNode
	 */
	public TopologicalNode unAssignFromEndName( UUID parentId ) {
		TopologicalNode parentEntity = repository.findById(parentId).get();

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
     * @return	TopologicalNode
     */
    public TopologicalNode assignFromEndNameTso( UUID parentId, StringProxy assignment ) {
	    LOGGER.info("assigning FromEndNameTso as " + assignment.toString() );

	    TopologicalNode parentEntity = repository.findById( parentId ).get();
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
	 * @return	TopologicalNode
	 */
	public TopologicalNode unAssignFromEndNameTso( UUID parentId ) {
		TopologicalNode parentEntity = repository.findById(parentId).get();

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
     * @return	TopologicalNode
     */
    public TopologicalNode assignToEndIsoCode( UUID parentId, StringProxy assignment ) {
	    LOGGER.info("assigning ToEndIsoCode as " + assignment.toString() );

	    TopologicalNode parentEntity = repository.findById( parentId ).get();
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
	 * @return	TopologicalNode
	 */
	public TopologicalNode unAssignToEndIsoCode( UUID parentId ) {
		TopologicalNode parentEntity = repository.findById(parentId).get();

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
     * @return	TopologicalNode
     */
    public TopologicalNode assignToEndName( UUID parentId, StringProxy assignment ) {
	    LOGGER.info("assigning ToEndName as " + assignment.toString() );

	    TopologicalNode parentEntity = repository.findById( parentId ).get();
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
	 * @return	TopologicalNode
	 */
	public TopologicalNode unAssignToEndName( UUID parentId ) {
		TopologicalNode parentEntity = repository.findById(parentId).get();

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
     * @return	TopologicalNode
     */
    public TopologicalNode assignToEndNameTso( UUID parentId, StringProxy assignment ) {
	    LOGGER.info("assigning ToEndNameTso as " + assignment.toString() );

	    TopologicalNode parentEntity = repository.findById( parentId ).get();
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
	 * @return	TopologicalNode
	 */
	public TopologicalNode unAssignToEndNameTso( UUID parentId ) {
		TopologicalNode parentEntity = repository.findById(parentId).get();

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
     * @return	TopologicalNode
     */
    public TopologicalNode assignConnectivityNodeContainer( UUID parentId, ConnectivityNodeContainer assignment ) {
	    LOGGER.info("assigning ConnectivityNodeContainer as " + assignment.toString() );

	    TopologicalNode parentEntity = repository.findById( parentId ).get();
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
	 * @return	TopologicalNode
	 */
	public TopologicalNode unAssignConnectivityNodeContainer( UUID parentId ) {
		TopologicalNode parentEntity = repository.findById(parentId).get();

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

    /*
     * Assign a BaseVoltage
     * 
     * @param	parentId	UUID
     * @param	assignment 	BaseVoltage 
     * @return	TopologicalNode
     */
    public TopologicalNode assignBaseVoltage( UUID parentId, BaseVoltage assignment ) {
	    LOGGER.info("assigning BaseVoltage as " + assignment.toString() );

	    TopologicalNode parentEntity = repository.findById( parentId ).get();
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
	 * @return	TopologicalNode
	 */
	public TopologicalNode unAssignBaseVoltage( UUID parentId ) {
		TopologicalNode parentEntity = repository.findById(parentId).get();

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




    /**
     * Method to retrieve the TopologicalNode via an FindTopologicalNodeQuery
     * @return 	query	FindTopologicalNodeQuery
     */
    @SuppressWarnings("unused")
    public TopologicalNode find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a TopologicalNode - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all TopologicalNodes
     *
     * @param	query	FindAllTopologicalNodeQuery 
     * @return 	List<TopologicalNode> 
     */
    @SuppressWarnings("unused")
    public List<TopologicalNode> findAll( FindAllTopologicalNodeQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all TopologicalNode - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final TopologicalNodeRepository repository;
    @Autowired
	@Qualifier(value = "booleanProxy-entity-projector")
	BooleanProxyEntityProjector BooleanProxyProjector;
    @Autowired
	@Qualifier(value = "stringProxy-entity-projector")
	StringProxyEntityProjector StringProxyProjector;
    @Autowired
	@Qualifier(value = "connectivityNodeContainer-entity-projector")
	ConnectivityNodeContainerEntityProjector ConnectivityNodeContainerProjector;
    @Autowired
	@Qualifier(value = "baseVoltage-entity-projector")
	BaseVoltageEntityProjector BaseVoltageProjector;
    @Autowired
	@Qualifier(value = "svInjection-entity-projector")
	SvInjectionEntityProjector SvInjectionProjector;
    @Autowired
	@Qualifier(value = "topologicalIsland-entity-projector")
	TopologicalIslandEntityProjector TopologicalIslandProjector;

    private static final Logger LOGGER 	= Logger.getLogger(TopologicalNodeEntityProjector.class.getName());

}
