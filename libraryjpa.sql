-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 08/10/2024 às 13:31
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `libraryjpa`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `author`
--

CREATE TABLE `author` (
  `id` int(11) NOT NULL,
  `biography` varchar(255) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `author`
--

INSERT INTO `author` (`id`, `biography`, `birthDate`, `nationality`, `name`) VALUES
(2, 'A regional Writer', '1945-02-05', 'Brazilian', 'Alceu V.'),
(3, 'Protestant Writer', '1968-01-02', 'Brazilian', 'Augusto Nicodemus');

-- --------------------------------------------------------

--
-- Estrutura para tabela `book`
--

CREATE TABLE `book` (
  `isbn` bigint(20) NOT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `publishDate` date DEFAULT NULL,
  `qty` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `book`
--

INSERT INTO `book` (`isbn`, `genre`, `publishDate`, `qty`, `title`, `author_id`) VALUES
(1234567891234, 'Fantasy', '2000-01-05', 10, 'O Alto da Compadecida', 2),
(2345678912345, 'Religious', '2004-11-12', 6, 'A Holy grace', 3);

-- --------------------------------------------------------

--
-- Estrutura para tabela `loan`
--

CREATE TABLE `loan` (
  `id` int(11) NOT NULL,
  `bookState` varchar(255) DEFAULT NULL,
  `dateLoanFinishes` date DEFAULT NULL,
  `dateLoanStarts` date DEFAULT NULL,
  `fineValue` decimal(19,2) DEFAULT NULL,
  `book_isbn` bigint(20) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `loan`
--

INSERT INTO `loan` (`id`, `bookState`, `dateLoanFinishes`, `dateLoanStarts`, `fineValue`, `book_isbn`, `member_id`) VALUES
(1, 'goof', '2024-10-06', '2024-10-02', 10.00, 1234567891234, 5);

-- --------------------------------------------------------

--
-- Estrutura para tabela `member`
--

CREATE TABLE `member` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `joinDate` date DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `member`
--

INSERT INTO `member` (`id`, `address`, `email`, `joinDate`, `phone`, `name`) VALUES
(5, '54470-570 / 65', 'luquian.santos@outlook.com', '2024-10-06', '81997844261', 'Luquian César'),
(6, '54478-350 / 54', 'lucaspaes@gmail.com', '2018-10-01', '81985651877', 'Lucas Paes'),
(7, '54420-670 / 4', 'raqueldama@hotmail.com', '2020-03-08', '81985641214', 'Raquel Damares');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`isbn`),
  ADD KEY `FK5gbo4o7yxefxivwuqjichc67t` (`author_id`);

--
-- Índices de tabela `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7isuqcuoluyk1r2qljbr0rf4y` (`book_isbn`),
  ADD KEY `FKhyo5n5nuj9bg5yvu77l8ytyl5` (`member_id`);

--
-- Índices de tabela `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `author`
--
ALTER TABLE `author`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `loan`
--
ALTER TABLE `loan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `member`
--
ALTER TABLE `member`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `FK5gbo4o7yxefxivwuqjichc67t` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`);

--
-- Restrições para tabelas `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `FK7isuqcuoluyk1r2qljbr0rf4y` FOREIGN KEY (`book_isbn`) REFERENCES `book` (`isbn`),
  ADD CONSTRAINT `FKhyo5n5nuj9bg5yvu77l8ytyl5` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
