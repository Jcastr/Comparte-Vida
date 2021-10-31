
package restful.Model;

import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class DonanteModel {
    private String nombres, apellidos,
            tipo_documento, numero_documento,
            genero, RH, telefono,
            correo, pwd;

    public DonanteModel() {
    }

    public DonanteModel(String nombres, String apellidos, String tipo_documento, String numero_documento, String genero, String RH, String telefono, String correo, String pwd) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipo_documento = tipo_documento;
        this.numero_documento = numero_documento;
        this.genero = genero;
        this.RH = RH;
        this.telefono = telefono;
        this.correo = correo;
        this.pwd = pwd;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRH() {
        return RH;
    }

    public void setRH(String RH) {
        this.RH = RH;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
}
