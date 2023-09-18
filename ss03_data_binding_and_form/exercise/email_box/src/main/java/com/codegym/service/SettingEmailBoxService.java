package com.codegym.service;

import com.codegym.model.SettingEmailBox;
import com.codegym.reporistory.ISettingEmailBoxRepository;
import com.codegym.reporistory.SettingEmailBoxRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SettingEmailBoxService implements ISettingEmailBoxService{
    private ISettingEmailBoxRepository iSettingEmailBoxRepository = new SettingEmailBoxRepository();
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
    public void save(SettingEmailBox settingEmailBox) {
        iSettingEmailBoxRepository.save(settingEmailBox);
    }
}
