import com.cybertaotao.database.user.root.data.User;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class TestPostPage {

  public static void main (String[] args) {
    RestTemplate restTemplate = new RestTemplate();
    User a =new User();
    a.setUid(10);
    a.setUsername("a10");
    String b=restTemplate
      .postForObject("http://localhost:7110/user/10",a, String.class);
    System.out.println(a);
  }
}
