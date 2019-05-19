package dataStructure;

import java.io.Serializable;

public class DrawAppAttribute implements Serializable {

  private static final long serialVersionUID = -3343727125953190575L;
  //  List<链接(e.g. cybertaotao/app=id),name,是否选中，是否可以选（running?）> 新建app属性数据类型
  public String link;
  public String name;
  public int id;
  public boolean isChoosed;
  public boolean isRunning;

  public DrawAppAttribute () {
  }

  public DrawAppAttribute (String link, String name, int id, boolean isChoosed, boolean isRunning) {
    this.link = link;
    this.name = name;
    this.id = id;
    this.isChoosed = isChoosed;
    this.isRunning = isRunning;
  }

  public void setLink (String link) {
    this.link = link;
  }

  public void setName (String name) {
    this.name = name;
  }

  public void setId (int id) {
    this.id = id;
  }

  public void setChoosed (boolean choosed) {
    isChoosed = choosed;
  }

  public void setRunning (boolean running) {
    isRunning = running;
  }

  private DrawAppAttribute (Builder builder) {
    this.link = builder.link;
    this.name = builder.name;
    this.id = builder.id;
    this.isChoosed = builder.isChoosed;
    this.isRunning = builder.isRunning;
  }

  public static class Builder {

    String link = "noValue";
    String name = "noValue";
    int id = -1;
    boolean isChoosed = false;
    boolean isRunning = true;

    public DrawAppAttribute build () {
      return new DrawAppAttribute(this);
    }

    public Builder setLink (String link) {
      this.link = link;
      return this;
    }

    public Builder setName (String name) {
      this.name = name;
      return this;
    }

    public Builder setId (int id) {
      this.id = id;
      return this;
    }

    public Builder setChoosed (boolean choosed) {
      isChoosed = choosed;
      return this;
    }

    public Builder setRunning (boolean running) {
      isRunning = running;
      return this;
    }

  }

}
