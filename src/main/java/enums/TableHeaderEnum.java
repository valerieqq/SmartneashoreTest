package enums;

public enum TableHeaderEnum {

    IMAGE("Image"),
    TITLE("Title"),
    AUTHOR("Author"),
    PUBLISHER("Publisher");

    TableHeaderEnum(String headerName) {
        this.headerName = headerName;
    }

    private final String headerName;

    public String getHeaderName() {
        return headerName;
    }
}
