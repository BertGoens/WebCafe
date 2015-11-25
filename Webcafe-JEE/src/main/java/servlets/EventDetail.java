package servlets;

import dao.EventDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.Event;

/* @author BertGoens */
public class EventDetail extends HttpServlet {

    private Event detailEvent;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String eventId = request.getParameter("id");
        if (eventId != null && eventId.matches("[+]?\\d*\\.?\\d+")) {
            EventDao daoEvent = new EventDao();
            int id = Integer.parseInt(eventId);
            detailEvent = daoEvent.findById(id);
            request.setAttribute("detailEvent", detailEvent);
        } else {
            detailEvent = (Event) request.getAttribute("nextEvent");
            request.setAttribute("detailEvent", detailEvent);
        }

        request.getRequestDispatcher("/eventDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("eventDetail.jsp").forward(request, response);
    }

}
