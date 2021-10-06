
jdbc 的使用样例
TestStudent 的几个测试方法测试jdbc 的使用

方法在：StudentDao 与DbUtils 中

测试使用DriverManager ,DataSource  连接数据。

环境准备
sql
CREATE TABLE `student_t` (
`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(50) DEFAULT NULL,
`age` INT(11) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=INNODB 

数据库连接在application.properties 中
