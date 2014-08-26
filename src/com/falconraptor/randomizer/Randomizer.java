package com.falconraptor.randomizer;

import com.falconraptor.utilities.*;
import com.falconraptor.utilities.files.Read;
import com.falconraptor.utilities.files.Write;
import com.falconraptor.utilities.logger.Logger;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Randomizer {
    private static final String log="[com.falconraptor.randomizer.Randomizer.";
    public static void main(String[] args){
        try{
            Logger.level=Integer.parseInt(args[0]);
        }catch (Exception e){
            Logger.logERROR(log+"main] "+e);
        }
        Shutdown shutdown=new Shutdown();
        shutdown.packagename= Randomizer.class.getSimpleName();
        shutdown.attachShutDownHook();
    }
    public Randomizer() {
        String filename=JOptionPane.showInputDialog(null,"Please enter the file name of the file you would like to randomize");
        if (filename.equals("")) {
            JOptionPane.showMessageDialog(null,"Filename is blank","Randomizer",JOptionPane.ERROR_MESSAGE);
            return;
        }
        Read.Read(filename);
        String read=Read.read();
        Logger.logINFO("Reading from file");
        String[] objectstorandomize=read.split("↔");
        ArrayList<String> objects=new ArrayList<>(0);
        Collections.addAll(objects, objectstorandomize);
        ArrayList<String> randomized=new ArrayList<>(0);
        Random r=new Random();
        Logger.logINFO("Randomizing Items");
        for (int i=0;i<objects.size();i++){
            int index=r.nextInt(objects.size());
            randomized.add(objects.get(index));
            objects.remove(index);
            i--;
        }
        Logger.logINFO("Writing Randomized Items to file");
        Write.Write(filename.substring(0,filename.indexOf("."))+"randomized.txt",false);
        Write.write(randomized);
        Write.close();
        JOptionPane.showMessageDialog(null,"Done Randomizing","Randomizer",JOptionPane.INFORMATION_MESSAGE);
    }
}
