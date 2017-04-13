package com.ptc.licensing.spring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptc.licensing.core.LicenseController;
import com.ptc.licensing.core.LicenseEntitlement;
import com.ptc.licensing.core.LicenseManagerType;
import com.ptc.licensing.core.LicensingException;

@Service
public class LicenseEntitilementService {

	@Autowired
	private LicenseEntitlementRepository licenseEntitlementRepository;

	public void addLicenseEntitlement(LicenseEntitlement LicenseEntitlement) {
		licenseEntitlementRepository.save(LicenseEntitlement);
	}
	
	public LicenseEntitlement getLicenseEntitlement(Integer sonId) {
		return licenseEntitlementRepository.findOne(sonId);
	}

	public List<LicenseEntitlement> getAllLicenseEntitlements() {
		Iterable<LicenseEntitlement> iter = licenseEntitlementRepository.findAll();

		List<LicenseEntitlement> entitilements = new ArrayList<>();

		for (LicenseEntitlement feat : iter) {
			entitilements.add(feat);
		}

		return entitilements;
	}
	
	public void updateLicenseEntitlement(LicenseEntitlement licenseEntitlement) {
		licenseEntitlementRepository.save(licenseEntitlement);
	}

	public void deleteLicenseEntitlement(Integer sonId) {
		licenseEntitlementRepository.delete(sonId);
	}

	public void deleteAllLicenseEntitlements() {
		licenseEntitlementRepository.deleteAll();
	}
	
	public synchronized boolean checkOutLicense(Integer sonId, String featureName, int count)
			throws LicensingException, IOException {
		LicenseEntitlement licenseEntitlement = getLicenseEntitlement(sonId);
		LicenseController.getLicenseController(LicenseManagerType.PTC).checkOutLicense(sonId, featureName,
				licenseEntitlement, count);

		// Update the database with new values.
		licenseEntitlementRepository.save(licenseEntitlement);

		return true;
	}

	public synchronized boolean checkInLicense(Integer sonId, String featureName, int count) throws LicensingException, IOException {
		LicenseEntitlement licenseEntitlement = getLicenseEntitlement(sonId);
		LicenseController.getLicenseController(LicenseManagerType.PTC).checkInLicense(sonId, featureName,
				licenseEntitlement, count);

		// Update the database with new values.
		licenseEntitlementRepository.save(licenseEntitlement);

		return true;
	}

}
