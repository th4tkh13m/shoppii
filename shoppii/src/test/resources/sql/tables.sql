-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Nov 07, 2022 at 07:26 AM
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
(3, 'VN', 'VVD', '1234567890', 0, 2, '', '', ''),
(2, 'Thạch Thang', 'Ton That Khiem', '0159877459', 1, 3, 'Đà Nẵng', 'Hải Châu', '1x/45x đường Hoàng Diệu, phường Thạch Thang, Quận Hải Châu, Thành phố Đà Nẵng'),
(2, 'Hòa Hải', 'Ton That Khiem', '0159877459', 0, 4, 'Đà Nẵng', 'Ngũ Hành Sơn', '1x/45x đường Phan Châu Trinh'),
(3, 'Xuân Hà', 'Ton That Khiem', '0159877459', 0, 5, 'Đà Nẵng', 'Thanh Khê', '1x/45x đường Trần Cao Vân');

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
  `price` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Contain`
--

INSERT INTO `Contain` (`order_id`, `product_id`, `quantity`, `price`) VALUES
(1, 1, 2, 640000);

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
(1, 3, 'cash', 'Accepted', '2022-10-24 14:19:36', 1);

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
  `description` varchar(5000) DEFAULT NULL,
  `is_available` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Product`
--

INSERT INTO `Product` (`product_id`, `shop_id`, `name`, `price`, `quantity`, `category_id`, `description`, `is_available`) VALUES
(1, 2, 'Nicehck EB2S 3.5mm Metal CNC HIFI Earbud 15.4mm LCP Nhạc động Âm nhạc giọng hát Tai nghe có dây Tai nghe HD B40 / B70 / EBX21', 320000, 10000, 2, 'Nicehck EB2S 3.5mm Metal CNC HIFI Earbud 15.4mm LCP Nhạc động Âm nhạc giọng hát Tai nghe có dây Tai nghe HD B40 / B70 / EBX21\n\n\n\nThông tin chi tiết:\nThương hiệu: NICEHCK\nMô hình: EB2S\nLoại sản phẩm: Tai nghe\nĐơn vị trình điều khiển: động 15,4mm\nMàng: Màng loa LCP\nĐộ nhạy: 112dB / mW\nTrở kháng: 32Ω\nĐáp ứng tần số: 20-25kHz\nLoại giắc cắm: 3.5 mm\nChất liệu vỏ: Hợp kim nhôm\nVật liệu cáp: Độ tinh khiết cao OFC\nChiều dài cáp: Khoảng 1,2m', 1),
(2, 1, 'Apple iPad Gen 9th 10.2-inch Wi-Fi 64GB', 11990000, 10000, 2, 'Mạnh mẽ. Dễ sử dụng. Đa năng. iPad mới có màn hình Retina tuyệt đẹp, chip A13 Bionic mạnh mẽ, camera trước Ultra Wide có tính năng Trung Tâm Màn Hình, tương thích với Apple Pencil và Smart Keyboard (1). iPad giúp bạn dễ dàng làm được nhiều việc hơn nữa. Tất cả tính năng với mức giá ấn tượng.\n\n\nTính năng nổi bật\n\n•	Màn hình Retina 10.2 inch sống động với True Tone\n\n•	Chip A13 Bionic với Neural Engine\n\n•	Camera sau Wide 8MP, camera trước Ultra Wide 12MP với tính năng Trung Tâm Màn Hình\n\n•	Ổ lưu trữ lên tới 64GB\n\n•	Loa stereo\n\n•	Xác thực bảo mật với Touch ID\n\n•	Wi-Fi 802.11ac và dữ liệu di động LTE chuẩn Gigabit (2)\n\n•	Thời lượng pin lên tới 10 giờ (3)\n\n•	Cổng kết nối Lightning để sạc và kết nối phụ kiện\n\n•	Tương thích với Apple Pencil (thế hệ thứ 1) và Smart Keyboard (1)\n\n•	iPadOS 15 sở hữu sức mạnh độc đáo, dễ sử dụng và được thiết kế cho tính đa năng của iPad\n\n\nPháp lý \n\nỨng dụng có sẵn trên App Store. Nội dung được cung cấp có thể thay đổi.\n\n(1) Phụ kiện được bán riêng. Khả năng tương thích tùy thuộc thế hệ sản phẩm.\n\n(2) Cần có gói cước dữ liệu. Mạng LTE chuẩn Gigabit, 4G LTE Advanced, 4G LTE và gọi Wi-Fi chỉ khả dụng ở một số thị trường và được cung cấp qua một số nhà mạng. Tốc độ phụ thuộc vào thông lượng lý thuyết và có thể thay đổi tùy địa điểm và nhà mạng. Để biết thông tin về hỗ trợ mạng LTE, vui lòng liên hệ nhà mạng và truy cập apple.com/ipad/cellular.\n\n(3) Thời lượng pin khác nhau tùy theo cách sử dụng và cấu hình. Truy cập apple.com/batteries để biết thêm thông tin.\n\n\nBộ sản phẩm bao gồm: \n\n•	iPad\n\n•	Dây sạc Lighting to USB-C\n\n•	20W USB Power Adaper\n\n•	HDSD Bảo hành điện tử 12 tháng.\n\n\nThông tin bảo hành:\n\nBảo hành: 12 tháng kể từ ngày kích hoạt sản phẩm.\n\nKích hoạt bảo hành tại: https://checkcoverage.apple.com/vn/en/\n\n\nHướng dẫn kiểm tra địa điểm bảo hành gần nhất:\n\nBước 1: Truy cập vào đường link https://getsupport.apple.com/?caller=grl&locale=en_VN \n\nBước 2: Chọn sản phẩm.\n\nBước 3: Điền Apple ID, nhập mật khẩu.\n\nSau khi hoàn tất, hệ thống sẽ gợi ý những trung tâm bảo hành gần nhất.\n\n\nModel: A2602 : 10.2-inch', 1),
(3, 1, 'Phấn bắt sáng ZEESEA lấp lánh tiện dụng chất lượng cao 6g', 119000, 10000, 8, 'Thời gian giao hàng dự kiến cho sản phẩm này là từ 7-9 ngày\r\n  \r\n  Phiên bản độc quyền ZEESEA Cupid\r\n  \r\n  Phấn bắt sáng tạo khối ZEESEA Cupid\r\n  Phấn mịn và mềm, ánh kim cương lấp lánh, tạo đường nét khuôn mặt thanh tú và mang đến cho bạn làn da không tì vết.\r\n  Không bay phấn, không thấm nước và mồ hôi, trang điểm lâu trôi, tăng độ sáng cho màu da của bạn.\r\n  Độ bóng cao, mặc dù sản phẩm không quá lấp lánh nhưng vẫn rất sáng bóng.\r\n  Chất phấn mịn, dễ bám vào da, đáp ứng tất cả các phong cách trang điểm.\r\n  \r\n  Thành phần: \"Talc\", \"Triethoxyoctylsilane\", \"(Synthetic Fluorphlogopite, Triethoxyoctylsilane)\", \"(Silica, Triethoxyoctylsilane)\", \"Synthesis Wax\",\"Zinc Stearate\",\"(Boron Nitride, Lauroyl Lysine)\",\"Bismuth Oxychloride\",\"Citrate\",\"(Mica,CI 77891)\",\"(Borosilicate Sodium calcium, CI 77891, tin oxide)\",\"(Calcium aluminum borosilicate, CI 77891, tin oxide)\", \"(Synthetic fluorphlogopite, CI 77891, tin oxide)\",\"(Mica, CI 77891, oxide Tin,CI 77491)\",\"(Mica,CI 77491)\",\"Dimethicone\",\"Diisostearyl malate\",\"Ethylhexyl palmitate\",\"Octyl ten Glycol stearoyloxystearate\",\"bis-diglycerol polyacyl adipate-2\",\"(phenoxyethanol, ethylhexylglycerol)\",\"glyceryl caprylate\",\"fertility Phenol acetate\"\r\n  \r\n  Cách dùng: Dùng cọ hoặc tay thoa đều 2 bên má\r\n  Bảo quản: nơi khô ráo và thoáng mát\r\n  \r\n  Thương hiệu: ZEESEA\r\n  Xuất xứ: sản xuất tại Trung Quốc\r\n  Thời hạn sử dụng: 3 năm\r\n  Trọng lượng: 6g\r\n  Loại da phù hợp: mọi loại da\r\n  Màu sắc: 4 màu\r\n  Phân loại màu sắc: 01# Trắng ngọc trai , 02# Vàng Champagne, #H07 Màu be sáng, #H08 Kim cương\r\n  Hiệu quả: Bắt sáng đường viền khuôn mặt và làm sáng tông màu da\r\n  Loại đặc điểm kỹ thuật: bình thường\r\n  Mỹ phẩm chuyên dụng: không', 1),
(4, 2, 'Bảng Phấn Mắt Zeesea 16 Màu Phong Cách Ai Cập Flower Fire Night 20g', 399000, 10000, 8, 'Thời gian giao hàng dự kiến cho sản phẩm này là từ 7-9 ngày\r\n\r\n  \r\n\r\n  ღ ZEESEA × British Museum Exclusive ღ\r\n\r\n  \r\n\r\n  Độc đáo, tuyệt đẹp và dễ sử dụng, hãy bắt đầu thôi!\r\n\r\n  Kết cấu phấn mịn, dễ lấy phấn, không có bụi phấn bay\r\n\r\n  Màu sắc đa dạng và nổi bật, phù hợp để trang trí hàng ngày và khiến bạn thêm sắc sảo\r\n\r\n  Lên đúng màu, thậm chí có thể tán chồng lên nhau nhưng vẫn đẹp, sẽ không bị nhòe\r\n\r\n  \r\n\r\n  Một bảng phấn mắt bạn không thể bỏ qua, hãy lột tả hết nét quyến rũ của bạn trong mọi dịp\r\n\r\n  Mềm mịn và dễ tán, một bảng màu chất lượng gấp đôi, chất phấn mịn, bền màu lâu dài\r\n\r\n  Màu sắc phong phú đa dạng, cảm giác chạm tốt, dễ lên màu, dễ tán\r\n\r\n  Bền màu lâu dài với độ dẻo cao, màu đặc đa dạng, kết cấu mịn, không dễ bay bụi phấn\r\n\r\n  \r\n\r\n  Thành phần: \"mica\", \"talc\", \"silica\", \"boron nitride\", \"polydimethylsiloxane\", \"pentaerythritol tetraisostearate\", \"CI 77891\", \"kaolin\" ,\"CI 77499\",\"Tocopherol Acetate\",\"(1,2-hexanediol, 1,2-pentanediol, propylene glycol, p-hydroxyacetophenone)\",\"CI 19140\",\"CI 16035 \"\r\n\r\n  \r\n\r\n  Hướng dẫn bảo quản: để nói khô thoáng, tránh ánh nắng mặt trời \r\n\r\n \r\n\r\n  Hướng dẫn:\r\n\r\n  Dùng cọ hoặc ngón tay quét phấn mắt, và tán đều xung quanh mắt\r\n\r\n  \r\n\r\n  Thương hiệu: ZEESEA\r\n\r\n  Tên sản phẩm: Bảng phấn mắt 16 màu Flower Fire Night \r\n\r\n  Thông số kỹ thuật: Thông số kỹ thuật thông thường\r\n\r\n  Màu sắc：16 màu\r\n\r\n  Màu sắc:\r\n\r\n  # 01\r\n\r\n  # 02\r\n\r\n  # 03\r\n\r\n  # 04\r\n\r\n  # 05\r\n\r\n  # 06\r\n\r\n  Mỹ phẩm chuyên dụng: Không\r\n\r\n  Hạn sử dụng: 36 tháng\r\n\r\n  Khối lượng tịnh: 20g', 1),
(5, 1, 'Kem lót trang điểm Zeesea kem che khuyết điểm da khô 30g', 119000, 23, 8, '01 # Purple\r\n\r\n  Thích hợp cho: Những người có nước da vàng, da xỉn màu và không đều màu.\r\n\r\n  \r\n\r\n  02 # Green\r\n\r\n  Thích hợp cho: Những người có làn da đỏ và mụn trứng cá.\r\n\r\n  \r\n\r\n  03 # Natural\r\n\r\n  Thích hợp cho: Da trắng, cần trang điểm tự nhiên.\r\n\r\n  \r\n\r\n  \r\n\r\n  1. Trang điểm hoàn hảo, không bóng nhờn, tinh tế và mịn màng.\r\n\r\n  2. Tăng cường tông màu da. Màu da có thể được sửa đổi theo các vấn đề về tông màu da khác nhau\r\n\r\n  3. Trang điểm kiểm soát dầu. Trang điểm tươi tắn, làm cho lớp trang điểm tiếp theo tự nhiên hơn\r\n\r\n \r\n\r\n  \r\n\r\n  Thành phần: water\", \"glycerin\", \"propylene glycol\", \"titanium dioxide\", \"isopropyl palmitate\", \"cyclopentadimethylsiloxane\", \"polydimethylsiloxane\", \"betaine\" \",\"Hydroxyethyl acrylate/sodium acryloyl dimethyl taurate copolymer\",\"Cetyl PEG/PPG-10/1 polydimethylsiloxane\",\"VP/eicosene copolymer \",\"Phenoxyethanol\",\"Methyl Paraben\",\"Hydrogenated Lecithin\",\"Sodium Hyaluronate\",\"Tocopherol (Vitamin E)\",\"PRUNUS SPECIOSA Flower Extract\",\"Mother Chrysanthemum (CHAMOMILLA RECUTITA) flower extract\", \"ROSMARINUS OFFICINALIS extract\", \"CI 77742\", \"Fragrance\"\r\n\r\n  \r\n\r\n  Hướng dẫn bảo quản: để nơi khô ráo, tránh ánh nắng mặt trời\r\n\r\n \r\n\r\n  Cách sử dụng: Lấy một lượng kem lót trang điểm vừa đủ thoa đều lên mặt\r\n\r\n  \r\n\r\n  Hướng dẫn:\r\n\r\n  1. Thoa trước khi thoa kem nền dạng lỏng\r\n\r\n  2. Nhẹ nhàng thoa lên toàn bộ khuôn mặt để làm đều màu da và giúp lớp nền lỏng hơn.\r\n\r\n  \r\n\r\n  Thương hiệu: ZEESEA\r\n\r\n  Tên sản phẩm: Kem lót trang điểm\r\n\r\n  Mỹ phẩm chuyên dụng: Không\r\n\r\n  Mã sản phẩm: ZS-812\r\n\r\n  Phân loại kết cấu: kem\r\n\r\n  Phân loại màu: màu tím, xanh, nude\r\n\r\n  Hiệu quả: dưỡng ẩm, làm sáng tông màu da, che khuyết điểm, thu nhỏ lỗ chân lông, tách lớp trang điểm \r\n\r\n  Thích hợp cho loại da: mọi loại da \r\n\r\n  Thời hạn sử dụng của mỹ phẩm: 36 tháng\r\n\r\n  Ngay sản xuất: in trên bao bì\r\n\r\n  Ngày hết hạn: in trên bao bì', 1),
(6, 1, 'Bảng phấn mắt ZEESEA 9 màu sắc trọng lượng 84g', 189000, 23, 8, 'Thời gian giao hàng dự kiến cho sản phẩm này là từ 7-9 ngày\r\n\r\n  \r\n\r\n  Bảng phấn mắt ZEESEA 9 màu sắc\r\n\r\n  \r\n\r\n  Lấy cảm hứng từ câu truyện Alice ở xứ sở thần tiên mới\r\n\r\n  Rơi vào cuộc phiêu lưu ở xứ sở thần tiên với Alice\r\n\r\n  Bảng phấn mắt 9 màu\r\n\r\n  \r\n\r\n  Siêu mịn ◆ Siêu bám phấn ◆ Dễ lên màu\r\n\r\n  1. Kết cấu mịn và bám như sáp\r\n\r\n  2. Màu sắc cao\r\n\r\n  3. Thủ công\r\n\r\n  4. Mềm và mịn\r\n\r\n  5. Có thể mang theo và trang điểm lâu dài\r\n\r\n  6. Thiết kế độc đáo\r\n\r\n  \r\n\r\n  Thành phần:\r\n\r\n  \"Talc\", \"Mica\", \"Silica\", \"Polymethylmethacrylate\",\"Magnesium Stearate\",\"(Calcium Sodium Borosilicate, CI 77891, Tin Oxide)\",\"Polydimethyl Silicone\",\"CI 77491\",\"Caprylic/Capric Triglyceride\",\"CI 19140\",\"CI 77492\",\"Phenoxyethanol\",\"(Caprylyl glycol, Ethylhexylglycerol)\",\" Tocopherol Acetate\",\"CI 77499\"\r\n\r\n  \r\n\r\n  Hướng dẫn:\r\n\r\n  Dùng cọ hoặc ngón tay quét phấn mắt, và tán đều xung quanh mắt\r\n\r\n  \r\n\r\n  Hướng dẫn bảo quản: Để ở nơi thoáng mát, tránh tiếp xúc với ánh nắng mặt trời\r\n\r\n  \r\n\r\n  Thương hiệu: ZEESEA\r\n\r\n  Xuất xứ: Sản xuất tại Trung Quốc\r\n\r\n  Màu sắc: 9 màu\r\n\r\n  Trọng lượng tịnh: 10g\r\n\r\n  Chi tiết sản phẩm: Bình thường\r\n\r\n  Mỹ phẩm sử dụng cho mục đích đặc biệt: Không\r\n\r\n  Thời hạn sử dụng: 36 tháng', 1),
(7, 1, 'Bảng phấn mắt Zeesea phong cách Alice ở Xứ Sở Thần Tiên 20g', 399000, 23, 8, ' Bảng phấn mắt Alice In Wonderland ZEESEA x bảo tàng Anh \r\n  \r\n  Phấn mắt lâu trôi.\r\n  Xu hướng thời trang độc đáo.\r\n  \r\n  1. Phấn siêu mịn\r\n  Lặp lại quá trình trộn bột phấn 3 lần, bột phấn mịn hơn, mềm hơn.\r\n  2. Cường độ màu sắc cao\r\n  Phấn nhập khẩu, sẽ lên màu ngay với một lần chạm nhẹ, không bay màu.\r\n  3. Giữ màu suốt cả ngày\r\n  Chế biến bột phấn độc đáo, lâu trôi mà không cần trang điểm.\r\n  4. Gia công thủ công\r\n  Phấn mắt được làm thủ công\r\n  \r\n  Quá trình:\r\n  Sản phẩm sử dụng quá trình gia công dập nóng + khắc laser + quy trình tạo hình ba chiều 3D.\r\n  Bao bì đa sắc, cực kì sống động và bắt mắt.\r\n  \r\n  ZEESEA và Bảo tàng Anh độc quyền sản phẩm tùy chỉnh giới hạn\r\n  Bảo tàng Anh\r\n  Tùy biến độc quyền\r\n  \r\n  Thành phần: \"talc\", \"mica\", \"CI 77891\", \"silica\", \"ethylhexyl palmitate\", \"hydrogenated polyisobutylene\", \"CI 77492\", \"polydimethylsiloxane\", \"CI 77491\",\"(Sodium borosilicate calcium, CI 77891, tin oxide)\",\"CI 77499\",\"Polybutene\",\"Tocopherol Acetate\",\"(1,2-hexanediol, P-hydroxyacetophenone, propylene glycol, 1,2-pentanediol)\",\"CI 15985\",\"CI 45410\",\"CI 45380\",\"CI 16035\"\r\n\r\n Hướng dẫn:\r\nDùng cọ hoặc ngón tay quét phấn mắt, và tán đều xung quanh mắt\r\n  \r\n  Hướng dẫn bảo quản: cất ở nơi khô thoáng, tránh ánh nắng mặt trời\r\n  \r\n  Thương hiệu: ZEESEA\r\n  Xuất xứ: sản xuất tại Trung Quốc\r\n  Màu sắc: 3 màu\r\n  Hàm lượng tịnh: 20g\r\n  Loại đặc điểm kỹ thuật: thông số kỹ thuật bình thường\r\n  Mỹ phẩm có chức năng đặc biệt: Không\r\n  Thời hạn sử dụng: 36 tháng', 1),
(8, 1, 'Bảng phấn mắt Perfect Diary 12 màu hiệu ứng lì nhũ ánh kim mịn kèm cọ 14g', 678000, 23, 8, 'Ngày bán hàng Hoàn hảo Nhật ký 13-15,6 cuối cùng đã đến!\r\n\r\n🎊Các đơn đặt hàng trên 380K Free Rouge Ultimate * 1 Son môi, Giá trị 299K\r\n\r\n🎉Các đơn đặt hàng trên Bảng phấn mắt Rouge Ultimate * 1 Lipstick + Bảng phấn mắt 12 màu Explorer, trị giá 699K\r\n\r\n\r\n\r\nThời gian giao hàng dự kiến cho sản phẩm này là 7-9 ngày\r\n\r\n  \r\n\r\n Chi tiết:\r\n\r\n Màu sắc sản phẩm:\r\n\r\n  02 con hổ\r\n\r\n  09 Mèo\r\n\r\n  10 Cá chép độc đáo\r\n\r\n  12 Cần cẩu đám đông đỏ\r\n\r\n  13 Red Fox\r\n\r\n  14 con bướm\r\n\r\n  \r\n\r\n  Mã sản phẩm:\r\n\r\n  Pdc027\r\n\r\n  \r\n\r\n  Thành phần:\r\n\r\n  1.Octyldodecanol\r\n\r\n  2.Caprylic / CAPRIC TRIGLYCERIDE\r\n\r\n  3.Wax tổng hợp, SILICA\r\n\r\n  4.Các nước khác\r\n\r\n  \r\n\r\n  Hướng dẫn sử dụng:\r\n\r\n  Dùng đầu ngón tay hoặc cọ và tán đều.\r\n\r\n  \r\n\r\n  Hướng dẫn bảo quản:\r\n\r\n  Tránh bảo quản ở nhiệt độ cao và ánh nắng trực tiếp, tránh xa tầm tay trẻ em.\r\n\r\n  Ngày hết hạn:\r\n\r\n  3 năm', 1),
(9, 1, 'Phấn nước GILAA LONG WEAR DD CUSHION - SPF50+/PA+++ (1 lõi x 13g)', 525000, 23, 8, 'GILAA LONG WEAR DD CUSHION (13g)\r\n\r\nPHẤN NƯỚC THẾ HỆ MỚI\r\n\r\n\r\n\r\nGilaa Long Wear DD Cushion được đánh giá là Thế hệ DD Cushion hoàn hảo khi kết hợp đồng thời giữa trang điểm và dưỡng da. Lớp nền mỏng nhẹ nhưng che khuyết đến 85% khuyết điểm trên da chỉ với 1 lần dặm phấn. Thành phần chống nắng cao và chứa nhiều dưỡng chất, đặc biệt là Nhụy Hoa Nghệ Tây sẽ bảo vệ da hoàn hảo, trang điểm mỗi ngày vẫn không lo hư da.\r\n\r\n \r\n\r\n---\r\n\r\n\r\n\r\nTHÔNG TIN SẢN PHẨM\r\n\r\n\r\n\r\n**Tông màu chinh phục mọi nước da Châu Á\r\n\r\n\r\n\r\n#01 Light Beige - Màu sáng\r\n\r\nPhù hợp với những nàng có tông da sáng hoặc thích kiểu trang điểm nâng tông.\r\n\r\n#02 Natural Beige - Màu tự nhiên\r\n\r\nPhù hợp tông da trung bình đến ngăm hoặc thích kiểu trang điểm tự nhiên.\r\n\r\n\r\n\r\n**Công dụng\r\n\r\n\r\n\r\n- Kết hợp trang điểm và dưỡng da\r\n\r\n- Che phủ 80% khuyết điểm như thâm mụn, ửng đỏ,...\r\n\r\n- Tạo nền mỏng nhẹ, bám chắc và bền màu nhờ hạt phấn siêu vi\r\n\r\n- An tâm hơn nhờ thành phần không cồn, không hương liệu\r\n\r\n- Kiềm dầu tốt và bền màu 24h, hạn chế tình trạng loang lổ hay xỉn màu\r\n\r\n\r\n\r\n**Thành phần hỗ trợ dưỡng trắng và chống nắng nâng cao\r\n\r\n\r\n\r\n- Saffron: dưỡng trắng, cấp ẩm, chống lão hóa, chống tia UV\r\n\r\n- Bisabolol: ức chế melanin, chống dị ứng, chống oxy hóa, làm mịn, giảm nhăn, giảm sẹo\r\n\r\n- Niacinamide (Phức hợp vitamin B3): giảm mụn, cải thiện lỗ chân lông\r\n\r\n- Madecassoside: làm dịu, hạn chế kích ứng \r\n\r\n- Multiex Bsasm (chiết xuất từ 7 thành phần thực vật): tạo rào cản, bảo vệ tăng cường\r\n\r\n- Adenosine: phục hồi, làm dịu da, làm lành tổn thương, chống lão hóa\r\n\r\n- Chỉ số chống nắng SPF50+/PA+++\r\n\r\n\r\n\r\n**Hướng dẫn sử dụng\r\n\r\n\r\n\r\nNhấn bông phấn để lấy một lượng cushion vừa đủ. Apply lên mặt và tán đều đến khi được lớp nền như mong muốn.\r\n\r\n\r\n\r\n**Hướng dẫn bảo quản\r\n\r\n\r\n\r\nBảo quản ở nơi khô ráo, thoáng mát, tránh ánh nắng trực tiếp.\r\n\r\n\r\n\r\n---\r\n\r\n\r\n\r\nVỀ THƯƠNG HIỆU\r\n\r\n\r\n\r\nGilaa - From A Girl To A Lady\r\n\r\n\r\n\r\nGilaa hòa hợp phong cách thời thượng của một cô gái (a girl) và thần thái tinh tế của một quý cô (a lady). Từ mong muốn nuôi dưỡng và tỏa sáng vẻ đẹp hiện đại, Gilaa áp dụng triết lý “Mỹ Phẩm Phức Hợp” để đem đến phương pháp chăm sóc gần gũi và thiết thực.\r\n\r\n\r\n\r\nGilaa hy vọng đây sẽ những giải pháp tối ưu và vượt trội, để mỗi một thay đổi của bạn đều thật hoàn hảo.\r\n\r\n\r\n\r\nXuất xứ: Hàn Quốc\r\n\r\nHạn sử dụng: 36 tháng kể từ ngày sản xuất, 12 tháng sau khi mở nắp.\r\n\r\nNSX: In trên bao bì \r\n\r\nCông ty chịu trách nhiệm nhập khẩu và phân phối độc quyền: Công ty TNHH OQR - Tầng 6, Tầng 7, Tòa nhà Bcons, 176/1-176/3 Đường Nguyễn Văn Thương, Phường 25, Quận Bình Thạnh, Thành phố Hồ Chí Minh, Việt Nam', 1),
(10, 2, 'Giá đỡ điện thoại đa năng Ankndo xoay 1200° gắn bảng điều khiển xe hơi', 85999, 23, 4, 'Đặc điểm sản phẩm:\r\n 1. Giá đỡ xoay 1200 °, model cũ chỉ xoay 360, đáp ứng nhu cầu cho mọi hướng\r\n 2. Giúp hiển thị thông tin trên điện thoại tốt hơn, thuận tiện và thiết thực hơn\r\n 3. Thiết kế khóa lò xo, giúp điện thoại ổn định hơn và không dễ rơi, an toàn hơn khi lái xe\r\n \r\n Thông tin sản phẩm:\r\n Tên sản phẩm: Giá đỡ điện thoại di động đa chức năng\r\n Kích thước sản phẩm: 8cm x 13cm x 11.5cm\r\n Chất liệu sản phẩm: ABS + Phần cứng\r\n Thích hợp cho điện thoại di động: điện thoại di động phổ biến 3-7 inch\r\n Phù hợp với: Bảng điều khiển ô tô / tấm che nắng / gắn bàn máy tính \r\n \r\n Lưu ý:\r\n 1. Tất cả các sản phẩm có sẵn, các bạn có thể yên tâm mua hàng.\r\n 2. Do đo lường thủ công sẽ có sai số kích thước nhỏ. \r\n 3. Hình ảnh được chụp từ sản phẩm thực tế.\r\n \r\n Gói hàng bao gồm:\r\n 1 x giá đỡ điện thoại di động đa chức năng', 1),
(11, 2, 'Xe Máy Honda Winner X Phiên Bản Đặc Biệt 2022', 37590000, 31, 4, '*1. Quy định về giá bán:\r\n\r\n- Giá bán xe đã bao gồm thuế VAT, Chưa bao gồm thuế trước bạ và chi phí làm giấy tờ, biển số\r\n\r\n- HEAD có hỗ trợ về thủ tục đăng ký, hoặc khách hàng mua xe “CÓ THỂ TỰ ĐI ĐĂNG KÝ”\r\n\r\n2. Khách hàng nhận xe và làm thủ tục giấy tờ tại 1 trong 5 cửa hàng của Shop tại Quận 11, Quận 8 và Bình Dương.\r\n\r\n* THỜI GIAN NHẬN XE: từ 3 đến 5 ngày-  (sau khi khách hàng hoàn tất thành công các bước thanh toán trên Shopee) không kể T7,CN, Lễ Tết.\r\n\r\n**NHẬN XE TRONG NGÀY: vui lòng liên hệ hotline  kiểm tra tồn kho trước khi đặt\r\n\r\n*** Khách hàng vui lòng nhận xe trong N+3 (N là ngày đã thanh toán thành công trên Shopee) không kể T7,CN, Lễ Tết\r\n\r\n3. Quy định về bảo hành và đổi trả xe: \r\n\r\n- Xe được bảo hành chính hãng 3 năm/30.000 km (Tùy điều kiện nào đến trước) tại tất cả các HEAD trên toàn quốc theo chính sách của Honda Việt Nam.\r\n\r\n- Hàng đã nhận không được đổi trả (điều kiện đổi trả theo quy định của Honda Việt Nam). Quý khách hàng vui lòng kiểm tra kỹ sản phẩm trước khi nhận xe, HEAD không chịu trách nhiệm khi có bất kỳ lỗi xảy ra do sự chủ quan trong quá trình kiểm tra và giao nhận sản phẩm.ì', 1),
(12, 2, 'Dụng Cụ Sửa Chữa Xe Đạp Bỏ Túi ROCKBROS 16 Trong 1 Đa Năng Có Thể Gấp Gọn Kích Thước', 63050, 31, 4, 'Tên thương hiệu: ROCKBROS\r\nLoại sản phẩm: Dụng cụ đa năng\r\nĐặc điểm:\r\nCó 11 chức năng trong một bộ dụng cụ sửa xe đạp.\r\nSử dụng chất liệu thép carbon bền.\r\nThiết kế độc đáo, có thể gấp lại thành một kích thước nhỏ.\r\nDễ dàng mang theo và cất giữ.\r\nChức năng:\r\nDụng cụ cắt xích\r\nChìa khóa lục giác 2 / 2.5 / 3 / 4 / 5 / 6mm\r\nCờ lê ổ cắm\r\nTua vít có rãnh\r\nTua vít Phillips \r\nTua vít lục giác\r\nThông tin chi tiết:\r\nGói hàng bao gồm: 1 x Dụng cụ sửa chữa\r\nChất liệu: Thép carbon\r\nTrọng lượng: Khoảng 0.2KG\r\nMàu sắc: Đen / Xanh dương / Đỏ / Vàng\r\nKích thước gấp lại: 7.5 * 4 * 2cm / 2.95 * 1.57 * 0.79in\r\nGói hàng bao gồm: 1 x Bộ dụng cụ sửa chữa xe đạp', 1),
(13, 2, 'Ổ khóa mật mã ROCKBROS bằng hợp kim kẽm chống trộm mini trọng lượng nhẹ cho mũ bảo hiểm', 65778, 31, 4, 'Tên sản phẩm: Ổ khóa mật mã ROCKBROS bằng hợp kim kẽm chống trộm mini trọng lượng nhẹ cho mũ bảo hiểm xe đạp\r\n  MÀU SẮC: Đen, Trắng, Xanh lá, Đỏ\r\n  Chất liệu: hợp kim\r\n  Trọng lượng: 45g\r\n  Chiều dài: 90cm\r\n  Thương hiệu: ROCKBROS\r\n \r\n Thông tin sản phẩm:\r\n  Kích thước nhỏ, dễ mang theo,\r\n  (Có thể để trong túi, túi xe đạp hoặc quấn quanh xe đạp của bạn)\r\n  MÀU SẮC: Đen, Trắng, Xanh lá, Đỏ\r\n  Chất liệu: hợp kim\r\n  Trọng lượng: 45g\r\n  Chiều dài: 90cm\r\nĐặc điểm:\r\n  Kích thước nhỏ, dễ mang theo,\r\n  (Có thể để trong túi, túi xe đạp hoặc quấn quanh xe đạp của bạn)', 1),
(14, 2, 'Cảm Biến Áp Suất Lốp Ô Tô TPMS Phiên Bản QUỐC TẾ Màn Hình Màu dùng Năng Lượng Mặt Trời (Loại gắn ngoài van', 499000, 31, 4, '☑ Phiên bản QUỐC TẾ, sử dụng hoàn toàn tiếng Anh.\r\n ☑ Trang bị VI XỬ LÝ CỦA ĐỨC (cao cấp hơn bản dùng chip TQ)\r\n ☑ MÀN HÌNH MÀU, hộp đựng lót mút chống sốc, ...\r\n ☑ HUD cảnh báo dùng nguồn năng lượng kép từ nguồn sạc USB và NĂNG LƯỢNG MẶT TRỜI\r\n ☑ Đạt chứng chỉ CE (Châu Âu) và FCC (Mỹ)\r\n ☑ Hỗ trợ ĐỔI MỚI TRONG VÒNG 10 NGÀY (giữ nguyên seal)\r\n ☑ BẢO HÀNH 12 THÁNG\r\n ☑ TẶNG KÈM 2 viên pin dự phòng (Panasonic, made in Indonesia)', 1),
(15, 2, 'Sách - Dưỡng tâm giàu có dưỡng thân nghèo khó', 63700, 31, 5, 'Tên sản phẩm: Dưỡng tâm giàu có dưỡng thân nghèo khó\r\n\r\nTác giả: Nguyễn Anh Dũng\r\n\r\nNhà xuất bản: Nhà xuất bản Thế Giới\r\n\r\nNăm xuất bản: 2020\r\n\r\nNhà phát hành: Công ty CP Sbooks\r\n\r\nKích thước sản phẩm: 13x20x0.72 (cm)\r\n\r\nTrọng lượng: 72 (gram)\r\n\r\nSố trang: 144\r\n\r\nHình thức bìa: Bìa mềm\r\n\r\n_____________\r\n\r\n“Dưỡng tâm giàu có Dưỡng Thân Nghèo Khó” là cuốn sách mang lại định nghĩa hoàn hảo về “sự giàu có”. Sự giàu có là một đáp án khiến tôi mất nhiều năm để nhận thức nên, cũng chính là triết lý xuyên suốt giúp tôi sống tốt hơn mỗi ngày. Tôi đã thực sự tin rằng, giàu có không chỉ là khi tích cóp thật nhiều mà nó còn tồn tại khi tôi đem cho đi. Chính vì suy nghĩ tích cực này, đã đem tôi đến bên những câu chuyện khó khăn của mỗi người, chia sẻ và hạnh phúc bên niềm vui của họ. “Dưỡng tâm giàu có Dưỡng Thân Nghèo Khó” đã đưa tôi đến thế giới của an yên, từ bi, đạo hạnh mà bao người mơ ước. Đừng vội đem những khó khăn bạn đang gặp phải so bì với hạnh phúc người khác. Người ta sinh cùng giờ còn có số mệnh khác nhau mà. Chỉ cần bạn cảm thấy vừa đủ thì đó đã là một sự sung túc bao người mong ước rồi. Tôi tin chắc rằng cuốn sách nhỏ này sẽ giúp bạn tự tin sống cuộc đời của mình, dũng cảm đối mặt với khó khăn, và rộng lượng chia sẻ cùng mọi người. Mong rằng trái tim bạn sẽ sớm giàu có.\r\n\r\n\r\n\r\n- Tác giả: Nguyễn Anh Dũng, Sáng Lập Sbooks\r\n\r\n\r\n\r\nBằng những câu chuyện kể với giọng văn nhẹ nhàng, dễ thương, những triết lý nhân sinh gần gũi với cuộc sống đời thường, cuốn sách sẽ mang lại cho bạn những trải nghiệm quí giá để xây dựng cho mình một cuộc sống thật bình yên và hạnh phúc. Không phải ai cũng có thể hài lòng với những gì mình đang sở hữu, bởi họ cho rằng giàu có chính là sự sung túc về vật chất và tiền bạc. Vì vậy mà suốt cuộc đời, họ cứ mải miết chạy theo những giá trị vật chất bình thường mà quên đi còn vô số những giá trị khác nữa song hành bên họ. Một khi con người ta biết đủ thì mọi thiếu thốn về vật chất cũng chính là một sự sung túc trong tâm thức của mình. Một khi tâm con người ta không còn sân si thì đó chính là lúc trái tim mình luôn giàu có tình thương yêu vô bờ bến. “Dưỡng tâm giàu có - Dưỡng thân nghèo khó” là cuốn sách giá trị cho mọi độ tuổi để làm cho cuộc sống trở nên nhẹ nhàng và hạnh phúc hơn. Trong thực tế, hiếm có ai cảm thấy hạnh phúc khi bản thân sống trong cảnh nghèo túng nhưng cũng ít người vui vẻ khi giàu có. Khái niệm hạnh phúc cần được cắt nghĩa và làm rõ sâu hơn nữa so với những điều người ta gán ghép cho nó hiện nay. Hãy đọc những cuốn sách giá trị như “Dưỡng tâm giàu có - Dưỡng thân nghèo khó” để xây dựng lối sống yêu thương lành mạnh, để bồi dưỡng thân tâm mình dù nghèo khó vẫn luôn lạc quan và giàu năng lượng tích cực. Cuốn sách cũng sẽ giúp cho chúng ta tự xây dựng cho mình một cuộc sống thật hạnh phúc theo đúng nghĩa mà mình luôn mong muốn.\r\n\r\n\r\n\r\n- Ths. Thái Thu Hoài, Phó trưởng khoa Xuất bản, Phát hành. Trường Đại học Văn hóa Tp.HCM\r\n\r\n\r\n\r\n“Dưỡng tâm giàu có - Dưỡng thân nghèo khó” là cẩm nang vàng cho chặng đời mỗi người trẻ không bị bấp bênh, lạc hướng bởi những cám dỗ. Chỉ khi bạn đặt tất cả niềm tin của mình, rằng bạn xứng đáng được hạnh phúc thì mọi chuyện sẽ tốt đẹp hơn. Ai trong chúng ta cũng đều phải trải qua những thời khắc gian nan, gần như mất phương hướng; nhưng chỉ cần tiết chế tham sân si của bản thân, trở lại với bản ngã và lấy lại tâm thế, thì có thể tìm thấy hạnh phúc trong phút chốc. Triết lý xuyên suốt của cuốn sách sẽ đưa bạn vượt qua những ngày tháng khó khăn nhất với tâm thế an yên. “Dưỡng tâm giàu có - Dưỡng thân nghèo khó” là một cuốn sách có thể “gối đầu giường” cho những người trẻ tuổi nhiều ước vọng nhưng dễ thất vọng. Hãy chọn con đường đưa bạn đến hạnh phúc. Và nếu bạn đã thành công thì bạn vẫn nên có cuốn sách này để nhắc nhở mình hàng ngày.\r\n\r\n\r\n\r\n- Nhà văn Võ Thị Xuân Hà, Nguyên Trưởng Ban Nhà văn Trẻ toàn quốc, Nguyên Tổng biên tập Tạp chí Nhà văn Phó ban Thường trực Ban Sáng tác Hội Nhà văn Việt Nam\r\n\r\n\r\n\r\n______________________\r\n\r\nSBOOKS CAM KẾT:\r\n\r\n- Mọi đơn hàng đều được đóng gói tỉ mỉ và cẩn thận.\r\n\r\n- Sbooks luôn có chương trình tốt cho mọi đơn hàng.\r\n\r\n- Đổi trả sản phẩm trong vòng 7 ngày nếu lỗi từ nhà sản xuất.\r\n\r\n\r\n\r\n______________________', 1),
(16, 2, 'Cảm Biến Áp Suất Lốp Ô Tô TPMS Phiên Bản QUỐC TẾ Màn Hình Màu dùng Năng Lượng Mặt Trời (Loại gắn ngoài van', 499000, 31, 5, '☑ Phiên bản QUỐC TẾ, sử dụng hoàn toàn tiếng Anh.\r\n ☑ Trang bị VI XỬ LÝ CỦA ĐỨC (cao cấp hơn bản dùng chip TQ)\r\n ☑ MÀN HÌNH MÀU, hộp đựng lót mút chống sốc, ...\r\n ☑ HUD cảnh báo dùng nguồn năng lượng kép từ nguồn sạc USB và NĂNG LƯỢNG MẶT TRỜI\r\n ☑ Đạt chứng chỉ CE (Châu Âu) và FCC (Mỹ)\r\n ☑ Hỗ trợ ĐỔI MỚI TRONG VÒNG 10 NGÀY (giữ nguyên seal)\r\n ☑ BẢO HÀNH 12 THÁNG\r\n ☑ TẶNG KÈM 2 viên pin dự phòng (Panasonic, made in Indonesia)', 1),
(17, 2, 'Sách Tuổi Trẻ Đáng Giá Bao Nhiêu', 65700, 31, 5, 'Công ty phát hành	Nhã Nam\r\nTác giả	Rosie Nguyễn\r\nNgày xuất bản	10-2020\r\nKích thước	14 x 20.5 cm\r\nNhà xuất bản	Nhà Xuất Bản Hội Nhà Văn\r\nLoại bìa	Bìa mềm\r\nSố trang	285\r\nSKU	2431864615280\r\nTuổi Trẻ Đáng Giá Bao Nhiêu\r\n\r\n\"Bạn hối tiếc vì không nắm bắt lấy một cơ hội nào đó, chẳng có ai phải mất ngủ.\r\n\r\nBạn trải qua những ngày tháng nhạt nhẽo với công việc bạn căm ghét, người ta chẳng hề bận lòng.\r\n\r\nBạn có chết mòn nơi xó tường với những ước mơ dang dở, đó không phải là việc của họ.\r\n\r\nSuy cho cùng, quyết định là ở bạn. Muốn có điều gì hay không là tùy bạn.\r\n\r\nNên hãy làm những điều bạn thích. Hãy đi theo tiếng nói trái tim. Hãy sống theo cách bạn cho là mình nên sống.\r\n\r\nVì sau tất cả, chẳng ai quan tâm.\"\r\n\r\nNhận định\r\n\r\n\"Tôi đã đọc quyển sách này một cách thích thú. Có nhiều kiến thức và kinh nghiệm hữu ích, những điều mới mẻ ngay cả với người gần trung niên như tôi.\r\n\r\nTuổi trẻ đáng giá bao nhiêu? được tác giả chia làm 3 phần: HỌC, LÀM, ĐI.\r\n\r\nNhưng tôi thấy cuốn sách còn thể hiện một phần thứ tư nữa, đó là ĐỌC.\r\n\r\nHãy đọc sách, nếu bạn đọc sách một cách bền bỉ, sẽ đến lúc bạn bị thôi thúc không ngừng bởi ý muốn viết nên cuốn sách của riêng mình.\r\n\r\nNếu tôi còn ở tuổi đôi mươi, hẳn là tôi sẽ đọc Tuổi trẻ đáng giá bao nhiêu? nhiều hơn một lần.\"', 1),
(18, 2, 'Sách Đắc Nhân Tâm( khổ lớn)', 55900, 31, 5, 'Công ty phát hành	First News - Trí Việt\r\n\r\nTác giả	Dale Carnegie\r\n\r\nNgày xuất bản	 2022\r\n\r\nKích thước	14.5 x 20.5 cm\r\n\r\nNhà xuất bản	Nhà Xuất Bản Tổng hợp TP.HCM\r\n\r\nLoại bìa	Bìa mềm\r\n\r\nSố trang	320\r\n\r\nSKU	2436661537384\r\n\r\nTại sao Đắc Nhân Tâm luôn trong Top sách bán chạy nhất thế giới?\r\n\r\nBởi vì đó là cuốn sách mọi người đều nên đọc.\r\n\r\nHiện nay có một sự hiểu nhầm đã xảy ra. Tuy Đắc Nhân Tâm là tựa sách hầu hết mọi người đều biết đến, vì những danh tiếng và mức độ phổ biến, nhưng một số người lại “ngại” đọc. Lý do vì họ tưởng đây là cuốn sách “dạy làm người” nên có tâm lý e ngại. Có lẽ là do khi giới thiệu về cuốn sách, người ta luôn gắn với miêu tả đây là “nghệ thuật đối nhân xử thế”, “nghệ thuật thu phục lòng người”… Những cụm từ này đã không còn hợp với hiện nay nữa, gây cảm giác xa lạ và không thực tế.\r\n\r\nNhưng đâu phải thế, Đắc Nhân Tâm là cuốn sách không hề lỗi thời! \r\n\r\nNhững vấn đề được chỉ ra trong đó đều là căn bản ứng xử giữa người với người. Nếu diễn giải theo từ ngữ bây giờ, có thể gọi đây là “giáo trình” giúp hiểu mình – hiểu người để thành công trong giao tiếp. Có ai sống mà không cần giao tiếp? Có bao nhiêu người ngày ngày mệt mỏi, khổ sở vì gặp phải các vấn đề trong giao tiếp? Vì thế, Đắc Nhân Tâm chính là cuốn sách dành cho mọi người. Con cái nên đọc – cha mẹ càng nên đọc, nhân viên nên đọc – sếp càng nên đọc, người quen nhau nên đọc – người lạ nhau càng nên đọc…. Và đó mới chính thật là lý do Đắc Nhân Tâm luôn lọt vào Top sách bán chạy nhất thế giới, dù đã ra đời cách đây gần 80 năm.\r\n\r\nCó lẽ sẽ có người vừa đọc vừa nghĩ, mấy điều trong sách này đơn giản mà, ai chẳng biết? Đúng thế, vì toàn bộ đều là những quy tắc, những cách cư xử căn bản giữa chúng ta với nhau thôi. Kiểu như “Không chỉ trích, oán trách hay than phiền”, “Thành thật khen ngợi và biết ơn người khác”, “Thật lòng làm cho người khác thấy rằng họ quan trọng”… Những điều này đúng thật là ai cũng biết, nhưng bạn có chắc bạn nhớ được và làm được những điều đơn giản đó? Vì vậy, cuốn sách mới ra đời, để giúp bạn thực hành.\r\n\r\nNhưng có lẽ đa số người đọc sẽ thành thật gật gù đồng ý với từng trang sách. Ồ nếu như bình tâm suy xét lại mọi việc, thì trong bất cứ trường hợp nào mình cũng có thể cư xử đúng mực, không làm người khác tổn thương, giúp bầu không khí luôn thoải mái, và thế là cả hai bên đều vui vẻ, công việc cũng vì thế mà suôn sẻ, thành công hơn. Vậy chứ mà cũng không dễ, bởi “cái tôi” của mỗi người thường chiến thắng tâm trí trong đa số trường hợp. Để thỏa mãn nó, chúng ta hay mắc sai lầm không đáng. Đó cũng chính là lý do Đắc Nhân Tâm có mặt, để nhắc nhở và từng chút giúp ta uốn nắn chính “cái tôi” của mình.\r\n\r\nĐắc Nhân Tâm - Dale Carnegie\r\n\r\nCuốn Sách Hay Nhất Của Mọi Thời Đại Đưa Bạn Đến Thành Công\r\n\r\n\r\n\r\nĐắc nhân tâm – How to win friends and Influence People của Dale Carnegie là quyển sách nổi tiếng nhất, bán chạy nhất và có tầm ảnh hưởng nhất của mọi thời đại. Tác phẩm đã được chuyển ngữ sang hầu hết các thứ tiếng trên thế giới và có mặt ở hàng trăm quốc gia. Đây là quyển sách duy nhất về thể loại self-help liên tục đứng đầu danh mục sách bán chạy nhất (best-selling Books) do báo The New York Times bình chọn suốt 10 năm liền. Riêng bản tiếng Anh của sách đã bán được hơn 15 triệu bản trên thế giới. Tác phẩm có sức lan toả vô cùng rộng lớn – dù bạn đi đến bất cứ đâu, bất kỳ quốc gia nào cũng đều có thể nhìn thấy. Tác phẩm được đánh giá là quyển sách đầu tiên và hay nhất, có ảnh hưởng làm thay đổi cuộc đời của hàng triệu người trên thế giới.\r\n\r\n\r\n\r\nKhông còn nữa khái niệm giới hạn Đắc Nhân Tâm là nghệ thuật thu phục lòng người, là làm cho tất cả mọi người yêu mến mình. Đắc nhân tâm và cái Tài trong mỗi người chúng ta. Đắc Nhân Tâm trong ý nghĩa đó cần được thụ đắc bằng sự hiểu rõ bản thân, thành thật với chính mình, hiểu biết và quan tâm đến những người xung quanh để nhìn ra và khơi gợi những tiềm năng ẩn khuất nơi họ, giúp họ phát triển lên một tầm cao mới. Đây chính là nghệ thuật cao nhất về con người và chính là ý nghĩa sâu sắc nhất đúc kết từ những nguyên tắc vàng của Dale Carnegie.', 1),
(19, 2, 'Sách - Người Dẫn Lối Cảm Xúc', 179000, 31, 5, 'Tên Nhà Cung Cấp	Thái Hà\r\n\r\nTác giả	Sun Hwa Oh, Goojakka\r\n\r\nNgười Dịch	Vương Thuý Quỳnh Anh\r\n\r\nNXB	Lao Động\r\n\r\nNăm XB	2022\r\n\r\nTrọng lượng (gr)	300\r\n\r\nKích Thước Bao Bì	20 x 14 cm\r\n\r\nSố trang	248\r\n\r\nHình thức	Bìa Mềm\r\n\r\nNgôn ngữ  Tiếng việt\r\n\r\n\r\n\r\nGiới thiệu    Tên của tớ là Thỏ Mộng Mơ\r\n\r\n\r\n\r\nBố đặt tên cho tớ như vậy là để nhắc nhở tớ rằng đừng bao giờ quên đi giấc mơ của mình. Nhưng tớ nghĩ cái tên và cuộc đời mình chẳng liên quan gì đến nhau hết. Tớ chẳng thích làm gì và cũng chẳng biết mình giỏi thứ gì. Đến cả giấc mơ của mình, tớ cũng chẳng biết luôn. Lúc tớ kể nỗi băn khoăn ấy với Thỏ Nhiệt Huyết, bạn ấy đã nói thế này: “Cậu thử nói chuyện với bố xem. Mỗi khi có người đăng băn khoăn lên trang web ‘Cố vấn cho thanh thiếu niên’ của bố cậu, ông ấy đều trả lời hết sức tận tình mà. Sống chung nhà với một người như vậy mà sao cậu lại không tận dụng cơ hội chứ?”.\r\n\r\n\r\n\r\nTrích đoạn sách hay:\r\n\r\n\r\n\r\nRốt cuộc giấc mơ của em là gì nhỉ?\r\n\r\n\r\n\r\nMọi người thường nhắc đến “vision”, tức là “tương lai”, nhưng chỉ cần nhắc đến hai chữ “tương lai” là em cảm thấy buồn bực rồi.\r\n\r\n\r\n\r\nKhông biết là “tương lai” của em trốn ở nơi nào cơ chứ?\r\n\r\n\r\n\r\nHừm… Không có gì lạ khi em cảm thấy buồn bực.\r\n\r\n\r\n\r\nRất nhiều người nói về giấc mơ hay tương lai, nhưng thực ra để xác định được tương lai của mình không phải là chuyện đơn giản. Chắc em sẽ cảm thấy được an ủi một chút khi biết rằng không chỉ riêng em mà nhiều người khác cũng vậy, đúng không?\r\n\r\n\r\n\r\nUri Shulevitz có viết một câu truyện cổ tích tên là Báu vật. Nhân vật chính là ông lão Issac cực kỳ nghèo khó. Một ngày nọ, ông mơ thấy một kho báu vật được cất giấu ở một cung điện xa xôi. Ông lão Issac tin vào giấc mơ của mình, đã vượt núi lội sông và tìm đến được đến cung điện ấy. Ông kể lại giấc mơ của mình với người lính canh và nhờ anh ta tìm giúp báu vật. Thế nhưng, anh lính canh lại cười nhạo ông lão rằng:\r\n\r\n\r\n\r\n“Tôi cũng có một giấc mơ ông lão ạ. Tôi mơ thấy báu vật được cất giấu ở dưới bếp lò nhà ông lão Issac sống rất xa nơi này. Ông thử đến đó tìm xem sao.”\r\n\r\n\r\n\r\nThế là, ông lão Issac lại tiếp tục vượt núi lội sông để trở về nhà. Khi về đến nhà, ông thực sự đã tìm thấy báu vật ở dưới bếp lò.\r\n\r\n\r\n\r\nThỏ Mộng Mơ à, giấc mơ là một báu vật quý giá. Để tìm được thứ quý giá ấy, em sẽ tốn rất nhiều thời gian, công sức, và cũng có thể còn phải quay lại để kiếm tìm giống như ông lão Issac vậy. Đến cuối cùng, thứ quý giá có khi lại đang ở dưới bếp lò nhà em cũng nên. Em bảo nhà mình không có bếp lò hả? Ha ha ha ha. Nếu vậy, em hãy thử nhìn vào trái tim mình xem sao, bởi vì thứ em thực sự yêu thích, thứ em thực sự giỏi có thể đang nằm ở dưới bếp lò trong trái tim em đấy', 1),
(20, 2, 'Sách - Tôi Quyết Định Sống Cho Chính Tôi (Bản Không Sổ To Do List)', 59000, 31, 5, 'GIỚI THIỆU SÁCH\r\nTÔI QUYẾT ĐỊNH SỐNG CHO CHÍNH TÔI\r\n-	KIM SUHYUN –\r\n\r\nCuốn sách đạt giải thưởng “Cuốn sách của năm” tại Hàn Quốc vào năm 2017.\r\nĐã tái bản hơn 100 lần kể từ lần xuất bản đầu tiên vào năm 2016.\r\nĐược nhiều nghệ sĩ chọn đọc và giới thiệu đến các fan của mình, đơn cử là JungKook (BTS).\r\n\r\nNhư tác giả Kim Suhyun đã chia sẻ về quá trình sáng tác cuốn sách này:\r\n“Tôi đã cố gắng ghi ra những điều quan trọng nhất trong cuộc sống như: \r\nCông việc, các mối quan hệ, niềm vui, tinh thần, thể chất, sức khỏe…\r\nTôi đã không lường trước những việc có thể xảy ra . Mà tôi chỉ làm (Do) những việc tôi muốn (Want) và có thể (Can).  \r\nMột công thức đơn giản là Want + Can = Do\r\nThay vào đó, hãy làm việc chăm chỉ. \r\nTôi dần tránh những mối quan hệ chỉ nhấn chìm tôi hoặc không quan trọng với tôi và tự nhủ rằng tôi sẽ không cho phép bản thân làm bất kể điều gì một cách tùy tiện nữa. 	\r\nTôi dành thời gian tìm kiếm niềm vui trong cuộc sống. \r\nCó những ngày, tôi ngồi ngắm bầu trời vài lần để cảm nhận cuộc sống. Tôi đối diện với những vấn đề còn bỏ ngỏ, giải quyết chúng và cố gắng sống lành mạnh. \r\nVì những đáp án do xã hội quy định khác xa những quan niệm thông thường của mọi người xung quanh chúng ta nên chỉ có một điều mà chúng ta biết chắc, đó là cuộc sống rất rõ ràng và nhẹ tựa lông hồng. \r\nVậy ý nghĩa của cuộc sống rốt cuộc là gì? \r\nSau nhiều lần chất vấn, câu trả lời tôi tìm thấy không còn là đáp án cá nhân mà trở thành đáp án của cả một cộng đồng, đó chính là hiện thực hóa giá trị của bản thân trong xã hội. \r\nTôi luôn quan tâm và khích lệ sự đóng góp của cá nhân cho một xã hội tương lai. \r\nBởi vì giải quyết các vấn đề xã hội chính là một cách trực tiếp để giải quyết các vấn đề cá nhân.\r\nNhưng đó không phải lí do duy nhất khiến chúng ta đóng góp một phần bản thân vào xã hội.\r\nThông qua cuốn sách này, tôi muốn lan truyền một làn sóng dù nhỏ nhưng có ý nghĩa đến mọi người.\r\nNhững câu hỏi không có hồi kết như “Chúng ta phải sống thế nào?” luôn nảy sinh trong cuộc sống. Để tôi nói cho bạn nghe câu trả lời của tôi nhé, đó chính là: “Hãy sống tốt với đời mình.” \r\nBạn không cần phải phức tạp hóa mọi vấn đề. \r\nBạn chỉ cần làm việc chăm chỉ, tâm sự với những người thân yêu, cùng nhau ăn những món ngon, chia sẻ với nhau những bài hát yêu thích, những cuốn sách hay vào một ngày đẹp trời bạn tận hưởng ánh mặt trời. \r\nTôi nghĩ rằng một cuộc sống tốt đẹp trọn vẹn chính là chuỗi ngày ấm áp, bình dị đang diễn ra. Nếu có thể, hãy bước thêm một nhịp để hướng tới cuộc sống có ý nghĩa.\r\nMặc dù trong vũ trụ này còn tồn tại những hạt bụi gây vẩn đục cuộc sống thì chúng ta vẫn phải vượt qua và giữ lấy phẩm giá, tôn nghiêm của chính mình. Bất chấp quy luật thành bại của thế giới, tôi vẫn muốn tự hào về một cuộc sống như vậy. \r\nCó một cuốn sách viết rằng: “Dù bạn sống thế nào, tôi vẫn luôn cổ vũ.”\r\nTuy nhiên, điều cần thiết không phải là người khác cổ vũ bạn mà bạn phải cổ vũ chính mình. \r\nGửi đến bản thân tôi - bạn đồng hành với tôi cho đến cuối cuộc đời:\r\n“Dù tôi sống thế nào, tôi vẫn luôn cổ vũ chính mình.””\r\n\r\nThông tin sách:\r\n-	Tên sách: Tôi quyết định sống cho chính tôi\r\n-	Tác giả: Kim Suhyun\r\n-	Người dịch: Đỗ Phương Nhung\r\n-	Thể loại: tản văn\r\n-	Giá bìa: 88.000 vnđ\r\n-	Số trang: 236 trang\r\n-	Khổ sách: 13,5 x 19.5 cm \r\n-	Loại bìa: bìa mềm tay gấp\r\n-	Phụ bản: Tặng kèm 1 sổ to do list 2020\r\n-	Nhà xuất bản liên kết: NXB Thanh Niên\r\n-	Đơn vị phát hành: công ty TNHH Văn hóa & TT AZ VIETNAM – Thương hiệu sách MINTBOOKS \r\n-	ISBN: 978-604-978-141-4\r\n-	Mã công ty: 8936186543999\r\n-	Ngày phát hành: 29/12/2019', 1),
(21, 2, 'Sách - Những Điều Tốt Đẹp Luôn Đúng Hạn Mà Đến', 72000, 31, 5, 'Những Điều Tốt Đẹp Luôn Đúng Hạn Mà Đến\r\n\r\nQuà tặng: Tặng kèm 3 postcard\r\nTên tác giả: Cá yêu tinh – Rei                                   \r\nKhổ sách:  13.5 x 19.5 cm\r\nSố trang: 176 trang\r\nNhà xuất bản: Văn Học\r\nThương hiệu: Người Trẻ Việt\r\nLoại bìa: Bìa mềm\r\nThể loại: Tản Văn\r\nGiá: 96.000 VNĐ\r\nDự kiến phát hành Hà Nội: 06/03/2022                              Sài Gòn: 06/03/2022\r\nMã ISBN:   9786043492743                                                 \r\nMã công ty: 8935325004193\r\n\r\n* * *\r\nGiới thiệu sách: \r\n\r\nCó bao giờ bạn gặp chuyện phiền muộn, chỉ vì một chuyện nhỏ mà tâm trạng buồn bực, không thể giải thích?\r\nCó bao giờ bạn cảm thấy cô đơn, cố tỏ ra là mình ổn rồi trở về một góc nhà gặm nhấm chỉ mong có ai đó để hướng về?\r\nCó bao giờ bạn cảm thấy cuộc sống giống như một hành lang dài, bạn có cố gắng mở vô số cửa sổ thì kết quả nhận lại là những khoảng không vô định và  tối om?\r\nChắc hẳn trong khoảnh khắc nào đó của cuộc đời, ai cũng có những mệt mỏi đến nỗi chẳng mở mắt nổi để nhìn ra chút ánh sáng le lói như ngọn nến trước gió dù bé nhỏ nhưng quyết không để tắt. Nhưng đừng lo bạn nhé, vì chắc chắn những điều tốt đẹp sẽ luôn đúng hạn mà đến thôi. \r\n\r\nĐó cũng chính là thông điệp mà Cá Yêu Tinh và Rei muốn nhắn gửi đến bạn. \r\n\r\nCuốn sách tựa những dòng nhật ký đầy chân thật, trong sáng và giản dị, truyền tải thông điệp tích cực: Dù có chuyện gì đi nữa thì ngày mai mặt trời vẫn mọc, dù bị thực tại làm té ngã hàng vạn lần cũng đừng quên đứng dậy, phủi bụi trên người rồi hạnh phúc sẽ đến với những người biết chờ đợi. \r\n\r\nHy vọng vào những điều tốt đẹp để mong rằng “Có thể cùng nhau đi khắp cùng trời cuối đất. Lúc còn trẻ có thể rong ruổi tận chân trời, khi về già có thể bình bình đạm đạm sống trong an yên. Cả một đời này, không cầu phú quý, chỉ cầu bình an”.\r\n\r\nCòn bạn, bạn đã sẵn sàng để đón nhận những điều tốt đẹp sắp tới chưa?', 1),
(22, 2, 'Ghế Massage Trị Liệu Toàn Thân Đa Chức Năng T22 Công Nghệ Điều Khiển Giọng Nói Cao Cấp', 39990000, 31, 3, 'TÍNH NĂNG THÔNG MINH\r\n\r\n* Công nghệ I-ON âm làm sạch không khí xung quanh:\r\n\r\nI-on âm có tác dụng lọc và khử mùi khó chịu, loại bỏ bớt các loại bụi siêu nhỏ và các chất rắn lơ lửng trong không khí. Ở trạng thái lơ lửng, các Ion âm tự bám lấy, làm kết tủa tạo thành các hạt nặng dần và rơi xuống đất, cho không khí thêm trong lành. Bên cạnh đó Ion âm làm giảm thời gian máu ngưng tụ, làm tăng hàm lượng oxy trong máu, có lợi với việc vận chuyển, hấp thụ và sử dụng oxy trong máu.\r\n*Hệ thống túi khí kép \r\n\r\n Tích hợp hoàn hảo hệ thống túi khí massage tại nhiều vùng trên cơ thể như đầu, vai gáy, hông, cánh tay, bắp chân. Các túi khí bóp nhả nhẹ nhàng giúp cải thiện tốt quá trình lưu thông máu, thúc đẩy điều hòa huyết áp, giảm tê mỏi, căng cứng. Đặc biệt, túi khí bàn tay massage dạng lượn sóng, đem lại cảm giác chân thực nhất.\r\n* Trải nghiệm công nghệ massage 4D\r\n\r\nCon lăn 4D thế hệ mới giúp massage sâu và rộng, có thể điều chỉnh độ rộng của con lăn, tự động ngắt – dừng hoạt động khi rời ghế, massage sâu vùng cổ vai gáy, nâng cao hiệu quả massage.\r\n* Công nghệ điều khiển giọng nói thế hệ thứ III (cao nhất)\r\n\r\nChức năng này đặc biệt phát huy tác dụng với đối tượng sử dụng ghế như người già, mắt kém, hoặc những người khó tiếp cận với công nghệ. Bạn chỉ cần nói lên yêu cầu, ghế sẽ tự động bắt sóng và thực hiện theo.\r\n* Chế độ massage không trọng lực\r\n\r\nVới phương pháp massage không trọng lực mô phỏng ghế của các phi hành gia giúp bạn tận hưởng phút giây thư giãn tối đa. Các con lăn massage có tiếp xúc tốt hơn với khu vực thắt lưng, giúp tăng cường tuần hoàn máu, giảm áp lực lên cột sống, cải thiện hệ thô hấp.\r\n🔴 LỢI ÍCH ĐEM LẠI\r\n\r\n- Giúp bạn có giấc ngủ ngon, sâu hơn tinh thần luôn thoải mái, minh mẫn, tránh stress.\r\n\r\n- Massage thường xuyên giúp loại bỏ các vấn đề đau nhức đầu, đau mỏi lưng, tê cứng chân tay.\r\n\r\n- Tăng khả năng tuần hoàn máu, giúp lưu thông máu nhanh cải thiện huyết áp.\r\n🔴 CHÍNH SÁCH BẢO HÀNH \r\n\r\n- Cam kết hàng chính hãng 100%\r\n\r\n- Đổi trả miễn phí trong vòng 15 ngày, đổi mới ngay lập tức nếu sản phẩm lỗi\r\n\r\n- Miễn phí giao hàng và lắp đặt tận nơi trên toàn quốc\r\n\r\n- Bảo hành 6 năm về máy móc, 3 năm về da.\r\n\r\n- Bảo trì trọn đời\r\n\r\n- Tặng nhiều phần quà hấp dẫn: Máy đo huyết áp, đường huyết, gối chữ U, bạt phủ', 1),
(23, 2, 'Giàn tạ tập đa năng 3 vị trí FUJILA Zasami KZ-82 - Thiết kế cho các bài tập thể lực toàn diện tại nhà', 36000000, 31, 3, '💎Nếu bạn đang tìm kiếm một thiết bị hỗ trợ tập thể hình chuyên nghiệp ngay tại nhà thì giàn tạ đa năng của Fujila Sport là một lựa chọn tuyệt vời!\r\n💎CHÍNH SÁCH BẢO HÀNH CỦA FUJILA\r\n✔️Cam kết hàng chính hãng 100%\r\n✔️Đổi trả miễn phí trong vòng 15 ngày, đổi mới ngay lập tức nếu sản phẩm lỗi\r\n✔️Miễn phí giao hàng và lắp đặt tận nơi trên toàn quốc\r\n✔️Bảo hành 3 năm về máy móc\r\n✔️Bảo trì trọn đời\r\n💎ĐẶC ĐIỂM CỦA GIÀN TẠ TẬP ĐA NĂNG FUJILA SPORT\r\nGiàn tạ tập KZ-82 có ba cụm tập luyện chính:\r\n\r\n1) Cụm tập tạ: Được thiết kế cho các bài tập kéo xô, ép ngực, đẩy ngực, tập tay, đá đùi, kéo xô lưng.\r\n\r\n2) Cụm xà đơn - xà kép: Được thiết kế cho các bài tập thân trên như lên xà đơn, xà kép, tập bụng với khung, gập bụng với ghế cong.\r\n\r\n3) Cụm boxing: Với bao đấm bọc da, hỗ trợ cho các bạn tập boxing.\r\n\r\nKhung giàn được làm từ thép chuyên dụng phủ sơn tính điện giúp tăng độ bền và chống gỉ.\r\n\r\nNgười dùng có thể thay đổi trọng lượng tạ thông qua 1 chốt nhỏ, từ đó nâng dần độ khó, cho hiệu quả tập luyện cao hơn.\r\n\r\nVới giàn tạ đa năng 3 vị trí Zasami KZ-82 bạn có thể thỏa mãn đam mê tập thể hình ngay tại nhà.\r\n\r\n💎LỢI ÍCH KHI SỬ DỤNG GIÀN TẠ TẬP ĐA NĂNG FUJILA KZ-8\r\n\r\n✔️Với KZ-82 bạn có thể tập luyện mỗi ngày và phát triển toàn bộ các nhóm cơ trên cơ thể: cơ bắp tay, cơ bắp chân, bắp đùi, cơ ngực, cơ xô... khỏe mạnh hơn.\r\n\r\n✔️Các bài tập bụng với khung tập tổng hợp về ghế cong đem lại 1 vòng 2 săn chắc, thon gọn.\r\n\r\n✔️Các bài tập boxing giúp cơ thể linh hoạt, tăng cường khả năng phối hợp của chân, tay và các cơ, khớp trên cơ thể.\r\n\r\n✔️Tập luyện mỗi ngày với giàn tạ còn giúp bạn tăng cường lưu thông máu, trao đổi chất dưới da, từ đó phòng ngừa được nhiều căn bệnh nguy hiểm.\r\n\r\n✔️Một cơ thể khỏe mạnh sẽ giúp bạn hoàn thành tốt các mục tiêu trong cuộc sống; Sống khỏe mạnh và có ý nghĩa hơn.', 1),
(24, 2, 'Máy chạy bộ SHUA E7 SH-T399P Ibox để có mức giá Hấp Dẫn hơn', 24990000, 31, 3, 'Máy chạy bộ điện chuyên dùng cho gia đình, xịn sò xứng đáng với số tiền bỏ ra\r\n\r\nThương hiệu: SHUA\r\n\r\nMã lực: DC 1.25HP\r\n\r\nPhạm vi tốc độ: 0,5-16km / h\r\n\r\nKích thước máy chạy bộ: 480 * 1350mm\r\n\r\nThông số kỹ thuật màn hình: 3 cửa sổ LED trắng\r\n\r\nHệ thống giảm chấn: Cột đệm 6 vị trí\r\n\r\nKích thước sản phẩm: 1715 * 850 * 1445mm\r\n\r\nKích thước gấp lại: 1070 * 850 * 1445mm\r\n\r\nTrọng lượng: 101kg\r\n\r\nTrọng lượng người dùng tối đa: 126kg\r\n\r\nMáy chạy bộ có kết nối tốc độ Bluetooth cao, 2 loa giúp phát nhạc không giây thư giãn khi chạy bộ. Có kết nối USB \r\n\r\nMáy sẵn kho cho nhu cầu gia đình\r\n\r\nSẽ có giá tốt cho khách hàng thật sự có nhu cầu\r\n\r\nMáy chạy bộ Shua E7 SH- T399 thu hút được người dùng bởi nó đáp ứng được hầu hết mọi nhu cầu tập luyện của khách hàng trong việc tăng cường sức khỏe và giúp mang lại vóc dáng thon gọn như mơ ước.\r\n\r\nMáy có dạng hình chữ z vừa mang lại tính cổ điển vừa mang tới sự sang trọng cho ngôi nhà. Với kết cấu cũng như hình dạng như vậy nên máy có tổng trọng lượng là 101kg, có thể chịu được tải trọng tối đa 126kg của người tập. Chính vì thế, phù hợp với hầu hết thể trạng tập luyện với cường độ trong các gia đình Việt nam.\r\n\r\nThảm chạy rộng có khả năng chống trượt đủ diện tích rộng rãi giúp bạn tự tin sải bước như đang chạy bộ ở ngoài.\r\n\r\nMáy có hệ thống đàn hồi, giảm xóc được cải tiến vượt bậc, nhằm tạo ra cảm giác mềm mại, thoải mái cho người chạy nhờ vào miếng đệm cao su tự nhiên.\r\n\r\nMáy có giá đỡ điều chỉnh đặt điện thoại hoặc Ipad cho bạn sự thoải mái tầm nhìn khi luyện tập, bạn có thể nhìn thẳng về phía trước, nâng cao độ an toàn.\r\n\r\nMàn hình hiển cảm ứng LED rộng, độ cảm biến cao, sắc nét, tích hợp giải trí đa phương tiện và các chức năng đo hiển thị.\r\n\r\n\r\nVới hơn 20 năm danh tiếng, Shua là dòng sản phẩm có thương hiệu uy tín, nếu bạn lựa chọn Shua để luyện tập thể dục thể thao thì đây là sự lựa chọn đún đắn giành cho bạn.\r\n\r\n+ CHÍNH SÁCH ĐỔI TRẢ HÀNG\r\n. Trường hợp chấp nhận đổi trả hàng\r\n- Đổi trả hàng trong trường hợp sai hàng, hỏng hàng, hàng không đủ số lượng \r\n\r\n+ TRƯỜNG HỢP KHÔNG CHẤP NHẬN ĐỔI TRẢ HÀNG\r\n- Hỏng hàng do quá trình sử dụng của khách hàng \r\n- Sai hàng, thiếu hàng, lỗi hàng nhưng khách không quay Video\r\n\r\n#máy_chạy_bộ_gia_đình_loại_nào_tốt', 1),
(25, 2, 'Xe Điện Lihaze Mini Gấp Gọn | Khuyến mãi tặng kèm giỏ sắt - Xe Điện HOT (pin LITHIUM )', 19900000, 31, 3, 'XE ĐIỆN LIHAZE - XE ĐIỆN MINI SIÊU HOT\r\nHiện mẫu xe LIHAZE đang rất được ưu chuộng tại thị trường Việt Nam, thu hút sự chú ý của cả giới nghệ sĩ, diễn viên, người mẫu. Điển hình là Nữ hoàng nội y NGỌC TRINH đã sử dụng chiếc xe này.\r\n\r\n🚴️  Xin chào các bạn, shop xin giới thiệu tới các bạn mẫu xe cực kì hot\r\n👉 Giới thiệu mẫu xe : Lihaze - mẫu mới 2020\r\nPin khủng - khung xe chịu lực - Nhiều công nghệ mới\r\nHàng có sẵn - luôn tìm kiếm thêm đại lý phân phối có đủ năng lực.\r\n👉  Xe điện mini bên mình bán là hàng chuẩn, đúng 100% với thông số quảng cáo, xe đi chắc chắn, êm ái và bền. Được đặt hàng nhập khẩu trực tiếp nguyên chiếc và chính hãng nhà máy tại Quảng Châu, chứ không phải hàng biên giới trôi nổi, bài giới thiệu thì đi copy bên mình, xe về chỉ chạy dc 5-10km đã hết pin.  \r\n\r\nTHÔNG TIN SẢN PHẨM\r\n👉Xe điện LIHAZE là dòng xe mới  2020.\r\n👉Xe điện thông minh thiết kế đáp ứng nhu cầu của bạn với 3 chỗ ngồi và giỏ cực lớn\r\n👉Xe xử lí đa chức năng, màn hình hiển thị LCD hiển thị mức độ pin\r\n👉Hệ thống phanh kép nhanh nhạy: phanh đĩa trước, phanh cơ sau giúp xử lí các tình huống bất ngờ tốt hơn\r\n👉Giảm xóc trước và sau giúp xe di chuyển nhẹ nhàng, êm ái trên nhiều địa hình\r\n👉Giỏ lưu trữ lớn, chắc chắn, đi mua sắm, đựng nhiều đồ, để thú cưng đều được. \r\n👉Khóa cơ chống trộm, còi báo động kiểm soát hiệu quả\r\n👉Chiếc xe mini có cổ xe gấp xuống được, ghế ngồi có thể điều chỉnh cao thấp, có lớp sơn bóng mượt.\r\n👉Phía trước thiết kế đèn chiếu sáng tròn, to. Rất độc đáo, phá cách.\r\n👉Xe có tốc độ tối đa đảm bảo an toàn với dòng xe điện mini 25-30km/h.\r\n👉Tải trọng xe trên 120kg.\r\n👉 Chất liệu bánh: chất liệu bằng cao su, dùng xăm lốp\r\n👉 Trang bị: Còi, đèn Led ban đêm, xi-nhan, đèn báo phanh, màn hình báo Pin.\r\n👉 Có nhiều màu sắc để lựa chọn\r\n👉 Thời gian sạc điện: 4-5h\r\nHiện mẫu xe LIHAZE đang rất được ưu chuộng tại thị trường Việt Nam, thu hút sự chú ý của cả giới nghệ sĩ, diễn viên, người mẫu.\r\n==> 1 chiếc xe với quá nhiều ưu điểm nổi trội cùng 1 thương hiệu xe điện đình đám có thể thuyết phục những vị khách hàng khó tính nhất, \r\nSo sánh với những dòng xe điện đắt tiền thì mẫu xe điện LIHAZE có tất cả những thứ là xe điện đắt tiền có,với giá rẻ bất ngờ vậy nên bạn còn chần chừ gì nữa \r\n\r\n❤️ Chính sách BẢO HÀNH CAM KẾT từ Trung tâm xe điện MB Bike:\r\n-BH pin Xe Cân Bằng trong vòng 3 tháng. Bao đổi trong vòng 7 ngày theo chính sách của MB Bike.\r\n-Ảnh sản phẩm do MB Bike tự chụp , cam kết giống hình 100%, giữ nguyên bản quyền hình ảnh.\r\n-Hàng luôn có sẵn, đóng gói cẩn thận trước khi giao đi.\r\n-Hỗ trợ 24/24, tư vấn nhiệt tình trước và sau khi bán hàng...^^...\r\n------------------------------------\r\n👉 Đừng ngại, hãy Click liên hệ Inbox để MB Bike hỗ trợ tốt nhất cho bạn nhé...^^...\r\n❤️ Thông tin liên hệ hỗ trợ, BẢO HÀNH:\r\n☎️ Hotline : 0913.194.888 - 0985.965.278\r\n🏠 Cửa Hàng: Số 132 , Đặng Thái Thân , Phường Quang Trung, Tp Vinh, Nghệ An', 1);
INSERT INTO `Product` (`product_id`, `shop_id`, `name`, `price`, `quantity`, `category_id`, `description`, `is_available`) VALUES
(26, 2, 'Giày thể thao Nike Hyper Adapt 1.0', 1200000, 31, 3, 'Siêu phẩm đình đám Nike Hyper Adapt 1.0\r\n⏭️⏭️ Chất lượng, chất liệu tuyệt vời \r\n⏭️⏭️ Giày hightech, có đèn led...\r\n⏭️⏭️ Tặng ngay voucher giảm giá 10% cho lần thanh toán tiếp theo\r\n👉👉 Đặt hàng #CMT_SĐT sẽ có nhân viên gọi điện tư vấn về size và cách phối đồ ạ\r\n------------------------------------------\r\n🏆 NIKEFANVN cam kết: \r\n1️⃣Sản phẩm giống trong hình 100%, không giống không nhận hàng\r\n2️⃣Chất liệu cao cấp 100%\r\n3️⃣Đổi mới hoàn toàn trong 7 ngày khi lỗi sản phẩm hoặc kích cỡ không vừa chân\r\n► NIKEFANVN NÓI KHÔNG VỚI HÀNG KÉM CHẤT LƯỢNG.\r\n--------------------------------------\r\nCách ĐẶT HÀNG nhanh nhất:\r\nÁp dụng cho 50 khách hàng đầu tiên, vì vậy anh em hãy nhanh chóng【#COMMENT】ngay bên dưới nhé!\r\n➡ COMMENT_SĐT để được tư vấn và đặt hàng NGAY', 1),
(27, 2, 'QKCASE - Loa Bluetooth Mini - Loa Trứng Nhấp Nháy (Loa Blt)', 38900, 31, 2, 'QKCASE - Rẻ Nhất Thị Trường\r\nNhiều màu sắc để lụa chọn\r\nGiới thiệu Loa Bluetooth HLD-600 đèn led nháy theo nhạc\r\nSản phẩm gồm : 1 loa + 1 cáp sạc\r\nLoa Bluetooth Beats HLD 600 mini  được đa số người dùng ưa chuộng bởi tính di động tuyệt vời, cùng với thiết kế bắt mắt. Với công nghệ kết nối bằng Bluetooth giúp bạn thoải mái nghe nhạc di động mọi nơi mà không phải bị đau tai khi mang tai phone hay vướng víu bởi dây kết nối.\r\n\r\nThông số kỹ thuật loa mini Bluetooth HLD 600:\r\n· Hỗ trợ A2DP, AVRCP, tai nghe.\r\n· Bluetooth phiên bản: V2.1\r\n· Phạm vi làm việc: ~10m\r\n· Thời gian làm việc: 1-3 giờ\r\n· Kích thước: 60 x 60 x 50mm\r\n· Trọng lượng tịnh: 200g\r\n· Loa Output: 3w\r\n· Tần số đáp ứng: 280HZ - 16KHz\r\n· Signal-to-noise: ≥ 95dB\r\n· Distortion: ≤ 0,5%\r\n· Pin điện áp / công suất: 520mAh\r\n· Pin sạc điện áp: 5V ± 0.5V\r\n· Thời gian sạc pin: 2 giờ', 1),
(28, 2, 'Tai nghe chụp tai không dây bluetooth STN28 màu sắc cute,âm thanh ấm bass mạnh- Phụ Kiện 123', 199000, 31, 2, 'Ra đời từ năm 2019,  Phụ kiện điện thoại 123 tự hào là một trong những đơn vị phân phối các sản phẩm điện tử uy tín trên thị trường phụ kiện - đồ chơi điện tử hiện nay. Các sản phẩm chính của Phụ kiện điện thoại 123 chủ yếu là sạc dự phòng, tai nghe Bluetooth, loa Bluetooth, cáp sạc điện thoại và các phụ kiện hỗ trợ khác. Với tiêu chí giá thành rẻ đi cùng chất lượng tốt, nhiều mẫu mã và màu sắc, các sản phẩm của Phụ kiện điện thoại 123 chính là lựa chọn hàng đầu dành đa dạng các tệp khách hàng khác nhau, đặc biệt là giới trẻ.\r\n\r\nTHÔNG SỐ SẢN PHẨM TAI NGHE MÈO BLUETOOTH STN28\r\n- Màu sắc: Hồng/Tím/Đen/Xanh ngọc/Xanh dương/Xanh lam\r\n- Bluetooth: V5.0 (Tương thích ngược 2.1/2.0/1.2)\r\n- Sạc USB: DC 5V\r\n- Tần số: 87.5 - 108MHz\r\n- Khoảng cách kết nối: 10m\r\n- Thời gian sạc: 1.5h\r\n- Độ nhạy: 90dB/mW\r\n- Thời gian sử dụng liên tục: 3-4h\r\n- Thời gian chờ: 40h\r\n- Dung lương PIN: 400mAh\r\n- Loại:  Fullsize\r\n\r\nĐẶC ĐIỂM NỔI TRỘI CỦA TAI NGHE MÈO BLUETOOTH STN28\r\n- Thiết kế thông minh: Có khả năng gấp gọn lại cũng như kéo dãn ra để vừa mọi cỡ đầu. \r\n- Chất liệu cao cấp: Phần khung được làm bằng nhựa cao cấp cực bền và không gây mùi khó chịu.\r\n- Kết nối đa dạng: Kết nối không dây bằng Bluetooth cho phạm vi kết nối lên tới 10m. Hỗ trợ mic đàm thoại khả năng tương thích với các smartphone là 100%. Hỗ trợ kết nối có dây AUX 3.5mm có thể sử dụng với laptop (Hỗ trợ mic). Hỗ trợ thẻ nhớ (Cứ download những bài hát hình thích vào thẻ nhớ nhé vào tai nghe và thưởng thức thôi).\r\n- Chất lượng âm thanh cực tốt trong tầm giá: Bass treb thể hiện rõ ràng trong từng dải, với mức âm lượng lớn cũng không bị rè. Công nghệ Bluetooth v5.0 ổn định tín hiệu và khắc phục vấn đề delay khi chơi game so với các công nghệ bluetooth cũ. Tai nghe loại Fullsize giúp chất lượng âm bass và âm lượng cao, khoảng âm lớn hơn, khả năng lọc tiếng ồn tốt.\r\n- Nệm tai cao cấp cực kì êm ái và thoáng khí.\r\n- Đèn led tích hợp theo tai nghe có thể tùy chỉnh tắt/bật\r\n\r\nCHÍNH SÁCH ĐỔI TRẢ HÀNG TẠI PHỤ KIỆN ĐIỆN THOẠI 123\r\n- Cam kết phân phối sản phẩm 100% chính hãng.\r\n- Hỗ trợ vận chuyển với các đơn hàng từ 50k trở lên.\r\n- Hỗ trợ đổi/trả hàng, hoàn tiền nếu xảy ra các lỗi do nhà sản xuất trong vòng 07 ngày.\r\n- Sản phẩm chính hãng, được bảo hành 1 đổi 1 theo tiêu chuẩn của hãng.\r\n\r\n\r\n* Lưu ý:\r\n- Sản phẩm đổi trả phải còn nguyên mác niêm phong, chưa qua sử dụng.\r\n- Khi nhận hàng, khách hàng nên quay lại video mở sản phẩm để đảm quyền lợi khi phát sinh sự cố phải đổi trả sản phẩm.\r\n- Khi đổi trả sản phẩm, nếu không phải lỗi sản phẩm hay shop giao sai sản phẩm, quý khách vui lòng trả phí vận chuyển.', 1),
(29, 2, 'Tai nghe bluetooth 5.0 True Wireless Amoi F9 Pro bản quốc tế cao cấp cảm biến vân tay , kiêm sạc dự phòng', 119000, 31, 2, '📌 THÔNG SỐ KỸ THUẬT:\r\n\r\n\r\n\r\n-	Model: Amoi F9 PRO\r\n\r\n\r\n\r\n-	Phiên bản Bluetooth: 5.0 mới nhất\r\n\r\n\r\n\r\n-	Chất liệu: Vỏ ngoài bằng nhựa phủ Polymer\r\n\r\n\r\n\r\n-	Dung lượng pin từng tai: 50 mAh *2 \r\n\r\n\r\n\r\n-	Dung lượng dock sạc: 2000 mAh\r\n\r\n\r\n\r\n-	Phạm vi kết nối: 10m\r\n\r\n\r\n\r\n-	Sạc tiêu chuẩn: MicroUSB\r\n\r\n\r\n\r\n-	Tần số âm thanh: 50Hz-24kHz\r\n\r\n\r\n\r\n-	Thời gian nghe nhạc: 3-3,5h\r\n\r\n\r\n\r\n-	Thời gian đàm thoại liên tục: 3-4h\r\n\r\n\r\n\r\n-	Trọng lượng tai nghe: 150g\r\n\r\n\r\n\r\n-	Trọng lượng gói: 200 gr\r\n\r\n\r\n\r\n-	Đóng gói sản phẩm: 2 tai nghe trái phải, hộp sạc, dây sạc, núm tai, sách hướng dẫn\r\n\r\n\r\n\r\n📌 ƯU ĐIỂM NỔI BẬT:\r\n\r\n\r\n\r\n-	Hỗ trợ điều khiển bằng cảm ứng cực kì tiện dụng. Nút cảm ứng đơn điểm của phiên bản trước đã bị loại bỏ và \r\n\r\n        thay thế bằng công nghệ cảm ứng đa điểm, êm hơn và mượt hơn\r\n\r\n\r\n\r\n-	Công nghệ âm thanh mới  vovc 8D cho chất lượng âm thanh cực đỉnh với bass êm hơn, âm thanh to hơn và chip AIC chống gây chói tai khi nghe ở mức âm lượng cao\r\n\r\n\r\n\r\n-	Công nghệ IPX7 chống nước giúp chống mồ hôi và đi mưa.\r\n\r\n\r\n\r\n-	Dock sạc dùng pin li-on thiết kế gọn hơn, pin dùng được lâu hơn so với bản F9 thông thường\r\n\r\n\r\n\r\n-	Dock sạc siêu trâu, dùng được nhiều lần, ngoài chức năng sạc cho tai nghe, có thể dùng sạc cho điện thoại \r\n\r\n\r\n\r\n-	Thời gian sử dụng lâu, tiện lợi khi di chuyển, vận động\r\n\r\n\r\n\r\n📌 TÍNH NĂNG SẢN PHẨM: \r\n\r\n\r\n\r\n1. Nghe nhạc và tiếp nhận/kết thúc cuộc gọi thoại\r\n\r\n\r\n\r\n2. Chuyển đổi bài hát trước/sau, tăng/giảm âm lượng., Âm thanh nhắc nhở kết nối, báo pin yếu bằng giọng nói, có Siri, hỗ trợ Google điều khiển bằng giọng nói. \r\n\r\n\r\n\r\n3. Tự động kết nối khi mở Bluetooth trên thiết bị điện thoại, Ngắt kết nối khi sạc. \r\n\r\n\r\n\r\n4. Tương thích thông minh: hỗ trợ tất cả điện thoại có kết nối Bluetooth, máy tính bảng, laptop, máy nghe nhạc, v.v…\r\n\r\n\r\n\r\n📌 HƯỚNG DẪN KẾT NỐI\r\n\r\n\r\n\r\nBước 1: Lấy 2 tai nghe ra khỏi dock sạc, đợi đến khi cả 2 tai nhấp nháy xanh đỏ báo hiệu thiết bị sẵn sàng kết nối\r\n\r\n\r\n\r\nBước 2: Bật Bluetooth trên điện thoại, tìm tai nghe để kết nối (nếu kết nối tai trái (L) trước thì 2 tai sẽ tự động kết nối với nhau).\r\n\r\n\r\n\r\nBước 3: Mở danh sách nhạc yêu thích và thưởng thức\r\n\r\n\r\n\r\n 📌ĐIÊÙ KIỆN BẢO HÀNH:\r\n\r\n\r\n\r\n- Shop bảo hành khi sản phẩm lỗi còn mới nguyên vẹn (tức là lỗi do nhà sản xuất), và còn hộp ( vì trên hộp có mã xưởng bảo hành). Cũ bẩn mất hộp do lỗi người dùng xưởng không nhận bảo hành.\r\n\r\n\r\n\r\n- Trong trường hợp sản phẩm lỗi, quý khách mang tới shop kiểm tra và bảo hành. Nếu khách ở xa tự gửi về cho shop kiểm tra và đổi bảo hành.', 1),
(30, 2, 'Đồng hồ đôi Điện Tử Thể Thao Thông Minh Bằng Silicon Có Đèn LED Thời Trang Cho Nam Và Nữ', 38900, 31, 2, 'Thương hiệu: WoMaGe\r\nChất liệu vỏ: Hợp kim\r\nChiều dài vòng tay: 24cm\r\nĐộ sâu kháng nước: Không chống nước\r\nPhong cách: Hiện đại và giản dị\r\nKiểu máy: Kỹ thuật số\r\nLoại móc khóa: Xô da\r\nHình dạng hộp: Hình chữ nhật\r\nChiều rộng vòng đeo tay: 21mm\r\nĐộ dày hộp: 7mm\r\nLoại vật liệu cửa sổ hiển thị: Nhựa\r\nTính năng: Màn hình LED\r\nCác tính năng: Lịch đầy đủ\r\nSố mô hình: Wrist Watch\r\nChất liệu của Hộp và Bao bì: Không có bao bì\r\nLoại sản phẩm: Đồng hồ đeo tay kỹ thuật số\r\nĐường kính quay số: 38mm\r\nLoại vật liệu vòng đeo tay: Cao su\r\nDành cho: Nam, nữ, trẻ em gái, học sinh, trai, gái\r\nPhong cách: Giản dị, thể thao, phong cách\r\nXem HOT 1: zegarki meskie ceasuri barbati panske hodinky Klockor Watches Hombre\r\nĐồng hồ HOT 2: Đồng hồ kỹ thuật số sang trọng Silicone thương hiệu nổi tiếng\r\nĐồng hồ HOT 3: Đồng hồ đeo chéo nổi tiếng Ceasuri\r\nĐồng hồ HOT 4: Đồng hồ thường nam Quartz bằng gốm\r\nĐồng hồ HOT 5: Đồng hồ sang trọng nam thương hiệu chất lượng\r\nXem HOT 6: Đồng hồ nam Reloj Hombre saat erkekler\r\nĐồng hồ HOT 7: Đồng hồ thể thao quân đội nam Giờ LED giờ\r\nThể loại: Đồng hồ nam\r\n****************************************************', 1),
(31, 2, 'Tai Nghe Bluetooth Pro 6 Bluetooth 5.1 có chất lượng âm thanh tốt và tích hợp micro', 72900, 31, 2, 'Lưu ý: không có hộp bán lẻ.\r\n\r\n1.Logo JBL hoặc văn bản trong hộp sạc\r\n\r\n2. Cửa sổ bật lên cho IOS\r\n\r\nHỗ trợ vị trí ba, tên và GPS.\r\n\r\n4.Tai nghe Binural, trái và phải có thể được sử dụng riêng biệt.\r\n\r\nNăm. tự động mở gói / kết nối tự động.\r\n\r\n6no LED ánh sáng sử dụng.\r\n\r\n7 nút điều khiển cảm ứng đa chức năng.\r\n\r\n8 khử trùng hyfi và âm thanh thấp.\r\n\r\n9. Nâng cấp chip Bluetooth 5.0, hiệu suất được cải thiện, mười mét không được kết nối và liên tục.\r\n\r\nKiểm tra đường đua trước / theo dõi tiếp theo.\r\n\r\n11. Hỗ trợ kích hoạt Siri.\r\n\r\n12. Hoàn toàn tương thích với hệ thống IOS và Android.\r\n\r\nChức năng bật lên mới nhất sẽ chỉ hoạt động khi bạn kết nối với IOS 13.2 trở lên, hãy đảm bảo rằng bạn đã cập nhật hệ thống IOS của mình.', 1),
(32, 2, 'Áo croptop cộc ngắn tay cổ chữ v ôm dáng in họa tiết ngọt ngào thời trang cho nữ thun', 89000, 31, 1, 'Thời gian giao hàng dự kiến cho sản phẩm này là từ 7-9 ngày\r\n\r\n \r\n\r\n Mô tả sản phẩm\r\n\r\n \r\n\r\n Phong cách: quý cô gợi cảm / ấm áp\r\n\r\n Chất liệu: Polyester / Polyester\r\n\r\n Nội dung thành phần: 91% (bao gồm) - 95% (bao gồm)\r\n\r\n \r\n\r\n -❤️❤️-Kích Thước\r\n\r\n Chiều dài S 39cm / vai 30,5cm / ngực 72-89cm / tay áo 15cm\r\n\r\n \r\n\r\n Chiều dài M 40cm / vai 31,5cm / ngực 76-93cm / tay áo 15,5cm\r\n\r\n \r\n\r\n Chiều dài L 41cm / vai 32,5cm / ngực 80-97cm / tay áo 16cm\r\n\r\n\r\n\r\nChiều dài XL 42cm / vai 33,5cm / ngực 84-101cm / tay áo 16,5cm\r\n\r\n \r\n\r\n Lưu ý:\r\n\r\n  🌿🌿1. Do đo lường thủ công, có thể có sai số 2-4 cm. Đây không phải là vấn đề về chất lượng sản phẩm (1 inch = 2,54 cm)\r\n\r\n  🌿🌿2. Do sự khác biệt và hiệu ứng ánh sáng, màu sắc của sản phẩm thực tế sẽ hơi khác với hình ảnh, vui lòng tham khảo sản phẩm thực tế!\r\n\r\n  🌿🌿3. Vui lòng kiểm tra thông tin chính xác trước khi đặt hàng. Vui lòng chọn đúng kích cỡ và màu sắc. Điền vào tên, địa chỉ, người nhận và số điện thoại.\r\n\r\n  🌿🌿4. Sản phẩm của chúng tôi được gửi từ Trung Quốc, chúng tôi sẽ giao hàng sớm nhất có thể.\r\n\r\n  🌿🌿5. Bạn có thể tìm thấy một người bán sản phẩm này với giá thấp hơn so với chúng tôi, nhưng họ không thể đảm bảo chất lượng và dịch vụ như chúng tôi.\r\n\r\n  📢📢📢6. Nếu bạn có nhiều câu hỏi hơn, xin vui lòng liên hệ với dịch vụ chăm sóc khách hàng của chúng tôi.', 1),
(33, 2, 'VÁY 2 DÂY VOAN TƠ ULZZANG (ảnh thật/video)', 160000, 31, 1, '❤️Babydoll voan tơ xước Ulzzang.\r\nChất voan tơ có lót.\r\n.\r\n2 màu Đen / Be nhạt\r\nFreesize <55kg mang thoải mái.\r\nDài 110cm\r\nNgực max 85cm', 1),
(34, 2, 'Áo polo unisex có phối logo NKI trước ngực - Áo cổ bẻ chất cotton cá sấu mềm và mát', 129000, 31, 1, 'Áo polo unisex có phối logo NKI trước ngực - Áo cổ bẻ chất cotton poly cá sấu mềm và mát\r\n\r\n🌈 Bảng kích thước áo Polo lỡ unisex:\r\nSize M : dài 63cm, rộng 47cm , 40-59 kg, cao 1m50 – 1m60\r\nSize L : dài 68cm, rộng 51cm,  60 - 70 kg, cao 1m61 – 1m70\r\nSize XL : dài 72cm, rộng 57cm,  71 - 85 kg, cao 1m71 – 1m80\r\nmọi người ưu tiên chọn size theo chiều cao nhé\r\n\r\nBảng size chỉ mang tính chất tham khảo vì còn tùy thuộc vào cơ địa của mỗi bạn ạ\r\n👉 Bảng size mang tính chất tham khảo bạn có thể lấy size to hơn hoặc nhỏ theo yêu cầu của bạn!\r\n\r\nI.SHOP CAM KẾT\r\n- Chất liệu cotton 100%, chất lượng đi đôi với giá cả, đừng so sánh với áo hàng rẻ ạ\r\n- Sản phẩm áo Polo xịn đét, form rộng giống mô tả 100%\r\n- Hình ảnh sản phẩm là ảnh thật, các hình hoàn toàn do shop tự thiết kế và chụp.\r\n- Kiểm tra  cẩn thận trước khi gói hàng giao cho Quý Khách\r\n- Hàng có sẵn, giao hàng ngay khi nhận được đơn \r\n- Hoàn tiền nếu sản phẩm không giống với mô tả\r\n- Chấp nhận đổi hàng khi size không vừa trong 3 ngày.\r\n\r\nII. HỖ TRỢ ĐỔI TRẢ THEO QUY ĐỊNH CỦA SHOPEE\r\n- Điều kiện áp dụng (trong vòng 3 ngày kể từ khi nhận sản phẩm) \r\n- Hàng hoá bị rách, in lỗi, bung chỉ, và các lỗi do vận chuyển hoặc do nhà sản xuất.\r\n1. Trường hợp được chấp nhận: \r\n- Hàng giao sai size khách đã đặt hàng \r\n- Giao thiếu hàng \r\n2. Trường hợp không đủ điều kiện áp dụng chính sách: \r\n- Quá 2 ngày kể từ khi Quý khách nhận hàng \r\n- Gửi lại hàng không đúng mẫu mã, không phải sản phẩm của Tiny Clothes\r\n- Không thích, không hợp, đặt nhầm mã, nhầm màu,... \r\n\r\nIII. MÔ TẢ SẢN PHẨM\r\n⭐ Tên sản phẩm : Áo Polo unisex\r\n⭐ Chất Liệu: chất Cotton\r\n⭐ Màu Sắc:   Đen + Trắng+ Xanh + Nâu\r\n⭐ Đặc Tính:  Chất vải áo là chất cotton poly cá sấu mặc thoáng mát thấm hút mồ hôi', 1),
(35, 2, 'Quần short Unisex, Quần đùi chất tổ ong mặc thoáng mát, Nam nữ đều mặc được', 99000, 31, 1, 'I. CAM KẾT CỦA SHOP\r\n- Sản phẩm giống hình và mô tả\r\n- Chấp nhận đổi hàng khi size không vừa trong vòng 3 ngày\r\n- Hàng có sẵn, giao hàng ngay khi nhận được đơn của quý khách\r\n- Hoàn tiền nếu sản phẩm không giống với mô tả\r\n- Chấp nhận đổi hàng khi size không vừa\r\n- Giao hàng trên toàn quốc, nhận hàng trả tiền \r\n\r\nII. HỖ TRỢ ĐỔI TRẢ THEO QUY ĐỊNH CỦA SHOPEE\r\n- Điều kiện áp dụng (trong vòng 3 ngày kể từ khi nhận sản phẩm) \r\n- Hàng hoá bị rách, in lỗi, bung chỉ, và các lỗi do vận chuyển hoặc do nhà sản xuất.\r\n1. Trường hợp được chấp nhận: \r\n- Hàng giao sai size khách đã đặt hàng \r\n- Giao thiếu hàng \r\n2. Trường hợp không đủ điều kiện áp dụng chính sách: \r\n- Quá 2 ngày kể từ khi Quý khách nhận hàng \r\n- Gửi lại hàng không đúng mẫu mã, không phải sản phẩm của Tiny Clothes\r\n- Không thích, không hợp, đặt nhầm mã, nhầm màu,... \r\n\r\nIII. MÔ TẢ SẢN PHẨM \r\nTên SP: QUẦN SHORT UNISEX\r\n- Xuất xứ: Việt Nam\r\n- Chất liệu: chất nỉ da cá thấm hút mồ hôi tốt, thoáng mát\r\n- Đường may tỉ mỉ chắc chắc\r\n- Thiết kế: hiện đại, trẻ trung, dễ phối\r\n- Đủ size: M - L - XL (các bạn tham khảo tại bảng size nhé)\r\n\r\n🌿 Bảng Size:\r\nSize M : 40-50 kg, cao 1m50 – 1m57 |  dài 48cm, ống chân rộng 28,5cm\r\nSize L :  51- 70 kg, cao 1m58 – 1m70 |  dài 50,5cm, ống chân rộng 29cm\r\nSize XL : 71 - 85 kg, cao 1m71 – 1m83 | dài 53cm, ống chân rộng 30cm\r\n\r\nIV. HƯỚNG DẪN SỬ DỤNG SẢN PHẨM QUẦN SHORT\r\n- Lộn trái khi giặt\r\n- Lần đầu giặt nên giặt bằng tay trước và phơi kỹ\r\n- Không đổ trực tiếp bột giặt vào quần', 1),
(36, 2, 'Áo Phông Chất Tổ Ong Thoáng Mát - Áo Thun Tay Lỡ Unisex Nam Nữ OverSize Form Rộng', 119000, 31, 1, '* THÔNG TIN SẢN PHẨM:\r\n- 100% chất liệu cotton tổ ong mềm cao cấp, thấm hút mồ hôi gấp 5 lần vải bình thường giúp người mặc cảm thấy thoải mái, dễ chịu.\r\n- Áo thun basic, đơn giản, dễ mặc, dễ phối đồ, vải cực mềm mịn thấm hút mồ hôi cực tốt\r\n- Form rộng mặc cực trẻ trung và phong cách, phù hợp mọi hoàn cảnh kể cả đi chơi và đi làm.\r\n\r\n✅  Áo có 4 màu: Xanh lá, Cam, Đen, Trắng\r\n\r\n✅ Áo phông đủ size từ M đến XL cho khách từ 42-85 kg mặc vừa:\r\nSize M : dài 61cm, rộng 47cm , 40-50 kg, cao 1m50 – 1m57\r\nSize L : dài 65cm, rộng 51cm,  51- 70 kg, cao 1m58 – 1m70\r\nSize XL : dài 70cm, rộng 57cm,  71 - 85 kg, cao 1m71 – 1m83\r\n\r\n* Bảng size chỉ mang tính chất tham khảo, tùy thuộc vào số đo cơ thể và chất liệu vải khác nhau sẽ có sự chênh lệch nhất định. \r\n-------------------------------\r\nSHOP CAM KẾT: \r\n- Chất lượng luôn là hàng đầu để shop có thể phát triển thương hiệu và vươn xa. \r\n- Tư vấn nhiệt tình, chu đáo luôn lắng nghe khách hàng để phục vụ tốt. \r\n- Giao hàng nhanh đúng tiến độ không phải để quý khách chờ đợi lâu để nhận hàng. \r\n- Vì lên ảnh do điều kiện ánh sáng, môi trường,… và nhiều vấn đề khách quan nên màu sắc có thể chênh lệch so với thực tế 1-5% (cực nhỏ). \r\n- Áo mới về bạn nên ủi qua trước khi mặc bạn nhé,\r\n- Khách feedback sẽ được nhận thêm mã giảm giá nên đừng bỏ phí cơ hội này nhé.\r\n-------------------------------\r\n* Đổi trả theo đúng quy định của Shopee \r\n1. Điều kiện áp dụng (trong vòng 07 ngày kể từ khi nhận sản phẩm): \r\n- Hàng hoá vẫn còn mới, chưa qua sử dụng \r\n- Hàng hóa hư hỏng do vận chuyển hoặc do nhà sản xuất. \r\n2. Trường hợp được chấp nhận: \r\n- Hàng không đúng size, kiểu dáng như quý khách đặt hàng \r\n- Không đủ số lượng, không đủ bộ như trong đơn hàng\r\n3. Trường hợp không đủ điều kiện áp dụng chính sách:\r\n - Quá 07 ngày kể từ khi Quý khách nhận hàng \r\n - Gửi lại hàng không đúng mẫu mã, không phải hàng của shop.\r\n - Đặt nhầm sản phẩm, chủng loại, không thích, không hợp,...\r\n Ấn theo dõi để ủng hộ shop và tham khảo các sản phẩm mới của shop, SHOP rất hân hạnh được phục vụ quý khách.', 1);

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
  MODIFY `address_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

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
