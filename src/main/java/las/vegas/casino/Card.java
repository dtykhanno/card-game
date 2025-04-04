package las.vegas.casino;

public enum Card {
    C2("C2", 2), C3("C3", 3), C4("C4", 4), C5("C5", 5), C6("C6", 6), C7("C7", 7), C8("C8", 8), C9("C9", 9), C10("C10", 10), CJ("CJ", 10), CD("CD", 10), CK("CK", 10), CA("CA", 11),
    H2("H2", 2), H3("H3", 3), H4("H4", 4), H5("H5", 5), H6("H6", 6), H7("H7", 7), H8("H8", 8), H9("H9", 9), H10("H10", 10), HJ("HJ", 10), HD("HD", 10), HK("HK", 10), HA("HA", 11),
    S2("S2", 2), S3("S3", 3), S4("S4", 4), S5("S5", 5), S6("S6", 6), S7("S7", 7), S8("S8", 8), S9("S9", 9), S10("S10", 10), SJ("SJ", 10), SD("SD", 10), SK("SK", 10), SA("SA", 11),
    D2("D2", 2), D3("D3", 3), D4("D4", 4), D5("D5", 5), D6("D6", 6), D7("D7", 7), D8("D8", 8), D9("D9", 9), D10("D10", 10), DJ("DJ", 10), DD("DD", 10), DK("DK", 10), DA("DA", 11),;

    public static final int SIZE = 52;

    private final String code;
    private final int rank;

    Card(String code, int rank) {
        this.code = code;
        this.rank = rank;
    }

    public String getCode() {
        return code;
    }

    public int getRank() {
        return rank;
    }
}