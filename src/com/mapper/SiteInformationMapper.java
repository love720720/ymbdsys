package com.mapper;

import com.pojo.SiteInformation;

public interface SiteInformationMapper {

	public SiteInformation getSiteInformation();

	public void insertSiteInformation(SiteInformation siteInformation);

	public void updateSiteInformation(SiteInformation siteInformation);

	public void truncateSiteInformation();

}
