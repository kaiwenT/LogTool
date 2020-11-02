import java.io.File;

public class GetLogsExecutor extends CmdExecutor{
    @Override
    public void execCmd(String serialNumber, String remoteDir, String localDir, CmdCallback callback) {
        super.execCmd("adb -s " + serialNumber + " pull " + remoteDir + " ", new File(localDir), callback);
    }
}
