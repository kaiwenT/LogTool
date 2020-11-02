import java.io.File;
import java.util.List;

public class CmdController {
    private CmdExecutor cmdExecutor;

    private CmdCallback callback;

    private List<String> devSerialNums;

    private List<String> devNames;

    public CmdController(CmdCallback callback) {
        this.callback = callback;
    }


    public void getLogs() {
        String appName = "";
        String mainLogDir = "D:\\00_日志";

        String appDir = mainLogDir + File.pathSeparator + appName;
        Utils.mkdir(appDir);

        String timeStr = Utils.getSysTimeStr();
        String logDir = appDir + File.pathSeparator + "LOGS" + timeStr;
        Utils.mkdir(logDir);

        cmdExecutor = new GetDevicesExecutor();
        String serialNumber = "serialNumber";
        cmdExecutor.execCmd(serialNumber, "", logDir, callback);
    }

    public void getDevices() {
        devSerialNums.clear();
        cmdExecutor = new GetDevicesExecutor();
        cmdExecutor.execCmd(null, null, null, callback);
    }

    private void getDeviceName(String sn) {
        cmdExecutor = new GetDevNameExecutor();
        cmdExecutor.execCmd(sn, "", null, callback);
    }

}
