/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csc545finalproject;

/**
 *
 * @author yangm
 */
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Optional;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import javax.swing.JOptionPane;

public class DatabaseConnection {

    Connection conn;
    Gson gson;

    DatabaseConnection() {
        conn = setupConnection();
        gson = new Gson();
    }

    public abstract class Queryable {

        // For some reason, I can't seem to create a "static abstract" type
        // A bit of internet searching blames this on bad language design
        // I don't disagree, but what do I know
        public static String getQueryString() {
            return "not implemented";
        }

        ;
        
        public Queryable() {
        }
    ;

    }
    
    public class Recipe extends Queryable {

        public String name;
        public String category;
        public String instructions;

        public static String getQueryString() {
            return """
                               select json_object('name' VALUE RECIPENAME, 'category' VALUE RECIPECATEGORY, 'instructions' VALUE RECIPEINSTRUCTIONS) AS "result" from recipes
                               """;
        }

    }

    public class Meal extends Queryable {

        public String recipe;
        public String weekday;
        public String mealtime;

        Meal(String weekday, String mealtime) {
            this.weekday = weekday;
            this.mealtime = mealtime;
        }

        public static String getQueryString() {
            return """
                               select json_object('weekday' VALUE WEEKDAY, 'mealtime' VALUE MEAL, 'recipe' VALUE RECIPENAME) AS "result" from mealPlan
                               """;
        }
    }

    public class Ingredient extends Queryable {

        public String food_name;
        public String food_group;
        public int calories;
        public int sugars;
        public int fat;
        public boolean in_stock;
        public float qty;
        public String units;

        public String getDescription() {
            return "Name: " + food_name + "\n"
                    + "Food Group: " + food_group + "\n"
                    + "Calories: " + calories + "\n"
                    + "Sugars:  " + sugars + "\n"
                    + "Fat: " + fat + "\n"
                    + "In stock: " + in_stock;
        }

        public static String getQueryString() {
            return """
                           select json_object( 'food_name' VALUE foodname,
                           'food_group' VALUE foodgroup,
                           'calories' VALUE foodcalories,
                           'sugars' VALUE foodsugars,
                           'fat' value foodfat,
                           'in_stock' value foodinfridge ) AS "result" from food""";
        }
    }

