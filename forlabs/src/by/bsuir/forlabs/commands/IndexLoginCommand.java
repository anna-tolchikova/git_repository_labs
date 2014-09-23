package by.bsuir.forlabs.commands;

import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class IndexLoginCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty("path.page.login");

        return page;
    }
}
