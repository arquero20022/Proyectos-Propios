print("Entro en la cocina")
print("abro la nevera")
HayLeche = input("¿hay leche? (si/no) ")
HayColacao=input("¿hay colacado? (si/no) ")

if HayLeche != "si" or HayColacao != "si":
    print("vamos al super")
    if HayLeche != "si":
        print("compramos leche")
    if HayColacao != "si":
        print("compramos colacado")

print("lo mezclamos")
print("Servimos el colacado")