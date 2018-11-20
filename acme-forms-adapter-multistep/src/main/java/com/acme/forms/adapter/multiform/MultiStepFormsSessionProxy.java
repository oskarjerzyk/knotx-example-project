/*
 * Copyright (C) 2018 Knot.x Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.forms.adapter.multiform;


import com.acme.forms.adapter.multiform.common.configuration.MultiStepFormsAdapterConfiguration;
import com.google.common.net.HttpHeaders;
import io.knotx.dataobjects.KnotContext;
import io.knotx.knot.AbstractKnotProxy;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import io.reactivex.Single;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.MultiMap;
import io.vertx.reactivex.core.Vertx;
import java.util.Random;
import java.util.Set;


public class MultiStepFormsSessionProxy extends AbstractKnotProxy {

  private static final Logger LOGGER = LoggerFactory.getLogger(MultiStepFormsSessionProxy.class);

  private static final String SESSION_NAME_COOKIE = "multiform_session";

  private final Vertx vertx;

  private MultiStepFormsAdapterConfiguration configuration;

  MultiStepFormsSessionProxy(Vertx vertx, MultiStepFormsAdapterConfiguration configuration) {
    this.vertx = vertx;
    this.configuration = configuration;
  }

  @Override
  public Single<KnotContext> processRequest(KnotContext knotContext) {
    String sessionValue = getSessionValue(knotContext);

    if (sessionValue == null) {
      Cookie sessionCookie = new DefaultCookie(SESSION_NAME_COOKIE, generateSessionId());

      MultiMap headers = knotContext.getClientResponse().getHeaders();
      headers.add("Set-Cookie",
          ServerCookieEncoder.STRICT.encode(sessionCookie));
      knotContext.getClientResponse().setHeaders(headers);
    }

    return Single.just(knotContext);
  }

  @Override
  protected boolean shouldProcess(Set<String> knots) {
    return true;
  }

  @Override
  protected KnotContext processError(KnotContext context, Throwable error) {
    LOGGER.error("Could not process template [{}]", context.getClientRequest().getPath(), error);
    KnotContext errorResponse = new KnotContext().setClientResponse(context.getClientResponse());
    errorResponse.getClientResponse()
        .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
    return errorResponse;
  }


  private String getSessionValue(KnotContext knotContext) {
    String sessionValue = null;
    String cookieHeader = knotContext.getClientRequest().getHeaders().get(HttpHeaders.COOKIE);

    if (cookieHeader != null) {
      Set<Cookie> cookies = ServerCookieDecoder.STRICT.decode(cookieHeader);

      for (Cookie cookie : cookies) {
        if (SESSION_NAME_COOKIE.equals(cookie.name())) {
          sessionValue = cookie.value();
        }
      }
    }

    return sessionValue;
  }

  private String generateSessionId() {
    return Integer.toString(new Random().nextInt());
  }
}
