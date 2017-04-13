package com.ptc.licensing.core;

import java.util.Map;

public class PTCLicenseManager implements ILicenseManager {
	
	PTCLicenseManager() {
		
	}
	
	@Override
	public void checkOutOrCheckInLicense(Integer sonId, String featureName, LicenseEntitlement licenseEntitilement, int count, boolean isCheckOut)
			throws LicensingException {
		Map<String, Integer> featureMap = licenseEntitilement.getFeatureEntitilement();
		Map<String, Integer> featureUsage = licenseEntitilement.getFeatureUsage();

		Integer entitiledCount = featureMap.get(featureName);
		Integer usageCount = featureUsage.get(featureName);
		
		updateUsageCount(featureName, featureUsage, entitiledCount, usageCount, count, isCheckOut);
	}

	private synchronized void updateUsageCount(String featureName, Map<String, Integer> featureUsage, Integer entitiledCount,
			Integer usageCount, int count, boolean isCheckOut) throws LicensingException {
		if (isCheckOut) {
			if ((entitiledCount - usageCount) >= count) {
				featureUsage.replace(featureName, usageCount + count);
			} else {
				throw new LicensingException("Allowable limit for feature " + featureName + " is reached.");
			}
		} else {
			if(usageCount > 0) {
				featureUsage.replace(featureName, usageCount - count);
			} else {
				throw new LicensingException("Already checked In all licenses for feature " + featureName);
			}
		}
	}
}
