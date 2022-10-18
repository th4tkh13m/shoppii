-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Oct 18, 2022 at 02:39 PM
-- Server version: 10.8.3-MariaDB-1:10.8.3+maria~jammy
-- PHP Version: 8.0.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `TQKDN`
--

-- --------------------------------------------------------

--
-- Table structure for table `Address`
--

CREATE TABLE `Address` (
  `user_id` int(11) NOT NULL,
  `receiver_address` varchar(255) DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL,
  `receiver_phone` varchar(10) DEFAULT NULL,
  `is_default` tinyint(1) DEFAULT 0,
  `address_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `Cart`
--

CREATE TABLE `Cart` (
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Cart`
--

INSERT INTO `Cart` (`user_id`, `product_id`, `quantity`) VALUES
(3, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `Category`
--

CREATE TABLE `Category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(100) NOT NULL,
  `imgLink` varchar(5000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Category`
--

INSERT INTO `Category` (`category_id`, `category_name`, `imgLink`) VALUES
(1, 'Thời trang', 'https://cf.shopee.vn/file/687f3967b7c2fe6a134a2c11894eea4b_tn'),
(2, 'Thiết bị điện tử', 'https://cf.shopee.vn/file/978b9e4cb61c611aaaf58664fae133c5_tn'),
(3, 'Thể thao', 'https://cf.shopee.vn/file/6cb7e633f8b63757463b676bd19a50e4_tn'),
(4, 'Phương tiện giao thông', 'https://cf.shopee.vn/file/3fb459e3449905545701b418e8220334_tn'),
(5, 'Sách', 'https://cf.shopee.vn/file/36013311815c55d303b0e6c62d6a8139_tn'),
(6, 'Trang sức phụ kiện', 'https://cf.shopee.vn/file/8e71245b9659ea72c1b4e737be5cf42e_tn'),
(7, 'Sức khoẻ', 'https://cf.shopee.vn/file/49119e891a44fa135f5f6f5fd4cfc747_tn'),
(8, 'Sắc đẹp', 'https://cf.shopee.vn/file/ef1f336ecc6f97b790d5aae9916dcb72_tn'),
(9, 'Vật dụng đời sống', 'https://cf.shopee.vn/file/24b194a695ea59d384768b7b471d563f_tn'),
(10, 'Khác', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Symbol_Resin_Code_7_OTHER.svg/1000px-Symbol_Resin_Code_7_OTHER.svg.png');

-- --------------------------------------------------------

--
-- Table structure for table `Contain`
--

CREATE TABLE `Contain` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `Customer`
--

CREATE TABLE `Customer` (
  `user_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `password` varchar(4096) DEFAULT NULL,
  `security_code` varchar(4096) NOT NULL
) ;

--
-- Dumping data for table `Customer`
--

INSERT INTO `Customer` (`user_id`, `name`, `mail`, `phone`, `dob`, `sex`, `password`, `security_code`) VALUES
(1, 'rnQLAvaHt3', NULL, '0123456789', NULL, NULL, '$argon2i$v=19$m=2048,t=10,p=1$$fGnUm65WsmUCo48RP++2HomyqBQ2Oq4KBke6FQFc8QwUWAo2Q430Qo8GnUD/9JTj9okHiOP/UMhDiAXkaylxmxvVCy8yM4sqOax2qTkzvQ2NeHeIr0YbumNDxqsZe3mERJ4mF5aXLH6nAaDQmZq6MwIuid2YL8bCa7PsOuM8HQo', '$argon2i$v=19$m=2048,t=10,p=1$$i5ne7a17e7aNyiOHW7kGA2PCU3vJ8jQAtgkKJTXQT78J7SeWbjmuATB0k7R49jkHa3PHajWqb9XmFgOX6ayaihPM3Z5Cj5VMnGLHoQdI1EUxXknRIiRyyJCc3zjFRfZVP5xLdqs5Ar0VlGxTZohsi1qIzBzesaI+K18dJ4+10pI'),
(2, 'rsAaD8nQkS', NULL, '0987654321', NULL, NULL, '$argon2i$v=19$m=2048,t=10,p=1$$eCEkfIeA1n+DbEwxb4RlePpUpyXwv4EZLgiPuVbirOLfoe/jMNJM0aGYdF17OwAfe0tOJhPv1HHV3DAp+gfc2ji1exjOE3tF6qMAY93DxiQ/P218cZDLfhc4TGJ3PRquC3QDKPUbI9K3jE+jnaWNpaKeaqIj/EOsCD/2Pib+1u0', '$argon2i$v=19$m=2048,t=10,p=1$$KVDqJTgUcthD9Hzps+otms3rTHWikpsMIsoaSxfvpoAyNnGrmFpa9r0RUGRki3fLVjQeo0h3DxnQ0va4T1VqXTrszWuS2WPRMg/ZmDSWfAy60B9fdt1kThqQWZmtq4z9pABRwm9u/VXxQ3CJ4dy44emLKE5BhM91vZ7oP7ACz4c'),
(3, 'mH9TcWQyPc', NULL, '0905123456', NULL, NULL, '$argon2i$v=19$m=2048,t=10,p=1$$eCEkfIeA1n+DbEwxb4RlePpUpyXwv4EZLgiPuVbirOLfoe/jMNJM0aGYdF17OwAfe0tOJhPv1HHV3DAp+gfc2ji1exjOE3tF6qMAY93DxiQ/P218cZDLfhc4TGJ3PRquC3QDKPUbI9K3jE+jnaWNpaKeaqIj/EOsCD/2Pib+1u0', '$argon2i$v=19$m=2048,t=10,p=1$$KVDqJTgUcthD9Hzps+otms3rTHWikpsMIsoaSxfvpoAyNnGrmFpa9r0RUGRki3fLVjQeo0h3DxnQ0va4T1VqXTrszWuS2WPRMg/ZmDSWfAy60B9fdt1kThqQWZmtq4z9pABRwm9u/VXxQ3CJ4dy44emLKE5BhM91vZ7oP7ACz4c'),
(4, 'Fg1DTkygx6', NULL, '0901234567', NULL, NULL, '$argon2i$v=19$m=2048,t=10,p=1$$eCEkfIeA1n+DbEwxb4RlePpUpyXwv4EZLgiPuVbirOLfoe/jMNJM0aGYdF17OwAfe0tOJhPv1HHV3DAp+gfc2ji1exjOE3tF6qMAY93DxiQ/P218cZDLfhc4TGJ3PRquC3QDKPUbI9K3jE+jnaWNpaKeaqIj/EOsCD/2Pib+1u0', '$argon2i$v=19$m=2048,t=10,p=1$$KVDqJTgUcthD9Hzps+otms3rTHWikpsMIsoaSxfvpoAyNnGrmFpa9r0RUGRki3fLVjQeo0h3DxnQ0va4T1VqXTrszWuS2WPRMg/ZmDSWfAy60B9fdt1kThqQWZmtq4z9pABRwm9u/VXxQ3CJ4dy44emLKE5BhM91vZ7oP7ACz4c');

-- --------------------------------------------------------

--
-- Table structure for table `Order`
--

CREATE TABLE `Order` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `status` enum('Pending','Accepted','Rejected') DEFAULT 'Pending',
  `time` datetime DEFAULT current_timestamp(),
  `address_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `Product`
--

CREATE TABLE `Product` (
  `product_id` int(11) NOT NULL,
  `shop_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `description` varchar(5000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Product`
--

INSERT INTO `Product` (`product_id`, `shop_id`, `name`, `price`, `quantity`, `category_id`, `description`) VALUES
(1, 2, 'NiceHCK EB2S ', 25, 10000, 2, 'Nicehck EB2S 3.5mm Metal CNC HIFI Earbud 15.4mm LCP Nháº¡c Äá»ng Ãm nháº¡c giá»ng hÃ¡t Tai nghe cÃ³ dÃ¢y Tai nghe HD B40 / B70 / EBX21\n\nThÃ´ng tin chi tiáº¿t:\nThÆ°Æ¡ng hiá»u: NICEHCK\nMÃ´ hÃ¬nh: EB2S\nLoáº¡i sáº£n pháº©m: Tai nghe\nÄÆ¡n vá» trÃ¬nh Äiá»u khiá»n: Äá»ng 15,4mm\nMÃ ng: MÃ ng loa LCP\nÄá» nháº¡y: 112dB / mW\nTrá» khÃ¡ng: 32Î©\nÄÃ¡p á»©ng táº§n sá»: 20-25kHz\nLoáº¡i giáº¯c cáº¯m: 3.5 mm\nCháº¥t liá»u vá»: Há»£p kim nhÃ´m\nVáº­t liá»u cÃ¡p: Äá» tinh khiáº¿t cao OFC\nChiá»u dÃ i cÃ¡p: Khoáº£ng 1,2m');

-- --------------------------------------------------------

--
-- Table structure for table `Shop`
--

CREATE TABLE `Shop` (
  `shop_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `status` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Shop`
--

INSERT INTO `Shop` (`shop_id`, `name`, `address`, `description`, `status`) VALUES
(1, 'Apple Official Store', 'VietNam', 'Sell Official Apple\'s products ', 1),
(2, 'NiceHCK Official Store', 'China', 'Sell audio accessories', 1);

-- --------------------------------------------------------

--
-- Table structure for table `ShopRequests`
--

CREATE TABLE `ShopRequests` (
  `customer_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `status` enum('Pending','Accepted','Rejected') DEFAULT 'Pending',
  `time` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ShopRequests`
--

INSERT INTO `ShopRequests` (`customer_id`, `name`, `address`, `description`, `status`, `time`) VALUES
(1, 'Apple Official Store', 'VietNam', 'Sell Apple\'s products', 'Rejected', '2022-10-18 13:43:55'),
(1, 'Apple Official Store', 'VietNam', 'Sell Official Apple\'s products ', 'Accepted', '2022-10-18 13:50:19'),
(3, 'Scam store', 'VietNam', 'Sell real dollars', 'Rejected', '2022-10-18 14:03:13'),
(2, 'NiceHCK Official Store', 'China', 'Sell audio accessories', 'Accepted', '2022-10-18 14:04:14');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Address`
--
ALTER TABLE `Address`
  ADD PRIMARY KEY (`address_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `Cart`
--
ALTER TABLE `Cart`
  ADD PRIMARY KEY (`user_id`,`product_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `Category`
--
ALTER TABLE `Category`
  ADD PRIMARY KEY (`category_id`),
  ADD UNIQUE KEY `category_name` (`category_name`);

--
-- Indexes for table `Contain`
--
ALTER TABLE `Contain`
  ADD PRIMARY KEY (`order_id`,`product_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `Customer`
--
ALTER TABLE `Customer`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `mail` (`mail`);

--
-- Indexes for table `Order`
--
ALTER TABLE `Order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `address_id` (`address_id`);

--
-- Indexes for table `Product`
--
ALTER TABLE `Product`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `Shop`
--
ALTER TABLE `Shop`
  ADD PRIMARY KEY (`shop_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `ShopRequests`
--
ALTER TABLE `ShopRequests`
  ADD KEY `customer_id` (`customer_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Address`
--
ALTER TABLE `Address`
  MODIFY `address_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Category`
--
ALTER TABLE `Category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `Customer`
--
ALTER TABLE `Customer`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Order`
--
ALTER TABLE `Order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Product`
--
ALTER TABLE `Product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Address`
--
ALTER TABLE `Address`
  ADD CONSTRAINT `Address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Customer` (`user_id`);

--
-- Constraints for table `Cart`
--
ALTER TABLE `Cart`
  ADD CONSTRAINT `Cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Customer` (`user_id`),
  ADD CONSTRAINT `Cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `Product` (`product_id`);

--
-- Constraints for table `Contain`
--
ALTER TABLE `Contain`
  ADD CONSTRAINT `Contain_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `Order` (`order_id`),
  ADD CONSTRAINT `Contain_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `Product` (`product_id`);

--
-- Constraints for table `Order`
--
ALTER TABLE `Order`
  ADD CONSTRAINT `Order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Customer` (`user_id`),
  ADD CONSTRAINT `Order_ibfk_2` FOREIGN KEY (`address_id`) REFERENCES `Address` (`address_id`);

--
-- Constraints for table `Product`
--
ALTER TABLE `Product`
  ADD CONSTRAINT `Product_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `Shop` (`shop_id`),
  ADD CONSTRAINT `Product_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `Category` (`category_id`);

--
-- Constraints for table `Shop`
--
ALTER TABLE `Shop`
  ADD CONSTRAINT `Shop_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `Customer` (`user_id`);

--
-- Constraints for table `ShopRequests`
--
ALTER TABLE `ShopRequests`
  ADD CONSTRAINT `ShopRequests_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `Customer` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
