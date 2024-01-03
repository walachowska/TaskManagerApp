package Model.tasks;

public enum Priority {
    HIGH("High"),
    MEDIUM("Mediumi"),
    LOW("Low");

    private final String label;

    Priority(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
