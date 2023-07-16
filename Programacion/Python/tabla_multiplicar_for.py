numero= int(input("De que numero quiers que te diga la tabla de multiplicar "))

for a in range(1,11):
    resultado = numero * a
    print("{} X {} = {}".format(numero, a, resultado))

print("Los que son multiplos de 2 son: ")

for a in range(1, 11):
    resultado = numero * a
    if resultado % 2 == 0:
        print("{} X {} = {}".format(numero, a, resultado))


