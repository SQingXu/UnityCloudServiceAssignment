package com.webappjava2;
import java.io.IOException;
import java.util.*;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class ParsePage {
	Document doc;
	List<Integer> rand_list; 
	int rand_ptr;
	int pre_index;
	
	/* constructor initialize everything to previous value */
	public ParsePage(){
		this.doc = null;
		this.rand_list = null;
		this.rand_ptr = 0; 
		this.pre_index = -1;
	}
	/* This function will pass in the intended URL via argument
	 * url and update the instance variable doc of type Document
	 * IOException is handled by printing out stackTrace */
	public void updateDocument(String url)
	{
		try{
			doc = Jsoup.connect(url).get();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/* This function will return a random element from a given unordered list
	 * of the page which is stored in Document type with a given index; 
	 * div_class: the string name of div immediately contained the 
	 * unordered list 
	 * */
	public Element getUlElement(String div_class){
		Element rt = null;
		Elements ul;
		if(doc == null){
			return rt;
		}
		/*construct query string by concatenate two strings
		 * to find the unordered list under the class defined by
		 * variable div_class
		*/
		String query_str = div_class + " > ul";
		ul = doc.select(query_str).select("li");
		if(ul == null){
			return rt;
		}
		
		/* query the randomIndex function for next index with known
		 * size of unordered list */
		int size = ul.size();
		int i = randomIndex(size);
		rt = ul.get(i);
		return rt;
	}
	
	/* This function return a new value of index and make 
	 * it does not go out of bounds or repeat until all games are shown.
	 * range: the (0-range) the random index should generate within */
	private int randomIndex(int range){
		//error check for invalid range
		if(range <= 1){
			return 0;
		}
		
		//first random number generation, initialization needed
		if(rand_list == null){
			rand_list = initRandList(range);
			rand_ptr = 0;
		}
		
		//source content changed or one loop complete, re-initialization needed
		if(rand_list.size() != range || rand_ptr >= range){
			rand_ptr = 0;
			rand_list = initRandList(range);
		}
		
		int rt = rand_list.get(rand_ptr);
		//if there are consecutive repeated index, retrieve a new element
		if(rt == pre_index){
			int removed = rand_list.remove(rand_ptr);
			rand_list.add(removed);
			rt = rand_list.get(rand_ptr);
		}
		pre_index = rt;
		rand_ptr++;
		return rt;
	}
	
	/* This function initialize a new shuffled list with all integers
	 * from range 0 to given up-bound, used for generate random index
	 * range: up-bound of the range */
	private List<Integer> initRandList(int range){
		List<Integer> rt = new ArrayList<Integer>(range);
		for(int i = 0; i < range; i++){
			rt.add(i);
		}
		Collections.shuffle(rt);
		return rt;
	}
	
}

