
package webcrawler;

import java.util.Date;


public class Page_html {
    private int id;
    private byte[] compressedhtml;
    private Domain urldomain;
    private Date  visit_date;

    public Page_html(String html, Domain urldomain, Date visit_date) throws Exception {
        this.compressedhtml = new ComUtility().Compress(html);
        this.urldomain = urldomain;
        this.visit_date = visit_date;
    }
   
   // save html page to database
    public void store()
    {
        
    }

    public int getId() {
        return id;
    }

    public byte[] getCompressedhtml() {
        return compressedhtml;
    }

    public Domain getUrldomain() {
        return urldomain;
    }

    public Date getVisit_date() {
        return visit_date;
    }
    
}
