package servlets;

import dao.EventDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Event;

/* @author BertGoens */
public class Home extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //pagina id krijgen & 5 correcte events in eventList steken
        //is page ingevuld?
        String page = request.getParameter("page");
        if (page != null && page.matches("[-+]?\\d*\\.?\\d+")) {
            int paginaId = Integer.parseInt(page);
        }

        List<Event> eventList = new ArrayList<>();
        List<Event> hardcodeEventList = (List<Event>) getServletContext().getAttribute("PremadeEventList");
        eventList.add(hardcodeEventList.get(0));

        request.setAttribute("eventList", eventList);

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
