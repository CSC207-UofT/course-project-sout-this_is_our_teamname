package demoGUI.util;

import javax.swing.*;
import java.awt.*;

public class DimensionUtil {

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
