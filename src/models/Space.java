package models;

public class Space {

    private final boolean fixed;
    private final int valueExpected;
    private Integer valueActual;


    public Space(boolean fixed, int valueExpected) {
        this.fixed = fixed;
        this.valueExpected = valueExpected;
        if (fixed) {
            this.valueActual = valueExpected;
        }
    }

    public Integer getValueActual() {
        return valueActual;
    }

    public void setValueActual(final Integer valueActual) {
        if(fixed) return;
        this.valueActual = valueActual;
    }

    public boolean isFixed() {
        return fixed;
    }

    public int getValueExpected() {
        return valueExpected;
    }

}
