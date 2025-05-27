package utils;

import java.util.stream.Stream;

public class InjectionPayloads {

    public static Stream<String> htmlInjectionPayloads() {
        return Stream.of(
                "<script>alert('XSS')</script>",
                "<img src='x' onerror='alert(1)'>",
                "<svg onload='alert(1)'>",
                "<iframe src='javascript:alert(1)'>",
                "<body onload=alert('XSS')>",
                "<div><p onmouseover=alert(1)>test</p></div>"
        );
    }

    public static Stream<String> sqlInjectionPayloads() {
        return Stream.of(
                "' OR '1'='1",
                "' OR 1=1 --",
                "'; DROP TABLE users; --",
                "' OR 'a'='a",
                "'; EXEC xp_cmdshell('dir'); --"
        );
    }
}
