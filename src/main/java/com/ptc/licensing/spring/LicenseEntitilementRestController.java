package com.ptc.licensing.spring;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptc.licensing.core.LicenseEntitlement;
import com.ptc.licensing.core.LicensingException;

@RestController
@CrossOrigin(origins = "http://localhost:9000")	
public class LicenseEntitilementRestController {

	@Autowired
	private LicenseEntitilementService licenseEntitlementService;
	
	@RequestMapping("/licenseEntitlements")
	public List<LicenseEntitlement> getAllLicenseEntitlements () {
		return licenseEntitlementService.getAllLicenseEntitlements();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/licenseEntitlements")
	public void addLicenseEntitlement(@RequestBody LicenseEntitlement licenseEntitlement) {
		licenseEntitlementService.addLicenseEntitlement(licenseEntitlement);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/licenseEntitlements")
	public void updateLicenseEntitlement(@RequestBody LicenseEntitlement licenseEntitlement) {
		licenseEntitlementService.updateLicenseEntitlement(licenseEntitlement);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/licenseEntitlements")
	public void deleteAllLicenseFeatures() {
		licenseEntitlementService.deleteAllLicenseEntitlements();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/checkoutLicense")
	public void checkOutLicense(@RequestParam(value="sonId", required=true) Integer sonId,
			@RequestParam(value="featureName", required=true) String featureName,
			@RequestParam(value="count", required=true) Integer count) throws LicensingException, IOException {
		licenseEntitlementService.checkOutLicense(sonId, featureName, count);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/checkInLicense")
	public void checkInLicense(@RequestParam(value="sonId", required=true) Integer sonId,
			@RequestParam(value="featureName", required=true) String featureName,
			@RequestParam(value="count", required=true) Integer count) throws LicensingException, IOException {
		licenseEntitlementService.checkInLicense(sonId, featureName, count);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/trialEntitlement")
	public void addTrialLicenseEntitlement() {
		
		deleteAllLicenseFeatures();

		Map<String, Integer> featureEntitilement = new HashMap<>();
		Map<String, Integer> featureUsage = new HashMap<>();
		
		featureEntitilement.put("Storage", 1);  
		featureEntitilement.put("ActiveProjects", 5); 
		
		featureUsage.put("Storage", 0);
		featureUsage.put("ActiveProjects", 0);

		Date expiryDate = new Date();
		expiryDate.setMonth(7);

		LicenseEntitlement entitilement = new LicenseEntitlement(100, featureEntitilement, featureUsage, new Date(),
				expiryDate);
		
		addLicenseEntitlement(entitilement);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/basicEntitlement")
	public void addBasicLicenseEntitlement() {
		
		deleteAllLicenseFeatures();

		Map<String, Integer> featureEntitilement = new HashMap<>();
		Map<String, Integer> featureUsage = new HashMap<>();
		
		featureEntitilement.put("Storage", 10);  
		featureEntitilement.put("ActiveProjects", 10); 
		
		featureUsage.put("Storage", 0);
		featureUsage.put("ActiveProjects", 0);

		Date expiryDate = new Date();
		expiryDate.setMonth(7);

		LicenseEntitlement entitilement = new LicenseEntitlement(100, featureEntitilement, featureUsage, new Date(),
				expiryDate);
		
		addLicenseEntitlement(entitilement);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/premiumEntitlement")
	public void addPremiumLicenseEntitlement() {
		
		deleteAllLicenseFeatures();

		Map<String, Integer> featureEntitilement = new HashMap<>();
		Map<String, Integer> featureUsage = new HashMap<>();
		
		featureEntitilement.put("Storage", 20);  
		featureEntitilement.put("ActiveProjects", 20); 
		
		featureUsage.put("Storage", 0);
		featureUsage.put("ActiveProjects", 0);

		Date expiryDate = new Date();
		expiryDate.setMonth(7);

		LicenseEntitlement entitilement = new LicenseEntitlement(100, featureEntitilement, featureUsage, new Date(),
				expiryDate);
		
		addLicenseEntitlement(entitilement);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/lifeTimeEntitlement")
	public void addLifeTimeLicenseEntitlement() {
		
		deleteAllLicenseFeatures();

		Map<String, Integer> featureEntitilement = new HashMap<>();
		Map<String, Integer> featureUsage = new HashMap<>();
		
		featureEntitilement.put("Storage", 1000);  
		featureEntitilement.put("ActiveProjects", 1000); 
		
		featureUsage.put("Storage", 0);
		featureUsage.put("ActiveProjects", 0);

		Date expiryDate = new Date();
		expiryDate.setMonth(7);

		LicenseEntitlement entitilement = new LicenseEntitlement(100, featureEntitilement, featureUsage, new Date(),
				expiryDate);
		
		addLicenseEntitlement(entitilement);
	}
	
	@RequestMapping("/storageVal")
	public Integer getAvlStorageCount () {
		String featureName = "Storage";
		LicenseEntitlement entitlement = licenseEntitlementService.getLicenseEntitlement(100);
		Map<String, Integer> entitlmentMap =  entitlement.getFeatureEntitilement();
		Map<String, Integer> usageMap =  entitlement.getFeatureUsage();
		
		Integer entitiledCount = entitlmentMap.get(featureName);
		Integer usageCount = usageMap.get(featureName);
		
		return entitiledCount - usageCount;
	}
	
	@RequestMapping("/projectVal")
	public Integer getAvlProjCount () {
		String featureName = "ActiveProjects";
		LicenseEntitlement entitlement = licenseEntitlementService.getLicenseEntitlement(100);
		Map<String, Integer> entitlmentMap =  entitlement.getFeatureEntitilement();
		Map<String, Integer> usageMap =  entitlement.getFeatureUsage();
		
		Integer entitiledCount = entitlmentMap.get(featureName);
		Integer usageCount = usageMap.get(featureName);
		
		return entitiledCount - usageCount;
	}
}
