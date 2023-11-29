/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author DELL
 */
public class XImage {

    public static Image getAppIcon() {
        URL url = XImage.class.getResource("/icon/fpt.png");
        return new ImageIcon(url).getImage();
    }

 
    public static boolean save(File src) {
        File dst = new File("src\\Icon", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();// tạo thư mục
        }

        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return false;
        }
    }
        public static File saveExel(File src){
        File dst = new File("storeFiles", src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return dst;
        } 
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
     public static ImageIcon read(String fileName, int width, int height) {
        File path = new File("src\\Icon", fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
    
}
