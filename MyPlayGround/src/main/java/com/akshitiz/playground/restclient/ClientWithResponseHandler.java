package com.akshitiz.playground.restclient;

/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * This example demonstrates the use of the {@link ResponseHandler} to simplify the process of
 * processing the HTTP response and releasing associated resources.
 */
public class ClientWithResponseHandler {

  public static void playRestClient() throws IOException {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    try {
      HttpGet httpget = new HttpGet("http://httpbin.org/");
      // Create a custom response handler
      ResponseHandler<String> responseHandler =
          new ResponseHandler<String>() {

            @Override
            public String handleResponse(final HttpResponse response)
                throws ClientProtocolException, IOException {
              int status = response.getStatusLine().getStatusCode();
              if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
              } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
              }
            }
          };
      for (int i = 0; i < 10; i++) {

        long time = System.currentTimeMillis();
        httpclient.execute(httpget, responseHandler);
        time = System.currentTimeMillis() - time;
        String msg = "Time taken: ";
        System.out.println(msg + time);
      }
    } finally {
      httpclient.close();
    }
    System.out.println("----------------------------------------");
  }

  public void getPost(String postId) {

    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
      HttpGet httpGet = new HttpGet("https://jsonplaceholder.typicode.com/posts/" + postId);
      HttpResponse response = httpClient.execute(httpGet);
      System.out.println(response.getStatusLine().getStatusCode());
    } catch (IOException e) {
      System.out.println("Unable to fetch post with id = " + postId);
    }
  }

  //  public void getPosts(String[] postIds) {
  //
  //    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
  //      HttpGet httpGet;
  //      long time;
  //      HttpResponse response;
  //      for (String postId : postIds) {
  //        httpGet = new HttpGet("https://jsonplaceholder.typicode.com/posts/" + postId);
  //        time = System.currentTimeMillis();
  //        response = httpClient.execute(httpGet);
  //        System.out.println(
  //            response.getStatusLine().getStatusCode()
  //                + " Time Taken : "
  //                + (System.currentTimeMillis() - time));
  //        httpGet.releaseConnection();
  //      }
  //    } catch (IOException e) {
  //      System.out.println("Unable to fetch posts");
  //      getPosts(postIds);
  //    }
  //  }

  public void getPosts(String[] postIds) {

    try {
      for (String postId : postIds) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://jsonplaceholder.typicode.com/posts/" + postId);
        long time = System.currentTimeMillis();
        HttpResponse response = httpClient.execute(httpGet);
        System.out.println(
            response.getStatusLine().getStatusCode()
                + " Time Taken : "
                + (System.currentTimeMillis() - time));
      }
    } catch (IOException e) {
      System.out.println("Unable to fetch posts");
    }
  }
}
