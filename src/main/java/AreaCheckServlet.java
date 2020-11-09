import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        resp.setContentType("text/html;charset=UTF-8");
        List<String[]> currentTable = (LinkedList)session.getAttribute("table");

        if(!"show".equals(req.getParameter("key"))){
            String errorMessage = "";

            float x = 66.0F;
            try {
                x = Float.parseFloat(req.getParameter("x"));
                if (x < -5.0F || x > 3.0F) {
                    errorMessage = errorMessage + "Ошибка! X координата должна быть [-5 ; 5]\n";
                }
                try {
                    if (String.valueOf(req.getParameter("x")).split("\\.")[1].length() > 6) {
                        errorMessage = errorMessage + "Ошибка! Нельзя вводить более 6 цифр в дробной части числа";
                    }
                }catch (ArrayIndexOutOfBoundsException e){}
            } catch (NumberFormatException e) {
                errorMessage = errorMessage + "Ошибка! X координата должен быть числом\n";
            }

            float y = 66.0F;
            try {
                y = Float.parseFloat(req.getParameter("y"));
                if (y >= 5.0F || y <= -5.0F) {
                    errorMessage = errorMessage + "Ошибка! Y координата должна быть (-5 ; 5)\n";
                }
                try {
                    if (String.valueOf(req.getParameter("y")).split("\\.")[1].length() > 6) {
                        errorMessage = errorMessage + "Ошибка! Нельзя вводить более 6 цифр в дробной части числа";
                    }
                }catch (ArrayIndexOutOfBoundsException e){}
            } catch (NumberFormatException e) {
                errorMessage = errorMessage + "Ошибка! Y координата должна быть числом\n";
            }

            float r = 66.0F;
            try {
                r = Float.parseFloat(req.getParameter("r"));
                if (r < 1.0F || r > 5.0F) {
                    errorMessage = errorMessage + "Ошибка! Радиус должен быть [1 ; 5]\n";
                }
                try {
                    if (String.valueOf(req.getParameter("r")).split("\\.")[1].length() > 6) {
                        errorMessage = errorMessage + "Ошибка! Нельзя вводить более 6 цифр в дробной части числа";
                    }
                }catch (ArrayIndexOutOfBoundsException e){}
            } catch (NumberFormatException e) {
                errorMessage = errorMessage + "Ошибка! Радиус должен быть числом\n";
            }

            if (!errorMessage.equals("")) {
                out.println(errorMessage);
            } else {
                String[] row = new String[6];
                row[0] = LocalDateTime.now().toString();
                row[1] = String.valueOf(System.nanoTime() - Long.parseLong(req.getAttribute("time").toString()));
                row[2] = String.valueOf(x);
                row[3] = String.valueOf(y);
                row[4] = String.valueOf(r);
                row[5] = String.valueOf(check(x, y, r));

                if (session.getAttribute("table") == null){
                    session.setAttribute("table", new LinkedList<>());
                    currentTable = (LinkedList)session.getAttribute("table");
                }

                currentTable.add(row);
                session.setAttribute("table", currentTable);
            }
        }
        printTable(out, currentTable);
    }

    private void printTable(PrintWriter out, List<String[]> currentTable){
        if (currentTable != null){
            for (String[] point: currentTable){
                out.println("<tr>");
                out.println("<td>" + point[0] + "</td>");
                out.println("<td>" + point[1] + " мкс" + "</td>");
                out.println("<td>" + point[2] + "</td>");
                out.println("<td>" + point[3] + "</td>");
                out.println("<td>" + point[4] + "</td>");
                out.println("<td>" + point[5] +  "</td>");
                out.println("</tr>");
            }
        }
    }

    private boolean check(float x, float y, float r) {

        return (x <= 0 && y >= 0 && y <= 2*x + r ) ||
                (x >= 0 && y >= 0 && (x * x) + (y * y) <= ((r * r) / 4)) ||
                (x <= 0 && y <= 0 && x >= -r && y >= -r/2);

    }
}