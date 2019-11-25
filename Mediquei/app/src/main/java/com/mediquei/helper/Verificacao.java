package com.mediquei.helper;

import android.content.Context;

import com.mediquei.R;
import com.mediquei.config.CreditCard;
import com.mediquei.model.Bancarios;
import com.mediquei.model.Endereco;
import com.mediquei.model.ProdutoFinal;

public class Verificacao {

    private static int ERRO_QTDADE = 1;
    private static int ERRO_TAXA_ENTREGA = 2;
    private static int ERRO_QUANTAS_VEZES = 3;
    private static int ERRO_NOME = 4;

    public static boolean verificaCEPInserido(String cep){
        char[] characters = cep.toCharArray();

        /*Verifica se não está vazio*/
        if(cep.isEmpty()){
            return false;
        }

        /*Verifica se foram digitados 8 character*/
        if(characters.length != 8){
            return false;
        }

        /*Verifica se todos as entradas sao Digitos*/
        for (char character : characters) {
            if (!Character.isDigit(character)) {
                return false;
            }
        }
        return true;
    }

    public static Endereco formataEnderecos(Context context, Endereco endereco){
        /*Recuperando ID*/
        UsuarioPreferences preferences = new UsuarioPreferences(context);
        int idfk = preferences.recuperarIdUsuarioPreferences();

        endereco.setEndIdfk(idfk);

        /*Verificando se o CEP esta nulo*/
        if(endereco.getEndCep().isEmpty()){
            endereco.setEndCep("XXXXXYYY");
        }

        /*Verificando se o UF esta nulo*/
        if(endereco.getEndUF().isEmpty()){
            endereco.setEndUF("XX");
        }

        /*Verificando se o Cidade esta nulo*/
        if(endereco.getEndCidade().isEmpty()){
            endereco.setEndCidade("XXXXX");
        }

        /*Verificando se o Bairro esta nulo*/
        if(endereco.getEndBairro().isEmpty()){
            endereco.setEndBairro("XXXXX");
        }

        /*Verificando se o Logradouro esta nulo*/
        if(endereco.getEndLogradouro().isEmpty()){
            endereco.setEndLogradouro("R. XXXXX");
        }

        /*Verificando se o Complemento esta nulo*/
        if(endereco.getEndComplemento().isEmpty()){
            endereco.setEndComplemento("XXXXX");
        }

        /*Verificando se o Numero esta nulo*/
        if(endereco.getEndNumero().isEmpty()){
            endereco.setEndNumero("0");
        }

        return endereco;
    }

    /*Verifica se todos os campos preenchidos estao corretos*/
    public static boolean verificaFormaPgmtPreenchido(Bancarios bancarios){
        if(bancarios.getBanNomeCartao().isEmpty()) {
            return false;
        }
        if(bancarios.getBanCPF().isEmpty() || bancarios.getBanCPF().length() != 11){
            return false;
        }
        if(bancarios.getBanIdUsuario() == 0) {
            return false;
        }
        if(bancarios.getBanValidade().isEmpty() || bancarios.getBanValidade().length() != 4) {
            return false;
        }
        if(bancarios.getBanCodSeguranca().isEmpty() || bancarios.getBanCodSeguranca().length() != 3) {
            return false;
        }

        if(bancarios.getBanNumero().isEmpty()){
            return false;
        }
        return true;
    }

    /*Formata os Campos Bancarios*/
    public static Bancarios formataCamposBancarios(Bancarios bancarios){
        bancarios.setBanNomeCartao(Formatacoes.formataNomeBancarios(bancarios.getBanNomeCartao()));
        bancarios.setBanCPF(Formatacoes.formataCPF(bancarios.getBanCPF()));
        bancarios.setBanValidade(Formatacoes.formataValidade(bancarios.getBanValidade()));
        /*Determina a bandeira*/
        CreditCard creditCard = new CreditCard(bancarios.getBanNumero());
        bancarios.setBanIdBandeira(creditCard.getBrand());
        return bancarios;
    }

    /*Verifica qual imagem deve ser retornada*/
    public static int retornImagemEscolhida(int id){
        switch (id){
            case 1:
                id =  R.drawable.bandeira_amex;
                break;
            case 2:
                id = R.drawable.bandeira_dinners_club;
            case 3:
                id = R.drawable.bandeira_elo;
                break;
            case 4:
                id = R.drawable.bandeira_hipercard;
                break;
            case 5:
                id = R.drawable.bandeira_master_card;
                break;
            case 6:
                id = R.drawable.bandeira_visa;
                break;
        }
        return id;
    }

    /*Retorna o ERRO se tiver*/
    public static int retornaCodigoErro(ProdutoFinal pFinal){
        if(pFinal.getQtdadeProdutoFinal() == 0){
            return ERRO_QTDADE;
        } else if(pFinal.getTaxaEntregaFinal() == 0){
            return ERRO_TAXA_ENTREGA;
        } else if (pFinal.getQtdadeParcelasFinal() == 0){
            return ERRO_QUANTAS_VEZES;
        } else if (pFinal.getMedicNomeFinal().isEmpty() || pFinal.getFarmNomeFinal().isEmpty()){
            return ERRO_NOME;
        }
        return 0;
    }
}
