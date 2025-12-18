package CekelDuit.utill;

public class CurencyUtill {
    public static String format(double amount) {
        return "Rp " + String.format("%,.0f", amount);
    }
}
