package com.example.pengzhang.fridgepay.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@ModelContainer
@Table(database = ProductDatabase.class)
public class Bill extends BaseModel {

    @PrimaryKey
    public int id;
    @Column
    public String name;
    @Column
    public double price;
    @Column
    public int selectedCount;

}
