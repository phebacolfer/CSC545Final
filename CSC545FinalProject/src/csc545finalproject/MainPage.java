/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package csc545finalproject;

import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JComboBox;
import oracle.jdbc.OracleResultSet;
/**
 *
 * @author pheba

*/


public class MainPage extends javax.swing.JFrame {
    
    private DatabaseConnection conn = null;
    
    private ArrayList<DatabaseConnection.Recipe> recipes = null;
    
    private HashMap<String, DatabaseConnection.Ingredient> all_ingredients = null;

    private HashMap<String, JComboBox> food_schedule = null;
    


    /**
     * Creates new form MainPage
     */
    public MainPage() {
        
        conn = new DatabaseConnection();

        
        this.all_ingredients = new HashMap<>();
        
        initComponents();
        
        recipes = conn.getRecipes();
        
        HashSet<String> recipe_types = new HashSet<>();
        
        recipelist.removeAll();
        for (var recipe: recipes) {
            recipe_types.add(recipe.category);
        }
        
        updateFridgeContentView();
        
        
        this.food_schedule = new HashMap<>();
        
        initFoodSchedule();
        updateFoodSchedule();
        
        ingredient_search_select.addItem(null);
        
        
        
        for (var ingredient: all_ingredients.values()) {
            ingredient_search_select.addItem(ingredient.food_name);
            
        }
        
        categorysearch_select.addItem(null);
        for (var type: recipe_types)
            categorysearch_select.addItem(type);
        
        updateFilteredRecipes();
        
    }
    
    private void updateFilteredRecipes() {
        
        String category = null;
        if (categorysearch_select.getSelectedItem() != null)
            category = categorysearch_select.getSelectedItem().toString();
        
        String ingredient = null;
        if (ingredient_search_select.getSelectedItem() != null)
            ingredient = ingredient_search_select.getSelectedItem().toString();
        
        recipelist.removeAll();
        for (var recipe: conn.getFilteredRecipes(category, ingredient)) {
            recipelist.add(recipe);
        }
    }

    private void initFoodSchedule() {
        food_schedule.put("Sunday Breakfast", sun_breakfast);
        food_schedule.put("Sunday Dinner", sun_dinner);
        food_schedule.put("Sunday Lunch", sun_lunch);
        food_schedule.put("Monday Breakfast", mon_breakfast);
        food_schedule.put("Monday Dinner", mon_dinner);
        food_schedule.put("Monday Lunch", mon_lunch);
        food_schedule.put("Tuesday Breakfast", tue_breakfast);
        food_schedule.put("Tuesday Dinner", tue_dinner);
        food_schedule.put("Tuesday Lunch", tue_lunch);
        food_schedule.put("Wednesday Breakfast", wed_breakfast);
        food_schedule.put("Wednesday Dinner", wed_dinner);
        food_schedule.put("Wednesday Lunch", wed_lunch);
        food_schedule.put("Thursday Breakfast", thu_breakfast);
        food_schedule.put("Thursday Dinner", thu_dinner);
        food_schedule.put("Thursday Lunch", thu_lunch);
        food_schedule.put("Friday Breakfast", fri_breakfast);
        food_schedule.put("Friday Dinner", fri_dinner);
        food_schedule.put("Friday Lunch", fri_lunch);
        food_schedule.put("Saturday Breakfast", sat_breakfast);
        food_schedule.put("Saturday Dinner", sat_dinner);
        food_schedule.put("Saturday Lunch", sat_lunch);
        
        for (String mealday: food_schedule.keySet()) {
            JComboBox meal = food_schedule.get(mealday);
            
            meal.removeAllItems();
            meal.addItem(null);
            for (var r: recipes)
                meal.addItem(r.name);
            
            DatabaseConnection.Meal db_meal = conn.getMeal(mealday);
            if (db_meal != null){
                meal.setSelectedItem(db_meal.recipe);
            }
        }
        
        

    }
    
    private void updateFoodSchedule() {
        // collect information about food schedule from dropdown
        // update database
        // print out requirements
        
        
        // and for robustness, we'll repopulate the schedule items
        for (String mealday: food_schedule.keySet()) {
            JComboBox schedule_selector = food_schedule.get(mealday);
            
            // if we're here, a selection must have been made and the user
            // sees what they want to see.  Update the DB
            if (schedule_selector.getSelectedItem() != null)
                // go ahead and sync all of the changes
                conn.updateMeal(mealday, schedule_selector.getSelectedItem().toString());
            else
                conn.deleteMeal(mealday);
        }
        // go ahead and update the shopping list on the other page
        String needed = "";
        for (var ingredient: conn.getShoppingList()) {
            needed += ingredient + '\n';
        }
        
        needed_box.setText(needed);
    }
    
