import re
'''
NOMBRE: logic
----------------
PARAMETRO 1: String
----------------
DESCRIPCION: Recibe un string correspondiendo a la expresion logica encontrada, luego remplaza las variables por sus valores y finalmente evalua la funcion.
----------------
RETORNO: Un booleano dependiendo si el string corresponde a falso o verdadero (True: verdadero y False: Falso)
'''
def logic(entrada):
    operacion=entrada
    flagop=True
    while flagop:
        #tenemos una variable entre nosotros
        aux = re.findall(r"[a-zA-Z]\w*",operacion)
        for a in aux:
            if a not in ["and","or"]:
                remplazo = str(a)
                remplazado = str(valuedic[remplazo])
                operacion = operacion.replace(remplazo,remplazado)
        else:
            flagop=False
    return eval(operacion)
'''
NOMBRE: recursiveCycle
----------------
PARAMETRO 1: LIST
PARAMETRO 2: STRING
----------------
DESCRIPCION: Recibe la lista de lineas dentro del ciclo y la condicion del ciclo. Luego corre las lineas mientras la condicion aun sea falsa.
----------------
RETORNO: None
'''
def recursiveCycle(plist,condstr):
    while logic(condstr):
        flagop = True
        condif=True
        dentrodeif=False
        dentrodewhile=False
        condwhile=False
        hasstarted=True
        for line in (plist):
            flagop = True
            if re.fullmatch(r"(?P<fun>Secret Level \w+ [a-zA-Z]\w*(-[a-zA-Z]\w*)*\n)|(?P<main>Start Game\n)", line):
                hasstarted=True
                TypeCheck = re.fullmatch(r"(?P<fun>Secret Level \w+ (-\w+)*\n)|(?P<main>Start Game\n)", line)
                if TypeCheck.group("fun"):
                    isfun=True
                    isfuntrue=False 
                elif TypeCheck.group("main"):
                    ismain=True
                    ismaintrue=False
            else:
                if hasstarted:
                    if dentrodewhile:
                        if condwhile:
                            pendiente.append(line)
                            if re.fullmatch(r"AH HA!\n",line):
                                dentrodewhile=False
                                pendiente.remove("AH HA!\n")
                                recursiveCycle(pendiente,condstr)
                    if dentrodeif:
                        if condif:
                            if re.fullmatch(r"(Add Player (?P<variable>[a-zA-Z]\w*)\n)",line):
                                m = re.fullmatch(r"(Add Player (?P<variable>[a-zA-Z]\w*)\n)",line)
                                valuedic[m.group("variable")]=None       
                            elif re.fullmatch(r"(?:(?P<variable>[a-zA-Z]\w*) took (?P<valor>-?\d+)\n)",line):
                                m = re.fullmatch(r"(?:(?P<variable>[a-zA-Z]\w*) took (?P<valor>-?\d+)\n)",line)
                                valuedic[m.group("variable")]=m.group("valor")
                            elif re.fullmatch(r"(([a-zA-Z]\w*) needs a power up\n)",line):
                                m = re.fullmatch(r"((?P<variable>[a-zA-Z]\w*) needs a power up\n)",line)
                                valuedic[m.group("variable")] = input("Input: ")
                            elif re.fullmatch(r"(?P<variable>[a-zA-Z]\w*) jumps to (?P<valor>\(+(?:[a-zA-Z]\w+|\d+)[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|(?:(?:\(+(?:[a-zA-Z]\w+|\d+))(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))|(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))*\)*)\n", line):
                                m = re.fullmatch(r"(?P<variable>[a-zA-Z]\w*) jumps to (?P<valor>\(+(?:[a-zA-Z]\w+|\d+)[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|(?:(?:\(+(?:[a-zA-Z]\w+|\d+))(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))|(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))*\)*)\n",line)
                                operacion = m.group("valor")
                                while flagop:
                                    try:
                                        valuedic[m.group("variable")]=eval(operacion)
                                        flagop = False
                                    except:
                                        #tenemos una variable entre nosotros
                                        aux = re.search(r"[a-zA-Z]\w*",operacion)
                                        remplazo = aux.group()
                                        operacion = operacion.replace(remplazo,str(valuedic[remplazo]))
                            elif re.fullmatch(r"(?P<nom>[a-zA-Z]\w+ enters (?P<par>\w+ \w+(-\w+)*\n))",line):
                                m = re.fullmatch(r"((?P<var>[a-zA-Z]\w+) enters (?P<nom>\w+) (?P<par>\w+(-\w+)*\n))",line)
                                valuedic[m.group("var")]=fun(m.group("nom"),m.group("par").strip("\n").split("-"))
                            elif re.fullmatch(r"(show (-?\d+|[a-zA-Z]?\w+)\n)",line):
                                m = re.fullmatch(r"(show ((?P<num>-?\d+)|(?P<var>[a-zA-Z]?\w+))\n)",line)
                                if m.group("num"):
                                    print(int(m.group("num")))
                                else:
                                    print(valuedic[m.group("var")])
                            elif re.fullmatch(r"(Mamma Mia\.\.\.\n)",line):
                                condif=False
                        else:
                            if re.fullmatch(r"(Mamma Mia\.\.\.\n)",line):
                                condif=True
                            elif re.fullmatch(r"Let's Go!\n",line):
                                dentrodeif=False
                    else:
                        if re.fullmatch(r"(Add Player (?P<variable>[a-zA-Z]\w*)\n)",line):
                            m = re.fullmatch(r"(Add Player (?P<variable>[a-zA-Z]\w*)\n)",line)
                            valuedic[m.group("variable")]=None       
                        elif re.fullmatch(r"(?:(?P<variable>[a-zA-Z]\w*) took (?P<valor>-?\d+)\n)",line):
                            m = re.fullmatch(r"(?:(?P<variable>[a-zA-Z]\w*) took (?P<valor>-?\d+)\n)",line)
                            valuedic[m.group("variable")]=m.group("valor")
                        elif re.fullmatch(r"(([a-zA-Z]\w*) needs a power up\n)",line):
                            m = re.fullmatch(r"((?P<variable>[a-zA-Z]\w*) needs a power up\n)",line)
                            valuedic[m.group("variable")] = input("Input: ")
                        elif re.fullmatch(r"(?P<variable>[a-zA-Z]\w*) jumps to (?P<valor>\(+(?:[a-zA-Z]\w+|\d+)[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|(?:(?:\(+(?:[a-zA-Z]\w+|\d+))(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))|(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))*\)*)\n", line):
                            m = re.fullmatch(r"(?P<variable>[a-zA-Z]\w*) jumps to (?P<valor>\(+(?:[a-zA-Z]\w+|\d+)[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|(?:(?:\(+(?:[a-zA-Z]\w+|\d+))(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))|(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))*\)*)\n",line)
                            operacion = m.group("valor")
                            while flagop:
                                try:
                                    valuedic[m.group("variable")]=eval(operacion)
                                    flagop = False
                                except:
                                    #tenemos una variable entre nosotros
                                    aux = re.search(r"[a-zA-Z]\w*",operacion)
                                    remplazo = aux.group()
                                    operacion = operacion.replace(remplazo,str(valuedic[remplazo]))
                        elif re.fullmatch(r"(show (-?\d+|[a-zA-Z]?\w+)\n)",line):
                            m = re.fullmatch(r"(show ((?P<num>-?\d+)|(?P<var>[a-zA-Z]?\w+))\n)",line)
                            if m.group("num"):
                                print(int(m.group("num")))
                            else:
                                print(valuedic[m.group("var")])
                        elif re.fullmatch(r"(?P<nom>[a-zA-Z]\w+ enters (?P<par>\w+ \w+(-\w+)*\n))",line):
                            m = re.fullmatch(r"((?P<var>[a-zA-Z]\w+) enters (?P<nom>\w+) (?P<par>\w+(-\w+)*\n))",line)
                            valuedic[m.group("var")]=fun(m.group("nom"),m.group("par").strip("\n").split("-"))
                        elif re.fullmatch(r"It's a me a conditional (?P<cond>(?:\(+(?:\w+|>=|==|<|>|<=)+\)(?:and|or))*\(*\((?:\w+|>=|==|<|>|<=)+\)(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+(?:(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+)*|\((?:\w+|>=|==|<|>|<=)+\)) Yahoo\n",line):
                            m = re.fullmatch(r"It's a me a conditional (?P<cond>(?:\(+(?:\w+|>=|==|<|>|<=)+\)(?:and|or))*\(*\((?:\w+|>=|==|<|>|<=)+\)(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+(?:(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+)*|\((?:\w+|>=|==|<|>|<=)+\)) Yahoo\n",line)
                            if logic(m.group("cond")):
                                condif=True
                                dentrodeif=True
                            else:
                                condif=False
                                dentrodeif=True
                        elif re.fullmatch(r"YA MA ((\(+(\w+|>=|==|<|>|<=)+\)(and|or))*\(*\((\w+|>=|==|<|>|<=)+\)(and|or)\((\w+|>=|==|<|>|<=)+\)+((and|or)\((\w+|>=|==|<|>|<=)+\)+)*|\((\w+|>=|==|<|>|<=)+\)) YAHOO!\n",line):
                            m = re.fullmatch(r"YA MA (?P<cond>(?:\(+(?:\w+|>=|==|<|>|<=)+\)(?:and|or))*\(*\((?:\w+|>=|==|<|>|<=)+\)(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+(?:(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+)*|\((?:\w+|>=|==|<|>|<=)+\)) YAHOO!\n",line)
                            if logic(m.group("cond")):
                                condstr=m.group("cond")
                                condwhile=True
                                dentrodewhile=True
                                
    return
