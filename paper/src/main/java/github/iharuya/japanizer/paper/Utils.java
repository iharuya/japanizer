package github.iharuya.japanizer.paper;

import org.bukkit.entity.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class Utils {
  public static Component addPlayerPrefix(Player player, Component component) {
    Component componentWithPrefix =
        Component.text().append(Component.text("[" + player.getName() + "]", NamedTextColor.GREEN))
            .appendSpace().append(component).build();
    return componentWithPrefix;
  }

  public static Component addPlayerPrefix(Player player, String message) {
     Component componentWithPrefix =
        Component.text().append(Component.text("[" + player.getName() + "]", NamedTextColor.GREEN))
            .appendSpace().append(Component.text(message)).build();
    return componentWithPrefix;
  }
}
