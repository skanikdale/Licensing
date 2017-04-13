package com.ptc.licensing.core;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "licenseEntitlements")
public class LicenseEntitlement {
	
	@Id
	private int sonId;
	
	private Map<String, Integer> featureEntitilement;
	private Map<String, Integer> featureUsage;
	private Date startDate;
	private Date expiryDate;
	
	public LicenseEntitlement() {
		
	}
	
	public LicenseEntitlement(int sonId, Map<String, Integer> featureEntitilement, Map<String, Integer> featureUsage,
			Date startDate, Date expiryDate) {
		super();
		this.sonId = sonId;
		this.featureEntitilement = featureEntitilement;
		this.featureUsage = featureUsage;
		this.startDate = startDate;
		this.expiryDate = expiryDate;
	}



	public Map<String, Integer> getFeatureEntitilement() {
		return featureEntitilement;
	}

	public void setFeatureEntitilement(Map<String, Integer> featureEntitilement) {
		this.featureEntitilement = featureEntitilement;
	}

	public int getSonId() {
		return sonId;
	}

	public void setSonId(int sonId) {
		this.sonId = sonId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Map<String, Integer> getFeatureUsage() {
		return featureUsage;
	}

	public void setFeatureUsage(Map<String, Integer> featureUsage) {
		this.featureUsage = featureUsage;
	}
}
