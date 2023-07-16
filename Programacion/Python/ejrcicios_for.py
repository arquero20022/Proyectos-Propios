texto_usuario = input("dame un texto ")
cantidad = [0, 0, 0]
contador = 0
for letra in texto_usuario:
    if letra == " ":
        cantidad[0] += 1

    elif letra == ",":
        cantidad[1] += 1

    elif letra == ".":
        cantidad[2] += 1

print("He encontrado {} espacios, {} comas , {} puntos ".format(cantidad[0], cantidad[1], cantidad[2]))