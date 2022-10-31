-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Oct 31, 2022 at 02:57 PM
-- Server version: 10.9.3-MariaDB-1:10.9.3+maria~ubu2204
-- PHP Version: 8.0.24

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
  `ward` varchar(255) DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL,
  `receiver_phone` varchar(10) DEFAULT NULL,
  `is_default` tinyint(1) DEFAULT 0,
  `address_id` int(11) NOT NULL,
  `province` varchar(1000) NOT NULL,
  `district` varchar(255) NOT NULL,
  `receiver_address` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Address`
--

INSERT INTO `Address` (`user_id`, `ward`, `receiver_name`, `receiver_phone`, `is_default`, `address_id`, `province`, `district`, `receiver_address`) VALUES
(3, 'VN', 'VVD', '1234567890', 1, 1, '', '', ''),
(3, 'VN', 'VVD', '1234567890', 0, 2, '', '', '');

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

--
-- Dumping data for table `Contain`
--

INSERT INTO `Contain` (`order_id`, `product_id`, `quantity`, `price`) VALUES
(1, 1, 2, 320000);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Customer`
--

INSERT INTO `Customer` (`user_id`, `name`, `mail`, `phone`, `dob`, `sex`, `password`, `security_code`) VALUES
(1, 'rnQLAvaHt3', NULL, '0123456789', NULL, NULL, '$argon2i$v=19$m=2048,t=10,p=1$$fGnUm65WsmUCo48RP++2HomyqBQ2Oq4KBke6FQFc8QwUWAo2Q430Qo8GnUD/9JTj9okHiOP/UMhDiAXkaylxmxvVCy8yM4sqOax2qTkzvQ2NeHeIr0YbumNDxqsZe3mERJ4mF5aXLH6nAaDQmZq6MwIuid2YL8bCa7PsOuM8HQo', '$argon2i$v=19$m=2048,t=10,p=1$$i5ne7a17e7aNyiOHW7kGA2PCU3vJ8jQAtgkKJTXQT78J7SeWbjmuATB0k7R49jkHa3PHajWqb9XmFgOX6ayaihPM3Z5Cj5VMnGLHoQdI1EUxXknRIiRyyJCc3zjFRfZVP5xLdqs5Ar0VlGxTZohsi1qIzBzesaI+K18dJ4+10pI'),
(2, 'rsAaD8nQkS', NULL, '0987654321', NULL, NULL, '$argon2i$v=19$m=2048,t=10,p=1$$eCEkfIeA1n+DbEwxb4RlePpUpyXwv4EZLgiPuVbirOLfoe/jMNJM0aGYdF17OwAfe0tOJhPv1HHV3DAp+gfc2ji1exjOE3tF6qMAY93DxiQ/P218cZDLfhc4TGJ3PRquC3QDKPUbI9K3jE+jnaWNpaKeaqIj/EOsCD/2Pib+1u0', '$argon2i$v=19$m=2048,t=10,p=1$$KVDqJTgUcthD9Hzps+otms3rTHWikpsMIsoaSxfvpoAyNnGrmFpa9r0RUGRki3fLVjQeo0h3DxnQ0va4T1VqXTrszWuS2WPRMg/ZmDSWfAy60B9fdt1kThqQWZmtq4z9pABRwm9u/VXxQ3CJ4dy44emLKE5BhM91vZ7oP7ACz4c'),
(3, 'mH9TcWQyPc', NULL, '0905123456', NULL, NULL, '$argon2i$v=19$m=2048,t=10,p=1$$eCEkfIeA1n+DbEwxb4RlePpUpyXwv4EZLgiPuVbirOLfoe/jMNJM0aGYdF17OwAfe0tOJhPv1HHV3DAp+gfc2ji1exjOE3tF6qMAY93DxiQ/P218cZDLfhc4TGJ3PRquC3QDKPUbI9K3jE+jnaWNpaKeaqIj/EOsCD/2Pib+1u0', '$argon2i$v=19$m=2048,t=10,p=1$$KVDqJTgUcthD9Hzps+otms3rTHWikpsMIsoaSxfvpoAyNnGrmFpa9r0RUGRki3fLVjQeo0h3DxnQ0va4T1VqXTrszWuS2WPRMg/ZmDSWfAy60B9fdt1kThqQWZmtq4z9pABRwm9u/VXxQ3CJ4dy44emLKE5BhM91vZ7oP7ACz4c'),
(4, 'Fg1DTkygx6', NULL, '0901234567', NULL, NULL, '$argon2i$v=19$m=2048,t=10,p=1$$eCEkfIeA1n+DbEwxb4RlePpUpyXwv4EZLgiPuVbirOLfoe/jMNJM0aGYdF17OwAfe0tOJhPv1HHV3DAp+gfc2ji1exjOE3tF6qMAY93DxiQ/P218cZDLfhc4TGJ3PRquC3QDKPUbI9K3jE+jnaWNpaKeaqIj/EOsCD/2Pib+1u0', '$argon2i$v=19$m=2048,t=10,p=1$$KVDqJTgUcthD9Hzps+otms3rTHWikpsMIsoaSxfvpoAyNnGrmFpa9r0RUGRki3fLVjQeo0h3DxnQ0va4T1VqXTrszWuS2WPRMg/ZmDSWfAy60B9fdt1kThqQWZmtq4z9pABRwm9u/VXxQ3CJ4dy44emLKE5BhM91vZ7oP7ACz4c'),
(5, '0ReWRfySq5', NULL, '0345678901', NULL, NULL, '$argon2i$v=19$m=2048,t=10,p=1$$eCEkfIeA1n+DbEwxb4RlePpUpyXwv4EZLgiPuVbirOLfoe/jMNJM0aGYdF17OwAfe0tOJhPv1HHV3DAp+gfc2ji1exjOE3tF6qMAY93DxiQ/P218cZDLfhc4TGJ3PRquC3QDKPUbI9K3jE+jnaWNpaKeaqIj/EOsCD/2Pib+1u0', '$argon2i$v=19$m=2048,t=10,p=1$$gZz0ftnOWxRl6dDnMmWQcKTpO7KBa0ML8LaKtZpJmDWBGl0cavtpyDGIHLeFOVHxAgiN41GzDVsO8o++IIrar8m/ndaUpS4ykA6Xd3c5BpDQEgOZ6CP0hw6jQ7pk+dI1tNbV9cAbRnTEvS67ZyasEp2aFa5vUtUfqmCTdNbl8yI'),
(6, 'ucyqYeD37P', NULL, '036633697', NULL, NULL, '$argon2i$v=19$m=2048,t=10,p=1$$eCEkfIeA1n+DbEwxb4RlePpUpyXwv4EZLgiPuVbirOLfoe/jMNJM0aGYdF17OwAfe0tOJhPv1HHV3DAp+gfc2ji1exjOE3tF6qMAY93DxiQ/P218cZDLfhc4TGJ3PRquC3QDKPUbI9K3jE+jnaWNpaKeaqIj/EOsCD/2Pib+1u0', '$argon2i$v=19$m=2048,t=10,p=1$$X4Q2zgT+ycTJhahNmPVex6dvGcjg817OOfpeM+tTAMKA74W1GwxcgoVtGcZOnhrXe5lG7v8Z8PWkLtAL5ASfOubMu+U6E9GDf80oep6SRqeZb1FWX0b6ePkys5Ev0U7d3GkSHw0190KcgWqiUHbJYgZdIfpM8lPNhakyNDzbxag');

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

