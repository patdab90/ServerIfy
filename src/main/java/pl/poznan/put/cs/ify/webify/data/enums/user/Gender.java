package pl.poznan.put.cs.ify.webify.data.enums.user;

/**
 * Reprezentacja płci
 * 
 * 
 */
public enum Gender {
	/**
	 * Kobieta
	 */
	FEMALE,
	/**
	 * Mężczyzna
	 */
	MALE,
	/**
	 * Płeć nieznana
	 */
	UNDEFINED;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		switch (this) {
		case FEMALE:
			return "KOBIETA";
		case MALE:
			return "MĘŻCZYZNA";
		default:
			return "PŁEĆ NIEZNANA";
		}
	}
}
