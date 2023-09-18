package com.codegym.repository;

import com.codegym.model.SettingEmailBox;

import java.util.List;

public interface ISettingEmailBoxRepository {
    List<SettingEmailBox> getAll();
    List<String> getLanguageList();
    int[] getListPages();
    void update (SettingEmailBox settingEmailBox);
    SettingEmailBox getSettingById (int id);
}
