# ToDoAppFX
How to run the app? 

1) Need to have MySQL, a Java SDK and JavaFX installed(MySQL version above 8 and Java SDK version above 17 should be fine).
  
2) Have the MySQL driver for JDBC downloaded and copy-pasted in the path of src/lib.

3) Make a database called 'newschema' using the code:

   create database newschema;
   
4) In that make a table running this code in it:

CREATE TABLE task_details (
    taskID INT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    date DATE,
    time TIME,
    Completed BOOLEAN
);

5) Almost done. Put your MySQL password in the TheTaskApp.java file in the places that need it. They look like this

   String jdbcUrl = "jdbc:mysql://localhost:3306/newschema";
        String username = "root";
        String password = "";//Add your MySQL password here

6) Can't say if you have all the required dependencies so do check them from the Maven website.



   HOPE you are able to run the application. Do contact me for any problems and if you want to collaborate on something relating these technologies.
    
