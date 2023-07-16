print("\n Hola bienvenido al QUIZ de Qesso \n")
puntos = 0
print(" ¿Cunando ves una tabla de queso que haces? ")
print("A) La devoro")
print("B) La ignoro")
print("C) La pruebo")
respuesta = input()
if respuesta == "a" or "A":
    puntos += 10
elif respuesta == "c" or respuesta == "C":
    puntos += 5

print(" ¿Cunando ves una hamburguesa con queso que haces? ")
print("A) La devoro")
print("B) La ignoro")
print("C) La pruebo")
respuesta = input()
if respuesta == "a" or "A":
    puntos += 10
elif respuesta == "c" or respuesta == "C":
    puntos += 5

print(" ¿Eres intolerante a la lactosa? ")
print("A) No")
print("B) Si")
print("C) A ratos")
respuesta = input()
if respuesta == "a" or "A":
    puntos += 10
elif respuesta == "c" or respuesta == "C":
    puntos += 5

if puntos > 25:
    print("Eres adicto ")
elif 25> puntos > 4:
    print("Te mola")
else:
    print("Eres lo peor")

