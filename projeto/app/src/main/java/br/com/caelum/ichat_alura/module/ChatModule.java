package br.com.caelum.ichat_alura.module;

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

    @Provides
    public ChatService getChatService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.6.17:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ChatService.class);

    }

}
