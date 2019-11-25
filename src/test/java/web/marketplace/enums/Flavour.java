package web.marketplace.enums;

public enum Flavour {
    ANGULAR("Angular"),
    VUE("Vue"),
    JAVASCRIPT("JavaScript"),
    TYPESCRIPT("TypeScript");

    private final String text;

    /**
     * Init Flavour.
     *
     * @param text Flavour as String.
     */
    Flavour(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

    /**
     * Get suffix for NativeScript templates.
     * For example suffix used in `tns-template-hello-world-ng` is ng.
     * For JavaScript flavor no suffix is used.
     *
     * @return suffix as String.
     */
    public String getSuffix() {
        switch (this) {
            case JAVASCRIPT:
                return "";
            case TYPESCRIPT:
                return "-ts";
            case ANGULAR:
                return "-ng";
            case VUE:
                return "-vue";
            default:
                return null;
        }
    }
}
