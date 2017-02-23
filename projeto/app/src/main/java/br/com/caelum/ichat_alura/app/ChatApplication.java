package br.com.caelum.ichat_alura.app;

import android.app.Application;

import br.com.caelum.ichat_alura.component.ChatComponent;
import br.com.caelum.ichat_alura.component.DaggerChatComponent;
import br.com.caelum.ichat_alura.module.ChatModule;

/**
 * Created by ralmendro on 2/5/17.
 */

public class ChatApplication extends Application {

    private ChatComponent component;

    @Override
    public void onCreate() {
        component = DaggerChatComponent.builder()
                .chatModule(new ChatModule(this))
                .build();
    }

    public ChatComponent getChatComponent() {
        return component;
    }

}
