package GUI.util;

import javax.swing.*;
import java.awt.*;

/**
 * A class to get the screen size of the user.
 */
public class DimensionUtil {

    /**
     * Gets the bounds from the screen set of the user.
     * @return the bounds
     */
    public static Rectangle getBounds() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Showing the task board
        Insets screenInsets = Toolkit.getDefaultToolkit()
                .getScreenInsets(new JFrame().getGraphicsConfiguration());

        return new Rectangle(screenInsets.left, screenInsets.top,
                screenSize.width - screenInsets.left - screenInsets.right,
                screenSize.height - screenInsets.top - screenInsets.bottom);
    }
}
