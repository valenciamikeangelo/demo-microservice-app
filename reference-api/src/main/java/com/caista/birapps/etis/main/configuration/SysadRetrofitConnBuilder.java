/*
  * Modified by: tolentf
  * Last updated: Jul 18, 2019 2:26:50 PM
  */
package com.caista.birapps.etis.main.configuration;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import com.caista.birapps.etis.reference.service.util.DateTypeDeserializer;
import com.google.gson.*;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The Class SysadRetrofitConnBuilder.
 */
@Configuration
@PropertySources({@PropertySource("classpath:application.properties")})
public class SysadRetrofitConnBuilder {

  /** The sysad url. */
  @Value("${sysad.url}")
  private String sysadUrl;

  /**
   * Sysad api.
   *
   * @return the retrofit
   */
  @Bean
  public Retrofit sysadApi() {
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTypeDeserializer())
        .create();

    OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.MINUTES)
        .readTimeout(15, TimeUnit.MINUTES).writeTimeout(15, TimeUnit.MINUTES).build();

    return new Retrofit.Builder().baseUrl(sysadUrl).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build();
  }

}
