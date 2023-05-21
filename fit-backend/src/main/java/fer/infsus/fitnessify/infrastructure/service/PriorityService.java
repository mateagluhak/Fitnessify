package fer.infsus.fitnessify.infrastructure.service;

import fer.infsus.fitnessify.domain.model.Priority;
import fer.infsus.fitnessify.domain.repository.PriorityRepository;
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
