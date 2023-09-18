package com.codegym.repository;

import com.codegym.model.SettingEmailBox;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class SettingEmailBoxRepository implements ISettingEmailBoxRepository {
   private static List<SettingEmailBox> settingEmailBoxList = new ArrayList<>();
    static {
        settingEmailBoxList.add(new SettingEmailBox(1,"Chinese",5,true,"Chinh Dat"));
        settingEmailBoxList.add(new SettingEmailBox(2,"Vietnamese",10,false,"Chinh Dat"));
        settingEmailBoxList.add(new SettingEmailBox(3,"Japanese",15,true,"Chinh Dat"));
        settingEmailBoxList.add(new SettingEmailBox(4,"English",25,false,"Chinh Dat"));
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
    public void update(SettingEmailBox settingEmailBox) {
        System.out.println("check update");
        for (int i = 0; i < settingEmailBoxList.size(); i++) {
            if (settingEmailBoxList.get(i).getId() == settingEmailBox.getId()){
                settingEmailBoxList.set(i,settingEmailBox);
                break;
            }
        }

    }

    @Override
    public SettingEmailBox getSettingById(int id) {
        for (SettingEmailBox settingEmailBox:settingEmailBoxList) {
            if (settingEmailBox.getId() == id){
                return settingEmailBox;
            }
        }
        return null;
    }
}
