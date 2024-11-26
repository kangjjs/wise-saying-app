package service;

import domain.WiseSaying;
import repository.WiseSayingRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class WiseSayingService {
    private final WiseSayingRepositoryImpl wiseSayingRepositoryImpl;

    public WiseSayingService() {
        this.wiseSayingRepositoryImpl = new WiseSayingRepositoryImpl();
    }

    public WiseSaying add(String content, String author) {
        WiseSaying wiseSaying = WiseSaying.of(0, content, author);
        wiseSayingRepositoryImpl.add(wiseSaying);

        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        return wiseSayingRepositoryImpl.findAll();
    }

    public boolean removeById(int id) {
        return wiseSayingRepositoryImpl.removeById(id);
    }

    public Optional<WiseSaying> findById(int id) {
        return wiseSayingRepositoryImpl.findById(id);
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);

        wiseSayingRepositoryImpl.modify(wiseSaying);
    }
}