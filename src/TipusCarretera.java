public enum TipusCarretera {
    AUTOVIA('A', 0.5),
    NACIONAL('N', 1.0),
    COMARCAL('C', 2.0),
    BUID('0', Double.MAX_VALUE); // '0' per caselles on no es pot circular
    
    private final char simbol;
    private final double tempsBase;
    
    TipusCarretera(char simbol, double tempsBase) {
        this.simbol = simbol;
        this.tempsBase = tempsBase;
    }
    
    public char getSimbol() {
        return simbol;
    }
    
    public double getTempsBase() {
        return tempsBase;
    }
    
    public static TipusCarretera fromChar(char c) {
        for (TipusCarretera t : values()) {
            if (t.simbol == c) {
                return t;
            }
        }
        return BUID;
    }
    
    public boolean esTransitable() {
        return this != BUID;
    }
}