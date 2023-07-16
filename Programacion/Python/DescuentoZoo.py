print("Bienvenido al zoologico")
edad = int(input("Digame se edad: "))
tipoDeCarnet = (input("que carnet tiene(E,P,F,N)"))
if (25 < edad < 35 and tipoDeCarnet == "E") or (edad < 10) or (edad > 65 and tipoDeCarnet == "P") or tipoDeCarnet == "F":
    print("Se le aplica el descuento del 25% ")
else:
    print("no se le aplica na de na ")
