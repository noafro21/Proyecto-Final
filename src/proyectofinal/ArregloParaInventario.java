package proyectofinal;

public class ArregloParaInventario {

    public String codigo, marca, modelo;
    public double precio;
    public int cantidad;

    public ArregloParaInventario(String codigo, String marca, String modelo, double precio, int cantidad) {
        
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.cantidad = cantidad;
    }

   

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
     @Override
        public String toString() {
            return "Código: " + codigo + ", Marca: " + marca + ", Modelo: " + modelo + ", Precio: $" + precio + ", Cantidad: " + cantidad;
        }
    }

  
    
    
    


