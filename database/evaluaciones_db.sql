-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 04-07-2024 a las 20:55:41
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `evaluaciones_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bloque`
--

CREATE TABLE `bloque` (
  `idBloque` int(11) NOT NULL,
  `nombreBloque` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `bloque`
--

INSERT INTO `bloque` (`idBloque`, `nombreBloque`) VALUES
(1, 'Enlaza'),
(2, 'Mejora'),
(3, 'Prospecta'),
(4, 'Argumenta'),
(5, 'Asegura'),
(6, 'Comunica'),
(7, 'Fideliza/Agradece');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evaluacion`
--

CREATE TABLE `evaluacion` (
  `idEvaluacion` int(11) NOT NULL,
  `idLlamada` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `notaFinal` decimal(5,2) DEFAULT NULL,
  `comentarios` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `evaluacion`
--

INSERT INTO `evaluacion` (`idEvaluacion`, `idLlamada`, `fecha`, `notaFinal`, `comentarios`) VALUES
(1, 1, '2024-07-01', 90.00, 'Comentarios de prueba'),
(3, 4, '2024-07-04', 60.50, 'atendio correctamente'),
(4, 5, '2024-07-04', 94.73, 'Comentarios opcionales'),
(5, 6, '2004-07-04', 15.00, 'regular'),
(6, 14, '2024-07-04', 63.78, 'Comentarios opcionales');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `llamada`
--

CREATE TABLE `llamada` (
  `idLlamada` int(11) NOT NULL,
  `nombreCliente` varchar(100) DEFAULT NULL,
  `nombreEvaluador` varchar(100) DEFAULT NULL,
  `numeroLlamada` varchar(50) DEFAULT NULL,
  `fechaLlamada` date DEFAULT NULL,
  `resumenLlamada` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `llamada`
--

INSERT INTO `llamada` (`idLlamada`, `nombreCliente`, `nombreEvaluador`, `numeroLlamada`, `fechaLlamada`, `resumenLlamada`) VALUES
(1, 'Cliente 1', 'Evaluador 1', '123456', '2024-07-01', 'Resumen de la llamada 1'),
(2, 'Cliente 2', 'Evaluador 2', '789012', '2024-07-02', 'Resumen de la llamada 2'),
(3, 'paco perez', 'juanito alcachofa', '909125687', '2024-04-20', 'dadasdasd'),
(4, 'dada', 'dasdasd', '902912024', '2023-02-20', 'dadasda'),
(5, 'adsad', 'asdasd', '902912024', '2013-02-20', 'sdada'),
(6, 'manuel', 'mario', '902912024', '2004-09-23', 'sdada'),
(7, 'maria', 'maria', '909454687', '2025-05-22', 'prueba 6'),
(8, 'sara', 'ernesto', '909222355', '2024-02-19', 'llamada regular'),
(9, 'maria', 'sara', '752463189', '2004-02-19', 'cosas que se colocan ejem'),
(10, 'maria', 'sara', '9798933254', '2023-03-19', 'ejemplo 6'),
(11, 'maria', 'sara', '847567898', '2504-04-19', 'practica 8'),
(12, 'sara', 'maria', '453622689', '2025-02-19', 'practica 10\n'),
(13, 'sara', 'maria', '987456321', '0007-02-20', 'ejemplo 10'),
(14, 'sara', 'maria', '901265423', '2022-07-19', 'prueba 20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pregunta`
--

CREATE TABLE `pregunta` (
  `idPregunta` int(11) NOT NULL,
  `idBloque` int(11) DEFAULT NULL,
  `textoPregunta` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pregunta`
--

INSERT INTO `pregunta` (`idPregunta`, `idBloque`, `textoPregunta`) VALUES
(1, 1, '¿Cómo se presenta el asesor?'),
(2, 1, '¿Solicita consentimiento para brindar la propuesta?'),
(3, 2, '¿Gestiona el ofrecimiento del equipo según lo registrado?'),
(4, 2, '¿Brinda información completa del plan y equipo?'),
(5, 2, '¿Promueve la entrega por delivery adecuadamente?'),
(6, 2, '¿Ofrece y argumenta la venta de accesorios?'),
(7, 3, '¿Realiza preguntas filtro sobre líneas adicionales?'),
(8, 3, '¿Identifica la necesidad y presupuesto del cliente?'),
(9, 4, '¿Demuestra habilidad para contrarrestar el motivo de no venta?'),
(10, 4, '¿Utiliza frases potentes de pre-cierre?'),
(11, 4, '¿Ofrece líneas adicionales como argumento de venta?'),
(12, 5, '¿Lee contrato?'),
(13, 6, '¿Mantiene buena comunicación y personalización durante la llamada?'),
(14, 7, '¿Fideliza y agradece al cliente de manera adecuada?'),
(15, 1, '¿Cómo se presenta el asesor?'),
(16, 1, '¿Solicita consentimiento para brindar la propuesta?'),
(17, 2, '¿Gestiona el ofrecimiento del equipo según lo registrado?'),
(18, 2, '¿Brinda información completa del plan y equipo?'),
(19, 2, '¿Promueve la entrega por delivery adecuadamente?'),
(20, 2, '¿Ofrece y argumenta la venta de accesorios?'),
(21, 3, '¿Realiza preguntas filtro sobre líneas adicionales?'),
(22, 3, '¿Identifica la necesidad y presupuesto del cliente?'),
(23, 4, '¿Demuestra habilidad para contrarrestar el motivo de no venta?'),
(24, 4, '¿Utiliza frases potentes de pre-cierre?'),
(25, 4, '¿Ofrece líneas adicionales como argumento de venta?'),
(26, 5, '¿Lee contrato?'),
(27, 6, '¿Mantiene buena comunicación y personalización durante la llamada?'),
(28, 7, '¿Fideliza y agradece al cliente de manera adecuada?');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuesta`
--

CREATE TABLE `respuesta` (
  `idRespuesta` int(11) NOT NULL,
  `idEvaluacion` int(11) DEFAULT NULL,
  `idPregunta` int(11) DEFAULT NULL,
  `respuesta` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `respuesta`
--

INSERT INTO `respuesta` (`idRespuesta`, `idEvaluacion`, `idPregunta`, `respuesta`) VALUES
(1, 1, 1, 'Sí'),
(2, 1, 2, 'No'),
(3, 1, 3, 'N/'),
(4, 4, 1, 'Sí'),
(5, 4, 2, 'Sí'),
(6, 4, 15, 'Sí'),
(7, 4, 16, 'Sí'),
(8, 4, 3, 'N/A'),
(9, 4, 4, 'Sí'),
(10, 4, 5, ''),
(11, 4, 6, 'Sí'),
(12, 4, 17, 'Sí'),
(13, 4, 18, 'N/A'),
(14, 4, 19, ''),
(15, 4, 20, 'Sí'),
(16, 4, 7, 'Sí'),
(17, 4, 8, 'N/A'),
(18, 4, 21, 'Sí'),
(19, 4, 22, 'Sí'),
(20, 4, 9, 'Sí'),
(21, 4, 10, 'Sí'),
(22, 4, 11, 'Sí'),
(23, 4, 23, 'Sí'),
(24, 4, 24, 'Sí'),
(25, 4, 25, 'N/A'),
(26, 4, 12, 'Sí'),
(27, 4, 26, 'Sí'),
(28, 4, 13, 'Sí'),
(29, 4, 27, 'Sí'),
(30, 4, 14, 'No'),
(31, 4, 28, 'No'),
(32, 5, 1, 'Sí'),
(33, 5, 2, 'No'),
(34, 5, 15, 'Sí'),
(35, 5, 16, 'Sí'),
(36, 5, 3, 'Sí'),
(37, 5, 4, 'Sí'),
(38, 5, 5, 'No'),
(39, 5, 6, 'No'),
(40, 5, 17, 'N/A'),
(41, 5, 18, 'N/A'),
(42, 5, 19, 'Sí'),
(43, 5, 20, 'Sí'),
(44, 5, 7, 'Sí'),
(45, 5, 8, 'Sí'),
(46, 5, 21, 'Sí'),
(47, 5, 22, 'Sí'),
(48, 5, 9, 'No'),
(49, 5, 10, 'Sí'),
(50, 5, 11, 'No'),
(51, 5, 23, 'Sí'),
(52, 5, 24, 'No'),
(53, 5, 25, 'Sí'),
(54, 5, 12, 'No'),
(55, 5, 26, 'Sí'),
(56, 5, 13, 'Sí'),
(57, 5, 27, 'Sí'),
(58, 5, 14, 'Sí'),
(59, 5, 28, 'Sí'),
(60, 6, 1, 'Sí'),
(61, 6, 2, 'Sí'),
(62, 6, 15, 'Sí'),
(63, 6, 16, 'No'),
(64, 6, 3, 'N/A'),
(65, 6, 4, 'Sí'),
(66, 6, 5, 'No'),
(67, 6, 6, 'N/A'),
(68, 6, 17, 'No'),
(69, 6, 18, 'Sí'),
(70, 6, 19, 'Sí'),
(71, 6, 20, 'Sí'),
(72, 6, 7, 'Sí'),
(73, 6, 8, 'N/A'),
(74, 6, 21, 'No'),
(75, 6, 22, 'No'),
(76, 6, 9, 'N/A'),
(77, 6, 10, 'No'),
(78, 6, 11, 'No'),
(79, 6, 23, 'No'),
(80, 6, 24, 'Sí'),
(81, 6, 25, 'No'),
(82, 6, 12, 'Sí'),
(83, 6, 26, 'No'),
(84, 6, 13, 'Sí'),
(85, 6, 27, 'No'),
(86, 6, 14, 'Sí'),
(87, 6, 28, 'No');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bloque`
--
ALTER TABLE `bloque`
  ADD PRIMARY KEY (`idBloque`);

--
-- Indices de la tabla `evaluacion`
--
ALTER TABLE `evaluacion`
  ADD PRIMARY KEY (`idEvaluacion`),
  ADD KEY `idLlamada` (`idLlamada`);

--
-- Indices de la tabla `llamada`
--
ALTER TABLE `llamada`
  ADD PRIMARY KEY (`idLlamada`);

--
-- Indices de la tabla `pregunta`
--
ALTER TABLE `pregunta`
  ADD PRIMARY KEY (`idPregunta`),
  ADD KEY `idBloque` (`idBloque`);

--
-- Indices de la tabla `respuesta`
--
ALTER TABLE `respuesta`
  ADD PRIMARY KEY (`idRespuesta`),
  ADD KEY `idEvaluacion` (`idEvaluacion`),
  ADD KEY `idPregunta` (`idPregunta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `bloque`
--
ALTER TABLE `bloque`
  MODIFY `idBloque` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `evaluacion`
--
ALTER TABLE `evaluacion`
  MODIFY `idEvaluacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `llamada`
--
ALTER TABLE `llamada`
  MODIFY `idLlamada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `pregunta`
--
ALTER TABLE `pregunta`
  MODIFY `idPregunta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `respuesta`
--
ALTER TABLE `respuesta`
  MODIFY `idRespuesta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `evaluacion`
--
ALTER TABLE `evaluacion`
  ADD CONSTRAINT `evaluacion_ibfk_1` FOREIGN KEY (`idLlamada`) REFERENCES `llamada` (`idLlamada`);

--
-- Filtros para la tabla `pregunta`
--
ALTER TABLE `pregunta`
  ADD CONSTRAINT `pregunta_ibfk_1` FOREIGN KEY (`idBloque`) REFERENCES `bloque` (`idBloque`);

--
-- Filtros para la tabla `respuesta`
--
ALTER TABLE `respuesta`
  ADD CONSTRAINT `respuesta_ibfk_1` FOREIGN KEY (`idEvaluacion`) REFERENCES `evaluacion` (`idEvaluacion`),
  ADD CONSTRAINT `respuesta_ibfk_2` FOREIGN KEY (`idPregunta`) REFERENCES `pregunta` (`idPregunta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
