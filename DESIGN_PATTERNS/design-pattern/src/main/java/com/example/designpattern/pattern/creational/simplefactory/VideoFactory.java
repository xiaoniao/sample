package com.example.designpattern.pattern.creational.simplefactory;

/**
 * Created by geely
 */
public class VideoFactory {

    public Video getVideo(Class c){
        Video video = null;
        try {
            video = (Video) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return video;
    }

    public Video getVideo(String type){
        if("java".equalsIgnoreCase(type)){
            return new JavaVideo();
        }else if("python".equalsIgnoreCase(type)){
            return new PythonVideo();
        }
        return null;
    }

}
