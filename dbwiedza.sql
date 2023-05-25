-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 25, 2023 at 06:00 PM
-- Server version: 8.0.33
-- PHP Version: 7.4.3-4ubuntu2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbwiedza`
--

-- --------------------------------------------------------

--
-- Table structure for table `assessments`
--

CREATE TABLE `assessments` (
  `id` int NOT NULL,
  `class_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `value` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `assessments`
--

INSERT INTO `assessments` (`id`, `class_id`, `name`, `value`) VALUES
(3, 2, 'Avaliacao I', 15);

-- --------------------------------------------------------

--
-- Table structure for table `attendances`
--

CREATE TABLE `attendances` (
  `id` int NOT NULL,
  `class_id` int NOT NULL,
  `student_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `attendances`
--

INSERT INTO `attendances` (`id`, `class_id`, `student_id`) VALUES
(1, 1, 7),
(2, 2, 7);

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

CREATE TABLE `classes` (
  `id` int NOT NULL,
  `course_id` int NOT NULL,
  `content` varchar(200) NOT NULL,
  `date` date NOT NULL,
  `start_time` time NOT NULL,
  `number_of_class_hours` tinyint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`id`, `course_id`, `content`, `date`, `start_time`, `number_of_class_hours`) VALUES
(1, 1, 'Aula inaugural', '2023-05-24', '10:51:36', 4),
(2, 1, 'Primeira prova', '2023-05-25', '13:50:00', 4);

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `id` int NOT NULL,
  `subject_id` int NOT NULL,
  `teacher_id` int NOT NULL,
  `year` year NOT NULL,
  `completed` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`id`, `subject_id`, `teacher_id`, `year`, `completed`) VALUES
(1, 41, 2, '2023', 0),
(2, 13, 1, '2022', 1);

-- --------------------------------------------------------

--
-- Table structure for table `credentials`
--

CREATE TABLE `credentials` (
  `id` int NOT NULL,
  `user` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `credentials`
--

INSERT INTO `credentials` (`id`, `user`, `password`) VALUES
(2, 'maicon.leles', 'Marmelad4'),
(3, 'alexandre.f', '1234a'),
(4, 'frilos', '852456'),
(5, 'guisso.bibliotecario', 'amolivros'),
(6, 'catarina.soares', 'c%12##,kjKJKJ@**&(..)..(..)!!'),
(7, 'ana.zaira', 'aninhazairinha123');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id` int NOT NULL,
  `credentials_id` int NOT NULL,
  `name` varchar(300) NOT NULL,
  `date_of_birth` date NOT NULL,
  `cpf` bigint NOT NULL,
  `sector` enum('SECRETARIAT','COORDENATION','DIRECTION','TEACHING','OTHER') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(60) NOT NULL,
  `salary` float NOT NULL,
  `weekly_work_hours` tinyint NOT NULL,
  `street` varchar(255) NOT NULL,
  `district` varchar(100) NOT NULL,
  `number` varchar(10) NOT NULL,
  `cep` int NOT NULL,
  `primary_phone_number` bigint NOT NULL,
  `secondary_phone_number` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `credentials_id`, `name`, `date_of_birth`, `cpf`, `sector`, `role`, `salary`, `weekly_work_hours`, `street`, `district`, `number`, `cep`, `primary_phone_number`, `secondary_phone_number`) VALUES
(1, 3, 'Alexandre Ferreira', '2013-05-09', 70423232388, 'DIRECTION', 'Diretor Geral', 65780, 20, 'Rua dos Elefantes', 'Bairro do Barro', '106', 39402123, 38999045566, 38998981232),
(2, 2, 'Maicon Leles', '2004-05-25', 17480987641, 'SECRETARIAT', 'Secretário de matrículas', 6750, 60, 'Rua Francisco Gonçalves', 'Vera Cruz', '494', 39401430, 999213087, 0);

-- --------------------------------------------------------

--
-- Table structure for table `enrollments`
--

