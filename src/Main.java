import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] results = {"3:1", "2:2", "0:1", "4:2", "3:a", "3- 12"};
        Map<String, Integer> teamScores = new HashMap<>();

        for (String result : results) {
            String[] parts = result.split(":");
            if (parts.length != 2) {
                System.out.println("Ошибка: некорректный формат результата матча: " + result);
                continue;
            }

            try {
                int homeGoals = Integer.parseInt(parts[0]);
                int awayGoals = Integer.parseInt(parts[1]);
                if (homeGoals < 0 || awayGoals < 0) {
                    System.out.println("Ошибка: отрицательное количество голов в результате матча: " + result);
                    continue;
                }

                if (!parts[0].matches("\\d+") || !parts[1].matches("\\d+")) {
                    System.out.println("Ошибка: результат матча содержит некорректные символы: " + result);
                    continue;
                }

                if (homeGoals > awayGoals) {
                    // Победа домашней команды
                    teamScores.put("Команда №1", teamScores.getOrDefault("Команда №1", 0) + 3);
                    teamScores.put("Команда №2", teamScores.getOrDefault("Команда №2", 0));
                } else if (homeGoals < awayGoals) {
                    // Победа гостевой команды
                    teamScores.put("Команда №1", teamScores.getOrDefault("Команда №1", 0));
                    teamScores.put("Команда №2", teamScores.getOrDefault("Команда №2", 0) + 3);
                } else {
                    // Ничья
                    teamScores.put("Команда №1", teamScores.getOrDefault("Команда №1", 0) + 1);
                    teamScores.put("Команда №2", teamScores.getOrDefault("Команда №2", 0) + 1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: некорректный формат числа в результате матча: " + result);
            }
        }

        // Выводим результаты
        for (Map.Entry<String, Integer> entry : teamScores.entrySet()) {
            System.out.println(entry.getKey() + " набрала " + entry.getValue() + " очков.");
        }
    }
}
