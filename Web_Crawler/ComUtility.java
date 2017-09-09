package webcrawler;

import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ComUtility {

    public byte[] Compress(String input) throws Exception {
        byte[] in = input.getBytes("UTF-8");
        byte[] out = input.getBytes();
        Deflater compress = new Deflater();
        compress.setInput(in);
        compress.finish();
        int length = compress.deflate(out);

        return out;
    }

    public String decompress(byte[] inbyte) throws Exception {
        Inflater decompreser = new Inflater();
        decompreser.setInput(inbyte, 0, inbyte.length);
        byte[] res = new byte[inbyte.length];
        int reslen = decompreser.inflate(res);
        decompreser.end();

        return new String(res, 0, reslen, "UTF-8");
    }

}
