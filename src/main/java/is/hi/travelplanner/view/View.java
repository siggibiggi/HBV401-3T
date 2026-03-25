package is.hi.travelplanner.view;

public enum View {
    MAIN("MainView.fxml");

    private final String fileName;

    View(String fileName){
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}