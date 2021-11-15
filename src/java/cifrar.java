

import java.io.IOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/ProcesoArchivo")
@MultipartConfig
public class cifrar extends HttpServlet {

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
        String tipo = request.getParameter("tipo");
        String llave = request.getParameter("llave");
        InputStream inputStream = null;
        Part filePart = request.getPart("file_2");
            if (filePart.getSize() > 0) {
                inputStream = filePart.getInputStream();
            }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        Stream<String> streamOfString= new BufferedReader(inputStreamReader).lines();
        String streamToString = streamOfString.collect(Collectors.joining());
        String textocifrado = "";
        int seguir = 0;
        if(tipo.equals("128")){
            if(llave.length() == 16) seguir =1;
        }else if(tipo.equals("192")){
            if(llave.length() == 24) seguir =1;
        }else if (tipo.equals("256")){
            if(llave.length() == 32) seguir =1;
        }
        boolean ss = false;
        if(seguir != 0){
            try{
                textocifrado = CodigoCifrar.encrypt(streamToString, llave);
                ss = GestionarFirma.firmar(textocifrado);
            }catch(Exception e){
                System.out.println(e.getMessage());
                response.sendRedirect("index.html");
                }
            PrintWriter bufferr = response.getWriter(); 
            String filename = "DocumentoFirmadoyCifrado.txt";
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
            bufferr.write(textocifrado);
            bufferr.close();
        }else{
            response.sendRedirect("index.html");
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
        processRequest(request, response);
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
