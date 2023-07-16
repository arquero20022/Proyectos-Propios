import os, time
clear = lambda: os.system('cls')
numeros= []
respuesta= None
numero= None
respuesta_s_n= None
salida=["Q", "q"]
while respuesta not in salida:
    clear()
    respuesta=input("¿Que numero quieres añadir?  [q] para salir ")
    if respuesta not in salida:
        numero = int(respuesta)
        if numero in numeros:
            print( "El numero {} ya esta en la lista".format(numero))
            time.sleep(3.5)
        else:
            respuesta_s_n= input("¿Seguro que quieres añadir {} a la lista? ".format(numero))
            if respuesta_s_n == "si" or respuesta_s_n == "SI" or respuesta_s_n == " Si" or respuesta_s_n == "sI" :
                print("Se ha añadido {} a la lista".format(numero))
                numeros.append(numero)

            else:
                print(" no se ha añadido nada a la lista")

print("La lista de Numeros es {}".format(numeros))
mayor = numeros[0]
menor = numeros[0]
for i in numeros:
    if i < menor:
        menor = i
    elif i > mayor:
        mayor = i

print("El numero mas pequeño de las lista es: {} y el numero mayor es {}".format(menor, mayor))
input("Enter para salir")