package com.example.madsstoltenborg.miniprojektforfra;

/**
 * Created by simon on 13-03-2018.
 */

public class Storage {

    private static Storage storage;

    private Storage(){};
    
    public static Storage getInstance(){
        if(storage == null){
            storage = new Storage();
        }
        return storage;
    }


}
