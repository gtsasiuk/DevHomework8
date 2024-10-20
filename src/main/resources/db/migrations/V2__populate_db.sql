INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES
('John Doe', '1985-05-15', 'Senior', 7500),
('Jane Smith', '1990-08-23', 'Middle', 4000),
('Alice Johnson', '1995-12-11', 'Junior', 2500),
('Bob Brown', '2000-04-02', 'Trainee', 900),
('Michael Taylor', '1988-07-19', 'Senior', 6800),
('Laura White', '1993-11-10', 'Middle', 3500),
('Chris Green', '2001-02-21', 'Trainee', 950),
('David Black', '1997-09-30', 'Junior', 1800),
('Samantha Blue', '1989-06-14', 'Senior', 8000),
('Jessica Pink', '1994-04-25', 'Middle', 4200);

INSERT INTO client (NAME) VALUES
('MegaCorp'),
('Tech Innovations'),
('Blue Ocean'),
('Greenfield Inc.'),
('HyperSolutions');

INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES
(1, '2023-01-01', '2024-01-01'),
(2, '2022-05-15', '2023-03-15'),
(3, '2021-07-01', '2022-07-01'),
(4, '2020-10-01', '2021-12-31'),
(5, '2019-06-15', '2021-06-15'),
(1, '2023-03-01', '2023-12-01'),
(2, '2021-09-01', '2022-05-01'),
(3, '2020-04-01', '2022-02-01'),
(4, '2018-01-01', '2018-12-01'),
(5, '2017-03-01', '2020-03-01');


INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5),
(3, 6),
(4, 7),
(4, 8),
(5, 9),
(5, 10);
