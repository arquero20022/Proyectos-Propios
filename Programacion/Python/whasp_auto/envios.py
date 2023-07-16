import os
import time as tn

import pyautogui as pg
import pywhatkit as kit

import base_datos
import main


def envio_mensaje(lista_nombres, lista_numeros):
    lista_numeros = main.actualizar()[0]
    lista_nombres = main.actualizar()[1]
    hora = "25:61"
    hora = hora.split(":")
    Nom_contacto = (input("¿A que contacto quieres que se envie?  ( |Q| para salir |Agenda| ) "))

    if Nom_contacto.lower() == "q":
        print(os.system('cls'))
        print("Adios")
        quit()
    elif Nom_contacto.lower() == "agenda":
        base_datos.ver_agenda(lista_nombres)
    if Nom_contacto not in lista_nombres:
        print(os.system("cls"))
        print("el contacto {} no esta en la agenda ".format(Nom_contacto))
        print("Tus contactos son: ")
        base_datos.ver_agenda()
        numero_tel = input("¿Cual es su numero de telefono?")
        nuevo_contacto = "{}:{}".format(Nom_contacto, numero_tel)
        base_datos.añadir_nuevo(nuevo_contacto)
        lista_numeros = main.actualizar()[0]
        lista_nombres = main.actualizar()[1]

    mensaje = input("¿Que mensajes quieres enviar?  ( [Q] para salir ) ")
    if mensaje.lower() == "q":
        quit()
    while hora[0] not in range(0, 23) and hora[1] not in range(0, 60):
        hora = input("¿A que hora quieres que lo envie? [Hora:Minutos]  ( [Q] para salir ) ")
        if hora.lower() == "q":
            quit()
        hora = hora.split(":")
        hora = [int(i) for i in hora]
        os.system('cls')
        print("El mensaje {} se enviara a {} a las {}:{} ".format(mensaje, Nom_contacto.upper(), hora[0], hora[1]))
        number = base_datos.numero_contacto(lista_nombres, lista_numeros, Nom_contacto)
    kit.sendwhatmsg("+34" + number, mensaje, hora[0], hora[1])
    return mensaje


def spam(cantidad, tiempo, lista_nombres, lista_numeros):
    mensaje = envio_mensaje(lista_nombres, lista_numeros)
    while cantidad - 1 > 0:
        pg.write(mensaje)
        tn.sleep(tiempo)
        pg.press('enter')
        cantidad += -1
