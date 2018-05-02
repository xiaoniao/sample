package com.example.springlearn.basic.scope.prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

@Service
public class SingletonService {

    @Autowired
    private ProtoTypeService protoTypeService;

    private ProtoTypeServiceWithLooKup protoTypeServiceWithLooKup;

    @Autowired
    private ProtoTypeService2 protoTypeService2;

    public void displayInstance() {
        System.out.println(protoTypeService);

        System.out.println(protoTypeServiceWithLookup());

        System.out.println(protoTypeService2);
    }

    @Lookup
    public ProtoTypeServiceWithLooKup protoTypeServiceWithLookup() {
        // spring will override this method
        return null;
    }

}
