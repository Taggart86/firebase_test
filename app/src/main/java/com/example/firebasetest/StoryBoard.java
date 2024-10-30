package com.example.firebasetest;

public class StoryBoard {
    private String screenTitle;
    private String uiComponents;
    private String navigationLinks;

    // Constructor
    public StoryBoard(String screenTitle, String uiComponents, String navigationLinks) {
        this.screenTitle = screenTitle;
        this.uiComponents = uiComponents;
        this.navigationLinks = navigationLinks;
    }

    // Getters and setters
    public String getScreenTitle() {
        return screenTitle;
    }

    public void setScreenTitle(String screenTitle) {
        this.screenTitle = screenTitle;
    }

    public String getUiComponents() {
        return uiComponents;
    }

    public void setUiComponents(String uiComponents) {
        this.uiComponents = uiComponents;
    }

    public String getNavigationLinks() {
        return navigationLinks;
    }

    public void setNavigationLinks(String navigationLinks) {
        this.navigationLinks = navigationLinks;
    }
}

