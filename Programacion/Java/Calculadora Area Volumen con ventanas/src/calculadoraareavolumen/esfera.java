package calculadoraareavolumen;

import static java.lang.Math.PI;

public class esfera {

    double radio;

    public esfera(double radio) {
        this.radio = radio;

    }
    
    public double area(){
        double area = (4 * PI * radio * radio);
        System.out.println("El area de la esfera es: " + area + " metros cuadrados");
        return area;
    }
    public double volumen(){
        double volumen = (4/3) * (PI * radio * radio * radio); 
        System.out.println("El volumen de la esfera es: " + volumen + " metros cubicos");
        return volumen;
    }
}
