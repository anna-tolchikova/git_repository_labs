package by.bsuir.forlabs.commandfactory;

import by.bsuir.forlabs.commands.*;

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
    },
    INDEX_ERROR404 {
        {
            this.command = new IndexError404Command();
        }
    },
    INDEX_EXCEPTION {
        {
            this.command = new IndexExceptionCommand();
        }
    },
    INDEX_LOGIN {
        {
            this.command = new IndexLoginCommand();
        }
    },
    INDEX_ADMIN_HOME {
        {
            this.command = new IndexAdminHomeCommand();
        }
    },
    INDEX_CLIENT_HOME {
        {
            this.command = new IndexClientHomeCommand();
        }
    };

    Command command;
    public Command getCurrentCommand() {
        return command;
    }
}