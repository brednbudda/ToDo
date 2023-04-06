<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>To Do Application</title>
		<style>
			<%@include file="/resources/styles.css" %>
		</style>
	</head>
	
	<body>		

		<header>
			<div id="logo">
				<h1>To Do Application</h1>
			</div>
		</header>
				
		<main>
			<div class="inner-box">
				<a class="button" href="<%=request.getContextPath()%>/new">Add New Task</a>
			</div>
			<div class="inner-box">

			<table>
				<thead>
					<tr>
						<th>Title</th>
						<th>Project Name</th>
						<th>Due Date</th>
						<th>Status</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="task" items="${listTask}">
						<tr>
							<td hidden="hidden">
								<c:out value="${task.id}"/>
							</td>
							<td>
								<c:out value="${task.title}"/>
							</td>
							<td>
								<c:out value="${task.projectName}"/>
							</td>
							<td>
								<c:out value="${task.dueDate}"/>
							</td>
							<td>
								<c:choose>
									<c:when test="${task.status==true}">
										<input type="checkbox" name="status" checked onclick="return false;"/>
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="status" onclick="return false;"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<a class="inner-button" href="complete?id=<c:out value='${task.id}' />">Complete</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a class="inner-button" href="edit?id=<c:out value='${task.id}' />">Edit</a> 
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a class="inner-button" href="delete?id=<c:out value='${task.id}' />">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</main>
		
<p>${pageContext.servletContext.contextPath}/*</p>
	</body>
</html>

