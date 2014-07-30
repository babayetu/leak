package tomcatstudy;

public class StaticResourceProcessor {
	public void process (RequestCh2 req, ResponseCh2 resp) {
		resp.sendStaticResponse();
	}
}