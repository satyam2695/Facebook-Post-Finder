package webcrawler;

public class Url_domain 
{        public Url_domain(String Url_raw, String dom)
    {}
    public static String  urlinformat(String Url_raw, String dom)
    {     String Url_new=Url_raw;
         
         if(!Url_new.startsWith(dom))
        {
         if(!Url_new.startsWith("http"))
            {
                if(Url_new.startsWith("/"))
                     Url_new=dom.concat(Url_new);
                 else
                     Url_new=dom.concat("/"+Url_new);
            }
         else
            {
                String temp=Url_new.split("/")[2];
                   if(temp.lastIndexOf(".")>100)
                        Url_new=temp.concat(dom);
            }
        }
          return Url_new;
          
    }
    
}
