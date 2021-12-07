package pe.edu.pucp.iweb.ex_final.Controllers;

import pe.edu.pucp.iweb.ex_final.Beans.Empleado;
import pe.edu.pucp.iweb.ex_final.Beans.Rol;
import pe.edu.pucp.iweb.ex_final.Daos.EmpleadoDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MainServlet", value = "/MainServlet")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dniStr = request.getParameter("dni");
        String contrasenaStr = request.getParameter("contrasena");
        int dni = Integer.parseInt(dniStr);
        Double contrasena = Double.parseDouble(contrasenaStr);
        EmpleadoDao emppleadoDao = new EmpleadoDao();
        HttpSession session = request.getSession();

        if(emppleadoDao.validarUsuario(dniStr,contrasenaStr)!=null){
            Empleado empleado = null;
            try {
                empleado = emppleadoDao.obtenerEmpleado(dni);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Rol rol = empleado.getRoles().get(0);

            if(rol.getNombre().equalsIgnoreCase("admin")){

            }else{
                response.sendRedirect(request.getContextPath() + "/CarteleraServlet");
            }

        }else{
            session.setAttribute("error","Error al iniciar sesion");
            response.sendRedirect(request.getContextPath());
        }




        }


}
