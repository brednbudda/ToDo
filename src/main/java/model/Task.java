package model;

import java.time.LocalDate;

/*
 * Java class to represent a Task object, which is the building block of the
 * To Do list. Attributes of the Tasks will be used to generate different views
 * within the app depending on dueDate, projectName, status, etc.
 */

public class Task {
	
	protected int id;
	protected String title;
	protected LocalDate dueDate;
	protected String projectName;
	protected boolean status;
	
	public Task() {}

	public Task(String title, String projectName, LocalDate dueDate) {
		this.title = title;
		this.projectName = projectName;
		this.dueDate = dueDate;
		this.status = false;
	}
	
	public Task(int id, String title, String projectName, LocalDate dueDate) {
		this.id = id;
		this.title = title;
		this.dueDate = dueDate;
		this.projectName = projectName;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public int boolToInt(boolean bStatus) {
		int iStatus = bStatus ? 1 : 0;
		return iStatus;
	}
	
	public boolean intToBool(int iStatus) {
		boolean bStatus = iStatus == 1 ? true : false;
		return bStatus;
		
	}
}
