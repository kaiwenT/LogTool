import java.io.*;

public abstract class CmdExecutor {
    public abstract void execCmd(String serialNumber, String remoteDir, String localDir, CmdCallback callback);

    public void execCmd(String cmd, File dir, CmdCallback callback) {
        System.out.println(cmd);
    }
}
