package pi3esoft2015.unicesumar.br.temlab.informatica;

import pi3esoft2015.unicesumar.br.temlab.informatica.enums.Period;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePeriod {

    private final SimpleDateFormat formmater = new SimpleDateFormat("yyyy-MM-dd");
    private String param;

    public DatePeriod(final Date date, final Period period) {
        this.param = formmater.format(date) + "|" + period.getPeriod();
    }

    public String getParam() {
        return param;
    }
}
