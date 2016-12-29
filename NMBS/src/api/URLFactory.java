package api;

import java.net.MalformedURLException;
import java.net.URL;

public class URLFactory {
	private String baseURL;
	private String file;
	private StringBuffer query;

	public URLFactory(String baseURL, String file) {
		this.baseURL = baseURL;
		this.file = file;
		this.query = new StringBuffer();
	}

	public URLFactory addQuery(String label, String value) {
		if (this.query.length() != 0) {
			this.query.append("&");
		}
		this.query.append(label);
		this.query.append("=");
		this.query.append(value);
		return this;
	}

	public URL getURL() throws MalformedURLException {
		StringBuilder variablePart = new StringBuilder(this.file);
		if (this.query.length() > 0) {
			variablePart.append("?");
			variablePart.append(this.query);
		}

		System.err.println(variablePart.toString());

		return new URL(new URL(this.baseURL), variablePart.toString());
	}
}