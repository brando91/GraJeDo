package utilities;

import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;

public class ServerResponse {

	private CloseableHttpResponse response;

	public ServerResponse(CloseableHttpResponse response) {
		this.response = response;
	}

	public int statusCode() {
		return this.response.getStatusLine().getStatusCode();
	}

	public String body() throws Exception {
		return IOUtils.toString(this.response.getEntity().getContent(), StandardCharsets.UTF_8);
	}

}
