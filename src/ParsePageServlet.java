

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.*;

import java.io.IOException;
import java.io.PrintWriter;


import com.webappjava2.ParsePage;
/**
 * Servlet implementation class FileCounter
 */
@WebServlet("/ParsePageServlet")
public class ParsePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	int count;
	private ParsePage pp;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParsePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	HttpSession session = request.getSession(true);
    	Integer ival = (Integer)session.getAttribute("mysession.counter");
    	if(ival == null){
    		ival = new Integer(1);
    	}else{
    		ival = new Integer(ival.intValue() + 1);
    	}
    	session.setAttribute("mysession.counter", ival);
    	PrintWriter out = response.getWriter();
    	
    	out.print("<HTML>");
    	//out.print("<center> you have hit this page ");
    	//out.print(ival + " times! ");
    	Element out_str;
		out_str = pp.parseURLJsoup();
    	out.print(out_str);
    	out.println("</html>");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(5);
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		if(session.isNew()){
			count++;
		}
		out.println("This site has been accessed " + count + " times.");
		
	}
	
	@Override
	public void init() throws ServletException{
		pp = new ParsePage();
	}
	
	@Override
	public void destroy(){
		super.destroy();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
