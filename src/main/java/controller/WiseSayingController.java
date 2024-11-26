package controller;

import domain.WiseSaying;
import service.WiseSayingService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class WiseSayingController {
    private final WiseSayingService wiseSayingService;
    private final Scanner scanner;

    public WiseSayingController(Scanner scanner) {
        this.wiseSayingService = new WiseSayingService();
        this.scanner = scanner;
    }

    public void loadSampleData() {
        wiseSayingService.add("나 자신을 알라.", "소크라테스");
        wiseSayingService.add("삶은 선택의 연속이다.", "알버트 아인슈타인");
    }

    public void handleAdd() {
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = wiseSayingService.add(content, author);
        System.out.printf("%d번 명언이 등록되었습니다.%n", wiseSaying.getId());
    }

    public void handleList() {
        List<WiseSaying> wiseSayings = wiseSayingService.findAll();

        if (wiseSayings.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
            return;
        }

        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying = wiseSayings.get(i);
            System.out.printf("%d / %s / %s%n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }

    public void handleDelete(String cmd) {
        int id = extractIdFromCommand(cmd, "삭제");

        if (id == -1) return;

        boolean success = wiseSayingService.removeById(id);
        if (success) {
            System.out.printf("%d번 명언이 삭제되었습니다.%n", id);
        } else {
            System.out.printf("%d번 명언은 존재하지 않습니다.%n", id);
        }
    }

    public void handleModify(String cmd) {
        int id = extractIdFromCommand(cmd, "수정");

        if (id == -1) return;

        Optional<WiseSaying> foundWiseSaying = wiseSayingService.findById(id);

        if (foundWiseSaying.isEmpty()) {
            System.out.printf("%d번 명언은 존재하지 않습니다.%n", id);
            return;
        }

        WiseSaying wiseSaying = foundWiseSaying.get();

        System.out.printf("명언(기존) : %s%n", wiseSaying.getContent());
        System.out.print("명언 : ");
        String newContent = scanner.nextLine().trim();

        System.out.printf("작가(기존) : %s%n", wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String newAuthor = scanner.nextLine().trim();

        wiseSayingService.modify(wiseSaying, newContent, newAuthor);
        System.out.printf("%d번 명언이 수정되었습니다.%n", id);
    }

    private int extractIdFromCommand(String cmd, String action) {
        try {
            return Integer.parseInt(cmd.substring(action.length()).trim());
        } catch (NumberFormatException e) {
            System.out.println("올바른 ID를 입력해주세요.");
            return -1;
        }
    }
}
