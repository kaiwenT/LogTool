import java.io.File;
import java.util.List;

public class TestExecutor extends CmdExecutor{

    @Override
    public void execCmd(String serialNumber, String remoteDir, String localDir, CmdCallback callback) {
        System.out.println("TestExecutor exe");
        super.execCmd("py -v ", null, callback);
    }
}
