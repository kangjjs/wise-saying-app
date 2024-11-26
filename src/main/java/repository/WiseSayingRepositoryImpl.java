package repository;

import domain.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WiseSayingRepositoryImpl implements WiseSayingRepository {

    private final List<WiseSaying> wiseSayings;
    private int id;

    public WiseSayingRepositoryImpl() {
        this.wiseSayings = new ArrayList<>();
        this.id = 1;
    }

    @Override
    public void add(WiseSaying wiseSaying) {
        wiseSaying.setId(id++);
        wiseSayings.add(wiseSaying);
    }

    @Override
    public List<WiseSaying> findAll() {
        return new ArrayList<>(wiseSayings);
    }

    @Override
    public Optional<WiseSaying> findById(int id) {
        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst();
    }

    @Override
    public boolean removeById(int id) {
        Optional<WiseSaying> wiseSaying = findById(id);
        if (wiseSaying.isPresent()) {
            wiseSayings.remove(wiseSaying.get());
            return true;
        }
        return false;
    }

    @Override
    public void modify(WiseSaying wiseSaying) {
        wiseSayings.stream()
                .filter(existingWiseSaying -> existingWiseSaying.getId() == wiseSaying.getId())
                .findFirst()
                .ifPresent(existingWiseSaying -> {
                    existingWiseSaying.setContent(wiseSaying.getContent());
                    existingWiseSaying.setAuthor(wiseSaying.getAuthor());
                });
    }
}
