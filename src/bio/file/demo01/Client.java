package bio.file.demo01;

import java.io.*;
import java.net.Socket;

/**
 * 需求：向客户端发送一个文件
 */
public class Client {

    public static void main(String[] args) {
        try (var socket = new Socket("127.0.0.1", 9999)) {
            File file = new File("src/io/bio/socket/demo05/client/test.txt");
            byte[] bytes = new byte[1024];
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(suffix(file));
            BufferedInputStream bi = new BufferedInputStream(new FileInputStream(file));
            int len;
            while ((len = bi.read(bytes)) != -1) {
                dos.write(bytes, 0, len);
                dos.flush();
            }
            bi.close();
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String suffix(File file) {
        if (file == null) return "";
        String filename = file.getName();
        int idx = filename.lastIndexOf(".");
        if (idx == -1) {
            return "";
        }
        return filename.substring(idx);
    }
}
