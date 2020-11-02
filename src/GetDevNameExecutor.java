import java.io.File;

public class GetDevNameExecutor extends CmdExecutor{
    @Override
    public void execCmd(String serialNumber, String remoteDir, String localDir, CmdCallback callback) {
        super.execCmd("adb -s " + serialNumber + " shell getprop ", null, callback);
    }
}
