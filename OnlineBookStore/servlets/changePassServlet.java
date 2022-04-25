package servlets;

import javax.servlet.*;

import constants.IBookExchangeConstants;
import sql.IUserContants;

import java.io.*;
import java.sql.*;

public class changePassServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType(IBookExchangeConstants.CONTENT_TYPE_TEXT_HTML);
		String uName = req.getParameter(IUserContants.COLUMN_USERNAME);
		String mailId = req.getParameter(IUserContants.COLUMN_MAILID);
		String pWord = req.getParameter(IUserContants.COLUMN_PASSWORD);
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("UPDATE" + IUserContants.TABLE_USERS +"SET"+ IUserContants.COLUMN_PASSWORD+ "=?" + " WHERE "
					+ IUserContants.COLUMN_USERNAME + "=? AND " + IUserContants.COLUMN_MAILID + "=? AND " + IUserContants.COLUMN_USERTYPE + "=2");
			ps.setString(2, pWord);
			ps.setString(1, uName);
			ps.setString(7, mailId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
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