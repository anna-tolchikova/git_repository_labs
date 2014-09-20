package by.bsuir.forlabs.commandfactory;

import by.bsuir.forlabs.commands.ChangeLocaleCommand;
import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.commands.LoginCommand;
import by.bsuir.forlabs.commands.LogoutCommand;

public enum CommandEnum {

    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    CHANGELOCALE {
        {
            this.command = new ChangeLocaleCommand();
        }
    };

    Command command;
    public Command getCurrentCommand() {
        return command;
    }
}