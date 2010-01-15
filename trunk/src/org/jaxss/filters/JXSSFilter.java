package org.jaxss.filters;

import com.jaxss.error.JXSSError;
import com.jaxss.exception.JXSSFilterException;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jaxss.util.Types;

import sun.text.Normalizer;

/**
 *
 * @author Chander Singh
 * @created.on December 27, 2007
 */
public class JXSSFilter extends HttpServlet {
    private static final long serialVersionUID = 2157331544630602943L;
	//These are the characters which we want to filter.
    private String filterCharsParamName = "<>?*";
    private String filterCharsParamValue = "<>?*";    

    private String filterCharsCookieName = "<>?*";
    private String filterCharsCookieValue = "<>?*";    
    
    private String filterCharsReqHeaderName = "<>?*";
    private String filterCharsReqHeaderValue = "<>?*";    
    
    //These are the characters which will get replaced with the subsequent character in the same position from filterChars array.
    //private String[] replacementChars = {" ", " ", " ", " ", "#", "'", "/", "0 x"};

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {        
        JXSSFilterException jxssFilterException = new JXSSFilterException ();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String parametername = (String) enumeration.nextElement();
            String invalidchars = filterParamName (parametername);
            if (invalidchars != null && !"".equals (invalidchars.trim())) {
                JXSSError jxssError = new JXSSError (parametername, Types.PARAMNAME, invalidchars);
                jxssFilterException.addError(jxssError);
            }
            String[] parametervalues = request.getParameterValues (parametername);
            invalidchars = filterParamValues (parametervalues);
            if (invalidchars != null && !"".equals (invalidchars.trim())) {
                JXSSError jxssError = new JXSSError (parametername, Types.PARAMVAL, invalidchars);
                jxssFilterException.addError(jxssError);
            }
        }
        Cookie[] cookies = request.getCookies();
        for (int cnt = 0; cnt < cookies.length; cnt++) {
            Cookie cookie = cookies[cnt];
            String cookiename = cookie.getName ();
            String invalidchars = filterCookieName (cookiename);
            if (invalidchars != null && !"".equals (invalidchars.trim())) {
                JXSSError jxssError = new JXSSError (cookiename, Types.COOKIENAME, invalidchars);
                jxssFilterException.addError(jxssError);
            }
            String cookievalue = cookie.getValue ();
            invalidchars = filterCookieName (cookievalue);
            if (invalidchars != null && !"".equals (invalidchars.trim())) {
                JXSSError jxssError = new JXSSError (cookievalue, Types.COOKIEVAL, invalidchars);
                jxssFilterException.addError(jxssError);
            }
        }
        enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String reqheadername = (String) enumeration.nextElement();
            String invalidchars = filterReqHeaderName (reqheadername);
            if (invalidchars != null && !"".equals (invalidchars.trim())) {
                JXSSError jxssError = new JXSSError (reqheadername, Types.REQHEADERNAME, invalidchars);
                jxssFilterException.addError(jxssError);
            }
            String reqheadervalue = request.getHeader (reqheadername);
            invalidchars = filterReqHeaderValue (reqheadervalue);
            if (invalidchars != null && !"".equals (invalidchars.trim())) {
                JXSSError jxssError = new JXSSError (reqheadervalue, Types.REQHEADERVAL, invalidchars);
                jxssFilterException.addError(jxssError);
            }
        }
        
        response.setContentType("text/html;charset=UTF-8");
        if (!jxssFilterException.isEmpty ()) {
            throw new ServletException (jxssFilterException);
        }
    }
    
    protected String filterUrl(String url) {
        StringBuffer sb = new StringBuffer(url);        
        /*Loop through the entire string and check for as many instances of the character in the parameter string.
         *If found, then replace it with the replacement character.
         */
        for(int position = url.toLowerCase().indexOf("http://"); position >= 0; )  {
            sb.replace(position, position + "http://".length(), "/");
            url = sb.toString();
            position = url.toLowerCase().indexOf("http://");
        }        
        return sb.toString();
    }    
    
    /*
     *The actual function which checks for the character to be filtered and replaces it with the replacement character.
     *@param originalChar character which we want to filter.
     *@param newChar character which we want to replace it with, if found in the parameter.
     *@param param the parameter from the query string.
     *@return updated parameter after the characters which we want to filter are replaced (if found).
     */
    private String filterCharacters(String originalChar, String newChar, String param) {
        StringBuffer sb = new StringBuffer(param);        
        /*Loop through the entire string and check for as many instances of the character in the parameter string.
         *If found, then replace it with the replacement character.
         */
        for(int position = param.toLowerCase().indexOf(originalChar); position >= 0; )  {
            sb.replace(position, position + originalChar.length(), newChar);
            param = sb.toString();
            position = param.toLowerCase().indexOf(originalChar);
        }        
        return sb.toString();
    }

   /**
    * Simplifies input to its simplest form to make encoding tricks more difficult
    **/
   private String canonicalize( String input ) {
       String canonical = Normalizer.normalize(input, Normalizer.DECOMP, 0);
       return canonical;
   }

    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    private String filterParamName (String data) {
        String invalidchars = "";
        for (int cnt = 0; cnt < data.length() - 1; cnt++) {
            String string = data.substring(cnt, cnt+1);
            if (filterCharsParamName.indexOf(string) > -1) {
                invalidchars += string;
            }
        }
        return invalidchars;
    }
    
    private String filterParamValues (String[] datas) {
        String invalidchars = "";
        for (int count = 0; count < datas.length; count++) {
            String data = datas[count];
            for (int cnt = 0; cnt < data.length() - 1; cnt++) {
                String string = data.substring(cnt, cnt+1);
                if (filterCharsParamValue.indexOf(string) > -1) {
                    invalidchars += string;
                }
            }
        }
        return invalidchars;
    }
    
    private String filterCookieName (String data) {
        String invalidchars = "";
        for (int cnt = 0; cnt < data.length() - 1; cnt++) {
            String string = data.substring(cnt, cnt+1);
            if (filterCharsCookieName.indexOf(string) > -1) {
                invalidchars += string;
            }
        }
        return invalidchars;
    }
    
    private String filterCookieValue (String data) {
        String invalidchars = "";
        for (int cnt = 0; cnt < data.length() - 1; cnt++) {
            String string = data.substring(cnt, cnt+1);
            if (filterCharsCookieValue.indexOf(string) > -1) {
                invalidchars += string;
            }
        }
        return invalidchars;
    }
    
    private String filterReqHeaderName (String data) {
        String invalidchars = "";
        for (int cnt = 0; cnt < data.length() - 1; cnt++) {
            String string = data.substring(cnt, cnt+1);
            if (filterCharsReqHeaderName.indexOf(string) > -1) {
                invalidchars += string;
            }
        }
        return invalidchars;
    }
    
    private String filterReqHeaderValue (String data) {
        String invalidchars = "";
        for (int cnt = 0; cnt < data.length() - 1; cnt++) {
            String string = data.substring(cnt, cnt+1);
            if (filterCharsReqHeaderValue.indexOf(string) > -1) {
                invalidchars += string;
            }
        }
        return invalidchars;
    }    
}
