package com.ptc.licensing.spring;

import org.springframework.data.repository.CrudRepository;

import com.ptc.licensing.core.LicenseEntitlement;

public interface LicenseEntitlementRepository extends CrudRepository <LicenseEntitlement, Integer>{

}