CREATE TABLE `enrollments` (
  `id` int NOT NULL,
  `course_id` int NOT NULL,
  `student_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `enrollments`
--

INSERT INTO `enrollments` (`id`, `course_id`, `student_id`) VALUES
(1, 1, 7),
(2, 1, 8),
(3, 2, 4),
(4, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `grades`
--

CREATE TABLE `grades` (
  `id` int NOT NULL,
  `assessment_id` int NOT NULL,
  `student_id` int NOT NULL,
  `student_grade` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `grades`
--

INSERT INTO `grades` (`id`, `assessment_id`, `student_id`, `student_grade`) VALUES
(1, 3, 7, 3),
(2, 3, 8, 0);

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

CREATE TABLE `locations` (
  `id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `capacity` smallint NOT NULL,
  `monthly_cost` float NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `locations`
--

INSERT INTO `locations` (`id`, `name`, `capacity`, `monthly_cost`, `description`) VALUES
(3, 'Sala de Aula 1', 30, 17.5, 'Equipada com: 30 carteiras, um quadro branco, mesa do professor.'),
(4, 'Sala de Aula 2', 30, 17.5, 'Equipada com: 30 carteiras, um quadro branco, mesa do professor.'),
(5, 'Sala de Aula 3', 30, 17.5, 'Equipada com: 30 carteiras, um quadro branco, mesa do professor.'),
(6, 'Sala de Aula 4', 30, 17.5, 'Equipada com: 30 carteiras, um quadro branco, mesa do professor.'),
(7, 'Sala de Aula 5', 30, 17.5, 'Equipada com: 30 carteiras, um quadro branco, mesa do professor.'),
(8, 'Sala de Aula 6', 30, 17.5, 'Equipada com: 30 carteiras, um quadro branco, mesa do professor.'),
(9, 'Sala de Aula 7', 30, 17.5, 'Equipada com: 30 carteiras, um quadro branco, mesa do professor.'),
(10, 'Sala de Aula 8', 60, 37.5, 'Equipada com: 60 carteiras, 2 quadros branco, mesa do professor, um dispositivo de datashow.'),
(11, 'Auditório I', 300, 430, '300 cadeiras, um projetor de alta capacidade, caixas de som.'),
(12, 'Auditório II', 250, 360, '250 cadeiras, um projetor de alta capacidade, caixas de som.'),
(13, 'Laboratório de Informática', 40, 230, 'Possui 40 computadores de tipo desktop. Uma mesa central com 40 cadeiras. Possui 2 quadros brancos e um dispositivo de datashow.\r\nOs computadores possuem conexão à Internet e também softwares educacionais variados.'),
(14, 'Laboratório de Química', 30, 200, 'Possui diversas bancadas com pias e torneiras de água destilada. Possui também armários com diversas vidrarias típicas das práticas laboratoriais de química (tubos de ensaio, erlenmeyers, béqueres, buretas, pipetas, balões de fundo chato e redondo etc.). O laboratório é provido também de equipamentos importantes como balanças de precisão, deionizadores, filtros, agitadores, aquecedores, almofarizes, bicos de Bunsen, capelas, microscópios, pHmetros, condensadores e até mesmo um espectrofotômetro. \r\n\r\nO laboratório pode servir também para aulas práticas de biologia, pois possui lâminas preparadas para observação no microscópio. Também estão presentes modelos anatômicos e espécimes diversas.'),
(15, 'Laboratório de Física', 35, 60, 'Possui balcões individuais e coletivos bem como assentos para cada um. Contém um dispositivo de redução de atrito por aplicação de ar comprimido. O laboratório possui diversos objetos de diferentes massas, formatos e propriedades físicas distintas. '),
(16, 'Pátio', 80, 50, 'O pátio possui uma bela fonte de água potável e diversos pés de carambola. Existem vários bancos espalhados pelo pátio.'),
(17, 'Quadra poliesportiva', 200, 50, 'Possui uma área de 500 x 750 m^2. Uma arquibancada com capacidade de 100 pessoas. A quadra possui demarcações para diversos esportes. Há uma pequena sala anexada onde estão objetos correlatos: bolas de futebol, bolas de basquete, bolas de vôlei, petecas,  redes, etc. '),
(18, 'Jardim', 75, 100, 'Um jardim ao céu aberto com várias árvores e flores amarelas e medrosas.');

-- --------------------------------------------------------

--
-- Table structure for table `occurrences`
--

CREATE TABLE `occurrences` (
  `id` int NOT NULL,
  `student_id` int NOT NULL,
  `date` date NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `occurrences`
--

INSERT INTO `occurrences` (`id`, `student_id`, `date`, `description`) VALUES
(3, 6, '2023-05-24', 'Longo Manuel chegou 3 horas atrasado.'),
(4, 5, '2005-05-11', 'Anafreda não fez o dever de casa.');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `id` int NOT NULL,
  `location_id` int NOT NULL,
  `week_day` enum('MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY') NOT NULL,
  `start_time` time NOT NULL,
  `number_of_class_hours` tinyint NOT NULL DEFAULT '2'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`id`, `location_id`, `week_day`, `start_time`, `number_of_class_hours`) VALUES
(1, 18, 'TUESDAY', '12:14:21', 4),
(2, 4, 'WEDNESDAY', '13:50:00', 2);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` int NOT NULL,
  `credentials_id` int NOT NULL,
  `name` varchar(300) NOT NULL,
  `date_of_birth` date NOT NULL,
  `cpf` bigint NOT NULL,
  `street` varchar(100) NOT NULL,
  `district` varchar(100) NOT NULL,
  `number` varchar(10) NOT NULL,
  `cep` int NOT NULL,
  `primary_phone_number` bigint NOT NULL,
  `secondary_phone_number` bigint DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `credentials_id`, `name`, `date_of_birth`, `cpf`, `street`, `district`, `number`, `cep`, `primary_phone_number`, `secondary_phone_number`, `active`) VALUES
(1, 0, 'Joaquim Ferreira dos Santos', '1990-05-23', 11111, 'street', 'district', 'number', 12, 1, 2, 0),
(2, 0, 'Anne Invertida', '2023-05-18', 0, '22', '33', '11', 0, 1, 2, 1),
(3, 0, 'Joaquina Infinita 2', '2023-05-19', 817674740, 'Street 60', 'The district', 'Number 86', 952160, 0, -9223372036854775808, 1),
(4, 0, 'Godofredo Inexistente', '1970-01-01', 5457476143, 'Street 23', 'The district', 'Number 76', 273986, 0, -9223372036854775808, 1),
(5, 0, 'Anafreda Inexistente', '1970-01-01', 3368406192, 'Street 58', 'The district', 'Number 4', 46731, 0, -9223372036854775808, 1),
(6, 0, 'Longo Manuel', '1970-01-01', 4046129653, 'Street 0', 'The district', 'Number 71', 132644, 0, -9223372036854775808, 1),
(7, 0, 'Pedro Gabriel', '2002-04-18', 92902597, 'Rua Sinabrio', 'Monte Carmelo', '401', 40230024, 997397549, 0, 1),
(8, 0, 'Flavio Rodrigo', '2002-09-04', 8649505511, 'Av. Rui de Albuquerque', 'Planalto', '1052', 39404046, 999685478, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `code` char(6) NOT NULL,
  `total_class_hours` smallint NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `requirement_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`id`, `name`, `code`, `total_class_hours`, `content`, `requirement_id`) VALUES
