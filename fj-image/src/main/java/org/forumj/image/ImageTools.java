package org.forumj.image;

import org.apache.commons.fileupload.FileItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Andrew on 03/03/2017.
 */
public class ImageTools {

    public static ImageSize fitImageToSize(ImageSize originalSize, int maxWidth, int maxHeight){
        int destHeight = 0;
        int destWidth = 0;
        if(originalSize.getHeight() > maxHeight || originalSize.getWidth() > maxWidth){
            if (originalSize.getHeight() >= originalSize.getWidth()){
                destHeight = maxHeight;
            }else{
                destWidth = maxWidth;
            }
            if (destWidth == 0){
                double imageRatio = (double)originalSize.getHeight() / (double)destHeight;
                destWidth = (int)((double)originalSize.getWidth() / imageRatio);
                if (imageRatio < 1){
                    destHeight = (int)((double)originalSize.getHeight() / imageRatio);
                }
            }else{
                double imageRatio = (double)originalSize.getWidth() / (double)destWidth;
                destHeight = (int)((double)originalSize.getHeight() / imageRatio);
                if (imageRatio < 1){
                    destWidth = (int)((double)originalSize.getWidth() / imageRatio);
                }
            }
        }
        return new ImageSize(destHeight, destWidth);
    }

    public static ImageSize fitImageToWidth(ImageSize originalSize, int maxWidth){
        int destHeight = 0;
        if(originalSize.getWidth() != maxWidth){
            destHeight = (maxWidth * originalSize.getHeight()) / originalSize.getWidth();
        }
        return new ImageSize(destHeight, maxWidth);
    }

    public static String getFileExtension(String fileName){
        String[] name = fileName.split("\\.");
        String ext = name[name.length - 1];
        if (ext != null){
            ext = ext.toLowerCase();
        }
        return ext;
    }

    public static Image getImage(FileItem file) throws IOException {
        Image image = null;
        try (
                InputStream in = file.getInputStream();
                InputStream imageStream = new BufferedInputStream(in);
        ){
            image = (Image) ImageIO.read(imageStream);
        }
        return image;
    }

    public static Image getImage(String fileName) throws IOException {
        Image image = null;
        try (
                FileInputStream in = new FileInputStream(fileName);
                InputStream imageStream = new BufferedInputStream(in);
        ){
            image = (Image) ImageIO.read(imageStream);
        }
        return image;
    }

    public static ImageSize getImageSize(Image image){
        return new ImageSize(image.getHeight(null), image.getWidth(null));
    }

    public static String makePath(Long id, String root){
        StringBuilder builder = new StringBuilder(root).append('/');
        long[] parts = new long[10];
        for (int partsIndex = 0; partsIndex < parts.length; partsIndex++) {
            parts[partsIndex] = id % 100;
            id /= 100;
        }
        for (int partsIndex = parts.length - 1; partsIndex > 0 ; partsIndex--) {
            Long part = parts[partsIndex];
            if (part < 10){
                builder.append('0');
            }
            builder.append(part);
            builder.append('/');
        }
        return builder.toString();
    }

    public static void checkAndCreateImagePath(String imagePath){
        File destPath = new File(imagePath);
        if (!destPath.exists()){
            destPath.mkdirs();
        }
    }

    public static String makeImageName(Long id, String imagePath, String imageExtension){
        StringBuilder result = new StringBuilder(imagePath);
        String imageName = makeImageName(id);
        result.append(imageName).append(".").append(imageExtension);
        return  result.toString();
    }

    public static String makeImageName(Long id){
        long name = id % 100;
        StringBuilder result = new StringBuilder();
        result.append(name < 10 ? "0" : "").append(name);
        return  result.toString();
    }

    public static void writeImageToFile(ImageSize destSize, int imgType, Image image, String fileName, String imageFormat) throws IOException{
        File destFile = new File(fileName);
        BufferedImage destImage = null;
        try (OutputStream out = new FileOutputStream(destFile);) {
            destImage = renderImage(destSize, imgType, image);
            ImageIO.write(destImage, imageFormat, out);
        } finally {
            if (destImage != null) destImage.flush();
        }
    }

    private static BufferedImage renderImage(ImageSize destSize, int imgType, Image image){
        BufferedImage thumbsImage = new BufferedImage(destSize.getWidth(), destSize.getHeight(), imgType);
        Graphics2D graphics2D = thumbsImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, destSize.getWidth(), destSize.getHeight(), null);
        return thumbsImage;
    }

}
