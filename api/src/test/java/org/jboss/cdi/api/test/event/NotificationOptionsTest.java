package org.jboss.cdi.api.test.event;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.time.Duration;
import java.util.concurrent.Executor;

import jakarta.enterprise.event.NotificationOptions;

import org.testng.annotations.Test;

/**
 * 
 * @author Martin Kouba
 */
public class NotificationOptionsTest {

    @Test
    public void testBuilder() {
        Duration timeout = Duration.ofDays(1);
        NotificationOptions options = NotificationOptions.of("timeout", timeout);
        assertEquals(timeout, options.get("timeout"));
        assertNull(options.getExecutor());
        assertNull(options.get("alpha"));
        options = NotificationOptions.builder().set("foo", "bar").setExecutor(command -> {
        }).build();
        assertEquals("bar", options.get("foo"));
        assertNotNull(options.getExecutor());
        options = NotificationOptions.ofExecutor(command -> {
        });
        Executor executor = options.getExecutor();
        assertNotNull(executor);
        assertNull(options.get("timeout"));
    }

}
