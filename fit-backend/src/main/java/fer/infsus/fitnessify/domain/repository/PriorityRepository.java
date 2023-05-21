package fer.infsus.fitnessify.domain.repository;

import fer.infsus.fitnessify.domain.model.Priority;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.util.List;

import static fer.infsus.fitnessify.Tables.PRIORITY;

@RequiredArgsConstructor
public class PriorityRepository {
    @NonNull
    private DSLContext dslContext;

    public Priority getPriority(Integer priorityId) {
        return dslContext.selectFrom(PRIORITY).where(PRIORITY.ID.eq(priorityId)).fetchSingleInto(Priority.class);
    }

    public List<Priority> getPriorities() {
        return dslContext.selectFrom(PRIORITY).fetchInto(Priority.class);
    }
}
