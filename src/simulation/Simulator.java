package simulation;

import changes.AnnualChange;
import child.Child;
import output.ChildOutput;
import child.ChildrenUpdate;
import database.Database;
import output.AnnualOutput;
import output.Output;
import santa.Santa;
import utils.Utils;

import java.util.ArrayList;

public final class Simulator implements Simulation {
    private final Santa santa;
    private final Output output = new Output();
    private int nrOfYears;

    /***
     *
     * @param santa obiectul de tip Santa pe care il vom introduce in campul corespunzator
     * @param nrOfYears numarul de ani pentru care vom face simulari.
     */
    public Simulator(final Santa santa, final int nrOfYears) {
        this.santa = santa;
        this.nrOfYears = nrOfYears;
    }

    @Override
    public void justExec() {
        /*
            Sortam lista de copii crescator dupa id.
         */
        Utils.sortChildsById(santa.getChildrens());
        /*
            In continuare vom urma toti pasii unei simulari, asa cum apar in cerinta:
            1. Calculam categoria de varsta pentru fiecare copil.
         */
        santa.calculateAgeCategoryForEveryChild();
        /*
            2. Eliminam din lista pe cei care au categoria de varsta YOUNG_ADULT(au varsta
            mai mare de 18 ani).
         */
        santa.removeYoungAdults();
        /*
            3. Calculam media pentru fiecare copil.
         */
        santa.calculateNiceScoreAverageForEveryChild();
        /*
            4. Calculam budgetUnit-ul.
         */
        santa.calculateBudgetUnit();
        /*
            5. Calculam bugetul alocat fiecarui copil.
         */
        santa.calculateAllocatedBudgetForEveryChild();

        /*
            Fiecare copil il primeste pe Mos Craciun.
         */
        for (Child child : Database.getDatabase().findAllChildrens()) {
            child.accept(santa);
        }

        /*
            Cream un obiect de tip AnnualOutput care va fi scris in fisierul de output si care
         reprezinta o lista de obiecte ChildOutput ce contin informatii obtinute in urma simularii
         rundei curente.
         */
        AnnualOutput annualOutput = new AnnualOutput();
        for (Child child : santa.getChildrens()) {
            /*
                Pentru fiecare copil din lista realizam un obiect de tip ChildOutput care
             sa contina informatiile necesare si pe care il adaugam la lista din AnnualOutput.
             */
            if (child.getReceivedGifts() != null) {
                /*
                    Cazul in care copilul a primit cadouri.
                */
                annualOutput.getChildren().add(new ChildOutput(
                        child.getId(), child.getLastName(), child.getFirstName(),
                        Utils.stringToCities(child.getCity()), child.getAge(),
                        Utils.stringListToCategoryList(child.getGiftsPreferences()),
                        child.getAverageScore(), new ArrayList<>(child.getNiceScoreHistory()),
                        child.getAssignedBudget(), new ArrayList<>(child.getReceivedGifts())
                ));
            } else {
                /*
                    Cazul in care copilul nu a primit niciun cadou(receivedGifts va fi o lista goala
                 si constructorul nu ne poate crea un ArrayList<>(null) deci ii vom da ca parametru
                 un new ArrayList<>().
                    Din acest motiv am fost nevoit sa tratez 2 cazuri(cand copilul a primit cadouri
                 si cand acesta nu a primit niciun cadou.
                 */
                annualOutput.getChildren().add(new ChildOutput(
                        child.getId(), child.getLastName(), child.getFirstName(),
                        Utils.stringToCities(child.getCity()), child.getAge(),
                        Utils.stringListToCategoryList(child.getGiftsPreferences()),
                        child.getAverageScore(), new ArrayList<>(child.getNiceScoreHistory()),
                        child.getAssignedBudget(), new ArrayList<>()
                ));
            }
        }

        /*
            Adaugam un output, lista cu copii dupa realizarea simularii.
         */
        output.getAnnualChildren().add(annualOutput);

        /*
            Deoarece am terminat simularea(a trecut anul) fiecare copil va creste cu un an.
         */
        for (Child child : santa.getChildrens()) {
            child.grow();
        }

        /*
            Golim lista de cadouri primite a fiecarui copil in parte
         */
        Utils.clearReceivedGiftsListForEveryChild(santa.getChildrens());

        /*
            Am realizat simularea pentru un an si marcam acest lucru.
         */
        nrOfYears--;

    }

    @Override
    public void updateAndExec() {
        /*
            Vom lua fiecare annualChange in parte din lista si vom vedea ce presupune fiecare.
         */
        for (AnnualChange annualChange : Database.getDatabase().findAllAnnualChanges()) {
            /*
                Verificam daca inca mai trebuie sa simulam o runda.
             */
            if (nrOfYears >= 0) {
                /*
                    Actualizam bugetul mosului.
                 */
                santa.setSantaBudget(annualChange.getNewSantaBudget());
                /*
                    Actualizam lista de cadouri a mosului(le adaugam pe toate cele noi).
                 */
                santa.getSantaGiftsList().addAll(annualChange.getNewGifts());
                /*
                    Actualizam lista de copii a mosului(adaugandu-i pe toti cei noi).
                 */
                santa.getChildrens().addAll(annualChange.getNewChildren());

                /*
                    Pentru fiecare dintre copii noi vom seta primul element din istoricul
                 de niceScores.
                 */
                Utils.addFirstNiceScoreToHistoryForChilds(annualChange.getNewChildren());

                /*
                    In cazul in care avem schimbari la copii deja existenti.
                 */
                for (ChildrenUpdate childrenUpdate : annualChange.getChildrenUpdates()) {
                    /*
                        Cautam copilul in lista de copii dupa id.
                     */
                    Child child = Utils.searchChildById(childrenUpdate.getId());
                    if (child != null) {
                        /*
                            Inseamna ca s-a gasit un copil cu id-ul cautat si actualizam
                         informatiile despre acesta.
                         */
                        if (childrenUpdate.getNiceScore() != null) {
                            /*
                                Daca niceScore-ul din history nu este null il vom adauga
                             la istoricul copilului pe ultima pozitie.
                             */
                            child.getNiceScoreHistory().add(childrenUpdate.getNiceScore());
                        }

                        /*
                            Actualizam preferintele de cadouri ale copilului in cazul
                         in care acestea exista.
                         */
                        for (int i = childrenUpdate.getGiftsPreferences().size() - 1; i >= 0;
                             i--) {
                            if (child.getGiftsPreferences().contains(
                                    childrenUpdate.getGiftsPreferences().get(i))) {
                                /*
                                    Daca deja exista preferinta in lista copilului
                                 atunci o vom sterge din lista si ulterior o vom
                                 adauga pe prima pozitie.
                                 */
                                child.getGiftsPreferences().remove(
                                        childrenUpdate.getGiftsPreferences().get(i));
                            }
                            /*
                                Adaugam noua preferinta in materie de cadouri a copilului pe
                             prima pozitie din lista.
                             */
                            child.getGiftsPreferences().add(
                                    0, childrenUpdate.getGiftsPreferences().get(i));
                        }
                    }
                }
                /*
                    Apelam cealalta metoda pentru a simula o runda dupa ce am facut toate
                 modificarile necesare anului curent.
                 */
                justExec();
            }
        }
    }

    /***
     *
     * @return Obiectul de tip Output creat in urma simularii tuturor rundelor.
     */
    public Output getOutput() {
        return output;
    }

}
