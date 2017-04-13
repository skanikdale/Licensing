package com.ptc.licensing;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ptc.licensing.core.LicenseEntitlement;
import com.ptc.licensing.core.LicensingException;
import com.ptc.licensing.spring.LicenseEntitilementService;
import com.ptc.licensing.spring.LicenseEntitlementRepository;
import com.ptc.licensing.spring.LicensingApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LicensingApplication.class })
public class LicensingApplicationTests {

	@Autowired
	private LicenseEntitlementRepository repository;
	
	@Autowired
	private LicenseEntitilementService licenseEntitlementService;

	@Before
	public void setUp() {

		repository.deleteAll();
		
		populateEntitlements();
	}

	private void populateEntitlements() {
		Map<String, Integer> featureEntitilement = new HashMap<>();
		Map<String, Integer> featureUsage = new HashMap<>();
		
		featureEntitilement.put("Memory", 10);   // 10 GB
		featureEntitilement.put("Storage", 100);  // 100 GB
		
		featureUsage.put("Memory", 0);
		featureUsage.put("Storage", 0);

		Date expiryDate = new Date();
		expiryDate.setMonth(7);

		LicenseEntitlement entitilement = new LicenseEntitlement(100, featureEntitilement, featureUsage, new Date(),
				expiryDate);
		
		repository.save(entitilement);
		
		// 2nd entitlement
		
		featureEntitilement.clear();
		
		featureEntitilement.put("Memory", 20);  // 20 GB
		featureEntitilement.put("Storage", 200); // 200 GB
		expiryDate.setMonth(10);
		
		LicenseEntitlement entitilement2 = new LicenseEntitlement(200, featureEntitilement, featureUsage, new Date(),
				expiryDate);
		
		repository.save(entitilement2);
	}

	@Test
	public void checkOutLicense() throws LicensingException, IOException {

		licenseEntitlementService.checkOutLicense(200, "Memory", 2);
		
		
		
		
//		licenseEntitlementService.checkOutLicense(100, "Memory", 2);
//		licenseEntitlementService.checkOutLicense(100, "Memory", 4);
//		licenseEntitlementService.checkOutLicense(100, "Memory", 6);
//		
//		licenseEntitlementService.checkInLicense(100, "Memory", 5);
//		
//		licenseEntitlementService.checkOutLicense(100, "Storage", 5);
//		
//		// 2nd Organization
//		licenseEntitlementService.checkOutLicense(200, "Memory", 2);
//		
//		licenseEntitlementService.checkOutLicense(200, "Storage", 1);
//		licenseEntitlementService.checkOutLicense(200, "Storage", 2);
	}

}
