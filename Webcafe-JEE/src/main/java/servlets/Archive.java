package servlets;

import dao.EventDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.Event;

/* @author BertGoens */
public class Archive extends HttpServlet {

    private int currentPage;
    private int endPage;
    private List<Event> allPastEvents;
    private List<Event> past5Events;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("archive.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getRequestedPageNumber(request); //pagina id krijgen
        request.setAttribute("currentPage", currentPage);

        getPastEvents(); //db aanvraag voor verleden events
        getPast5Events();
        request.setAttribute("pastEventList", past5Events);

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

    private void getPastEvents() {
        EventDao daoEvent = new EventDao();
        allPastEvents = daoEvent.getPastEvents();
    }

    private void getPast5Events() {
        past5Events = new ArrayList<>();
        int start, end;
        start = currentPage * 5;
        end = start + 4;
        for (int i = start; i < end; i++) {
            if (i < allPastEvents.size()) {
                past5Events.add(allPastEvents.get(i));
            } else {
                break;
            }
        }
    }

    private void getPageCount() {
        double totalPages = allPastEvents.size() / 5;
        totalPages = Math.ceil(totalPages);
        endPage = (int) totalPages;
    }

}
