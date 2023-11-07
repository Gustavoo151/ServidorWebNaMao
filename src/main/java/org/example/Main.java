package org.example;

import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(2222);  // cria um socket de servidor na porta 2222

            while (true) {  // loop infinito para aguardar conexões
                Socket conexao = servidor.accept();  // aguarda conexão com o cliente

                InputStream is = conexao.getInputStream(); // obtém o fluxo de entrada da conexão
                is = new BufferedInputStream(is);  // cria um buffer para o fluxo de entrada

                BufferedReader reader = new BufferedReader(new InputStreamReader(is)); // cria um leitor de fluxo de entrada

                String resposta = "";  // variável para armazenar a resposta da requisição do cliente
                String path = getRequestPath(reader.readLine()); // obtém o caminho da requisição do cliente

                if (path.equals("/endpoint1")) {  // verifica se o caminho é o endpoint1, retorna 200
                    resposta = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n" +
                            "<html><title>Endpoint 1 </title><body><h1>Endpoint 1 - Para http 200</h1></body></html>";

                } else if (path.equals("/endpoint2")) { // verifica se o caminho é o endpoint2, retorna 404
                    resposta = "HTTP/1.1 404 Not Found\r\nContent-Type: text/html\r\n\r\n" +
                            "<html><title>Endpoint 2</title><body><h1>Endpoint 2 - Para http 404</h1></body></html>";

                } else if (path.equals("/endpoint3")) { // verifica se o caminho é o endpoint3, retorna 500
                    resposta = "HTTP/1.1 500 Internal Server Error\r\nContent-Type: text/html\r\n\r\n" +
                            "<html><title>Endpoint 3</title><body><h1>Endpoint 3 - Para http 500</h1></body></html>";

                } else { // caso o caminho não seja nenhum dos endpoints, retorna 404
                    resposta = "HTTP/1.1 404 Not Found\r\nContent-Type: text/html\r\n\r\n" +
                            "<html><title>Not Found</title><body><h1>404 - Not Found</h1></body></html>";
                }

                conexao.getOutputStream().write(resposta.getBytes());  // Aqui envia a resposta para o cliente, convertendo a string em bytes, que é o que o cliente espera receber.
                conexao.close();  //  fecha a conexão com o cliente
            }
        } catch (IOException e) { // trata exceções de entrada e saída
            e.printStackTrace();
        }
    }

    private static String getRequestPath(String requestLine) { // Esse método pega a linha de requisição do cliente e retorna o caminho da requisição
        if (requestLine != null) {
            String[] parts = requestLine.split(" ");
            if (parts.length >= 2) {
                return parts[1];  // retorna o caminho da requisição.  Ex: /endpoint1
            }
        }
        return "/";  // retorna a raiz, caso não encontre o caminho da requisição.
    }
}