'''
NOMBRE: inputcheck
----------------
PARAMETRO 1: LIST
----------------
DESCRIPCION: Recibe todas las lineas de la entrada y comprueba su sintaxis, reportando de los errores encontrados
----------------
RETORNO: False si hay errores en su sintaxis y True si no hay errores
'''
def inputcheck(linelist):
    isplayer=False
    isfun=False
    ismain=False
    ismaintrue=False
    isfuntrue=False
    iscycle=False
    isif=True
    isiftrue=True
    iselse=False
    hasstarted=False
    failcounter=0
    iscycletrue=True
    clean=True
    waitingforelse=False
    waitingforend=False
    waitingforifcontent=False
    for line in (linelist):
        flagop = True
        if re.fullmatch(r"(?P<fun>Secret Level \w+ \w+(-\w+)*\n)|(?P<main>Start Game\n)", line):
            hasstarted=True
            waitingforifcontent=False
            TypeCheck = re.fullmatch(r"(?P<fun>Secret Level \w+ [a-zA-Z]\w*(-[a-zA-Z]\w*)*\n)|(?P<main>Start Game\n)", line)
            if TypeCheck.group("fun"):
                if isfun:
                    failcounter=failcounter+1
                    print("woooooooohhhh!",str(failcounter)+":",line.strip())
                    clean=False
                else:
                    isfun=True
                    isfuntrue=False
            elif TypeCheck.group("main"):
                ismain=True
                ismaintrue=False
                if isfuntrue==False and isfun==True:
                    failcounter=failcounter+1
                    print("woooooooohhhh!",str(failcounter)+":",line.strip())
                    clean=False
        else:
            if hasstarted:
                if re.fullmatch(r"(Add Player [a-zA-Z]\w+\n)",line):
                    isplayer=True
                    waitingforifcontent=False
                if isplayer==False:
                    failcounter=failcounter+1
                    print("woooooooohhhh!",str(failcounter)+":",line.strip())
                    clean=False
                    isplayer=True #asumiendo que existiera un juegador
                else:
                    if re.fullmatch(r"([a-zA-Z]?\w+ took -?\d+\n)",line):
                        if isplayer==False:
                            failcounter=failcounter+1
                            print("woooooooohhhh!",str(failcounter)+":",line.strip())
                            clean=False
                        waitingforifcontent=False
                        trash=1 #basura solo para salir del if
                        iselse=False
                        isif=False
                        iscycle=False
                    elif re.fullmatch(r"([a-zA-Z]?\w+ needs a power up\n)",line):
                        waitingforifcontent=False
                        trash=1
                        iselse=False
                        isif=False
                        iscycle=False
                    elif re.fullmatch(r"(?P<variable>[a-zA-Z]\w*) jumps to (?P<valor>\(+(?:[a-zA-Z]\w+|\d+)[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|(?:(?:\(+(?:[a-zA-Z]\w+|\d+))(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))|(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))*\)*)\n", line):
                        trash=1
                        waitingforifcontent=False
                        iselse=False
                        isif=False
                        iscycle=False
                    elif re.fullmatch(r"(Add Player [a-zA-Z]\w+\n)",line):
                        trash=1
                        waitingforifcontent=False
                        iselse=False
                        isif=False
                        iscycle=False
                    elif re.fullmatch(r"Game Over|(?P<chale>Game Over\n)", line):
                        m=re.fullmatch(r"Game Over|(?P<chale>Game Over\n)", line)
                        ismaintrue=True
                        hasstarted=False
                        if waitingforend==True or m.group("chale"):
                            failcounter=failcounter+1
                            print("woooooooohhhh!",str(failcounter)+":",line.strip())
                            clean=False
                    elif re.fullmatch(r"It's a me a conditional ((\(+(\w+|>=|==|<|>|<=)+\)(and|or))*\(*\((\w+|>=|==|<|>|<=)+\)(and|or)\((\w+|>=|==|<|>|<=)+\)+((and|or)\((\w+|>=|==|<|>|<=)+\)+)*|\((\w+|>=|==|<|>|<=)+\)) Yahoo\n",line):
                        trash=1
                        isif=True
                        isiftrue=False
                        iselse=False
                        iscycle=False
                        waitingforelse=True
                        waitingforend=True
                        waitingforifcontent=True
                    elif re.fullmatch(r"(Mamma Mia\.\.\.\n)",line):
                        iselse=True
                        iscycle=False
                        if isif==True or waitingforifcontent==True:
                            failcounter=failcounter+1
                            print("woooooooohhhh!",str(failcounter)+":",line.strip())
                            clean=False
                    elif re.fullmatch(r"Let's Go!\n",line):
                        waitingforifcontent=False
                        waitingforend=False
                        iscycle=False
                        if iselse==True or waitingforifcontent==True:
                            failcounter=failcounter+1
                            print("woooooooohhhh!",str(failcounter)+":",line.strip())
                            clean=False
                        else:
                            isiftrue=True
                            isif=False
                    elif re.fullmatch(r"(show (-?\d+|[a-zA-Z]?\w+)\n)",line):
                        waitingforifcontent=False
                        iscycle=False
                        iselse=False
                        trash=1
                        isif=False
                    elif re.fullmatch(r"YA MA ((\(+(\w+|>=|==|<|>|<=)+\)(and|or))*\(*\((\w+|>=|==|<|>|<=)+\)(and|or)\((\w+|>=|==|<|>|<=)+\)+((and|or)\((\w+|>=|==|<|>|<=)+\)+)*|\((\w+|>=|==|<|>|<=)+\)) YAHOO!\n",line):
                        waitingforifcontent=False
                        iscycle=True
                        iscycletrue=False
                    elif re.fullmatch(r"AH HA!\n",line):
                        waitingforifcontent=False
                        if iscycletrue==True or iscycle==True:
                            failcounter=failcounter+1
                            print("woooooooohhhh!",str(failcounter)+":",line.strip())
                            clean=False
                        iscycletrue=True
                    elif re.fullmatch(r"([a-zA-Z]\w+ enters \w+ \w+(-\w+)*\n)",line):
                        waitingforifcontent=False
                        iselse=True
                        iscycle=False
                        if ismain==False:
                            failcounter=failcounter+1
                            print("woooooooohhhh!",str(failcounter)+":",line.strip())
                            clean=False
                    elif re.fullmatch(r"Return to Level (?P<var>[a-zA-Z]\w+)\n",line):
                        waitingforifcontent=False
                        hasstarted=False
                        iselse=True
                        iscycle=False
                        if isfuntrue == False:
                            isfuntrue=True
                            isfun=False
                        else:
                            failcounter=failcounter+1
                            print("woooooooohhhh!",str(failcounter)+":",line.strip())
                            clean=False
                    else:
                        failcounter=failcounter+1
                        print("woooooooohhhh!",str(failcounter)+":",line.strip())
                        clean=False
            else:
                failcounter=failcounter+1
                print("woooooooohhhh!",str(failcounter)+":",line.strip())
                clean=False
    if clean==True:
        return True
    else:
        return False
