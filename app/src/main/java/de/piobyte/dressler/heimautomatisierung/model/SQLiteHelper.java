package de.piobyte.dressler.heimautomatisierung.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class SQLiteHelper extends SQLiteOpenHelper {

    // Database Info
    public static final String DATABASE_NAME = "heimautomatisierung.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String GROUP_TABLE_NAME = "group";
    private static final String DEVICE_TABLE_NAME = "device";
    private static final String MODUS_TABLE_NAME = "modus";
    private static final String MODUS_DEVICE_TABLE_NAME = "modusDevice";
    private static final String GROUP_DEVICE_TABLE_NAME = "groupDevice";

    // device Table Columns
    private static final String KEY_DEVICE_ID = "id";
    private static final String KEY_DEVICE_NAME = "deviceName";
    private static final String KEY_DEVICE_STATUS = "deviceStatus";
    private static final String KEY_DEVICE_ICON = "deviceIcon";
    private static final String KEY_DEVICE_ATTRIBUTES = "deviceAttributes";

    // group Table Columns
    private static final String KEY_GROUP_ID = "id";
    private static final String KEY_GROUP_NAME = "groupName";

    // modus Table Columns
    private static final String KEY_MODUS_ID = "id";
    private static final String KEY_MODUS_NAME = "name";

    // group_device Table Columns
    private static final String KEY_GROUP_DEVICE_ID = "id";
    private static final String KEY_GD_GROUP_ID = "groupId";
    private static final String KEY_GD_DEVICE_ID = "deviceId";

    // modus_device Table Colums
    private static final String KEY_MODUS_DEVICE_ID = "id";
    private static final String KEY_MD_MODUS_ID = "modusId";
    private static final String KEY_MD_DEVICE_ID = "deviceId";

    private static SQLiteHelper sInstance;
    Context mContext;

    public static synchronized SQLiteHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SQLiteHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DEVICE_TABLE = "CREATE TABLE " + DEVICE_TABLE_NAME +
                "(" +
                KEY_DEVICE_ID + " INTEGER PRIMARY KEY," +
                KEY_DEVICE_NAME + " TEXT," +
                KEY_DEVICE_ATTRIBUTES + " TEXT," +
                KEY_DEVICE_STATUS + " BOOLEAN" +
                ")";

        String CREATE_GROUP_TABLE = "CREATE TABLE " + GROUP_TABLE_NAME +
                "(" +
                KEY_GROUP_ID + " INTEGER PRIMARY KEY," +
                KEY_GROUP_NAME + " TEXT"  +
                ")";
        String CREATE_MODUS_TABLE = "CREATE TABLE " + MODUS_TABLE_NAME +
                "(" +
                KEY_MODUS_ID + " INTEGER PRIMARY KEY," +
                KEY_MODUS_NAME + " TEXT"  +
                ")";

        String CREATE_GD_TABLE = "CREATE TABLE " + GROUP_DEVICE_TABLE_NAME +
                "(" +
                KEY_GROUP_DEVICE_ID + " INTEGER PRIMARY KEY," +
                KEY_GD_DEVICE_ID + " INTEGER REFERENCES " + DEVICE_TABLE_NAME + "," +
                KEY_GD_GROUP_ID + " INTEGER REFERENCES " + GROUP_TABLE_NAME +
                ")";

        String CREATE_MD_TABLE = "CREATE TABLE " + MODUS_DEVICE_TABLE_NAME +
                "(" +
                KEY_MODUS_DEVICE_ID + " INTEGER PRIMARY KEY," +
                KEY_MD_DEVICE_ID + " INTEGER REFERENCES " + DEVICE_TABLE_NAME + "," +
                KEY_MD_MODUS_ID + " INTEGER REFERENCES " + MODUS_TABLE_NAME +
                ")";

        db.execSQL(CREATE_DEVICE_TABLE);
        db.execSQL(CREATE_GROUP_TABLE);
        db.execSQL(CREATE_MODUS_TABLE);
        db.execSQL(CREATE_MD_TABLE);
        db.execSQL(CREATE_GD_TABLE);
        Log.d("database", "database successfully created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DEVICE_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + GROUP_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + MODUS_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + GROUP_DEVICE_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + MODUS_DEVICE_TABLE_NAME);
            onCreate(db);
        }
    }

    public void addDevice(hADevice item) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            //long itemId = addOrUpdateListItem(item);

            ContentValues values = new ContentValues();
            values.put(KEY_DEVICE_NAME, item.getName());
            values.put(KEY_DEVICE_STATUS, item.isStatus());
            values.put(KEY_DEVICE_ATTRIBUTES, "keine");
            values.put(KEY_DEVICE_ID, item.getId());

            db.insertOrThrow(DEVICE_TABLE_NAME, null, values);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d("d", "Error while trying to add listitem to database");
        } finally {
            db.endTransaction();
        }
    }

    public long addOrUpdateDevice(hADevice item) {

        SQLiteDatabase db = getWritableDatabase();
        long itemId = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_DEVICE_NAME, item.getName());
            values.put(KEY_DEVICE_ID, item.getId());
            values.put(KEY_DEVICE_ATTRIBUTES, "keine");
            values.put(KEY_DEVICE_STATUS, item.isStatus());

            int rows = db.update(DEVICE_TABLE_NAME, values, KEY_DEVICE_ID + "= ?" , new String[]{String.valueOf(item.getId())});

            if (rows == 1) {
                String usersSelectQuery = String.format("SELECT * FROM %s WHERE %s = ?",
                        DEVICE_TABLE_NAME, KEY_DEVICE_ID);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(item.getId())});

                try {
                    if (cursor.moveToFirst()) {
                        itemId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                itemId = db.insertOrThrow(DEVICE_TABLE_NAME, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d("d", "Error while trying to add or update listitem");
        } finally {
            db.endTransaction();
        }
        return itemId;
    }

    public void addGroup(hAGroup group) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            //long listId = addOrUpdateGroup(group);

            ContentValues values = new ContentValues();
            values.put(KEY_GROUP_NAME, group.getName());
            values.put(KEY_GROUP_ID, group.getId());

            db.insertOrThrow(GROUP_TABLE_NAME, null, values);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d("d", "Error while trying to add list to database");
        } finally {
            db.endTransaction();
        }
    }

    public long addOrUpdateGroup(hAGroup group) {

        SQLiteDatabase db = getWritableDatabase();
        long listId = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_GROUP_NAME, group.getName());
            values.put(KEY_GROUP_ID, group.getId());

            int rows = db.update(GROUP_TABLE_NAME, values, KEY_GROUP_ID + "= ?", new String[]{String.valueOf(group.getId())});

            if (rows == 1) {
                String usersSelectQuery = String.format("SELECT * FROM %s WHERE %s = ?",
                        GROUP_TABLE_NAME, KEY_GROUP_ID);
                Cursor cursor = db.rawQuery(usersSelectQuery,  new String[]{String.valueOf(group.getId())});
                try {
                    if (cursor.moveToFirst()) {
                        listId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                listId = db.insertOrThrow(GROUP_TABLE_NAME, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d("d", "Error while trying to add or update list");
        } finally {
            db.endTransaction();
        }
        return listId;
    }

    public ArrayList<hADevice> getAllDevices() {
        ArrayList<hADevice> list = new ArrayList<>();

        String POSTS_SELECT_QUERY =
                String.format("SELECT * FROM %s ",
                        DEVICE_TABLE_NAME);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    hADevice newDevice = new hADevice();
                    newDevice.setName(cursor.getString(cursor.getColumnIndex(KEY_DEVICE_NAME)));
                    newDevice.setId(cursor.getInt(cursor.getColumnIndex(KEY_DEVICE_ID)));

                    list.add(newDevice);

                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("database", "Error while trying to get devices from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        Log.d("database", "successfully got all devices");
        return list;
    }
    public ArrayList<hADevice> getAllGroupDevices(int groupId) {
        ArrayList<hADevice> items = new ArrayList<>();

        String ITEMS_SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s = %s",
                        GROUP_DEVICE_TABLE_NAME,
                        KEY_GD_GROUP_ID,
                        groupId);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(ITEMS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    hADevice newDevice = new hADevice();
                    newDevice.setId(cursor.getInt(cursor.getColumnIndex(KEY_DEVICE_ID)));
                    newDevice.setName(cursor.getString(cursor.getColumnIndex(KEY_DEVICE_NAME)));
                    boolean isDone = cursor.getInt(cursor.getColumnIndex(KEY_DEVICE_ATTRIBUTES)) > 0;
                    newDevice.setStatus(isDone);

                    items.add(newDevice);

                } while(cursor.moveToNext());
                Log.d("database", "successfully got all items from list nr." + groupId);
            }
        } catch (Exception e) {
            Log.d("database", "Error while trying to get items from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return items;
    }

    public hADevice getDevice(int id){
        hADevice device = new hADevice();

        String POSTS_SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s",
                        DEVICE_TABLE_NAME,
                        "id = " + id + ";");

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);
        try {
            device.setName(cursor.getString(cursor.getColumnIndex(KEY_DEVICE_NAME)));
            device.setIcon(cursor.getInt(cursor.getColumnIndex(KEY_DEVICE_ICON)));
            boolean isOn = cursor.getInt(cursor.getColumnIndex(KEY_DEVICE_ATTRIBUTES)) > 0;
            device.setStatus(isOn);

        } catch (Exception e) {
            Log.d("database", "Error while trying to get list nr. " + id + " from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        Log.d("database", "successfully got list nr. " + id);

        return device;
    }

    public void deleteDevice(hADevice device) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            String id = String.valueOf(device.getId());

            int rows = db.delete(DEVICE_TABLE_NAME,  KEY_DEVICE_ID + "= ?", new String[]{id});

            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d("d", "Error while trying to delete list from database");
        } finally {
            db.endTransaction();
        }
    }

    public void deleteGroup(hAGroup group) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            String id = String.valueOf(group.getId());

            int rows = db.delete(GROUP_TABLE_NAME,  KEY_GROUP_ID + "= ?", new String[]{id});

            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d("d", "Error while trying to delete item from database");
        } finally {
            db.endTransaction();
        }
    }

    public void deleteModus(hAModi modus) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            String id = String.valueOf(modus.getId());

            int rows = db.delete(GROUP_TABLE_NAME,  KEY_GROUP_ID + "= ?", new String[]{id});

            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d("d", "Error while trying to delete item from database");
        } finally {
            db.endTransaction();
        }
    }


}

