package pl.poznan.put.cs.ify.webify.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pl.poznan.put.cs.ify.webify.BaseTest;

public class MathUtilsTest extends BaseTest {

	@Test
	public void getDigitFromPosLongTest() {

		// Liczba do przetestowania
		Long num = 123498765l;
		// Liczba do przetestowania w postaci tablicy
		Long numArr[] = { 1l, 2l, 3l, 4l, 9l, 8l, 7l, 6l, 5l };

		// Właściwy test
		for (int i = 0; i < numArr.length; i++) {

			assertEquals("Nieprawidłowa wartość z pozycji: " + i, numArr[i],
					MathUtils.getDigitFromPos(num, i));

		}

	}

	@Test
	public void getDigitFromPosIntegerTest() {

		// Liczba do przetestowania
		Integer num = 123498765;
		// Liczba do przetestowania w postaci tablicy
		Integer numArr[] = { 1, 2, 3, 4, 9, 8, 7, 6, 5 };

		// Właściwy test
		for (int i = 0; i < numArr.length; i++) {

			assertEquals("Nieprawidłowa wartość z pozycji: " + i, numArr[i],
					MathUtils.getDigitFromPos(num, i));

		}

	}
}
