package com.example.hangman.utilities;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.example.hangman.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CSVParser extends AppCompatActivity {

    String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
    String fileName;
    String filePath;
    File f;

    private static CSVParser csvParser;

    private CSVParser() {}

    public static CSVParser getinstance() {
        return csvParser;
    }

    public void writeCSV(String fileName, ) throws IOException {
        this.fileName = fileName;
        filePath = baseDir + File.separator + fileName;
        f = new File(Environment.getExternalStorageDirectory() + filePath);

        if(!f.exists()) {
            System.out.println("Could not create file.");
        }

        FileWriter writer = new FileWriter(filePath);

        writer.

    }

    public void exportEmailInCSV() throws IOException {
        {

            File folder = new File(Environment.getExternalStorageDirectory()
                    + "/Folder");

            boolean var = false;
            if (!folder.exists())
                var = folder.mkdir();

            System.out.println("" + var);


            final String filename = folder.toString() + "/" + "Test.csv";

            };

            new Thread() {
                public void run() {
                    try {

                        FileWriter fw = new FileWriter(filename);

                        Cursor cursor = db.selectAll();

                        fw.append("No");
                        fw.append(',');

                        fw.append("code");
                        fw.append(',');

                        fw.append("nr");
                        fw.append(',');

                        fw.append("Orde");
                        fw.append(',');

                        fw.append("Da");
                        fw.append(',');

                        fw.append("Date");
                        fw.append(',');

                        fw.append("Leverancier");
                        fw.append(',');

                        fw.append("Baaln");
                        fw.append(',');

                        fw.append("asd");
                        fw.append(',');

                        fw.append("Kwaliteit");
                        fw.append(',');

                        fw.append("asd");
                        fw.append(',');

                        fw.append('\n');

                        if (cursor.moveToFirst()) {
                            do {
                                fw.append(cursor.getString(0));
                                fw.append(',');

                                fw.append(cursor.getString(1));
                                fw.append(',');

                                fw.append(cursor.getString(2));
                                fw.append(',');

                                fw.append(cursor.getString(3));
                                fw.append(',');

                                fw.append(cursor.getString(4));
                                fw.append(',');

                                fw.append(cursor.getString(5));
                                fw.append(',');

                                fw.append(cursor.getString(6));
                                fw.append(',');

                                fw.append(cursor.getString(7));
                                fw.append(',');

                                fw.append(cursor.getString(8));
                                fw.append(',');

                                fw.append(cursor.getString(9));
                                fw.append(',');

                                fw.append(cursor.getString(10));
                                fw.append(',');

                                fw.append('\n');

                            } while (cursor.moveToNext());
                        }
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }

                        // fw.flush();
                        fw.close();

                    } catch (Exception e) {
                    }
                    handler.sendEmptyMessage(0);
                    progDailog.dismiss();
                }
            }.start();

        }

    }

    /*
    CSVWriter writer;
    // File exist
if(f.exists() && !f.isDirectory()){
        mFileWriter = new FileWriter(filePath , true);
        writer = new CSVWriter(mFileWriter);
    }
else {
        writer = new CSVWriter(new FileWriter(filePath));
    }
    String[] data = {"Ship Name","Scientist Name", "...",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").formatter.format(date)});

writer.writeNext(data);

writer.close();
*/
}
