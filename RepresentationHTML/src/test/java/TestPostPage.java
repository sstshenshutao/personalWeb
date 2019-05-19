import dataStructure.DrawAppAttribute;
import dataStructure.DumpFile;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class TestPostPage {

  public static void main (String[] args) {
    RestTemplate restTemplate = new RestTemplate();
    //
    List<DrawAppAttribute> drawAppAttributes =  new ArrayList<>();
    DrawAppAttribute d=
      new DrawAppAttribute.Builder().setId(5).setChoosed(true).setLink("https://cybertaotao.com").setName("title1").setRunning(true).build();
    DrawAppAttribute d2=
      new DrawAppAttribute.Builder().setId(5).setChoosed(true).setLink("https://cybertaotao.com").setName("title2").setRunning(true).build();
    drawAppAttributes.add(d);
    drawAppAttributes.add(d2);
    String a=restTemplate
      .postForObject("http://localhost:4443/representation/",new DumpFile("test", drawAppAttributes), String.class);
    System.out.println(a);
  }

}
