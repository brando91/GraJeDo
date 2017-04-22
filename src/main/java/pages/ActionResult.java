package pages;

import java.nio.charset.StandardCharsets;

import org.eclipse.jetty.http.HttpStatus;

import core.ContentTypes;

public class ActionResult implements PageResult{

	private int status;
	private String content;
	
	public ActionResult(String content, int status) {
		this.content = content;
		this.status = status;
	}

	public ActionResult(String content) {
		this(content, HttpStatus.OK_200);
	}

	@Override
	public String encoding() {
		return StandardCharsets.UTF_8.toString();
	}

	@Override
	public String contentType() {
		return ContentTypes.Html;
	}

	@Override
	public int status() {
		return this.status;
	}

	@Override
	public String content() {
		return this.content;
	}

}
