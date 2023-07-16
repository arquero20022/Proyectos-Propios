import random
numero = random.randint(1, 10)
tirada = int(input("Di un numero del 1 al 10: "))
print(tirada)
if 10 >= tirada >= 1:
    if tirada == numero:
        print("Enhorabuena has ganado")
    if tirada != numero:
        print("has perdido")
if 1 > tirada or tirada > 10:
    print(" te saliste wey!!")


