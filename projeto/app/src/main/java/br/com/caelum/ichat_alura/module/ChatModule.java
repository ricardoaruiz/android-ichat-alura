package br.com.caelum.ichat_alura.module;

import android.app.Application;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import br.com.caelum.ichat_alura.service.ChatService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ralmendro on 2/5/17.
 */

@Module
public class ChatModule {

    private Application app;

    public ChatModule(Application app) {
        this.app = app;
    }

    @Provides
    public ChatService getChatService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ChatService.class);

    }

    @Provides
    public Picasso getPicasso() {
        return new Picasso.Builder(app).build();
    }

    @Provides
    public EventBus getEventBus() {
        return EventBus.builder().build();
    }

    @Provides
    public InputMethodManager getInputMethodManager() {
        return (InputMethodManager) app.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

}
