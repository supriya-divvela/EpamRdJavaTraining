package com.epam.stepDefinition;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.epam.dto.BatchDto;
import com.epam.dto.MenteeDto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BatchSteps {

	@LocalServerPort
	private int port;
	private ResponseEntity<BatchDto> responseEntity;
	private ResponseEntity<List<BatchDto>> responseOfAllBatches;
	private ResponseEntity<Void> responseEntityForDelete;
	private BatchDto batchDto;
	private int id;
	private static RestTemplate restTemplate = new RestTemplate();
	private String baseUrl = "http://localhost:";

	@Given("batch {int}")
	public void batchId(int id) {
		this.id = id;
		baseUrl = baseUrl.concat(port + "").concat("/batch");
	}

	@When("requested to find batch")
	public void requestedToFindBatch() {
		restTemplate.postForEntity(baseUrl,
				new BatchDto(1, "java", new ArrayList<>(List.of(new MenteeDto(1, "supriya", "sup@gmail.com")))),
				BatchDto.class);
		responseEntity = restTemplate.getForEntity(baseUrl + "/" + id, BatchDto.class);
	}

	@Then("the status code should be {int}")
	public void giveBatchInfo(int expectedStatus) {
		assertEquals(expectedStatus, responseEntity.getStatusCode().value());
	}

	@Given("i will give a request body for batch")
	public void i_will_give_a_request_body_for_batch() {
		baseUrl = baseUrl.concat(port + "").concat("/batch");
	}

	@When("the user retrieve the details with {int} and {string} and {int},{string},{string}\"")
	public void the_user_retrieve_the_details_with_and_and(int batchId, String batchName, int menteeId,
			String menteeName, String email) {
		batchDto = new BatchDto();
		batchDto.setBatchId(batchId);
		batchDto.setBatchName(batchName);
		batchDto.setMentee(new ArrayList<>(Arrays.asList(new MenteeDto(menteeId, menteeName, email))));
		responseEntity = restTemplate.postForEntity(baseUrl, batchDto, BatchDto.class);
	}

	@Then("the user verify the response code {int}")
	public void the_user_verify_the_response_code(int responseCode) {
		assertEquals(responseCode, responseEntity.getStatusCode().value());
	}

	@Then("the user verify the response body {int} and {string} and {int},{string},{string}")
	public void the_user_verify_the_response_body_and_and(int batchId, String batchName, int menteeId,
			String menteeName, String email) {
		assertEquals(batchId, responseEntity.getBody().getBatchId());
		assertEquals(menteeId, responseEntity.getBody().getMentee().get(0).getMenteeId());
	}

	@Given("batch url")
	public void batch_url() {
		baseUrl = baseUrl.concat(port + "").concat("/batch");
	}

	@When("requested to find all batch")
	public void requested_to_find_all_batch() {
		responseOfAllBatches = restTemplate.exchange(baseUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<BatchDto>>() {
				});
	}

	@Then("the status code should for all batches {int}")
	public void the_status_code_should_for_all_batches(Integer responseCode) {
		assertEquals(responseCode, responseOfAllBatches.getStatusCode().value());
		assertTrue(responseOfAllBatches.getBody().size() > 0);
	}

	@Given("delete batch with id {int}")
	public void delete_batch_with_id(Integer id) {
		this.id = id;
		baseUrl = baseUrl.concat(port + "").concat("/batch");
	}

	@When("requested to delete batch")
	public void requested_to_delete_batch() {
		responseEntityForDelete = restTemplate.exchange(baseUrl + "/" + id, HttpMethod.DELETE, null, Void.class);
	}

	@Then("the status code for delete batch should be {int}")
	public void the_status_code_for_delete_batch_should_be(Integer responseCode) {
		assertEquals(responseCode, responseEntityForDelete.getStatusCode().value());
	}

	@Given("i will give a request body to update a batch")
	public void i_will_give_a_request_body_to_update_a_batch() {
		baseUrl = baseUrl.concat(port + "").concat("/batch");
	}

	@When("the user retrieve the details for update request with {int} and {string} and {int},{string},{string}\"")
	public void the_user_retrieve_the_details_for_update_request_with_and_and(Integer batchId, String batchName,
			Integer menteeId, String menteeName, String email) {
		batchDto = new BatchDto();
		batchDto.setBatchId(batchId);
		batchDto.setBatchName(batchName);
		batchDto.setMentee(new ArrayList<>(Arrays.asList(new MenteeDto(menteeId, menteeName, email))));
		restTemplate.postForEntity(baseUrl,
				new BatchDto(1, "python", new ArrayList<>(List.of(new MenteeDto(1, "pavan", "pavan@gmail.com")))),
				BatchDto.class);
		restTemplate.put(baseUrl + "/" + batchId, batchDto, BatchDto.class);
		responseEntity = restTemplate.getForEntity(baseUrl + "/" + batchId, BatchDto.class);
	}

	@Then("the user verify the response code for update should be {int}")
	public void the_user_verify_the_response_code_for_update_should_be(Integer responseCode) {
		assertEquals(responseCode, responseEntity.getStatusCode().value());
	}

	@Then("the user verify the updated response body {int} and {string} and {int},{string},{string}")
	public void the_user_verify_the_updated_response_body_and_and(Integer batchId, String batchName, Integer menteeId,
			String menteeName, String email) {
		assertEquals(batchId, responseEntity.getBody().getBatchId());
		assertEquals(menteeId, responseEntity.getBody().getMentee().get(0).getMenteeId());
	}
}