--
-- Dumping data for table `Order`
--

INSERT INTO `Order` (`order_id`, `user_id`, `payment_method`, `status`, `time`, `address_id`) VALUES
(1, 3, 'ATM', 'Accepted', '2022-10-24 14:19:36', 1);

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
(1, 2, 'Nicehck EB2S 3.5mm Metal CNC HIFI Earbud 15.4mm LCP Nhạc động Âm nhạc giọng hát Tai nghe có dây Tai nghe HD B40 / B70 / EBX21', 320000, 10000, 2, 'Nicehck EB2S 3.5mm Metal CNC HIFI Earbud 15.4mm LCP Nhạc động Âm nhạc giọng hát Tai nghe có dây Tai nghe HD B40 / B70 / EBX21\n\n\n\nThông tin chi tiết:\nThương hiệu: NICEHCK\nMô hình: EB2S\nLoại sản phẩm: Tai nghe\nĐơn vị trình điều khiển: động 15,4mm\nMàng: Màng loa LCP\nĐộ nhạy: 112dB / mW\nTrở kháng: 32Ω\nĐáp ứng tần số: 20-25kHz\nLoại giắc cắm: 3.5 mm\nChất liệu vỏ: Hợp kim nhôm\nVật liệu cáp: Độ tinh khiết cao OFC\nChiều dài cáp: Khoảng 1,2m'),
(2, 1, 'Apple iPad Gen 9th 10.2-inch Wi-Fi 64GB', 11990000, 10000, 2, 'Mạnh mẽ. Dễ sử dụng. Đa năng. iPad mới có màn hình Retina tuyệt đẹp, chip A13 Bionic mạnh mẽ, camera trước Ultra Wide có tính năng Trung Tâm Màn Hình, tương thích với Apple Pencil và Smart Keyboard (1). iPad giúp bạn dễ dàng làm được nhiều việc hơn nữa. Tất cả tính năng với mức giá ấn tượng.\n\n\nTính năng nổi bật\n\n•	Màn hình Retina 10.2 inch sống động với True Tone\n\n•	Chip A13 Bionic với Neural Engine\n\n•	Camera sau Wide 8MP, camera trước Ultra Wide 12MP với tính năng Trung Tâm Màn Hình\n\n•	Ổ lưu trữ lên tới 64GB\n\n•	Loa stereo\n\n•	Xác thực bảo mật với Touch ID\n\n•	Wi-Fi 802.11ac và dữ liệu di động LTE chuẩn Gigabit (2)\n\n•	Thời lượng pin lên tới 10 giờ (3)\n\n•	Cổng kết nối Lightning để sạc và kết nối phụ kiện\n\n•	Tương thích với Apple Pencil (thế hệ thứ 1) và Smart Keyboard (1)\n\n•	iPadOS 15 sở hữu sức mạnh độc đáo, dễ sử dụng và được thiết kế cho tính đa năng của iPad\n\n\nPháp lý \n\nỨng dụng có sẵn trên App Store. Nội dung được cung cấp có thể thay đổi.\n\n(1) Phụ kiện được bán riêng. Khả năng tương thích tùy thuộc thế hệ sản phẩm.\n\n(2) Cần có gói cước dữ liệu. Mạng LTE chuẩn Gigabit, 4G LTE Advanced, 4G LTE và gọi Wi-Fi chỉ khả dụng ở một số thị trường và được cung cấp qua một số nhà mạng. Tốc độ phụ thuộc vào thông lượng lý thuyết và có thể thay đổi tùy địa điểm và nhà mạng. Để biết thông tin về hỗ trợ mạng LTE, vui lòng liên hệ nhà mạng và truy cập apple.com/ipad/cellular.\n\n(3) Thời lượng pin khác nhau tùy theo cách sử dụng và cấu hình. Truy cập apple.com/batteries để biết thêm thông tin.\n\n\nBộ sản phẩm bao gồm: \n\n•	iPad\n\n•	Dây sạc Lighting to USB-C\n\n•	20W USB Power Adaper\n\n•	HDSD Bảo hành điện tử 12 tháng.\n\n\nThông tin bảo hành:\n\nBảo hành: 12 tháng kể từ ngày kích hoạt sản phẩm.\n\nKích hoạt bảo hành tại: https://checkcoverage.apple.com/vn/en/\n\n\nHướng dẫn kiểm tra địa điểm bảo hành gần nhất:\n\nBước 1: Truy cập vào đường link https://getsupport.apple.com/?caller=grl&locale=en_VN \n\nBước 2: Chọn sản phẩm.\n\nBước 3: Điền Apple ID, nhập mật khẩu.\n\nSau khi hoàn tất, hệ thống sẽ gợi ý những trung tâm bảo hành gần nhất.\n\n\nModel: A2602 : 10.2-inch');

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
  MODIFY `address_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `Category`
--
ALTER TABLE `Category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `Customer`
--
ALTER TABLE `Customer`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `Order`
--
ALTER TABLE `Order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Product`
--
ALTER TABLE `Product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
