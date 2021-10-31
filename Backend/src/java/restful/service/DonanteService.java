package restful.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import restful.Model.DonanteModel;
import restful.Model.Conexion;

public class DonanteService {
    public ArrayList<DonanteModel> getDonantes() {
        ArrayList<DonanteModel> lista = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM donante";

        try {
            Statement stm = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                //Crear un objeto de clase Donante
                DonanteModel donante = new DonanteModel();
                donante.setNombres(rs.getString("nombres"));
                donante.setApellidos(rs.getString("apellidos"));
                donante.setTipo_documento(rs.getString("tipo_documento"));
                donante.setNumero_documento(rs.getString("numero_documento"));
                donante.setGenero(rs.getString("genero"));
                donante.setRH(rs.getString("RH"));
                donante.setTelefono(rs.getString("telefono"));
                donante.setCorreo(rs.getString("email"));
                donante.setPwd(rs.getString("password"));
                //Agregar el objeto al arreglo de objetos
                lista.add(donante);
            }
        } catch (SQLException e) {
        }
        return lista;
    }
    
    public DonanteModel getDonante(String id) {
        DonanteModel donante = new DonanteModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM donante WHERE numero_documento = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                //Crear el objeto
                donante.setNombres(rs.getString("nombres"));
                donante.setApellidos(rs.getString("apellidos"));
                donante.setTipo_documento(rs.getString("tipo_documento"));
                donante.setNumero_documento(rs.getString("numero_documento"));
                donante.setGenero(rs.getString("genero"));
                donante.setRH(rs.getString("RH"));
                donante.setTelefono(rs.getString("telefono"));
                donante.setCorreo(rs.getString("email"));
                donante.setPwd(rs.getString("password"));
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return donante;
    }
    public DonanteModel addDonante(DonanteModel donante) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO donante (nombres,apellidos,tipo_documento,numero_documento,";
        Sql = Sql + "genero,RH,telefono,email,password)";
        Sql = Sql + "values (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setString(1, donante.getNombres());
            pstm.setString(2, donante.getApellidos());
            pstm.setString(3, donante.getTipo_documento());
            pstm.setString(4, donante.getNumero_documento());
            pstm.setString(5, donante.getGenero());
            pstm.setString(6, donante.getRH());
            pstm.setString(7, donante.getTelefono());
            pstm.setString(8, donante.getCorreo());
            pstm.setString(9, donante.getPwd());
            
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return donante;
    }
    
    public DonanteModel updateDonante(DonanteModel donante) {
        Conexion conn = new Conexion();
        String sql = "UPDATE donante SET nombres=?, apellidos=?, tipo_documento=?,";
        sql = sql + "genero=?, RH=?, telefono=?, email=?, password=? WHERE numero_documento=?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setString(1, donante.getNombres());
            pstm.setString(2, donante.getApellidos());
            pstm.setString(3, donante.getTipo_documento());
            pstm.setString(4, donante.getGenero());
            pstm.setString(5, donante.getRH());
            pstm.setString(6, donante.getTelefono());
            pstm.setString(7, donante.getCorreo());
            pstm.setString(8, donante.getPwd());
            pstm.setString(9, donante.getNumero_documento());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al modificar  " + excepcion.getMessage());
            return null;
        }
        return donante;
    }
    
    public String delDonante(String id) {
        Conexion conn = new Conexion();

        String sql = "DELETE FROM donante WHERE numero_documento= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setString(1, id);
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return "{\"Accion\":\"Error\"}";
        }
        return "{\"Accion\":\"Registro Borrado\"}";
    }
    
}
