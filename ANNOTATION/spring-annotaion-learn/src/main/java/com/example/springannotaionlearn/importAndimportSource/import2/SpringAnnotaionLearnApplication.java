package com.example.springannotaionlearn.importAndimportSource.import2;

import com.example.springannotaionlearn.importAndimportSource.bean.CDPlayer;
import com.example.springannotaionlearn.importAndimportSource.bean.SgtPeppers;
import com.example.springannotaionlearn.importAndimportSource.bean.XmlBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringAnnotaionLearnApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.example.springannotaionlearn");

        CDConfig cdConfig = applicationContext.getBean(CDConfig.class);

        SgtPeppers sgtPeppers = applicationContext.getBean(SgtPeppers.class);
        sgtPeppers.play();

        CDPlayerConfig cdPlayerConfig = applicationContext.getBean(CDPlayerConfig.class);

        CDPlayer cdPlayer = applicationContext.getBean(CDPlayer.class);
        cdPlayer.play();


        XmlBean xmlBean = applicationContext.getBean(XmlBean.class);
        xmlBean.print();
    }
}
