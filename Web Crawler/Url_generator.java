/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;
import java.util.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author gaurav
 */
public class Url_generator {
    private String inputword;
    private Document d;
  HashSet<String> tags=new HashSet<String>();
    crawler c;
    public void Begin() {
             //  application logic here
             System.out.print(88);
             String url=geturl();
             Searcher s=new Searcher();
             Filterdomain domain=new Filterdomain(Searcher.hash(url),url);
             Domain dom=new Domain (domain.getDhash(),domain.getDurl(),domain);
             s.hash(url);
                     
            HashSet<String> tags=new HashSet<String>();
           try
           {
              d = Jsoup.connect(dom.getDurl()).get();
                     Page_html webpage=new Page_html(d.html(),dom,new Date());
              Frontpage page=new Frontpage("",dom,new Date());
               Elements reference = d.select("a");
            for (Element iter:reference)
            {   String temp=iter.attr("href");
                temp=Handler_html.Url(temp,domain);
                tags.add(temp);
        //      System.out.println(temp);
             }
        } catch (IOException ex) {
            Logger.getLogger(Url_generator.class.getName()).log(Level.SEVERE, null, ex);
        }
           catch (Exception ex) {
                     Logger.getLogger(Url_generator.class.getName()).log(Level.SEVERE, null, ex);
                 }
                  
      
         
         for (String  itr:tags  ){
             System.out.println(itr);
         }
         
         System.out.println("Number of pages visited :"+tags.size());
      
         System.out.println("----------------------------------------------------");
         
         System.out.println("Want to search for the word? Press y /n");
           Scanner read=new Scanner(System.in);
         String check=read.nextLine();
         
         if(check.equals("y"))
         { inputword=read.nextLine();  
         if(searchword())
             System.out.println("Word Found Matching");
             else
             System.out.println("Not found");
         } 
         
         
         if (check=="n")
             return;
      
    
    }
    
    
       public boolean searchword()
    { 
        // Defensive coding
        if(this.d == null)
        {
            System.out.println("ERROR! Crawl first");
            return false;
        }
        else
   
        {
        System.out.println(" Searching word " + inputword+ "...");
        String body = this.d.body().text();
        return body.toUpperCase().contains(inputword.toUpperCase());
    }
        }
    public  String geturl()
    {
     /*   Scanner read=new Scanner(System.in);
       String inputurl=read.nextLine();*/
             //.String  inputurl="http://www.jsoup.org"; 
        
        String inputurl=c.s;
        //System.out.print(c.s);
             if (inputurl.startsWith("www"))
                 inputurl="http://"+inputurl;
              return inputurl;
    }
    
}
