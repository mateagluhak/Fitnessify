package fer.infsus.fitnessify.integration;

import fer.infsus.fitnessify.AbstractTestIT;
import fer.infsus.fitnessify.domain.model.Exercise;
import fer.infsus.fitnessify.enums.MuscleGroup;
import fer.infsus.fitnessify.interfaces.controller.ExerciseController;
import fer.infsus.fitnessify.interfaces.dto.ExerciseDto;
import fer.infsus.fitnessify.interfaces.mapper.ExerciseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@AutoConfigureMockMvc
public class ExerciseTestIT extends AbstractTestIT {
    @Autowired
    private ExerciseController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getExercise_when_exerciseExists() {
        Integer exerciseId = 1;
        ExerciseDto expectedResult = new ExerciseDto(1, "Bench press", "upper_body", 80.0);
        ResponseEntity<ExerciseDto> response = controller.getExercise(exerciseId);

        assertThat(response.getStatusCode(), equalTo(HttpStatusCode.valueOf(200)));
        assertThat(response.getBody(), equalTo(expectedResult));
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

        List<ExerciseDto> expectedResult = List.of(exercise1, exercise2, exercise3, exercise4, exercise5, exercise6,
                exercise7, exercise8, exercise9, exercise10, exercise11, exercise12,
                exercise13, exercise14, exercise15, exercise16, exercise17).stream().map(ExerciseMapper::toDto).toList();

        ResponseEntity<List<ExerciseDto>> response = controller.getExercises();
        assertThat(response.getStatusCode(), equalTo(HttpStatusCode.valueOf(200)));
        assertThat(response.getBody(), equalTo(expectedResult));
    }
}
