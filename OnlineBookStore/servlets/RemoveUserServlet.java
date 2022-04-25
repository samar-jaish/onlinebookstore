package servlets;

import java.sql.*;
import javax.servlet.*;

import sql.IBookConstants;
import sql.IUserContants;

import java.io.*;

public class RemoveUserServlet extends GenericServlet {

	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String uName = req.getParameter("username");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps =con.prepareStatement(
					"delete from " + IUserContants.TABLE_USERS + "  where " + IUserContants.COLUMN_USERNAME + "=?");
			ps.setString(1, uName);
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">User Removed Successfully</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveUser.html\">Remove more User</a></div>");

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">User Not Available In The Store</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveUser.html\">Remove more User</a></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}