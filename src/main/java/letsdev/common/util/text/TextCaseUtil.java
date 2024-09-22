package letsdev.common.util.text;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class TextCaseUtil {

    public static String capitalizeAndSaveUpperSnakeCase(String upperSnakeCase) {
        return TextCaseUtilCachedMapHolder.cachedCapitalizationMap
                .computeIfAbsent(upperSnakeCase, TextCaseUtil::capitalizeUpperSnakeCase);
    }

    public static String capitalizeUpperSnakeCase(String upperSnakeCase) {
        Objects.requireNonNull(upperSnakeCase);
        // 파라미터는 확실하게 UPPER_CASE_WITH_UNDERSCORE 로 왔다고 가정함. (성능을 위해 assert로만 체크 중)
        char[] chars = upperSnakeCase.toCharArray();
        boolean willBeLower = false;

        assert checkIfUpperCaseWithUnderscore(chars) : "UPPER_CASE_WITH_UNDERSCORE 양식이 아닌 문자열이 입력됨.";
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (willBeLower && 'A' <= ch && ch <= 'Z') {
                ch |= ' '; // ch = ch | ' ';
            } else if (ch == '_') {
                ch = ' ';
                willBeLower = false;
            } else {
                willBeLower = true;
            }
            chars[i] = ch;
        }

        return new String(chars);
    }

    private static boolean checkIfUpperCaseWithUnderscore(char[] chars) {
        if (chars[0] == '_' || chars[chars.length - 1] == '_') {
            return false;
        }
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == '_') { // precondition: 마지막 인덱스는 이곳에 진입하지 않음.
                if (chars[i + 1] == '_') {  // 인덱스를 벗어나지 않으며, chars[i - 1]을 사용하는 것보다 한 턴 빠르다.
                    return false;
                }
            } else if (ch < 'A' || ch > 'Z') {
                return false;
            }
        }
        return true;
    }

    /**
     * 지연 로딩을 위한 내부 클래스 (홀더)
     */
    private static final class TextCaseUtilCachedMapHolder {
        private static final ConcurrentHashMap<String, String> cachedCapitalizationMap = new ConcurrentHashMap<>();
    }
}
