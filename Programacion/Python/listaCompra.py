lista_compra=[]
respuesta=None
respuesta_s_n=None
while respuesta not in ["q" , "Q"]:
    respuesta=input("¿Que desea comprar? ([Q] Para salir)")
    if respuesta not in ["q" , "Q"]:
        print("¿Desea añadir {} a la lista de la compra? (S/N) ".format(respuesta))
        respuesta_s_n = input()
        if respuesta_s_n in ["si" , "SI"]:
            if respuesta in lista_compra:
                print("Ya estaba {} en la lista de la compra".format(respuesta))

            else:
                print(" Vale he añadido a la lista de la compra {}".format(respuesta))
                lista_compra.append(respuesta)
        else:
            print("Vale no lo he añadido")

print(lista_compra)