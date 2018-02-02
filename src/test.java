import org.bukkit.entity.Player;

import de.bergtiger.tigerchat.spam.FilterType;
import de.bergtiger.tigerchat.spam.SpamFilter;

public class test implements SpamFilter{

	public test() {
		System.out.println("test 1 2 3");
	}

	@Override
	public boolean handle(Player p, String message) {
		System.out.println(message);
		return false;
	}

	@Override
	public FilterType type() {
		// TODO Auto-generated method stub
		return null;
	}
}
