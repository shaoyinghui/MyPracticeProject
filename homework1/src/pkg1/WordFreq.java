package pkg1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFreq {
	private int freq;
WordFreq(){
	freq = 0;
}
public static void search (String s[]){
    Map m = new HashMap();
    for (int i = 0; i < s.length; i++) {
	int freq = (Integer) m.get(s[i]) == null ? 0 : (Integer) m.get(s[i]);
	m.put(s[i], freq==0 ? 1 : freq + 1);
       }

    Collection<String> keyset= m.keySet();  
    List<String> list = new ArrayList<String>(keyset);  
    Collections.sort(list);  


try{
    File file = new File("d:\\result.txt");
    FileWriter fw = new FileWriter(file.getAbsoluteFile());
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(" 检索到"+m.size() + "个单词     ");
   for (int i = 0; i < list.size(); i++) { 
	bw.newLine();
    bw.write(list.get(i)+" 个数为:"+m.get(list.get(i))+"  ");}
   bw.close();


}catch(IOException e){
e.printStackTrace();
	}

}
	public static void main(String args[]){
   WordFreq w = new WordFreq();
   String f = readTxtFile("D:\\1.txt");
   String[] split = f.split(" |\\,|\\.");
   search(split);
	}
	public static String readTxtFile(String filePath){
             String txt = "" ;
        try {
                String encoding="GB2312";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ 
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                txt = bufferedReader.readLine();
                read.close();

                }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
                        return txt;
    }
}
