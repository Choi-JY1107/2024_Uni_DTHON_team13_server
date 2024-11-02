package Image;

public enum ApplicationError {
    FILE_NOT_EXIST;

    @Override
    public String toString() {
        return "File does not exist.";
    }
}
