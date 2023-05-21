package fer.infsus.fitnessify.interfaces.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fer.infsus.fitnessify.domain.model.Exercise;
import fer.infsus.fitnessify.enums.MuscleGroup;
import fer.infsus.fitnessify.infrastructure.service.ExerciseService;
import fer.infsus.fitnessify.interfaces.dto.ExerciseDto;
import fer.infsus.fitnessify.interfaces.mapper.ExerciseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class ExerciseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ExerciseService exerciseService;

    @InjectMocks
    private ExerciseController exerciseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(exerciseController).build();
    }

    @Test
    void getExercise_shouldReturnExerciseDto() throws Exception {
        Exercise exercise = new Exercise(1,"vjezba", MuscleGroup.back,120.0);

        when(exerciseService.getExercise(anyInt())).thenReturn(exercise);

        mockMvc.perform(get("/exercise/{exercise_id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("vjezba"));
    }

    @Test
    void getExercises_shouldReturnListOfExerciseDto() throws Exception {
        Exercise exercise1 = new Exercise(1,"vjezba1", MuscleGroup.back,120.0);
        Exercise exercise2 = new Exercise(2,"vjezba2", MuscleGroup.upper_body,100.0);

        List<Exercise> exercises = Arrays.asList(exercise1, exercise2);

        when(exerciseService.getExercises()).thenReturn(exercises);

        mockMvc.perform(get("/exercises"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("vjezba1"))
                .andExpect(jsonPath("$[0].muscleGroup").value("back"))
                .andExpect(jsonPath("$[0].maxWeight").value(120.0))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("vjezba2"))
                .andExpect(jsonPath("$[1].muscleGroup").value("upper_body"))
                .andExpect(jsonPath("$[1].maxWeight").value(100.0));
    }

    @Test
    void createExercise_withValidExerciseDto_shouldReturnCreatedExerciseDto() throws Exception {
        ExerciseDto exerciseDto = new ExerciseDto("vjezba","back",100.00);

        Exercise createdExercise = new Exercise(1,"vjezba",MuscleGroup.back,100.00);

        when(exerciseService.createExercise(any(ExerciseDto.class))).thenReturn(createdExercise);

        mockMvc.perform(post("/exercise/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(exerciseDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("vjezba"))
                .andExpect(jsonPath("$.muscleGroup").value("back"))
                .andExpect(jsonPath("$.maxWeight").value(100.0));
    }

    @Test
    void editExercise_withValidExerciseDto_shouldReturnUpdatedExerciseDto() throws Exception {
        ExerciseDto exerciseDto = new ExerciseDto("updated","upper_body",100.00);

        Exercise updatedExercise = new Exercise(12,"updated",MuscleGroup.upper_body,100.00);

        when(exerciseService.editExercise(anyInt(), any(ExerciseDto.class))).thenReturn(updatedExercise);

        mockMvc.perform(put("/exercise/edit/{exercise_id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(exerciseDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(12))
                .andExpect(jsonPath("$.name").value("updated"))
                .andExpect(jsonPath("$.muscleGroup").value("upper_body"))
                .andExpect(jsonPath("$.maxWeight").value(100.0));
    }

    @Test
    void deleteExercise_withValidExerciseId_shouldReturnOkStatus() throws Exception {
        when(exerciseService.deleteExercise(anyInt())).thenReturn(true);

        mockMvc.perform(delete("/exercise/{exercise_id}", 1))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
