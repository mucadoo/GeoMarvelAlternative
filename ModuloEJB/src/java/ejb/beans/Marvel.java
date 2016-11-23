package ejb.beans;

import ejb.entities.Personagem;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class Marvel {

    private static String apikey = "";
    private static String privatekey = "";
    private static String urlbase = "http://gateway.marvel.com/v1/public/characters";

    public static Personagem pegaPersonagem(String nome) {
        //Criação de um timestamp
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhhmmss");
        String ts = sdf.format(date);

        //Criação do HASH
        String hashStr = MD5(ts + privatekey + apikey);
        String uri;
        String resposta = null;
        //url de consulta
        uri = urlbase + "?nameStartsWith=" + nome + "&ts=" + ts + "&apikey=" + apikey + "&hash=" + hashStr + "&limit=1";

        try {
            HttpClient cliente = HttpClients.createDefault();

            //HttpHost proxy = new HttpHost("172.16.0.10", 3128, "http");
            //RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            HttpGet httpget = new HttpGet(uri.replace(" ", "%20"));
            //httpget.setConfig(config);
            HttpResponse response = cliente.execute(httpget);
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            Header[] h = response.getAllHeaders();

            for (Header head : h) {
                System.out.println(head.getValue());
            }

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                StringBuilder out = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    out.append(line);
                }
                System.out.println(out.toString());
                reader.close();
                instream.close();
                resposta = out.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject respostaJson = new JSONObject(resposta);
        String nomeRetorno = respostaJson.getJSONObject("data").getJSONArray("results").getJSONObject(0).getString("name");
        String imagemRetorno = respostaJson.getJSONObject("data").getJSONArray("results").getJSONObject(0).getJSONObject("thumbnail").getString("path") + "." + respostaJson.getJSONObject("data").getJSONArray("results").getJSONObject(0).getJSONObject("thumbnail").getString("extension");
        Personagem personagem = new Personagem(nomeRetorno, imagemRetorno);
        return personagem;
    }

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

}
