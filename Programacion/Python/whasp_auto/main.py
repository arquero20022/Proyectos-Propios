import pywhatkit

import base_datos
import envios
import os

texto = "¿Quieres enviar  [Un mensaje],[Hacer Spam],[Ver agenda],[Salir] ? ( |Uno| |Spam| |Agenda| |Nuevo| |Q|)"
def actualizar():
    datos_numeros_nombres = base_datos.get_datos()
    lista_numeros = datos_numeros_nombres[0]
    lista_nombres = datos_numeros_nombres[1]
    return lista_numeros,lista_nombres
def pregunta_contacto(lista_nombres, lista_numeros,respuesta):
    opciones = ["q", "uno", "spam", "agenda", "nuevo"]
    while respuesta not in opciones:
        respuesta = input("{}".format(texto)).lower()
    print(os.system("cls"))
    if respuesta == "q":
        quit()
    elif respuesta == "uno":
        envios.envio_mensaje(lista_nombres, lista_numeros)

        pywhatkit.close_tab(3)

    elif respuesta == "spam":
        cantidad = int(input("¿Cuantos mensajes quieres enviar? (|-1| para salir)"))
        if cantidad == -1:
            quit()
        tiempo = input("¿Quieres que tengan delay? ")
        if tiempo.lower() == "si":
            tiempo = int(input("¿Cuantos tiempo quieres entre mensajes? (|-1| para salir)"))
            if tiempo == "-1":
                quit()
        else:
            tiempo = 0
        envios.spam(cantidad, tiempo, lista_nombres, lista_numeros)
        pywhatkit.close_tab(3)
    elif respuesta == "agenda":
        datos_numeros_nombres = base_datos.get_datos()
        lista_nombres = datos_numeros_nombres[1]
        base_datos.ver_agenda(lista_nombres)
        print()
        respuesta = input("{}".format(texto)).lower()
        pregunta_contacto(lista_nombres, lista_numeros, respuesta)
    elif respuesta == "nuevo":
        print(os.system("cls"))
        nombre = input("¿Como se llama el nuevo contacto? (|Q| para salir) ")
        if nombre.lower() == "q":
            quit()
        while nombre in lista_nombres:
            print("Ese nombre ya esta en uso, selecciona otro ")
            nombre = input("¿Cual es el nombre? (|Q| para salir |Agenda|) ")
            if nombre.lower() == "q":
                quit()
            if nombre == "agenda":
                datos_numeros_nombres = base_datos.get_datos()
                lista_nombres = datos_numeros_nombres[1]
                base_datos.ver_agenda(lista_nombres)
                print()
                nombre = input("¿Como se llama el nuevo contacto? (|Q| para salir) ")
                pregunta_contacto(lista_nombres, lista_numeros, respuesta)
        telefono = input("¿Cual es su numero de telefono? (|Q| para salir) ")
        if telefono.lower() =="q":
            quit()
        nuevo_contacto= "{}:{}".format(nombre,  telefono)
        base_datos.añadir_nuevo(nuevo_contacto)
        print()
        respuesta = input("{}".format(texto)).lower()
        pregunta_contacto(lista_nombres, lista_numeros, respuesta)

def main():
    lista_numeros = actualizar()[0]
    lista_nombres = actualizar()[1]
    respuesta = input("{}".format(texto)).lower()
    pregunta_contacto(lista_nombres, lista_numeros,respuesta)


if __name__ == "__main__":
    main()
