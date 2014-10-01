package by.bsuir.forlabs.commandfactory;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.commands.admin.ApproveRequestCommand;
import by.bsuir.forlabs.commands.admin.IndexAdminApplicationCommand;
import by.bsuir.forlabs.commands.admin.IndexAdminApplicationsCommand;
import by.bsuir.forlabs.commands.admin.IndexAdminHomeCommand;
import by.bsuir.forlabs.commands.client.IndexClientHomeCommand;
import by.bsuir.forlabs.commands.common.ChangeLocaleCommand;
import by.bsuir.forlabs.commands.common.IndexLoginCommand;
import by.bsuir.forlabs.commands.common.LoginCommand;
import by.bsuir.forlabs.commands.common.LogoutCommand;
import by.bsuir.forlabs.commands.errors.IndexError403Command;
import by.bsuir.forlabs.commands.errors.IndexError404Command;
import by.bsuir.forlabs.commands.errors.IndexExceptionCommand;

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
    APPROVE_REQUEST {
        {
            this.command = new ApproveRequestCommand();
        }

    },
    INDEX_ERROR403 {
        {
            this.command = new IndexError403Command();
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
    },INDEX_ADMIN_APPLICATION {
        {
            this.command = new IndexAdminApplicationCommand();
        }
    },
    INDEX_ADMIN_APPLICATIONS {
        {
            this.command = new IndexAdminApplicationsCommand();
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