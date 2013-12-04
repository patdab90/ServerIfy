package pl.poznan.put.cs.ify.webify.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import pl.poznan.put.cs.ify.webify.BaseTest;
import pl.poznan.put.cs.ify.webify.data.enums.user.Gender;

/**
 * Testy dla narzędzi do numeru pesel
 * 
 * @date 15-06-2013
 * 
 */
public class PeselUtilsTest extends BaseTest {

	@Test
	public void getChecksumTest() {

		String pesel = "91021200416";
		Long expectedResult = 6l;

		assertEquals("Nieprawidłowa suma kontrolna", expectedResult,
				PeselUtils.getChecksum(pesel));

	}

	@Test
	public void getGenderTest() {
		String pesel[] = { "91021200416", "91021200423", "123" };
		Gender expected[] = { Gender.MALE, Gender.FEMALE, Gender.UNDEFINED };

		for (int i = 0; i < pesel.length; i++) {
			assertEquals("Nieprawidłowa płeć dla numeru: " + pesel[i],
					expected[i], PeselUtils.getGender(pesel[i]));
		}
	}

	@Test
	public void getBirthDayStringTest() {
		String pesel = "91021200416";
		String expected = "12-02-1991";

		assertEquals("Nieprawidłowa data lub jej format dla numeru: " + pesel,
				expected, PeselUtils.getBirthDayString(pesel));

		assertEquals("BRAK DANYCH", PeselUtils.getBirthDayString(""));
	}

	@Test
	public void getBirthDayTest() {
		String pesel = "91021200416";
		Long expected = 12l;

		assertEquals("Nieprawidłowy dzień lub jej format dla numeru: "
				+ pesel, expected, PeselUtils.getBirthDay(pesel));

		assertNull(PeselUtils.getBirthDay(""));
	}

	@Test
	public void getBirthMonthTest() {
		String pesel = "91021200416";
		Long expected = 2l;

		assertEquals("Nieprawidłowy miesiąc lub jej format dla numeru: "
				+ pesel, expected, PeselUtils.getBirthMonth(pesel));

		assertNull(PeselUtils.getBirthMonth(""));
	}

	@Test
	public void getBirthYearTest() {
		String pesel = "91021200416";
		Long expected = 1991l;

		assertEquals("Nieprawidłowy rok lub jego format dla numeru: " + pesel,
				expected, PeselUtils.getBirthYear(pesel));

		assertNull(PeselUtils.getBirthYear(""));
	}

}
