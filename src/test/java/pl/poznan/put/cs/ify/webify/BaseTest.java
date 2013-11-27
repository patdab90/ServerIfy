package pl.poznan.put.cs.ify.webify;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:pl/poznan/put/cs/ify/webify/spring-context.xml" })
@ActiveProfiles("test")
public class BaseTest {
	/**
	 * Pusty bezsensowny test - sprawdzenie czy środowisko dobrze działa
	 * 
	 * @date 30-05-2013
	 * 
	 */
	@Test
	public void assertionTest() {
		assertTrue(true);
	}
}
