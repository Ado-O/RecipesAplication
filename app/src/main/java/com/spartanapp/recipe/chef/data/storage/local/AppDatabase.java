package com.spartanapp.recipe.chef.data.storage.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.spartanapp.recipe.chef.data.Directions;
import com.spartanapp.recipe.chef.data.Favorites;
import com.spartanapp.recipe.chef.data.Ingredients;
import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.data.RecipesTag;
import com.spartanapp.recipe.chef.data.SubRecipe;
import com.spartanapp.recipe.chef.data.Tag;
import com.spartanapp.recipe.chef.data.storage.local.content.ContentDao;
import com.spartanapp.recipe.chef.data.storage.local.favorite.FavoriteDao;
import com.spartanapp.recipe.chef.data.storage.local.recipes.RecipesDao;
import com.spartanapp.recipe.chef.data.storage.local.search.SearchDao;

import java.io.File;

@Database(entities = {
        Recipe.class,
        SubRecipe.class,
        Tag.class,
        Ingredients.class,
        Directions.class,
        RecipesTag.class,
        Favorites.class,
}, version = 15, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ContentDao getContentDao();

    public abstract RecipesDao getRecipesDao();

    public abstract SearchDao getSuggestedDao();

    public abstract FavoriteDao getFavoriteDao();

    public static final Object sLock = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "recipe.db").build();
                pragmaCheckpoint(context);
            }
            return INSTANCE;
        }
    }

    /**
     * cod for db editor
     */
    private static void pragmaCheckpoint(Context context) {
        String dbpath = (context).getDatabasePath("spartan.db").getPath();
        if (ifDBExists(dbpath)) {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbpath, null, Context.MODE_PRIVATE);
            Cursor csr = db.rawQuery("PRAGMA wal_checkpoint", null);
            while (csr.moveToNext()) {
                StringBuilder sb = new StringBuilder();
                for (int c = 0; c < csr.getColumnCount(); c++) {
                    sb.append("\n\tColumnName = ").append(csr.getColumnName(c)).append(" Value=").append(csr.getString(c));
                }
                android.util.Log.d("INFO", sb.toString());
            }
            db.close();
        }
    }

    private static boolean ifDBExists(String dbpath) {
        File db = new File(dbpath);
        if (db.exists()) return true;
        File dir = new File(db.getParent());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return false;
    }
}

