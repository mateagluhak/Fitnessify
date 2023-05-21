package fer.infsus.fitnessify.domain.repository;

import fer.infsus.fitnessify.AbstractTestIT;
import fer.infsus.fitnessify.domain.model.Exercise;
import fer.infsus.fitnessify.enums.MuscleGroup;
import fer.infsus.fitnessify.interfaces.dto.ExerciseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ExerciseRepositoryTest extends AbstractTestIT {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Test
    void getExerciseById_when_exerciseExists_should_returnExercise() {
        Integer exerciseId = 1;
        Exercise expectedResult = new Exercise(1, "Bench press", MuscleGroup.upper_body, 80.0);

        Exercise result = exerciseRepository.getExerciseById(exerciseId);
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    void getExerciseById_when_exerciseNotExists_should_returnNull() {
        Integer exerciseId = 12405;
        Exercise expectedResult = null;

        Exercise result = exerciseRepository.getExerciseById(exerciseId);
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    void getExercises_should_returnAllExercises() {
        Exercise exercise1 = new Exercise(1, "Bench press", MuscleGroup.upper_body, 80.0);
        Exercise exercise2 = new Exercise(2, "Overhead press", MuscleGroup.upper_body, 40.0);
        Exercise exercise3 = new Exercise(3, "Triceps extension", MuscleGroup.upper_body, 30.0);
        Exercise exercise4 = new Exercise(4, "Pullup", MuscleGroup.upper_body, 60.0);
        Exercise exercise5 = new Exercise(5, "Dumbell curl", MuscleGroup.upper_body, 11.5);
        Exercise exercise6 = new Exercise(6, "Back squat", MuscleGroup.lower_body, 100.5);
        Exercise exercise7 = new Exercise(7, "Sumo deadlift", MuscleGroup.lower_body, 70.0);
        Exercise exercise8 = new Exercise(8, "Calf raise", MuscleGroup.lower_body, 120.0);
        Exercise exercise9 = new Exercise(9, "Seated cable row", MuscleGroup.back, 50.0);
        Exercise exercise10 = new Exercise(10, "Lateral raise", MuscleGroup.upper_body, 10.0);
        Exercise exercise11 = new Exercise(11, "Close grip bench press", MuscleGroup.upper_body, 50.0);
        Exercise exercise12 = new Exercise(12, "Cable face pull", MuscleGroup.back, 25.0);
        Exercise exercise13 = new Exercise(13, "EZ-bar curl", MuscleGroup.upper_body, 30.0);
        Exercise exercise14 = new Exercise(14, "Barbell row", MuscleGroup.back, 60.0);
        Exercise exercise15 = new Exercise(15, "Deadlift", MuscleGroup.back, 130.0);
        Exercise exercise16 = new Exercise(16, "Front squat", MuscleGroup.lower_body, 60.0);
        Exercise exercise17 = new Exercise(17, "Lateral pulldown", MuscleGroup.back, 55.0);

        List<Exercise> result = exerciseRepository.getExercises();
        assertThat(result, contains(exercise1, exercise2, exercise3, exercise4, exercise5, exercise6,
                exercise7, exercise8, exercise9, exercise10, exercise11, exercise12, exercise13, exercise14, exercise15, exercise16, exercise17));
        assertThat(result, hasSize(17));
    }

    @Test
    void createExercise_when_validExercise_should_returnExercise() {
        ExerciseDto inputExerciseDto = new ExerciseDto("name", "upper_body", 10.0);
        Exercise expectedResult = new Exercise(18, "name", MuscleGroup.upper_body, 10.0);

        Exercise result = exerciseRepository.createExercise(inputExerciseDto);
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    void createExercise_when_invalidMuscleGroup_should_returnNull() {
        ExerciseDto inputExerciseDto = new ExerciseDto("name", "gasxca", 10.0);

        Exercise result = exerciseRepository.createExercise(inputExerciseDto);
        assertThat(result, equalTo(null));
    }

    @Test
    void editExercise_when_validExercise_should_returnExercise() {
        Integer exerciseId = 10;
        ExerciseDto inputExerciseDto = new ExerciseDto("name", "upper_body", 10.0);
        Exercise expectedResult = new Exercise(10, "name", MuscleGroup.upper_body, 10.0);

        Exercise result = exerciseRepository.editExercise(exerciseId, inputExerciseDto);
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    void editExercise_when_invalidMuscleGroup_should_throwInvalidMuscleGroupException() {
        Integer exerciseId = 10;
        ExerciseDto inputExerciseDto = new ExerciseDto("name", "fsagas", 10.0);

        Exercise result = exerciseRepository.editExercise(exerciseId, inputExerciseDto);
        assertThat(result, equalTo(null));
    }

    @Test
    void deleteExercise_when_exerciseExists_should_returnTrue() {
        Integer exerciseId = 1;

        boolean result = exerciseRepository.deleteExercise(exerciseId);
        assertTrue(result);
    }

    @Test
    void deleteExercise_when_exerciseNotExists_should_returnFalse() {
        Integer exerciseId = 12154;

        boolean result = exerciseRepository.deleteExercise(exerciseId);
        assertFalse(result);
    }
}
