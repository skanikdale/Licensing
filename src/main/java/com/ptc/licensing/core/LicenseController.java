package com.ptc.licensing.core;

import java.io.IOException;

public class LicenseController {

	private ILicenseManager licenseManager;
	private static LicenseController instance;
	
	public static LicenseController getLicenseController(LicenseManagerType licenseType) throws LicensingException, IOException {
		if(instance == null) {
			instance = new LicenseController(licenseType);
		}
		
		return instance;
	}
	
	private LicenseController(LicenseManagerType licenseType) throws LicensingException, IOException {
		super();
		
		initLicenseManager(licenseType);
	}

	private void initLicenseManager(LicenseManagerType licenseType) throws LicensingException, IOException {

		switch (licenseType) {
//			case TRIAL:
//				licenseManager = new TrialLicenseManager();
//				break;
			case PTC:
				licenseManager = new PTCLicenseManager();
				break;
			default:
				break;
		}

	}
	
	public void checkOutLicense(Integer sonId, String featureName, LicenseEntitlement licenseEntitilement, int count) throws LicensingException {
		licenseManager.checkOutOrCheckInLicense(sonId, featureName, licenseEntitilement, count, true);
	}

	public void checkInLicense(Integer sonId, String featureName, LicenseEntitlement licenseEntitilement, int count) throws LicensingException {
		licenseManager.checkOutOrCheckInLicense(sonId, featureName, licenseEntitilement, count , false);
	}

}
