package servlets;

import dao.EventDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.Event;

/* @author BertGoens */
public class Home extends HttpServlet {

    private int pageNumber;
    private List<Event> allComingEvents;
    private double totalPages;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getRequestedPageNumber(request); //pagina id krijgen

        getNextEvents(); //db aanvraag voor komende events

        request.setAttribute("nextEventList", allComingEvents);

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

    private void getNextEvents() {
        EventDao daoEvent = new EventDao();
        allComingEvents = daoEvent.getComingEvents();
    }

    private void getPageCount() {
        totalPages = allComingEvents.size() / 5;
        totalPages = Math.ceil(totalPages);
    }

}
