package view;

public class NavItem {
    private final String label;
    private final String viewName;

    public NavItem(String label, String viewName) {
        this.label = label;
        this.viewName = viewName;
    }

    public String label() { return label; }
    public String viewName() { return viewName; }
}
