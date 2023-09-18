package com.codegym.reporistory;

import com.codegym.model.SettingEmailBox;

import java.util.List;

public interface ISettingEmailBoxRepository {
    List<SettingEmailBox> getAll();

    List<String> getLanguageList();
    int[] getListPages();

    void save (SettingEmailBox settingEmailBox);
}
