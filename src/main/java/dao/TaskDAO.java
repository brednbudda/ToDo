package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import model.Task;

//Connection information for MySQL
public class TaskDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/to-do?useSSL=false";
    private String jdbcUsername = "USERNAME";
    private String jdbcPassword = "PASSWORD";

    //SQL strings for Prepared Statements for CRUD operations
    private static final String INSERT_TASK = "INSERT INTO tasks (title, projectName, dueDate) VALUES (?, ?, ?);";
    private static final String SELECT_ALL_TASKS = "SELECT * FROM tasks;";
    private static final String SELECT_TASK_BY_ID = "SELECT * FROM tasks WHERE id = ?;";
    private static final String UPDATE_TASK = "UPDATE tasks set title = ?, projectName = ?, dueDate = ?, status = 0 WHERE id = ?;";
    private static final String DELETE_TASK = "DELETE FROM tasks WHERE id = ?;";
    private static final String COMPLETE_TASK = "UPDATE tasks set status = 1 WHERE id = ?;";

    public TaskDAO() {}
    
    //Method to create new connection to database
    protected Connection getConnection() {
    	Connection newConnection = null;
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		newConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	return newConnection;
    }
    
    public void insertTask(Task task) throws SQLException {
    	try(Connection newConnection = getConnection(); 
    			PreparedStatement newPreparedStatement = newConnection.prepareStatement(INSERT_TASK);) {
    		newPreparedStatement.setString(1, task.getTitle());
    		newPreparedStatement.setString(2, task.getProjectName());
    		newPreparedStatement.setObject(3, Date.valueOf(task.getDueDate()));
    		newPreparedStatement.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public List <Task> selectAllTasks()	{
    	List<Task> taskList = new ArrayList<Task>();
    	try(Connection newConnection = getConnection(); 
    			PreparedStatement newPreparedStatement = newConnection.prepareStatement(SELECT_ALL_TASKS);) {
    		ResultSet rs = newPreparedStatement.executeQuery();
    		
    		while (rs.next()) {
    			Task task = new Task();
    			task.setId(rs.getInt("id"));
    			task.setTitle(rs.getString("title"));
    			task.setProjectName(rs.getString("projectName"));
    			task.setDueDate(rs.getDate("dueDate").toLocalDate());
    			task.setStatus(task.intToBool(rs.getInt("status")));
    			taskList.add(task);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return taskList;
    }
    
    public Task selectTask(long queryId) {

    	Task task = null;

    	try(Connection newConnection = getConnection(); 
    			PreparedStatement newPreparedStatement = newConnection.prepareStatement(SELECT_TASK_BY_ID);) {
    		newPreparedStatement.setLong(1, queryId);
    		ResultSet rs = newPreparedStatement.executeQuery();
    		
    		while (rs.next()) {
    			task = new Task();
    			task.setId(rs.getInt("id"));
    			task.setTitle(rs.getString("title"));
    			task.setProjectName(rs.getString("projectName"));
    			task.setDueDate(rs.getDate("dueDate").toLocalDate());
    			task.setStatus(task.intToBool(rs.getInt("status")));
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}

    	return task;
    }
    
    public boolean updateTask(Task task) throws SQLException {
    	boolean taskUpdated = false;
    	try(Connection newConnection = getConnection(); 
    			PreparedStatement newPreparedStatement = newConnection.prepareStatement(UPDATE_TASK);) {
    		newPreparedStatement.setString(1, task.getTitle());
    		newPreparedStatement.setString(2, task.getProjectName());
    		newPreparedStatement.setDate(3, Date.valueOf(task.getDueDate()));
    		newPreparedStatement.setInt(4, task.getId());

    		taskUpdated = newPreparedStatement.executeUpdate() > 0;

    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return taskUpdated;
    }
    
    public boolean completeTask(int id) throws SQLException {
    	boolean taskComplete = false;
    	try(Connection newConnection = getConnection(); 
    			PreparedStatement newPreparedStatement = newConnection.prepareStatement(COMPLETE_TASK);) {
    		newPreparedStatement.setInt(1, id);
    		
    		taskComplete = newPreparedStatement.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return taskComplete;
    }

    public boolean deleteTask(int id) throws SQLException {
    	boolean taskDeleted = false;
    	try(Connection newConnection = getConnection(); 
    			PreparedStatement newPreparedStatement = newConnection.prepareStatement(DELETE_TASK);) {
    		newPreparedStatement.setInt(1, id);
    		
    		taskDeleted = newPreparedStatement.execute();

    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return taskDeleted;
    }

}
