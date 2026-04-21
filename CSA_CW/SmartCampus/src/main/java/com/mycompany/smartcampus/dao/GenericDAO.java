/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.dao;

import java.util.List;
import com.mycompany.smartcampus.model.BaseModel;

/**
 *
 * @author DELL
 */
public class GenericDAO<T extends BaseModel> {
    
    private final List<T> items;

    public GenericDAO(List<T> items) {
        this.items = items;
    }

    public List<T> getAll() {
        return items;
    }

    public T getById(String id) {
        for (T item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public void add(T item) {
        int max = 0;
        for (T i : items) {
            String id = i.getId();
            
            try{
                int num = Integer.parseInt(id.split("-")[1]);
                if (num > max){
                    max = num;
                }
            } catch (Exception e){
            
            }
        }
        
        //auto-increment
        //String newId = "ID-" + (max + 1);
        //item.setId(newId);
        
        items.add(item);
    }

    public void update(T updatedItem) {
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            if (item.getId().equals(updatedItem.getId())) {
                items.set(i, updatedItem);
                return;
            }
        }
    }

    public void delete(String id) {
        items.removeIf(item -> item.getId().equals(id));
    }
    
}