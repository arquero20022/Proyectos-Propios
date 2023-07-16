nombre_base = "agenda.txt"
def crear_cargar_base():
    agenda_bruto = []
    try:
        with open(nombre_base, "r") as datos_brutos:
            agenda_bruto = datos_brutos.read().split("\n")
            return agenda_bruto
    except FileNotFoundError:
        print("No habia una base de datos por lo que se ha creado")
        with open(nombre_base, "w") as datos_brutos:
            datos_brutos.write("\n".join(agenda_bruto))

        agenda_bruto.append("nombre:numero")
        agenda_bruto.append("adrian:683571361")
        with open(nombre_base, "w") as datos_brutos:
            datos_brutos.write("\n".join(agenda_bruto))

        return agenda_bruto


def separador_nombres_numeros():
    datos_brutos = crear_cargar_base()
    if len(datos_brutos) > 0:
        lista_separada = []
        for datos in datos_brutos:
            lista_separada.append(datos.split(":"))
    else:
        lista_separada = []
    return lista_separada


def get_datos():
    lista_separada = separador_nombres_numeros()
    lista_nombre = []
    lista_numero = []
    for pos in range(len(lista_separada)-1):
        lista_nombre.append(lista_separada[pos+1][0])

        lista_numero.append(lista_separada[pos+1][1])

    return lista_numero, lista_nombre


def numero_contacto(lista_nombres, lista_numeros, nombre=""):
    for posicion in range(len(lista_nombres)):
        if lista_nombres[posicion] == nombre.lower():
            return lista_numeros[posicion]


def añadir_nuevo(nuevo_contacto=""):
    datos_numeros_nombres = get_datos()
    lista_nombres = get_datos()[1]
    lista = crear_cargar_base()
    contacto = nuevo_contacto.split()


    lista.append(nuevo_contacto)
    with open(nombre_base, "w") as nuevo:
        nuevo.write("\n".join(lista))
    crear_cargar_base()


    print("Se ha añadido correctamente")
def main():
    nombres = get_datos()[1]
    print(nombres)

def ver_agenda(lista_nombres):
    for nombre in lista_nombres:
        print("|",end=" ")
        print(nombre, end=" |")



if __name__ == "__main__":
    main()
