package calculadoraareavolumen;

public class cubo {

    double alto;
    double ancho;
    double profundidad;

    public cubo(double alto, double ancho, double profundidad) {
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;

    }
    
    public double area(){
        double area = (2 * ancho * profundidad) + (4 * alto * ancho);
        System.out.println("El area del cubo es: " + area + " metros cuadrados");
        return area;
    }
    
    public double volumen(){
        double volumen = (ancho * profundidad) * alto;
        System.out.println("El volumen del cubo es: " + volumen + " metros cubicos");
        return volumen;
    }

}
