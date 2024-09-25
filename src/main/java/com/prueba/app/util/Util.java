package com.prueba.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {


    public static boolean validarValorExpresionRegular(String valor, String expReg) {
        Pattern pattern = Pattern.compile(expReg);
        Matcher matcher = pattern.matcher(valor);
        return matcher.matches();
    }


}