    private void updateFridgeContentView() {
        in_fridge.removeAll();
        add_item_to_stock.removeAllItems();
        
        all_ingredients.clear();
        ingredient_info_selection.clear();
        ingredient_info_text.setText("");
        conn.getIngredients().forEach( (x) -> {all_ingredients.put(x.food_name, x); });
        
        
        for (var ingredient: all_ingredients.values()) {
            ingredient_info_selection.add(ingredient.food_name);
            if (ingredient.in_stock)
                in_fridge.add(ingredient.food_name);
            else
                add_item_to_stock.addItem(ingredient.food_name);
        }
        
        // go ahead and update the shopping list on the other page
        String needed = "";
        for (var ingredient: conn.getShoppingList()) {
            needed += ingredient + '\n';
        }
        
        needed_box.setText(needed);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        nav_tabs = new javax.swing.JTabbedPane();
        recipe_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        recipe_content_box = new javax.swing.JTextArea();
        recipelist = new java.awt.List();
        activeIngredientList = new java.awt.List();
        jLabel15 = new javax.swing.JLabel();
        ingredient_search_select = new javax.swing.JComboBox<>();
        categorysearch_select = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        meal_panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        mon_breakfast = new javax.swing.JComboBox<>();
        tue_breakfast = new javax.swing.JComboBox<>();
        wed_breakfast = new javax.swing.JComboBox<>();
        thu_breakfast = new javax.swing.JComboBox<>();
        fri_breakfast = new javax.swing.JComboBox<>();
        sat_breakfast = new javax.swing.JComboBox<>();
        sun_breakfast = new javax.swing.JComboBox<>();
        sun_lunch = new javax.swing.JComboBox<>();
        sat_lunch = new javax.swing.JComboBox<>();
        fri_lunch = new javax.swing.JComboBox<>();
        thu_lunch = new javax.swing.JComboBox<>();
        wed_lunch = new javax.swing.JComboBox<>();
        tue_lunch = new javax.swing.JComboBox<>();
        mon_lunch = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        sun_dinner = new javax.swing.JComboBox<>();
        sat_dinner = new javax.swing.JComboBox<>();
        fri_dinner = new javax.swing.JComboBox<>();
        thu_dinner = new javax.swing.JComboBox<>();
        wed_dinner = new javax.swing.JComboBox<>();
        tue_dinner = new javax.swing.JComboBox<>();
        mon_dinner = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        needed_box = new javax.swing.JTextArea();
        update_meal_plan_button = new javax.swing.JButton();
        fridge_panel = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        add_to_stock_button = new javax.swing.JButton();
        in_fridge = new java.awt.List();
        add_item_to_stock = new javax.swing.JComboBox<>();
        remove_from_stock_button = new javax.swing.JButton();
        ingredient_info_panel = new javax.swing.JPanel();
        ingredient_info_selection = new java.awt.List();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ingredient_info_text = new javax.swing.JTextArea();

        jLabel9.setText("Saturday");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Available Recipes");

        jLabel2.setText("Recipe Contents");

        recipe_content_box.setEditable(false);
        recipe_content_box.setColumns(20);
        recipe_content_box.setRows(5);
        recipe_content_box.setWrapStyleWord(true);
        jScrollPane2.setViewportView(recipe_content_box);

        recipelist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recipelistActionPerformed(evt);
            }
        });

        activeIngredientList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeIngredientListActionPerformed(evt);
            }
        });

        jLabel15.setText("Ingredients");

        ingredient_search_select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredient_search_selectActionPerformed(evt);
            }
        });

        categorysearch_select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorysearch_selectActionPerformed(evt);
            }
        });

        jLabel16.setText("Categories");

        javax.swing.GroupLayout recipe_panelLayout = new javax.swing.GroupLayout(recipe_panel);
        recipe_panel.setLayout(recipe_panelLayout);
        recipe_panelLayout.setHorizontalGroup(
            recipe_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recipe_panelLayout.createSequentialGroup()
                .addGroup(recipe_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(recipe_panelLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(recipe_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(recipe_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categorysearch_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ingredient_search_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(recipe_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(recipe_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(recipelist, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addGroup(recipe_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addGroup(recipe_panelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(activeIngredientList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        recipe_panelLayout.setVerticalGroup(
            recipe_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recipe_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(recipe_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(recipe_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(recipe_panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(activeIngredientList, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addGroup(recipe_panelLayout.createSequentialGroup()
                        .addComponent(recipelist, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(recipe_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ingredient_search_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(recipe_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(categorysearch_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        nav_tabs.addTab("Recipes", recipe_panel);

        jLabel3.setText("Monday");

        jLabel4.setText("Tuesday");

        jLabel5.setText("Wednesday");

        jLabel6.setText("Thursday");

        jLabel7.setText("Friday");

        jLabel8.setText("Saturday");

        jLabel10.setText("Sunday");

        jLabel11.setText("Breakfast");

        jLabel12.setText("Lunch");

        jLabel13.setText("Dinner");

        jLabel14.setText("Ingredients Needed");

        needed_box.setColumns(20);
        needed_box.setRows(5);
        jScrollPane3.setViewportView(needed_box);

        update_meal_plan_button.setText("Update");
        update_meal_plan_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_meal_plan_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout meal_panelLayout = new javax.swing.GroupLayout(meal_panel);
        meal_panel.setLayout(meal_panelLayout);
        meal_panelLayout.setHorizontalGroup(
            meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meal_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(meal_panelLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(update_meal_plan_button))
                    .addGroup(meal_panelLayout.createSequentialGroup()
                        .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(meal_panelLayout.createSequentialGroup()
                                .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(meal_panelLayout.createSequentialGroup()
                                        .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(mon_breakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(tue_breakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(wed_breakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(thu_breakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(fri_breakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(sat_breakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(sun_breakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(meal_panelLayout.createSequentialGroup()
                                .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(meal_panelLayout.createSequentialGroup()
                                        .addComponent(mon_lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tue_lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(wed_lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(thu_lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fri_lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sat_lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sun_lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(meal_panelLayout.createSequentialGroup()
                                .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addGroup(meal_panelLayout.createSequentialGroup()
                                        .addComponent(mon_dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tue_dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(wed_dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(thu_dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fri_dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sat_dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sun_dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        meal_panelLayout.setVerticalGroup(
            meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meal_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mon_breakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tue_breakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wed_breakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thu_breakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fri_breakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sat_breakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sun_breakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mon_lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tue_lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wed_lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thu_lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fri_lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sat_lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sun_lunch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mon_dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tue_dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wed_dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thu_dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fri_dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sat_dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sun_dinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(meal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(update_meal_plan_button))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
        );

        nav_tabs.addTab("Meal Plan", meal_panel);

        jLabel18.setText("Current Ingredients");

        jLabel19.setText("Add Ingredient");

        add_to_stock_button.setText("Add");
        add_to_stock_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_to_stock_buttonActionPerformed(evt);
            }
        });

        add_item_to_stock.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        remove_from_stock_button.setText("Remove");
        remove_from_stock_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_from_stock_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fridge_panelLayout = new javax.swing.GroupLayout(fridge_panel);
        fridge_panel.setLayout(fridge_panelLayout);
        fridge_panelLayout.setHorizontalGroup(
            fridge_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fridge_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fridge_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(in_fridge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(fridge_panelLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(410, 410, 410))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, fridge_panelLayout.createSequentialGroup()
                        .addComponent(add_item_to_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(add_to_stock_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(remove_from_stock_button))
                    .addGroup(fridge_panelLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(445, 445, 445)))
                .addContainerGap(219, Short.MAX_VALUE))
        );
        fridge_panelLayout.setVerticalGroup(
            fridge_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fridge_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(in_fridge, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(fridge_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_to_stock_button)
                    .addComponent(add_item_to_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(remove_from_stock_button))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        nav_tabs.addTab("Fridge Contents", fridge_panel);

        ingredient_info_selection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredient_info_selectionActionPerformed(evt);
            }
        });

        jLabel20.setText("Ingredient");

        ingredient_info_text.setEditable(false);
        ingredient_info_text.setColumns(20);
        ingredient_info_text.setRows(5);
        ingredient_info_text.setWrapStyleWord(true);
        jScrollPane4.setViewportView(ingredient_info_text);

        javax.swing.GroupLayout ingredient_info_panelLayout = new javax.swing.GroupLayout(ingredient_info_panel);
        ingredient_info_panel.setLayout(ingredient_info_panelLayout);
        ingredient_info_panelLayout.setHorizontalGroup(
            ingredient_info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ingredient_info_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ingredient_info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(ingredient_info_panelLayout.createSequentialGroup()
                        .addComponent(ingredient_info_selection, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        ingredient_info_panelLayout.setVerticalGroup(
            ingredient_info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ingredient_info_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ingredient_info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(ingredient_info_selection, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
                .addContainerGap())
        );

        nav_tabs.addTab("Ingredient Info", ingredient_info_panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(nav_tabs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nav_tabs)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingredient_info_selectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredient_info_selectionActionPerformed
        // TODO add your handling code here:
        var ingredient = all_ingredients.get(ingredient_info_selection.getSelectedItem());

        ingredient_info_text.setText(ingredient.getDescription());

    }//GEN-LAST:event_ingredient_info_selectionActionPerformed

    private void remove_from_stock_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_from_stock_buttonActionPerformed
        // TODO add your handling code here:
        DatabaseConnection.Ingredient to_update = all_ingredients.get(in_fridge.getSelectedItem());
        conn.updateStock(to_update, false);
        updateFridgeContentView();
    }//GEN-LAST:event_remove_from_stock_buttonActionPerformed

    private void add_to_stock_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_to_stock_buttonActionPerformed
        // TODO add your handling code here:
        // add to fridge
        DatabaseConnection.Ingredient to_update = all_ingredients.get(add_item_to_stock.getSelectedItem().toString());

        conn.updateStock(to_update, true);
        updateFridgeContentView();

    }//GEN-LAST:event_add_to_stock_buttonActionPerformed

    private void update_meal_plan_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_meal_plan_buttonActionPerformed
        // TODO add your handling code here:
        updateFoodSchedule();

    }//GEN-LAST:event_update_meal_plan_buttonActionPerformed

    private void categorysearch_selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorysearch_selectActionPerformed
        // TODO add your handling code here:
        updateFilteredRecipes();
    }//GEN-LAST:event_categorysearch_selectActionPerformed

    private void ingredient_search_selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredient_search_selectActionPerformed
        // TODO add your handling code here:
        // filter recipes
        updateFilteredRecipes();
    }//GEN-LAST:event_ingredient_search_selectActionPerformed

    private void activeIngredientListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeIngredientListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_activeIngredientListActionPerformed

    private void recipelistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recipelistActionPerformed
        // TODO add your handling code here:
        var recipe = recipes.get(recipelist.getSelectedIndex());
        recipe_content_box.setText(recipe.instructions);

        activeIngredientList.removeAll();
        // Set active ingredient list
        for (var ingredient: conn.getIngredients(recipe))
        activeIngredientList.add(ingredient.qty + " " + ingredient.units + " " + ingredient.food_name);

    }//GEN-LAST:event_recipelistActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.List activeIngredientList;
    private javax.swing.JComboBox<String> add_item_to_stock;
    private javax.swing.JButton add_to_stock_button;
    private javax.swing.JComboBox<String> categorysearch_select;
    private javax.swing.JComboBox<String> fri_breakfast;
    private javax.swing.JComboBox<String> fri_dinner;
    private javax.swing.JComboBox<String> fri_lunch;
    private javax.swing.JPanel fridge_panel;
    private java.awt.List in_fridge;
    private javax.swing.JPanel ingredient_info_panel;
    private java.awt.List ingredient_info_selection;
    private javax.swing.JTextArea ingredient_info_text;
    private javax.swing.JComboBox<String> ingredient_search_select;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel meal_panel;
    private javax.swing.JComboBox<String> mon_breakfast;
    private javax.swing.JComboBox<String> mon_dinner;
    private javax.swing.JComboBox<String> mon_lunch;
    private javax.swing.JTabbedPane nav_tabs;
    private javax.swing.JTextArea needed_box;
    private javax.swing.JTextArea recipe_content_box;
    private javax.swing.JPanel recipe_panel;
    private java.awt.List recipelist;
    private javax.swing.JButton remove_from_stock_button;
    private javax.swing.JComboBox<String> sat_breakfast;
    private javax.swing.JComboBox<String> sat_dinner;
    private javax.swing.JComboBox<String> sat_lunch;
    private javax.swing.JComboBox<String> sun_breakfast;
    private javax.swing.JComboBox<String> sun_dinner;
    private javax.swing.JComboBox<String> sun_lunch;
    private javax.swing.JComboBox<String> thu_breakfast;
    private javax.swing.JComboBox<String> thu_dinner;
    private javax.swing.JComboBox<String> thu_lunch;
    private javax.swing.JComboBox<String> tue_breakfast;
    private javax.swing.JComboBox<String> tue_dinner;
    private javax.swing.JComboBox<String> tue_lunch;
    private javax.swing.JButton update_meal_plan_button;
    private javax.swing.JComboBox<String> wed_breakfast;
    private javax.swing.JComboBox<String> wed_dinner;
    private javax.swing.JComboBox<String> wed_lunch;
    // End of variables declaration//GEN-END:variables
}
