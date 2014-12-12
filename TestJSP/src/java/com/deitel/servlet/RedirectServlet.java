package com.deitel.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MdMahadiHasan
 */
public class RedirectServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RedirectServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RedirectServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        String location = request.getParameter("page");
        
        if(location != null) {
            if(location.equals("deitel")){
                response.sendRedirect("http://www.deitel.com");
            }
            else if(location.equals("welcome1")) {
                response.sendRedirect("index");
            }
            
        }
        
        
        //----------------------------------
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        out.println("<?xml version = \"1.0\"?>");
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD "
                + "XHTML 1.0 Strict//EN\" \"http://www.w3.org"
                + "/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        out.println("<html xmlns = \"http://www.w3.org/1999/xhtml\">");
        out.println("<head>");
        out.println("<title>Invalid page</title>");
        out.println("</head>");
        
        
        out.println("<body>");
        out.println("<h1>Invalid page requested</h1>");
        out.println("<p><a href = "
                + "\"RedirectServlet.html\">");
        out.println("Click Here To Choose Again</a></p>");
        out.println("</body>");
        
        
        out.println("</html>");
        out.close();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
