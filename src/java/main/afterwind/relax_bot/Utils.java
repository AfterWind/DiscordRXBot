package afterwind.relax_bot;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.util.List;

public class Utils {

    public static void sendMessage(IUser user, IChannel channel, String message) throws RateLimitException, DiscordException, MissingPermissionsException {
        channel.sendMessage(user.mention() + ": " + message);
    }

    public static boolean hasPermission(List<IRole> roles, Permissions perm) {
        for (IRole role : roles) {
            if (role.getPermissions().contains(perm)) {
                return true;
            }
        }
        return false;
    }

}
