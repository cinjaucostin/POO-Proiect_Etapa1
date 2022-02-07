package output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class WriteOutput {
    /*
        Clasa folosita pentru a forma un obiect de tip json asociat output-ului nostru.
    */
    private static ObjectMapper objectMapper = new ObjectMapper();

    /***
     * Constructor definit pentru coding-style.
     */
    private WriteOutput() {
    }

    /***
     * Metoda care transforma un obiect
     * @param a o instanta a unui obiect oarecare
     * @return JsonNode-ul asociat obiectului primit ca parametru.
     */
    public static JsonNode toJson(final Object a) {
        return objectMapper.valueToTree(a);
    }

    /***
     * Metoda care transforma un obiect de tip JsonNode intr-un String.
     * @param node obiectul de tip JsonNode primit ca parametru pe care trebuie sa il
     *             transformam intr-un String(pe care ulterior il vom scrie in fisierul json).
     * @return  String-ul obtinut in urma transformarii.
     * @throws JsonProcessingException exceptia pe care o putem primii in cazul unei erori
     *                                 de procesare a obiectului JsonNode
     */
    public static String stringify(final JsonNode node) throws JsonProcessingException {
        /*
            Cream un ObjectWriter folosindu-ne de metoda writer specifica obiectelor
         de tip ObjectMapper
         */
        ObjectWriter objectWriter = objectMapper.writer();
        /*
            Definim o particularitate a writer-ului nostru(vrem ca in fisierul json sa avem
         output-ul pe mai multe linii, nu doar pe una singura).
         */
        objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        /*
            Returnam string-ul asociat obiectului de tip JsonNode si format cu ajutorul
         metodei writeValueAsString().
         */
        return objectWriter.writeValueAsString(node);
    }

    /***
     *
     * @param content String-ul pe care trebuie sa il scriem in fisierul de output.
     * @param outputPath Path-ul catre fisierul in care dorim sa scriem.
     * @throws IOException In cazul in care apare o eroare in timpul scrierii in fisier.
     */
    public static void writeStringToFile(final String content, final String outputPath)
            throws IOException {
        /*
            Cream un obiect de tip File, care reprezinta fisierul in care vom scrie.
         */
        File outputFile = new File(outputPath);
        /*
            Definim un obiect de tip FileWriter cu ajutorul caruia vom scrie String-ul
         primit ca si parametru in fisierul creat anterior.
         */
        FileWriter fileWriter = new FileWriter(outputFile);
        /*
            Ne folosim de metoda write() a obiectului FileWriter pentru a scrie in fisierul
         definit String-ul primit ca parametru.
         */
        fileWriter.write(content);
        /*
            Inchidem fileWriter-ul.
         */
        fileWriter.close();
    }

}
