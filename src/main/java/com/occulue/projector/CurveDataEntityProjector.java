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
 * Projector for CurveData as outlined for the CQRS pattern.
 * 
 * Commands are handled by CurveDataAggregate
 * 
 * @author your_name_here
 *
 */
@Component("curveData-entity-projector")
public class CurveDataEntityProjector {
		
	// core constructor
	public CurveDataEntityProjector(CurveDataRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a CurveData
	 * 
     * @param	entity CurveData
     */
    public CurveData create( CurveData entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a CurveData
	 * 
     * @param	entity CurveData
     */
    public CurveData update( CurveData entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a CurveData
	 * 
     * @param	id		UUID
     */
    public CurveData delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    CurveData entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    

    /*
     * Assign a Xvalue
     * 
     * @param	parentId	UUID
     * @param	assignment 	Simple_Float 
     * @return	CurveData
     */
    public CurveData assignXvalue( UUID parentId, Simple_Float assignment ) {
	    LOGGER.info("assigning Xvalue as " + assignment.toString() );

	    CurveData parentEntity = repository.findById( parentId ).get();
	    assignment = Simple_FloatProjector.find(assignment.getSimple_FloatId());
	    
	    // ------------------------------------------
		// assign the Xvalue to the parent entity
		// ------------------------------------------ 
	    parentEntity.setXvalue( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Xvalue
	 * 
	 * @param	parentId		UUID
	 * @return	CurveData
	 */
	public CurveData unAssignXvalue( UUID parentId ) {
		CurveData parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Xvalue on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Xvalue on the parent entithy
		// ------------------------------------------     
	    parentEntity.setXvalue(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Y1value
     * 
     * @param	parentId	UUID
     * @param	assignment 	Simple_Float 
     * @return	CurveData
     */
    public CurveData assignY1value( UUID parentId, Simple_Float assignment ) {
	    LOGGER.info("assigning Y1value as " + assignment.toString() );

	    CurveData parentEntity = repository.findById( parentId ).get();
	    assignment = Simple_FloatProjector.find(assignment.getSimple_FloatId());
	    
	    // ------------------------------------------
		// assign the Y1value to the parent entity
		// ------------------------------------------ 
	    parentEntity.setY1value( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Y1value
	 * 
	 * @param	parentId		UUID
	 * @return	CurveData
	 */
	public CurveData unAssignY1value( UUID parentId ) {
		CurveData parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Y1value on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Y1value on the parent entithy
		// ------------------------------------------     
	    parentEntity.setY1value(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Y2value
     * 
     * @param	parentId	UUID
     * @param	assignment 	Simple_Float 
     * @return	CurveData
     */
    public CurveData assignY2value( UUID parentId, Simple_Float assignment ) {
	    LOGGER.info("assigning Y2value as " + assignment.toString() );

	    CurveData parentEntity = repository.findById( parentId ).get();
	    assignment = Simple_FloatProjector.find(assignment.getSimple_FloatId());
	    
	    // ------------------------------------------
		// assign the Y2value to the parent entity
		// ------------------------------------------ 
	    parentEntity.setY2value( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Y2value
	 * 
	 * @param	parentId		UUID
	 * @return	CurveData
	 */
	public CurveData unAssignY2value( UUID parentId ) {
		CurveData parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Y2value on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Y2value on the parent entithy
		// ------------------------------------------     
	    parentEntity.setY2value(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}

    /*
     * Assign a Curve
     * 
     * @param	parentId	UUID
     * @param	assignment 	Curve 
     * @return	CurveData
     */
    public CurveData assignCurve( UUID parentId, Curve assignment ) {
	    LOGGER.info("assigning Curve as " + assignment.toString() );

	    CurveData parentEntity = repository.findById( parentId ).get();
	    assignment = CurveProjector.find(assignment.getCurveId());
	    
	    // ------------------------------------------
		// assign the Curve to the parent entity
		// ------------------------------------------ 
	    parentEntity.setCurve( assignment );

	    // ------------------------------------------
    	// save the parent entity
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

	/*
	 * Unassign the Curve
	 * 
	 * @param	parentId		UUID
	 * @return	CurveData
	 */
	public CurveData unAssignCurve( UUID parentId ) {
		CurveData parentEntity = repository.findById(parentId).get();

		LOGGER.info("unAssigning Curve on " + parentEntity.toString() );
		
	    // ------------------------------------------
		// null out the Curve on the parent entithy
		// ------------------------------------------     
	    parentEntity.setCurve(null);

	    // ------------------------------------------
		// save the parent entity
		// ------------------------------------------ 
	    repository.save(parentEntity);
    
	    return parentEntity;
	}




    /**
     * Method to retrieve the CurveData via an FindCurveDataQuery
     * @return 	query	FindCurveDataQuery
     */
    @SuppressWarnings("unused")
    public CurveData find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a CurveData - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all CurveDatas
     *
     * @param	query	FindAllCurveDataQuery 
     * @return 	List<CurveData> 
     */
    @SuppressWarnings("unused")
    public List<CurveData> findAll( FindAllCurveDataQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all CurveData - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final CurveDataRepository repository;
    @Autowired
	@Qualifier(value = "simple_Float-entity-projector")
	Simple_FloatEntityProjector Simple_FloatProjector;
    @Autowired
	@Qualifier(value = "curve-entity-projector")
	CurveEntityProjector CurveProjector;

    private static final Logger LOGGER 	= Logger.getLogger(CurveDataEntityProjector.class.getName());

}
