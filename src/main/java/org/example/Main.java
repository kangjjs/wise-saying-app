package org.example;

import domain.WiseSaying;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static int id = 1;
    static Scanner sc;

    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");
        sc = new Scanner(System.in);
        id = 1;
        HashMap<Integer, WiseSaying> wiseBooks = new HashMap<>();

        while (true) {
            System.out.print("명령) ");
            String command = sc.nextLine();

            if (command.equals("종료")) {
                System.out.println("앱을 종료합니다.");
                break;
            } else if (command.equals("등록")) {
                save(wiseBooks);
            } else if (command.equals("목록")) {
                get(wiseBooks);
            } else if (command.startsWith("삭제")) {
                try {
                    delete(command, wiseBooks);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.startsWith("수정")) {
                try {
                    update(command, wiseBooks);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("알 수 없는 명령입니다.");
            }
        }
    }

    private static void save(HashMap<Integer, WiseSaying> wiseBooks) {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        wiseBooks.put(id, WiseSaying.of(id, content, author));
        System.out.println((id++) + "번 명언이 등록되었습니다.");
    }

    private static void get(HashMap<Integer, WiseSaying> wiseBooks) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (Integer wiseId : wiseBooks.keySet()) {
            WiseSaying wiseSaying = wiseBooks.get(wiseId);
            System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " + wiseSaying.getContent());
        }
    }

    private static void delete(String command, HashMap<Integer, WiseSaying> wiseBooks) {
        int extId = extractId(command);
        if (wiseBooks.containsKey(extId)) {
            wiseBooks.remove(extId);
            System.out.println(extId + "번 명언이 삭제되었습니다.");
        } else {
            throw new IllegalArgumentException(extId + "번 명언은 존재하지 않습니다.");
        }
    }

    private static void update(String command, HashMap<Integer, WiseSaying> wiseBooks) {
        int extId = extractId(command);
        if (wiseBooks.containsKey(extId)) {
            WiseSaying wiseSaying = wiseBooks.get(extId);
            System.out.println("명언(기존) : " + wiseSaying.getContent());
            System.out.print("명언 : ");
            String content = sc.nextLine();
            System.out.println("작가(기존) : " + wiseSaying.getAuthor());
            System.out.print("작가 : ");
            String author = sc.nextLine();
            wiseSaying.setContent(content);
            wiseSaying.setAuthor(author);
            System.out.println(extId + "번 명언이 수정되었습니다.");
        } else {
            throw new IllegalArgumentException(extId + "번 명언은 존재하지 않습니다.");
        }
    }

    private static int extractId(String command) {
        try {
            return Integer.parseInt(command.split("=")[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 명령입니다.");
        }
    }
}
