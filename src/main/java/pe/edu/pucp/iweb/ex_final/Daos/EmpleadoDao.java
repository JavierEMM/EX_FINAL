package pe.edu.pucp.iweb.ex_final.Daos;

import pe.edu.pucp.iweb.ex_final.Beans.Cine;
import pe.edu.pucp.iweb.ex_final.Beans.Empleado;


import java.sql.*;
import java.util.ArrayList;

public class EmpleadoDao extends BaseDao{

    public ArrayList<Empleado> listaEmpleador(){
        ArrayList<Empleado> listaEmpleado = new ArrayList<>();

        try(Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM movies.empleado e\n" +
                    "left join movies.cine c on c.idcine = e.idcine\n" +
                    "left join movies.empleado m on e.idjefe = m.idempleado;");){

            while (rs.next()){

                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellido(rs.getString(3));
                empleado.setDni(rs.getString(4));
                empleado.setSalario(rs.getBigDecimal(5));
                empleado.setFechaContrato(rs.getString(6));
                empleado.setNombreUsuario(rs.getString(7));
                empleado.setEdad(rs.getInt(8));
                empleado.setActivo(rs.getBoolean(9));

                Cine cinecito = new Cine();
                cinecito.setIdCine(rs.getInt(10));
                cinecito.setNombre(rs.getString(13));
                empleado.setCine(cinecito);

                Empleado jefe = new Empleado();
                jefe.setIdEmpleado(rs.getInt(11));
                jefe.setNombre(rs.getString(16));
                jefe.setApellido(rs.getString(17));
                empleado.setJefe(jefe);

                listaEmpleado.add(empleado);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return  listaEmpleado;
    }

    public Empleado obtenerEmpleado(int empleadoId) throws SQLException{

        Empleado empleado = null;

        String sql = "SELECT * FROM empleado e WHERE idempleado = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){

            pstmt.setInt(1,empleadoId);

            try(ResultSet rs = pstmt.executeQuery();){

                if(rs.next()){

                    empleado = new Empleado();
                    empleado.setIdEmpleado(rs.getInt(1));
                    empleado.setNombre(rs.getString(2));
                    empleado.setApellido(rs.getString(3));
                    empleado.setDni(rs.getString(4));
                    empleado.setSalario(rs.getBigDecimal(5));
                    empleado.setFechaContrato(rs.getString(6));
                    empleado.setNombreUsuario(rs.getString(7));
                    empleado.setEdad(rs.getInt(8));
                    empleado.setActivo(rs.getBoolean(9));

                    Cine cine = new Cine();
                    cine.setIdCine(rs.getInt(10));
                    empleado.setCine(cine);

                    Empleado jefe = new Empleado();
                    jefe.setIdEmpleado(rs.getInt(11));
                    empleado.setJefe(jefe);
                }
            }
        }return empleado;
    }

    public  Empleado validarUsuario(String nombreUsuario,String contrasenia){

        Empleado empleado = null;

        String sql = "SELECT (dni - salario) as contrasenia FROM movies.empleado WHERE dni = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, nombreUsuario);

            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    int employeeId = rs.getInt(1);
                    empleado = this.obtenerEmpleado(employeeId);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return empleado;
    }

}
