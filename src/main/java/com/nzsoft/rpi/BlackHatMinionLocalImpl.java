package com.nzsoft.rpi;

import static java.lang.Thread.sleep;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public final class BlackHatMinionLocalImpl implements Minion {
	private static final String GCP_DATA_URL = "http://%s:8080/v1/data";
	private HttpClient client = HttpClientBuilder.create().build();
	private String url;

	public BlackHatMinionLocalImpl() {
		this.url = String.format(GCP_DATA_URL, "127.0.0.1");
	}

	protected BlackHatMinionLocalImpl(String address) {
		this.url = String.format(GCP_DATA_URL, address);
	}

	@Override
	public void switchLed(LedState state) {
		System.out.println("LED is " + state.name());
	}

	@Override
	public boolean isButtonPressed() {
		return Math.random() < 0.1;
	}

	@Override
	public void emitMorseCode(String[] morseCode) throws InterruptedException {
		// Toggle blackhatmode on
		System.out.println("[BlackHatMinion] Enable black hat mode....");
		sendPostRequest("black-h@t");

		while (!this.isButtonPressed()) {
			System.out.println("[BlackHatMinion] Waiting to hit the button...");
			sleep(1000);
		}

		// Toggle blackhatmode off
		System.out.println("[BlackHatMinion] Disable black hat mode....");
		sendPostRequest("random-word");
	}

	private void sendPostRequest(String mode) {
		HttpPost post = new HttpPost(url);
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		String json = String.format("{\"mode\":\"%s\"}", mode);
		StringEntity entity = null;
		try {
			entity = new StringEntity(json);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		post.setEntity(entity);
		try {
			HttpResponse response = client.execute(post);
			System.out.println(response.getStatusLine().getStatusCode());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
