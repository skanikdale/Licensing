package com.ptc.licensing.core;

public interface ILicenseManager {

	void checkOutOrCheckInLicense(Integer sonId, String featureName, LicenseEntitlement licenseEntitilement, int count, boolean isCheckOut) throws LicensingException;
}
