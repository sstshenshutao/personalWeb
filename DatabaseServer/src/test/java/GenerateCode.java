import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateCode {

  public static void main (String[] args) {
    String className="user_security";
    String classNameJAVA="UserSecurity";
    String databaseSTr="+--------------+--------------+------+-----+---------+-------+\n"
      + "| uid          | int(11)      | NO   | PRI | NULL    |       |\n"
      + "| username     | varchar(20)  | NO   | UNI | NULL    |       |\n"
      + "| email        | varchar(50)  | NO   | UNI | NULL    |       |\n"
      + "| question1    | varchar(255) | YES  |     |         |       |\n"
      + "| answer1      | varchar(255) | YES  |     |         |       |\n"
      + "| question2    | varchar(255) | YES  |     |         |       |\n"
      + "| answer2      | varchar(255) | YES  |     |         |       |\n"
      + "| handy_number | varchar(20)  | NO   | UNI | NULL    |       |\n"
      + "| password     | varchar(255) | NO   |     | NULL    |       |\n"
      + "+--------------+--------------+------+-----+---------+-------+";
    List<String> everyLine=Arrays.asList(databaseSTr.split("\n"));
    everyLine=everyLine.stream().filter(x->!x.startsWith("+")).collect(Collectors.toList());
    List<GeneCodeDS> parseList = everyLine.stream().map(x->{
      String[] xs=x.split("\\|");
      String tmp=xs[2].trim();
      int index = tmp.indexOf("(");
      if(index>=0){
        tmp=tmp.substring(0,index);
      }
      GeneCodeDS obj= new GeneCodeDS(xs[1].trim(),tmp.contains("varchar")?tmp.contains("tinyint")?"boolean":"String":
                                                  tmp);
      obj.varNonNull = xs[3].trim().equals("NO")?true:false;
      return obj;
    }).collect(Collectors.toList());
//    parseList.forEach(x-> System.out.println(x.toString()));
    //generate class
    StringBuffer attributeStrBF=new StringBuffer();
    parseList.stream().sequential().forEach(x->{
      if (x.varNonNull){
        attributeStrBF.append("@NotNull\n");
      }
      attributeStrBF.append(x.varType+" "+x.varName+";\n");
    });
    String outString = "public class "+classNameJAVA+"{\n"
      +attributeStrBF.toString()+"}";
    System.out.println(outString);
    System.out.println();
    String mapperString = "@Mapper @Component public interface "+classNameJAVA+"Mapper {\n";
    //insert
    StringBuffer insertStringBF = new StringBuffer();
    parseList.stream().sequential().forEach(x->{
      insertStringBF.append(x.varName);
      insertStringBF.append(",");
      });
    String insertString= insertStringBF.toString();
    insertString=insertString.substring(0,insertString.length()-1);

    StringBuffer insertStringBF2 = new StringBuffer();
    parseList.stream().sequential().forEach(x->{
      insertStringBF2.append("#{");
      insertStringBF2.append(x.varName);
      insertStringBF2.append("},");
    });
    String insertString2= insertStringBF2.toString();
    insertString2=insertString2.substring(0,insertString2.length()-1);

    mapperString+="@Insert(\"INSERT INTO `"+className+"`("+insertString+") \"\n"
      + "            + \"VALUES("+insertString2+")\")\n"
      + "  boolean insert ("+classNameJAVA+" model);\n";
    mapperString+="  @Delete(\"DELETE FROM `"+className+"` WHERE uid=#{uid}\")\n" + "  boolean delete (Integer uid);";
    mapperString+="  @Select(\"SELECT * FROM `"+className+"` WHERE uid=#{uid}\")\n" + "  "+classNameJAVA+" select "
      + "(int uid);\n";
    mapperString+="  @Select(\"SELECT * FROM `"+className+"`\")\n" + "  List<"+classNameJAVA+"> selectAll ();";

//update
    StringBuffer updateStringBF = new StringBuffer();
    parseList.stream().sequential().forEach(x->{
      updateStringBF.append(x.varName);
      updateStringBF.append("=#{");
      updateStringBF.append(x.varName);
      updateStringBF.append("},");
    });
    String updateString= updateStringBF.toString();
    updateString=updateString.substring(0,updateString.length()-1);

    mapperString+="  @Update(\"UPDATE `"+className+"` SET "+updateString+"\" + \" WHERE uid=#{uid}\")\n"
      + "  int update ("+classNameJAVA+" model);";
    mapperString+="\n}";
    System.out.println(mapperString);


    //service
    System.out.println();
    String serviceString= "@Service public class "+classNameJAVA+"Service {\n";
    serviceString+="private final "+classNameJAVA+"Mapper dao;\n";
    serviceString+="@Autowired\n" + "  public "+classNameJAVA+"Service ("+classNameJAVA+"Mapper dao) {\n" + "    this.dao = dao;\n"
      + "  }\n";
    serviceString+="public boolean insert"+classNameJAVA+" ("+classNameJAVA+" model) {\n"
      + "    return dao.insert(model);\n" + "  }\n";
    serviceString+="public "+classNameJAVA+" select"+classNameJAVA+" (int uid) {\n" + "    return dao.select(uid);\n" + "  }\n";
    //handle select each
    StringBuffer selectEachStringBF = new StringBuffer();
    parseList.stream().sequential().forEach(x->{

      String firstUP=Character.toUpperCase(x.varName.charAt(0))+x.varName.substring(1,x.varName.length());
      selectEachStringBF.append("public "+x.varType+" "+(x.varType.equals("boolean")?"is"+firstUP:"get"+ firstUP)+" (int "
        + "uid) {\n"
        + "    return select"+classNameJAVA+"(uid)."+(x.varType.equals("boolean")?"is"+firstUP:"get"+ firstUP)+"();"
        + "\n" + "  }\n");

    });
    String selectEachString= selectEachStringBF.toString();
    serviceString+=selectEachString;

    serviceString+="public List<"+classNameJAVA+"> selectAllEntries () {\n" + "    return dao.selectAll();\n" + "  }\n";
    serviceString+="public int updateEntry ("+classNameJAVA+" model) {\n" + "    return dao.update(model);\n" + "  }\n";
    serviceString+="public boolean deleteEntry (Integer uid) {\n" + "    return dao.delete(uid);\n" + "  }\n";
    serviceString+="}";
    System.out.println(serviceString);
  }
}
