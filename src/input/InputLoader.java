package input;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InputLoader {
    private final String inputPath;

    public InputLoader(final String path) {
        this.inputPath = path;
    }

    /***
     *
     * @return intoarce un obiect de tip Input ce contine toate informatiile din fisierul
     * .json din care am parsat.
     * @throws IOException in cazul in care nu se poate citi din fisier.
     * @throws ParseException in cazul in care nu se poate parsa ceea ce se citeste din fisier.
     */
    public Input readData() throws IOException, ParseException {
        /*
            Definim un obiect de tip JsonParser cu ajutorul caruia vom parsa
         din fisierul de intrare.
         */
        JSONParser jsonParser = new JSONParser();
        /*
            Definim un obiect de tip JSONObject in care vom retine rezultatul parsarii
         din fisierul de input.
         */
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(inputPath));
        /*
            Definim un obiect de tip ObjectMapper cu ajutorul caruia vom extrage toate
         informatiile din String-ul asociat JSONObject-ului si le vom introduce intr-o
         instanta a clasei Input.
         */
        ObjectMapper mapper = new ObjectMapper();
        /*
            Transformam rezultatul parsarii de mai devreme(un JSONObject) intr-un String.
         */
        String jsonInput = jsonObject.toString();

        Input input = null;
        try {
            /*
                Introducem informatiile din jsonInput in instanta input a unui obiect de tip Input.
             */
            input = mapper.readValue(jsonInput, Input.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        /*
            Intoarcem obiectul obtinut in urma parsarii din fisierul json.
         */
        return input;
    }


}
