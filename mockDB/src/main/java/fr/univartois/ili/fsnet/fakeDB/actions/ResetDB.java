package fr.univartois.ili.fsnet.fakeDB.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univartois.ili.fsnet.fakeDB.DBUtils;

@WebServlet("/ResetDB")
public class ResetDB extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		DBUtils.resetDB();
		resp.sendRedirect("index.jsp");
	}
	
}
