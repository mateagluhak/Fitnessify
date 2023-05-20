package fer.infsus.fitnessify.configuration;

import fer.infsus.fitnessify.repository.ExerciseRepository;
import fer.infsus.fitnessify.repository.WorkoutRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RepositoryConfiguration {
    @NonNull
    private DSLContext dslContext;

    @Bean
    public WorkoutRepository getWorkoutRepository() {
        return new WorkoutRepository(dslContext);
    }

    @Bean
    public ExerciseRepository getExerciseRepository() {
        return new ExerciseRepository(dslContext);
    }
}
