package com.apiwhatsapp.application;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

import javax.inject.Inject;

import com.apiwhatsapp.enviadores.EnviaMensagemTexto;

public class TesteApiWhatsapp {

	private static final String BASE_URL = "https://6j689r.api.infobip.com";
	private static final String API_KEY = "App b17f6baa0d1004c80c91d5a15f79084a-d6647be1-cff3-43e4-b40b-c023b3cc825c";
	private static final String MEDIA_TYPE = "application/json";

	private static final String SENDER = "447860099299";
	private static final String RECIPIENT = "5551989358406";
	
	private static EnviaMensagemTexto enviaMensagemTexto =  new EnviaMensagemTexto();

	public static void main(String[] args) throws IOException {
		
		try {

		OkHttpClient client = new OkHttpClient().newBuilder().build();

		String bodyJson = String.format("{\n" + "  \"messages\": [\n" + "    {\n" + "        \"from\": \"%s\",\n"
				+ "        \"to\": \"%s\",\n" + "        \"content\": {\n"
				+ "          \"templateName\": \"registration_success\",\n" + "          \"templateData\": {\n"
				+ "            \"body\": {\n" + "              \"placeholders\": [\n" + "                \"sender\",\n"
				+ "                \"message\",\n" + "                \"delivered\",\n"
				+ "                \"testing\"\n" + "              ]\n" + "            },\n"
				+ "            \"header\": {\n" + "              \"type\": \"IMAGE\",\n"
				+ "              \"mediaUrl\": \"https://api.infobip.com/ott/1/media/infobipLogo\"\n"
				+ "            },\n" + "            \"buttons\": [\n" + "              {\n"
				+ "                \"type\": \"QUICK_REPLY\",\n" + "                \"parameter\": \"yes-payload\"\n"
				+ "              },\n" + "              {\n" + "                \"type\": \"QUICK_REPLY\",\n"
				+ "                \"parameter\": \"no-payload\"\n" + "              },\n" + "              {\n"
				+ "                \"type\": \"QUICK_REPLY\",\n" + "                \"parameter\": \"later-payload\"\n"
				+ "              }\n" + "            ]\n" + "        },\n" + "        \"language\": \"en\"\n"
				+ "      }\n" + "    }\n" + "  ]\n" + "}", SENDER, RECIPIENT);

		RequestBody body = RequestBody.create(bodyJson, MediaType.parse(MEDIA_TYPE));
		
		Request request = prepareHttpRequest(body);
		
		Response response = client.newCall(request).execute();

		System.out.println("HTTP status code: " + response.code());
		System.out.println("Response body: " + response.body().string());
		
		response = enviaMensagemTexto.enviaMensagemTexto();
		
		System.out.println("HTTP status code: " + response.code());
		System.out.println("Response body: " + response.body().string());
		
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	private static Request prepareHttpRequest(RequestBody body) {

		return new Request.Builder().url(String.format("%s/whatsapp/1/message/template", BASE_URL)).method("POST", body)
				.addHeader("Authorization", API_KEY).addHeader("Content-Type", MEDIA_TYPE)
				.addHeader("Accept", MEDIA_TYPE).build();
	}

}
