/*
* generated by Xtext
*/
package com.atropos;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class GarfieldStandaloneSetup extends GarfieldStandaloneSetupGenerated{

	public static void doSetup() {
		new GarfieldStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

