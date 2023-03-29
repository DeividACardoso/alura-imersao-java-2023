import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        //Leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        //InputStream inputStream = new URL("").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //Cria nova imagem em memório com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        
        //Copiar a imagem original(em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);
        
        //configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 72);
        graphics.setFont(fonte);

        //escrever uma frase na nova imagem
        graphics.setColor(Color.YELLOW);
        graphics.drawString("TOPZERA", largura/4, novaImagem.getHeight() - 75);


        //escrever uma nova imagem
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }


}
