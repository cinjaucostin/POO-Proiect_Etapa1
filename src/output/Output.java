package output;

import java.util.ArrayList;
import java.util.List;

public class Output {
    /*
        Clasa creata de asemenea pentru a obtine un fisier de iesire de tip json
     dupa format-ul cerut.
        Contine o lista de ChildrenOutput(pentru fiecare simulare vom avea un obiect
     de tip ChildrenOutput, care la randul lui va contine o lista de obiecte de tip
     ChildOutput(informatiile despre un copil dupa simularea unei runde).
     */
    private List<AnnualOutput> annualChildren = new ArrayList<>();

    /***
     *
     * @return lista de obiecte de tip ChildrenOutput din clasa curenta
     */
    public List<AnnualOutput> getAnnualChildren() {
        return annualChildren;
    }

    /***
     *
     * @param annualChild o lista de obiecte de tip ChildrenOutput pe care o vom seta
     *                    pe campul corespunzator.
     */
    public void setAnnualChildren(final List<AnnualOutput> annualChild) {
        this.annualChildren = annualChild;
    }

}
