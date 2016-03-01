package ee.itcollege.weblist;

import javax.servlet.ServletContext;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Simple test using the WicketTester
 */
public class TestPersonsPage {
	private static WicketTester tester;
	
	@BeforeClass
	public static void setUp() {
		tester = new WicketTester(new WicketApplication() {
			@Override
			public ServletContext getServletContext() {
				ServletContext servletContext = super.getServletContext();
				AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
				applicationContext.setConfigLocation("ee.itcollege.weblist.test");
				applicationContext.setServletContext(servletContext);
				applicationContext.refresh();
				servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, applicationContext);
				return servletContext;
			}
		});
	}

	@Test
	public void homepageRendersSuccessfully() {
		tester.startPage(PersonsPage.class);

		tester.assertRenderedPage(PersonsPage.class);
		
		tester.assertContains("test-person");
	}
	
	@Test
	public void testAddPerson() {
		tester.startPage(PersonsPage.class);

		tester.assertRenderedPage(PersonsPage.class);
		tester.assertContainsNot("Toomas");
		
		FormTester formTester = tester.newFormTester("add-person-form");
		formTester.setValue("name", "Toomas");
		formTester.submit();
		
		tester.assertContains("Toomas");
	}
}
