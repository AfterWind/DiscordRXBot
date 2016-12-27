package afterwind.relax_bot;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.DiscordException;

public class RelaxBot {
    public static IDiscordClient client;
    private static EventDispatcher dispatcher;

    public static void main(String[] args) {
        client = getClient("MjU4MjYyMzY1MjMyMDM3ODky.C0PxTw.mr3CpwyIEBUnmaHdFsboNjBEfWE", true);
        dispatcher = client.getDispatcher();
        dispatcher.registerListener(new GenericEventHandler());
    }

    public static void init() {
        try {
            RelaxBot.client.changeUsername("RelaxBot");
        } catch (Exception ex) {
            System.out.println("Didn't change username!");
            ex.printStackTrace();
        }
        for (IGuild guild : client.getGuilds()) {
            dispatcher.registerListener(new ColorChanger(guild));
            System.out.println("Registered color changer for guild " + guild.getName());
        }
    }

    public static IDiscordClient getClient(String token, boolean login) {
        // Returns an instance of the Discord client
        ClientBuilder clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
        clientBuilder.withToken(token); // Adds the login info to the builder
        try {
            if (login) {
                return clientBuilder.login(); // Creates the client instance and logs the client in
            } else {
                return clientBuilder.build(); // Creates the client instance but it doesn't log the client in yet, you would have to call client.login() yourself
            }
        } catch (DiscordException e) {
            throw new RuntimeException(e);
        }
    }
}
