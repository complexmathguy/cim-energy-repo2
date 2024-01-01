/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.validator;

import org.springframework.util.Assert;

import com.occulue.api.*;

public class LocationValidator {
		
	/**
	 * default constructor
	 */
	protected LocationValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public LocationValidator getInstance() {
		return new LocationValidator();
	}
		
	/**
	 * handles creation validation for a Location
	 */
	public void validate( CreateLocationCommand location )throws Exception {
		Assert.notNull( location, "CreateLocationCommand should not be null" );
//		Assert.isNull( location.getLocationId(), "CreateLocationCommand identifier should be null" );
	}

	/**
	 * handles update validation for a Location
	 */
	public void validate( UpdateLocationCommand location ) throws Exception {
		Assert.notNull( location, "UpdateLocationCommand should not be null" );
		Assert.notNull( location.getLocationId(), "UpdateLocationCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a Location
	 */
    public void validate( DeleteLocationCommand location ) throws Exception {
		Assert.notNull( location, "{commandAlias} should not be null" );
		Assert.notNull( location.getLocationId(), "DeleteLocationCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a Location
	 */
	public void validate( LocationFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "LocationFetchOneSummary should not be null" );
		Assert.notNull( summary.getLocationId(), "LocationFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign PowerSystemResources validation for a Location
	 * 
	 * @param	command AssignPowerSystemResourcesToLocationCommand
	 */	
	public void validate( AssignPowerSystemResourcesToLocationCommand command ) throws Exception {
		Assert.notNull( command, "AssignPowerSystemResourcesToLocationCommand should not be null" );
		Assert.notNull( command.getLocationId(), "AssignPowerSystemResourcesToLocationCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignPowerSystemResourcesToLocationCommand assignment should not be null" );
	}

	/**
	 * handles unassign PowerSystemResources validation for a Location
	 * 
	 * @param	command UnAssignPowerSystemResourcesFromLocationCommand
	 */	
	public void validate( UnAssignPowerSystemResourcesFromLocationCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignPowerSystemResourcesFromLocationCommand should not be null" );
		Assert.notNull( command.getLocationId(), "UnAssignPowerSystemResourcesFromLocationCommand identifier should not be null" );
	}
	/**
	 * handles assign CoordinateSystem validation for a Location
	 * 
	 * @param	command AssignCoordinateSystemToLocationCommand
	 */	
	public void validate( AssignCoordinateSystemToLocationCommand command ) throws Exception {
		Assert.notNull( command, "AssignCoordinateSystemToLocationCommand should not be null" );
		Assert.notNull( command.getLocationId(), "AssignCoordinateSystemToLocationCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignCoordinateSystemToLocationCommand assignment should not be null" );
	}

	/**
	 * handles unassign CoordinateSystem validation for a Location
	 * 
	 * @param	command UnAssignCoordinateSystemFromLocationCommand
	 */	
	public void validate( UnAssignCoordinateSystemFromLocationCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignCoordinateSystemFromLocationCommand should not be null" );
		Assert.notNull( command.getLocationId(), "UnAssignCoordinateSystemFromLocationCommand identifier should not be null" );
	}


}
