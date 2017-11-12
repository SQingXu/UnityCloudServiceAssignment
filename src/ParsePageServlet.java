

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.nodes.Element;
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
	String url = "https://unity3d.com/showcase/gallery";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParsePageServlet() {
        super();
    }
    
    
    /* This function handle the rendering content retrieved from the source page */
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	PrintWriter out = response.getWriter();
    	
    	/* print out the game content part */
    	out.print("<HTML>");
    	Element out_str;
		out_str = pp.getUlElement("div.game-list-wrap.clear");
    	out.print(out_str);
    	out.println("</html>");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(5);
		response.setContentType("text/plain");
		//update the source when the current session expires
		if(session.isNew()){
			pp.updateDocument(url);
		}
		
	}
	
	@Override
	public void init() throws ServletException{
		pp = new ParsePage();
		pp.updateDocument(url);
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
