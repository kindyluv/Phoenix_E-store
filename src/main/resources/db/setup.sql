create database phoenix_db;
create user 'phoenix_user'@'localhost' identified by 'loisdiz244';
grant all privileges on phoenix_db.* to 'phoenix'@'localhost';
flush privileges