# SpringBoot_GamesAPI (In process)
After having gathered some experience, I am working on making an API from scratch with data insertion in a H2 database.

##Index
http://localhost:8080/index

##H2 Database
http://localhost:8080/h2-console
###Credentials database
- url: jdbc:h2:mem:mydb
- user: sa
- password:


##Steps
1. You need register or nav button (you can invent credentials)
2. Login with email and password.
3. Select role (ROLE_ADMIN, ROLE_USER).

#Roles
###Role admin(in process)
- You can delete games.
- You can access to users and delete users ( not your user).
###Role user
- Only can view elements (no user list), throw 403 ERROR.