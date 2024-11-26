package app;

import controller.WiseSayingController;

import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private final WiseSayingController wiseSayingController;

    public App() {
        scanner = new Scanner(System.in);
        wiseSayingController = new WiseSayingController(scanner);
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        wiseSayingController.loadSampleData();

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            switch (cmd) {
                case "종료" -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                case "등록" -> wiseSayingController.handleAdd();
                case "목록" -> wiseSayingController.handleList();
                default -> {
                    if (cmd.startsWith("삭제")) {
                        wiseSayingController.handleDelete(cmd);
                    } else if (cmd.startsWith("수정")) {
                        wiseSayingController.handleModify(cmd);
                    } else {
                        System.out.println("알 수 없는 명령입니다. 다시 시도해주세요.");
                    }
                }
            }
        }
    }
}