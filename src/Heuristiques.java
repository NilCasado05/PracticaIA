public class Heuristiques {

    // H1: Manhattan * cost mínim (admissible)
    public static double h1(Estat actual, Estat fi) {
        int dx = Math.abs(actual.x - fi.x);
        int dy = Math.abs(actual.y - fi.y);
        return (dx + dy) * 0.5;
    }

    // H2: Manhattan * cost del tipus actual (no admissible)
    public static double h2(Estat actual, Estat fi) {
        int dx = Math.abs(actual.x - fi.x);
        int dy = Math.abs(actual.y - fi.y);
        return (dx + dy) * actual.tipus.getCost();
    }

    // H3: Manhattan * cost mínim + penalització estimada
    public static double h3(Estat actual, Estat fi) {
        int dx = Math.abs(actual.x - fi.x);
        int dy = Math.abs(actual.y - fi.y);
        return (dx + dy) * 0.5 + 3;
    }
}