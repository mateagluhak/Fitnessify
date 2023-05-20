CREATE TABLE profile
(
    id          SERIAL PRIMARY KEY,
    username    VARCHAR(50) UNIQUE NOT NULL,
    email       VARCHAR(50) UNIQUE NOT NULL,
    name        VARCHAR(15),
    surname     VARCHAR(15),
    password    VARCHAR(100)       NOT NULL,
    age         INTEGER,
    gender      CHAR(1),
    height      DECIMAL(5, 2)      NOT NULL,
    weight      DECIMAL(5, 2)      NOT NULL,
    goal_weight DECIMAL(5, 2)      NOT NULL,
    created_at  TIMESTAMP          NOT NULL DEFAULT NOW()
);

CREATE TABLE workoutplan
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    profile_id INTEGER      NOT NULL,
    FOREIGN KEY (profile_id) REFERENCES profile (id) ON DELETE CASCADE
);
CREATE TABLE workout
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    workout_plan_id INTEGER      NOT NULL,
    FOREIGN KEY (workout_plan_id) REFERENCES workoutplan (id) ON DELETE CASCADE
);

CREATE TABLE exercise
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(100)  NOT NULL,
    max_weight DECIMAL(5, 2) NOT NULL,
    workout_id INTEGER       NOT NULL,
    FOREIGN KEY (workout_id) REFERENCES workout (id) ON DELETE CASCADE
);
