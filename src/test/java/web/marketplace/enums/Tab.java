package web.marketplace.enums;

public enum Tab {
    PLUGINS("Plugins"),
    TEMPLATES("Templates"),
    SAMPLES("Samples");

    private final String text;

    /**
     * @param text
     */
    Tab(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
