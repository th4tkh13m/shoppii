CREATE USER `admin`@'%';
GRANT ALL PRIVILEGES ON *.* TO `admin`@`%` IDENTIFIED BY PASSWORD '*4ACFE3202A5FF5CF467898FC58AAB1D615029441' WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON `admin`.* TO `admin`@`%`;

GRANT ALL PRIVILEGES ON `admin\_%`.* TO `admin`@`%`;