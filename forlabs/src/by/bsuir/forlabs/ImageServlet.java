package by.bsuir.forlabs;

import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.admin.ShowCategoryIndexLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

public class ImageServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        byte[] content = null;

        try {
            if (id!=null && !("null").equals(id) && (content = ShowCategoryIndexLogic.findImage(Integer.parseInt(id)))!= null) {
                response.setContentType("image/gif");
                response.setContentLength(content.length);
                response.getOutputStream().write(content);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            }
        } catch (LogicalException e) {
            printException(e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
        }
    }
}
