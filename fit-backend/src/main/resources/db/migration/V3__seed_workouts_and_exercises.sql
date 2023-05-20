INSERT INTO profile (username, email, name, surname, password, age, gender, height, weight, goal_weight)
VALUES ('admin', 'admin@gmail.com', 'Admin', 'Admin', 'password123', 30, 'M', 183.5, 75.3, 80.0);

INSERT INTO workoutplan (name, profile_id)
VALUES ('NSUNS Plan', 1);

INSERT INTO workout (name, workout_plan_id)
VALUES ('Upper Body 1', 1),
       ('Lower Body 1', 1),
       ('Upper body 2', 1),
       ('Back 1', 1);

INSERT INTO exercise (name, max_weight)
VALUES ('Bench press', 80),
       ('Overhead press', 40),
       ('Triceps extension', 30),
       ('Pullup', 60),
       ('Dumbell curl', 11.5),
       ('Back squat', 100.5),
       ('Sumo deadlift', 70),
       ('Calf raise', 120),
       ('Seated cable row', 50),
       ('Lateral raise', 10),
       ('Close grip bench press', 50),
       ('Cable face pull', 25),
       ('EZ-bar curl', 30),
       ('Barbell row', 60),
       ('Deadlift', 130),
       ('Front squat', 60),
       ('Lat pulldown', 55);

INSERT INTO workoutexercise (workout_id, exercise_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (2, 6),
       (2, 7),
       (2, 8),
       (2, 9),
       (2, 10),
       (3, 1),
       (3, 11),
       (3, 12),
       (3, 13),
       (4, 14),
       (4, 15),
       (4, 16),
       (4, 17);

