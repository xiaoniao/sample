package com.example.mybatisTest.vfs;

import java.io.IOException;
import java.util.List;
import org.apache.ibatis.io.DefaultVFS;

/**
 * Created by liuzz on 2018/04/08
 */
public class VFSLearn {

    public static void main(String[] args) throws IOException {
        DefaultVFS defaultVFS = new DefaultVFS();
        List<String> children = defaultVFS.list("com/example");
        for (String child : children) {
            System.out.println(child);
        }
    }
}