'''
NOMBRE: fun
----------------
PARAMETRO 1: String
PARAMETRO 2: List
----------------
DESCRIPCION: Asigna los valores con los que se llamo a la funcion a las variables correspondientes a la funcion y luego corre las lineas de la funcion
             Finalmente retorna el valor de la variable que llamo a "enters"
----------------
RETORNO: Retorna un numero correspondiente al valor de la variable que llamo a "enters"
'''
def fun(nom,par):
    flagop = True
    condif=True
    dentrodeif=False
    dentrodewhile=False
    condwhile=False
    hasstarted=True
    for a in range(len(dicfun[nom][0])):
        valuedic[dicfun[nom][0][a]]=par[a]
    for line in dicfun[nom][1]:
        flagop = True
        if dentrodewhile:
            if condwhile:
                pendiente.append(line)
                if re.fullmatch(r"AH HA!\n",line):
                    dentrodewhile=False
                    pendiente.remove("AH HA!\n")
                    recursiveCycle(pendiente,condstr)
        if dentrodeif:
            if condif:
                if re.fullmatch(r"(Add Player (?P<variable>[a-zA-Z]\w*)\n)",line):
                    m = re.fullmatch(r"(Add Player (?P<variable>[a-zA-Z]\w*)\n)",line)
                    valuedic[m.group("variable")]=None       
                elif re.fullmatch(r"(?:(?P<variable>[a-zA-Z]\w*) took (?P<valor>-?\d+)\n)",line):
                    m = re.fullmatch(r"(?:(?P<variable>[a-zA-Z]\w*) took (?P<valor>-?\d+)\n)",line)
                    valuedic[m.group("variable")]=m.group("valor")
                elif re.fullmatch(r"(([a-zA-Z]\w*) needs a power up\n)",line):
                    m = re.fullmatch(r"((?P<variable>[a-zA-Z]\w*) needs a power up\n)",line)
                    valuedic[m.group("variable")] = input("Input: ")
                elif re.fullmatch(r"(?P<variable>[a-zA-Z]\w*) jumps to (?P<valor>\(+(?:[a-zA-Z]\w+|\d+)[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|(?:(?:\(+(?:[a-zA-Z]\w+|\d+))(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))|(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))*\)*)\n", line):
                    m = re.fullmatch(r"(?P<variable>[a-zA-Z]\w*) jumps to (?P<valor>\(+(?:[a-zA-Z]\w+|\d+)[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|(?:(?:\(+(?:[a-zA-Z]\w+|\d+))(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))|(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))*\)*)\n",line)
                    operacion = m.group("valor")
                    while flagop:
                        try:
                            valuedic[m.group("variable")]=eval(operacion)
                            flagop = False
                        except:
                            #tenemos una variable entre nosotros
                            aux = re.search(r"[a-zA-Z]\w*",operacion)
                            remplazo = aux.group()
                            operacion = operacion.replace(remplazo,str(valuedic[remplazo]))
                elif re.fullmatch(r"(show (-?\d+|[a-zA-Z]?\w+)\n)",line):
                    m = re.fullmatch(r"(show ((?P<num>-?\d+)|(?P<var>[a-zA-Z]?\w+))\n)",line)
                    if m.group("num"):
                        print(int(m.group("num")))
                    else:
                        print(valuedic[m.group("var")])
                elif re.fullmatch(r"(?P<nom>[a-zA-Z]\w+ enters (?P<par>\w+ \w+(-\w+)*\n))",line):
                    m = re.fullmatch(r"((?P<var>[a-zA-Z]\w+) enters (?P<nom>\w+) (?P<par>\w+(-\w+)*\n))",line)
                    valuedic[m.group("var")]=fun(m.group("nom"),m.group("par").strip("\n").split("-"))
                elif re.fullmatch(r"(Mamma Mia\.\.\.\n)",line):
                    condif=False
            else:
                if re.fullmatch(r"(Mamma Mia\.\.\.\n)",line):
                    condif=True
                elif re.fullmatch(r"Let's Go!\n",line):
                    dentrodeif=False
        else:
            if re.fullmatch(r"(Add Player (?P<variable>[a-zA-Z]\w*)\n)",line):
                m = re.fullmatch(r"(Add Player (?P<variable>[a-zA-Z]\w*)\n)",line)
                valuedic[m.group("variable")]=None       
            elif re.fullmatch(r"(?:(?P<variable>[a-zA-Z]\w*) took (?P<valor>-?\d+)\n)",line):
                m = re.fullmatch(r"(?:(?P<variable>[a-zA-Z]\w*) took (?P<valor>-?\d+)\n)",line)
                valuedic[m.group("variable")]=m.group("valor")
            elif re.fullmatch(r"(([a-zA-Z]\w*) needs a power up\n)",line):
                m = re.fullmatch(r"((?P<variable>[a-zA-Z]\w*) needs a power up\n)",line)
                valuedic[m.group("variable")] = input("Input: ")
            elif re.fullmatch(r"(?P<variable>[a-zA-Z]\w*) jumps to (?P<valor>\(+(?:[a-zA-Z]\w+|\d+)[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|(?:(?:\(+(?:[a-zA-Z]\w+|\d+))(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))|(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))*\)*)\n", line):
                m = re.fullmatch(r"(?P<variable>[a-zA-Z]\w*) jumps to (?P<valor>\(+(?:[a-zA-Z]\w+|\d+)[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|(?:(?:\(+(?:[a-zA-Z]\w+|\d+))(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))|(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))*\)*)\n",line)
                operacion = m.group("valor")
                while flagop:
                    try:
                        valuedic[m.group("variable")]=eval(operacion)
                        flagop = False
                    except:
                        #tenemos una variable entre nosotros
                        aux = re.search(r"[a-zA-Z]\w*",operacion)
                        remplazo = aux.group()
                        operacion = operacion.replace(remplazo,str(valuedic[remplazo]))
            elif re.fullmatch(r"(show (-?\d+|[a-zA-Z]?\w+)\n)",line):
                m = re.fullmatch(r"(show ((?P<num>-?\d+)|(?P<var>[a-zA-Z]?\w+))\n)",line)
                if m.group("num"):
                    print(int(m.group("num")))
                else:
                    print(valuedic[m.group("var")])
            elif re.fullmatch(r"It's a me a conditional (?P<cond>(?:\(+(?:\w+|>=|==|<|>|<=)+\)(?:and|or))*\(*\((?:\w+|>=|==|<|>|<=)+\)(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+(?:(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+)*|\((?:\w+|>=|==|<|>|<=)+\)) Yahoo\n",line):
                m = re.fullmatch(r"It's a me a conditional (?P<cond>(?:\(+(?:\w+|>=|==|<|>|<=)+\)(?:and|or))*\(*\((?:\w+|>=|==|<|>|<=)+\)(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+(?:(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+)*|\((?:\w+|>=|==|<|>|<=)+\)) Yahoo\n",line)
                if logic(m.group("cond")):
                    condif=True
                    dentrodeif=True
                else:
                    condif=False
                    dentrodeif=True
            elif re.fullmatch(r"YA MA ((\(+(\w+|>=|==|<|>|<=)+\)(and|or))*\(*\((\w+|>=|==|<|>|<=)+\)(and|or)\((\w+|>=|==|<|>|<=)+\)+((and|or)\((\w+|>=|==|<|>|<=)+\)+)*|\((\w+|>=|==|<|>|<=)+\)) YAHOO!\n",line):
                m = re.fullmatch(r"YA MA (?P<cond>(?:\(+(?:\w+|>=|==|<|>|<=)+\)(?:and|or))*\(*\((?:\w+|>=|==|<|>|<=)+\)(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+(?:(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+)*|\((?:\w+|>=|==|<|>|<=)+\)) YAHOO!\n",line)
                if logic(m.group("cond")):
                    condstr=m.group("cond")
                    condwhile=True
                    dentrodewhile=True
            elif re.fullmatch(r"Game Over", line):
                hasstarted = False
                print("Termino la ejecucion.")
            elif re.fullmatch(r"(?P<nom>[a-zA-Z]\w+ enters (?P<par>\w+ \w+(-\w+)*\n))",line):
                m = re.fullmatch(r"((?P<var>[a-zA-Z]\w+) enters (?P<nom>\w+) (?P<par>\w+(-\w+)*\n))",line)
                valuedic[m.group("var")]=None
                valuedic[m.group("var")]=fun(m.group("nom"),m.group("par").strip("\n").split("-")) 
    return valuedic[dicfun[nom][2]]

