import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String getSysTimeStr() {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd_HH_mm_ss_SSS" );
        Date d= new Date();
        return sdf.format(d);
    }


    public static void mkdir(String logDir) {
        File logDirFile = new File(logDir);
        if (!logDirFile.exists()) {
            logDirFile.mkdirs();
        }
    }
}
