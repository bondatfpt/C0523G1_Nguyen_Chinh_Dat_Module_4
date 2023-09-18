package com.codegym.reporistory;

import com.codegym.model.SettingEmailBox;

import java.util.ArrayList;
import java.util.List;

public class SettingEmailBoxRepository implements ISettingEmailBoxRepository {
   private static List<SettingEmailBox> settingEmailBoxList = new ArrayList<>();
    static {
        settingEmailBoxList.add(new SettingEmailBox("Chinese",5,true,"Chinh Dat"));
    }
    @Override
    public List<SettingEmailBox> getAll() {
        return settingEmailBoxList;
    }
    public List<String> getLanguageList (){
        List<String> stringList = new ArrayList<>();
        stringList.add("Vietnamese");
        stringList.add("Chinese");
        stringList.add("Japanese");
        stringList.add("English");
        return stringList;
    };
    public int[] getListPages (){
        int[] ints = {5,10,15,25,50,100};
        return ints;
    }

    @Override
    public void save(SettingEmailBox settingEmailBox) {
        settingEmailBoxList.add(settingEmailBox);
    }
}
