CREATE USER `admin`@'%' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON *.* TO `admin`@`%` WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON `admin`.* TO `admin`@`%`;

GRANT ALL PRIVILEGES ON `admin\_%`.* TO `admin`@`%`;
