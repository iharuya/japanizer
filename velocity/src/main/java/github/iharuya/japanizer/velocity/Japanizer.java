package github.iharuya.japanizer.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.event.player.PlayerChatEvent.ChatResult;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.ServerInfo;
import net.kyori.adventure.text.Component;
import java.util.Optional;
import org.slf4j.Logger;
import github.iharuya.japanizer.common.Transliterator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

@Plugin(id = "japanizer", name = "Japanizer", version = "0.0.2", description = "paper mc plugin to Japanize chat messages. For example, 'konnnichiwa' will be 'こんにちは (konnnichiwa)'")
public class Japanizer {
  private final ProxyServer server;

  @Inject
  public Japanizer(ProxyServer server) {
    this.server = server;
  }

  @Subscribe
  public void onPlayerChat(PlayerChatEvent event) {
    final Player sender = event.getPlayer();
    final String senderName = sender.getUsername();
    final String text = event.getMessage();
    final Optional<ServerConnection> senderServer = sender.getCurrentServer();
    final String senderServerName = senderServer.map(ServerConnection::getServerInfo).map(ServerInfo::getName).orElse("Unknown");

    Component message = Component.empty();
    if (text.startsWith("!")) {
      message = Component.text(text.substring(1));
    } else {
      final String japanizedText = Transliterator.japanize(text);
      if (japanizedText == text) {
        message = Component.text(text);
      } else {
        message = Component.text().content(japanizedText).appendSpace()
            .append(Component.text().content("(" + text + ")").color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, true))
            .build();
      }
    }

    for (Player recipient : server.getAllPlayers()) {
      final Optional<ServerConnection> recipientServer = recipient.getCurrentServer();
      final String recipientServerName = recipientServer.map(ServerConnection::getServerInfo).map(ServerInfo::getName).orElse("Unknown");
   
      message = recipientServerName.equals(senderServerName) ? Utils.addMetadata(senderName, "hopher", message) : Utils.addMetadata(senderName, senderServerName, message);
      recipient.sendMessage(message);
    }

    event.setResult(ChatResult.denied());
  }

}

