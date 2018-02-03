package de.bergtiger.tigerchat.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.bergtiger.tigerchat.TigerChat;
import de.bergtiger.tigerchat.data.MyPermission;
import de.bergtiger.tigerchat.data.MyString;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class MyMessage extends MyListener {

	private String format = "%prefix%player%suffix %message";
	private String prefix = "<";
	private String suffix = ">";
	private boolean permEx = false;
	private int radius = -1;
	
	public MyMessage(TigerChat plugin) {
		super(plugin);
	}
		
	@EventHandler
	public void onMessage(AsyncPlayerChatEvent e) {
		if(!e.isCancelled()) {	//Nachricht wird nicht durch anderes Plugin geblocked
			Player p = e.getPlayer();
			if(p.hasPermission(MyPermission.ADMIN.get()) || p.hasPermission(MyPermission.PLAYER.get()) || p.hasPermission(MyPermission.CHAT.get())) {	//Player hat permission um im Chat zu schreiben
				//Spam pre check
				
				//format setzen
				String message = this.format;
				e.setFormat(this.setFormat(e.getPlayer(), message.replace("%message", "%2$s").replace("%displayname", "%1$s")));
				e.setMessage(this.setColor(e.getPlayer(), e.getMessage()));
				
				//chat channel / ignores / radius
				
				if(radius > 0) {	//only when a radius is set
					for(Player player : e.getRecipients()) {
						double abstand = Math.sqrt(Math.pow((p.getLocation().getX() - player.getLocation().getX()), 2)
												 + Math.pow((p.getLocation().getY() - player.getLocation().getY()), 2)
												 + Math.pow((p.getLocation().getZ() - player.getLocation().getZ()), 2));
						if(radius < abstand) {
							e.getRecipients().remove(player);
						}
					}
				}
				//spam post check
			} else {
				//Player has no Permission for Chat
				p.sendMessage(MyString.CHAT_NOTALLOWED.colored());
				e.setCancelled(true);
			}
		}
	}
	
	private String setFormat(Player p, String format){
		if(this.permEx){
			PermissionUser user = PermissionsEx.getPermissionManager().getUser(p);
//			format = user.getOption("global-message-format", p.getWorld().getName(), format);
			return ChatColor.translateAlternateColorCodes('&', format.replace("%prefix", user.getPrefix(p.getWorld().getName())).replace("%suffix", user.getSuffix(p.getWorld().getName())).replace("%world", p.getWorld().getName()).replace("%player", p.getDisplayName()));
		} else {
			return ChatColor.translateAlternateColorCodes('&', format.replace("%prefix", this.prefix).replace("%suffix", this.suffix).replace("%world", p.getWorld().getName()).replace("%player", p.getDisplayName()));
		}
	}
	
	private String setColor(Player p, String message){
		if(p.hasPermission(MyPermission.ADMIN.get()) || p.hasPermission(MyPermission.CHAT_COLOR.get())){
			message = message.replaceAll("&0", ChatColor.BLACK.toString())
							 .replaceAll("&1", ChatColor.DARK_BLUE.toString())
							 .replaceAll("&2", ChatColor.DARK_GREEN.toString())
							 .replaceAll("&3", ChatColor.DARK_AQUA.toString())
							 .replaceAll("&4", ChatColor.DARK_RED.toString())
							 .replaceAll("&5", ChatColor.DARK_PURPLE.toString())
							 .replaceAll("&6", ChatColor.GOLD.toString())
							 .replaceAll("&7", ChatColor.GRAY.toString())
							 .replaceAll("&8", ChatColor.DARK_GRAY.toString())
							 .replaceAll("&9", ChatColor.BLUE.toString())
							 .replaceAll("&a", ChatColor.GREEN.toString())
							 .replaceAll("&b", ChatColor.AQUA.toString())
							 .replaceAll("&c", ChatColor.RED.toString())
							 .replaceAll("&d", ChatColor.LIGHT_PURPLE.toString())
							 .replaceAll("&e", ChatColor.YELLOW.toString())
							 .replaceAll("&f", ChatColor.WHITE.toString());
		}
		if(p.hasPermission(MyPermission.ADMIN.get()) || p.hasPermission(MyPermission.CHAT_FORMAT.get())){
			message = message.replaceAll("&l", ChatColor.BOLD.toString())
							 .replaceAll("&m", ChatColor.STRIKETHROUGH.toString())
							 .replaceAll("&n", ChatColor.UNDERLINE.toString())
							 .replaceAll("&o", ChatColor.ITALIC.toString())
							 .replaceAll("&r", ChatColor.RESET.toString());
		}
		if(p.hasPermission(MyPermission.ADMIN.get()) || p.hasPermission(MyPermission.CHAT_FORMAT_K.get())){
			message = message.replaceAll("&k", ChatColor.MAGIC.toString());
		}
		return message;
	}
}
