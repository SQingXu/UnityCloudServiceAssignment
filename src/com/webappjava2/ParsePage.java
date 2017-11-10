package com.webappjava2;
import java.io.File;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class ParsePage {
	public String parseURL(){
		String rt = "";
		try{
			URL url = new URL("https://unity3d.com/showcase/gallery");
			URLConnection uc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine;
			if((inputLine = in.readLine())!= null){
				rt = inputLine;
				System.out.println(rt);
			}
			in.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		return rt;
	}
	//public static void main(String[] args){
	public Element parseURLJsoup(){
		Element rt = null;
		Document doc;
		Elements div;
		Elements ul;
		try {
			doc = Jsoup.connect("https://unity3d.com/showcase/gallery").get();
			div = doc.select("div.game-list-wrap.clear");
			ul = doc.select("div.game-list-wrap.clear > ul");
			rt = ul.select("li").get(0);
			//rt = ul;
			//System.out.println(rt);
			//System.out.println("uuuuuu");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rt;
	}
}

