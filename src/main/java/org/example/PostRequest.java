package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostRequest {
    public static void main(String[] args) {
        try {
            String urlString = "http://localhost:2222/requisicaopost1"; // Substitua pela URL desejada
            URL url = new URL(urlString);  // Crie uma URL com a String
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  // Abra uma conexão HTTP com a URL
            connection.setRequestMethod("POST");  // Defina o método HTTP para POST
            connection.setDoOutput(true);  // Habilita a saída de dados

            // Corpo da solicitação POST
            String postData = "nome=value1&idade=value2";  // Corpo da solicitação POST

            // Configurar o tipo de conteúdo
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Obtenha o fluxo de saída da conexão para escrever os dados POST
            try (OutputStream os = connection.getOutputStream()) {  // Abra o fluxo de saída da conexão
                byte[] input = postData.getBytes("UTF-8"); // Converta a String em bytes
                os.write(input, 0, input.length);  // Escreva os dados POST na conexão
            }

            int responseCode = connection.getResponseCode();  // Obtenha o código de resposta da solicitação

            if (responseCode == HttpURLConnection.HTTP_OK) {  // Verifique se a resposta é HTTP OK
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));  // Crie um leitor de fluxo de entrada
                String line;  // Variável para armazenar cada linha do fluxo de entrada
                StringBuilder response = new StringBuilder();  // Variável para armazenar a resposta

                while ((line = reader.readLine()) != null) {  // Enquanto houver linhas no fluxo de entrada
                    response.append(line);  // Adicione a linha à resposta
                }

                reader.close();  // Feche o leitor de fluxo de entrada

                System.out.println(responseCode);
                System.out.println("Response from the server:");
                System.out.println(response.toString());
            } else {
                System.out.println("POST request failed with response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
