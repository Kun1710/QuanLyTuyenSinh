package model;

public enum KhoiXetTuyen {
    A("A"), 
    B("B"), 
    C("C"), 
    D("D");

    private final String value;

    private KhoiXetTuyen(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static KhoiXetTuyen fromString(String text) {
        for (KhoiXetTuyen khoi : KhoiXetTuyen.values()) {
            if (khoi.value.equalsIgnoreCase(text.trim())) {
                return khoi;
            }
        }
        throw new IllegalArgumentException("Không tìm thấy khối phù hợp: " + text);
    }

    @Override
    public String toString() {
        return value;
    }
}