import os
import random

import readchar
clear = lambda: os.system('cls')

obtacle_definition = ''' \
    ###################
## ############         
##   ###########        
###                #    
################        
#                       
##################      
##############          
                        
#######                 
########        ####    
########                
#              ######   
#                       
#############           
############            
                        \
'''

obtacle_definition = [list(row) for row in obtacle_definition.split("\n")]

direction = "p"
Map_Object = []
Object_amount = 5
My_Position = [0, 0]
Pos_y = 1
Pos_x = 0
tail_length = 0
tail = []

while direction != "q":

    while len(Map_Object) < Object_amount :
        position = [random.randrange(len(obtacle_definition)-1),random.randrange(len(obtacle_definition[0])-1)]

        while position in Map_Object or My_Position == position or obtacle_definition[position[0]][position[1]]=="#":
            position = [random.randrange(len(obtacle_definition)), random.randrange(len(obtacle_definition[0]))]
        Map_Object.append(position)

    #Mostrar inicio
    print("+" + "-" * (len(obtacle_definition[0]) * 3) + "+")
    for cord_y in range(len(obtacle_definition)):
        print("|",end="")
        for cord_x in range(len(obtacle_definition[cord_y])):
            chart_draw = " "
            apple = [cord_x,cord_y]
            #mostrar objetos en mapa
            if cord_x == My_Position[Pos_x] and cord_y == My_Position[Pos_y]:
                chart_draw = "@"

            elif obtacle_definition[cord_y][cord_x] == "#":
                chart_draw = "#"
            elif apple in Map_Object:
                chart_draw = "*"


            print(" {} ".format(chart_draw), end="")
        print("|")
    print("+" + "-" * (len(obtacle_definition[0]) * 3) + "+")
    #Mostrar mapa fin

    #moverse

    direction = readchar.readchar().decode()
    if My_Position in tail:
        print("HAS PERDIDO")
        break

    new_position = None

    if direction == "w" or direction == "W":
        new_position = [My_Position[Pos_x], (My_Position[Pos_y]-1)% len(obtacle_definition)]


    elif direction == "S" or direction == "s":
        new_position = [My_Position[Pos_x], (My_Position[Pos_y] + 1) % len(obtacle_definition)]


    elif direction == "a" or direction == "A":
        new_position = [(My_Position[Pos_x]- 1) % len(obtacle_definition[0]), My_Position[Pos_y] ]

    elif direction == "d" or direction == "D":
        new_position = [(My_Position[Pos_x] + 1) % len(obtacle_definition[0]), My_Position[Pos_y]]

    if new_position:
        if obtacle_definition[new_position[Pos_y]][new_position[Pos_x]] != "#":
            tail.insert(0, My_Position.copy())
            tail = tail[:tail_length]
            My_Position = new_position

    clear()
