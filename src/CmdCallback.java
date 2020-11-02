import java.util.List;

public interface CmdCallback {
    void onResult(Command cmd, List<String> results);
}
