package com.consulting_Arc.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.consulting_Arc.dao.EmployeeDAO;
import com.consulting_Arc.db.DBConnection;
import com.consulting_Arc.entity.Employee;

@WebServlet("/updateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// get all data which is coming from consultant.jsp doctor details
			String fullName = req.getParameter("fullName");
			String dateOfBirth = req.getParameter("dateOfBirth");
			String qualification = req.getParameter("qualification");
			String specialist = req.getParameter("specialist");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			String password = req.getParameter("password");
			
			//here need to get id also...for updating the consultant details
			//consultant will update based on respective doctor's id
			int id = Integer.parseInt(req.getParameter("id"));

			Employee employee = new Employee(id, fullName, dateOfBirth, qualification, specialist, email, phone, password);

			EmployeeDAO empDAO = new EmployeeDAO(DBConnection.getConn());

			boolean f = empDAO.updateEmployee(employee);

			HttpSession session = req.getSession();

			if (f == true) {
				session.setAttribute("successMsg", "Consultant update Successfully");
				resp.sendRedirect("admin/view_consultant.jsp");

			} else {
				session.setAttribute("errorMsg", "Something went wrong on server!");
				resp.sendRedirect("admin/view_consultant.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
