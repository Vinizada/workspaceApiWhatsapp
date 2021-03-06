package com.apiwhatsapp.enviadores;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EnviaMensagemTexto {

	private static final String BASE_URL = "https://6j689r.api.infobip.com";
	private static final String API_KEY = "App b17f6baa0d1004c80c91d5a15f79084a-d6647be1-cff3-43e4-b40b-c023b3cc825c";
	private static final String MEDIA_TYPE = "application/json";

	public Response enviaMensagemTexto() throws IOException {

		OkHttpClient client = new OkHttpClient().newBuilder().build();
		
		String bodyJson = "{\"from\":\"447860099299\",\"to\":\"5551989358406\",\"messageId\":\"a28dd97c-1ffb-4fcf-99f1-0b557ed381da\",\"content\":{\"text\":\"Olá, mensagem enviada automaticamente pelo Java!\"},\"callbackData\":\"Callback data\",\"notifyUrl\":\"https://www.example.com/whatsapp\"}";

		RequestBody body = RequestBody.create(bodyJson, MediaType.parse(MEDIA_TYPE));
				
		Request request = new Request.Builder()
				.url(String.format("%s/whatsapp/1/message/template", BASE_URL))
				.method("POST", body)
				.addHeader("Authorization", API_KEY)
				.addHeader("Content-Type", MEDIA_TYPE)
				.addHeader("Accept", MEDIA_TYPE)
				.build();

		Response response = client.newCall(request).execute();

		return response;

	}

}
