package ee.itcollege.web;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ee.itcollege.web.config.SpringConfiguration;
import ee.itcollege.web.page.FirstPage;
import ee.itcollege.web.service.StaticNewsService;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	// selenide

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
		
		AnnotationConfigApplicationContext annotationConfigApplicationContext
			= new AnnotationConfigApplicationContext(StaticNewsService.class);
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		FirstPage page = tester.startPage(FirstPage.class);
		//page.setNewsService(newsService);

		//assert rendered page class
		tester.assertRenderedPage(FirstPage.class);
		
		tester.clickLink("secondLink");
		
		tester.assertContains("3");
	}
}
