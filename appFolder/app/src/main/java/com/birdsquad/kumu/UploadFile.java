package com.birdsquad.kumu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UploadFile extends AsyncTask<String, Void, String> {

    private Photo photo;
    private int formID;
    private Number latitude, longitude;
    private Context context;

    public UploadFile(Photo photo, Number latitude, Number longitude, int formID, Context context){
        this.photo = photo;
        this.formID = formID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        try{
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            Log.d("PostToServer", "not compressed");

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inSampleSize = 2;  //you can also calculate your inSampleSize
            options.inJustDecodeBounds = false;
            options.inTempStorage = new byte[16 * 1024];

            Bitmap bm = BitmapFactory.decodeFile(photo.getFileAbsPath(),options); //changed line code
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

            Log.d("PostToServer", "currently compressed");
            String path = context.getFilesDir() + File.separator + "temporary_file.jpg";
            File file = new File( path);
            //Generate random file
            while(file.exists()){
                path = context.getFilesDir() + File.separator + "temporary_file" + (Math.random()*100) + ".jpg";
                file = new File(path);
            }
            Log.d("PostToServer", "got temp file");
            try {
                FileOutputStream fo = new FileOutputStream(file);
                fo.write(bytes.toByteArray());
                fo.flush();
                fo.close();
            } catch (IOException e) {
                Log.d("PostToServer", e.getMessage());
            }
            Log.d("PostToServer", "done did");

            String content = "";
            String url = KumuApp.URLToServerPostImage;

            if(file != null) {

                Map<String, String> pms = new HashMap<String, String>(2);
                pms.put("longitude", longitude + "");
                pms.put("latitude", latitude + "");
                pms.put("formid", formID + "");

                try{
                    String result = multipartRequest(url, pms, path, "image", "image/jpeg");
                    Log.d("PostToServer", "SUCCESS WE DID IT BOIIISS || " + result);
                }catch(Exception e){
                    Log.d("PostToServer", "Error: " + e.getMessage());
                }
            } else {
                Log.d("PostToServer", "Image file is null");
            }
        } catch(Exception e){
            Log.d("PostToServer", "Error occurred: " + e.getMessage());
        }

        return "done";
    }

    public String multipartRequest(String urlTo, Map<String, String> parmas, String filepath, String filefield, String fileMimeType) throws Exception {
        HttpURLConnection connection = null;
        DataOutputStream outputStream = null;
        InputStream inputStream = null;

        String twoHyphens = "--";
        String boundary = "*****" + Long.toString(System.currentTimeMillis()) + "*****";
        String lineEnd = "\r\n";

        String result = "";

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;

        String[] q = filepath.split("/");
        int idx = q.length - 1;

        try {
            File file = new File(filepath);
            FileInputStream fileInputStream = new FileInputStream(file);

            URL url = new URL(urlTo);
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("User-Agent", "Android Multipart HTTP Client 1.0");
            connection.setRequestProperty("enctype", "multipart/form-data");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"" + filefield + "\"; filename=\"" + q[idx] + "\"" + lineEnd);
            outputStream.writeBytes("Content-Type: " + fileMimeType + lineEnd);
            outputStream.writeBytes("Content-Transfer-Encoding: binary" + lineEnd);

            outputStream.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            int fileSize = 0;
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                fileSize += bytesRead;
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
            Log.d("PostToServer", "Size of file: " + fileSize);

            outputStream.writeBytes(lineEnd);

            // Upload POST Data
            Iterator<String> keys = parmas.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = parmas.get(key);

                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd);
                outputStream.writeBytes("Content-Type: text/plain" + lineEnd);
                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(value);
                outputStream.writeBytes(lineEnd);
            }

            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);


            if (200 != connection.getResponseCode()) {
                throw new Exception("Failed to upload code: " + connection.getResponseCode() + " " + connection.getResponseMessage());
            }

            inputStream = connection.getInputStream();

            result = this.convertStreamToString(inputStream);

            fileInputStream.close();
            inputStream.close();
            outputStream.flush();
            outputStream.close();

            file.delete();

            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    private String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            Log.d("PostToServer", e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.d("PostToServer", e.getMessage());
            }
        }
        return sb.toString();
    }


    @Override
    protected void onPostExecute(String result) {

    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }

}
