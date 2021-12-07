package pe.edu.pucp.iweb.ex_final.Daos;

import pe.edu.pucp.iweb.ex_final.Beans.Cartelera;
import pe.edu.pucp.iweb.ex_final.Beans.Cine;
import pe.edu.pucp.iweb.ex_final.Beans.Pelicula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

    public class CarteleraDao extends BaseDao{
        public ArrayList<Cartelera> listar(){
            ArrayList<Cartelera> carteleras = null;
            String sql="SELECT ca.idCartelera,pe.*,ci.*,ca.3d,ca.doblada,ca.subtitulada,ca.horario FROM cine ci INNER JOIN cartelera ca ON ca.idcine = ci.idcine INNER JOIN pelicula pe ON pe.idpelicula = ca.idpelicula";
            try(Connection conn =  this.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
                while (rs.next()){
                    Cartelera cartelera = new Cartelera();
                    Pelicula pelicula =  new Pelicula();
                    Cine cine = new Cine();
                    cartelera.setIdCartelera(rs.getInt(1));
                    pelicula.setIdPelicula(rs.getInt(2));
                    pelicula.setNombre(rs.getString(3));
                    cine.setIdCine(rs.getInt(4));
                    cine.setNombre(rs.getString(5));
                    cartelera.setPelicula(pelicula);
                    cartelera.setCine(cine);
                    cartelera.setTresD(rs.getInt(7));
                    cartelera.setDoblada(rs.getInt(8));
                    cartelera.setSubtitulada(rs.getInt(9));
                    cartelera.setHorario(rs.getString(10));
                    carteleras.add(cartelera);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return carteleras;
        }
    }
