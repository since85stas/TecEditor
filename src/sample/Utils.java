package sample;

public class Utils {

    public static boolean checkTimestep(int step) {
        if (step < 0) {
            return false;
        } else {
            String stepStr = String.valueOf(step);
            if (stepStr.length() > 0 && stepStr.length() < 7) {
                return true;
            } else {
                return false;
            }
        }
    }

}
