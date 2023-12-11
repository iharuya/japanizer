package github.iharuya.japanizer.velocity;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class Utils {
  public static Component addMetadata(String sender, String server, Component message) {
    Component newComponent =
        Component.text().append(Component.text(sender, NamedTextColor.GREEN))
            .append(Component.text("@", NamedTextColor.GRAY))
            .append(Component.text(server, NamedTextColor.AQUA))
            .appendSpace()
            .append(message).build();
    return newComponent;
  }

  public static Component addMetadata(String sender, Component message) {
    Component newComponent =
        Component.text().append(Component.text(sender, NamedTextColor.GREEN))
            .appendSpace()
            .append(message).build();
    return newComponent;
  }
}
