package simplestock.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;
import simplestock.model.Trade;

import java.math.BigDecimal;
import java.net.URL;
import java.time.Instant;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleStockControllerIT extends BaseTest {

    @LocalServerPort
    private int port;

    private URL baseURL;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.baseURL = new URL("http://localhost:" + port + "/");
        generateTradeInformation();
        generateStockInformation();
    }

    @After
    public void cleanUp() throws Exception {
        cleanUpTradeInformation();
        cleanUpStockInformation();
    }

    @Test
    public void postBuyTrade() throws Exception {
        Trade trade = new Trade("TEA", Instant.now(), 12, new BigDecimal(991));
        URL url = new URL(baseURL, "trade/buy");
        RequestEntity<Trade> request = RequestEntity.post(url.toURI())
                .contentType(MediaType.APPLICATION_JSON).body(trade);

        ResponseEntity<Void> response = template.exchange(request, Void.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void postSellTrade() throws Exception {
        Trade trade = new Trade("BOA", Instant.now(), 102, new BigDecimal(636));
        URL url = new URL(baseURL, "trade/sell");
        RequestEntity<Trade> request = RequestEntity.post(url.toURI())
                .contentType(MediaType.APPLICATION_JSON).body(trade);

        ResponseEntity<Void> response = template.exchange(request, Void.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getStockPrice() throws Exception {
        URL url = new URL(baseURL, "stockPrice");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url.toString())
                .queryParam("stockName", "POP");
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = template.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), equalTo("5.00"));
    }

    @Test
    public void getPOPPeRatio() throws Exception {
        URL url = new URL(baseURL, "peRatio");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url.toString())
                .queryParam("stockName", "POP")
                .queryParam("price", 12.5);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = template.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), equalTo("19.5313"));
    }

    @Test
    public void getTEAPeRatioGivesError() throws Exception {
        URL url = new URL(baseURL, "peRatio");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url.toString())
                .queryParam("stockName", "TEA")
                .queryParam("price", 102.7);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = template.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody(), equalTo("Could not calculate PE Ratio since dividendYield is zero"));
    }

    @Test
    public void getMarketAllShareIndex() throws Exception {
        URL url = new URL(baseURL, "marketAllShareIndex");
        RequestEntity<Void> request = RequestEntity.get(url.toURI()).build();
        ResponseEntity<String> response = template.exchange(request, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), equalTo("2.9938"));
    }

}