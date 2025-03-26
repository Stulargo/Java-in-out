import java.io.*;
import java.util.*;

public class Main {
    private static final String INPUT_FILE = "input.txt";  // Stała dla pliku wejściowego
    private static final String OUTPUT_FILE = "output.txt";  // Stała dla pliku wyjściowego

    public static void main(String[] args) {
        try {
            // Odczytanie danych z pliku i zapisanie ich jako obiekty Dane
            List<Dane> daneList = readFromFile(INPUT_FILE);

            // Zapisanie obiektów Dane do pliku wyjściowego
            writeToFile(OUTPUT_FILE, daneList);

            // Wyświetlanie obiektów w konsoli
            displayInConsole(daneList);

            System.out.println("Operacja zakończona sukcesem.");
        } catch (IOException e) {
            System.out.println("Błąd przy odczycie lub zapisie pliku: " + e.getMessage());
        }
    }

    // Klasa do przechowywania danych z id i wartości
    public static class Dane {
        private static int idCounter = 1;  // Licznik id
        private final int id;
        private final int value;

        // Konstruktor
        public Dane(int value) {
            this.id = idCounter++;  // Przypisanie unikalnego id
            this.value = value;
        }

        // Gettery
        public int getId() {
            return id;
        }

        public int getValue() {
            return value;
        }

        // Metoda toString, aby łatwiej zapisać dane do pliku
        @Override
        public String toString() {
            return "ID: " + id + ", Value: " + value;
        }
    }

    // Metoda odczytująca dane z pliku i zapisująca je jako obiekty Dane
    private static List<Dane> readFromFile(String inputFile) throws IOException {
        List<Dane> daneList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("[,\\s]+"); // Obsługuje spacje i przecinki jako separator
                for (String part : parts) {
                    try {
                        int number = Integer.parseInt(part);
                        daneList.add(new Dane(number));
                    } catch (NumberFormatException e) {
                        System.out.println("Błąd formatu liczby: " + part);
                    }
                }
            }
        }
        return daneList;
    }

    // Metoda zapisująca dane z List<Dane> do pliku
    private static void writeToFile(String outputFile, List<Dane> daneList) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (Dane dane : daneList) {
                writer.write(dane.toString());
                writer.newLine();
            }
        }
    }

    // Metoda wyświetlająca dane w konsoli
    private static void displayInConsole(List<Dane> daneList) {
        System.out.println("Wczytane dane:");
        daneList.forEach(System.out::println);  // Użycie strumieni zamiast pętli
    }
}
