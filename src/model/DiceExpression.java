package model;

import java.util.ArrayList;
import java.util.List;

public class DiceExpression {

    private final List<DiceTerm> terms = new ArrayList<>();

    private Comparison comparison;

    private int target;

    public void addTerm(DiceTerm term) {
        terms.add(term);
    }

    public List<DiceTerm> getTerms() {
        return terms;
    }

    public Comparison getComparison() {
        return comparison;
    }

    public void setComparison(Comparison comparison) {
        this.comparison = comparison;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}