(1, 'Curso Rápido de Beiju', 'CRB001', 36, 'O básico sobre a técnica milenar de cozimento de beijus através de goma de mandioca.', NULL),
(2, 'Matemática', 'MAT006', 160, 'Potenciação e exponenciação. Multiplicidade, divisibilidade e primalidade. \r\nNoções de geometria plana. Polígonos. Semelhança de triângulos. \r\nNoções de geometria espacial. Poliedros.\r\nRelação de Euler. Classificação de Poliedros.', NULL),
(4, 'Matemática', 'MAT007', 160, 'Introdução à álgebra. Variáveis e domínio. Polinômios. Operações algébricas: adição, multiplicação, fatoração e expansão. Produtos notáveis. Equações de primeiro grau. Inequações de primeiro grau. Sistemas de equações de primeiro grau.', 2),
(5, 'Matemática', 'MAT008', 160, 'Triângulos: área, semelhança, relações métricas, classificação, pontos notáveis. Quadriláteros, hexágonos e circunferências. Polígonos regulares.\r\nPotenciação e Radiciação. Equações de primeiro grau e sistemas de equações de primeiro grau. ', 4),
(6, 'Matemática', 'MAT009', 160, 'Equações de segundo grau. Equações irracionais. Equações biquadradas. Sistemas redutíveis a equações de segundo grau. \r\nInscrição e circunscrição de polígonos. Relações trigonométricas no triângulo retângulo. Noções de estatística.', 5),
(7, 'Língua Portuguesa', 'POR006', 160, 'Compreensão e interpretação textual. Tipologia e gêneros textuais. Ortografia e acentuação gráfica. Redação. ', NULL),
(8, 'Língua Portuguesa', 'POR007', 160, 'Compreensão e intepretação textual. Morfologia gramatical: substantivos, adjetivos, artigos, pronomes, verbos, advérbios, conjunções, numerais, preposições e interjeições.\r\nRedação. ', 7),
(9, 'Língua Portuguesa', 'POR008', 160, 'Compreensão e interpretação textual.\r\nEstrutura gramatical: frase, oração e período. Sintaxe: termos essenciais da oração (sujeito e predicado); termos acessórios (adjunto adnominal, adjunto advervial, agente da passiva, aposto, vocativos). Regência verbal e nominal.', 8),
(10, 'Língua Portuguesa', 'POR009', 160, 'Compreensão e interpretação textual. Crase e acento grave. Composição de períodos por coordenação e subordinação. Pontuação. \r\nArticulação, coesão e coerência textual. \r\nRedação. Semântica textual.', 9),
(11, 'História', 'HIS006', 160, 'Pré-história: paleolítico e neolítico. Antiguidade: Egito Antigo; Grécia Antiga; Sociedades mesopotâmicas (babilônios, assírios, sumérios). Sociedade romana: ascensão, cisma e decadência.', NULL),
(12, 'História', 'HIS007', 160, 'Fim do Império Romano. Idade média: invasões bárbaras; o reino franco e o império carolíngio; sociedade feudal, cruzadas; renascimento comercial; peste negra; crise do feudalismo. ', 11),
(13, 'História', 'HIS008', 160, 'Formação dos estados nacionais. Expansão marítima e colonialismo. Brasil colônia. A Reforma e a Contrarreforma. Mercantilismo e Absolutismo. Iluminismo. Revoluções burguesas. Era napoleônica. Brasil Império.', 12),
(14, 'História', 'HIS009', 160, 'Imperialismo. Primeira e Segunda Guerra Mundial. Revolução Russa. Brasil República. Guerra Fria. Nova Ordem Mundial. Conflitos do Oriente Médio. ', 13),
(15, 'Geografia', 'GEO006', 160, 'Representação e documentação cartográfica; formas de organização do espaço geográfico.\r\n', 14),
(16, 'Geografia', 'GEO007', 160, 'Os grandes conjuntos naturais\r\ndo globo, sua ocupação e seu aproveitamento; a indústria e suas tecnologias; o processo de globalização/fragmentação no mundo contemporâneo: origens,\r\ndimensões e perspectivas; a circulação, o comércio e o transporte e suas implicações na organização do espaço.', NULL),
(17, 'Geografia', 'GEO008', 160, 'A dinâmica demográfica brasileira e mundial,\r\ncaracterísticas e modalidade da força de trabalho; o meio ambiente, os recursos naturais e o desenvolvimento sustentável no mundo e no Brasil; a questão\r\nenergética em escala brasileira e mundial; a organização do espaço urbano industrial brasileiro e seus desdobramentos socioambientais.', 16),
(18, 'Geografia', 'GEO009', 160, 'Organização do espaço\r\nagrário brasileiro e mundial, problemas e perspectivas; configuração dos complexos regionais brasileiros; papel do Brasil no contexto do capitalismo mundial\r\nintegrado e sua inserção no Mercosul; a apropriação dos recursos minerais e energéticos do Brasil e processo de privatização.', 17),
(19, 'Literatura', 'LIT006', 80, 'Introdução aos estudos literários: conceitos fundamentais (discurso ficcional e não-ficcional, texto literário, autor, narrador, eu-lírico, personagem, pluralidades enunciativas, os gêneros literários). ', 18),
(20, 'Literatura', 'LIT007', 80, 'Movimentos literários: trovadorismo e arcadismo.', NULL),
(21, 'Literatura', 'LIT008', 80, 'As prosas de ficção romântica e realista/naturalista.', 20),
(22, 'Literatura', 'LIT009', 80, 'Pré-modernismo, Modernismo, Tendências contemporâneas.', 21),
(23, 'Redação', 'RED006', 80, 'Habilidades em redigir textos de diferentes gêneros redigidos em Língua Portuguesa, tais como: jornalísticos (notícia, editorial, artigo, reportagem, carta ao leitor, entrevista, crônicas, charge, tira, HQs), divulgação científica (esquema, resumo, artigos, verbetes), publicitários, instrucionais, técnicos, políticos, históricos,\r\nreligiosos, populares, humorísticos (verbais e não-verbais), literários (conto, novela, crônica, poema, texto dramático); hipertexto e gêneros digitais (e-mail, blog, etc).\r\n', 22),
(24, 'Redação', 'RED007', 80, 'Habilidades em redigir textos de diferentes gêneros redigidos em Língua Portuguesa, tais como: jornalísticos (notícia, editorial, artigo, reportagem, carta ao leitor, entrevista, crônicas, charge, tira, HQs), divulgação científica (esquema, resumo, artigos, verbetes), publicitários, instrucionais, técnicos, políticos, históricos,\r\nreligiosos, populares, humorísticos (verbais e não-verbais), literários (conto, novela, crônica, poema, texto dramático); hipertexto e gêneros digitais (e-mail, blog, etc).\r\n', NULL),
(25, 'Redação', 'RED008', 80, 'Habilidades em redigir textos de diferentes gêneros redigidos em Língua Portuguesa, tais como: jornalísticos (notícia, editorial, artigo, reportagem, carta ao leitor, entrevista, crônicas, charge, tira, HQs), divulgação científica (esquema, resumo, artigos, verbetes), publicitários, instrucionais, técnicos, políticos, históricos,\r\nreligiosos, populares, humorísticos (verbais e não-verbais), literários (conto, novela, crônica, poema, texto dramático); hipertexto e gêneros digitais (e-mail, blog, etc).\r\n', 24),
(26, 'Redação', 'RED009', 80, 'Textos dissertativos (argumentativos e expositivos). Adequação textual. Coesão e coerência. Mecanismos coesivos típicos da língua portuguesa. Eficiência textual a adequação ao nível de linguagem.', 25),
(27, 'Artes', 'ART006', 80, 'O conceito de artes. Formas de expressão. Manifestações artísticas. Arte rupestre.  Pintura corporal. Teatro primitivo. Arte ritualísitca. Dança e música dos diferentes povos.\r\n', NULL),
(28, 'Artes', 'ART007', 80, 'Arte medieval. Arquitetura românica e gótica. Iluminuras e afrescos. ', NULL),
(29, 'Artes', 'ART008', 80, 'Arte clássica: pintura e música. Balé moderno. Dramaturgia grega. O realismo, impressionismo, expressionismo e o romantismo. ', NULL),
(30, 'Artes', 'ART009', 80, 'Pop-art. Cultura popular e cultura da massa. Arte digital. Arte moderna. Movimento tropicalista. Gêneros cinematográficos.', NULL),
(31, 'Educação Física', 'EDF006', 80, 'Desenvolvimento da cooperação e socialização; força e resiliência física; atenção e foco. Saúde física e mental. Velocidade e agilidade. Prática de esportes.', NULL),
(32, 'Educação Física', 'EDF007', 80, 'Desenvolvimento da cooperação e socialização; força e resiliência física; atenção e foco. Saúde física e mental. Velocidade e agilidade. Prática de esportes.', NULL),
(33, 'Educação Física', 'EDF008', 80, 'Desenvolvimento da cooperação e socialização; força e resiliência física; atenção e foco. Saúde física e mental. Velocidade e agilidade. Prática de esportes.', NULL),
(34, 'Educação Física', 'EDF009', 80, 'Desenvolvimento da cooperação e socialização; força e resiliência física; atenção e foco. Saúde física e mental. Velocidade e agilidade. Prática de esportes.', NULL),
(35, 'Ciências', 'CIE006', 160, 'Introdução ao estudo da Química: matéria e energia, fenômenos químicos e físicos, estado físico da matéria.\r\nBases do funcionamento dos sistemas ecológicos; Interações do homem com a natureza; Condições ambientais e a saúde.', NULL),
(36, 'Ciências', 'CIE007', 160, 'Níveis de organização dos sistemas biológicos; Processos fundamentais da fisiologia celular: respiração, fotossíntese, síntese proteica e divisão celular; Noções básicas dos tipos de tecidos e de sistemas humanos, bem como dos tipos de tecidos e de sistemas de vegetais superiores. Substancias puras e misturas: substância pura e mistura, substância simples e composta, atomicidade e alotropia, mistura homogênea e heterogênea.', NULL),
(37, 'Ciências', 'CIE008', 160, 'Introdução à Física – Grandezas físicas: vetores, Sistemas de Unidades; Mecânica – Conceitos básicos de cinemática. Estrutura atômica: partículas atômicas fundamentais, modelo atômico atual, número atômico e número de massa, elemento químico, isotopos, Isóbaros, isótonos,\r\níon, números quânticos, evolução dos modelos atômicos: Dalton, Thomson, Rutherford e Bohr.Características gerais dos vírus: Características gerais, condições de habitat, adaptações,\r\nimportância ecológica e econômica dos seguintes grupos: Bactérias, Algas e Fungos; Características morfológicas e adaptativas das Plantas; Características gerais dos seguintes grupos animais: Anelídeos, Moluscos, Artrópodes e Vertebrados.', NULL),
(38, 'Ciências', 'CIE009', 160, 'Movimento retilíneo e uniforme; Movimento\r\nretilíneo e uniformemente variado; Lançamentos: vertical, horizontal e oblíquo; Movimentos circulares.\r\n\r\nClassificação periódica dos elementos: organização, localização e classificação dos elementos, propriedades periódicas e aperiódicas.\r\n\r\nTipos de reprodução e fecundação; Reprodução humana; Etapas do desenvolvimento humano,\r\nate gastrula e anexos embrionários; Deverão ser conhecidos os tipos de reprodução assexuada – como divisão binária, esporulação, brotamento e vegetativo', NULL),
(39, 'Filosofia', 'FIL006', 80, 'A origem, o motivo e a utilidade da Filosofia. O senso comum e o bom senso. Crescimento e mudança. Ética e o bem e o mal.', NULL),
(40, 'Filosofia', 'FIL007', 80, 'O homem e o ser político. Comportamento. A causa e a consequência; a razão e o motivo.  Poder e cidadania.', NULL),
(41, 'Filosofia', 'FIL008', 80, 'Lógica clássica. Dogmatismo, ceticismo, racionalismo e empirismo.', NULL),
(42, 'Filosofia', 'FIL009', 80, 'A justiça; direitos, obrigações e privilégios. A ética e a moral. O julgamento e as leis naturais. Estética: o belo e o feio.', NULL),
(43, 'Língua Inglesa', 'ING006', 80, 'Familiarização com a língua inglesa. Aquisição de vocabulário básico. Práticas de pronúncia e ortografia. Leitura de frases simples em língua inglesa. Uso de dicionários. ', NULL),
(44, 'Língua Inglesa', 'ING007', 80, 'História de Língua Inglesa. Aquisição de vocabulário. Práticas de pronúncia, leitura e escrita. Tópicos básicos de gramática.', NULL),
(45, 'Língua Inglesa', 'ING008', 80, 'Cultura anglófona. Compreensão e interpretação de textos simples em inglês. Tópicos selecionados de gramática.', NULL),
(46, 'Língua Inglesa', 'ING009', 80, 'Variações linguísticas da Língua Inglesa. Influência e uso da língua inglesa pelo mundo. Compreensão e interpretação textual (escrita e oral). Tópicos selecionados de gramática.', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assessments`
--
ALTER TABLE `assessments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `class_id` (`class_id`);

--
-- Indexes for table `attendances`
--
ALTER TABLE `attendances`
  ADD PRIMARY KEY (`id`),
  ADD KEY `class_id` (`class_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `subject_id` (`subject_id`),
  ADD KEY `teacher_id` (`teacher_id`);

--
-- Indexes for table `credentials`
--
ALTER TABLE `credentials`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD KEY `credentials_id` (`credentials_id`);

--
-- Indexes for table `enrollments`
--
ALTER TABLE `enrollments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `student_id` (`student_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `grades`
--
ALTER TABLE `grades`
  ADD PRIMARY KEY (`id`),
  ADD KEY `assessment_id` (`assessment_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `locations`
--
ALTER TABLE `locations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `occurrences`
--
ALTER TABLE `occurrences`
  ADD PRIMARY KEY (`id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `site_id` (`location_id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD KEY `credentials_id` (`credentials_id`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`id`),
  ADD KEY `requirement_id` (`requirement_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assessments`
--
ALTER TABLE `assessments`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `attendances`
--
ALTER TABLE `attendances`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `classes`
--
ALTER TABLE `classes`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `credentials`
--
ALTER TABLE `credentials`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `enrollments`
--
ALTER TABLE `enrollments`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `grades`
--
ALTER TABLE `grades`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `locations`
--
ALTER TABLE `locations`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `occurrences`
--
ALTER TABLE `occurrences`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assessments`
--
ALTER TABLE `assessments`
  ADD CONSTRAINT `assessments_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`);

--
-- Constraints for table `attendances`
--
ALTER TABLE `attendances`
  ADD CONSTRAINT `attendances_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`),
  ADD CONSTRAINT `attendances_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`);

--
-- Constraints for table `classes`
--
ALTER TABLE `classes`
  ADD CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`);

--
-- Constraints for table `courses`
--
ALTER TABLE `courses`
  ADD CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`),
  ADD CONSTRAINT `courses_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `employees` (`id`);

--
-- Constraints for table `enrollments`
--
ALTER TABLE `enrollments`
  ADD CONSTRAINT `enrollments_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  ADD CONSTRAINT `enrollments_ibfk_3` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`);

--
-- Constraints for table `grades`
--
ALTER TABLE `grades`
  ADD CONSTRAINT `grades_ibfk_1` FOREIGN KEY (`assessment_id`) REFERENCES `assessments` (`id`),
  ADD CONSTRAINT `grades_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`);

--
-- Constraints for table `occurrences`
--
ALTER TABLE `occurrences`
  ADD CONSTRAINT `occurrences_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`);

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`);

--
-- Constraints for table `subjects`
--
ALTER TABLE `subjects`
  ADD CONSTRAINT `subjects_ibfk_1` FOREIGN KEY (`requirement_id`) REFERENCES `subjects` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
