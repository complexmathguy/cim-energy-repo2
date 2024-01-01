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
 * Projector for DiagramObject as outlined for the CQRS pattern.
 * 
 * Commands are handled by DiagramObjectAggregate
 * 
 * @author your_name_here
 *
 */
@Component("diagramObject-entity-projector")
public class DiagramObjectEntityProjector {
		
	// core constructor
	public DiagramObjectEntityProjector(DiagramObjectRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a DiagramObject
	 * 
     * @param	entity DiagramObject
     */
    public DiagramObject create( DiagramObject entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a DiagramObject
	 * 
     * @param	entity DiagramObject
     */
    public DiagramObject update( DiagramObject entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a DiagramObject
	 * 
     * @param	id		UUID
     */
    public DiagramObject delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    DiagramObject entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a DrawingOrder
     * 
     * @param	parentId	UUID
     * @param	assignment 	IntegerProxy 
     * @return	DiagramObject
     */
    public DiagramObject assignDrawingOrder( UUID parentId, IntegerProxy assignment ) {
	    LOGGER.info("assigning DrawingOrder as " + assignment.toString() );

	    DiagramObject parentEntity = repository.findById( parentId ).get();
	    assignment = IntegerProxyProjector.find(assignment.getIntegerProxyId());
	    
	    // ------------------------------------------
		// assign the DrawingOrder to the parent entity
		// ------------------------------------------ 
	    parentEntity.setDrawingOrder( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the DrawingOrder
	 * 
	 * @param	parentId		UUID
	 * @return	DiagramObject
	 */
	public DiagramObject unAssignDrawingOrder( UUID parentId ) {
		DiagramObject parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning DrawingOrder on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the DrawingOrder on the parent entithy
		// ------------------------------------------     
	    parentEntity.setDrawingOrder(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a PolygonFlag
     * 
     * @param	parentId	UUID
     * @param	assignment 	BooleanProxy 
     * @return	DiagramObject
     */
    public DiagramObject assignPolygonFlag( UUID parentId, BooleanProxy assignment ) {
	    LOGGER.info("assigning PolygonFlag as " + assignment.toString() );

	    DiagramObject parentEntity = repository.findById( parentId ).get();
	    assignment = BooleanProxyProjector.find(assignment.getBooleanProxyId());
	    
	    // ------------------------------------------
		// assign the PolygonFlag to the parent entity
		// ------------------------------------------ 
	    parentEntity.setPolygonFlag( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the PolygonFlag
	 * 
	 * @param	parentId		UUID
	 * @return	DiagramObject
	 */
	public DiagramObject unAssignPolygonFlag( UUID parentId ) {
		DiagramObject parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning PolygonFlag on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the PolygonFlag on the parent entithy
		// ------------------------------------------     
	    parentEntity.setPolygonFlag(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a OffsetX
     * 
     * @param	parentId	UUID
     * @param	assignment 	Simple_Float 
     * @return	DiagramObject
     */
    public DiagramObject assignOffsetX( UUID parentId, Simple_Float assignment ) {
	    LOGGER.info("assigning OffsetX as " + assignment.toString() );

	    DiagramObject parentEntity = repository.findById( parentId ).get();
	    assignment = Simple_FloatProjector.find(assignment.getSimple_FloatId());
	    
	    // ------------------------------------------
		// assign the OffsetX to the parent entity
		// ------------------------------------------ 
	    parentEntity.setOffsetX( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the OffsetX
	 * 
	 * @param	parentId		UUID
	 * @return	DiagramObject
	 */
	public DiagramObject unAssignOffsetX( UUID parentId ) {
		DiagramObject parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning OffsetX on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the OffsetX on the parent entithy
		// ------------------------------------------     
	    parentEntity.setOffsetX(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a OffsetY
     * 
     * @param	parentId	UUID
     * @param	assignment 	Simple_Float 
     * @return	DiagramObject
     */
    public DiagramObject assignOffsetY( UUID parentId, Simple_Float assignment ) {
	    LOGGER.info("assigning OffsetY as " + assignment.toString() );

	    DiagramObject parentEntity = repository.findById( parentId ).get();
	    assignment = Simple_FloatProjector.find(assignment.getSimple_FloatId());
	    
	    // ------------------------------------------
		// assign the OffsetY to the parent entity
		// ------------------------------------------ 
	    parentEntity.setOffsetY( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the OffsetY
	 * 
	 * @param	parentId		UUID
	 * @return	DiagramObject
	 */
	public DiagramObject unAssignOffsetY( UUID parentId ) {
		DiagramObject parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning OffsetY on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the OffsetY on the parent entithy
		// ------------------------------------------     
	    parentEntity.setOffsetY(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Rotation
     * 
     * @param	parentId	UUID
     * @param	assignment 	AngleDegrees 
     * @return	DiagramObject
     */
    public DiagramObject assignRotation( UUID parentId, AngleDegrees assignment ) {
	    LOGGER.info("assigning Rotation as " + assignment.toString() );

	    DiagramObject parentEntity = repository.findById( parentId ).get();
	    assignment = AngleDegreesProjector.find(assignment.getAngleDegreesId());
	    
	    // ------------------------------------------
		// assign the Rotation to the parent entity
		// ------------------------------------------ 
	    parentEntity.setRotation( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Rotation
	 * 
	 * @param	parentId		UUID
	 * @return	DiagramObject
	 */
	public DiagramObject unAssignRotation( UUID parentId ) {
		DiagramObject parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Rotation on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Rotation on the parent entithy
		// ------------------------------------------     
	    parentEntity.setRotation(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a DiagramObjectStyle
     * 
     * @param	parentId	UUID
     * @param	assignment 	DiagramObjectStyle 
     * @return	DiagramObject
     */
    public DiagramObject assignDiagramObjectStyle( UUID parentId, DiagramObjectStyle assignment ) {
	    LOGGER.info("assigning DiagramObjectStyle as " + assignment.toString() );

	    DiagramObject parentEntity = repository.findById( parentId ).get();
	    assignment = DiagramObjectStyleProjector.find(assignment.getDiagramObjectStyleId());
	    
	    // ------------------------------------------
		// assign the DiagramObjectStyle to the parent entity
		// ------------------------------------------ 
	    parentEntity.setDiagramObjectStyle( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the DiagramObjectStyle
	 * 
	 * @param	parentId		UUID
	 * @return	DiagramObject
	 */
	public DiagramObject unAssignDiagramObjectStyle( UUID parentId ) {
		DiagramObject parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning DiagramObjectStyle on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the DiagramObjectStyle on the parent entithy
		// ------------------------------------------     
	    parentEntity.setDiagramObjectStyle(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Diagram
     * 
     * @param	parentId	UUID
     * @param	assignment 	Diagram 
     * @return	DiagramObject
     */
    public DiagramObject assignDiagram( UUID parentId, Diagram assignment ) {
	    LOGGER.info("assigning Diagram as " + assignment.toString() );

	    DiagramObject parentEntity = repository.findById( parentId ).get();
	    assignment = DiagramProjector.find(assignment.getDiagramId());
	    
	    // ------------------------------------------
		// assign the Diagram to the parent entity
		// ------------------------------------------ 
	    parentEntity.setDiagram( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Diagram
	 * 
	 * @param	parentId		UUID
	 * @return	DiagramObject
	 */
	public DiagramObject unAssignDiagram( UUID parentId ) {
		DiagramObject parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Diagram on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Diagram on the parent entithy
		// ------------------------------------------     
	    parentEntity.setDiagram(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a IdentifiedObject
     * 
     * @param	parentId	UUID
     * @param	assignment 	IdentifiedObject 
     * @return	DiagramObject
     */
    public DiagramObject assignIdentifiedObject( UUID parentId, IdentifiedObject assignment ) {
	    LOGGER.info("assigning IdentifiedObject as " + assignment.toString() );

	    DiagramObject parentEntity = repository.findById( parentId ).get();
	    assignment = IdentifiedObjectProjector.find(assignment.getIdentifiedObjectId());
	    
	    // ------------------------------------------
		// assign the IdentifiedObject to the parent entity
		// ------------------------------------------ 
	    parentEntity.setIdentifiedObject( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the IdentifiedObject
	 * 
	 * @param	parentId		UUID
	 * @return	DiagramObject
	 */
	public DiagramObject unAssignIdentifiedObject( UUID parentId ) {
		DiagramObject parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning IdentifiedObject on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the IdentifiedObject on the parent entithy
		// ------------------------------------------     
	    parentEntity.setIdentifiedObject(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the DiagramObject via an FindDiagramObjectQuery
     * @return 	query	FindDiagramObjectQuery
     */
    @SuppressWarnings("unused")
    public DiagramObject find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a DiagramObject - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all DiagramObjects
     *
     * @param	query	FindAllDiagramObjectQuery 
     * @return 	List<DiagramObject> 
     */
    @SuppressWarnings("unused")
    public List<DiagramObject> findAll( FindAllDiagramObjectQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all DiagramObject - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final DiagramObjectRepository repository;
    @Autowired
	@Qualifier(value = "integerProxy-entity-projector")
	IntegerProxyEntityProjector IntegerProxyProjector;
    @Autowired
	@Qualifier(value = "booleanProxy-entity-projector")
	BooleanProxyEntityProjector BooleanProxyProjector;
    @Autowired
	@Qualifier(value = "simple_Float-entity-projector")
	Simple_FloatEntityProjector Simple_FloatProjector;
    @Autowired
	@Qualifier(value = "angleDegrees-entity-projector")
	AngleDegreesEntityProjector AngleDegreesProjector;
    @Autowired
	@Qualifier(value = "diagramObjectStyle-entity-projector")
	DiagramObjectStyleEntityProjector DiagramObjectStyleProjector;
    @Autowired
	@Qualifier(value = "diagram-entity-projector")
	DiagramEntityProjector DiagramProjector;
    @Autowired
	@Qualifier(value = "identifiedObject-entity-projector")
	IdentifiedObjectEntityProjector IdentifiedObjectProjector;
    @Autowired
	@Qualifier(value = "diagramObjectPoint-entity-projector")
	DiagramObjectPointEntityProjector DiagramObjectPointProjector;
    @Autowired
	@Qualifier(value = "visibilityLayer-entity-projector")
	VisibilityLayerEntityProjector VisibilityLayerProjector;

    private static final Logger LOGGER 	= Logger.getLogger(DiagramObjectEntityProjector.class.getName());

}
