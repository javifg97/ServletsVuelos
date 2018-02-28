/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.net.Proxy.Type.HTTP;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import modelo.Vuelos;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


/**
 *
 * @author javier.fernandez3
 */
@WebServlet(name = "MiServlet", urlPatterns = {"/MiServlet"})
public class MiServlet extends HttpServlet {

    @EJB
    session aEJB;

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
            /*out.println("<html>");
            out.println("<head>");
            out.println(
            "<title>Prueba Servlet con EJB hterfghgfjjfj</title >");
 out.println("</head>");
            out.println("<body>");*/
            //List<Vuelos> l = aEJB.findAll();
            
            String origen;
            String destino;
            String fecha;
            
            StringBuffer jb = new StringBuffer();
  String line = null;
  try {
    BufferedReader reader = request.getReader();
    while ((line = reader.readLine()) != null)
      jb.append(line);
  } catch (Exception e) { /*report an error*/ }

  
    //JSONObject jsonObject = HTTP.    HTTP.toJSONObject(jb.toString());
  
            //out.println(jb.toString());
  
            JSONObject peticionjson = (JSONObject) JSONValue.parse(jb.toString());
            //out.println(peticionjson.toJSONString());
            String peticion = peticionjson.get("peticion").toString();
            
            //out.println(peticion);
            
            if(peticion.equals("buscar")){
                
            
              
                      JSONObject vuelos = (JSONObject) peticionjson.get("vueloBuscar");
            
            //out.println(vuelos);
                      
            
            
            origen=(String)vuelos.get("origen");
            destino=(String)vuelos.get("destino");
            fecha=(String)vuelos.get("fecha");
            
            //out.println(origen +"  "+ destino +"  "+ fecha);
            
            List<Vuelos> l = aEJB.findByCriteria(origen,destino,fecha);
            
            // bucle para recorrer la lista que corresponda 
            JSONArray arrayPadre = new JSONArray();
            for (int i = 0; i < l.size(); i++) {
                //out.println("<b>Origen:</b>" + l.get(i).getOrigen()+ ", <b>Destino </b>" + l.get(i).getDestino() + ", <b>Precio </b>" + l.get(i).getPrecio()+ "<br>");
                
                JSONObject jsonHijo = new JSONObject();
                jsonHijo.put("idVuelo", l.get(i).getIdVuelo());
                jsonHijo.put("diayhora", l.get(i).getDiayhora());
                jsonHijo.put("origen", l.get(i).getOrigen());
                jsonHijo.put("destino", l.get(i).getDestino());
                jsonHijo.put("precio", l.get(i).getPrecio());
                jsonHijo.put("plazas_totales", l.get(i).getPlazasTotales());
                jsonHijo.put("plazas_libres", l.get(i).getPlazasLibres());
                
             arrayPadre.add(jsonHijo);
            }
            out.println(arrayPadre.toJSONString());
                  
            }else if(peticion.equals("leer")){
                List<Vuelos> l = aEJB.findAll();
                JSONArray arrayPadre = new JSONArray();
            for (int i = 0; i < l.size(); i++) {
                //out.println("<b>Origen:</b>" + l.get(i).getOrigen()+ ", <b>Destino </b>" + l.get(i).getDestino() + ", <b>Precio </b>" + l.get(i).getPrecio()+ "<br>");
                
                JSONObject jsonHijo = new JSONObject();
                jsonHijo.put("idVuelo", l.get(i).getIdVuelo());
                jsonHijo.put("diayhora", l.get(i).getDiayhora());
                jsonHijo.put("origen", l.get(i).getOrigen());
                jsonHijo.put("destino", l.get(i).getDestino());
                jsonHijo.put("precio", l.get(i).getPrecio());
                jsonHijo.put("plazas_totales", l.get(i).getPlazasTotales());
                jsonHijo.put("plazas_libres", l.get(i).getPlazasLibres());
                
             arrayPadre.add(jsonHijo);
            }
            out.println(arrayPadre.toJSONString());
            }
            /*out.println("</body>");
            out.println("</html>");*/
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
