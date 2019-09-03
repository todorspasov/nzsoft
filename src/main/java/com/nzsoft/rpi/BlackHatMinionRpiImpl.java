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

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public final class BlackHatMinionRpiImpl implements Minion {
	private static final String GCP_DATA_URL = "http://%s:8080/v1/data";
	private HttpClient client = HttpClientBuilder.create().build();
	private String url;

	// For pin numbering (RPi 3, Model B), see:
	// http://pi4j.com/pins/model-3b-rev1.html
	private static final Pin LED_OUTPUT_PIN = RaspiPin.GPIO_25;
	private static final Pin BUTTON_INPUT_PIN = RaspiPin.GPIO_01;

	private final GpioPinDigitalInput button;
	private final GpioPinDigitalOutput led;

	public BlackHatMinionRpiImpl() {
		this.url = String.format(GCP_DATA_URL, "127.0.0.1");
		final GpioController gpio = GpioFactory.getInstance();
		button = gpio.provisionDigitalInputPin(BUTTON_INPUT_PIN, PinPullResistance.PULL_UP);
		led = gpio.provisionDigitalOutputPin(LED_OUTPUT_PIN, "MinionLED", PinState.LOW);
		led.setShutdownOptions(true, PinState.LOW);
	}

	protected BlackHatMinionRpiImpl(String address) {
		this.url = String.format(GCP_DATA_URL, address);
		final GpioController gpio = GpioFactory.getInstance();
		button = gpio.provisionDigitalInputPin(BUTTON_INPUT_PIN, PinPullResistance.PULL_UP);
		led = gpio.provisionDigitalOutputPin(LED_OUTPUT_PIN, "MinionLED", PinState.LOW);
		led.setShutdownOptions(true, PinState.LOW);
	}

	@Override
	public void switchLed(LedState state) {
		switch (state) {
		case ON:
			led.high();
			System.out.println("LED ON");
			break;
		case OFF:
			led.low();
			System.out.println("LED OFF");
			break;
		}
	}

	@Override
	public boolean isButtonPressed() {
		return button.getState() == PinState.LOW;
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
