public enum Encodings {
    GZIP("gzip"),
    COMPRESS("compress"),
    DEFLATE("deflate"),
    BR("br"),
    ZSTD("zstd"),
    IDENTITY("identity"),
    ALL("*")
    ;

    private final String name;

    Encodings(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String getByName(String name) {
        for (Encodings encoding : Encodings.values()) {
            if (encoding.getName().equals(name)) {
                return name;
            }
        }
        return null;
    }

}
