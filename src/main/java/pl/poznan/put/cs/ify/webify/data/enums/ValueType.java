package pl.poznan.put.cs.ify.webify.data.enums;

public enum ValueType {
	STRING(String.class), BOOLEAN(Boolean.class), BYTE_ARRAY(byte[].class), DOUBLE(
			Double.class), INTEGER(Integer.class);
	Class<?> cls;

	ValueType(Class<?> c) {
		cls = c;
	}

	public Class<?> getCls() {
		return cls;
	}

	public static ValueType parse(Class<?> cls) {
		return null;
	}

	public static ValueType parse(String s) {
		return ValueType.valueOf(s.toUpperCase());
	}
}
