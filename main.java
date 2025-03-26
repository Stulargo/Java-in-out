import java.io.*;
import java.util.*;

// Klasa reprezentująca pojedynczy wiersz danych z dwoma wartościami
class Dane {
    final int v1, v2; // v1 - pierwsza wartość (kolumna 1), v2 - druga wartość (kolumna 2)

    // Konstruktor tworzący obiekt Dane z dwóch wartości
    Dane(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    // Metoda obliczająca sumę wartości w wierszu
    int sum() { return v1 + v2; }

    // Metoda określająca, która wartość jest większa: 1 (v1), 2 (v2) lub 0 (równe)
    int larger() { return v1 > v2 ? 1 : v2 > v1 ? 2 : 0; }

    // Nadpisywanie metody toString do formatowania danych w czytelny sposób
    @Override
    public String toString() {
        return v1 + "\t" + v2 + "\tSuma: " + sum() + "\tWiększa: " + larger();
    }
}

// Klasa obsługująca operacje na plikach (odczyt i zapis)
class FileHandler {
    // Metoda odczytująca dane z pliku i zwracająca listę obiektów Dane
    List<Dane> read(String file) throws IOException {
        List<Dane> list = new ArrayList<>(); // Lista do przechowywania obiektów Dane
        try (BufferedReader br = new BufferedReader(new FileReader(file))) { // Automatyczne zamknięcie strumienia
            String line;
            while ((line = br.readLine()) != null) { // Pętla odczytująca linie z pliku
                String[] parts = line.trim().split("\\s+"); // Podział linii na części (spacje/tabulacje)
                if (parts.length == 2) { // Sprawdzenie, czy linia ma dokładnie 2 wartości
                    list.add(new Dane(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]))); // Dodanie nowego obiektu Dane
                }
            }
        }
        return list; // Zwrócenie listy danych
    }

    // Metoda zapisująca listę obiektów Dane do pliku
    void write(String file, List<Dane> list) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) { // Automatyczne zamknięcie strumienia
            for (Dane d : list) { // Pętla po wszystkich obiektach Dane
                bw.write(d.toString()); // Zapisanie sformatowanego ciągu
                bw.newLine(); // Nowa linia po każdym wpisie
            }
        }
    }
}

// Klasa odpowiedzialna za analizę i wyświetlanie danych
class DataAnalyzer {
    // Metoda wyświetlająca dane w konsoli
    void display(List<Dane> list) {
        System.out.println("Wczytane dane:"); // Nagłówek
        list.forEach(System.out::println); // Wyświetlenie wszystkich obiektów Dane
    }

    // Metoda analizująca największą i najmniejszą wartość w całym zestawie danych
    void analyzeExtremes(List<Dane> list) {
        if (list.isEmpty()) return; // Zakończenie, jeśli lista jest pusta

        // Inicjalizacja zmiennych wartościami z pierwszego wiersza
        int max = list.get(0).v1, min = max, maxRow = 1, minRow = 1;
        String maxCol = "Kolumna 1", minCol = "Kolumna 1"; // Domyślne kolumny

        // Pętla po wszystkich wierszach w celu znalezienia ekstremów
        for (int i = 0; i < list.size(); i++) {
            Dane d = list.get(i); // Pobranie bieżącego obiektu
            int row = i + 1; // Numer wiersza (zaczynając od 1)

            // Sprawdzanie maksymalnej wartości dla v1
            if (d.v1 > max) { max = d.v1; maxCol = "Kolumna 1"; maxRow = row; }
            // Sprawdzanie maksymalnej wartości dla v2
            if (d.v2 > max) { max = d.v2; maxCol = "Kolumna 2"; maxRow = row; }
            // Sprawdzanie minimalnej wartości dla v1
            if (d.v1 < min) { min = d.v1; minCol = "Kolumna 1"; minRow = row; }
            // Sprawdzanie minimalnej wartości dla v2
            if (d.v2 < min) { min = d.v2; minCol = "Kolumna 2"; minRow = row; }
        }

        // Wyświetlenie wyników analizy
        System.out.println("\nAnaliza ekstremów:");
        System.out.println("Największa wartość: " + max + " (" + maxCol + ", wiersz " + maxRow + ")");
        System.out.println("Najmniejsza wartość: " + min + " (" + minCol + ", wiersz " + minRow + ")");
    }
}

// Główna klasa programu
public class Main {
    public static void main(String[] args) throws IOException {
        FileHandler fileHandler = new FileHandler(); // Obiekt do obsługi plików
        DataAnalyzer analyzer = new DataAnalyzer(); // Obiekt do analizy danych

        // Wykonanie operacji: odczyt, zapis, wyświetlenie i analiza
        List<Dane> daneList = fileHandler.read("input.txt"); // Odczyt danych z pliku
        fileHandler.write("output.txt", daneList); // Zapis danych do pliku
        analyzer.display(daneList); // Wyświetlenie danych w konsoli
        analyzer.analyzeExtremes(daneList); // Analiza ekstremów
        System.out.println("Operacja zakończona sukcesem."); // Potwierdzenie zakończenia
    }
}
