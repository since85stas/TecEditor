package sample;

public class Utils {

    public static boolean checkTimestep(String numb) {
        if (numb.length() > 0) {
            int step = Integer.parseInt(numb);
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
        } else {
            return false;
        }
    }

}
