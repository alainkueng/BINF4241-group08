package commands;

import devices.Device;

public interface Command {
    public Device execute();
    public Device undo();
    public String toString();
}
