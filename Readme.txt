注意：
        源码运行步奏：
	（1）将项目导入到IDE中，配置好MySQL驱动；
	（2）打开项目中com.sqlservice包下的DriveSQL.java文件，将里面URL
中密码修改为您的数据的密码；具体如下：
private String url = "jdbc:mysql://127.0.0.1:3306/mysql?"
+ "user=root&password=（您的数据库密码）&useUnicode=true&characterEncoding=UTF8"; // Create			
	（3）进入控制台，登录MySQL数据库，登录后，进入mysql仓库；
	（4）打开项目中com.data包下的类sqlTable.java文件，将上面的注释命令
分别粘贴到控制台下，创建这些表名和数据（技术有限,没有在程序中创建，谅解）。
	（5）创建好后，便可运行了。
由于时间有限，并没增加用户注册功能。
如有疑问？
请加Q联系我：2293952179