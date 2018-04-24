package com.example.jetty_jersey.ws.requests;

import java.io.*;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapRequest {

    private String name = "";
    private Integer privacy = 0;
    private String image = ",";
    private String imagePath = null;

    public MapRequest() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Integer privacy) {
        this.privacy = privacy;
    }

    public String getImage() {
        return image;
    }

    public String getPathFile() {
        return imagePath;
    }

    public void setPathFile(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void saveImage(String picturename) throws IOException {
        if(!image.isEmpty()){
            imagePath = saveImage(image, "dist/img/map",picturename);
        }
    }

    public static String saveImage(String image, String mainPath, String picturename){
        String imageType = "image/jpg";

        String[] base64Image = image.split(",");

        if(base64Image.length > 1){
            Pattern regex = Pattern.compile("\\Qdata:\\E(.*?)\\Q;base64\\E");
            Matcher matcher = regex.matcher(base64Image[0]);
            if(matcher.find()){
                imageType = matcher.group(1);
            }

            String extension = ".jpg";
            if(imageType.equals("image/png")){
                extension = ".png";
            }else if(imageType.equals("image/gif")){
                extension = ".gif";
            }

            String projectPath = "jetty-jersey-master/src/main/webapp/";
            String imageName = "/image" + picturename + extension; // generate image name
            String imagePath = mainPath + imageName;

            try (FileOutputStream imageOutFile = new FileOutputStream(projectPath + imagePath)) {
                byte[] imageByteArray = Base64.getDecoder().decode(base64Image[1]);
                imageOutFile.write(imageByteArray);
                System.out.println("Image created with success");
                return imagePath;
            } catch (FileNotFoundException e) {
                System.out.println("Image missing");
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "name: " + name +
                "\nprivacy: " + privacy +
                "\nimage: " + image +
                "\nfile path: " + imagePath ;
    }
}
