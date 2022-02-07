package output;

import java.util.ArrayList;
import java.util.List;

public class AnnualOutput {
    /*
        Clasa creata pentru a scrie in fisierul de output dupa formatul cerut.
        Pentru fiecare runda vom avea un obiect de acest tip ce contine o lista
     de obiecte ChildOutput denumita children pentru a obtine exact pattern-ul cerut.
     */
    private List<ChildOutput> children = new ArrayList<>();

    /***
     *
     * @return lista de obiecte de tip ChildOutput din clasa noastra curenta(ChildrenOutput).
     */
    public List<ChildOutput> getChildren() {
        return children;
    }

    /***
     *
     * @param children lista de obiecte de tip ChildOutput pe care o vom introduce in campul
     *                 children din clasa curenta.
     */
    public void setChildren(final List<ChildOutput> children) {
        this.children = children;
    }

}
