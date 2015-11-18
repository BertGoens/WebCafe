package servlets;

import dao.EventDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.Event;

/* @author BertGoens */
public class Archive extends HttpServlet {

    int pageNumber;
    double totalPages;
    List<Event> pastEventList;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("archive.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getRequestedPageNumber(request); //pagina id krijgen

        getPastEvents(); //db aanvraag voor verleden events

        request.setAttribute("pastEventList", pastEventList);

        getPageCount(); //hoeveel pagina links moet ik maken?

        request.setAttribute("pageLinks", totalPages);

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void getRequestedPageNumber(HttpServletRequest request) {
        String page = request.getParameter("page");
        pageNumber = 0;
        if (page != null && page.matches("[-+]?\\d*\\.?\\d+")) {
            pageNumber = Integer.parseInt(page);
        }
    }

    private void getPastEvents() {
        EventDao daoEvent = new EventDao();
        pastEventList = daoEvent.getPastEvents();
        return;
    }

    private void getPageCount() {
        totalPages = pastEventList.size() / 5;
        totalPages = Math.ceil(totalPages);
    }

}
