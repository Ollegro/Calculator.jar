public enum Convert {
    I( "I",1), II( "II",2), III("III", 3),
    IV( "IV",4),
    V("V",5), VI("VI", 6), VII( "VII",7),
    VIII("VIII", 8), IX("IX", 9), X("X", 10);

    public String key;
    public int value;
    Convert(String key,int value) {
        this.value = value;
        this.key = key;
    }
    public String getKey() {
        return key;
    }
    public int getValue() {
        return value;
    }
}