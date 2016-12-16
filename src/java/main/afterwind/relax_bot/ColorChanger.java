package afterwind.relax_bot;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.awt.*;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColorChanger {

    private IGuild guild;
    private Map<String, IRole> colors = new HashMap<>();

    public ColorChanger(IGuild guild) {
        this.guild = guild;
        initColorRoles();
    }

    private void initColorRoles() {
        colors.put("blue", getRole("RX_blue", Color.blue));
        colors.put("red", getRole("RX_red", Color.red));
        colors.put("orange", getRole("RX_orange", Color.orange));
        colors.put("black", getRole("RX_black", Color.black));
        colors.put("white", getRole("RX_white", Color.white));
        colors.put("green", getRole("RX_green", Color.green));
        colors.put("yellow", getRole("RX_yellow", Color.yellow));
        colors.put("pink", getRole("RX_pink", Color.pink));
        colors.put("cyan", getRole("RX_cyan", Color.cyan));
        colors.put("gray", getRole("RX_gray", Color.gray));
        colors.put("light_gray", getRole("RX_light_gray", Color.lightGray));
        colors.put("dark_gray", getRole("RX_dark_gray", Color.darkGray));
        colors.put("magenta", getRole("RX_magenta", Color.magenta));
//
//        List<IRole> roles = guild.getRoles().subList(3, guild.getRoles().size());
//        for (IRole r : roles) {
//            System.out.println(r.getName() + " " + r.getPosition());
//        }
//        if (roles.get(0).getName().equals("RX_blue")) {
//            return;
//        }
//        List<IRole> ordered = new ArrayList<>();
//        ordered.addAll(roles.subList(0, roles.size() - 13));
//        ordered.addAll(roles.subList(roles.size() - 13, roles.size()));
//        ordered.add(guild.getRoles().get(2));
//        ordered.add(guild.getRoles().get(1));
//        ordered.add(guild.getRoles().get(0));
//
//        try {
//            System.out.println("Trying to reorder!");
//            guild.reorderRoles((IRole[]) ordered.toArray(new IRole[ordered.size()]));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private IRole getRole(String name, Color color) {
        try {
            List<IRole> roles = guild.getRolesByName(name);
            IRole role;
            if (roles.size() <= 0) {
                role = guild.createRole();
                role.changeName(name);
                role.changeColor(color);
            } else {
                role = roles.get(0);
            }
            return role;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private boolean giveRole(String name, IUser user) {
        try {
            for (IRole role : colors.values()) {
                if (user.getRolesForGuild(guild).contains(role)) {
                    user.removeRole(role);
                }
            }
            user.addRole(colors.get(name));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent ev) {
        if (!ev.getMessage().getGuild().equals(guild)) {
            return;
        }

        try {
            if (ev.getMessage().getMentions().contains(RelaxBot.client.getOurUser())) {
                String[] split = ev.getMessage().getContent().split(" +");
                System.out.println(ev.getMessage().getContent());

                if (split.length < 3) {
                    return;
                }
                System.out.println(split[0] + "|" + split[1] + "|" + split[2]);

                if (split[1].trim().equals("color")) {
                    String color = split[2].trim();
                    if (!colors.keySet().contains(color)) {
                        ev.getMessage().getChannel().sendMessage(ev.getMessage().getAuthor().mention() + ": I am sorry but color " + color + " does not exist!");
                        return;
                    }
                    giveRole(color, ev.getMessage().getAuthor());
                    ev.getMessage().getChannel().sendMessage(ev.getMessage().getAuthor().mention() + ": your new color is now " + color + "!");
                } else {
                    ev.getMessage().getChannel().sendMessage(ev.getMessage().getAuthor().mention() + ": I am sorry but this command does not exist!");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
