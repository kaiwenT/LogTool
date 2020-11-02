import java.io.File;
import java.lang.ref.WeakReference;
import java.util.*;

/**
 * 1.取日志 创建文件夹，执行命令 adb -s xx pull remoteDir localDir
 * 2.获取设备列表-----设备sn列表-->devName列表
 * 3.重启设备
 * 4.连接设备
 * 5.push apk
 */
public class MainApp implements CmdCallback{
    private static WeakReference<MainApp> reference;

    private CmdExecutor cmdExecutor;

    private List<String> devSerialNums;

    private List<String> devNames;

    private MainApp() {
        devSerialNums = new ArrayList<>();
        devNames = new ArrayList<>();
    }

    public static MainApp getInstance() {
        if (reference == null || reference.get() == null) {
            reference = new WeakReference<>(new MainApp());
        }
        return reference.get();
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
        cmdExecutor.execCmd(serialNumber, "", logDir, this);
    }

    public void getDevices() {
        devSerialNums.clear();
        cmdExecutor = new GetDevicesExecutor();
        cmdExecutor.execCmd(null, null, null, this);
    }



    public static void main(String[] args) throws Exception {
        System.out.println(UUID.randomUUID());
    }


    @Override
    public void onResult(Command cmd, List<String> results) {
        switch (cmd) {
            case GET_DEVICES:
                onGetDevices(results);
                break;
            case GET_DEVICE_NAME:
                onGetDeviceName(results);
                break;
        }
    }

    private void onGetDeviceName(List<String> results) {
        if (results.size() == 0) {
            return;
        }

        devNames.add(results.get(0));
        if (devNames.size() == devSerialNums.size()) {
            // 显示到JCombox
        }
    }

    private void onGetDevices(List<String> results) {
        if (results.size() <= 1) {
            return;
        }
        results.remove(0);

        devNames.clear();

        for (String sn : results) {
            devSerialNums.add(sn);
            getDeviceName(sn);
        }
    }

    private void getDeviceName(String sn) {
        cmdExecutor = new GetDevNameExecutor();
        cmdExecutor.execCmd(sn, "", null, this);
    }
}

