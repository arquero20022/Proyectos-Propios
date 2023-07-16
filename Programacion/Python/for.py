frase = "hola mundo"
volcales=["a","e","i","o","u"]
contador=0
vocales_encontradas=""



for letra in frase:
    if letra in volcales:
        print("He encontrado {}".format(letra))

        contador +=1

print(contador)
print(vocales_encontradas)