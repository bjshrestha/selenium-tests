package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsKruxObject;
import org.testng.annotations.Test;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestKruxSegment extends NewTestTemplate {
	private static final String KRUX_SEGMENT_ID = "l7lznzoty";

	@Test(
		dataProviderClass = AdsDataProvider.class,
		dataProvider = "mainWikiPages",
		groups = {"KruxSegmentDesktop_GeoEdgeFree", "KruxSegmentMobile_GeoEdgeFree", "Ads"}
	)
	public void TestKruxSegment_GeoEdgeFree(String wikiName, String article) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsKruxObject ads = new AdsKruxObject(driver, testedPage);
		ads.refreshPage();
		ads.refreshPage();
		ads.refreshPage();
		ads.verifyKruxSegment(KRUX_SEGMENT_ID);
	}
}