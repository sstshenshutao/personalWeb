public class GeneCodeDS {
  String varName;
  String varType;
  boolean varNonNull;

  public GeneCodeDS (String varName, String varType) {
    this.varName = varName;
    this.varType = varType;
  }

  @Override
  public String toString () {
    return "varName:"+varName+"|"+"varType:"+varType+"|"+"varNonNull:"+varNonNull;
  }

}
