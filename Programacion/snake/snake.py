import os
import random
import time

import keyboard as keyboard
import readchar

clear = lambda: os.system('cls')
width = int(input("¿Que tamaño de mapa quieres?"))
height = width
died = False
my_position = [0, 0]
Pos_x = 0
Pos_y = 1
tail_length = 0
tail = []
desplazador = 1
direction = "x"
Map_Objects = []
Objects_Amounts = 5

while not died:
    time.sleep(0.04)
    print(tail_length)

    while len(Map_Objects)< Objects_Amounts:
        position = [random.randrange(width - 1), random.randrange(height - 1)]
        while position in Map_Objects and my_position in Map_Objects:
            position = [random.randrange(width - 1), random.randrange(height - 1)]
        Map_Objects.append(position)
        if my_position in Map_Objects:
            Map_Objects.remove(my_position)
            tail_length += 1
            position = [random.randrange(width - 1), random.randrange(height - 1)]
    clear()
    print("+" + "-" * (width * 3) + "+")
    for cord_y in range(height):
        print("|", end="")
        for cord_x in range(width):
            chart_draw = " "
            for tail_pice in tail:
                if tail_pice[Pos_x] == cord_x and tail_pice[Pos_y] == cord_y:
                    chart_draw = "@"
            if cord_x == my_position[Pos_x] and cord_y == my_position[Pos_y]:
                chart_draw = "@"
            elif [cord_y, cord_x] in Map_Objects:
                chart_draw = "*"
            print(" {} ".format(chart_draw), end="")
        print("|")
    print("+" + "-" * (width * 3) + "+")

    if direction == "y":
        tail.insert(0, my_position.copy())
        tail = tail[:tail_length]
        my_position[Pos_y] += desplazador
        my_position[Pos_y] %= height
        if my_position in Map_Objects:
            Map_Objects.remove(my_position)
            tail_length += 1
            position = [random.randrange(width - 1), random.randrange(height - 1)]
        time.sleep(0.02)

    if direction == "x":
        tail.insert(0, my_position.copy())
        tail = tail[:tail_length]
        my_position[Pos_x] += desplazador
        my_position[Pos_x] %= height
        if my_position in Map_Objects:
            Map_Objects.remove(my_position)
            tail_length += 1
            position = [random.randrange(width - 1), random.randrange(height - 1)]
        time.sleep(0.02)

    if keyboard.is_pressed('w'):
        desplazador = -1
        direction = "y"

    elif keyboard.is_pressed('s'):
        desplazador = 1
        direction = "y"

    elif keyboard.is_pressed('a'):
        desplazador = -1
        direction = "x"
    elif keyboard.is_pressed('d'):
        desplazador = 1
        direction = "x"
    elif keyboard.is_pressed('q'):
        died = True
        print("Has finalizado el programa")


