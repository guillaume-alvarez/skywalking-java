/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.oap.server.receiver.trace.provider;

import java.util.*;

/**
 * @author wusheng
 */
public class DBLatencyThresholds {
    private Map<String, Integer> thresholds;

    DBLatencyThresholds(String config) {
        thresholds = new HashMap<>();
        String[] settings = config.split(",");
        for (String setting : settings) {
            String[] typeValue = setting.split(":");
            if (typeValue.length == 2) {
                thresholds.put(typeValue[0].toLowerCase(), Integer.parseInt(typeValue[1]));
            }
        }
        if (!thresholds.containsKey("default")) {
            thresholds.put("default", 10000);
        }
    }

    public int getThreshold(String type) {
        type = type.toLowerCase();
        if (thresholds.containsKey(type)) {
            return thresholds.get(type);
        } else {
            return thresholds.get("default");
        }
    }
}