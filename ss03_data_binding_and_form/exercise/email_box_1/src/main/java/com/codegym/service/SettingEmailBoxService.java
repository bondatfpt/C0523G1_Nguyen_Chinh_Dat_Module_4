package com.codegym.service;

import com.codegym.model.SettingEmailBox;
import com.codegym.repository.ISettingEmailBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingEmailBoxService implements ISettingEmailBoxService{
    @Autowired
    private ISettingEmailBoxRepository iSettingEmailBoxRepository;

    @Override
    public List<SettingEmailBox> getAll() {
        List<SettingEmailBox> settingEmailBoxList = iSettingEmailBoxRepository.getAll();
        return settingEmailBoxList;
    }

    @Override
    public List<String> getLanguageList() {
        List<String> stringList = iSettingEmailBoxRepository.getLanguageList();
        return stringList;
    }

    @Override
    public int[] getListPages() {
        int [] ints = iSettingEmailBoxRepository.getListPages();
        return ints;
    }

    @Override
    public void update(SettingEmailBox settingEmailBox) {
        iSettingEmailBoxRepository.update(settingEmailBox);
    }

    @Override
    public SettingEmailBox getSettingById(int id) {
        SettingEmailBox settingEmailBox = iSettingEmailBoxRepository.getSettingById(id);
        return settingEmailBox;
    }
}
