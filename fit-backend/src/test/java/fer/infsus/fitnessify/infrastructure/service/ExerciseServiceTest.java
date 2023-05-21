package fer.infsus.fitnessify.infrastructure.service;

import fer.infsus.fitnessify.domain.model.Exercise;
import fer.infsus.fitnessify.domain.repository.ExerciseRepository;
import fer.infsus.fitnessify.domain.repository.PriorityRepository;
import fer.infsus.fitnessify.enums.MuscleGroup;
import fer.infsus.fitnessify.infrastructure.validation.Validator;
import fer.infsus.fitnessify.infrastructure.validation.exception.InvalidMuscleGroupException;
import fer.infsus.fitnessify.interfaces.dto.ExerciseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExerciseServiceTest {
    private ExerciseService exerciseService;
    private Validator validator;
    @Mock
    private ExerciseRepository exerciseRepository;
    @Mock
    private PriorityRepository priorityRepository;

    @BeforeEach
    void setUp() {
        validator = new Validator(exerciseRepository, priorityRepository);
        exerciseService = new ExerciseService(validator, exerciseRepository);
    }

    @Test
    void getExercise_when_exerciseExists_should_returnExercise() {
        Integer exerciseId = 1;
        Exercise mockExercise = new Exercise(1, "name", MuscleGroup.upper_body, 10.0);
        Exercise expectedResult = new Exercise(1, "name", MuscleGroup.upper_body, 10.0);
        when(exerciseRepository.getExerciseById(exerciseId)).thenReturn(mockExercise);

        Exercise result = exerciseService.getExercise(exerciseId);
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    void getExercise_when_exerciseNotExists_should_returnNull() {
        Integer exerciseId = 5210521;
        when(exerciseRepository.getExerciseById(exerciseId)).thenReturn(null);

        Exercise result = exerciseService.getExercise(exerciseId);
        assertThat(result, equalTo(null));
    }

    @Test
    void getExercises_should_returnAllExercises() {
        Exercise exercise1 = new Exercise(1, "name1", MuscleGroup.upper_body, 10.0);
        Exercise exercise2 = new Exercise(2, "name2", MuscleGroup.lower_body, 11.0);
        Exercise exercise3 = new Exercise(3, "name3", MuscleGroup.back, 12.0);
        List<Exercise> allExercises = List.of(exercise1, exercise2, exercise3);
        when(exerciseRepository.getExercises()).thenReturn(allExercises);

        List<Exercise> result = exerciseService.getExercises();
        assertThat(result.size(), equalTo(3));
        assertThat(result, contains(exercise1, exercise2, exercise3));
    }

    @Test
    void createExercise_when_validExercise_should_returnExercise() {
        ExerciseDto inputExerciseDto = new ExerciseDto("name", "upper_body", 10.0);
        Exercise mockExercise = new Exercise(1, "name", MuscleGroup.upper_body, 10.0);
        Exercise expectedResult = new Exercise(1, "name", MuscleGroup.upper_body, 10.0);
        when(exerciseRepository.createExercise(inputExerciseDto)).thenReturn(mockExercise);

        Exercise result = exerciseService.createExercise(inputExerciseDto);
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    void createExercise_when_invalidMuscleGroup_should_throwInvalidMuscleGroupException() {
        ExerciseDto inputExerciseDto = new ExerciseDto("name", "fsasca", 10.0);

        assertThrows(InvalidMuscleGroupException.class,
                () -> exerciseService.createExercise(inputExerciseDto),
                "Invalid muscle group fsasca: Muscle groups are upper body, lower body and back");
    }

    @Test
    void createExercise_when_notNormalizedMuscleGroup_should_returnExercise() {
        ExerciseDto inputExerciseDto = new ExerciseDto("name", "Upper body", 10.0);
        ExerciseDto normalizedInputExerciseDto = new ExerciseDto("name", "upper_body", 10.0);
        Exercise mockExercise = new Exercise(1, "name", MuscleGroup.upper_body, 10.0);
        Exercise expectedResult = new Exercise(1, "name", MuscleGroup.upper_body, 10.0);
        when(exerciseRepository.createExercise(normalizedInputExerciseDto)).thenReturn(mockExercise);

        Exercise result = exerciseService.createExercise(inputExerciseDto);
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    void editExercise_when_validExercise_should_returnExercise() {
        Integer exerciseId = 1;
        ExerciseDto inputExerciseDto = new ExerciseDto("name", "upper_body", 10.0);
        Exercise mockExercise = new Exercise(1, "name", MuscleGroup.upper_body, 10.0);
        Exercise expectedResult = new Exercise(1, "name", MuscleGroup.upper_body, 10.0);
        when(exerciseRepository.editExercise(exerciseId, inputExerciseDto)).thenReturn(mockExercise);

        Exercise result = exerciseService.editExercise(exerciseId, inputExerciseDto);
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    void editExercise_when_invalidMuscleGroup_should_throwInvalidMuscleGroupException() {
        Integer exerciseId = 1;
        ExerciseDto inputExerciseDto = new ExerciseDto("name", "fsasca", 10.0);

        assertThrows(InvalidMuscleGroupException.class,
                () -> exerciseService.editExercise(exerciseId, inputExerciseDto),
                "Invalid muscle group fsasca: Muscle groups are upper body, lower body and back");
    }

    @Test
    void editExercise_when_notNormalizedMuscleGroup_should_returnExercise() {
        Integer exerciseId = 1;
        ExerciseDto inputExerciseDto = new ExerciseDto("name", "Upper body", 10.0);
        ExerciseDto normalizedInputExerciseDto = new ExerciseDto("name", "upper_body", 10.0);
        Exercise mockExercise = new Exercise(1, "name", MuscleGroup.upper_body, 10.0);
        Exercise expectedResult = new Exercise(1, "name", MuscleGroup.upper_body, 10.0);
        when(exerciseRepository.editExercise(exerciseId, normalizedInputExerciseDto)).thenReturn(mockExercise);

        Exercise result = exerciseService.editExercise(exerciseId, inputExerciseDto);
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    void deleteExercise_when_exerciseExists_should_returnTrue() {
        Integer exerciseId = 1;
        when(exerciseRepository.deleteExercise(exerciseId)).thenReturn(true);

        boolean result = exerciseService.deleteExercise(exerciseId);
        assertTrue(result);
    }

    @Test
    void deleteExercise_when_exerciseNotExists_should_returnFalse() {
        Integer exerciseId = 155629108;
        when(exerciseRepository.deleteExercise(exerciseId)).thenReturn(false);

        boolean result = exerciseService.deleteExercise(exerciseId);
        assertFalse(result);
    }
}
