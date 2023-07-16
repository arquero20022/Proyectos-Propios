import os
from random import randint

print("\n Bienvenido a pokemon console \n")
VIDA_INICIAL_PIKA = 80
VIDA_INICIAL_SQUI =90
TAMANO_BARRA = 10

vidaPika = 80
vidaSqui = 90

while vidaPika > 0 and vidaSqui > 0:
    textTurno= "TURNO DE PIKACHU"
    print(textTurno)
    print("-"*len(textTurno))


    ataquePika = randint(1, 2)
    if ataquePika == 1:
        print("Pika ataca con bola voltio y hace 10 de daño!! ")
        vidaSqui -= 10

    elif ataquePika == 2:
        print("Pika ataca con inpactrueno y hace 11 de daño!!")
        vidaSqui -= 11
    if vidaSqui < 0:
        vidaSqui = 0
    barraVidaPika = int((vidaPika * TAMANO_BARRA) / VIDA_INICIAL_PIKA)
    print("VIDA PIKA " + "[" + "#" * barraVidaPika + " " * (10 - barraVidaPika) + "]" + " {}".format(vidaPika))

    barraVidaSqui = int((vidaSqui * TAMANO_BARRA/ VIDA_INICIAL_SQUI))
    print("VIDA SQUI " + "[" + "#" * barraVidaSqui + " " * (10 - barraVidaSqui) + "]" + " {}".format(vidaSqui))
    print("\n enter para continuar")
    input()
    os.system("cls")


    textTurno= "¡Es tu turno!"
    print(textTurno)
    print("-"*len(textTurno))

    ataqueSqui = None
    while ataqueSqui not in ["a" , "A" , "b", "B", "c" , "C" , "n" , "N"]:
        print("\n¿Que ataque deseas hacer?"
                           "\n A) Burbuuja"
                           "\n B) Placaje"
                           "\n c) Agua"
                           "\n D) Nada")
        ataqueSqui = input()

    if ataqueSqui == "a" or ataqueSqui == "A":

        print("Ataque burbuja y hace 10 de daño!!")
        vidaPika -= 10
    elif ataqueSqui == "b" or ataqueSqui == "B":
        print("Ataque placaje y hace 11 de daño!!")
        vidaPika -= 11
    elif ataqueSqui == "c" or ataqueSqui == "C":
        print("ataque agua y hace 15 de daño!!")
        vidaPika -= 15
    elif ataqueSqui == "d" or ataqueSqui == "D":
        print("Has decidido no atacar!")
    if vidaPika < 0:
        vidaPika = 0
    barraVidaPika = int((vidaPika * TAMANO_BARRA) / VIDA_INICIAL_PIKA)
    print("VIDA PIKA " + "[" + "#" * barraVidaPika + " " * (10 - barraVidaPika) + "]" + " {}".format(vidaPika))

    barraVidaSqui = int((vidaSqui * TAMANO_BARRA) / VIDA_INICIAL_SQUI )
    print("VIDA SQUI " + "[" + "#" * barraVidaSqui + " " * (10 - barraVidaSqui) + "]" + " {}".format(vidaSqui))

    print("\n enter para continuar")
    input()
    os.system("cls")


if vidaPika > 0:
    print("GANADOR PIKAAAAAA")
    vidaSqui = 0

else:
    print("GANADADOR SQUIIIIII")
    vidaPika=0
barraVidaPika = int((vidaPika *TAMANO_BARRA) / VIDA_INICIAL_PIKA)
print("VIDA PIKA " + "[" + "#" * barraVidaPika + " " * (10 - barraVidaPika) + "]" + " {}".format(vidaPika))

barraVidaSqui = int((vidaSqui * TAMANO_BARRA) / VIDA_INICIAL_SQUI)
print("VIDA SQUI " + "[" + "#" * barraVidaSqui + " " * (10 - barraVidaSqui) + "]" + " {}".format(vidaSqui))
