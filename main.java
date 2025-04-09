import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    // Funkcja wczytuje liczby z pliku
    public static int[] loadNumbers(String filePath) {
        int[] numbers = new int[1000]; // Tablica na max 1000 liczb
        int count = 0; // Licznik liczb
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath)); // Otwiera plik
            String line; // Zmienna na linijkę
            while ((line = reader.readLine()) != null) { // Czyta każdą linijkę
                if (line.trim().isEmpty()) continue; // Pomija puste linie
                String[] parts; // Tablica na liczby
                if (line.contains("|")) { // Jeśli linijka ma |
                    parts = line.trim().split("\\|"); // Dzieli po |
                } else if (line.contains(",")) { // Jeśli linijka ma ,
                    parts = line.trim().split(","); // Dzieli po ,
                } else { // Jeśli ani |, ani ,
                    parts = line.trim().split("\\s+"); // Dzieli po spacji
                }
                for (int i = 0; i < parts.length; i++) { // Przechodzi przez liczby
                    numbers[count] = Integer.parseInt(parts[i].trim()); // Dodaje liczbę
                    count++; // Zwiększa licznik
                }
            }
            reader.close(); // Zamyka plik
        } catch (Exception e) { // Jeśli coś pójdzie nie tak
            System.out.println("Błąd pliku: " + filePath); // Wyświetla błąd
        }
        int[] result = new int[count]; // Tworzy tablicę o dokładnym rozmiarze
        for (int i = 0; i < count; i++) { // Kopiuje liczby
            result[i] = numbers[i];
        }
        return result; // Zwraca tablicę liczb
    }

    public static void main(String[] args) { // Start programu
        // Ścieżki do plików
        String input1 = "input1.txt"; // Plik 1
        String input2 = "input2.txt"; // Plik 2
        String input3 = "input3.txt"; // Plik 3

        // Wczytanie liczb z każdego pliku
        int[] numbers1 = loadNumbers(input1); // Liczby z pliku 1
        int[] numbers2 = loadNumbers(input2); // Liczby z pliku 2
        int[] numbers3 = loadNumbers(input3); // Liczby z pliku 3

        // Output 1: suma liczb z pliku 1
        int sum1 = 0; // Zmienna na sumę
        for (int i = 0; i < numbers1.length; i++) { // Przechodzi przez liczby
            sum1 += numbers1[i]; // Dodaje każdą liczbę
        }
        System.out.println("Suma liczb z pliku 1: " + sum1); // Wyświetla sumę

        // Output 2: suma liczb z pliku 2
        int sum2 = 0; // Zmienna na sumę
        for (int i = 0; i < numbers2.length; i++) { // Przechodzi przez liczby
            sum2 += numbers2[i]; // Dodaje każdą liczbę
        }
        System.out.println("Suma liczb z pliku 2: " + sum2); // Wyświetla sumę

        // Output 3: suma liczb z pliku 3
        int sum3 = 0; // Zmienna na sumę
        for (int i = 0; i < numbers3.length; i++) { // Przechodzi przez liczby
            sum3 += numbers3[i]; // Dodaje każdą liczbę
        }
        System.out.println("Suma liczb z pliku 3: " + sum3); // Wyświetla sumę
    }
}
