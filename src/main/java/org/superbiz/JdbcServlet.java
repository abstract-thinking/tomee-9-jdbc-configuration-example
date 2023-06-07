package org.superbiz;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.annotation.Resource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.SQLException;


public class JdbcServlet extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 8229511817604046163L;

	@Resource(name = "Derby Database")
	private DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		try(final Connection connection = dataSource.getConnection()){
			
			boolean valid = connection.isValid(1000);
			writer.println("\n JDBC Connection is " + (valid ? "valid" : "NOT valid") + " @ timestamp = "+ System.currentTimeMillis());
			
			return;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.println("\n Unable to Connect");
	}
}