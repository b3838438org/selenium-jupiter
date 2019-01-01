/*
 * (C) Copyright 2017 Boni Garcia (http://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia.test.docker;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import io.github.bonigarcia.SelenoidConfig;
import io.github.bonigarcia.config.Config;

@ExtendWith(MockitoExtension.class)
public class SelenoidConfigFromHubTest {

    @InjectMocks
    SelenoidConfig selenoidConfig = new SelenoidConfig(new Config());

    @Test
    @SuppressWarnings("serial")
    void testBrowserConfig() throws IOException {
        String browsersJsonFromProperties = selenoidConfig
                .getBrowsersJsonAsString();

        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> browserMap = gson
                .fromJson(browsersJsonFromProperties, mapType);

        assertTrue(browserMap.containsKey("chrome"));
        assertTrue(browserMap.containsKey("firefox"));
        assertTrue(browserMap.containsKey("operablink"));
    }

}
