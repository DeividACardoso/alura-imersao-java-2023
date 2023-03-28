import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        //Fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        //Extrair só os dados que interessam (titulos, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body); 

        //exibir e manipular os dados
        int contador = 1; 
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(contador + ".");
            System.out.println("\u001b[1mTítulo:\u001b[m " + filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println("\u001b[1mClassifiação:\u001b[m " + filme.get("imDbRating"));
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelinhas = (int) classificacao;
            for (int n = 0; n <= numeroEstrelinhas; n++) {
                if(numeroEstrelinhas < 6){
                    System.out.println("🍅");
                } else {
                    if(numeroEstrelinhas < 4){
                        System.out.println("👎");
                    }
                    if(numeroEstrelinhas > 5){
                        System.out.print("⭐");
                    }
                }
            }
            
            System.out.println("\n");
            contador++;
        }

    }
}
