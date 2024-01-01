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

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.repository.*;

/**
 * Projector for ProprietaryParameterDynamics as outlined for the CQRS pattern.  All event handling and query handling related to ProprietaryParameterDynamics are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * Commands are handled by ProprietaryParameterDynamicsAggregate
 * 
 * @author your_name_here
 *
 */
//@ProcessingGroup("proprietaryParameterDynamics")
@Component("proprietaryParameterDynamics-projector")
public class ProprietaryParameterDynamicsProjector extends ProprietaryParameterDynamicsEntityProjector {
		
	// core constructor
	public ProprietaryParameterDynamicsProjector(ProprietaryParameterDynamicsRepository repository, QueryUpdateEmitter queryUpdateEmitter ) {
        super(repository);
        this.queryUpdateEmitter = queryUpdateEmitter;
    }	

	/*
     * @param	event CreateProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=CreateProprietaryParameterDynamicsEvent.class )
    public void handle( CreateProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling CreateProprietaryParameterDynamicsEvent - " + event );
	    ProprietaryParameterDynamics entity = new ProprietaryParameterDynamics();
        entity.setProprietaryParameterDynamicsId( event.getProprietaryParameterDynamicsId() );
	    
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    create(entity);
        
        // ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }

    /*
     * @param	event UpdateProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=UpdateProprietaryParameterDynamicsEvent.class )
    public void handle( UpdateProprietaryParameterDynamicsEvent event) {
    	LOGGER.info("handling UpdateProprietaryParameterDynamicsEvent - " + event );
    	
	    ProprietaryParameterDynamics entity = new ProprietaryParameterDynamics();
        entity.setProprietaryParameterDynamicsId( event.getProprietaryParameterDynamicsId() );
        entity.setBooleanParameterValue( event.getBooleanParameterValue() );
        entity.setFloatParameterValue( event.getFloatParameterValue() );
        entity.setIntegerParameterValue( event.getIntegerParameterValue() );
        entity.setParameterNumber( event.getParameterNumber() );
        entity.setPFVArControllerType1UserDefined( event.getPFVArControllerType1UserDefined() );
        entity.setVoltageCompensatorUserDefined( event.getVoltageCompensatorUserDefined() );
        entity.setMechanicalLoadUserDefined( event.getMechanicalLoadUserDefined() );
        entity.setExcitationSystemUserDefined( event.getExcitationSystemUserDefined() );
        entity.setAsynchronousMachineUserDefined( event.getAsynchronousMachineUserDefined() );
        entity.setDiscontinuousExcitationControlUserDefined( event.getDiscontinuousExcitationControlUserDefined() );
        entity.setTurbineGovernorUserDefined( event.getTurbineGovernorUserDefined() );
        entity.setVoltageAdjusterUserDefined( event.getVoltageAdjusterUserDefined() );
        entity.setSynchronousMachineUserDefined( event.getSynchronousMachineUserDefined() );
        entity.setUnderexcitationLimiterUserDefined( event.getUnderexcitationLimiterUserDefined() );
        entity.setTurbineLoadControllerUserDefined( event.getTurbineLoadControllerUserDefined() );
        entity.setOverexcitationLimiterUserDefined( event.getOverexcitationLimiterUserDefined() );
        entity.setPowerSystemStabilizerUserDefined( event.getPowerSystemStabilizerUserDefined() );
        entity.setLoadUserDefined( event.getLoadUserDefined() );
        entity.setPFVArControllerType2UserDefined( event.getPFVArControllerType2UserDefined() );
        entity.setWindType3or4UserDefined( event.getWindType3or4UserDefined() );
        entity.setWindPlantUserDefined( event.getWindPlantUserDefined() );
        entity.setWindType1or2UserDefined( event.getWindType1or2UserDefined() );
 
    	// ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		update(entity);

    	// ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );        
    }
    
    /*
     * @param	event DeleteProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=DeleteProprietaryParameterDynamicsEvent.class )
    public void handle( DeleteProprietaryParameterDynamicsEvent event) {
    	LOGGER.info("handling DeleteProprietaryParameterDynamicsEvent - " + event );
    	
    	// ------------------------------------------
    	// delete delegation
    	// ------------------------------------------
    	ProprietaryParameterDynamics entity = delete( event.getProprietaryParameterDynamicsId() );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );

    }    

    /*
     * @param	event AssignBooleanParameterValueToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignBooleanParameterValueToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignBooleanParameterValueToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignBooleanParameterValueToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignBooleanParameterValue( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignBooleanParameterValueFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignBooleanParameterValueFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignBooleanParameterValueFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignBooleanParameterValueFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignBooleanParameterValue( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignFloatParameterValueToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignFloatParameterValueToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignFloatParameterValueToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignFloatParameterValueToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignFloatParameterValue( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignFloatParameterValueFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignFloatParameterValueFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignFloatParameterValueFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignFloatParameterValueFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignFloatParameterValue( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignIntegerParameterValueToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignIntegerParameterValueToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignIntegerParameterValueToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignIntegerParameterValueToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignIntegerParameterValue( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignIntegerParameterValueFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignIntegerParameterValueFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignIntegerParameterValueFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignIntegerParameterValueFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignIntegerParameterValue( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignParameterNumberToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignParameterNumberToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignParameterNumberToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignParameterNumberToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignParameterNumber( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignParameterNumberFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignParameterNumberFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignParameterNumberFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignParameterNumberFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignParameterNumber( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignPFVArControllerType1UserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignPFVArControllerType1UserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignPFVArControllerType1UserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignPFVArControllerType1UserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignVoltageCompensatorUserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignVoltageCompensatorUserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignVoltageCompensatorUserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignVoltageCompensatorUserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignMechanicalLoadUserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignMechanicalLoadUserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignMechanicalLoadUserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignMechanicalLoadUserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignExcitationSystemUserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignExcitationSystemUserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignExcitationSystemUserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignExcitationSystemUserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignAsynchronousMachineUserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignAsynchronousMachineUserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignAsynchronousMachineUserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignAsynchronousMachineUserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignDiscontinuousExcitationControlUserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignDiscontinuousExcitationControlUserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignDiscontinuousExcitationControlUserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignDiscontinuousExcitationControlUserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignTurbineGovernorUserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignTurbineGovernorUserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignTurbineGovernorUserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignTurbineGovernorUserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignVoltageAdjusterUserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignVoltageAdjusterUserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignVoltageAdjusterUserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignVoltageAdjusterUserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignSynchronousMachineUserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignSynchronousMachineUserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignSynchronousMachineUserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignSynchronousMachineUserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignUnderexcitationLimiterUserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignUnderexcitationLimiterUserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignUnderexcitationLimiterUserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignUnderexcitationLimiterUserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignTurbineLoadControllerUserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignTurbineLoadControllerUserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignTurbineLoadControllerUserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignTurbineLoadControllerUserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignOverexcitationLimiterUserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignOverexcitationLimiterUserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignOverexcitationLimiterUserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignOverexcitationLimiterUserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignPowerSystemStabilizerUserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignPowerSystemStabilizerUserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignPowerSystemStabilizerUserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignPowerSystemStabilizerUserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignLoadUserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignLoadUserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignLoadUserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignLoadUserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignLoadUserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignLoadUserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignLoadUserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignLoadUserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignLoadUserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignLoadUserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignPFVArControllerType2UserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignPFVArControllerType2UserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignPFVArControllerType2UserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignPFVArControllerType2UserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignWindType3or4UserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignWindType3or4UserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignWindType3or4UserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignWindType3or4UserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignWindType3or4UserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignWindType3or4UserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignWindType3or4UserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignWindPlantUserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignWindPlantUserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignWindPlantUserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignWindPlantUserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignWindPlantUserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignWindPlantUserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignWindPlantUserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}

    /*
     * @param	event AssignWindType1or2UserDefinedToProprietaryParameterDynamicsEvent
     */
    @EventHandler( payloadType=AssignWindType1or2UserDefinedToProprietaryParameterDynamicsEvent.class)
    public void handle( AssignWindType1or2UserDefinedToProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling AssignWindType1or2UserDefinedToProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to assignTo
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = assignWindType1or2UserDefined( event.getProprietaryParameterDynamicsId(), event.getAssignment() );

	    // ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindProprietaryParameterDynamics( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllProprietaryParameterDynamics( entity );
    }
    

