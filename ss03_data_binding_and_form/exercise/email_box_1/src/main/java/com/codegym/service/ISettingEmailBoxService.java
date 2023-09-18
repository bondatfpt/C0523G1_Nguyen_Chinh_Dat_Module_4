package com.codegym.service;

import com.codegym.model.SettingEmailBox;

import java.util.List;

public interface ISettingEmailBoxService {
    List<SettingEmailBox> getAll();

    List<String> getLanguageList();

    int[] getListPages();

    void update (SettingEmailBox settingEmailBox);
    SettingEmailBox getSettingById (int id);


}
