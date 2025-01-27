-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 27, 2025 at 10:32 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `logship`
--

-- --------------------------------------------------------

--
-- Table structure for table `handler_details`
--

CREATE TABLE `handler_details` (
  `handler_id` int(255) NOT NULL COMMENT 'Handler ID - Autogenerated',
  `handler_name` varchar(50) NOT NULL COMMENT 'Name of handler',
  `warehouse_id` int(255) NOT NULL COMMENT 'Warehouse to which handler belongs to',
  `joined_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT 'Date of joining'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `manager_details`
--

CREATE TABLE `manager_details` (
  `manager_id` int(255) NOT NULL COMMENT 'Manager Id - Auto generated',
  `manager_name` varchar(50) NOT NULL COMMENT 'Manager Name',
  `address` text NOT NULL COMMENT 'Address',
  `warehouse_id` int(255) NOT NULL COMMENT 'Warehouse to which manager belong'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `order_id` int(255) NOT NULL COMMENT 'Order id - autogenerated',
  `order_name` text NOT NULL COMMENT 'Order name',
  `order_note` text DEFAULT NULL COMMENT 'Additional note on order',
  `sender_id` int(255) NOT NULL COMMENT 'User id who placed the order',
  `fragile` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'Flag stating whether order is fragile',
  `admission_warehouse_id` int(255) NOT NULL COMMENT 'Warehouse where order is admitted',
  `destination_warehouse_id` int(255) NOT NULL COMMENT 'Warehouse to which order is to be delivered',
  `created_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT 'Order Created date',
  `last_updated_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT 'Last order updated date',
  `data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'Any additional json data' CHECK (json_valid(`data`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_quality_check`
--

CREATE TABLE `order_quality_check` (
  `order_check_id` int(255) NOT NULL COMMENT 'Primary key entry id',
  `order_id` int(255) NOT NULL COMMENT 'Order Id',
  `quality_check_id` int(11) NOT NULL COMMENT 'Quality check id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_status`
--

CREATE TABLE `order_status` (
  `status_id` int(255) NOT NULL COMMENT 'Status Id- autogenerated',
  `order_id` int(255) NOT NULL COMMENT 'Order Id',
  `handler_id` int(255) NOT NULL COMMENT 'Id of the handler',
  `status` enum('OC','OP','IT','OD','OM') NOT NULL COMMENT 'Order Status\r\nOC - Order created\r\nOP - Order Processing\r\nIT - In transit\r\nOD - Order reached in destination\r\nOM - Order missing',
  `created_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT 'Datetime when order status is updated'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `quality_check_list`
--

CREATE TABLE `quality_check_list` (
  `quality_check_id` int(255) NOT NULL COMMENT 'Quality Check Entry Id',
  `quality_check_name` varchar(100) NOT NULL COMMENT 'Type of Quality Check',
  `description` text DEFAULT NULL COMMENT 'Description for quality check'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sender_details`
--

CREATE TABLE `sender_details` (
  `sender_id` int(255) NOT NULL COMMENT 'Sender Id',
  `sender_phone` varchar(12) NOT NULL COMMENT 'Sender phone',
  `sender_mail` varchar(100) DEFAULT NULL COMMENT 'Sender email - optional',
  `created_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT 'Sender profile created date'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `shipment_details`
--

CREATE TABLE `shipment_details` (
  `shipment_id` int(255) NOT NULL COMMENT 'Shipment id foreign key',
  `order_id` int(255) NOT NULL COMMENT 'Order Id foreign key',
  `added_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT 'Shipment added date',
  `end_date` datetime DEFAULT NULL COMMENT 'Shipment arrival date'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `shipment_master`
--

CREATE TABLE `shipment_master` (
  `shipment_id` int(255) NOT NULL COMMENT 'Shipment details',
  `from_warehouse_id` int(255) NOT NULL COMMENT 'From warehouse id',
  `to_warehouse_id` int(255) NOT NULL COMMENT 'To warehouse id',
  `shipment_handler_id` int(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT 'Shipment created date',
  `start_date` datetime DEFAULT NULL COMMENT 'Shipment start date',
  `end_date` datetime DEFAULT NULL COMMENT 'Shipment end date'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `warehouse_details`
--

CREATE TABLE `warehouse_details` (
  `warehouse_id` int(11) NOT NULL COMMENT 'Warehouse Id',
  `warehouse_name` varchar(100) NOT NULL COMMENT 'Warehouse Name',
  `address` text NOT NULL COMMENT 'Warehouse address'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `warehouse_quality_check`
--

CREATE TABLE `warehouse_quality_check` (
  `entry_id` int(255) NOT NULL COMMENT 'Entry Id - auto generated',
  `order_id` int(255) NOT NULL COMMENT 'Order id',
  `warehouse_id` int(255) NOT NULL COMMENT 'Warehouse Id',
  `handler_id` int(255) NOT NULL COMMENT 'Handler id',
  `quality_check_id` int(255) NOT NULL COMMENT 'quality Check Id',
  `status` enum('PASS','FAIL','PENDING') NOT NULL DEFAULT 'PENDING' COMMENT 'Status of QC',
  `created_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT 'created date of entry',
  `last_updated_date` datetime DEFAULT NULL COMMENT 'last updated date of entry'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `handler_details`
--
ALTER TABLE `handler_details`
  ADD PRIMARY KEY (`handler_id`),
  ADD KEY `warehouse_id` (`warehouse_id`);

--
-- Indexes for table `manager_details`
--
ALTER TABLE `manager_details`
  ADD PRIMARY KEY (`manager_id`),
  ADD KEY `warehouse_id` (`warehouse_id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `sender_id` (`sender_id`),
  ADD KEY `destination_warehouse` (`destination_warehouse_id`),
  ADD KEY `admission_warehouse` (`admission_warehouse_id`);

--
-- Indexes for table `order_quality_check`
--
ALTER TABLE `order_quality_check`
  ADD PRIMARY KEY (`order_check_id`),
  ADD KEY `order_id` (`order_id`,`quality_check_id`),
  ADD KEY `quality_check_id` (`quality_check_id`);

--
-- Indexes for table `order_status`
--
ALTER TABLE `order_status`
  ADD PRIMARY KEY (`status_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `handler_id` (`handler_id`);

--
-- Indexes for table `quality_check_list`
--
ALTER TABLE `quality_check_list`
  ADD PRIMARY KEY (`quality_check_id`);

--
-- Indexes for table `sender_details`
--
ALTER TABLE `sender_details`
  ADD PRIMARY KEY (`sender_id`),
  ADD UNIQUE KEY `sender_phone` (`sender_phone`,`sender_mail`);

--
-- Indexes for table `shipment_details`
--
ALTER TABLE `shipment_details`
  ADD PRIMARY KEY (`shipment_id`,`order_id`);

--
-- Indexes for table `shipment_master`
--
ALTER TABLE `shipment_master`
  ADD PRIMARY KEY (`shipment_id`),
  ADD KEY `from_warehouse_id` (`from_warehouse_id`),
  ADD KEY `to_warehouse_id` (`to_warehouse_id`),
  ADD KEY `shipment_handler_id` (`shipment_handler_id`);

--
-- Indexes for table `warehouse_details`
--
ALTER TABLE `warehouse_details`
  ADD PRIMARY KEY (`warehouse_id`);

--
-- Indexes for table `warehouse_quality_check`
--
ALTER TABLE `warehouse_quality_check`
  ADD PRIMARY KEY (`entry_id`),
  ADD KEY `order_id` (`order_id`,`warehouse_id`,`quality_check_id`),
  ADD KEY `quality_check_id` (`quality_check_id`),
  ADD KEY `warehouse_id` (`warehouse_id`),
  ADD KEY `handler_id` (`handler_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `handler_details`
--
ALTER TABLE `handler_details`
  MODIFY `handler_id` int(255) NOT NULL AUTO_INCREMENT COMMENT 'Handler ID - Autogenerated';

--
-- AUTO_INCREMENT for table `manager_details`
--
ALTER TABLE `manager_details`
  MODIFY `manager_id` int(255) NOT NULL AUTO_INCREMENT COMMENT 'Manager Id - Auto generated';

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `order_id` int(255) NOT NULL AUTO_INCREMENT COMMENT 'Order id - autogenerated';

--
-- AUTO_INCREMENT for table `order_quality_check`
--
ALTER TABLE `order_quality_check`
  MODIFY `order_check_id` int(255) NOT NULL AUTO_INCREMENT COMMENT 'Primary key entry id';

--
-- AUTO_INCREMENT for table `order_status`
--
ALTER TABLE `order_status`
  MODIFY `status_id` int(255) NOT NULL AUTO_INCREMENT COMMENT 'Status Id- autogenerated';

--
-- AUTO_INCREMENT for table `quality_check_list`
--
ALTER TABLE `quality_check_list`
  MODIFY `quality_check_id` int(255) NOT NULL AUTO_INCREMENT COMMENT 'Quality Check Entry Id';

--
-- AUTO_INCREMENT for table `sender_details`
--
ALTER TABLE `sender_details`
  MODIFY `sender_id` int(255) NOT NULL AUTO_INCREMENT COMMENT 'Sender Id';

--
-- AUTO_INCREMENT for table `shipment_master`
--
ALTER TABLE `shipment_master`
  MODIFY `shipment_id` int(255) NOT NULL AUTO_INCREMENT COMMENT 'Shipment details';

--
-- AUTO_INCREMENT for table `warehouse_details`
--
ALTER TABLE `warehouse_details`
  MODIFY `warehouse_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Warehouse Id';

--
-- AUTO_INCREMENT for table `warehouse_quality_check`
--
ALTER TABLE `warehouse_quality_check`
  MODIFY `entry_id` int(255) NOT NULL AUTO_INCREMENT COMMENT 'Entry Id - auto generated';

--
-- Constraints for dumped tables
--

--
-- Constraints for table `handler_details`
--
ALTER TABLE `handler_details`
  ADD CONSTRAINT `handler_details_ibfk_1` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse_details` (`warehouse_id`) ON UPDATE CASCADE;

--
-- Constraints for table `manager_details`
--
ALTER TABLE `manager_details`
  ADD CONSTRAINT `manager_details_ibfk_1` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse_details` (`warehouse_id`) ON UPDATE CASCADE;

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `sender_details` (`sender_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`admission_warehouse_id`) REFERENCES `warehouse_details` (`warehouse_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `order_details_ibfk_3` FOREIGN KEY (`destination_warehouse_id`) REFERENCES `warehouse_details` (`warehouse_id`) ON UPDATE CASCADE;

--
-- Constraints for table `order_quality_check`
--
ALTER TABLE `order_quality_check`
  ADD CONSTRAINT `order_quality_check_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_details` (`order_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `order_quality_check_ibfk_2` FOREIGN KEY (`quality_check_id`) REFERENCES `quality_check_list` (`quality_check_id`) ON UPDATE CASCADE;

--
-- Constraints for table `order_status`
--
ALTER TABLE `order_status`
  ADD CONSTRAINT `order_status_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_details` (`order_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `order_status_ibfk_2` FOREIGN KEY (`handler_id`) REFERENCES `handler_details` (`handler_id`) ON UPDATE CASCADE;

--
-- Constraints for table `shipment_master`
--
ALTER TABLE `shipment_master`
  ADD CONSTRAINT `shipment_master_ibfk_1` FOREIGN KEY (`from_warehouse_id`) REFERENCES `warehouse_details` (`warehouse_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `shipment_master_ibfk_2` FOREIGN KEY (`to_warehouse_id`) REFERENCES `warehouse_details` (`warehouse_id`) ON UPDATE CASCADE;

--
-- Constraints for table `warehouse_quality_check`
--
ALTER TABLE `warehouse_quality_check`
  ADD CONSTRAINT `warehouse_quality_check_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_details` (`order_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `warehouse_quality_check_ibfk_2` FOREIGN KEY (`quality_check_id`) REFERENCES `quality_check_list` (`quality_check_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `warehouse_quality_check_ibfk_3` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse_details` (`warehouse_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `warehouse_quality_check_ibfk_4` FOREIGN KEY (`handler_id`) REFERENCES `handler_details` (`handler_id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
