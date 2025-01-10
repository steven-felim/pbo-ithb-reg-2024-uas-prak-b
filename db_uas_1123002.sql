-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2025 at 05:21 PM
-- Server version: 11.3.0-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_uas_1123002`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `cust_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`cust_id`, `name`, `address`, `phone`, `password`) VALUES
(1, 'Jaysen', 'tki', '1123006', 'heeeee'),
(2, 'Felim', 'pabaki', '1123002', 'hehehe'),
(3, 'Herry', 'tki', '102938', 'wkwkw'),
(4, 'Jojo', 'patung kuda', '00000', 'fuiih');

-- --------------------------------------------------------

--
-- Table structure for table `delivery_category`
--

CREATE TABLE `delivery_category` (
  `category` varchar(255) DEFAULT NULL,
  `delivery_fee` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `delivery_category`
--

INSERT INTO `delivery_category` (`category`, `delivery_fee`) VALUES
('Building Materials', 15000),
('House Moving', 20000),
('Instant Delivery', 10000);

-- --------------------------------------------------------

--
-- Table structure for table `delivery_details`
--

CREATE TABLE `delivery_details` (
  `id` int(11) NOT NULL,
  `trans_id` int(11) NOT NULL,
  `status` enum('PENDING','IN_PROGRESS','ON_DELIVERY','ARRIVED') NOT NULL,
  `current_position` varchar(255) NOT NULL,
  `evidence` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `updated_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `delivery_details`
--

INSERT INTO `delivery_details` (`id`, `trans_id`, `status`, `current_position`, `evidence`, `date`, `updated_by`) VALUES
(1, 2, 'PENDING', 'di sini', 'C:\\Users\\Steven Felim\\OneDrive\\Documents\\DSC_4148.jpg', '2025-01-10', 'me'),
(2, 5, 'PENDING', 'rumah felim', 'C:\\Users\\Steven Felim\\OneDrive\\Documents\\1ttd.png', '2025-01-10', 'felim');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `trans_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `delivery_type` varchar(255) NOT NULL,
  `expected_weight` int(11) NOT NULL,
  `total_cost` int(11) NOT NULL,
  `createdAt` date NOT NULL,
  `receipt_name` varchar(255) NOT NULL,
  `receipt_address` varchar(255) NOT NULL,
  `receipt_phone` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`trans_id`, `customer_id`, `delivery_type`, `expected_weight`, `total_cost`, `createdAt`, `receipt_name`, `receipt_address`, `receipt_phone`) VALUES
(1, 1, 'Instant Delivery', 2, 20000, '2025-01-10', 'Jay', 'tki 1', '01112'),
(2, 2, 'House Moving', 1, 20000, '2025-01-10', 'Felim', 'ithb', '2'),
(3, 2, 'Building Materials', 2, 30000, '2025-01-10', 'fel', 'rumah', '2222'),
(4, 3, 'House Moving', 10, 200000, '2025-01-10', 'Herry', 'rumah', '999999'),
(5, 1, 'Instant Delivery', 3, 30000, '2025-01-10', 'Jaysen', 'rumah jojo', '00000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`cust_id`);

--
-- Indexes for table `delivery_details`
--
ALTER TABLE `delivery_details`
  ADD PRIMARY KEY (`id`,`trans_id`),
  ADD KEY `trans_id` (`trans_id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`trans_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `cust_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `delivery_details`
--
ALTER TABLE `delivery_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `trans_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `delivery_details`
--
ALTER TABLE `delivery_details`
  ADD CONSTRAINT `delivery_details_ibfk_1` FOREIGN KEY (`trans_id`) REFERENCES `transactions` (`trans_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`cust_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
