-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 16, 2024 at 10:45 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `Patient_ID` int(11) NOT NULL,
  `Doctor_ID` int(11) NOT NULL,
  `Appointment_Date` date NOT NULL,
  `Appointment_Time` time NOT NULL,
  `Appointment_Duration` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`Patient_ID`, `Doctor_ID`, `Appointment_Date`, `Appointment_Time`, `Appointment_Duration`) VALUES
(1, 1, '2023-01-15', '08:00:00', 30),
(1, 2, '2023-01-16', '09:30:00', 45),
(2, 2, '2023-01-17', '11:00:00', 60),
(2, 3, '2023-01-18', '08:30:00', 30),
(3, 1, '2023-01-20', '11:30:00', 60),
(3, 3, '2023-01-19', '10:00:00', 45),
(4, 1, '2023-01-21', '09:00:00', 30),
(4, 2, '2023-01-22', '10:30:00', 45),
(5, 2, '2023-01-23', '12:00:00', 60),
(5, 3, '2023-01-24', '09:30:00', 30),
(6, 1, '2023-01-26', '12:30:00', 60),
(6, 3, '2023-01-25', '11:00:00', 45),
(7, 1, '2023-01-27', '10:00:00', 30),
(7, 2, '2023-01-28', '11:30:00', 45),
(8, 2, '2023-01-29', '01:00:00', 60),
(8, 3, '2023-01-30', '10:30:00', 30),
(9, 1, '2023-02-02', '12:30:00', 60),
(9, 3, '2023-02-01', '11:00:00', 45),
(10, 1, '2023-02-03', '10:00:00', 30),
(10, 2, '2023-02-04', '11:30:00', 45);

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `Department_ID` int(11) NOT NULL,
  `D_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`Department_ID`, `D_name`) VALUES
(1, 'Department 1'),
(2, 'Department 2'),
(3, 'Department 3');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `Doctor_ID` int(11) NOT NULL,
  `Doctor_Specialization` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`Doctor_ID`, `Doctor_Specialization`) VALUES
(1, 'General Medicine'),
(2, 'Pediatrics'),
(3, 'Cardiology');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `patient_ID` int(11) NOT NULL,
  `patient_Fname` varchar(255) DEFAULT NULL,
  `patient_Lname` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL CHECK (`gender` in ('Male','Female')),
  `disease` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`patient_ID`, `patient_Fname`, `patient_Lname`, `date_of_birth`, `gender`, `disease`, `address`) VALUES
(1, 'Michael', 'Johnson', '1990-01-15', 'Male', 'Fever', '321 Maple Ave'),
(2, 'Sarah', 'Brown', '1985-05-22', 'Female', 'Headache', '456 Oak St'),
(3, 'Robert', 'Smith', '1982-11-10', 'Male', 'Back Pain', '789 Pine Lane'),
(4, 'Amanda', 'Davis', '1992-03-08', 'Female', 'Flu', '111 Elm Street'),
(5, 'Christopher', 'Miller', '1988-07-17', 'Male', 'Allergies', '222 Birch Blvd'),
(6, 'Rebecca', 'Wilson', '1980-09-30', 'Female', 'Cough', '333 Cedar Dr'),
(7, 'Daniel', 'Taylor', '1975-12-05', 'Male', 'Sprained Ankle', '444 Pine Ct'),
(8, 'Benjamin', 'Johnson', '1978-08-20', 'Male', 'Migraine', '555 Oak Ave'),
(9, 'Jonathan', 'Thomas', '1983-06-25', 'Male', 'Stomachache', '666 Elm Street'),
(10, 'Emily', 'Anderson', '1995-04-18', 'Female', 'Sinusitis', '777 Birch Ln');

-- --------------------------------------------------------

--
-- Table structure for table `patient_phone`
--

CREATE TABLE `patient_phone` (
  `patient_ID` int(11) NOT NULL,
  `phone_number` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patient_phone`
--

INSERT INTO `patient_phone` (`patient_ID`, `phone_number`) VALUES
(1, '01112345678'),
(1, '01298765432'),
(2, '01587654321'),
(3, '01023456789'),
(3, '01187654321'),
(4, '01234567890'),
(5, '01598765432'),
(6, '01123456789'),
(7, '01087654321'),
(8, '01298765432'),
(9, '01523456789'),
(10, '01187654321');

-- --------------------------------------------------------

--
-- Table structure for table `patient_treatment`
--

