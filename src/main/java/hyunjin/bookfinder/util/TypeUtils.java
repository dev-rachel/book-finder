package hyunjin.bookfinder.util;

public class TypeUtils {

    public static int getDefault(Number n, int defaultValue) {

        return n == null ? defaultValue : n.intValue();
    }

}
