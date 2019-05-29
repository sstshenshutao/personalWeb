import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class NetworkWebScanner {

  private CloseableHttpClient httpclient = null;
  private CookieStore httpCookieStore = null;
  private HttpGet httpGet = null;
  private HttpPost httpPost = null;
  private final static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36";
  private final static int DEFAULT_SOCKET_MAX_TOTAL = 5000;
  private final static int DEFAULT_CONNECT_TIMEOUT = 1000;
  private final static int DEFAULT_REQUEST_TIMEOUT = 2000;
  private final static int DEFAULT_SOCKET_TIMEOUT = 1000;

  String pStStudiumNr = new String();
  String pSJNr = new String();
  String pStpStpNr = new String();
  String pPersonenId = new String();
  String pPersonenGruppe = new String();

  public NetworkWebScanner () {
    init();
  }

  private void init () {
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setDefaultMaxPerRoute(DEFAULT_SOCKET_MAX_TOTAL);
    connectionManager.setMaxTotal(DEFAULT_SOCKET_MAX_TOTAL);
    RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(DEFAULT_CONNECT_TIMEOUT)
                                               .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT)
                                               .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).setRedirectsEnabled(true)
                                               .setCircularRedirectsAllowed(true).setCookieSpec(CookieSpecs.STANDARD)
                                               .build();
    this.httpCookieStore = new BasicCookieStore();
    HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore)
                                                 .setUserAgent(USER_AGENT).setDefaultRequestConfig(requestConfig)
                                                 .setConnectionManager(connectionManager);
    this.httpclient = builder.build();
    this.httpGet = new HttpGet();
    this.httpPost = new HttpPost();
  }

  public CloseableHttpResponse tryConnectTo (String url, HashMap<String, String> headers) throws IOException {
    HttpGet hg = null;
    try {
      hg = new HttpGet();
      hg.setURI(new URI(url));
    } catch (URISyntaxException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    // manageCookie(this.httpGet);
    if (headers != null) {
      for (String k : headers.keySet()) {
        hg.setHeader(k, headers.get(k));
      }
    }
    CloseableHttpResponse response1 = null;
    response1 = httpclient.execute(hg);
    return response1;
  }

  public static void main (String[] args) {
    NetworkWebScanner wc = new NetworkWebScanner();
    List<String> ls=LogForm.getList();
    ls.stream().parallel().forEach(x->{
      try {
        CloseableHttpResponse response = wc.tryConnectTo(x, null);
        BufferedReader br=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        while((line=br.readLine())!=null){
          if(line.contains("Tut_01")){
            System.out.println(x);
            System.exit(0);
          }
//          System.out.println(line);
        }
//        System.out.println(x+"nothing");
      }catch (IOException e){

      }
    });
  }
  public static void getDnsAvailibleWeb () {
    Logger logger1 = Logger.getLogger("https");
    try {
      logger1.addHandler(new FileHandler("https"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    Logger logger2 =Logger.getLogger("http");
    try {
      logger2.addHandler(new FileHandler("http"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    List<String> all4list = new ArrayList<>();
    for (int i = 'u'; i <= 'u'; i++) {
      for (int j = 'a'; j <= 'z'; j++) {
        for (int k = 'a'; k <= 'z'; k++) {
          for (int l = 'a'; l <= 'z'; l++) {
            all4list.add("https://grnvs." + (char) i + (char) j + (char) k + (char) l + ".org");
          }
        }
      }
    }
    List<String> dnsAvailible4list = new ArrayList<>();
    List<Integer> allReturnList = new ArrayList<>();
    List<String> allExceptionList = new ArrayList<>();
    NetworkWebScanner wc = new NetworkWebScanner();
    all4list.stream().parallel().forEach(x -> {
      try {
        CloseableHttpResponse response = wc.tryConnectTo(x, null);
        if (response != null) {
          int statusCode = response.getStatusLine().getStatusCode();
        }
        //        dnsAvailible4list.add(x);

        logger1.info(x);
        System.out.println("successful:" + x);
      } catch (java.net.UnknownHostException ee) {
      } catch (org.apache.http.conn.ConnectTimeoutException ww) {
      } catch (org.apache.http.conn.HttpHostConnectException www) {
      } catch (javax.net.ssl.SSLException sse) {
        String aa = "http" + x.substring("https".length());
        dnsAvailible4list.add(aa);
        //        System.out.println("successful:"+x);
        logger2.info(aa);
      } catch (java.net.SocketException se) {
      } catch (IOException e) {
        if (!allExceptionList.contains(e.getClass().getName())) {
          allExceptionList.add(e.getClass().getName());
          //          System.out.println(e);
        }
      }
    });
    System.out.println(dnsAvailible4list.size());
    //    dnsAvailible4list.forEach(x -> {
    //      CloseableHttpResponse response = wc.tryConnectTo(x, null);
    //      if (response != null) {
    //        int statusCode = response.getStatusLine().getStatusCode();
    //        if (!allReturnList.contains(statusCode)) {
    //          allReturnList.add(statusCode);
    //        }
    //      }
    //    });
    //    allReturnList.forEach(x -> System.out.println(x));
  }

}
