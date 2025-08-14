package enums;

public enum SudokuStatus {

    NON_STARTED("não iniciado"),
    INCOMPLETE("incompleto"),
    COMPLETE("completo");

    private String label;


    SudokuStatus(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
