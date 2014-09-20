package by.bsuir.forlabs.utilits;

/**
 *  class-wrapper for resulting page from servlet
 */

public class ResponsePage {
    private String page;
    private boolean isRedirected;

    public ResponsePage() {

    }

    public ResponsePage(String page, boolean isRedirected) {
        this.page = page;
        this.isRedirected = isRedirected;
    }

    public void setPage(String page) {
        if ( page == null || page.isEmpty())
            throw new IllegalArgumentException("Parameter 'page' cannot be null");
        this.page = page;
    }

    public void setRedirected(boolean isRedirected) {
        this.isRedirected = isRedirected;
    }

    public boolean isRedirected() {
        return isRedirected;
    }
}
