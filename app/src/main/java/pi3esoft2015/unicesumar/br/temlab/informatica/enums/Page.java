package pi3esoft2015.unicesumar.br.temlab.informatica.enums;

public enum Page {
    informatica("http://www.cesumar.br/informatica/horario.php?dados=");

    private final String url;

    Page(final String url) {
        this.url = url;
    }

    public String getUrlWithParams(final String encoded) {
        return url + encoded;
    }
}
