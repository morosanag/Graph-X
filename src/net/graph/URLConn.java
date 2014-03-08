package net.graph;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class URLConn{
	public static String theText= "";
	public static int nrLines=0;
	public static String theResult;


	public static String changeText(String text)
	{
		String[] lines = text.split("\r\n|\r|\n");
		String result = "";
		int k=0;

		while(!lines[k].contains("/EUR</title>"))
		{
			k++;
		}

		while (k<lines.length )
		{
			if(!lines[k].contains("http://") && !lines[k].contains(" GMT"))
			{
				result = result + lines[k]+"\n";
			}
			k++;
		}

		result=result.replaceAll("\\<.*?>","");
		result=result.replaceAll("(?m)^[ \t]*\r?\n", "");

		return result;
	}

	public static int countLines(String str)
	{
		String[] lines = str.split("\r\n|\r|\n");
		return  lines.length;
	}

	public static String getContents() throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient(); // Create HTTP Client
		HttpGet httpget = new HttpGet("http://themoneyconverter.com/rss-feed/EUR/rss.xml"); // Set the action you want to do
		HttpResponse response = httpclient.execute(httpget); // Executeit
		HttpEntity entity = response.getEntity(); 
		InputStream is = entity.getContent(); // Create an InputStream with the response
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) // Read line by line
			sb.append(line + "\n");

		String resString = sb.toString(); // Result is here

		is.close(); // Close the stream
		return resString;
	}


}
