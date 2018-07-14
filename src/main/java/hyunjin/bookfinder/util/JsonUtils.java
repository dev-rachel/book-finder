package hyunjin.bookfinder.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Date;

public class JsonUtils {
    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper sharedObjectMapper = createDefaultMapper();

    @Nonnull
    private static ObjectMapper createDefaultMapper() {

        ObjectMapper mapper = new ObjectMapper();
        configureCommonObjectMapper(mapper);
        return mapper;
    }

    public static void configureCommonObjectMapper(@CheckForNull ObjectMapper om) {

        if (om != null) {
            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            om.addHandler(new DeserializationProblemHandler() {

                @Override
                public Object handleUnexpectedToken(
                        DeserializationContext ctx,
                        Class<?> targetType,
                        JsonToken t,
                        JsonParser p,
                        String failureMsg) throws IOException {

                    return (targetType == Date.class && JsonToken.VALUE_NUMBER_FLOAT == t) ?
                            new Date(p.getLongValue()) : NOT_HANDLED;
                }
            });
        }
    }

    @CheckForNull
    public static String toJsonString(@CheckForNull Object o) {

        return toJsonString(o, null);
    }

    @CheckForNull
    public static <T> String toJsonString(@CheckForNull T o, @Nullable Class<? super T> targetClass) {

        if (o == null) {
            return null;
        }

        try {
            return sharedObjectMapper.writerFor(targetClass).writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.debug("Failed to serialize object to json", e);
            return null;
        }
    }

    @CheckForNull
    public static JsonNode fromJson(@CheckForNull String jsonString) {

        if (jsonString == null) {
            return null;
        }

        try {
            return sharedObjectMapper.readTree(jsonString);
        } catch (IOException e) {
            logger.debug("Failed to deserialize object from json", e);
            return null;
        }
    }

    @CheckForNull
    public static <T> T fromJson(@CheckForNull String jsonString, @CheckForNull Class<T> type) {

        if (jsonString == null || type == null) {
            return null;
        }

        try {
            return sharedObjectMapper.readerFor(type).readValue(jsonString);
        } catch (IOException e) {
            logger.debug("Failed to deserialize object from json", e);
            return null;
        }
    }
}
