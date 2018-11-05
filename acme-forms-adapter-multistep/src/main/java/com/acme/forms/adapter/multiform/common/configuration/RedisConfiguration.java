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
package com.acme.forms.adapter.multiform.common.configuration;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * Describes a physical details of HTTP service endpoint the ServiceAdapter will use.
 */

@DataObject(generateConverter = true, publicConverter = false)
public class RedisConfiguration {

  private String host;
  private int port;

  /**
   * Default constructor
   */
  public RedisConfiguration() {
    //empty constructor
  }


  /**
   * Copy constructor
   *
   * @param other the instance to copy
   */
  public RedisConfiguration(
      RedisConfiguration other) {
    this.host = other.host;
  }

  /**
   * Create an settings from JSON
   *
   * @param json the JSON
   */
  public RedisConfiguration(JsonObject json) {
    this();
    RedisConfigurationConverter.fromJson(json, this);
  }

  /**
   * Convert to JSON
   *
   * @return the JSON
   */
  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    RedisConfigurationConverter.toJson(this, json);
    return json;
  }

  public String getHost() {
    return host;
  }

  public RedisConfiguration setHost(String host) {
    this.host = host;
    return this;
  }

  public int getPort() {
    return port;
  }

  public RedisConfiguration setPort(int port) {
    this.port = port;
    return this;
  }


}
