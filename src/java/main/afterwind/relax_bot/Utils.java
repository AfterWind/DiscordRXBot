package afterwind.relax_bot;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class Utils {

    public static void sendMessage(IUser user, IChannel channel, String message) throws RateLimitException, DiscordException, MissingPermissionsException {
        channel.sendMessage(user.mention() + ": " + message);
    }

}
