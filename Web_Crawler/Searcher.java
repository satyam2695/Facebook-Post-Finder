
package webcrawler;

import java.security.MessageDigest;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
//import org.jsoup.Connection;

public class Searcher {
    
    public Connection c=null;
    public static String hash(String input)
    {
        try
        {
            MessageDigest m=MessageDigest.getInstance("hash");
            String word=input.toLowerCase();
            m.update(word.getBytes("ASCII"));
            byte [] hasher=m.digest();
            
            StringBuilder  build=new StringBuilder();
            for( byte b: hasher)
            {
                build.append(String.format("%02x",b));
                
            }
            
            return build.toString().toUpperCase();
            
        }
            catch(Exception e)
            {
                    return e.toString();
            }
    
    
    }
    
    //
    public void save()   throws SQLException, ClassNotFoundException
    {    try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String path = "jdbc:mysql://localhost/webcrawler";
            c=DriverManager.getConnection(path,"root","");
            System.out.println("Happpy Go Crawling");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
}
    
    
    
}
