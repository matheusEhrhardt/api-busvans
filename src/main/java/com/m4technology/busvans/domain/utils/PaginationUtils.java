package com.m4technology.busvans.domain.utils;

public class PaginationUtils {

    public static int getPagina(int pagina,int limite){
        if (pagina == 1){
            return 0;
        } else {
            return ((pagina - 1) * limite) - 1;
        }
    }

    public static int getLimite(int pagina,int limite){
        if (pagina == 1){
            return limite - 1;
        } else {
            return (pagina * limite) - 1;
        }
    }
}


/*
pagina 1 - 0 = pagina - 1
limite 5 - 4 = limite - 1

pagina 2 - 4 = ((pagina - 1) * limite) - 1
limite 5 - 9 = (pagina * limite) - 1

pagina 3 - 9 = ((pagina - 1) * limite) - 1
limite 5 - 14 = (pagina * limite) - 1

pagina 4 - 14
limite 5 - 19

 */