respuesta = None

while respuesta != "a" and respuesta != "A" and respuesta != "b" and respuesta != "B" and respuesta != "c" and respuesta != "C":
    respuesta = input("dime un letra A/B/C")

if respuesta == "a" or respuesta == "A":
    print("bien")

elif respuesta == "b" or respuesta == "B":
    print("mal")
else:
    print("mal")
