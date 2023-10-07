package github.iharuya.japanizer;

import java.util.Collections;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.plugin.java.JavaPlugin;

public class Japanizer extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
  }

  @EventHandler
  public void onPlayerChat(AsyncChatEvent event) {
    Component message = event.message();
    if (!(message instanceof TextComponent))
      return;

    event.setCancelled(true);
    final String text = ((TextComponent) message).content();
    final Player player = event.getPlayer();

    if (text.startsWith("!")) {
      this.getServer().broadcast(Utils.addPlayerPrefix(player, text.substring(1)));
      return;
    }

    final String japanizedText = Transliterator.japanize(text, Collections.emptyMap());
    if (japanizedText.length() == 0)
      return;
    message =
        Component.text().content(japanizedText).appendSpace().append(Component.text().content("(" + text + ")")
            .color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, true)).build();
    this.getServer().broadcast(Utils.addPlayerPrefix(player, message));
  }
}
