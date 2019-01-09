/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.services;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ServletFileUpload.isMultipartContent(request)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Not a multipart request");
            return;
        }

        ServletFileUpload upload = new ServletFileUpload();

        try {
            FileItemIterator iter = upload.getItemIterator(request);

            if (iter.hasNext()) {
                FileItemStream fileItem = iter.next();

                ServletOutputStream out = response.getOutputStream();
                response.setBufferSize(32768);
                InputStream in = fileItem.openStream();

//                switch (fileItem.getContentType()) {
//                    case "image/png": case "image/jpeg":

                        // Gets the current dir path
                        String dir = new File(".").getAbsolutePath();
                        if (fileItem.getName().contains(".csv")) {
                            dir = dir.substring(0, dir.lastIndexOf(".")) + "/server/export/";  // goes to the server dir where it stores the images
                        } else {
                            dir = dir.substring(0, dir.lastIndexOf(".")) + "/server/uploads/imgs/";  // goes to the server dir where it stores the images
                        }
                        File file = new File(dir + fileItem.getName());
                        OutputStream outputStream = new FileOutputStream(file);

                        int length = 0;
                        byte[] bytes = new byte[1024];

                        while ((length = in.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, length);
                        }

                        response.setContentType("text/html");
                        response.setContentLength((length > 0 && length <= Integer.MAX_VALUE) ? (int) length : 0);

                        outputStream.close();
//                        break;
                        
                        
//                }

                in.close();
                out.flush();
                out.close();
            }
        } catch (Exception caught) {
            throw new RuntimeException(caught);
        }
    }
}
