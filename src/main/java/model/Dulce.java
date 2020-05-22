package model;

public class Dulce {


    private String identificacion;
    private String nombre;
    private double precio;
    private String marca;

    public Dulce(String identificacion, String nombre, double precio, String marca) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;

    }


    public Dulce(){}
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }




}
