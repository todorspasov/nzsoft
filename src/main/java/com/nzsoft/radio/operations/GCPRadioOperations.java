package com.nzsoft.radio.operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import com.nzsoft.radio.signal.RadioSignal;

public class GCPRadioOperations implements RadioOperations {

	private static final String GCP_DATA_URL = "http://%s:8080/v1/data";
	private String url;

	public GCPRadioOperations() {
		this.url = String.format(GCP_DATA_URL, "127.0.0.1");
	}

	public GCPRadioOperations(String address) {
		this.url = String.format(GCP_DATA_URL, address);
	}

	@Override
	public RadioSignal getSignal() {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		try {
			HttpResponse response = client.execute(request);
			String line;
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			while ((line = rd.readLine()) != null) {
				JSONObject jsonObj = new JSONObject(line);
				System.out.println("Got signal: id = " + jsonObj.get("id") + ", data = " + jsonObj.get("data"));
				return new RadioSignal(jsonObj.getString("id"), jsonObj.getString("data"));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Priority getSignalPriority(String signalId) {
		return null;
	}

	@Override
	public void markSignalReceived(String signalId) {

	}

}
