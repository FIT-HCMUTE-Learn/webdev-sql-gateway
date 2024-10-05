package murach.sql;

import java.sql.*;

public class SQLUtil
{
    public static String getHtmlTable(ResultSet results)
            throws SQLException
    {
        StringBuilder htmlTable = new StringBuilder();
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();

        htmlTable.append("<table class=\"table table-hover\">");
        
        // add header row
        htmlTable.append("<thead class=\"table-secondary\"> <tr>");
        for (int i = 1; i <= columnCount; i++) {
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnName(i));
            htmlTable.append("</th>");
        }
        htmlTable.append("</tr> </thead>");
        
        // add all other rows
        htmlTable.append("<tbody>");
        while (results.next())
        {
            htmlTable.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                htmlTable.append("<td>");
                htmlTable.append(results.getString(i));
                htmlTable.append("</td>");
            }
            htmlTable.append("</tr>");
        }
        htmlTable.append("</tbody>");
        
        htmlTable.append("</table>");
        return htmlTable.toString();
    }    
}