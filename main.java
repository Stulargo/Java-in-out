import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Klasa reprezentująca parę liczb (dla Input 1 i Input 3)
class Pair {
    private final int first;
    private final int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "Pair{first=" + first + ", second=" + second + "}";
    }
}

public class Main {
    // Wczytywanie danych z Input 1 (pary liczb)
    public static List<Pair> loadInput1(String filePath) {
        List<Pair> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 2) {
                    int first = Integer.parseInt(parts[0]);
                    int second = Integer.parseInt(parts[1]);
                    data.add(new Pair(first, second));
                }
            }
        } catch (IOException e) {
            System.err.println("Błąd wczytywania pliku " + filePath + ": " + e.getMessage());
        }
        return data;
    }

    // Wczytywanie danych z Input 2 (lista liczb o zmiennej długości)
    public static List<List<Integer>> loadInput2(String filePath) {
        List<List<Integer>> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                List<Integer> numbers = Arrays.stream(line.trim().split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                data.add(numbers);
            }
        } catch (IOException e) {
            System.err.println("Błąd wczytywania pliku " + filePath + ": " + e.getMessage());
        }
        return data;
    }

    // Wczytywanie danych z Input 3 (pary lub listy liczb oddzielone | lub ,)
    public static List<List<Integer>> loadInput3(String filePath) {
        List<List<Integer>> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts;
                if (line.contains("|")) {
                    parts = line.trim().split("\\|");
                } else {
                    parts = line.trim().split(",");
                }
                List<Integer> numbers = Arrays.stream(parts)
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                data.add(numbers);
            }
        } catch (IOException e) {
            System.err.println("Błąd wczytywania pliku " + filePath + ": " + e.getMessage());
        }
        return data;
    }

    public static void main(String[] args) {
        // Trzy różne inputy (pliki)
        String input1Path = "input1.txt";
        String input2Path = "input2.txt";
        String input3Path = "input3.txt";

        // Wczytanie danych
        List<Pair> data1 = loadInput1(input1Path);
        List<List<Integer>> data2 = loadInput2(input2Path);
        List<List<Integer>> data3 = loadInput3(input3Path);

        // Wyświetlenie wczytanych danych (opcjonalne)
        System.out.println("Dane z input1 (pierwsze 5): " + data1.subList(0, Math.min(5, data1.size())));
        System.out.println("Dane z input2 (pierwsze 5): " + data2.subList(0, Math.min(5, data2.size())));
        System.out.println("Dane z input3 (pierwsze 5): " + data3.subList(0, Math.min(5, data3.size())));

        // Output 1: Suma wszystkich wartości (first + second) z Input 1
        long sum1 = data1.stream()
                .mapToLong(pair -> pair.getFirst() + pair.getSecond())
                .sum();
        System.out.println("Output 1 (Suma z Input 1): " + sum1);

        // Output 2: Średnia wszystkich wartości z Input 2
        double average2 = data2.stream()
                .flatMapToInt(list -> list.stream().mapToInt(Integer::intValue))
                .average()
                .orElse(0.0);
        System.out.println("Output 2 (Średnia z Input 2): " + average2);

        // Output 3: Maksymalna wartość z każdej linii Input 3, a potem średnia tych maksimów
        double maxAverage3 = data3.stream()
                .mapToInt(list -> list.stream().mapToInt(Integer::intValue).max().orElse(Integer.MIN_VALUE))
                .average()
                .orElse(0.0);
        System.out.println("Output 3 (Średnia maksimów z Input 3): " + maxAverage3);
    }
}
