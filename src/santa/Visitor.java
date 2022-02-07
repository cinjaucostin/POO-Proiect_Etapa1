package santa;

import child.Child;

public interface Visitor {
    /***
     * Metoda specifica design pattern-ului visitor, pentru a vizita toate
     * obiectele ce pot fi vizitate.
     * @param child copilul ce trebuie vizitat
     */
    void visit(Child child);
}
