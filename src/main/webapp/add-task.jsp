<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<a class="button" href="<%=request.getContextPath()%>/list" class="nav-link">Tasks</a>
				</div>

                <div class="inner-add">
					<c:if test="${task != null}">
						<form action="update" method="post">
					</c:if>
					<c:if test="${task == null}">
						<form action="insert" method="post">
					</c:if>

					<caption>
						<h2>
							<c:if test="${task != null}">
								Edit Task
							</c:if>
							<c:if test="${task == null}">
								Add New Task
							</c:if>
						</h2>
					</caption>

					<c:if test="${task != null}">
						<input type="hidden" name="id" value="<c:out value='${task.id}' />" />
					</c:if>

					<fieldset>
						<label>Task Name</label> <input type="text" value="<c:out value='${task.title}' />" name="title" required="required">
					</fieldset>

					<fieldset>
						<label>Project Name</label> <input type="text" value="<c:out value='${task.projectName}' />" name="projectName">
					</fieldset>

					<fieldset>
						<label>Due Date</label> <input type="date" value="<c:out value='${task.dueDate}' />" name="dueDate">
					</fieldset>

					<div class="inner-box">
						<button class="button" type="submit">Save</button>
					</div>
					</form>
                </div>
            </main>
        </body>

        </html>