package pi3esoft2015.unicesumar.br.temlab.informatica;

import pi3esoft2015.unicesumar.br.temlab.informatica.enums.Page;
import pi3esoft2015.unicesumar.br.temlab.informatica.enums.Period;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GETTER {
    private final static String regex = "(<)([a-zA-Z0-9\\s\\/\'\"\\=\\:\\;\\%\\#\\,\\|\\.\\(\\)\\-\\_]*)(>)";
    private Page page;
    private DatePeriod datePeriod;
    private String result;

    public GETTER from(final Page page) {
        this.page = page;
        return this;
    }

    public GETTER period(final Date date, final Period period) {
        this.datePeriod = new DatePeriod(date, period);
        return this;
    }

    public GETTER get() throws IOException {
        final String encoded = URLEncoder.encode(datePeriod.getParam(), "UTF-8");
        final URL url = new URL(page.getUrlWithParams(encoded));
        final URLConnection connection = url.openConnection();

        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferReader.readLine()) != null) {
            final String decoded = URLDecoder.decode(line, "UTF-8");
            final String replaced = decoded.replaceAll(regex, "#");
            stringBuilder.append(replaced);
        }
        bufferReader.close();

        result = (stringBuilder.toString().replaceAll("(#+)", "#"));
        return this;
    }

    public List<String> asList() {
        return Arrays.asList(result.split("#"));
    }
}
