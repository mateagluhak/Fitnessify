package fer.infsus.fitnessify.integration;

import fer.infsus.fitnessify.AbstractTestIT;
import fer.infsus.fitnessify.interfaces.controller.ExerciseController;
import org.springframework.beans.factory.annotation.Autowired;

public class ExerciseTestIT extends AbstractTestIT {
    @Autowired
    private ExerciseController controller;
}
