package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.SiteInformationMapper;
import com.pojo.SiteInformation;

@Service
public class SiteInformationService {

	@Autowired
	private SiteInformationMapper siteInformationMapper;

	public SiteInformation getSiteInformation() {
		return siteInformationMapper.getSiteInformation();
	}

	public void insertSiteInformation(SiteInformation siteInformation) {
		siteInformationMapper.insertSiteInformation(siteInformation);
	}

	public void truncateSiteInformation() {
		siteInformationMapper.truncateSiteInformation();
	}
	
}
