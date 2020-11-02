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

    private CmdController cmdController;

    private MainApp() {
        cmdController = new CmdController(this);
    }

    public static MainApp getInstance() {
        if (reference == null || reference.get() == null) {
            reference = new WeakReference<>(new MainApp());
        }
        return reference.get();
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

        cmdController.addDevName(results.get(0));
        if (cmdController.isNameMatchSn()) {
            // 显示到JCombox
        }
    }

    private void onGetDevices(List<String> results) {
        if (results.size() <= 1) {
            return;
        }
        results.remove(0);

        cmdController.clearDevNames();

        for (String sn : results) {
            cmdController.addDevSerialNum(sn);
            cmdController.getDeviceName(sn);
        }
    }
}

