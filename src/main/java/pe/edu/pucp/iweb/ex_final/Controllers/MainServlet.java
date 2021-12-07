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
        String contrasena = request.getParameter("contrasena");

        EmpleadoDao emppleadoDao = new EmpleadoDao();
        HttpSession session = request.getSession();
        Empleado empleado = null;
        try {
            empleado = emppleadoDao.obtenerEmpleado(Integer.parseInt(dniStr));
        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
        }

        Rol rol = empleado.getRoles().get(0);

        if(rol.getNombre().equalsIgnoreCase("admin")){

        }else if (rol.getNombre().equalsIgnoreCase("gestor")){

        }else if(rol.getNombre().equalsIgnoreCase("vendedor")){

        }

    }
}
