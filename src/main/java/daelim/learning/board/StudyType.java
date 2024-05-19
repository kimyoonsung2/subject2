package daelim.learning.board;

public enum StudyType {
    ON("온라인"),
    OFF("오프라인"),
    BOTH("온/오프라인");

    private final String description;

    StudyType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
