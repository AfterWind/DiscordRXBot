package afterwind.relax_bot;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

public class EventHandler {

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent ev) {
        try {
            ev.getMessage().getChannel().sendMessage(ev.getMessage().getContent() + "asd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
