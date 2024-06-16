CREATE DATABASE testdb DEFAULT CHARACTER SET utf8;

CREATE USER 'replication'@'%' IDENTIFIED BY 'replication';
GRANT REPLICATION SLAVE ON *.* TO 'replication'@'%';

CREATE USER orc_client_user@'172.%' IDENTIFIED BY 'orc_client_password';
GRANT SUPER, PROCESS, REPLICATION SLAVE, RELOAD ON *.* TO orc_client_user@'172.%';
GRANT SELECT ON mysql.slave_master_info TO orc_client_user@'172.%';

CREATE USER appuser@'%' IDENTIFIED BY 'apppass';
GRANT SELECT, INSERT, UPDATE, DELETE ON testdb.* TO appuser@'%';

CREATE USER 'monitor'@'%' IDENTIFIED BY 'monitor';
GRANT REPLICATION CLIENT ON *.* TO 'monitor'@'%';
FLUSH PRIVILEGES;