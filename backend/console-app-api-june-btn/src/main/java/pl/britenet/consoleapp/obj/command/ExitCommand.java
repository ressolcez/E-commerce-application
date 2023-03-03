package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.Main;

public class ExitCommand extends Command{

    public ExitCommand(){
        super(Constants.COMMAND_NAME_EXIT);
    }

    @Override
    public void execute() {
        System.out.println("Koniec programu");
        Main.IS_ON = false;
    }
}
