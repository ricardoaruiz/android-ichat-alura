package br.com.caelum.ichat_alura.component;

import br.com.caelum.ichat_alura.activity.MainActivity;
import br.com.caelum.ichat_alura.module.ChatModule;
import dagger.Component;

/**
 * Created by ralmendro on 2/5/17.
 */

@Component(modules = ChatModule.class)
public interface ChatComponent {

    void inject(MainActivity activity);

}
