package com.example.alander.tt;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Conexao {

    private String dados=null;

    public String getArduino(String urlString){

        try {
            URL url= new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if (httpURLConnection.getResponseCode()==200){

                //Componente responsavel por ler os dados atraves da rede
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());

                //Para tratamento ex:acentos,etc
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                //remonta os dados
                StringBuilder stringBuilder= new StringBuilder();

                String linha;

                while((linha = bufferedReader.readLine())!= null){

                    stringBuilder.append(linha);
                }

                dados= stringBuilder.toString();

                //Fechar conexao
                httpURLConnection.disconnect();
            }

        }catch (IOException erro){
            return null;
        }

        return dados;
    }
}
