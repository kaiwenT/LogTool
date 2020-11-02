public enum Command {
    GET_DEVICES("devices"),
    GET_DEVICE_NAME("shell getprop");

    Command(String cmd) {
        this.cmd = cmd;
    }

    private String cmd;

    public String getCmd() {
        return cmd;
    }
}
