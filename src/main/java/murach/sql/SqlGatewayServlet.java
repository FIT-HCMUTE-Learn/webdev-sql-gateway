package murach.sql;
 
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet(urlPatterns = {"/sql-gateway"})
public class SqlGatewayServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get a connection
        Connection connection = null;
        
        String sqlCommand = request.getParameter("sqlCommand");
        String sqlResult = "";
        try {
            // create a statement
            connection = ConnectionPool.getConnection();
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);

            // parse the SQL string
            sqlCommand = sqlCommand.trim();
            if (sqlCommand.length() >= 6) {
                String sqlType = sqlCommand.substring(0, 6);
                if (sqlType.equalsIgnoreCase("select")) {
                    // create the HTML for the result set
                    ResultSet resultSet = preparedStatement.executeQuery(sqlCommand);
                    sqlResult = SQLUtil.getHtmlTable(resultSet);
                    resultSet.close();
                } else {
                    int i = preparedStatement.executeUpdate(sqlCommand);
                    if (i == 0) { // a DDL statement
                        sqlResult = "<p>The statement executed successfully.</p>";
                    } else { // an INSERT, UPDATE, or DELETE statement
                        sqlResult = "<p>The statement executed successfully.<br>" + i + " row(s) affected.</p>";
                    }
                }
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
        } finally {
//            assert connection != null;
//            ConnectionPool.freeConnection(connection);
        }

        HttpSession session = request.getSession();
        session.setAttribute("sqlResult", sqlResult);
        session.setAttribute("sqlCommand", sqlCommand);

        String url = "/index.jsp";
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}