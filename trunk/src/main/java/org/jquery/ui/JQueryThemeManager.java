package org.jquery.ui;

import java.io.Serializable;

/**
 * Contains a standard theme for this application.
 */
public class JQueryThemeManager implements Serializable {

    /** The theme. */
    protected String theme = null;

    /**
     * Retrieves the theme.
     *
     * @return The theme.
     */
    public String getTheme() {
        return theme;
    }

    /**
     * Assigns the theme.
     *
     * @param theme The theme.
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }
}