    public ArrayList<Recipe> getRecipes() {
        var results = new ArrayList<Recipe>();
        try {
            var result = (OracleResultSet) conn.prepareStatement(Recipe.getQueryString()).executeQuery();
            while (result.next()) {
                results.add(gson.fromJson(result.getString("result"), Recipe.class)
                );

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return results;
    }

    public ArrayList<Ingredient> getIngredients() {
        var results = new ArrayList<Ingredient>();
        try {
            var query = (OraclePreparedStatement) this.conn.prepareStatement(Ingredient.getQueryString());
            var result = (OracleResultSet) query.executeQuery();

            while (result.next()) {
                results.add(gson.fromJson(result.getString("result"), Ingredient.class)
                );

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return results;
    }

    public ArrayList<Ingredient> getIngredients(Recipe r) {
        var results = new ArrayList<Ingredient>();
        try {
            var query = (OraclePreparedStatement) this.conn.prepareStatement("""
                                                                     select json_object('food_name' VALUE FOODNAME, 'qty' VALUE AMOUNT, 'units' value UNITS) AS "result" from food_recipes WHERE RECIPENAME = ?
                """);
            query.setString(1, r.name);
            var result = (OracleResultSet) query.executeQuery();

            while (result.next()) {
                results.add(gson.fromJson(result.getString("result"), Ingredient.class)
                );

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return results;
    }

    public void updateStock(Ingredient i, boolean in_stock) {
        try {
            var query = (OraclePreparedStatement) this.conn.prepareStatement("""
                    update food set foodInFridge=? where FOODNAME = ?
                """);
            query.setBoolean(1, in_stock);
            query.setString(2, i.food_name);
            query.executeQuery();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Meal getMeal(String mealstring) {
        String[] parts = mealstring.split(" ");

        try {
            var query = (OraclePreparedStatement) this.conn.prepareStatement(
                    Meal.getQueryString() + " where weekday = ? and meal = ?"
            );
            query.setString(1, parts[0]);
            query.setString(2, parts[1]);
            var result = (OracleResultSet) query.executeQuery();

            if (result.next()) // assume that the database is handling duplicates properly...
            // i sure won't here
            {
                return gson.fromJson(result.getString("result"), Meal.class);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateMeal(String m, String recipe) {
        if (getMeal(m) == null) {
            insertMeal(m, recipe);
            return;
        }
        String[] parts = m.split(" ");
        try {
            var query = (OraclePreparedStatement) this.conn.prepareStatement("""
                    update mealPlan set recipename=? where weekday =? and meal =?
                """);
            query.setString(1, recipe);
            query.setString(2, parts[0]);
            query.setString(3, parts[1]);
            query.executeQuery();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteMeal(String m) {
        String[] parts = m.split(" ");
        try {
            var query = (OraclePreparedStatement) this.conn.prepareStatement("""
                    delete mealplan where weekday = ? and meal = ?
                """);

            query.setString(1, parts[0]);
            query.setString(2, parts[1]);
            query.executeQuery();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void insertMeal(String m, String recipe) {
        String[] parts = m.split(" ");
        try {
            var query = (OraclePreparedStatement) this.conn.prepareStatement("""
                    insert into mealplan (weekday, meal, recipename) values (?, ?, ?)
                """);

            query.setString(1, parts[0]);
            query.setString(2, parts[1]);
            query.setString(3, recipe);
            query.executeQuery();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<String> getShoppingList() {
        var results = new ArrayList<String>();
        try {
            var query = (OraclePreparedStatement) this.conn.prepareStatement("""
                 select * from shopping_list
                """);
            var result = (OracleResultSet) query.executeQuery();

            while (result.next()) {
                results.add(result.getString("FOODNAME"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return results;
    }
    
    public ArrayList<String> getFilteredRecipes(String category, String ingredient) {
        var results = new ArrayList<String>();
        
        String filter = "";
        if (category != null) {
            filter += " category = '" + category + "' ";
        }
        if (ingredient != null) {
            if (category != null)
                filter += "and";
            filter += " food = '" + ingredient + "' ";
        }
        if (!"".equals(filter)) {
            filter = " where " + filter;
        }
        
        try {
            var query = (OraclePreparedStatement) this.conn.prepareStatement("""
                 select DISTINCT(recipe) from recipe_rundown
                """ + filter);
            var result = (OracleResultSet) query.executeQuery();

            while (result.next()) {
                results.add(result.getString("RECIPE")
                );

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return results;
    }

    /*
    public <T extends Queryable> ArrayList<T> getArray() {
        var results = new ArrayList<T>();
        Class<T> type = null;
        try {
                var result = (OracleResultSet) DatabaseConnection.this.conn.prepareStatement(T.getQueryString());
            while (result.next()) {
                results.add(gson.fromJson(result.getString("result"), type)
                );

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return results;
    }*/
    public static Connection setupConnection() {
        /*
         Specify the database you would like to connect with:
         - protocol (e.g., jdbc)
         - vendor (e.g., oracle)
         - driver (e.g., thin)
         - server (e.g., csitoracle.eku.edu)
         - port number (e.g., default 1521)
         - database instance name (e.g., cscpdb)
         */

        String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:";  // URL for the database

        /*
         Specify the user account you will use to connect with the database:
         - user name (e.g., myName)
         - password (e.g., myPassword)
         */
        String username = "system";
        String password = "pass";

        try {
            // Load jdbc driver.
            Class.forName(jdbcDriver);

            // Connect to the Oracle database
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Throwable whatever) {
            }
        }
    }

    static void close(OraclePreparedStatement st) {
        if (st != null) {
            try {
                st.close();
            } catch (Throwable whatever) {
            }
        }
    }

    static void close(OracleResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Throwable whatever) {
            }
        }
    }

}
