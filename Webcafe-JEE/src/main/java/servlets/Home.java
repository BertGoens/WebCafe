package servlets;

import dao.EventDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.Event;

/* @author BertGoens */
public class Home extends HttpServlet {

    private int currentPage;
    private int endPage;
    private List<Event> allComingEvents;
    private List<Event> next5Events;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getRequestedPageNumber(request); //pagina id krijgen
        request.setAttribute("currentPage", currentPage);

        getNextEvents(); //db aanvraag voor komende events
        getNext5Events(); //sorteer 5 volgende
        request.setAttribute("nextEventList", next5Events);

        getPageCount(); //hoeveel pagina links moet ik maken?
        request.setAttribute("endPage", endPage);

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void getRequestedPageNumber(HttpServletRequest request) {
        String page = request.getParameter("page");
        currentPage = 0;
        if (page != null && page.matches("[+]?\\d*\\.?\\d+")) {
            currentPage = Integer.parseInt(page);
            currentPage--;
        }
    }

    private void getNextEvents() {
        EventDao daoEvent = new EventDao();
        allComingEvents = daoEvent.getComingEvents();
    }

    private void getNext5Events() {
        next5Events = new ArrayList<>();
        int start, end;
        start = currentPage * 5;
        end = start + 4;
        for (int i = start; i < end; i++) {
            if (i < allComingEvents.size()) {
                next5Events.add(allComingEvents.get(i));
            } else {
                break;
            }
        }
    }

    private void getPageCount() {
        double totalPages = allComingEvents.size() / 5;
        totalPages = Math.ceil(totalPages);
        endPage = (int) totalPages;
    }

}
