package com.qicfix.qicfixapp.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by  Steve Foo on 4/12/2016.
 */
public class CustomURLConnection {
    /** The time it takes for our client to timeout */
    public static final int HTTP_TIMEOUT = 5 * 1000; // milliseconds

    /**
     *
     * Performs an HTTP POST request to the specified url with the specified parameters.
     *
     * @param url The web address to post the request to
     * @param postParameters The parameters to send via the request
     * @return The result of the request
     * @throws Exception
     */

    public static String executeHttpPost(String url, String postParameters) throws Exception {
        URL apiUrl;
        HttpURLConnection request = null;
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder("");
        try {
            //Create connection
            apiUrl = new URL(url);
            request = (HttpURLConnection) apiUrl.openConnection();
            request.setConnectTimeout(HTTP_TIMEOUT);
            request.setRequestMethod("POST");
            request.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            //Send request
            DataOutputStream wr = new DataOutputStream(request.getOutputStream());
            System.out.println(postParameters);
            wr.writeBytes(postParameters);
            wr.flush();
            wr.close();
            System.out.println(request);

            //turn request response into a string
            if(request.getResponseCode() >= 400){
                //Error returned
                InputStream errStream = request.getErrorStream();
                if(errStream==null) {
                    //Error with no explanation, create one.
                    JSONObject noResponseError = new JSONObject();
                    noResponseError.put("code", request.getResponseCode());
                    noResponseError.put("message", "No Response Data");
                    sb = new StringBuilder(noResponseError.toString());
                } else {
                    //Error with explanation, get it.
                    in = new BufferedReader(new InputStreamReader(errStream, "utf-8"));
                    String line;
                    String NL = System.getProperty("line.separator");
                    while ((line = in.readLine()) != null) {
                        sb.append(line).append(NL);
                    }
                    in.close();
                    JSONObject errorJSON = new JSONObject(sb.toString());
                    try {
                        sb = new StringBuilder(errorJSON.getString("error"));
                    }
                    catch(JSONException e) {
                        //No error encapsulation
                        sb = new StringBuilder(errorJSON.toString());
                    }
                }
            } else if(request.getResponseCode() <= 200 && request.getResponseCode() < 300) {
                //Sucessful response
                try {
                    InputStream inStream = request.getInputStream();
                    in = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                    String line;
                    String NL = System.getProperty("line.separator");
                    while ((line = in.readLine()) != null) {
                        sb.append(line).append(NL);
                    }
                    in.close();
                }
                catch (java.net.SocketException s){
                    JSONObject timeOutResponseError = new JSONObject();
                    timeOutResponseError.put("code", "408");
                    timeOutResponseError.put("message", "No Response Data");
                    sb = new StringBuilder(timeOutResponseError.toString());

                } catch (IOException e) {
                    //No response message, create one.
                    JSONObject noResponseError = new JSONObject();
                    noResponseError.put("code", request.getResponseCode());
                    noResponseError.put("message", "No Response Data");
                    sb = new StringBuilder(noResponseError.toString());
                }
            }
            else {
                //unknown response, create error message
                JSONObject noResponseError = new JSONObject();
                noResponseError.put("code", request.getResponseCode());
                noResponseError.put("message", "Unknown response code");
                sb = new StringBuilder(noResponseError.toString());
            }
        }
        catch (IOException e) {
            JSONObject timeoutError = new JSONObject();
            timeoutError.put("code", 408);
            sb = new StringBuilder(timeoutError.toString());
        } finally {
            if (request != null) {
                request.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     *
     * Performs an HTTP PUT request to the specified url with the specified parameters.
     *
     * @param url The web address to post the request to
     * @param putParameters The parameters to send via the request
     * @return The result of the request
     * @throws Exception
     */
    public static String executeHttpPut(String url, String putParameters) throws Exception {
        BufferedReader in = null;
        URL apiUrl;
        HttpURLConnection request;
        StringBuilder sb = new StringBuilder("");
        try {
            //Create connection
            apiUrl = new URL(url);
            request = (HttpURLConnection) apiUrl.openConnection();
            request.setConnectTimeout(HTTP_TIMEOUT);
            request.setRequestMethod("PUT");
            request.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            //Send request
            DataOutputStream wr = new DataOutputStream(request.getOutputStream());

            wr.writeBytes(putParameters.toString());
            wr.flush();
            wr.close();

            //turn request response into a string
            if(request.getResponseCode() >= 400){
                //Error returned
                InputStream errStream = request.getErrorStream();
                if(errStream==null) {
                    //Error with no explanation, create one.
                    JSONObject noResponseError = new JSONObject();
                    noResponseError.put("code", request.getResponseCode());
                    noResponseError.put("message", "No Response Data");
                    sb = new StringBuilder(noResponseError.toString());
                } else {
                    //Error with explanation, get it.
                    in = new BufferedReader(new InputStreamReader(errStream, "utf-8"));
                    String line;
                    String NL = System.getProperty("line.separator");
                    while ((line = in.readLine()) != null) {
                        sb.append(line).append(NL);
                    }
                    in.close();
                    JSONObject errorJSON = new JSONObject(sb.toString());
                    try {
                        sb = new StringBuilder(errorJSON.getString("error"));
                    }
                    catch(JSONException e) {
                        //No error encapsulation
                        sb = new StringBuilder(errorJSON.toString());
                    }
                }
            } else if(request.getResponseCode() <= 200 && request.getResponseCode() < 300) {
                //Sucessful response
                try {
                    InputStream inStream = request.getInputStream();
                    in = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                    String line;
                    String NL = System.getProperty("line.separator");
                    while ((line = in.readLine()) != null) {
                        sb.append(line).append(NL);
                    }
                    in.close();
                }
                catch (IOException e) {
                    //No response message, create one.
                    JSONObject noResponseError = new JSONObject();
                    noResponseError.put("code", request.getResponseCode());
                    noResponseError.put("message", "No Response Data");
                    sb = new StringBuilder(noResponseError.toString());
                }
            }
            else {
                //unknown response, create error message
                JSONObject noResponseError = new JSONObject();
                noResponseError.put("code", request.getResponseCode());
                noResponseError.put("message", "Unknown response code");
                sb = new StringBuilder(noResponseError.toString());
            }
        }
        catch (java.net.SocketException s){
            JSONObject timeOutResponseError = new JSONObject();
            timeOutResponseError.put("code", "408");
            timeOutResponseError.put("message", "No Response Data");
            sb = new StringBuilder(timeOutResponseError.toString());

        } catch (IOException e) {
            JSONObject timeoutError = new JSONObject();
            timeoutError.put("code", 408);
            sb = new StringBuilder(timeoutError.toString());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * Performs an HTTP GET request to the specified url.
     *
     * @param url The web address to post the request to
     * @return The result of the request
     * @throws Exception
     */
    public static String executeHttpGet(String url) throws Exception {
        URL apiUrl;
        HttpURLConnection request;
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder("");
        try {
            apiUrl = new URL(url);
            request = (HttpURLConnection) apiUrl.openConnection();
            System.out.println(request);
            request.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            request.setConnectTimeout(HTTP_TIMEOUT);
            request.setReadTimeout(HTTP_TIMEOUT - 5);
            request.setRequestMethod("GET");

            //turn request response into a string
            if(request.getResponseCode() >= 400){
                //Error returned
                InputStream errStream = request.getErrorStream();
                if(errStream==null) {
                    //Error with no explanation, create one.
                    JSONObject noResponseError = new JSONObject();
                    noResponseError.put("code", request.getResponseCode());
                    noResponseError.put("message", "No Response Data");
                    sb = new StringBuilder(noResponseError.toString());
                } else {
                    //Error with explanation, get it.
                    in = new BufferedReader(new InputStreamReader(errStream, "utf-8"));
                    String line;
                    String NL = System.getProperty("line.separator");
                    while ((line = in.readLine()) != null) {
                        sb.append(line).append(NL);
                    }
                    in.close();
                    JSONObject errorJSON = new JSONObject(sb.toString());
                    try {
                        sb = new StringBuilder(errorJSON.getString("error"));
                    }
                    catch(JSONException e) {
                        //No error encapsulation
                        sb = new StringBuilder(errorJSON.toString());
                    }
                }
            } else if(request.getResponseCode() >= 200 && request.getResponseCode() < 300) {
                //Sucessful response
                try {
                    InputStream inStream = request.getInputStream();
                    in = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                    String line;
                    String NL = System.getProperty("line.separator");
                    while ((line = in.readLine()) != null) {
                        sb.append(line).append(NL);
                    }
                    in.close();
                }
                catch (IOException e) {
                    //No response message, create one.
                    JSONObject noResponseError = new JSONObject();
                    noResponseError.put("code", request.getResponseCode());
                    noResponseError.put("message", "No Response Data");
                    sb = new StringBuilder(noResponseError.toString());
                }
            }
            else {
                //unknown response, create error message
                JSONObject noResponseError = new JSONObject();
                noResponseError.put("code", request.getResponseCode());
                noResponseError.put("message", "Unknown response code");
                sb = new StringBuilder(noResponseError.toString());
            }
        } catch (java.net.SocketException s){
            JSONObject timeOutResponseError = new JSONObject();
            timeOutResponseError.put("code", "408");
            timeOutResponseError.put("message", "No Response Data");
            sb = new StringBuilder(timeOutResponseError.toString());

        } catch (IOException e) {
            JSONObject timeoutError = new JSONObject();
            timeoutError.put("code", 408);
            sb = new StringBuilder(timeoutError.toString());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}

