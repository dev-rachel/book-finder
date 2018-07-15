package hyunjin.bookfinder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;

@Service
public class ApiRequestService {

    protected RestTemplate restTemplateNoneInterceptor;
    private Logger logger = LoggerFactory.getLogger(getClass());

    private RestTemplate getRestTemplateNoneInterceptor() {
        this.restTemplateNoneInterceptor.setErrorHandler(new RestErrorHandler());
        return this.restTemplateNoneInterceptor;
    }

    public <T> ResponseEntity<T> requestApi(
            String url, HttpHeaders headers, HttpMethod method, Object body, Class<T> retCls) throws Exception {
        logger.info("API URL : {}", url);
        return getRestTemplateNoneInterceptor().exchange(
                url,
                method,
                new HttpEntity<>(body, headers),
                retCls
        );
    }

    class RestErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return false;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            System.out.println(Objects.toString(response.getBody()));
        }
    }
}