CREATE TABLE `patient_treatment` (
  `patient_ID` int(11) NOT NULL,
  `treatment_code` int(11) NOT NULL,
  `treatment_start_date` date DEFAULT NULL,
  `treatment_end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patient_treatment`
--

INSERT INTO `patient_treatment` (`patient_ID`, `treatment_code`, `treatment_start_date`, `treatment_end_date`) VALUES
(1, 1, '2023-01-16', '2023-01-30'),
(2, 2, '2023-01-18', '2023-02-02'),
(3, 3, '2023-01-20', '2023-02-10'),
(4, 4, '2023-01-22', '2023-02-05'),
(5, 5, '2023-01-24', '2023-02-15'),
(6, 1, '2023-01-26', '2023-02-20'),
(7, 2, '2023-01-28', '2023-02-25'),
(8, 3, '2023-01-30', '2023-02-28'),
(9, 4, '2023-02-01', '2023-03-05'),
(10, 5, '2023-02-03', '2023-03-10');

-- --------------------------------------------------------

--
-- Table structure for table `relative`
--

CREATE TABLE `relative` (
  `relative_ID` int(11) NOT NULL,
  `patient_ID` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `companion_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `relative`
--

INSERT INTO `relative` (`relative_ID`, `patient_ID`, `address`, `companion_name`) VALUES
(1, 1, '456 Side St', 'Mary Doe'),
(2, 2, '789 Back St', 'Michael Smith'),
(3, 3, '222 Alley St', 'Sarah Johnson'),
(4, 4, '333 Loop St', 'David Williams'),
(5, 5, '444 Crescent St', 'Emma Brown'),
(6, 6, '555 Cross St', 'Alex Davis'),
(7, 7, '666 Parallel St', 'Olivia Miller'),
(8, 8, '777 Diagonal St', 'Daniel Johnson'),
(9, 9, '888 Zigzag St', 'Sophia Taylor'),
(10, 10, '999 Intersection St', 'Matthew Wilson');

-- --------------------------------------------------------

--
-- Table structure for table `relative_phone`
--

CREATE TABLE `relative_phone` (
  `relative_ID` int(11) NOT NULL,
  `companion_phone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `relative_phone`
--

INSERT INTO `relative_phone` (`relative_ID`, `companion_phone`) VALUES
(1, '01011111111'),
(2, '01122222222'),
(3, '01233333333'),
(4, '01544444444'),
(5, '01055555555'),
(6, '01166666666'),
(7, '01277777777'),
(8, '01588888888'),
(9, '01099999999'),
(10, '01110101010');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `Admission_Date` date DEFAULT NULL,
  `Patient_ID` int(11) DEFAULT NULL,
  `Room_Num` int(11) NOT NULL,
  `Department_ID` int(11) DEFAULT NULL,
  `Room_Type` varchar(50) DEFAULT 'Regular'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`Admission_Date`, `Patient_ID`, `Room_Num`, `Department_ID`, `Room_Type`) VALUES
('2023-01-15', 1, 101, 1, 'room'),
('2023-01-18', 2, 102, 2, 'operation theatre'),
('2023-01-20', 3, 103, 3, 'ICU'),
('2023-01-22', 4, 104, 1, 'room'),
('2023-01-24', 5, 105, 2, 'operation theatre'),
('2023-01-26', 6, 106, 3, 'ICU'),
('2023-01-28', 7, 107, 1, 'room'),
('2023-01-30', 8, 108, 2, 'room');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `Staff_ID` int(11) NOT NULL,
  `Staff_address` varchar(255) DEFAULT NULL,
  `Staff_Phone` varchar(255) NOT NULL,
  `Salary` decimal(10,2) DEFAULT 0.00,
  `Department_ID` int(11) DEFAULT NULL,
  `Staff_Fname` varchar(255) NOT NULL,
  `Staff_Lname` varchar(255) NOT NULL,
  `Staff_Type` varchar(50) DEFAULT NULL CHECK (`Staff_Type` in ('Ward Boy','Doctor','Nurse'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`Staff_ID`, `Staff_address`, `Staff_Phone`, `Salary`, `Department_ID`, `Staff_Fname`, `Staff_Lname`, `Staff_Type`) VALUES
(1, '123 Main St', '555-1234', 80000.00, 1, 'John', 'Doe', 'Doctor'),
(2, '456 Oak St', '555-5678', 75000.00, 2, 'Jane', 'Smith', 'Doctor'),
(3, '789 Pine St', '555-9876', 70000.00, 3, 'Bob', 'Johnson', 'Doctor'),
(4, '111 Elm St', '555-1111', 60000.00, 1, 'Alice', 'Williams', 'Nurse'),
(5, '222 Birch St', '555-2222', 55000.00, 2, 'Charlie', 'Brown', 'Nurse'),
(6, '333 Cedar St', '555-3333', 50000.00, 2, 'Eve', 'Davis', 'Nurse'),
(7, '444 Pine St', '555-4444', 45000.00, 1, 'Tom', 'Miller', 'Ward Boy'),
(8, '555 Oak St', '555-5555', 40000.00, 3, 'Ben', 'Johnson', 'Ward Boy'),
(9, '666 Elm St', '555-6666', 35000.00, 3, 'Jack', 'Taylor', 'Ward Boy'),
(10, '777 Birch St', '555-7777', 30000.00, 2, 'Emily', 'Wilson', 'Nurse');

-- --------------------------------------------------------

--
-- Table structure for table `staff_room`
--

CREATE TABLE `staff_room` (
  `Staff_ID` int(11) NOT NULL,
  `Room_Num` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `treatment`
--

CREATE TABLE `treatment` (
  `treatment_code` int(11) NOT NULL,
  `treatment_price` decimal(10,2) DEFAULT 0.00,
  `treatment_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `treatment`
--

INSERT INTO `treatment` (`treatment_code`, `treatment_price`, `treatment_name`) VALUES
(1, 100.00, 'Physical Therapy'),
(2, 150.00, 'MRI Scan'),
(3, 200.00, 'Cardiac Surgery'),
(4, 120.00, 'Dental Cleaning'),
(5, 180.00, 'Allergy Testing');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`Patient_ID`,`Doctor_ID`,`Appointment_Date`,`Appointment_Time`),
  ADD KEY `Doctor_Staff_ID` (`Doctor_ID`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`Department_ID`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`Doctor_ID`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`patient_ID`),
  ADD UNIQUE KEY `patient_ID` (`patient_ID`);

--
-- Indexes for table `patient_phone`
--
ALTER TABLE `patient_phone`
  ADD PRIMARY KEY (`patient_ID`,`phone_number`);

--
-- Indexes for table `patient_treatment`
--
ALTER TABLE `patient_treatment`
  ADD PRIMARY KEY (`patient_ID`,`treatment_code`),
  ADD KEY `treatment_code` (`treatment_code`);

--
-- Indexes for table `relative`
--
ALTER TABLE `relative`
  ADD PRIMARY KEY (`relative_ID`),
  ADD UNIQUE KEY `patient_ID` (`patient_ID`);

--
-- Indexes for table `relative_phone`
--
ALTER TABLE `relative_phone`
  ADD PRIMARY KEY (`relative_ID`,`companion_phone`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`Room_Num`),
  ADD KEY `Patient_ID` (`Patient_ID`),
  ADD KEY `Department_ID` (`Department_ID`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`Staff_ID`),
  ADD KEY `Department_ID` (`Department_ID`),
  ADD KEY `i1` (`Salary`),
  ADD KEY `i2` (`Staff_Type`);

--
-- Indexes for table `staff_room`
--
ALTER TABLE `staff_room`
  ADD PRIMARY KEY (`Staff_ID`,`Room_Num`),
  ADD KEY `Room_Num` (`Room_Num`);

--
-- Indexes for table `treatment`
--
ALTER TABLE `treatment`
  ADD PRIMARY KEY (`treatment_code`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`Patient_ID`) REFERENCES `patient` (`patient_ID`),
  ADD CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`Doctor_ID`) REFERENCES `doctor` (`Doctor_ID`);

--
-- Constraints for table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`Doctor_ID`) REFERENCES `staff` (`Staff_ID`);

--
-- Constraints for table `patient_phone`
--
ALTER TABLE `patient_phone`
  ADD CONSTRAINT `patient_phone_ibfk_1` FOREIGN KEY (`patient_ID`) REFERENCES `patient` (`patient_ID`);

--
-- Constraints for table `patient_treatment`
--
ALTER TABLE `patient_treatment`
  ADD CONSTRAINT `patient_treatment_ibfk_1` FOREIGN KEY (`patient_ID`) REFERENCES `patient` (`patient_ID`),
  ADD CONSTRAINT `patient_treatment_ibfk_2` FOREIGN KEY (`treatment_code`) REFERENCES `treatment` (`treatment_code`);

--
-- Constraints for table `relative`
--
ALTER TABLE `relative`
  ADD CONSTRAINT `relative_ibfk_1` FOREIGN KEY (`patient_ID`) REFERENCES `patient` (`patient_ID`);

--
-- Constraints for table `relative_phone`
--
ALTER TABLE `relative_phone`
  ADD CONSTRAINT `relative_phone_ibfk_1` FOREIGN KEY (`relative_ID`) REFERENCES `relative` (`relative_ID`);

--
-- Constraints for table `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `room_ibfk_1` FOREIGN KEY (`Patient_ID`) REFERENCES `patient` (`patient_ID`),
  ADD CONSTRAINT `room_ibfk_3` FOREIGN KEY (`Department_ID`) REFERENCES `department` (`Department_ID`);

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`Department_ID`) REFERENCES `department` (`Department_ID`);

--
-- Constraints for table `staff_room`
--
ALTER TABLE `staff_room`
  ADD CONSTRAINT `staff_room_ibfk_1` FOREIGN KEY (`Staff_ID`) REFERENCES `staff` (`Staff_ID`),
  ADD CONSTRAINT `staff_room_ibfk_2` FOREIGN KEY (`Room_Num`) REFERENCES `room` (`Room_Num`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
