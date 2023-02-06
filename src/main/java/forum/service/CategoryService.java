package forum.service;

import forum.dao.CategoryDao;
import forum.pojo.Category;


import java.util.ArrayList;

public class CategoryService {
    private CategoryDao dao = new CategoryDao();
    //Category分類HCs/LCs
    public ArrayList<ArrayList<Category>> getAll(){
        ArrayList<Category> LCs = new ArrayList<>();
        ArrayList<Category> HCs = new ArrayList<>();
        ArrayList<Category> categories = dao.selectAll();
        for (Category c : categories) {
            if (c.getLevel() == 0) {
                LCs.add(c);
            } else {
                HCs.add(c);
            }
        }
        ArrayList<ArrayList<Category>> Cs = new ArrayList<>();
        Cs.add(LCs);
        Cs.add(HCs);
        return Cs;
    }
}


