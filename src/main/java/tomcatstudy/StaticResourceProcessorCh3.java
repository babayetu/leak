package tomcatstudy;


public class StaticResourceProcessorCh3 {
	public void process (HttpRequestCh3 req, HttpResponseCh3 resp) {
		resp.sendStaticResponse();
	}
}