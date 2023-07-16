import random

import readchar,os
clear = lambda: os.system('cls')


Pos_x = 0
Pos_y =1
Map_width = 20
Map_height = 16
my_position =[0, 0]
tail_length = 0
tail = []
Map_object = []
cantidad_object = 3
direction = "p"






while direction != "q":
    while len(Map_object)< cantidad_object:
        position = [random.randrange(Map_width - 1), random.randrange(Map_height - 1)]
        while position in Map_object and my_position in Map_object:
            position = [random.randrange(Map_width - 1), random.randrange(Map_height - 1)]
        Map_object.append(position)

    print("+" + "-" * (Map_width * 3) + "+")
    for y in range(Map_height):
        print("|", end="")
        for x in range(Map_width):
            chart_draw = "   "



            for tail_pice in tail:
                if tail_pice[Pos_x] == x and tail_pice[Pos_y] == y:
                    chart_draw = " @ "


            if x == my_position[Pos_x] and y == my_position[Pos_y]:
                chart_draw = " @ "
            elif [x,y] in Map_object:
                chart_draw = " * "
            print(chart_draw, end="")
        print("|")
    print("+"+ "-"*(Map_width*3) + "+")




    if my_position in Map_object:
        Map_object.remove(my_position)
        tail_length +=1
        position = [random.randrange(Map_width - 1), random.randrange(Map_height - 1)]
        while position in Map_object and my_position in Map_object:
            position = [random.randrange(Map_width - 1), random.randrange(Map_height - 1)]
        Map_object.append(position)


    direction = readchar.readchar().decode()
    if my_position in tail:
        print("HAS PERDIDO")
        break
    if direction == "w" or direction =="W":
        tail.insert(0,my_position.copy())
        tail = tail[:tail_length]
        my_position[Pos_y] -= 1
        my_position[Pos_y] %= Map_height


    elif direction == "S" or direction =="s":
        tail.insert(0,my_position.copy())
        tail = tail[:tail_length]
        my_position[Pos_y] += 1
        my_position[Pos_y] %= Map_height

    elif direction == "a" or direction =="A":
        tail.insert(0,my_position.copy())
        tail = tail[:tail_length]
        my_position[Pos_x] -= 1
        my_position[Pos_x] %= Map_width
    elif direction == "d" or direction =="D":
        tail.insert(0,my_position.copy())
        tail = tail[:tail_length]
        my_position[Pos_x] += 1
        my_position[Pos_x] %= Map_width


    clear()


