import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CmdController {
    private CmdExecutor cmdExecutor;

    private CmdCallback callback;

    private List<String> devSerialNums;

    private List<String> devNames;

    public CmdController(CmdCallback callback) {
        this.callback = callback;
        devSerialNums = new ArrayList<>();
        devNames = new ArrayList<>();
    }

    public List<String> getDevSerialNums() {
        return devSerialNums;
    }

    public void clearDevSerialNums() {
        devSerialNums.clear();
    }

    public void addDevSerialNum(String sn) {
        devSerialNums.add(sn);
    }

    public List<String> getDevNames() {
        return devNames;
    }

    public void addDevName(String sn) {
        devNames.add(sn);
    }

    public void clearDevNames() {
        devNames.clear();
    }

    public boolean isNameMatchSn() {
        return devNames.size() == devSerialNums.size();
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

    public void getDeviceName(String sn) {
        cmdExecutor = new GetDevNameExecutor();
        cmdExecutor.execCmd(sn, "", null, callback);
    }

}
