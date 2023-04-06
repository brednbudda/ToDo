# ToDo
To Do List Application based using JSP/Servlets

Application that accepts items in a to-do list format, where the user can add, edit, mark as complete, or delete the tasks on the list.

This application is built in consideration of an MVC architecture. It includes three classes.

The first class is a fairly straightforward Task object with several attributes such as name, date, status, etc.

The second class is a Data Access Object class that is used to perform actions against a MySQL database. 
  This DAO class includes a method for creating a connection, which is then used in individual methods for each seperate database action.
  
The third class is a Servlet class which is used to handle all the HTTP requests and responses.

The UI of the application includes two JSP pages. The first page is a list of n tasks with all the attributes in a chart. 
  From this chart the user can edit, mark as complete, and delete each individual task.
  There is also a button to add a new task which takes the user to the second JSP page which allows them to input information for a new task.
  
