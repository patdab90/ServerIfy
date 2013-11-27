package pl.poznan.put.cs.ify.webify.utils;

public class PwzUtils {

	/**
	 * Obliczanie sumy kontrolnej numeru PWZ
	 * 
	 * @author Olga Kamińska
	 * @author Mateusz Pogorzelski
	 * @date 24-06-2013
	 * 
	 * @param nr
	 * @return
	 */
	public static Long getChecksum(String nr) {
		// Numer PWZ
		long num = Long.parseLong(nr);
		// Numer PWZ w postaci tablicy
		long tab[] = new long[7];
		// Tablica z wagami do obliczenia sumy kontrolnej
		long weights[] = { 1, 2, 3, 4, 5, 6 };

		// Tworzenie tablicy z numerem
		for (int i = 0; i < 7; i++) {
			tab[i] = MathUtils.getDigitFromPos(num, i);
		}

		// Obliczanie sumy kontrolnej
		long sum = 0;
		for (int i = 0; i < 6; i++) {
			sum += weights[i] * tab[i];
		}
		sum = sum % 11;
		return sum % 10;
	}

	/**
	 * Walidacja numeru PWZ
	 * 
	 * @author Mateusz Pogorzelski
	 * @date 24-06-2013
	 * 
	 * @param pwz
	 * @return
	 */
	public static boolean isValid(String pwz) {
		if (pwz.length() != 7)
			return false;
		if (!MathUtils.getDigitFromPos(Long.parseLong(pwz), 6).equals(
				getChecksum(pwz)))
			return false;

		return true;
	}

}
