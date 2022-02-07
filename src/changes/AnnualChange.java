package changes;

import child.Child;
import child.ChildrenUpdate;
import gift.Gift;

import java.util.ArrayList;

public final class AnnualChange {
    /*
        Clasa ce modeleaza toate caracteristicile unui annualChange: noul buget
     al mosului, o lista de noi cadouri, o lista de noi copii si o lista cu
     update-uri pentru copiii deja existenti.
     */
    private double newSantaBudget;
    private ArrayList<Gift> newGifts;
    private ArrayList<Child> newChildren;
    private ArrayList<ChildrenUpdate> childrenUpdates;

    public AnnualChange(final double newSantaBudget, final ArrayList<Gift> newGifts,
                        final ArrayList<Child> newChildren,
                        final ArrayList<ChildrenUpdate> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    public AnnualChange() {
        this(0, null, null, null);
    }

    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(final double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(final ArrayList<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(final ArrayList<Child> newChildren) {
        this.newChildren = newChildren;
    }

    public ArrayList<ChildrenUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(final ArrayList<ChildrenUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

}
