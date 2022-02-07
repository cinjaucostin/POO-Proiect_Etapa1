package input;

import child.Child;
import gift.Gift;

import java.util.List;

public final class InitialData {
    /*
        Clasa creata de asemenea pentru a putea parsa din fisierul de input.
        Ea contine o lista children de obiecte de tip Child(lista cu copiii din runda 0) si
     o lista de obiecte de tip Gift(lista de cadouri din runda 0).
     */
    private List<Child> children;
    private List<Gift> santaGiftsList;

    public InitialData(final List<Child> children, final List<Gift> santaGiftsList) {
        this.children = children;
        this.santaGiftsList = santaGiftsList;
    }

    public InitialData() {
        this(null, null);
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(final List<Child> children) {
        this.children = children;
    }

    public List<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(final List<Gift> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }

}
