# Contribute
## How to set-up project

### Step 1 - Installation of programs

1.   Install newest version of Java. 
2.   Install IntelliJ.
3.   Install MySQL Workbench.
4.   Install Git.

### Step 2 - Set-up database
1.   Find scripts for SQL database.
       - They can be found in the SQL folder, in the root of the directory.
2.   Make sure the to run the scripts in your MySQL Workbench.

### Step 3 - Connect to github project
1.   Get added to the project by other project member.
2.   Clone the project in IntelliJ.
3.   Create you own **applications.propperties**
       - **IMPORTANT**
       *DON'T* add the file to git.
5.   Use the following application.propperties 
template:

```
url=jdbc:mysql://localhost:3306/wishlists?serverTimezone=UTC
username=<username>
password=<password>

wishlist.repository.impl=Wishlist_DB
server.error.whitelabel.enabled=false
```

Copy-paste the content into your own **application.properties** file, and change username and password to your own database.
      

### Step 4 - Test program
1.   Test the program using the web application to make sure it works. If any errors occour, check if there are any missed steps.

    
    
