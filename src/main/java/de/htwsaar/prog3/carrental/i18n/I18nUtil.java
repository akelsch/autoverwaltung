package de.htwsaar.prog3.carrental.i18n;

import lombok.Getter;

import java.util.ResourceBundle;

/**
 * Internationalization utility class used in this project mainly to keep code clean of String objects.
 * <p>
 * Use strings if it is something you need but don't want the user to see as a message.
 * Use messages if it is message that you want to show to the user, e.g. a dialog message.
 * Use components if it is something you want to display inside of your components.
 *
 * @author Lukas Raubuch
 */
public class I18nUtil {
    // Bundle base names
    private static final String I18N_BASENAME_STRINGS = "strings";
    private static final String I18N_BASENAME_MESSAGES = "messages";
    private static final String I18N_BASENAME_COMPONENTS = "components";

    @Getter
    private static ResourceBundle resourceBundleStrings;

    @Getter
    private static ResourceBundle resourceBundleMessages;

    @Getter
    private static ResourceBundle resourceBundleComponents;

    static {
        resourceBundleStrings = ResourceBundle.getBundle(I18N_BASENAME_STRINGS);
        resourceBundleMessages = ResourceBundle.getBundle(I18N_BASENAME_MESSAGES);
        resourceBundleComponents = ResourceBundle.getBundle(I18N_BASENAME_COMPONENTS);
    }
}