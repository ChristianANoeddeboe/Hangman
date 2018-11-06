package com.example.hangman.utilities;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class JSONreadwrite extends AppCompatActivity {

    private String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
    private String fileName;
    private String filePath;
    private File f;

    private static JSONreadwrite jsonreadwrite;

    private JSONreadwrite() {

    }

    public static JSONreadwrite getInstance() {
        if(jsonreadwrite == null) {
            jsonreadwrite = new JSONreadwrite();
        }
        return jsonreadwrite;
    }

    public void writeData(String word, int score) throws JSONException, IOException {


        //this.fileName = fileName;
        //filePath = baseDir + File.separator + fileName;
        //f = new File(Environment.getExternalStorageDirectory() + filePath);

        //FileWriter writer = new FileWriter(filePath);

        //writer.write(json.toString());
        //writer.flush();
        //writer.close();


    }


}


/**
 * @author Jacob Nordfalk
 */
/*
public class JsonGenerering extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);

        try {

            JSONObject json = new JSONObject(); // { }
            json.put("bank", "Merkur");  // {  "bank": "Merkur" }
            JSONArray kunder = new JSONArray();
            json.put("kunder", kunder);  // {  "bank": "Merkur", "kunder": [] }
            JSONObject k = new JSONObject();
            k.put("navn", "Jacob");
            k.put("kredit", 1000);
            kunder.put(k); // {  "bank": "Merkur", "kunder": [  { "navn": "Jacob", "kredit": 1000 }] }
            kunder.put(new JSONObject().put("navn", "Søren").put("kredit", 1007).put("alder", 29));
            kunder.put(new JSONObject("{ \"navn\": \"Bent\", \"kredit\": 5, \"kreditværdighed\": \"LAV\" }"));

            tv.append("\n\nHele JSONobjektet på en linje " + kunder.toString());
            tv.append("\n\nHele JSONobjektet på flere linjer " + kunder.toString(2));

        } catch (Exception ex) {
            ex.printStackTrace();
            tv.append("FEJL:" + ex.toString());
        }

        setContentView(tv);
    }
}

public class JsonParsning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);

        try {
            InputStream is = getResources().openRawResource(R.raw.data_jsoneksempel);
            //InputStream is = new URL("http://javabog.dk/eksempel.json").openStream();

            byte b[] = new byte[is.available()]; // kun små filer
            is.read(b);
            String str = new String(b, "UTF-8");
            tv.append(str);

            JSONObject json = new JSONObject(str);
            String bank = json.getString("bank");
            tv.append("\n=== Oversigt over " + bank + "s kunder ===\n");
            double totalKredit = 0;

            JSONArray kunder = json.getJSONArray("kunder");
            int antal = kunder.length();
            for (int i = 0; i < antal; i++) {
                JSONObject kunde = kunder.getJSONObject(i);
                System.err.println("obj = " + kunde);
                String navn = kunde.getString("navn");
                double kredit = kunde.getDouble("kredit");
                tv.append(navn + " med " + kredit + " kr.\n");
                totalKredit = totalKredit + kredit;
            }
            tv.append("\n\nTotal kredit er " + totalKredit + " kr.");

        } catch (Exception ex) {
            ex.printStackTrace();
            tv.append("FEJL:" + ex.toString());
        }

        setContentView(tv);
    }
}
*/