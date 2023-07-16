package calculadoraareavolumen;

import static java.lang.Math.PI;

public class cilindro {

    double radio;
    double altura;

    public cilindro(double radio, double altura) {
        this.altura = altura;
        this.radio = radio;
    }

    public double area() {
        double area = (2 * PI * radio * altura) + (2 * PI * radio * radio);
        System.out.println("El area del Cilindro es: " + area + " metros cuadrados");
        return area;
    }

    public double volumen() {
        double volumen = (PI * radio * altura);
        System.out.println("El volumen del cilindro es: " + volumen + " metros cubicos");
        return volumen;
    }
    
    

}
