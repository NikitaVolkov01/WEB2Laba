import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

public class ControllerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long requestStartTime = System.nanoTime();
        request.setAttribute("time", requestStartTime);
        super.service(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String errorMessage = "";

        if ("graph".equals(req.getParameter("key")) || "form".equals(req.getParameter("key"))){
            if (req.getParameter("x") == null) {
                errorMessage = errorMessage + "Ошибка! Данные о координате X не получены \n";
            }
            if (req.getParameter("y") == null) {
                errorMessage = errorMessage + "Ошибка! Данные о координате Y не получены)\n";
            }

            if (req.getParameter("r") == null) {
                errorMessage = errorMessage + "Ошибка! Данные о координате R не получены\n";
            }

            if (errorMessage.equals("")) {
                req.getRequestDispatcher("checker").forward(req, resp);
            } else {
                resp.getWriter().println(errorMessage);
            }
        }else if("show".equals(req.getParameter("key"))){
            req.getRequestDispatcher("checker").forward(req, resp);
        }else {
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

   @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp){
       req.getSession().setAttribute("table", new LinkedList<>());
   }
}