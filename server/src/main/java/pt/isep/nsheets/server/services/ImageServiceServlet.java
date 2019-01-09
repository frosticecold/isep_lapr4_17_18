package pt.isep.nsheets.server.services;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageServiceServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //your image servlet code here
        resp.setContentType("image/jpeg");

        // Set content size
        String dir = new File(".").getAbsolutePath();
        String param = req.getParameter("name");
        dir = dir.substring(0, dir.lastIndexOf(".")) + "/server/uploads/imgs/" + param;

        File file = new File(dir);
        resp.setContentLength((int)file.length());

        // Open the file and output streams
        FileInputStream in = new FileInputStream(file);
        OutputStream out = resp.getOutputStream();

        // Copy the contents of the file to the output stream
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        in.close();
        out.close();
    }
}