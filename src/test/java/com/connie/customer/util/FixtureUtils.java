package com.connie.customer.util;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.BeanArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.BuilderArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FailoverIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;

import java.util.List;

public class FixtureUtils {

    public static FixtureMonkey getInstance() {
        return FixtureMonkey.builder()
                .objectIntrospector(new FailoverIntrospector(
                        List.of(
                                FieldReflectionArbitraryIntrospector.INSTANCE,
                                BeanArbitraryIntrospector.INSTANCE,
                                BuilderArbitraryIntrospector.INSTANCE
                        )
                ))
                .defaultNotNull(true)
                .build();
    }

}