	/*
	 * @param	event UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsEvent
	 */
	@EventHandler( payloadType=UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsEvent.class)
	public void handle( UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsEvent event) {
	    LOGGER.info("handling UnAssignWindType1or2UserDefinedFromProprietaryParameterDynamicsEvent - " + event );

	    // ------------------------------------------
	    // delegate to unAssignFrom
	    // ------------------------------------------
	    ProprietaryParameterDynamics entity = unAssignWindType1or2UserDefined( event.getProprietaryParameterDynamicsId() );

		// ------------------------------------------
		// emit to subscribers that find one
		// ------------------------------------------    	
	    emitFindProprietaryParameterDynamics( entity );
	
		// ------------------------------------------
		// emit to subscribers that find all
		// ------------------------------------------    	
	    emitFindAllProprietaryParameterDynamics( entity );
	}




    /**
     * Method to retrieve the ProprietaryParameterDynamics via an ProprietaryParameterDynamicsPrimaryKey.
     * @param 	id Long
     * @return 	ProprietaryParameterDynamics
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public ProprietaryParameterDynamics handle( FindProprietaryParameterDynamicsQuery query ) 
    throws ProcessingException, IllegalArgumentException {
    	return find( query.getFilter().getProprietaryParameterDynamicsId() );
    }
    
    /**
     * Method to retrieve a collection of all ProprietaryParameterDynamicss
     *
     * @param	query	FindAllProprietaryParameterDynamicsQuery 
     * @return 	List<ProprietaryParameterDynamics> 
     * @exception ProcessingException Thrown if any problems
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public List<ProprietaryParameterDynamics> handle( FindAllProprietaryParameterDynamicsQuery query ) throws ProcessingException {
    	return findAll( query );
    }


	/**
	 * emit to subscription queries of type FindProprietaryParameterDynamics, 
	 * but only if the id matches
	 * 
	 * @param		entity	ProprietaryParameterDynamics
	 */
	protected void emitFindProprietaryParameterDynamics( ProprietaryParameterDynamics entity ) {
		LOGGER.info("handling emitFindProprietaryParameterDynamics" );
		
	    queryUpdateEmitter.emit(FindProprietaryParameterDynamicsQuery.class,
	                            query -> query.getFilter().getProprietaryParameterDynamicsId().equals(entity.getProprietaryParameterDynamicsId()),
	                            entity);
	}
	
	/**
	 * unconditionally emit to subscription queries of type FindAllProprietaryParameterDynamics
	 * 
	 * @param		entity	ProprietaryParameterDynamics
	 */
	protected void emitFindAllProprietaryParameterDynamics( ProprietaryParameterDynamics entity ) {
		LOGGER.info("handling emitFindAllProprietaryParameterDynamics" );
		
	    queryUpdateEmitter.emit(FindAllProprietaryParameterDynamicsQuery.class,
	                            query -> true,
	                            entity);
	}


	//--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
	private final QueryUpdateEmitter queryUpdateEmitter;
    private static final Logger LOGGER 	= Logger.getLogger(ProprietaryParameterDynamicsProjector.class.getName());

}
