package main;

import checker.Checker;
import com.fasterxml.jackson.databind.JsonNode;
import common.Constants;
import database.Database;
import input.Input;
import input.InputLoader;
import output.Output;
import output.WriteOutput;
import org.json.simple.parser.ParseException;
import santa.Santa;
import simulation.Simulator;
import utils.Utils;

import java.io.IOException;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }

    /**
     * This method is used to call the checker which calculates the score
     *
     * @param args the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException, ParseException {
        for (int i = 1; i <= Constants.TESTS_NUMBER; i++) {
            /*
                Cream un obiect de tip InputLoader cu ajutorul caruia vom parsa din fisierul
             de intrare json.
             */
            InputLoader inputLoader = new InputLoader(Constants.INPUT_PATH
                    + i + Constants.FILE_EXTENSION);
            /*
                Introducem toate datele obtinute in urma parsarii din json in InputLoader
            intr-un obiect de tip Input prin intermediul metodei definite in acest scop:readData.
             */
            Input input = inputLoader.readData();

            /*
                Initializam Database-ul cu toate datele necesare, obtinute in input prin parsarea
             din fisierul de intrare de tip json.
             */
            Database.getDatabase().addNumberOfYears(input.getNumberOfYears());
            Database.getDatabase().addSantaBudget(input.getSantaBudget());
            Database.getDatabase().addChildrens(input.getInitialData().getChildren());
            Database.getDatabase().addSantaGiftsList(input.getInitialData().getSantaGiftsList());
            Database.getDatabase().addAnnualChanges(input.getAnnualChanges());

            /*
                Cream un obiect de tip Santa caruia ii atribuim campurile corespunzatoare(bugetul,
            lista de copii si lista de cadouri disponibile) prin intermediul constructorului.
             */
            Santa santa = new Santa(Database.getDatabase().findSantaBudget(),
                    Database.getDatabase().findAllChildrens(),
                    Database.getDatabase().findAllSantaGifts());
            /*
                Adaugam pentru fiecare copil primul niceScore in istoricul fiecaruia.
             */
            Utils.addFirstNiceScoreToHistoryForChilds(Database.getDatabase().findAllChildrens());

            /*
                Cream un nou obiect de tip simulator, caruia ii setam campul cu numarul de ani
             pentru care trebuie sa faca o simulare cu cel obtinut din baza de date definita.
             */
            Simulator simulator = new Simulator(santa, Database.getDatabase().findNumberOfYears());
            /*
                Pentru a executa prima runda.
             */
            simulator.justExec();
            /*
                Pentru a executa urmatoarele nrOfYears runde.
             */
            simulator.updateAndExec();

            /*
                Scriem output-ul obtinut intr-un fisier cu numele specific testului curent.
             */
            Output output = simulator.getOutput();
            JsonNode node = WriteOutput.toJson(output);
            WriteOutput.writeStringToFile(WriteOutput.stringify(node), Constants.OUTPUT_PATH
                    + i + Constants.FILE_EXTENSION);

            /*
                Golim baza de date pentru a o putea folosi si la testul urmator.
            */
            Database.getDatabase().findAllAnnualChanges().clear();
            Database.getDatabase().findAllChildrens().clear();
            Database.getDatabase().findAllSantaGifts().clear();

        }
        Checker.calculateScore();
    }

}
