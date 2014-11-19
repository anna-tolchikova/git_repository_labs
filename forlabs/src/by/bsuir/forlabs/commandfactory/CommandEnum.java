package by.bsuir.forlabs.commandfactory;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.commands.admin.*;
import by.bsuir.forlabs.commands.admin.index.*;
import by.bsuir.forlabs.commands.client.AddRequestCommand;
import by.bsuir.forlabs.commands.client.PayForRentalCommand;
import by.bsuir.forlabs.commands.client.index.IndexCategorySpecificationsByClientCommand;
import by.bsuir.forlabs.commands.client.index.IndexClientHomeCommand;
import by.bsuir.forlabs.commands.client.index.IndexSpecificationsCommand;
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
    REJECT_REQUEST {
        {
            this.command = new RejectRequestCommand();
        }

    },
    RETURN_CAR {
        {
            this.command = new ReturnCarCommand();
        }

    },
    SET_REPAIR_BILL {
        {
            this.command = new SetRepairBillCommand();
        }

    },
    EDIT_CAR {
        {
            this.command = new EditCarCommand();
        }

    },
    DELETE_CAR {
        {
            this.command = new DeleteCarCommand();
        }

    },
    ADD_CAR {
        {
            this.command = new AddCarCommand();
        }

    },
    ADD_SPECIFICATIONS {
        {
            this.command = new AddSpecificationsCommand();
        }

    },
    PAY_BILL {
        {
            this.command = new PayForRentalCommand();
        }

    },
    ADD_REQUEST {
        {
            this.command = new AddRequestCommand();
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
            this.command = new IndexHomeCommand();
        }
    },
    INDEX_ADMIN_APPLICATION {
        {
            this.command = new IndexApplicationCommand();
        }
    },
    INDEX_ADMIN_APPLICATIONS {
        {
            this.command = new IndexApplicationsCommand();
        }
    },
    INDEX_ADMIN_CATEGORY_SPECIFICATIONS {
        {
            this.command = new IndexCategorySpecificationsCommand();
        }
    },
    INDEX_ADMIN_CATEGORY_SPECIFICATIONS_ADD {
        {
            this.command = new IndexAddSpecificationsCommand();
        }
    },
    INDEX_ADMIN_CATEGORY_SPECIFICATIONS_ADD_RESULT {
        {
            this.command = new IndexAddSpecificationsResultCommand();
        }
    },
    INDEX_ADMIN_SPECIFICATIONS_CARS {
        {
            this.command = new IndexCarsInSpecificationsCommand();
        }
    },
    INDEX_CLIENT_HOME {
        {
            this.command = new IndexClientHomeCommand();
        }
    },
    INDEX_CLIENT_SPECIFICATIONS {
        {
            this.command = new IndexSpecificationsCommand();
        }
    },
    INDEX_CLIENT_CATEGORY_SPECIFICATIONS {
        {
            this.command = new IndexCategorySpecificationsByClientCommand();
        }
    };

    Command command;
    public Command getCurrentCommand() {
        return command;
    }
}