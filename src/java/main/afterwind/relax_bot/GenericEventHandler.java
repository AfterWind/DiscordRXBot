package afterwind.relax_bot;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;

public class GenericEventHandler {

    @EventSubscriber
    public void onReady(ReadyEvent ev) {
        RelaxBot.init();
    }

}
