
package webcrawler;
import java.util.Date;

/**
 *
 * @author gaurav
 */
public class Frontpage {
    private int id;
    private String link;
    private Domain durl;
    private Date created;

    public Frontpage(String link, Domain durl, Date created) {
        this.link = link;
        this.durl = durl;
        this.created = created;
    }
}
