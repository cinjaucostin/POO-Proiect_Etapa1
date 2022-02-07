package santa;

import child.Child;
import common.Constants;
import gift.Gift;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public final class Santa implements Visitor {
    private double santaBudget;
    private double budgetUnit;
    private List<Child> childrens;
    private List<Gift> santaGiftsList;

    /***
     * Metoda prin care Mos Craciun viziteaza un copil si ii ofera acestuia cadourile
     * pe care si le doreste si care se incadreaza in bugetul alocat lui.
     * @param child copilul ce trebuie vizitat
     */
    @Override
    public void visit(final Child child) {
        double budgetUsed = 0;
        /*
            Parcurgem fiecare categorie din preferintele pentru cadouri ale copilului.
         */
        for (String category : child.getGiftsPreferences()) {
            /*
                Extragem din lista de cadouri doar cele ce fac parte din categoria specificata.
             */
            List<Gift> gifts = Utils.searchGiftsByCategory(category, this.santaGiftsList);
            /*
                Daca lista de cadouri ce apartin categoriei nu este nula:
             */
            if (gifts != null) {
                /*
                    Extragem primul cadou, deoarece noua ne trebuie cel mai ieftin dintre acestea,
                 si avand in vedere faptul ca lista a fost sortata anterior crescator dupa pret,
                 primul cadou din lista va fi cel mai ieftin.
                 */
                Gift gift = gifts.get(0);
                /*
                    Verificam daca nu depasim bugetul alocat copilului in cazul in care ii oferim
                acestuia cadoul.
                 */
                if (budgetUsed + gift.getPrice() <= child.getAssignedBudget()) {
                    if (child.getReceivedGifts() == null) {
                        /*
                            Inseamna ca nu a mai primit un cadou inainte si atunci vom crea o
                        instanta a unei liste pe care o vom asigna copilului.
                        */
                        List<Gift> giftsReceived = new ArrayList<>();
                        giftsReceived.add(gift);
                        child.setReceivedGifts(giftsReceived);
                    } else {
                        /*
                            Inseamna ca a mai primit cadouri inainte si atunci vom adauga doar
                        la sfarsitul listei.
                         */
                        child.getReceivedGifts().add(gift);
                    }
                    /*
                        Adaugam pretul cadoului la bugetul folosit pentru copil pana acum.
                     */
                    budgetUsed += gift.getPrice();
                }
            }
        }
    }

    /***
     * Metoda prin care stergem din lista de copii toti cei care au categoria de varsta
     * YOUNG_ADULT, deci ei nu va mai trebui sa primeasca cadouri.
     */
    public void removeYoungAdults() {
        childrens.removeIf(child -> child.getAgeCategory().equals(Constants.YOUNG_ADULT));
    }

    /**
     * In functie de intervalul in care se afla varsta fiecarui copil din lista
     * ii setam acestuia o categorie de varsta din cele specificate in enunt.
     */
    public void calculateAgeCategoryForEveryChild() {
        for (Child child : childrens) {
            if (child.getAge() < Constants.BABY_AGE) {
                child.setAgeCategory(Constants.BABY);
            } else if (child.getAge() >= Constants.BABY_AGE
                    && child.getAge() < Constants.KID_AGE) {
                child.setAgeCategory(Constants.KID);
            } else if (child.getAge() >= Constants.KID_AGE
                    && child.getAge() <= Constants.TEEN_AGE) {
                child.setAgeCategory(Constants.TEEN);
            } else if (child.getAge() > Constants.TEEN_AGE) {
                child.setAgeCategory(Constants.YOUNG_ADULT);
            }
        }
    }

    /***
     * Metoda prin care Mosul calculeaza media pentru fiecare copil din lista dupa
     * regulile date, in functie de categoria de varsta a fiecaruia.
     */
    public void calculateNiceScoreAverageForEveryChild() {
        for (Child child : childrens) {
            if (child.getAgeCategory().equals(Constants.BABY)) {
                /*
                    Daca categoria de varsta a copilului este BABY atunci
                 acesta va din start nota 10.
                 */
                child.setAverageScore(Constants.TEN_SCORE);
            } else if (child.getAgeCategory().equals(Constants.KID)) {
                /*
                    In cazul in care categoria de varsta a copilului este KID,
                 atunci media acestuia va fi chiar media aritmetica a scorurilor
                 din istoricul de niceScore.
                 */
                double sum = 0;
                for (Double score : child.getNiceScoreHistory()) {
                    sum += score;
                }
                child.setAverageScore(sum / child.getNiceScoreHistory().size());
            } else if (child.getAgeCategory().equals(Constants.TEEN)) {
                /*
                    In cazul in care categoria de varsta a copilului este TEEN,
                atunci media acestuia este reprezentata de media ponderata a
                niceScore-urilor din istoric(notele mai recente conteaza mai mult).
                 */
                double sum1 = 0;
                double sum2 = 0;
                for (int i = 0; i < child.getNiceScoreHistory().size(); i++) {
                    sum1 += (child.getNiceScoreHistory().get(i) * (i + 1));
                    sum2 = sum2 + (i + 1);
                }
                child.setAverageScore(sum1 / sum2);
            }
        }
    }

    /***
     * Metoda prin care Mosul calculeaza budgetUnit-ul.
     * Aduna averageNiceScore-ul pentru fiecare copil intr-o suma si pentru
     * a obtine budgetUnit-ul imparte bugetul sau(santaBudget) la suma obtinuta.
     */
    public void calculateBudgetUnit() {
        double sum = 0;
        for (Child copil : childrens) {
            sum += copil.getAverageScore();
        }
        this.budgetUnit = this.santaBudget / sum;
    }

    /***
     * Metoda prin care Mosul calculeaza bugetul alocat pentru fiecare copil din lista.
     * O face in modul urmator: bugetul alocat unui copil este media sa inmultita cu
     * budgetUnit-ul calculat.
     */
    public void calculateAllocatedBudgetForEveryChild() {
        for (Child copil : childrens) {
            copil.setAssignedBudget(copil.getAverageScore() * this.budgetUnit);
        }
    }

    public Santa(final double santaBudget, final List<Child> childrens,
                 final List<Gift> santaGiftsList) {
        this.santaBudget = santaBudget;
        this.childrens = childrens;
        this.santaGiftsList = santaGiftsList;
    }

    public Santa() {
        this(0, null, null);
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public List<Child> getChildrens() {
        return childrens;
    }

    public void setChildrens(final List<Child> childrens) {
        this.childrens = childrens;
    }

    public List<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(final List<Gift> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }

}
