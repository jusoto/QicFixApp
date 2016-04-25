package com.qicfix.qicfixapp.util;

import org.json.JSONObject;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Author Foo
 *
 * Uses Executor service to spin up new threads
 * and uses Callables to call http methods and returns back JSON response
 *
 */
public class CustomThread {

    public static String CustomThreadHttpGet(final String url){

        String result = null;

        ExecutorService service = Executors.newCachedThreadPool();

        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String response = CustomURLConnection.executeHttpGet(url);
                return response;
            }
        });

        try {
            // get() waits for the task to finish and then gets the result
            result = future.get();
        } catch (InterruptedException e) {
            // thrown if task was interrupted before completion
            e.printStackTrace();
        } catch (ExecutionException e) {
            // thrown if the task threw an execption while executing
            e.printStackTrace();
        }

        return result;
    }

    public static String CustomThreadHttpPost(final String url, final String parameters){

        String result = null;

        ExecutorService service = Executors.newCachedThreadPool();

        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String response = CustomURLConnection.executeHttpPost(url, parameters);
                return response;
            }
        });

        try {
            // get() waits for the task to finish and then gets the result
            result = future.get();
        } catch (InterruptedException e) {
            // thrown if task was interrupted before completion
            e.printStackTrace();
        } catch (ExecutionException e) {
            // thrown if the task threw an execption while executing
            e.printStackTrace();
        }

        return result;

    }

    public static String CustomThreadHttpPut(final String url, final JSONObject parameters){

        String result = null;

        ExecutorService service = Executors.newCachedThreadPool();

        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String response = CustomURLConnection.executeHttpPut(url, parameters);
                return response;
            }
        });

        try {
            // get() waits for the task to finish and then gets the result
            result = future.get();
        } catch (InterruptedException e) {
            // thrown if task was interrupted before completion
            e.printStackTrace();
        } catch (ExecutionException e) {
            // thrown if the task threw an execption while executing
            e.printStackTrace();
        }

        return result;

    }
}
