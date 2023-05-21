INSERT INTO profile (username, email, name, surname, password, age, gender, height, weight, goal_weight)
VALUES ('admin', 'admin@gmail.com', 'Admin', 'Admin', 'password123', 30, 'M', 183.5, 75.3, 80.0);

INSERT INTO workoutplan (name, profile_id)
VALUES ('NSUNS Plan', 1),
       ('Push/pull', 1),
       ('Full body split', 1);

INSERT INTO workout (name, workout_plan_id)
VALUES ('Upper Body 1', 1),
       ('Lower Body 1', 1),
       ('Upper body 2', 1),
       ('Back 1', 1);

INSERT INTO exercise (name, muscle_group, max_weight)
VALUES ('Bench press', 'upper_body', 80),
       ('Overhead press', 'upper_body', 40),
       ('Triceps extension', 'upper_body', 30),
       ('Pullup', 'upper_body', 60),
       ('Dumbell curl', 'upper_body', 11.5),
       ('Back squat', 'lower_body', 100.5),
       ('Sumo deadlift', 'lower_body', 70),
       ('Calf raise', 'lower_body', 120),
       ('Seated cable row', 'back', 50),
       ('Lateral raise', 'upper_body', 10),
       ('Close grip bench press', 'upper_body', 50),
       ('Cable face pull', 'back', 25),
       ('EZ-bar curl', 'upper_body', 30),
       ('Barbell row', 'back', 60),
       ('Deadlift', 'back', 130),
       ('Front squat', 'lower_body', 60),
       ('Lateral pulldown', 'back', 55);

INSERT INTO priority (name)
VALUES ('Primary'),
       ('Secondary');

INSERT INTO workoutexercise (workout_id, exercise_id, priority_id, repetitions)
VALUES (1, 1, 1, 40),
       (1, 2, 1, 40),
       (1, 3, 2, 32),
       (1, 4, 2, 32),
       (1, 5, 2, 32),
       (2, 6, 1, 40),
       (2, 7, 1, 40),
       (2, 8, 2, 40),
       (2, 9, 2, 32),
       (2, 10, 2, 32),
       (3, 1, 1, 40),
       (3, 11, 1, 40),
       (3, 12, 2, 32),
       (3, 13, 2, 40),
       (4, 14, 1, 60),
       (4, 15, 1, 40),
       (4, 16, 2, 60),
       (4, 17, 2, 32);

