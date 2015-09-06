package pi3esoft2015.unicesumar.br.temlab.informatica.enums;

public enum Period {
    night("N"), morning("M"), afternoon("T");

    private final String period;

    Period(final String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }
}
