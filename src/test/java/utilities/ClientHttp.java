package utilities;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class ClientHttp {

	private int port;

	public ServerResponse get(String path) throws Exception{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:" + this.port + "/" + path);
		return new ServerResponse(client.execute(request));
	}

	public ClientHttp locally() {
		this.port = 8080;
		return this;
	}

	public ClientHttp production() {
		this.port = 80;
		return this;
	}
}
