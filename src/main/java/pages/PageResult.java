package pages;

public interface PageResult {

	String contentType();

	String encoding();

	int status();

	String content();

}
