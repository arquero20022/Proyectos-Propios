import random
import time

import readchar, os

clear = lambda: os.system('cls')

Map_Obtacle = '''\
##############################
#        ######   ###        #
#                            #
#    ####    #   ####        #
#    #                       #
######          ###          #
#                            #
#    ###                     #
#                 ####       #
#     ####                   #
# ###########                #
#                ####        #
#        ####                #
#          ########          #
#                            #
#                            #
#     ####                   #
#                  ######    #
#                  #         #
#         ####     #         #
#                  #         #
#                  #         #
#         ####################
#                            #
#      #############         #
#            ##              #
##############################\
'''

Map_Obtacle = [list(row) for row in Map_Obtacle.split("\n")]

Pos_x = 0
Pos_y = 1
height = len(Map_Obtacle)
width = len(Map_Obtacle[0])
died = False
my_position = [1, 1]
couchs = []
couchs_amounts = 5

while len(couchs) < couchs_amounts:
    couchs_Position = [random.randrange(0,len(Map_Obtacle[0])), random.randrange(0,len(Map_Obtacle))]
    while couchs_Position == my_position or couchs_Position in couchs \
            or Map_Obtacle[couchs_Position[Pos_y]][couchs_Position[Pos_x]] == "#":
        couchs_Position = [random.randrange(0, len(Map_Obtacle[0])), random.randrange(0, len(Map_Obtacle))]
    couchs.append(couchs_Position)



while not died:

    if couchs_amounts == 0:
        clear()
        print("Has conseguido vencer a todos los entrenadores ¡QUE BIEEEN!")
        break



    for cord_y in range(height):

        for cord_x in range(width):
            chart_draw = "   "


            if my_position[Pos_x] == cord_x and my_position[Pos_y]== cord_y:
                chart_draw = " ☻ "
            elif Map_Obtacle[cord_y][cord_x] == "#":
                chart_draw = "▓▓▓"
            elif [cord_x, cord_y] in couchs:
                chart_draw = " ♦ "

            print(chart_draw, end="")
        print()

    # pedimos teclado

    direction = readchar.readchar().decode()

    if direction == "w" or direction == "W":
        new_position = [my_position[Pos_x], (my_position[Pos_y] - 1) % height]
    elif direction == "S" or direction == "s":
        new_position= [my_position[Pos_x], (my_position[Pos_y] + 1) % height]
    elif direction == "a" or direction == "A":
        new_position = [(my_position[Pos_x]-1) % width, my_position[Pos_y]]
    elif direction == "d" or direction == "D":
        new_position = [(my_position[Pos_x] + 1) % width, my_position[Pos_y]]
    elif direction =="q":
        died = True

    if new_position:
        if Map_Obtacle[new_position[Pos_y]][new_position[Pos_x]] != "#":
            my_position = new_position

    clear()

    # combate pokemon
    if my_position in couchs:
        print("\n ESPERO QUE ESTES PREPARADO PARA EL COMBATE \n")
        time.sleep(1.5)
        VIDA_INICIAL_PIKA = 80
        VIDA_INICIAL_SQUI = 90
        TAMANO_BARRA = 10

        vidaPika = 80
        vidaSqui = 90

        while vidaPika > 0 and vidaSqui > 0:
            textTurno = "TURNO DE PIKACHU"
            print(textTurno)
            print("-" * len(textTurno))
            time.sleep(0.7)

            ataquePika = random.randint(1, 3)
            if ataquePika == 1:
                print("Pika ataca con bola voltio y hace 10 de daño!! ")
                vidaSqui -= 10

            elif ataquePika == 2:
                print("Pika ataca con inpactrueno y hace 11 de daño!!")
                vidaSqui -= 11
            elif ataquePika == 2:
                print("Pika ataca y falla!!")
                vidaSqui -= 0
            if vidaSqui < 0:
                vidaSqui = 0
            barraVidaPika = int((vidaPika * TAMANO_BARRA) / VIDA_INICIAL_PIKA)
            print("VIDA PIKA " + "[" + "♥" * barraVidaPika + " " * (10 - barraVidaPika) + "]" + " {}".format(vidaPika))

            barraVidaSqui = int((vidaSqui * TAMANO_BARRA / VIDA_INICIAL_SQUI))
            print("VIDA SQUI " + "[" + "♥" * barraVidaSqui + " " * (10 - barraVidaSqui) + "]" + " {}".format(vidaSqui))
            time.sleep(0.7)
            print("\n enter para continuar")
            input()
            os.system("cls")

            textTurno = "¡Es tu turno!"
            print(textTurno)
            print("-" * len(textTurno))
            time.sleep(0.5)

            ataqueSqui = None
            while ataqueSqui not in ["a", "A", "b", "B", "c", "C", "n", "N","q","Q"]:
                print("\n¿Que ataque deseas hacer?"
                      "\n A) Burbuuja"
                      "\n B) Placaje"
                      "\n c) Agua"
                      "\n D) Nada"
                      "\n Q) Salir")
                ataqueSqui = input()

            if ataqueSqui == "a" or ataqueSqui == "A":

                print("Ataque burbuja y hace 10 de daño!!")
                vidaPika -= random.randint(9,11)
            elif ataqueSqui == "b" or ataqueSqui == "B":
                print("Ataque placaje y hace 11 de daño!!")
                vidaPika -= random.randint(10,12)
            elif ataqueSqui == "c" or ataqueSqui == "C":
                print("ataque agua y hace 15 de daño!!")
                vidaPika -= random.randint(14,16)
            elif ataqueSqui == "d" or ataqueSqui == "D":
                print("Has decidido no atacar!")
            elif ataqueSqui == "q" or ataqueSqui == "Q":
                died = True
                break
            if vidaPika < 0:
                vidaPika = 0
            barraVidaPika = int((vidaPika * TAMANO_BARRA) / VIDA_INICIAL_PIKA)
            print("VIDA PIKA " + "[" + "♥" * barraVidaPika + " " * (10 - barraVidaPika) + "]" + " {}".format(vidaPika))

            barraVidaSqui = int((vidaSqui * TAMANO_BARRA) / VIDA_INICIAL_SQUI)
            print("VIDA SQUI " + "[" + "♥" * barraVidaSqui + " " * (10 - barraVidaSqui) + "]" + " {}".format(vidaSqui))

            print("\n enter para continuar")
            input()
            os.system("cls")

        if vidaPika > 0:
            print("OOO QUE PENA HAS PERDIDO")
            died = True
            vidaSqui = 0



        else:
            couchs_amounts -= 1
            print("¡QUE BIEEEN HAS VENCIDO A UN ENTRENADOR, quedan en el mapa {}".format(couchs_amounts))
            couchs.remove(my_position)
            time.sleep(1.5)
            vidaPika = 0

        time.sleep(1.5)
        barraVidaPika = int((vidaPika * TAMANO_BARRA) / VIDA_INICIAL_PIKA)
        print("VIDA PIKA " + "[" + "♥" * barraVidaPika + " " * (10 - barraVidaPika) + "]" + " {}".format(vidaPika))

        barraVidaSqui = int((vidaSqui * TAMANO_BARRA) / VIDA_INICIAL_SQUI)
        print("VIDA SQUI " + "[" + "♥" * barraVidaSqui + " " * (10 - barraVidaSqui) + "]" + " {}".format(vidaSqui))
        time.sleep(2)
        clear()





