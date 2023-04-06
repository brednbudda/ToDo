package web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TaskDAO;
import model.Task;

/*
 * Servlet class for to handle all requests for tasks from user.
 * @author Brendan Burke
 */

@WebServlet("/")
public class TaskServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private TaskDAO taskDAO;
	
	public void init() {
		taskDAO = new TaskDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		        doGet(request, response);
		    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertTask(request, response);
					break;
				case "/delete":
					deleteTask(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updateTask(request, response);
					break;
				case "/complete":
					completeTask(request, response);
				default:
					listTask(request, response);
					break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listTask(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		        List <Task> taskList = taskDAO.selectAllTasks();
		        request.setAttribute("listTask", taskList);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("tasks.jsp");
		        dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		        RequestDispatcher dispatcher = request.getRequestDispatcher("add-task.jsp");
		        dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
		        int id = Integer.parseInt(request.getParameter("id"));
		        Task currentTask = taskDAO.selectTask(id);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("add-task.jsp");
		        request.setAttribute("task", currentTask);
		        dispatcher.forward(request, response);

	}
	
	private void insertTask(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException {
		        String title = request.getParameter("title");
		        String dateString = request.getParameter("dueDate");
		        LocalDate dueDate = LocalDate.parse(dateString);
		        String projectName = request.getParameter("projectName");
		        Task newTask = new Task(title, projectName, dueDate);
		        taskDAO.insertTask(newTask);
		        response.sendRedirect("list");
	}
	
	private void updateTask(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException {
		        int id = Integer.parseInt(request.getParameter("id"));
		        String title = request.getParameter("title");
		        String projectName = request.getParameter("projectName");
		        LocalDate dueDate = LocalDate.parse(request.getParameter("dueDate"));

		        Task task = new Task(id, title, projectName, dueDate);

		        taskDAO.updateTask(task);
		        response.sendRedirect("list");
	}
	
	private void completeTask(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
				int id = Integer.parseInt(request.getParameter("id"));
				taskDAO.completeTask(id);
	}
	
	private void deleteTask(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException {
		        int id = Integer.parseInt(request.getParameter("id"));
		        taskDAO.deleteTask(id);
		        response.sendRedirect("list");
	}
}
