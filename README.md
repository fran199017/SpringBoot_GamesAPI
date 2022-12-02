# SpringBoot_GamesAPI (In process)
After having gathered some experience,I am working on a new API Rest. This API downloads
from a public API a games. Enjoy!
##Technologies
- Spring Boot
- Thymeleaf
- Spring Security
- H2 Database
- MVC
- Swagger2

##Index
http://localhost:8080/index
or
http://localhost:8080/register

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

##Roles
###Role admin(in process)
- You can filter games by rating or like query name.
- You can delete games.
- You can access to users and delete users ( not your user).
###Role user
- Only can view elements (no user list).

###Some images
List of games.
![ScreenShot](https://raw.githubusercontent.com/fran199017/SpringBoot_GamesAPI/master/assets/img.png)
Filter.
![ScreenShot](https://raw.githubusercontent.com/fran199017/SpringBoot_GamesAPI/master/assets/img_1.png)
Result filter.
![ScreenShot](https://raw.githubusercontent.com/fran199017/SpringBoot_GamesAPI/master/assets/img_2.png)
List of users if ROLE_ADMIN result
![ScreenShot](https://raw.githubusercontent.com/fran199017/SpringBoot_GamesAPI/master/assets/img_3.png)
*List of users if ROLE_USER result
![ScreenShot](https://raw.githubusercontent.com/fran199017/SpringBoot_GamesAPI/master/assets/img_4.png)



