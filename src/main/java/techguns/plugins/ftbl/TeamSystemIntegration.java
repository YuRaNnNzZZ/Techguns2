package techguns.plugins.ftbl;

import java.util.UUID;

import com.feed_the_beast.ftblib.lib.data.ForgePlayer;
import com.feed_the_beast.ftblib.lib.data.ForgeTeam;
import com.feed_the_beast.ftblib.lib.data.Universe;

public class TeamSystemIntegration {

	public static boolean isAllied(UUID ply1, UUID ply2) {
		ForgePlayer p1 = Universe.get().getPlayer(ply1);
		ForgePlayer p2 = Universe.get().getPlayer(ply2);

		if(p1!=null) {
			ForgeTeam t1 = p1.team;
			if(t1!=null) {
				return t1.isMember(p2) || t1.isAlly(p2);
			} else {
				return ply1.equals(ply2);
			}

		} else {
			return ply1.equals(ply2);
		}
	}
	
	public static boolean isAlliedNoMember(UUID ply1, UUID ply2) {
		ForgePlayer p1 = Universe.get().getPlayer(ply1);
		ForgePlayer p2 = Universe.get().getPlayer(ply2);
		
		if(p1!=null) {
			ForgeTeam t1 = p1.team;
			if(t1!=null) {
				return t1.isAlly(p2) && !t1.isMember(p2);
			} else {
				return ply1.equals(ply2);
			}
		} else {
			return ply1.equals(ply2);
		}
	}
	
	public static boolean isTeamMember(UUID ply1, UUID ply2) {
		ForgePlayer p1 = Universe.get().getPlayer(ply1);
		ForgePlayer p2 = Universe.get().getPlayer(ply2);
		
		if(p1!=null) {
			ForgeTeam t1 = p1.team;
			if(t1!=null) {
				return t1.isMember(p2);
			} else {
				return ply1.equals(ply2);
			}
		} else {
			return ply1.equals(ply2);
		}
	}
	
	public static boolean isEnemy(UUID ply1, UUID ply2) {
		ForgePlayer p1 = Universe.get().getPlayer(ply1);
		ForgePlayer p2 = Universe.get().getPlayer(ply2);
		
		if(p1!=null) {
			ForgeTeam t1 = p1.team;
			if(t1!=null) {
				return t1.isEnemy(p2);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
