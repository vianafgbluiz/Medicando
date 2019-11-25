package com.mediquei.datamodel;

import android.content.SharedPreferences;

public class LinkServerDataModel {
    /* Classe para conter os links que será usado na aplicação
     * do BackEnd
     * Dados da Clase LINKSSERVER
     */

    private final static String APIBuscarDadosCompra = "APIBuscarDadosCompra.php";
    private final static String APIBuscarDadosEspecificos = "APIBuscarDadosEspecificos.php";
    private final static String APIBuscarDadosLogin = "APIBuscarDadosLogin.php";
    private final static String APIBuscarEnderecosFarmUser = "APIBuscarEnderecosFarmUser.php";
    private final static String APIBuscarEnderecosFarmUserCompra = "APIBuscarEnderecosFarmUserCompra.php";
    private final static String APIBuscarMedicamentos = "APIBuscarMedicamentos.php";
    private final static String APIBuscarOutrasOpcoes = "APIBuscarOutrasOpcoes.php";
    private final static String APIBuscarPedidos = "APIBuscarPedidos.php";
    private final static String APIBuscarResultados = "APIBuscarResultados.php";
    private final static String APIBuscarTodosUsuarios = "APIBuscarTodosUsuarios.php";
    //private final static String APIBuscarDadosLogin = "buscar.php";
    private final static String APICadastrarEnderecoUsuario = "APICadastrarEnderecoUsuario.php";
    private final static String APICadastrarFormaPgmt = "APICadastrarFormaPgmt.php";
    private final static String APICadastrarPedido = "APICadastrarPedido.php";
    private final static String APICadastrarUsuario = "APICadastrarUsuario.php";


    public static String getAPICadastrarEnderecoUsuario() {
        return APICadastrarEnderecoUsuario;
    }

    public static String getAPIBuscarDadosLogin() {
        return APIBuscarDadosLogin;
    }

    public static String getAPIBuscarTodosUsuarios() {
        return APIBuscarTodosUsuarios;
    }

    public static String getAPICadastrarUsuario() {
        return APICadastrarUsuario;
    }

    public static String getAPIBuscarDadosEspecificos() {
        return APIBuscarDadosEspecificos;
    }

    public static String getAPICadastrarFormaPgmt() {
        return APICadastrarFormaPgmt;
    }

    public static String getAPIBuscarResultados() {
        return APIBuscarResultados;
    }

    public static String getAPIBuscarEnderecosFarmUser() {
        return APIBuscarEnderecosFarmUser;
    }

    public static String getAPIBuscarEnderecosFarmUserCompra() {
        return APIBuscarEnderecosFarmUserCompra;
    }

    public static String getAPIBuscarDadosCompra() {
        return APIBuscarDadosCompra;
    }

    public static String getAPICadastrarPedido() {
        return APICadastrarPedido;
    }

    public static String getAPIBuscarPedidos() {
        return APIBuscarPedidos;
    }

    public static String getAPIBuscarMedicamentos() {
        return APIBuscarMedicamentos;
    }

    public static String getAPIBuscarOutrasOpcoes() {
        return APIBuscarOutrasOpcoes;
    }
}
