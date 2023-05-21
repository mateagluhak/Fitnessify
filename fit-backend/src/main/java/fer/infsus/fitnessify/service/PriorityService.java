package fer.infsus.fitnessify.service;

import fer.infsus.fitnessify.model.Priority;
import fer.infsus.fitnessify.repository.PriorityRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriorityService {
    @NonNull
    private PriorityRepository priorityRepository;

    public Priority getPriority(Integer priorityId) {
        return priorityRepository.getPriority(priorityId);
    }

    public List<Priority> getPriorities() {
        return priorityRepository.getPriorities();
    }
}