#Entrada
fp = open("yahooo.txt","r")
linelist = fp.readlines()
valuedic={}
listapalabra=[]
listaaux= []
flagop = True
condif=True
dentrodeif=False
dentrodewhile=False
condwhile=False
pendiente=[]
hasstarted=False
dicfun={}
if inputcheck(linelist)==True:
    print("Ejecutando codigo...")
    for line in (linelist):
        flagop = True
        if re.fullmatch(r"(?P<fun>Secret Level \w+ [a-zA-Z]\w*(-[a-zA-Z]\w*)*\n)|(?P<main>Start Game\n)", line):
            TypeCheck = re.fullmatch(r"(?P<fun>Secret Level \w+ [a-zA-Z]\w*(-[a-zA-Z]\w*)*\n)|(?P<main>Start Game\n)", line)
            if TypeCheck.group("fun"):
                isfun=True
                isfuntrue=False 
                m = re.fullmatch(r"(?P<fun>Secret Level (?P<nom>\w+) (?P<par>[a-zA-Z]\w*(-[a-zA-Z]\w*)*)\n)",line)
                nom=m.group("nom")
                dicfun[nom]=[[],[],[]]
                dicfun[nom][0]=m.group("par").strip("\n").split("-")
            elif TypeCheck.group("main"):
                ismain=True
                ismaintrue=False
                hasstarted=True
        else: 
            if hasstarted:
                if dentrodewhile:
                    if condwhile:
                        pendiente.append(line)
                        if re.fullmatch(r"AH HA!\n",line):
                            dentrodewhile=False
                            pendiente.remove("AH HA!\n")
                            recursiveCycle(pendiente,condstr)
                elif dentrodeif:
                    if condif:
                        if re.fullmatch(r"(Add Player (?P<variable>[a-zA-Z]\w*)\n)",line):
                            m = re.fullmatch(r"(Add Player (?P<variable>[a-zA-Z]\w*)\n)",line)
                            valuedic[m.group("variable")]=None       
                        elif re.fullmatch(r"(?:(?P<variable>[a-zA-Z]\w*) took (?P<valor>-?\d+)\n)",line):
                            m = re.fullmatch(r"(?:(?P<variable>[a-zA-Z]\w*) took (?P<valor>-?\d+)\n)",line)
                            valuedic[m.group("variable")]=m.group("valor")
                        elif re.fullmatch(r"(([a-zA-Z]\w*) needs a power up\n)",line):
                            m = re.fullmatch(r"((?P<variable>[a-zA-Z]\w*) needs a power up\n)",line)
                            valuedic[m.group("variable")] = input("Input: ")
                        elif re.fullmatch(r"(?P<variable>[a-zA-Z]\w*) jumps to (?P<valor>\(+(?:[a-zA-Z]\w+|\d+)[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|(?:(?:\(+(?:[a-zA-Z]\w+|\d+))(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))|(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))*\)*)\n", line):
                            m = re.fullmatch(r"(?P<variable>[a-zA-Z]\w*) jumps to (?P<valor>\(+(?:[a-zA-Z]\w+|\d+)[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|(?:(?:\(+(?:[a-zA-Z]\w+|\d+))(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))|(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))*\)*)\n",line)
                            operacion = m.group("valor")
                            while flagop:
                                try:
                                    valuedic[m.group("variable")]=eval(operacion)
                                    flagop = False
                                except:
                                    #tenemos una variable entre nosotros
                                    aux = re.search(r"[a-zA-Z]\w*",operacion)
                                    remplazo = aux.group()
                                    operacion = operacion.replace(remplazo,str(valuedic[remplazo]))
                        elif re.fullmatch(r"(show (-?\d+|[a-zA-Z]?\w+)\n)",line):
                            m = re.fullmatch(r"(show ((?P<num>-?\d+)|(?P<var>[a-zA-Z]?\w+))\n)",line)
                            if m.group("num"):
                                print(int(m.group("num")))
                            else:
                                print(valuedic[m.group("var")])
                        elif re.fullmatch(r"(?P<nom>[a-zA-Z]\w+ enters (?P<par>\w+ \w+(-\w+)*\n))",line):
                            m = re.fullmatch(r"((?P<var>[a-zA-Z]\w+) enters (?P<nom>\w+) (?P<par>\w+(-\w+)*\n))",line)
                            valuedic[m.group("var")]=None
                            valuedic[m.group("var")]=fun(m.group("nom"),m.group("par").strip("\n").split("-"))
                        elif re.fullmatch(r"(Mamma Mia\.\.\.\n)",line):
                            condif=False
                    else:
                        if re.fullmatch(r"(Mamma Mia\.\.\.\n)",line):
                            condif=True
                        elif re.fullmatch(r"Let's Go!\n",line):
                            dentrodeif=False
                else:
                    if re.fullmatch(r"(Add Player (?P<variable>[a-zA-Z]\w*)\n)",line):
                        m = re.fullmatch(r"(Add Player (?P<variable>[a-zA-Z]\w*)\n)",line)
                        valuedic[m.group("variable")]=None       
                    elif re.fullmatch(r"(?:(?P<variable>[a-zA-Z]\w*) took (?P<valor>-?\d+)\n)",line):
                        m = re.fullmatch(r"(?:(?P<variable>[a-zA-Z]\w*) took (?P<valor>-?\d+)\n)",line)
                        valuedic[m.group("variable")]=m.group("valor")
                    elif re.fullmatch(r"(([a-zA-Z]\w*) needs a power up\n)",line):
                        m = re.fullmatch(r"((?P<variable>[a-zA-Z]\w*) needs a power up\n)",line)
                        valuedic[m.group("variable")] = input("Input: ")
                    elif re.fullmatch(r"(?P<variable>[a-zA-Z]\w*) jumps to (?P<valor>\(+(?:[a-zA-Z]\w+|\d+)[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|(?:(?:\(+(?:[a-zA-Z]\w+|\d+))(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))|(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))*\)*)\n", line):
                        m = re.fullmatch(r"(?P<variable>[a-zA-Z]\w*) jumps to (?P<valor>\(+(?:[a-zA-Z]\w+|\d+)[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|(?:(?:\(+(?:[a-zA-Z]\w+|\d+))(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))|(?:[\+\/\*-](?:(?:[a-zA-Z]\w+|\d+)\)+|\(*(?:[a-zA-Z]\w+|\d+))))*\)*)\n",line)
                        operacion = m.group("valor")
                        while flagop:
                            try:
                                valuedic[m.group("variable")]=eval(operacion)
                                flagop = False
                            except:
                                #tenemos una variable entre nosotros
                                aux = re.search(r"[a-zA-Z]\w*",operacion)
                                remplazo = aux.group()
                                operacion = operacion.replace(remplazo,str(valuedic[remplazo]))
                    elif re.fullmatch(r"(show (-?\d+|[a-zA-Z]?\w+)\n)",line):
                        m = re.fullmatch(r"(show ((?P<num>-?\d+)|(?P<var>[a-zA-Z]?\w+))\n)",line)
                        if m.group("num"):
                            print(int(m.group("num")))
                        else:
                            print(valuedic[m.group("var")])
                    elif re.fullmatch(r"It's a me a conditional (?P<cond>(?:\(+(?:\w+|>=|==|<|>|<=)+\)(?:and|or))*\(*\((?:\w+|>=|==|<|>|<=)+\)(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+(?:(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+)*|\((?:\w+|>=|==|<|>|<=)+\)) Yahoo\n",line):
                        m = re.fullmatch(r"It's a me a conditional (?P<cond>(?:\(+(?:\w+|>=|==|<|>|<=)+\)(?:and|or))*\(*\((?:\w+|>=|==|<|>|<=)+\)(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+(?:(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+)*|\((?:\w+|>=|==|<|>|<=)+\)) Yahoo\n",line)
                        if logic(m.group("cond")):
                            condif=True
                            dentrodeif=True
                        else:
                            condif=False
                            dentrodeif=True
                    elif re.fullmatch(r"YA MA ((\(+(\w+|>=|==|<|>|<=)+\)(and|or))*\(*\((\w+|>=|==|<|>|<=)+\)(and|or)\((\w+|>=|==|<|>|<=)+\)+((and|or)\((\w+|>=|==|<|>|<=)+\)+)*|\((\w+|>=|==|<|>|<=)+\)) YAHOO!\n",line):
                        m = re.fullmatch(r"YA MA (?P<cond>(?:\(+(?:\w+|>=|==|<|>|<=)+\)(?:and|or))*\(*\((?:\w+|>=|==|<|>|<=)+\)(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+(?:(?:and|or)\((?:\w+|>=|==|<|>|<=)+\)+)*|\((?:\w+|>=|==|<|>|<=)+\)) YAHOO!\n",line)
                        if logic(m.group("cond")):
                            condstr=m.group("cond")
                            condwhile=True
                            dentrodewhile=True
                    elif re.fullmatch(r"Game Over", line):
                        hasstarted = False
                        print("Termino la ejecucion.")
                    elif re.fullmatch(r"(?P<nom>[a-zA-Z]\w+ enters (?P<par>\w+ \w+(-\w+)*\n))",line):
                        m = re.fullmatch(r"((?P<var>[a-zA-Z]\w+) enters (?P<nom>\w+) (?P<par>\w+(-\w+)*\n))",line)
                        valuedic[m.group("var")]=None
                        valuedic[m.group("var")]=fun(m.group("nom"),m.group("par").strip("\n").split("-"))
            else:
                if isfun:
                    m = re.fullmatch(r"(?P<fun>Secret Level (?P<nom>\w+) (?P<par>[a-zA-Z]\w*(-[a-zA-Z]\w*)*)\n)",line)
                    dicfun[nom][1].append(line)
                    if re.fullmatch(r"Return to Level (?P<var>[a-zA-Z]\w+)\n",line):
                        n = re.fullmatch(r"Return to Level (?P<var>[a-zA-Z]\w+)\n",line)
                        isfun=False
                        dicfun[nom][2]=n.group("var")
fp.close()